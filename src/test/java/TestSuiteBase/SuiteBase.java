package TestSuiteBase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utils.ExtentManager;

import java.io.FileInputStream;
import java.util.Properties;
import static org.openqa.selenium.remote.CapabilityType.*;

public class SuiteBase {

    public static WebDriver driver;
    public static Properties Object = null;
    public static Properties Param = null;
    public static Logger Logger = null;
    public static String reportsPath = null;

    public SuiteBase() {
        initialize();
    }

    public void initialize() {
        Object = new Properties();
        Param = new Properties();
        FileInputStream fip = null;
        String basePath = System.getProperty("user.dir") + "//src//main//resources//";
        try {

            Logger = Logger.getLogger("rootLogger");
            reportsPath = ExtentManager.CreateHTMLDir();

            fip = new FileInputStream(basePath + "Objects.properties");
            Object.load(fip);
            fip = new FileInputStream(basePath + "Param.properties");
            Param.load(fip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void loadWebBrowser() {
        if(Param.getProperty("testBrowser").trim().equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Executables\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setCapability(SUPPORTS_JAVASCRIPT, true);
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("disable-infobars");
            options.setExperimentalOption("useAutomationExtension",false);
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.navigate().to(Param.getProperty("siteURL"));
    }

    @AfterTest
    public void closeWebBrowser() {
       driver.close();
       if(driver !=null) {
           driver.quit();
           driver =null;
       }
    }

}
