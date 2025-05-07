package Pages.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    @FindBy(xpath = "//p[text()='Invalid email or password']")
    WebElement invalidCredentialsErrorMessage;

    public UserLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String email, String password) {
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    public boolean loginSuccessful() {
        UserPage userPage = new UserPage(driver);
        return userPage.loadUserDashboard();
    }

    public boolean loginNotSuccessful() { //reverse negative
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(invalidCredentialsErrorMessage));
            return true;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void doLogout(){
        btnProfileIcon.get(0).click();;
        menuItem.get(1).click();
    }

    public void clickResetButton() {
        btnReset.click();
    }
}

