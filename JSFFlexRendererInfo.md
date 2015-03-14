# Introduction #

Brief overview of JSF Flex Renderers

# Details #

## Overview ##
There are two maven projects for JSF Flex Renderers, namely renderKit14 and renderKit15, so to provide capability for JRE < 1.5 and JRE >= 1.5 {will let readers figure out which one is what =)}. The decision of which maven project gets linked with the examples project will be determined by the arguments passed during build time, but more on that later.

## UIComponent field inspection ##

### renderKit14 - deprecated from release-1.0.1b+ ###
For renderKit14, during the build time Javadocs will be inspected by the JSF Flex Plug-in to generate replaceMapping XML files that will be parsed during runtime to check which fields to inspect and how to fetch these fields from the sub-classes of UIComponent {by attribute map or by method invocation}.

### renderKit15 ###
Since these components are suppose to be used for JRE >= 1.5, the attributes will be fetched during runtime through annotation and will not require JSF Flex Plug-in during build time.

### Etcetera ###
In order to have a single entry point for retrieving these info for jsf-flex-shared-core project, each will implement `_AnnotationDocletParser` interface provided from the jsf-flex-shared-core project {i.e. renderKit14 will have
`AnnotationDocletParser14Impl` and renderKit15 will have `AnnotationDocletParser15Impl` }. Then during build time, the correct parameters will be set for the jsf-flex-shared-core's factory and will instantiate the correct implementation during runtime.


## Main points for JSF Flex Renderers ##
Main thing to note in terms of design for the renderers are the following :
  * Classes which are not concrete, meaning `MXML.+TemplateRenderer` will mainly have a method called mapFields which is simply to inspect either the replaceMapping XML or annotation {depending on the JRE version} to retrieve the values from the sub-class of UIComponent during the JSF Flex's encodeBegin lifecycle.
  * Concrete classes have a method createPreMxml which is to create the preMxml file with the responsibility of creating these files lie with implementations of `_FileManipulatorTaskRunner` interface {default being  `VelocityFileManipulatorTaskRunnerImpl` }. The implementation choice for this interface and two other interfaces `_CommonTaskRunner` and `_FlexTaskRunner` are determined during the maven build process.
  * `MXMLComponentBaseRenderer` class during the encodeEnd will write the body content of the component {i.e. MXMLScript} as long as the correct content is pushed to the attribute map. For an example, view the `MXMLUIScriptTag` within the jsf-flex project where the body content is pushed to the component's attribute map as MXMLConstants.TAG\_BODY\_CONTENT\_ATTR.
  * MXMLApplicationRenderer is a special class that has an added responsibility during its encodeEnd to
    * merge the preMxml files into a MXML file
    * create SWC file for the system's library
    * extract the SWF file from SWC for the shared resource of all application SWF files
    * flush necessary resources {i.e. componentValueMapper.xml} for the system's library to the correct directory
    * extract the flexSDK to the correct directory
    * create the application's SWF file {for multilingual application will create a SWF file for each locale}.
    * will invoke copyLocale if the application is multilingual **for release-1.0.1b+**