# Introduction #

This page contains brief overview in importing the project into Eclipse 3.6.x for Tomcat 7.0


# Details #
Follow the usual path of importing a WAR file.

![http://jsf-flex.googlecode.com/svn/wiki/importWarEclipseTomCat70.jpg](http://jsf-flex.googlecode.com/svn/wiki/importWarEclipseTomCat70.jpg)

## Importing the Jar artifacts for a Dynamic Web Project ##
  1. First create the Dynamic Web Project with the default Configuration for Tomcat 7.0.
> > ![http://jsf-flex.googlecode.com/svn/wiki/createNewDynamicWebProjectEclipseTomCat70.jpg](http://jsf-flex.googlecode.com/svn/wiki/createNewDynamicWebProjectEclipseTomCat70.jpg)
  1. Then configure the project with JSF 2.0 impl
    * Select the project and right click.
    * Select the Properties option.
    * Select Project Facets and click on `JavaServer Faces` with version 2.0.
    * Click on Further configuration required.
    * Select the JSF Libraries `[can choose the Mojarra 2.0 or MyFaces 2.0 impl]`, for the example chose the `MyFaces` 2.0 impl. For simplicity, took the artifacts from the WAR download.
    * Add additional URL Mapping Patterns for `FacesServlet with /jsfFlexHttpServiceRequestListener/* and *.jsf`.
> > > ![http://jsf-flex.googlecode.com/svn/wiki/projectFacetsEclipseTomCat70.jpg](http://jsf-flex.googlecode.com/svn/wiki/projectFacetsEclipseTomCat70.jpg)
> > > ![http://jsf-flex.googlecode.com/svn/wiki/settingJsf12BasePropertiesEclipseTomCat70.jpg](http://jsf-flex.googlecode.com/svn/wiki/settingJsf12BasePropertiesEclipseTomCat70.jpg)
  1. Then modify web.xml by adding the following content :
```
<!-- Below is for Facelet support with the example pages ending in standard .xhtml extension -->
<context-param>
  <param-name>javax.faces.FACELETS_VIEW_MAPPINGS</param-name>
  <param-value>*.xhtml</param-value>
</context-param>

<!-- To use JSP View Handler, although discouraged for JSF. Comment the content regarding facelet and uncomment the below
<context-param>
  <param-name>javax.faces.DEFAULT_SUFFIX</param-name>
  <param-value>.jsp</param-value>
</context-param>
-->

<context-param>
  <param-name>facelets.DEVELOPMENT</param-name>
  <param-value>true</param-value>
</context-param>

<context-param>
  <param-name>org.jboss.jbossfaces.WAR_BUNDLES_JSF_IMPL</param-name>
  <param-value>true</param-value>
</context-param>

<context-param>
  <description>Comma separated list of URIs of (additional) faces config files. (e.g. /WEB-INF/my-config.xml) See JSF 1.0 PRD2, 10.3.2 Attention: You may not put /WEB-INF/faces-config.xml in here.
  </description>
  <param-name>javax.faces.CONFIG_FILES</param-name>
  <param-value>/WEB-INF/examples-config.xml</param-value>
</context-param>

<context-param>
  <description>State saving method: "client" or "server" (= default)
    See JSF Specification 2.5.3</description>
  <param-name>javax.faces.STATE_SAVING_METHOD</param-name>
  <param-value>server</param-value>
</context-param>

<!-- 
  <context-param>
    <description>
      The generated SWF files and etcetera will be copied to this directory, so that user won't have to perform the manual operation from the server's temp directory [i.e. C:\Workspace\Server\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SampleProject\swf\]
  </description>
  <param-name>com.googlecode.jsfFlex.ProjectWorkspaceWebFlashDirectory</param-name>
  <param-value>C:\Workspace\SampleProject\WebContent\swf</param-value>
</context-param>
-->

<!-- 
<context-param>
  <description>
    Java path for Flex SDK, in case user is using a Windows 64bit applications [Flex requires a Java SDK of 32bit]. Note that MSVCR71.dll file [if missing] must be downloaded and placed within C:\Windows\SysWOW64
  </description>
  <param-name>com.googlecode.jsfFlex.FlexJavaPath</param-name>
  <param-value>C:\Program Files (x86)\Java\jdk1.6.0_23</param-value>
</context-param>
-->

<context-param>
  <description>
    The relative web context path for the locale resources, if being used for multilingual application
  </description>
  <param-name>com.googlecode.jsfFlex.LocaleWebContextRelativePath</param-name>
  <param-value>locale</param-value>
</context-param>

<context-param>
  <description>
    The default locale
  </description>
  <param-name>com.googlecode.jsfFlex.DefaultLocale</param-name>
  <param-value>en_US</param-value>
</context-param>

<context-param>
  <description>
    For JSF Flex Flash To JavaScript Log Level, possible values are (Log, Debug, Info, Warn, Error). If the field is not provided, it will make a decision based on com.googlecode.jsfFlex.MODE with production being Error and non-production being Log. Specifically it is only allowed for Firebug unless the user uses Firebug Lite or has Dojo with debug set to true.
  </description>
  <param-name>com.googlecode.jsfFlex.FlashToJavaScriptLogLevel</param-name>
  <param-value>Debug</param-value>
</context-param>

<context-param>
  <description>For JSF Flex build mode, possible values are (debugMode, productionMode [default])
  </description>
  <param-name>com.googlecode.jsfFlex.MODE</param-name>
  <param-value>debugMode</param-value>
</context-param>

<!-- 
<context-param>
  <description>
    Parameter if the user already has a Flex SDK that is unzipped to a directory. Rationally this is advised, since it cuts down in development time as extraction of flexSDK zip file is rather long. Also a lightweight version of sdk-standard-common-impl.jar will be provided since much of the jar size and memory consumption is due to the size of flexSDK zip file.
  </description>
  <param-name>flexSDKPath</param-name>
  <param-value>C:\\arena\\flexSDK\\</param-value>
</context-param>
-->

<filter>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <filter-class>com.googlecode.jsfFlex.filter.JsfFlexResourceFilter</filter-class>
</filter>
	
<!-- The below filter-mapping is to generate a standard <script> html elements within the html head, so to have /jsfFlexResourceRequest/* filter-mapping generate those resources  -->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>*.jsf</url-pattern>
</filter-mapping>

<!-- The below filter-mapping is used to respond to script requests from *.jsf filter-mapping -->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>/jsfFlexResourceRequest/*</url-pattern>
</filter-mapping>

<!-- The below filter-mapping is used for Facelet support -->
<filter-mapping>
  <filter-name>jsfFlexResourceFilter</filter-name>
  <url-pattern>/faces/*</url-pattern>
</filter-mapping>
```
  1. Copy the contents from "C:\jsfFlex\jsf-flex\examples\target\jsf-flex-examples\WEB-INF\lib" minus the JSF impl to the project's WEB-INF\lib directory.

> > ![http://jsf-flex.googlecode.com/svn/wiki/copyJarsToWebInfDirEclipseTomCat70.jpg](http://jsf-flex.googlecode.com/svn/wiki/copyJarsToWebInfDirEclipseTomCat70.jpg)
  1. Then you are done. Have fun!