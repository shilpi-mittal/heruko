# herukoapp

### Objective: ### 
Design framewirk for automating non-angular web app

 ### Tech Stack: ### 
* Java 8
* Selenium WebDriver
* TestNG
* Gradle 4.3.1
* geckodriver
* Firefox exe
* git

 ### System Requirements to run the tests: ### 
* Windows
* Java 8
* Gradle 4.3.1
* Firefox.exe

 ### Architechture: ### 
The framework is using Page Object Model, that is used to segregate page dependent information from test logic. For each pages there should be one pageObject, listing all elements locators and the methods on those elements.


There are two modules under testModule:


#### Under functional module:
* Page objects are grouped at /src/main/java/org/page/objects
* The functional tests on these pages are grouped at /src/test/java/functional
* test data for test cases is at /src/test/resources
* TestNG suite file

#### Under TestCore module,
* we have utils, or say helpers, for our test cases, at /src/main/java/utils
* And test environment configs at /src/main/resources 
* All external methods are wrapped under utils method to save rework due to deprecated methods in upgraded versions. 

* Each module has its own build.gradle file. The test tasks are created in build file functionalTests module.
* There is also geckodriver, to execute the test cases on firefox browser, at root level

 ### Test Scenarios: ### 
26 test cases are automated, one of them is failing due to a bug
#### TestNG scenario:
* Use case
    * as per manual test cases shared in excel sheet
* Execution
  * To run the test case there are three options,
      * Right clik on test file from IDE and run as TestNG (requires TestNG plugin)
      * Run the suite xml file
      * Run via Gradle task, from root directory run the following commands
        * gradle clean build
        * gradle clean testNGTests
* Test Result
  * An HTML report will be generated at 'testModule/functionalTests/build/reports/tests/testNGTests/packages/functional.html', and at 'testModule\functionalTests\test-output\testNG Test'
  * xml reports are also generated
			
					
