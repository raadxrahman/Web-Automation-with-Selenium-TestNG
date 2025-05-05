package TestRunner.Admin;

import Configuration.Setup;
import Pages.Admin.AdminLoginPage;
import org.testng.annotations.Test;

public class AdminLoginTestRunner extends Setup {

    //@Test(priority = 1, description = "Admin Login")
    public void adminLogin(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.doLogin("admin@test.com", "admin123");
    }
    //@Test (priority = 2, description = "Admin adminLogout")
    public void adminLogout(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        adminLoginPage.doLogout();
    }
}
