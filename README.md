# ATA Automation Tests

## Overview

This project contains automated test cases for the Swag Labs Web application using Selenium WebDriver and TestNG. 
The tests cover various functionalities such as login success, login fail,  sorting items, adding items to cart, and completing purchase.

## Prerequisites

- **Java JDK**: Java JDK 8 or later is installed on machine. This project uses version 22.0.1
- **Maven**: Maven for dependency management and building the project. This project uses version 3.9.8
- **WebDriver**: ChromeDriver to run the tests on Chrome browser. The project uses WebDriverManager to manage the WebDriver and its version.

## Project Structure

- **`src/test/java/pages`**: Contains Page Object Model classes includes pages of the Swag Labs Web application.
- **`src/test/java/SauceDemoTests.java`**: Contains the TestNG test cases for the Swag Labs Web application.
- **`pom.xml`**: Contains Maven configuration configs for managing dependencies and plugins.

## Setup

1. Install java jdk and check install success by checking java version
2. Set variables path for Windows, skips this step for Mac OS
3. Install IDE such as Intellij Community version 
4. Install maven and check installed success by checking mvn version 
5. Define dependencies in pom.xml file such as selenium, testNG, simple logger, web driver manager
6. Install the dependencies by Maven clean install command line mvn clean install or by manually
7. Clone the repository: https://github.com/lucaspaing/ATAAutomationTest.git
