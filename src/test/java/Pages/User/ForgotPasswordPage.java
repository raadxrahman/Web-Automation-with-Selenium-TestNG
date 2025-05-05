package Pages.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ForgotPasswordPage {

    @FindBy(tagName = "input")
    WebElement inputEmail;

    @FindBy(tagName = "button")
    WebElement btnSendResetLink;

    @FindBy(tagName = "p")
    WebElement pSuccessMessage;

    public ForgotPasswordPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void sendResetLinkToEmail(String registeredEmail) throws IOException {
        inputEmail.sendKeys(registeredEmail);
        btnSendResetLink.click();
    }

    public String getTooltipMessage() {
        return inputEmail.getAttribute("validationMessage");
    }

    public String getParagraphMessage() {
        return pSuccessMessage.getText();
    }
}

