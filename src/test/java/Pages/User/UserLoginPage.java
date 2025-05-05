package Pages.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class UserLoginPage {

    WebDriver driver;

    @FindBy(id="email")
    WebElement txtEmail;

    @FindBy(id="password")
    WebElement txtPassword;

    @FindBy(tagName = "button")
    WebElement btnLogin;

    @FindBy(linkText = "Reset it here")
    WebElement btnReset;


    @FindBy(css="[type=button]")
    List<WebElement> btnProfileIcon;
    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItem;

    public UserLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String email, String password) {
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    public boolean loginSuccessful() {
        UserPage user = new UserPage(driver);
        return user.loadUserDashboard();
    }

    public void doLogout(){
        btnProfileIcon.get(0).click();;
        menuItem.get(1).click();
    }

    public void clickResetButton() {
        btnReset.click();
    }
}

