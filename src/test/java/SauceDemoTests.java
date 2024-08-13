import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;


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
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        yourInformationPage = new YourInformationPage(driver);
        overviewPage = new OverviewPage(driver);
    }

    @Test (priority = 1)
    public void testLoginSuccessful() {
        loginPage.openLoginPage();
        loginPage.doLogin(1);
        loginPage.validateLoginSuccessful();
    }

    @Test (priority = 2)
    public void testSortResultsByPriceHighToLow() throws InterruptedException {
        loginPage.openLoginPage();
        loginPage.doLogin(1);
        homePage.sortByHighToLow();
        Thread.sleep(3000);
    }

    @Test (priority = 3)
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

    @Test (priority = 4)
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

