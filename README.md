# ORDS Adapter Boiler

## Technical Stack
- Spring framework
- Java 8
- Maven 3

## Maven Commands

```
## Install dependancy
mvn install

## Run Test
mvn clean test

## Build jar file
mvn clean package
```

## Java doc

Build java doc
```
mvn javadoc:javadoc
```

Then the javadoc is under `./target/site/apidocs`, open with `./target/site/apidocs/index.html`

## Trouble Shooting
Using > Java 8 to package jar file will trigger debug warning, see the link for detail: [https://www.cnblogs.com/hapday/p/13140231.html](https://www.cnblogs.com/hapday/p/13140231.html)
