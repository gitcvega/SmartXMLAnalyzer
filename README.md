# SmartXMLAnalyzer

This is an XML analyzer to detect element changes after minor page updates.

## Getting Started

### Prerequisites

- You need to have a recent version of Java8+ installed.
- You can find the binary version of the application in the "/dist" folder.
- You can get all samples from "src/main/resources/samples" folder.

### Run the executable JAR with Arguments

The executable JAR (SmartXMLAnalyzer-1.0-SNAPSHOT.jar) from the "/dist" folder is a simple application that just prints
out the results to the standard output.
We can execute it with the arguments shown below and this is a complete example with three arguments:

```
$ java -jar ./dist/SmartXMLAnalyzer-1.0-SNAPSHOT.jar -s src/main/resources/samples/sample-0-origin.html -t src/main/resources/samples/sample-1-evil-gemini.html -i make-everything-ok-button
```
We’ll see the following output in the console:
```
html > body > div > div > div > div > div > div > a[class="btn btn-success"]
```

And the rest of the examples:
Sample 2
```
$ java -jar ./dist/SmartXMLAnalyzer-1.0-SNAPSHOT.jar -s src/main/resources/samples/sample-0-origin.html -t src/main/resources/samples/sample-2-container-and-clone.html -i make-everything-ok-button

html > body > div > div > div > div > div > div > div > a[class="btn test-link-ok"]
```

Sample 3
```
$ java -jar ./dist/SmartXMLAnalyzer-1.0-SNAPSHOT.jar -s src/main/resources/samples/sample-0-origin.html -t src/main/resources/samples/sample-3-the-escape.html -i make-everything-ok-button

html > body > div > div > div > div > div > div > a[class="btn btn-success"]
```

Sample 4
```
$ java -jar ./dist/SmartXMLAnalyzer-1.0-SNAPSHOT.jar -s src/main/resources/samples/sample-0-origin.html -t src/main/resources/samples/sample-4-the-mash.html -i make-everything-ok-button

html > body > div > div > div > div > div > div > a[class="btn btn-success"]
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

For the versions available, see the [tags on this repository](https://github.com/gitcvega/SmartXMLAnalyzer/tags).

## Authors

* **Cesar Vega**

## Acknowledgments

* AgileEngine

