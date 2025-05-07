package Pages.User;

import Utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.security.Key;
import java.util.List;

public class AddItem {
    WebDriver webDriver;

    @FindBy(tagName = "button")
    List<WebElement> buttonList;

    @FindBy(tagName = "input")
    List<WebElement> inputList;

    @FindBy(id = "itemName")
    WebElement inputItemName;

    @FindBy(id = "amount")
    WebElement inputAmount;

    @FindBy(id = "purchaseDate")
    WebElement inputPurchaseDate;

    @FindBy(id = "month")
    WebElement selectMonth;

    @FindBy(id = "remarks")
    WebElement textareaRemarks;

    @FindBy(className = "submit-button")
    WebElement buttonSubmit;

    public AddItem(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void addNewItem(String itemName, String quantity,
                           String amount, String date, String month, String year, String remarks)
            throws InterruptedException {

        inputItemName.sendKeys(itemName);

        if(!quantity.isBlank())
            for (int i = 0; i < Integer.parseInt(quantity) -1; i++)
                increaseQuantity();

        inputAmount.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        inputAmount.sendKeys(amount);

        if(!date.isBlank() && !month.isBlank() && !year.isBlank())

            inputPurchaseDate.sendKeys(date, month, Keys.ARROW_RIGHT, year);

        if(!month.isBlank())

            for (int i = 0; i < Integer.parseInt(month) - 1; i++)
                selectMonth();

        if(!remarks.isBlank())
            textareaRemarks.sendKeys(remarks);

        buttonSubmit.click();
        Utils.acceptAlert(webDriver);
    }

    private void selectMonth() {
        selectMonth.sendKeys(Keys.ARROW_DOWN);
    }

    private void increaseQuantity() {
        buttonList.get(2).click();
    }

    private void decreaseQuantity() {
        buttonList.get(1).click();
    }

}
