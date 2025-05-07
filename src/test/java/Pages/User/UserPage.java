package Pages.User;

import Utils.Utils;
import Utils.randomEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

import static org.openqa.selenium.By.tagName;

public class UserPage{

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

    public static WebElement getFirst(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalStateException("Element list is null or empty");
        }
        return elements.get(0);
    }

    public void addItem(String itemName, String quantity, String amount,
                        String date, String month, String year, String remarks) throws InterruptedException {
        driver.findElement(By.cssSelector("[class=add-cost-button]")).click();
        AddItem addItem = new AddItem(driver);
        addItem.addNewItem(itemName, quantity, amount, date, month, year, remarks);
    }

    public boolean searchByItemName(String itemName) {
        if (h2PageHeading.getText().contains("User Daily Costs")) {
            getFirst(inputList).sendKeys(itemName);
            return UserPage.getFirst(driver.findElements(tagName("span")))
                    .getText()
                    .contains("Total Rows: 1");
        }
        return false;
    }

    public String updateGmail() throws InterruptedException, IOException {
        String updatedEmail = new randomEmail().getEmail();
        List<WebElement> btnList = driver.findElements(tagName("button"));
        getFirst(btnList).click();
        getFirst(menuItemList).click(); //click on profile

        List<WebElement> btnList1 = driver.findElements(tagName("button"));
        btnList1.get(1).click();
        inputList.get(3).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        inputList.get(3).sendKeys(updatedEmail);
        btnList1.get(1).click();
        Utils.acceptAlert(driver); //update new email and save

        return updatedEmail;

    }

    public void logout() {
        getFirst(btnList).click();
        menuItemList.get(1).click();
    }

    public boolean searchByEmail(String email) {
        if (h2PageHeading.getText().contains("Admin Dashboard")) {
            getFirst(inputList).sendKeys(email);
            return spanTotalUsers.getText().contains("Total Users: 1");
        }
        return false;
    }
}
