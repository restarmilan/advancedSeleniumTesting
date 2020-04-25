Advanced browser automation test project using Selenium, Java, Maven, Spring, JUnit5. For designing page / object repositories 
Page Object Model and Page Factory patterns are used.

System under test (SUT) is the [Selenium Easy](https://www.seleniumeasy.com/test/) test automation practice page.

Test reporting:

run one of the following from command line, then open the ./target/site/surefire-report.html file in browser
* for detailed test reports:
```
mvn site
```

* for standalone test reports:
```
mvn surefire-report:report
```

* for standalone test reports, showing only failed tests:
```
mvn surefire-report:report -DshowSuccess=false
```

Screenshots:

Selenium made screenshots of failed test cases are located in ./target/screenshots folder. Screenshots' file name contains the unique identifier
of the test case and a simplified timestamp.
