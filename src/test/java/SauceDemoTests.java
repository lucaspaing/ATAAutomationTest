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
import pages.*;

import java.util.List;

public class SauceDemoTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private YourInformationPage yourInformationPage;
    private OverviewPage overviewPage;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com"); // URL to navigate to

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        yourInformationPage = new YourInformationPage(driver);
        overviewPage = new OverviewPage(driver);
    }

    @Test
    public void testLoginSuccessful() {
        loginPage.openLoginPage();
        loginPage.doLogin(1);
        loginPage.validateLoginSuccessful();
    }

    @Test
    public void testSortResultsByPriceHighToLow() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.doLogin(1);
        homePage.sortByHighToLow();
        Thread.sleep(3000);
    }

    @Test
    public void testAddToCartByPriceAndPurchaseThenLog() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.doLogin(1);
        homePage.addToCartByPrice(15.99);
        Thread.sleep(2000);
        homePage.clickHomeCartIcon();
        cartPage.clickCartCheckoutButton();
        Thread.sleep(2000);
        yourInformationPage.fillUserInformationAndContinue();
        overviewPage.logCheckoutSummary();
        Thread.sleep(2000);
    }

    @Test
    public void testLoginFail() {
        loginPage.openLoginPage();
        loginPage.doLogin(2);
        loginPage.validateLoginFail();
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

