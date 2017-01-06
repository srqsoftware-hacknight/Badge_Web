# Suncoast Science Center Badge System

# Prerequisites

There are a few packages that you should have installed before attempting to work on the project:

1. `Node.js` - The NodeJS runtime
2. `npm` - NodeJS package manager; used to install project dependencies
3. `maven` - Java build tool and package manager
4. `bower` - JavaScript package manager
5. `karma` - JavaScript test runner. (We'll be using the Jasmine Test Framework with this.)
6. Java 8

If you're using a Mac, the best way to install the prerequisites is via brew. On Linux, apt-get should be able to install them as well.

# Summary

Steps for building the project:

1. Clone the repository (via git)
2. Navigate to the directory Badge_Web
3. Install bower and npm related dependencies.
    - `npm install -g bower`
    - Navigate to the src/main/webapp directory and type the following:
    - `bower install`
    - `npm install -g jasmine-core`
    - `npm install -g karma-jasmine`
    - `npm install -g phantomjs`
    - `npm install -g karma-phantomjs-launcher`
4. Navigate back to the root directory and type `mvn clean package`
5. To run the code, type: `java -jar target/badge-web-VERSION.war`
Note: To run the code in test, type: `java -jar -Dspring.profiles.active=test target/badge-web-VERSION.war`
6. Launch the following url:
http://localhost:8080
7. Run the web unit tests by navigating to the test directory and typing: `karma start`

# Creating a release and publishing to github

* Increment the project version in the pom.xml file.
* Create a github personal access token. Do not share this nor commit it.
* Modify your ~/.m2/settings.xml to include a server block similar to:
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_USERNAME</username>
      <password>GH_TOKEN</password>
    </server>
  </servers>
</settings>
```
* mvn clean package github-release-plugin:gh-upload
* Verify the release looks correct at: https://github.com/srqsoftware-hacknight/Badge_Web/releases

