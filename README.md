**Abstract**

**For ref, due to HTML5's ever growing presence I personally don't see much future with Flex.**

JSF Flex's main goals are :
  * Provide users capability in creating standard Flex components as JSF components [note dynamic charts and some other components are not part of standard Flex SDK]. By doing so users can be ignorant to Flash and Actionscript as the project will create the necessary content to bind the client side to the server side. For example in order to create a `s:TextInput` component [note that there exists an attribute nameSpaceOverride in case one wishes to use the other namespace, namely mx for now] all one would need to do is utilize the jf:flexTextInput tag and bind text attribute to a server managed bean. Then the project will create the necessary MXML, SWC, SWF files and etcetera and link the values of the components back to the managed beans using JSON+Javascript and Actionscript. Also since renderKitId FLEX_BASIC's renderKit wraps other renderKits,  one would be able to use other renderers from other renderKits within the same view [i.e. HTML_BASIC].
  * Support Locale for the Flex components using the familiar Locale directory/properties of web applications. For example, by providing: 
    * the following value to a JSF Flex tag's proper attribute `{{{"@Resource(bundle='LocaleExample', key='greeting')"}}}`
    * usual Locale directory setup for web applications
    * a config within web.xml dictating the relative path of the Locale directory to WebContent
    the project will pull the content from the .properties file, utilize it for Flex SDK's copylocale command, and will create corresponding SWF files [one for each Locale]. Then it will use the proper SWF file based on the Locale of each request.
  * Augment certain Flex components to allow proper usage with web applications. Following is a wiki page that provides a brief overview of some of the interesting augmented components [InterestingComponents]. For example, `DataGrid` component was augmented: 
    * Allowing users to fetch data from the server side in small partitions with standard JEE data binding.
    * Allowing drag + drop from one Grid to an another when the Grid was remote.
    * Allowing filtering of the server's grid content by
      * first filtering until the requested partition size of the data has been returned to the client
      * then partitioning the remaining data set into smaller chunks with each chunk being spanned off in its own thread for filtering
      * finally having them joined at the end
  * Allow integration of events from the client side to the server. For example jf:flexAsynchronousPropertyUpdateEventListener tag allows user to listen to certain events [i.e. change] and when the event is dispatched an asynchronous call would be made to the server side which will receive the changed value and return a value which will be utilized to update the Flex component.
  * Provide tools for better usage of the framework. For example there does exist an Eclipse plugin where if enabled will allow user to fetch the attribute listing of a corresponding Flex component to the selected JSF FLex's tag. [JSFFlexActionScriptAttributesEclipsePlugin]
  * And etcetera

There exists couple of configurations that one can enable for the application and they have been listed within the example WAR's web.xml.

Following is a Wiki page containing info of **JSF Flex project's Maven repo** which has been created on April 18th, 2009 : [JsfFlexMavenRepo].

The project aim is to support the following JSF implementations:
 * **MyFaces**
   * 1.2
   * 2.0
 * **Mojarra**
   * 1.2
   * 2.0

**Quick Start**

Following Wiki page contains info in importing the example WAR file and adding the project's Jar artifacts to a new Dynamic Web Project => [QuickStart].

Since following information is so critical, it is being duplicated within the home page. When one is finished tweaking of creating the components/tags, one **MUST** change the com.googlecode.jsfFlex.MODE field within web.xml to productionMode to avoid the cost of creating preMxml, MXML, SWF, and etceteras. Also one should copy all the system generated directories under {{{Webcontent/swf/*}}} to their corresponding directory within the Workspace during the move to higher env [since the system uses `servContext.getRealPath("")` as the root directory of preMxml, MXML, and SWF directory and]
    * Tomcat on default for example flushes it out to their cache directory under metadata [i.e. C:\OpenSource\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsf-flex-examples]
    * JBoss on default for example flushes it out to their temp directory [i.e. C:\Program Files\jboss-4.2.3.GA\server\default\tmp\deploy\tmp46689jsf-flex-examples.war]

**Roadmap**

Please refer to the following wiki page for information regarding what is planed for support within release 0.5 ALPHA => [RoadMap].

There exists a screen shot of an example at the bottom of this page [will provide a better screen shot later] following is the URL for the current SNAPSHOT download [note that I wished not to deploy the artifacts every single time there exists an update to the SNAPSHOT, so used buildnumber-maven-plugin to append the timestamp] => http://localhost:8080/jsf-flex-examples-0.5a-SNAPSHOT-1316484923203/faces/flexIndex.xhtml

**Testing Performed**

Brief testing has been performed using Sun's JVM as JAVA_HOME :

|| **Operating System** || **Browser Tested**                 || **Servlet Container/Application Server Tested** ||
|| Windows XP         || Firefox 2.0 , IE 6.0, Opera 9.25 || TomCat 6.0,7.0; JBoss 4.2,6.0           ||
|| Linux, Ubuntu 11.04              || FF 8.0                            || TomCat 7.0;                       ||
|| Mac  TODO          || TODO                             || TODO                       ||

**Rationale**

With the Web 2.0 technology flourishing within the web world, this would be a great opportunity in creating a project and bridging technologies such as Adobe Flex with JSF.

Specifically the project will provide :
 # Easy creation of standard Flex applications while preserving the ease of databinding to legacy systems through JSF
 # An alternative to purchasing Flexbuilder when creating simple Flex applications

![ScreenShot](https://github.com/JHKTruth/playground-arena/blob/master/examples/generic/src/main/webapp/images/screenshots/overallExample.jpg?raw=true)
