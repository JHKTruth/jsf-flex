# Introduction #

This page will contain a brief overview of building the JSF Flex code and importing the projects into an Eclipse IDE.

# Details #

## Checking out/Extracting the resources from repository ##
  1. Check out/extract the resources from https://jsf-flex.googlecode.com/svn/trunk/ or http://jsf-flex.googlecode.com/svn/trunk/ to a folder within the local system `[i.e. C:\jsfFlexProject]`

## Importing the project into Eclipse IDE ##
  1. Run **mvn install** within the resource directory `[i.e. C:\jsfFlexProject]`
  1. Then in the folder where the resources were checked out from, simply run the following command to enable importing of the projects into an Eclipse IDE
    * mvn -D wtpversion=<WTP Version of local Eclipse> eclipse:eclipse

This should be enough to import the projects into Eclipse IDE.

  * Create a new workspace :
    * File => Import => Existing Projects into Workspace
    * Select the folder where the resources were checked out to `[i.e. C:\jsfFlexProject]`
    * Finally add the "M2\_REPO" Classpath variable within the workspace by entering the following :
      * Window => Preferences => Java => Build Path => Classpath Variables
      * M2\_REPO as variable and value as the maven2 repository `[for me it was "C:/Documents and Settings/Administrator/.m2/repository"]`

> ![http://jsf-flex.googlecode.com/svn/wiki/importProjects.jpg](http://jsf-flex.googlecode.com/svn/wiki/importProjects.jpg)

> ![http://jsf-flex.googlecode.com/svn/wiki/M2_Repo_ClasspathVariable.jpg](http://jsf-flex.googlecode.com/svn/wiki/M2_Repo_ClasspathVariable.jpg)

## Testing the example pages ##
Simply import the generated War file into the workspace.

## Building after code modification ##
Whenever modification of the code is done and one wishes to create the Jar + War of the projects, simply run **mvn clean** then **mvn install** within the directory where the resources are checked out at `[i.e. C:\jsfFlexProject]`. Technically you should be able to run **mvn clean install**, but creating of the component, tags, and etcetera takes a bit of memory using the myfaces build plugIn, so it is best to run it as a separate transaction. Currently the build system is set to have max memory at 1024, but if needed in the future will modify it `[though technically the desire is NOT to]`. Additional info regarding building with different implementation can be found within the following wiki page [JSFFlexBuildSystemInfo](JSFFlexBuildSystemInfo.md).

## Directories of important artifacts ##
  1. Jar file of respective projects `[C:\jsfFlexProject\{project}\core\target]`
  1. War file of an example `[C:\jsfFlexProject\jsfFlex\examples\target]`