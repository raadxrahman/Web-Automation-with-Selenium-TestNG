package Pages.Admin;

import TestRunner.UserTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class AdminScrapePage extends UserTestRunner {

//    private WebDriver driver;

    public AdminScrapePage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void scrapeData() throws InterruptedException, IOException {

        try {
            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

            BufferedWriter Bwriter = new BufferedWriter
                    (new FileWriter("./src/test/resources/ScrapedUserInfo.txt"));

            Bwriter.write("First Name - Last Name - Email - Phone Number - Address - Gender - Registration Date");

            Bwriter.newLine();

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                StringBuilder rowText = new StringBuilder();
                for (int i = 0; i < cells.size() - 1; i++) {
                    rowText.append(cells.get(i).getText().trim()).append(" - ");
                }

                Bwriter.write(rowText.substring(0, rowText.length() - 3));
                Bwriter.newLine();
            }
            Bwriter.close();

        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

}
