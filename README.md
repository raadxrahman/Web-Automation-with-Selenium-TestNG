# Automating and Scraping Webpages using Selenium & TestNG following Page-Object-Model(POM)
In this project, I have used Selenium & TestNG to automate all features of the [Daily Finance](https://dailyfinance.roadtocareer.net/) website and scrape data from a data table. 

## Technologies Used

 * Java
 * Selenium-Java (https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
   implementation("org.seleniumhq.selenium:selenium-java:4.31.0"))
 * TestNG (https://mvnrepository.com/artifact/org.testng/testng
    testImplementation group: 'org.testng', name: 'testng', version: '7.10.2')
 * Java-Faker (https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    implementation group: 'com.github.javafaker', name: 'javafaker', version: '1.0.2')
 *  Json-Simple (https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation group: 'com.googlecode.json-simple', name: 'json-simple', version: '1.1.1')
 *  Apache-Commons (https://mvnrepository.com/artifact/org.apache.commons/commons-csv
    implementation group: 'org.apache.commons', name: 'commons-csv', version: '1.12.0')
 *  Allure-Testing (https://mvnrepository.com/artifact/io.qameta.allure/allure-testng
    implementation group: 'io.qameta.allure', name: 'allure-testng', version: '2.29.0')
   

 ## Prerequisites
 
  * Java Development Kit (JDK) 8 or later
  * IntelliJ IDEA (or your preferred Java IDE)
  * Gradle (if you want to use the provided Gradle build)
  * Allure CLI (for generating reports)
  * Gmail API 

## Setup Instructions

 1.  **Clone the repository:**

     ```bash
     git clone <repository_url>
     cd <repository_name>
     ```

 2.  **Open the project in IntelliJ IDEA:**

     * Open IntelliJ IDEA.
     * Select "Open or Import Project."
     * Navigate to the project directory and select the `build.gradle` file (if you are using Gradle) or the project directory itself.
     * IntelliJ IDEA will import the project.
## Tasks Automated
* User Registration: Automation of the new user registration process, including dynamic email generation (e.g., gmailuser+randomdigit@gmail.com) and assertion of successful registration.
* Password Reset - Negative Testing: Implementation of two negative test cases for the password reset functionality with appropriate assertions.
* Password Reset - Initiate Link: Automation of the process to input a valid registered email and trigger the sending of the password reset link.
* Password Reset - New Password Setup: Automation to retrieve the password reset email (via external means or simulated retrieval) and set a new password for the user account.
* Login with New Password: Automated verification of successful login using the newly set password.
* Item Creation: Automation to add two new items to the item list: one with all fields populated and another with only mandatory fields, along with assertions to confirm their presence.
* User Profile Update: Automation of the process to navigate to the user profile and update the registered email address.
* Login with Updated Credentials: Automated testing to ensure successful login with the newly updated email address and failed login attempts with the previous email.
* Admin Login (Secure Credentials): Automation of the admin login process, emphasizing the secure handling of admin credentials (passed via terminal).
* Admin User Search: Automated functionality for admin users to search by an updated user's email and assertion of the updated email's visibility on the admin dashboard.
* Bulk User Registration (CSV Data): Implementation of data-driven testing to register three additional users using data sourced from a CSV file.
* User Data Extraction (Admin): Automation for admin users to retrieve all user data from the user table and write it to a text file.

       
## Allure Reports
#### 1. Overview 
![Screenshot 2025-05-07 215539](https://github.com/user-attachments/assets/ae0c91fa-42f9-4b72-83d5-929653cfe1e7)
#### 2. Behaviours 
![Screenshot 2025-05-07 215558](https://github.com/user-attachments/assets/58f2379d-5dae-4a14-95de-94f1e4f4e928)

## Demonstration
#### Includes running the project both from IDE and Terminal.


Click on the video.
[![Watch the video](https://img.youtube.com/vi/<ac9AGWueGK4>/maxresdefault.jpg)](https://youtu.be/ac9AGWueGK4)

##  Author

Mahbubur Rahman
