# Introduction #

Brief overview in importing the project to respective IDE.

Beginning in the month of January the project will provide downloads, but till then for creating the artifacts from the latest source code, please refer to following wiki pages :
  * [ProjectBuild](ProjectBuild.md) - Page contains info in checking out latest source code from svn {please refer only to section Checking out/Extracting the resources from repository}
  * [JSFFlexBuildSystemInfo](JSFFlexBuildSystemInfo.md) - Page contains info in generating the artifacts {mvn commands}


# Details #

  * [EclipseQuickStart](EclipseQuickStart.md) - Page contains brief overview for Eclipse IDE.

# Critical NOTE #
Following information is extremely **CRITICAL**. When one is finished tweaking of creating the components/tags, one **MUST** change the **com.googlecode.jsfFlex.MODE** field within web.xml to productionMode to avoid the cost of creating preMxml, Mxml, Swf, Swc, and etceteras. Also one should copy all the system generated directories under `Webcontent/swf/*` to their corresponding directory within the Workspace during the move to higher env {since the system uses servContext.getRealPath("") as the root directory of preMxml, MXML, and SWF directory and}
  * Tomcat on default for example flushes it out to their cache directory under metadata i.e. `C:\OpenSource\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsf-flex-examples`
  * JBoss on default for example flushes it out to their temp directory i.e. `C:\Program Files\jboss-4.2.3.GA\server\default\tmp\deploy\tmp46689jsf-flex-examples.war`