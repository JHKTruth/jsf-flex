# Introduction #

This page provides a quick overview regarding the project's build system.

# Details #

## Methods of triggering different artifacts/dependencies for the project ##
  * **mvn install** will provide default artifacts with:
    * `_CommonTaskRunner`		sdkStandardCommonTaskRunnerImpl
    * `_FileManipulatorTaskRunner`	velocityFileManipulatorTaskRunnerImpl
    * `_FlexTaskRunner`		antFlexTaskRunnerImpl
> > and will utilize JSF 2.0 with `MyFaces` implementation.
  * **mvn install** `-Djsf=mojarra` will provide the examples artifact with Mojarra 2.0 implementation.
  * **mvn install** `-DtimestampAppend=true` will append timestamp to examples artifact for uploading to the Downloads.

**Hopefully below is deprecated**

Providing a quick note here for mvn deploy. Since the latest work for Mojarra is held within Dev Java Net, during development this repository will be added to pom.xml. However, when one is deploying the code for a tag, one must remove the inserted Dev Java Net repository, since only the public/standard repository is recognized for the deploy action. This means that hopefully the JSF 2.0 API + IMPL of Mojarra will be deployed to the public/standard repository in the future or there will be tweaking of Maven to allow additional repository within pom.xml for deploy.