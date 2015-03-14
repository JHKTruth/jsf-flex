# Introduction #

This page contains a brief overview in changing web.xml and faces-config.xml files to use Facelet for JSF Flex components. It is assumed that one already has a JSF project that has JSP as its default view handler and that one is using standard .xhtml files as Facelet extension.

# Details #

  1. First modify web.xml file to include .xhtml extension as Facelet's view mapping :
```
    <context-param>
      <param-name>facelets.VIEW_MAPPINGS</param-name>
      <param-value>*.xhtml</param-value>
    </context-param>
```
  1. Then one needs to have a mapping so that `FacesServlet` and `JsfFlexResourceFilter` handles the requests of .xhtml files, so add the following within web.xml :
```
     <filter-mapping>
      <filter-name>jsfFlexResourceFilter</filter-name>
      <url-pattern>/faces/*</url-pattern>
     </filter-mapping>
     
     <servlet-mapping>
      <servlet-name>Faces Servlet</servlet-name>
      <url-pattern>/faces/*</url-pattern>
     </servlet-mapping>
```
  1. Finally modify faces-config.xml file to include Facelet View Handler :
```
     <application>
       <locale-config>
         <default-locale>en</default-locale>
       </locale-config>
       <view-handler>com.sun.facelets.FaceletViewHandler</view-handler>
     </application>
```

That is it and here are examples for referring to .jsp with the default JSP view handler and .xhtml with the Facelet view handler :
|View Handler|Sample URL|
|:-----------|:---------|
|JSP|`http://localhost:8081/jsf-flex-examples/mxmlIndex.jsf`|
|Facelet using XHTML|`http://localhost:8081/jsf-flex-examples/faces/mxmlIndex.xhtml`|

For ease, here is the content for the complete web.xml file :
```
<web-app xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" version="2.4">
   
   <!-- Below is for Facelet support with the example pages ending in standard .xhtml extension -->
   <context-param>
     <param-name>facelets.VIEW_MAPPINGS</param-name>
     <param-value>*.xhtml</param-value>
   </context-param>
   
   <context-param>
     <description>Comma separated list of URIs of (additional) faces config files.
              (e.g. /WEB-INF/my-config.xml)
              See JSF 1.0 PRD2, 10.3.2
              Attention: You may not put /WEB-INF/faces-config.xml in here.
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
   
   <context-param>
     <description>
      	For JSF Flex Flash To JavaScript Log Level, possible values are (Log, Debug, Info, Warn, Error) If the field is not provided, it will make a decision based on com.googlecode.jsfFlex.MODE with production being Error and non-production being Log
     </description>
     <param-name>com.googlecode.jsfFlex.FlashToJavaScriptLogLevel</param-name>
     <param-value>Info</param-value>
   </context-param>
   
   <context-param>
     <description>For JSF Flex build mode, possible values are (debugMode, simplySwfMode, productionMode [default])
     </description>
     <param-name>com.googlecode.jsfFlex.MODE</param-name>
     <param-value>debugMode</param-value>
   </context-param>
   
   <filter>
     <filter-name>jsfFlexResourceFilter</filter-name>
     <filter-class>com.googlecode.jsfFlex.filter.JsfFlexResourceFilter</filter-class>
   </filter>

   <!-- The below filter-mapping is to generate a standard <script> html elements within the html head, so to have /jsfFlexResourceRequest/* filter-mapping generate those resources -->
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
   
   <!-- Faces Servlet -->
   <servlet>
     <servlet-name>Faces Servlet</servlet-name>
     <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
     <load-on-startup>1</load-on-startup>
   </servlet>

   <!-- Faces Servlet Mapping -->
   <servlet-mapping>
     <servlet-name>Faces Servlet</servlet-name>
     <url-pattern>*.jsf</url-pattern>
   </servlet-mapping>
   
   <!-- Below mapping is for JsfFlexHttpServicePhaseListener which will handle asynchronous requests from the client -->
   <servlet-mapping>
     <servlet-name>Faces Servlet</servlet-name>
     <url-pattern>/jsfFlexHttpServiceRequestListener/*</url-pattern>
   </servlet-mapping>
   
   <!-- Below mapping is for Facelet support -->
   <servlet-mapping>
     <servlet-name>Faces Servlet</servlet-name>
     <url-pattern>/faces/*</url-pattern>
   </servlet-mapping>
    
</web-app>
```