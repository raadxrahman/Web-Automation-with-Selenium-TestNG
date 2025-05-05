package Pages.User;

import Configuration.Setup;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class UserPage {

    WebDriver driver;

    @FindBy(tagName = "h2")
    WebElement h2PageHeading;

    @FindBy(className = "total-count")
    WebElement spanTotalUsers;

    @FindBy(tagName = "input")
    List<WebElement> inputList;

    @FindBy(tagName = "button")
    List<WebElement> btnList;

    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItemList;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean loadUserDashboard() {
        return h2PageHeading.isDisplayed();
    }
}
