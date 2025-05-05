package Configuration;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import javax.naming.ConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Setup {
    public WebDriver driver;
    static Properties properties;
    private static String fileConfig = "./src/test/resources/config.properties";

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://dailyfinance.roadtocareer.net/");
    }

    @BeforeTest
    public static Properties configFile() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(fileConfig));
        return properties;
    }

    public static void setProperty(String key, String value) throws ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(fileConfig);
        propertiesConfiguration.setProperty(key, value);
        propertiesConfiguration.save();
    }

    //    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
