# ORDS Adapter Boilerplate

## 🚀Overview
ORDS Adapter Boilerplate is the ORDS library to covert request and response data between ORDS and Spring Boot ORDS Boilerplate

## 📚Table of Content
- [Technical Stack](#technical-stack)
- [Build and Run](#build-and-run)
- [Folder Structure](#folder-structure)
- [Generate Java Doc](#generate-java-doc)
- [Trouble Shooting](#trouble-shooting)
- [Reference](#reference)

## 🔨<a name="technical-stack"></a>Technical Stack
- Spring framework
- Java 8
- Maven 3

## 🏗<a name="build-and-run"></a>Build and Run

STEP 1. Install dependancy
```
mvn install
```

STEP 2. Test
```
mvn clean test
```

STEP 3. Pack jar file
```
mvn clean package
```

STEP 4. Copy jar file from `target/ords-adapter-boilerplate-1.0.0.jar` to `spring-boot-ords-boilerplate` project

## 📦<a name="folder-structure"></a>Folder Structure
- converter: convert request, response to entity
- entity: Object entity for origin ORDS data structure 
- exception: Define exception
- parameter: Define RESTful API Parameters, Request, Response
- webclient: Connect ORDS with WebClient

## 📑<a name="generate-java-doc"></a>Generate Java Doc

STEP 1. Run the command to generate Java doc
```
mvn javadoc:javadoc
```

STEP 2. Open Java doc
Open `target/site/apidocs/index.html`, it will show Java doc as well

## 🎯<a name="trouble-shooting"></a>Trouble Shooting
Using > Java 8 to package jar file will trigger debug warning, see the link for detail: [https://www.cnblogs.com/hapday/p/13140231.html](https://www.cnblogs.com/hapday/p/13140231.html)

## 🗒<a name="reference"><a/>Reference
- Logging requests using a filter function: https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
