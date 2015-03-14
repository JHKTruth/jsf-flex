# Introduction #

This page contains brief overview in importing the project into Eclipse 3.4.0 for Tomcat 6.0

# Details #

## Importing the generated WAR artifact ##
Follow the usual path of importing a WAR file.

![http://jsf-flex.googlecode.com/svn/wiki/importWarEclipseTomCat60.jpg](http://jsf-flex.googlecode.com/svn/wiki/importWarEclipseTomCat60.jpg)

## Importing the Jar artifacts for a Dynamic Web Project ##
  1. First create the Dynamic Web Project with the default Configuration for Tomcat 6.0 {technically can set it to JSF 1.2 here, but will go with this path}.
> > ![http://jsf-flex.googlecode.com/svn/wiki/createNewDynamicWebProjectEclipseTomCat60.jpg](http://jsf-flex.googlecode.com/svn/wiki/createNewDynamicWebProjectEclipseTomCat60.jpg)
  1. Then configure the project with JSF 1.2 impl
    * Select the project and right click.
    * Select the Properties option.
    * Select Project Facets and click on `JavaServer` Faces with version 1.2.
    * Click on Further configuration required.
    * Select the JSF Libraries {can choose the Mojarra 1.2 or `MyFaces` 1.2 impl}, for the example chose the Mojarra 1.2 impl as it is Sun's impl. For simplicity, took the jsf-api + jsf-impl from the "C:\jsfFlex\jsf-flex\examples\target\jsf-flex-examples\WEB-INF\lib" directory.
    * Add additional URL Mapping Patterns for `FacesServlet` with `/jsfFlexHttpServiceRequestListener/* and *.jsf`.
> > > ![http://jsf-flex.googlecode.com/svn/wiki/projectFacetsEclipseTomCat60.jpg](http://jsf-flex.googlecode.com/svn/wiki/projectFacetsEclipseTomCat60.jpg)
> > > ![http://jsf-flex.googlecode.com/svn/wiki/settingJsf12BasePropertiesEclipseTomCat60.jpg](http://jsf-flex.googlecode.com/svn/wiki/settingJsf12BasePropertiesEclipseTomCat60.jpg)
  1. Then modify web.xml by adding the following content :
```
<context-param>
  <description>
    For JSF Flex Flash To JavaScript Log Level, possible values are (Log, Debug, Info, 
    Warn, Error)  If the field is not provided, it will make a decision based on 
    com.googlecode.jsfFlex.MODE with production being Error and non-production being Log
  </description>
  <param-name>com.googlecode.jsfFlex.FlashToJavaScriptLogLevel</param-name>
  <param-value>Info</param-value>
</context-param>

<context-param>
  <description>
    For JSF Flex build mode, possible values are (debugMode, simplySwfMode, productionMode
    [default])
  </description>
  <param-name>com.googlecode.jsfFlex.MODE</param-name>
  <param-value>debugMode</param-value>
</context-param>
    
<filter>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <filter-class>com.googlecode.jsfFlex.filter.JsfFlexResourceFilter</filter-class>
</filter>
	
<!-- The below filter-mapping is to generate a standard <script> html elements within the
     html head, so to have /jsfFlexResourceRequest/* filter-mapping generate those 
     resources  -->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>*.jsf</url-pattern>
</filter-mapping>
    
<!-- The below filter-mapping is used to respond to script requests from *.jsf filter-mapping -->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>/jsfFlexResourceRequest/*</url-pattern>
</filter-mapping>
```
  1. Copy the contents from "C:\jsfFlex\jsf-flex\examples\target\jsf-flex-examples\WEB-INF\lib" minus the jsf api + jsf impl to the project's WEB-INF\lib directory.

> > ![http://jsf-flex.googlecode.com/svn/wiki/copyJarsToWebInfDirEclipseTomCat60.jpg](http://jsf-flex.googlecode.com/svn/wiki/copyJarsToWebInfDirEclipseTomCat60.jpg)
  1. Then you are done. Have fun!

If you wish to use Facelet for JSF Flex components, please refer to the following Wiki page in modifying web.xml and faces-config.xml files : [FaceletQuickStart](FaceletQuickStart.md). Note that Facelet support is being started from 0.7 version onwards, so it may have some quirks within this version.