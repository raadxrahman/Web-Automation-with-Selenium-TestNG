package TestRunner.User;

import Configuration.Setup;
import Pages.Admin.AdminLoginPage;
import Pages.User.UserLoginPage;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserLoginTestRunner extends Setup {

    //@Test(priority = 1, description = "User Login with Valid Credentials")
    public void userLogin() throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=userObj.get("email").toString();
        String password=userObj.get("password").toString();

        UserLoginPage userLoginPage=new UserLoginPage(driver);
        userLoginPage.doLogin(email,password);
    }
    //@Test (priority = 2, description = "User Logout")
    public void userLogout(){
        UserLoginPage userLoginPage=new UserLoginPage(driver);
        userLoginPage.doLogout();
    }
}
