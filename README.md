# LMSAPIAutomationProject
API Automation Project with Rest assured
## Table of contents
* [General info](#general-info)
* [Tools and Technologies](#tools-and-technologies)
* [FrameWork](#framework)
* [Running Project](#running-project)
* [Reporting](#reporting)


## General info
* Program API

  The user (Admin) can create different programs. The details are inserted in LMS DB (tbl_lms_program).
  The user can retrieve all programs and specific program based on Program ID.
  The user can update a program Details based on program ID and Program Name.
  The user can delete a program based on program ID and Program Name.

* Batch API

  The user can create a batch only if the program is present. As the batch is associated with program Id.
  The details are inserted in LMS DB(tbl_lms_batch).
  The user can retrieve all batches and specific Batch based on Batch Id, Batch Name, and Program ID
(tbl_lms_program).
  The user can update a Batch based on Batch ID.

## Tools and Technologies
Project is created with:
* Maven - Dependency management
* Java
* Rest Assured
* Cucumber with TestNG - BDD approach
* log4j - Logging
* Allure-testng - Reporting

## Framework
```mermaid
flowchart TD
    src-->test
    test-->appHooks
    test-->runner
    test-->stepDefinition
    test-->utilities
    src-->resources
    resources-->features
    resources-->log4j
```
## Running Project
To run this project, 
Open terminal (MAC OS) or command prompt / power shell (for windows OS) and navigate to the project directory type mvn clean test command to run features
```
$ cd <Project Directory>

$ mvn clean test
```
Features will run in order :
1. SC_1_ProgramSaveProgram.feature
2. SC_2_UpdateProgramByName.feature
3. SC_5_DeleteByProgramId.feature


Running with tags
```
$ cd <Project Directory>

$ mvn test -Dcucumber.filter.tags="@program"
```
## Reporting

Once tests complete run reports are generated. This framework uses different types of test reporters to communicate pass/failure.

Allure Report: 

Report will be generated into temp folder. Web server with results will start appearing in your default browser. 

```
$ cd <Project Directory>

$ allure serve allure-results
```
HTML Report: 

Report will be generated tо directory: target/dsAlgoReport.html


### Develop automation scripts using BDD approach - Cucumber-Java :

Tests are written in the Cucumber framework using the Gherkin Syntax. More about Gherkin & Cucumber can be found at https://cucumber.io/docs/cucumber/ 

