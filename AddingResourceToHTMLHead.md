# Introduction #

This page will provide a brief overview of how to insert resources such as JS files within the head of the HTML page.

# Details #

There currently exists a java file to insert JS content to the head of HTML page :
  * `JsfFlexResource`

_It is assumed that the resource file is within the correct resource directory within the **src** hierarchy._

So in order to add the resource to the head of the HTML file, one simply gets the instance of `JsfFlexResource` {since they are abstract classes} and invoke the addResource method providing the class where the resource is nested under and the resource name.

Following is an example from `AbstractMXMLUIApplication` :
```
  JsfFlexResource jsfFlexResource = JsfFlexResource.getInstance();
  jsfFlexResource.addResource(getClass(), JSF_FLEX_COMMUNICATOR_CORE_JS);
  jsfFlexResource.addResource(getClass(), JSF_FLEX_COMMUNICATOR_LOGGER_JS);
```