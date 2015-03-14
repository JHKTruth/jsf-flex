# Introduction #

This page will provide a brief overview in referencing JSF Flex project's repository into a maven build system.

# Details #

## Insertion of JSF Flex Repository ##
First one needs to add JSF Flex project's repository into Maven's pom.xml. Here is the release repo :

```
<repositories>
  <repository>
    <id>jsf-flex-repository</id>
    <name>JSF Flex Repository</name>
    <url>http://jsf-flex.googlecode.com/svn/repository/release/</url>
  </repository>
</repositories>
```

## Needed JSF Flex artifacts ##
Next one needs to insert the needed artifacts within Maven's pom.xml :

```

<dependencies>
  <!-- Below artifact contains components, phaseListener, filter, and etcetera for JSF Flex project  -->
  <dependency>
    <groupId>com.googlecode.jsf-flex.jsf-flex-project</groupId>
    <artifactId>jsf-flex</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  <!-- Below artifact contains renderers and _AnnotationDocletParser implementation for JSF Flex project -->
  <dependency>
    <groupId>com.googlecode.jsf-flex.jsf-flex-project</groupId>
    <artifactId>jsf-flex-project-renderKit14</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  
  <!-- Below three dependencies are implementations that provide creation of preMxml, Mxml, SWC, SWF, and etcetera. -->
  <dependency>
    <groupId>
      com.googlecode.jsf-flex.runner-impl-project.common-runner-project
    </groupId>
    <artifactId>sdk-standard-common-impl</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>
      com.googlecode.jsf-flex.runner-impl-project.file-manipulator-runner-project
    </groupId>
    <artifactId>velocity-file-manipulator-impl</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>
      com.googlecode.jsf-flex.runner-impl-project.flex-runner-project
    </groupId>
    <artifactId>ant-flex-impl</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  
  <!-- Below dependency provides MXMLRenderKit* java files and etcetera that are specific to MyFaces Impl [NO LONGER REQUIRED FROM 1.1b+] -->
  <dependency>
    <groupId>com.googlecode.jsf-flex.jsf-flex-project</groupId>
    <artifactId>jsf-flex-myFaces-impl</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  
  <!-- Below dependency provides MXMLRenderKit* java files and etcetera that are specific to Mojarra Impl [NO LONGER REQUIRED FROM 1.1b+] -->
  <dependency>
    <groupId>com.googlecode.jsf-flex.jsf-flex-project</groupId>
    <artifactId>jsf-flex-mojarra-impl</artifactId>
    <version>${jsf.flex.version}</version>
    <scope>runtime</scope>
  </dependency>
  
</dependencies>

<properties>
  <jsf.flex.version>1.0b</jsf.flex.version>
</properties>

```

Note that one needs one dependency of either **jsf-flex-myFaces-impl** artifact or **jsf-flex-mojarra-impl** artifact. The reason is to provide capability in using other renderKits such as HTML\_BASIC when user is using MXML\_BASIC, but there exists a difference in `MyFaces` + Mojarra implementation. If I remember correctly, the latter implementation adds the HTML\_BASIC renderkit within the constructor, so must extend the class {there are other methods, but chose this option}.

That is it!!!