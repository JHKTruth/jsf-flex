# Introduction #

Brief overview of JSF Flex Process


# Details #
  1. Set the renderKitId to FLEX\_BASIC for the view.
  1. All Flex components must be nested within `<jf:flexApplication />` tag as it is the top component and this tag must be nested within `<h:form />` tag.
  1. Template files `[a.k.a. preMxml files]` will be created by implementation of `_FileManipulatorTaskRunner` java file with default being `VelocityFileManipulatorTaskRunnerImpl`. These templates known as preMxml file will be created per component. Attributes will be added to these preMxml files by inspecting nested `<jf:flexAttributeNode />` tags for the component tag.
  1. Necessary source files will be created to their respective directory, such as
    * SWC file containing the system's code
    * SWF library from generated SWC file
    * XML file{s} that is/are needed by the SWC.
    * Extraction of Flex SDK
    * Invocation of copyLocale
  1. After all the component's preMxml within `<jf:flexApplication />` have been created, MXML file and SWF file will be created by implementation of interface `_FlexTaskRunner`, default is `AntFlexTaskRunnerImpl`. One additional thing to note is that mxmlPackageName attribute for `<jf:flexApplication />` tag will be used to name the MXML file and the application SWF file.
  1. Also `<jf:flexApplication />` will create JSON objects which will be rendered onto the page for initial values of the Flex components. The reason for this is because `<jf:flexApplication />` has various modes :
    * debugMode : meaning preMxml, MXML, and SWF files would be created in each run as well as JSON objects.
    * productionEnv : meaning SWF file already exists, so nothing to do here but create JSON objects `[this is the default value to ensure that users do not run the components in either of the two other modes in an environment that is higher than dev]`. One thing to note is that all directories of `Webcontent/swf/*` should be copied to their corresponding directory within the Workspace during the move to higher env `[since the system uses servContext.getRealPath("") as the root directory of preMxml, MXML, and SWF directory and]`
      * Tomcat on default for example flushes it out to their cache directory under metadata `[i.e. C:\OpenSource\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsf-flex-examples]`
      * JBoss on default for example flushes it out to their temp directory `[i.e. C:\Program Files\jboss-4.2.3.GA\server\default\tmp\deploy\tmp46689jsf-flex-examples.war]`
> > For large data components such as `DataGrid`, information will be passed from server, `JsfFlexHttpServicePhaseListener.java`, to client, `JsfFlexHttpService.as`.
  1. Uses Javascript during the onLoad time to connect all the Form's onsubmit event with pageUnload function. Also each Flex/Flash app will communicate back to the page when it has finished setting up on its side `[such as setting callBack methods and etcetera]`, JSON will be passed to the apps to set the initial values. So the values will be decoded `[for example htmlText allows XML tags and etcetera, so on the Java side there will be encoding and decoding on the Flex/Flash side]` and set to the component's values.
  1. During the form's onsubmit action, pageUnload will return false and make a call to Flex/Flash app to return the component's values to the page. When returned, nodes will be appended to the form that was in the process of submission and will submit the page. On the Flex/Flash side, there will be an Actionscript which will use E4X, love it, to look up within an XML file the property/attributes that the component must return as JSON objects to the page, so that the page can create the elements with their attributes as child nodes of the form element.

One note regarding preMxml files. In order to keep everything simple and debugging easy, a simple method was chosen to create the preMxml files for each component. Namely the file with `<mxmlPackageName>_<classSimpleName>_<Major Num><Minor Num>.pre_mxml` will be created per component within directory named `Webcontent/preMxml/<mxmlPackageName>/`. So for instance, FlexUIApplication will have Major Num and Minor Num set to 0 and the first child of FlexUIApplication will have {1,0} set with it's sibling set to {1,1} and etcetera.