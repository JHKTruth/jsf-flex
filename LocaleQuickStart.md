# Introduction #

This page contains a brief overview in making JSF Flex application multilingual. Note at the current time Flex does not provide runtime access of Locale messages, meaning whenever there exists change within the .properties file one has to recreate the associated SWF files by changing **com.googlecode.jsfFlex.MODE** field to **debugMode** within web.xml

# Details #

Locale support for Flex is similar to Java, so the main point of this Wiki page will be to point out the specifics for JSF Flex application.

  1. First, one would need to add fields within web.xml file :
```
      <context-param>
        <description>
      	  The relative web context path for the locale resources, if being used for 
      	  multilingual application
        </description>
        <param-name>com.googlecode.jsfFlex.LocaleWebContextRelativePath</param-name>
        <param-value>locale</param-value>
      </context-param>
      
      <context-param>
        <description>
      	  The default locale
        </description>
        <param-name>com.googlecode.jsfFlex.DefaultLocale</param-name>
        <param-value>en_US</param-value>
      </context-param>
      
```
> > The fields are rather self explanatory, but for clarity
    * the first field represents the relative directory to `WebContent/` that will contain {Locale}.properties file. Here is the screen shot of example War's directory structure :
> > > ![http://jsf-flex.googlecode.com/svn/wiki/localeDirectoryStructure.jpg](http://jsf-flex.googlecode.com/svn/wiki/localeDirectoryStructure.jpg)
    * the second field represents the default locale to be used if the requested locale by the user is not found {i.e. if the application supports english + spanish and the user's browser setting is for german, it will default to english}.
  1. Then one will add the property files to their respective directory and run the application under debugMode to create each locale's respective SWF file. Following is an example for `LocaleExample.properties` :
```
     <jf:mxmlLabel text="@Resource(bundle='LocaleExample', key='greeting')" color="#FFFFFF" fontWeight="bold" fontSize="14"/>
```

That is it. When ran under debugMode, respective SWF files will be created under its respective directory and depending on the user's browser setting the correct Locale SWF file will be fetched :
  * en\_US : mxmlOverallExample-en\_US.swf
  * es\_ES : mxmlOverallExample-es\_ES.swf
  * ko\_KR : mxmlOverallExample-ko\_KR.swf