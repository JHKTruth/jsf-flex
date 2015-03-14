# Introduction #

Brief overview regarding JSF Flex components for JSF Flex project.

# Details #

## Components ##
JSF Flex project contains concrete JSF component for each Flex component.

Each of the JSF component that needs to preserve the value will have the following syntax of values checked during the decode process

`<id>_<nameOfField> {i.e. for List <id>_selectedIndex}. For certain components there will be a set of ids sent to the decode process {i.e. RadioButton component has two values checked during the JSF decode process { <id>_selectedValue and <id>_selected }.`

Exception to the above case is to components that require large amounts of data ` {i.e. DataGrid component} ` and for these components data will be synchronized to the managed beans using asynchronous calls through Actionscript.

JSF Flex project utilizes the myfaces build plugIn which automatically creates :
  * the concrete class for each abstract class of JSF component
  * the tag class for each JSF component
  * the tld file
  * the facesconfig.xml for the configuration

In order to view how to create such components, please refer to the current components within jsf-flex project.