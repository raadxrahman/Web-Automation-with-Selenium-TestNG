package Pages.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResetPasswordPage {

    WebDriver webDriver;

    @FindBy(tagName = "input")
    List<WebElement> inputList;

    @FindBy(tagName = "button")
    WebElement buttonResetPassword;

    @FindBy(tagName = "p")
    WebElement pSuccessMessage;

    public ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String newPassword() {
        String newPassword = String.valueOf(Utils.Utils.generateRandomNumber(0000,9999));
        webDriver.findElements(By.tagName("input")).get(0).sendKeys(newPassword);
        webDriver.findElements(By.tagName("input")).get(1).sendKeys(newPassword);
        webDriver.findElement(By.tagName("button")).click();
        return newPassword;
    }

    public boolean getSuccessMessage() {
        return pSuccessMessage.isDisplayed();
    }

}
