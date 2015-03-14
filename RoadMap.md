## Work to be done ##

---

JSF Flex goal is to provide users capability in creating standard Flex components as JSF components. In order to provide quality product for the users, the project must support :
  * various operating systems
    * Windows
    * Linux
    * Mac
  * various browsers
    * Firefox
    * IE
    * Opera
    * Safari
  * various servlet container/application servers
    * `TomCat`
    * `JBoss`


## Milestones ##

---

### Release 0.5 ALPHA ###

**NOTE There was a rewrite and that's why this is the LATEST release, time is only thing that can't go back right <8^)? Most of the features have been preserved, but the biggest change was removal of inclusion of attributes within the tags; however that has been compensated with the provided Eclipse plugin which will help in observing the attributes + detail of the selected tag's Flex component.**

#### Release Date ####
TBD

#### Planned ####
  * Support of JSF 2.0
  * Support of latest, stable Flex version `[i.e. 4.0]`. For dual components `[can have namespace of mxml or spark]` priority will be given to spark, but users will be able to use an alternative namespace by providing a field to the nameSpaceOverride attribute of the tag.
  * Capability of using `MethodExpression` for additional components.
    * Asynchronous data update event listener for listening to a specific event of a source Flex component, so that when it is triggered target Flex component's value is updated with the value returned from an asynchronous call that is bound by `MethodExpression` on the server side.
  * Additional functionality for componenets such as `DataGrid` component where there will be further interweaving of JSF/Java + Actionscript content.
  * Removal of unnecessary core-mojarra-impl + core-myFaces-impl Jar files
  * Attributes for XHTML will need to be provided as sub-tag of the tag. Meaning other than fields which are project specific or data bound to the component, attributes should be provided as a sub-tag of the component `[Can consider it to be attribute node of the node element`]
  * Eclipse plugin that will fetch and render the attributes for the Flex component of the currently selected tag `[to compensate for the attribute removal in the previous modification]`

### Release 1.0.1 BETA ###

#### Release Date ####
December 10, 2009

#### Accomplished ####
  * Change from usage of Qdox Javadoc to Annotation
  * Code written in JDK 5.0
  * Support of Flex version 3.4 `[i.e. DateChooser]` component and locale support according to the change needed of copyLocale execution for Flex version 3.x}
  * Capability to submit the form data using mxmlButton or mxmlLinkButton tags
  * Tweaking of performance `[usage of Executors in some of the tasks to possibly assist in creation of multiple SWF + locale; however swc + swf creation is dependent upon Flex SDK]`
  * Capability in creating components outside of JSF Flex by providing the component name + attributes as tag attributes
  * **One thing to note is the size of the download artifacts. Since Flex SDK now comes packaged with Air tools to allow creation of Air applications for Flex components, the size of flexSDK has been increased rather largely `[that's the reason the first hit to any .xhtml or .jsf page will take a bit of time, but afterward the time should be relatively similar when hitting additional pages to create SWF, SWC, and etcetera within debugMode. please note that when one is done, one should change the configuration to productionMode to see it's actual performance]`. There exists two reasons of why Air library hasn't been ripped out of flexSDK that is downloaded with the Jars :
    * First is for safety, meaning it is better to leave them in then to find out later that one needs certain Jars that has been removed
    * Second is a possible investigation of creation of Air. Strong emphasis on possible.**
### Release 1.0 BETA ###

#### Release Date ####
Approximate target date is April 15, 2009

#### Accomplished ####
Currently the project supports most of the Flex standard components as well as functionalities that have been added on for JSF application `[i.e. DataGrid components can have their data bounded to managedBeans, so that large information will be partitioned into batches and the pieces of the information being fetched asynchronously through Actionscript]`. Brief testing has been done on Windows XP operating system with the following list of browsers :
  * Firefox 2.0, 3.6
  * IE 6.0, 7.0
  * Opera 9.25
on application server `TomCat 6.0 and JBoss 4.2.`
This release supports Flex 2.

#### TODO ####
  * Complete testing on remaining OS, browsers, and application servers must be performed, so to support every type of user.
| **Operating System** | **Browser Tested**                 | **Application Server Tested**|
|:---------------------|:-----------------------------------|:-----------------------------|
| Windows XP         | Firefox 2.0, 3.6, IE 6.0, 7.0; Opera 9.25 | `TomCat 6.0, 7.0; JBoss 4.2, 6.0`           |
| Linux              | TODO                             | TODO                       |
| Mac                | TODO                             | TODO                       |