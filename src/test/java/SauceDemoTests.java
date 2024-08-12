import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.YourInformationPage;

import java.util.List;

public class SauceDemoTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private YourInformationPage yourInformationPage;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        // Initialize the WebDriver
        WebDriverManager.chromedriver().setup();  // Make sure WebDriverManager is up to date
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com"); // URL to navigate to

        // Initialize the page object
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        yourInformationPage = new YourInformationPage(driver);
    }

    @Test
    public void testLoginSuccessful() {
        loginPage.openLoginPage();
        loginPage.doLogin();
        loginPage.validateLoginSuccessful();
    }

    @Test
    public void testSortResultsByPriceHighToLow() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.doLogin();
        //Thread.sleep(1000);
        homePage.sortByHighToLow();
        //Thread.sleep(3000);
    }

    @Test
    public void testAddToCartByPriceAndPurchase() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.doLogin();
        //Thread.sleep(1000);
        homePage.addToCartByPrice(15.99);
        Thread.sleep(2000);
        homePage.clickHomeCartIcon();
        cartPage.clickCartCheckoutButton();
        Thread.sleep(2000);
        yourInformationPage.fillUserInformationAndContinue();
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

