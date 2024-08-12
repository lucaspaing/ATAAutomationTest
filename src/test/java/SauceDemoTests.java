import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.List;

public class SauceDemoTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        // Initialize the WebDriver
        WebDriverManager.chromedriver().setup();  // Make sure WebDriverManager is up to date
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com"); // URL to navigate to

        // Initialize the page object
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccessful() {
        loginPage.openLoginPage();
        loginPage.doLogin();
        loginPage.validateLoginSuccessful();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

