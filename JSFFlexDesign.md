# Introduction #

Navigational page for JSF Flex design

# Details #

## Page Listings ##
  * [QuickStart](QuickStart.md) page contains info regarding importing the project to various IDE.
  * [JSFFlexProcess](JSFFlexProcess.md) page contains info regarding the process of JSF Flex project.
  * [JSFFlexMavenProjects](JSFFlexMavenProjects.md) page contains info regarding the maven projects of JSF Flex project.
  * [JSFFlexBuildSystemInfo](JSFFlexBuildSystemInfo.md) page contains info regarding the project's build system.
  * [ComponentTagsBondableFieldsInfo](ComponentTagsBondableFieldsInfo.md) page contains info regarding the bondable fields of JSF Flex project's component tags.
  * [RDDL document](http://jsf-flex.googlecode.com/svn/wiki/jsf-flex.xhtml). Technically the url should be changed to reflect the location of this file {rather than http://jsf-flex.googlecode.com}.

The project itself has been componentized tremendously in order to provide :
  * flexibility in choosing the implementation preferred {i.e. if the institution using this component library only allows ANT implementation, one should choose `antFlexTaskRunnerImpl` as the project for implementing `_FlexTaskRunner` interface}


The choice of implementation is driven through arguments when executing the mvn build.