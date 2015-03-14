# Introduction #

This page will contain brief info regarding the maven projects of JSF Flex.


# Details #


## jsf-flex-build-plugIn ##
A plug-in project that :
  * utilizes qdox open source project to inspect the `JavaDocs` in order to generate replaceMapping XML for jsf-flex/renderKit14 project `[`**deprecated**`]`.
  * generates componentValueMapper.xml for jsf-flex-shared/core project by inspecting the `JavaDocs` or annotations for renderKit14 **deprecated** project or renderKit15 project respectively.
  * generates jsfFlexMainSwcConfigurationFile.xml by inspecting `@ISwcActionScriptFile` annotation


## jsf-flex/core ##
Contains code pertinent to JSF components. Since the project uses myfaces plug-in, tld, faces\_config.xml, tagClass, and etcetera are created during build time.


## jsf-flex/core-mojarra-impl **deprecated** ##
Contains following list of classes specific to Mojarra impl as MXML\_BASIC renderkit needs to wrap HTML\_BASIC renderKit `[in order to allow mixing of MXML + HTML components`], but there exists a difference in how Mojarra + `MyFaces` impl adds HTML\_BASIC renderkit to the factory :
  * MXMLRenderKitFactoryImplWrapper.java
  * MXMLRenderKitImpl.java
  * MXMLRenderKitImplWrapper.java
  * MXMLResponseStatemanagerImpl.java
  * MXMLResponseWriterImpl.java


## jsf-flex/core-myFaces-impl **deprecated** ##
Contains following list of classes specific to `MyFaces` impl as MXML\_BASIC renderkit needs to wrap HTML\_BASIC renderKit `[in order to allow mixing of MXML + HTML components]`, but there exists a difference in how Mojarra + `[MyFaces]` impl adds HTML\_BASIC renderkit to the factory :
  * MXMLRenderKitFactoryImplWrapper.java
  * MXMLRenderKitImpl.java
  * MXMLRenderKitImplWrapper.java
  * MXMLResponseStatemanagerImpl.java
  * MXMLResponseWriterImpl.java


## jsf-flex/examples ##
A WAR project containing sample jsf pages for the project.

## jsf-flex-shared ##
A project containing shared resource for other projects. Will also contain system Actionscript files.

## runnerImpl/commonTaskRunnerImpl ##
Contains projects that implement the `_CommonTaskRunner` interface used by jsf-flex-shared/core `[i.e. sdkStandardCommonTaskRunnerImpl project]`. There exists a task to unzip the Flex SDK to user's environment. One thing to note, `StubStandardCommonTaskRunnerImpl` should be only used when one does not wish to go through the painful process of unzipping the `flexSDK`, though it has been partitioned into smaller files it still takes a bit of time.

## runnerImpl/fileManipulatorTaskRunnerImpl ##
Contains projects that extends the `_FileManipulatorTaskRunner` abstract class used by jsf-flex-shared/core `[i.e. velocityFileManipulatorTaskRunnerImpl project]`. There exist various tasks for file manipulations `[i.e. creation of preMxml and etcetera]`.

## runnerImpl/flexTaskRunnerImpl ##
Contains projects that implement the `_FlexTaskRunner` interface used by jsf-flex-shared/core `[i.e. antFlexTaskRunnerImpl project]`. There exist various tasks for the project `[i.e. createSwf and etcetera]`.

## jsf-flex/renderKit14 JRE < 1.5 **deprecated** ##
For renderKit14, during the build time documentation for java files will be inspected by the JSF Flex plug-in and will generate replaceMapping XML files that will be parsed to check which fields to inspect and how to fetch these fields from the JSF component `[by attribute map or by method invocation]. Contains an implementation of _AnnotationDocletParser interface used by jsf-flex-shared/core for fetching the fields from the JSF component, AnnotationDocletParser14Impl]`

## jsf-flex/renderKit15 JRE > 1.4 ##
Since these components are suppose to be used for JRE >= 1.5, the attributes will be fetched during runtime through Java annotation and will not require JSF Flex plug-in during build time. Contains an implementation of `_AnnotationDocletParser` interface used by jsf-flex-shared/core for fetching the fields from the JSF component, `AnnotationDocletParser15Impl`.

## jsf-flex/annotations JRE > 1.4 ##
This maven project contains the annotations from other maven projects, such as from jsf-flex/renderKit15 maven project.

## jsf-flex-eclipse-plugIn ##
This project contains sub projects with reference to Eclipse plugin for the project.