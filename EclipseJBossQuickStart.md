# Introduction #

This page contains brief overview in importing the project into Eclipse 3.4.0 for JBoss 4.2.


# Details #

## Importing the generated WAR artifact ##
  1. Follow the usual path of importing a WAR file.
> > ![http://jsf-flex.googlecode.com/svn/wiki/importWarEclipseJBoss42.jpg](http://jsf-flex.googlecode.com/svn/wiki/importWarEclipseJBoss42.jpg)
  1. Then delete JSF API + IMPL Jar within `WebContent/WEB-INF/lib` directory
> > ![http://jsf-flex.googlecode.com/svn/wiki/deleteJsfApiImplJars.jpg](http://jsf-flex.googlecode.com/svn/wiki/deleteJsfApiImplJars.jpg)

## Importing the Jar artifacts for a Dynamic Web Project ##
  1. First create the Dynamic Web Project with the default Configuration for JBoss 4.2 {technically can set it to JSF 1.2 here, but will go with this path}.
> > ![http://jsf-flex.googlecode.com/svn/wiki/createNewDynamicWebProjectEclipseJBoss42.jpg](http://jsf-flex.googlecode.com/svn/wiki/createNewDynamicWebProjectEclipseJBoss42.jpg)
  1. Then configure the project with JSF 1.2 impl
    * Select the project and right click.
    * Select the Properties option.
    * Select Project Facets and click on `JavaServer` Faces with version 1.2.
    * Click on Further configuration required.
    * Select the default **Server Supplied JSF Implementation**
      * If one wishes to use a different JSF impl than the one provided from the Server `{i.e. MyFaces`}, provide the impl Jars within the WEB-INF/lib directory and add the following within the web.xml :
```
<context-param>
  <param-name>org.jboss.jbossfaces.WAR_BUNDLES_JSF_IMPL</param-name>
  <param-value>true</param-value>
</context-param>
```
> > > Then JBoss will use the implementation within the classpath of the project rather than from its default/provided impl.

  * Add additional URL Mapping Patterns for `FacesServlet` with `/jsfFlexHttpServiceRequestListener/* and *.jsf`.

> > ![http://jsf-flex.googlecode.com/svn/wiki/projectFacetsEclipseJBoss42.jpg](http://jsf-flex.googlecode.com/svn/wiki/projectFacetsEclipseJBoss42.jpg)
> > ![http://jsf-flex.googlecode.com/svn/wiki/settingJsf12BasePropertiesEclipseJBoss42.jpg](http://jsf-flex.googlecode.com/svn/wiki/settingJsf12BasePropertiesEclipseJBoss42.jpg)
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
    For JSF Flex build mode, possible values are (debugMode,
    simplySwfMode, productionMode [default])
  </description>
  <param-name>com.googlecode.jsfFlex.MODE</param-name>
  <param-value>debugMode</param-value>
</context-param>

<filter>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <filter-class>com.googlecode.jsfFlex.filter.JsfFlexResourceFilter</filter-class>
</filter>

<!--
  The below filter-mapping is to generate a standard <script> html
  elements within the html head, so to have /jsfFlexResourceRequest/* filter-mapping
  generate those resources
-->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>*.jsf</url-pattern>
</filter-mapping>

<!--
  The below filter-mapping is used to respond to script requests from
  *.jsf filter-mapping
-->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>/jsfFlexResourceRequest/*</url-pattern>
</filter-mapping>

<servlet>
  <servlet-name>Faces Servlet</servlet-name>
  <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
  <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
  <servlet-name>Faces Servlet</servlet-name>
  <url-pattern>*.jsf</url-pattern>
</servlet-mapping>

<servlet-mapping>
  <servlet-name>Faces Servlet</servlet-name>
  <url-pattern>/jsfFlexHttpServiceRequestListener/*</url-pattern>
</servlet-mapping>
```
> > Note the need to have two servlet-mapping for the different url-patterns. This is different in comparison to `TomCat 6.0 QuickStart`.
  1. Copy the contents from "C:\jsfFlex\jsf-flex\examples\target\jsf-flex-examples\WEB-INF\lib" minus the jsf api + jsf impl to the project's WEB-INF\lib directory.
> > ![http://jsf-flex.googlecode.com/svn/wiki/copyJarsToWebInfDirEclipseJBoss42.jpg](http://jsf-flex.googlecode.com/svn/wiki/copyJarsToWebInfDirEclipseJBoss42.jpg)
  1. Then you are done. Have fun!

If you wish to use Facelet for JSF Flex components, please refer to the following Wiki page in modifying web.xml and faces-config.xml files : [FaceletQuickStart](FaceletQuickStart.md). Note that Facelet support is being started from 0.7 version onwards, so it may have some quirks within this version.