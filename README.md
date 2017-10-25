# Funhouse

## What is it

* An eclectic bunch of code...

* All rolled together...

* Into a carnival-like...

* Ladybugs went to the ladybug picnic!!!!!!

* Filled with examples like this:

```
YOU | ARE | HERE
--- | --- | ---
*The* | `bird` | **sings**
while | cars | screech
and | that | is
why | 1 + 1 | 2

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
```

Here are some more examples:

```

Some tests have unimplemented classes, so they will obviously fail.

### This



#### That

One and Only:
***
Two

##### And...

One
***
Two

###### The Other
> Blockquotes are very handy in email to emulate reply text.
> This line is part of the same quote.

Quote break.

> This is a very long line that will still be quoted properly when it wraps. Oh boy let's keep writing to make sure this is long enough to actually wrap for everyone. Oh, you can *put* **Markdown** into a blockquote. 


```

The previous examples [were stolen](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet "GitHub") from the web.

## Prerequisites
1. java
2. maven
3. wildfly

## Features
* unit tests
* integration tests
* jmeter performance tests
* deployment to AWS


## Test Environment

###### Java

```bash
$ java -version
java version "1.8.0_40"
Java(TM) SE Runtime Environment (build 1.8.0_40-b26)
Java HotSpot(TM) 64-Bit Server VM (build 25.40-b25, mixed mode)
```
###### Maven

```
$ mvn -v
Apache Maven 3.2.1 (ea8b2b07643dbb1b84b6d16e1f08391b666bc1e9; 2014-02-14T09:37:52-08:00)
Maven home: C:\apache-maven-3.2.1
Java version: 1.8.0_40, vendor: Oracle Corporation
Java home: C:\Java\x64\jdk1.8.0_40\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 7", version: "6.1", arch: "amd64", family: "dos"

```

###### Wildfly
* wildfly-10.1.0.Final


## Running
clone the repo

```bash
mvn clean package
```

deploy to wildfly

## TODO
* AWS deployment pipeline

