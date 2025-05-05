package TestRunner.User;


import Configuration.Setup;
import Pages.User.ForgotPasswordPage;
import Pages.User.ResetPasswordPage;
import Pages.User.UserLoginPage;
import Utils.Utils;
import Pages.User.UserRegistrationPage;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.time.Duration;

public class UserTestRunner extends Setup {

    @Test(priority = 1, description = "New User Registration")
    public void userRegistration() throws InterruptedException, IOException, ParseException {

        driver.findElement(By.partialLinkText("Register")).click();

        UserRegistrationPage userRegistrationPage = new UserRegistrationPage(driver);

        Faker faker=new Faker();

        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email=("ozymandiaszx1+" + Utils.generateRandomNumber(0000,9999) + "@gmail.com");
//        String email = "ozymandiaszx1+6969@gmail.com";
        String password="1234";
        String phoneNumber="0160"+ Utils.generateRandomNumber(1000000,9999999);
        String address=faker.address().fullAddress();
        userRegistrationPage.userRegistration(firstName,lastName,email,password,phoneNumber,address);

        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50)); //explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successfulMessageActual= driver.findElement(By.className("Toastify__toast")).getText();
        String successfulMessageExpected= ("registered successfully!");
        System.out.println(successfulMessageActual);

        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstName);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phoneNumber);
        Utils.saveUserData("./src/test/resources/users.json",userObj);

        Assert.assertTrue(successfulMessageActual.contains(successfulMessageExpected));

    }

    @Test(priority = 2, description = "User Registration Gmail Assertion") //update OAuth every 1 hr
    public void assertRegistrationEmail() throws IOException,
            ConfigurationException, InterruptedException,
            org.apache.commons.configuration.ConfigurationException {

        JsonPath gmail = Utils.readLatestMail().jsonPath();
        Assert.assertTrue(gmail.get("snippet").toString().contains("Welcome to our platform!"));

        setProperty("registeredEmail", gmail.get("payload.headers[0].value").toString());

    }

    @Test(priority = 3, description = "User Tries to Reset Password Inputting null Email")
    public void ResetPasswordNegativeTest_1() throws IOException {
//        2. Now click on the reset password link. Write 2 negative test case and assert.

        UserLoginPage userLoginPage = new UserLoginPage(driver);
        userLoginPage.clickResetButton();

        ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
        forgotPassword.sendResetLinkToEmail(""); //empty email field
        Assert.assertTrue(forgotPassword.getTooltipMessage().contains("Please fill out this field"));

        driver.navigate().back();
    }

    @Test(priority = 4, description = "User Tries to Reset Password Inputting Invalid Email")
    public void ResetPasswordNegativeTest_2() throws IOException {
//        2. Now click on the reset password link. Write 2 negative test case and assert.

        UserLoginPage userLoginPage = new UserLoginPage(driver);
        userLoginPage.clickResetButton();


        ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
        forgotPassword.sendResetLinkToEmail("a1b2c3.d4e5f6@gmail.com");
        Assert.assertTrue(forgotPassword.getParagraphMessage().
                contains("Your email is not registered"));

        driver.navigate().back();
    }

    @Test(priority = 5, description = "User Tries to Reset Password Inputting Valid Email")
    public void ResetPasswordPositiveTest() throws IOException {
//        3. Now Input valid gmail account you have registered and click on send reset link button

        UserLoginPage userLoginPage = new UserLoginPage(driver);
        userLoginPage.clickResetButton();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.sendResetLinkToEmail(configFile().getProperty("registeredEmail"));

        Assert.assertTrue(forgotPasswordPage.getParagraphMessage().
                contains("Password reset link sent to your email"));
    }

    @Test(priority = 5, description = "User Retrieves Password Reset Mail and Sets New Password")
    public void RetrievePasswordResetLinkFromMail() throws ConfigurationException,
            IOException, InterruptedException, org.apache.commons.configuration.ConfigurationException {
//        4. Now retrieve password reset mail from your gmail and set new password

        String passwordResetMail = Utils.readLatestMail().jsonPath().get("snippet").toString();
        System.out.println(passwordResetMail);

        driver.get(Utils.extractPasswordResetLinkFromMail(passwordResetMail));

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        String newPassword = resetPasswordPage.newPassword();
        Assert.assertTrue(resetPasswordPage.getSuccessMessage());

        setProperty("password", newPassword);
    }

    @Test(priority = 6, description = "User Logins with New Password")
    public void UserLogin() throws IOException, InterruptedException {
//        5. Now login with the new password to ensure login successful
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        userLoginPage.doLogin(
                configFile().getProperty("registeredEmail"),
                configFile().getProperty("password")
        );
//        Thread.sleep(10000);
//        Assert.assertTrue(userLoginPage.loginSuccessful());
    }

}
