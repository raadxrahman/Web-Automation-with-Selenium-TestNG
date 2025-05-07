package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import javax.naming.ConfigurationException;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Configuration.Setup;


public class Utils {

    public static String FilePath = "./src/test/resources/users.json";
    public static Properties props;
    public static FileInputStream file;


    public static int generateRandomNumber(int min, int max){
        double randomNumber= Math.random()*(max-min)+min;
        return (int)randomNumber;
    }

    public static void main(String[] args) {
        int id= generateRandomNumber(1000,9999);
        System.out.println(id);
    }

    public static void saveUserData(String filePath, JSONObject jsonObject) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        jsonArray.add(jsonObject);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static Response readLatestMail() throws IOException, ConfigurationException, InterruptedException {
        Thread.sleep(9000);
        RestAssured.baseURI = "https://gmail.googleapis.com";

        Response gmailList = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + Setup.configFile().getProperty("access_token"))
                .when()
                .get("/gmail/v1/users/me/messages");

        String messageId = gmailList.jsonPath().get("messages[0].id").toString();

        Response gmail = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + Setup.configFile().getProperty("access_token"))
                .when()
                .get("/gmail/v1/users/me/messages/"+ messageId);

        return gmail;
    }

    public static String extractPasswordResetLinkFromMail(String passwordResetMail) {
        String pattern = "https://dailyfinance\\.roadtocareer\\.net/reset-password\\?token=[a-zA-Z0-9]+";
        Matcher matcher = Pattern.compile(pattern).matcher(passwordResetMail);
        if (matcher.find())
            return matcher.group();

        return "unable to extract";
    }

    public static void acceptAlert(WebDriver webDriver) throws InterruptedException {
        Thread.sleep(1000);
        webDriver.switchTo().alert().accept();
        Thread.sleep(1000);
    }


}

