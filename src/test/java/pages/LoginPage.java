package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LoginPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='login_credentials']")
    private WebElement usersList;

    @FindBy(className = "login_password")
    private WebElement passwords;

    @FindBy(id = "user-name")
    private WebElement usernameTextbox;

    @FindBy(id = "password")
    private WebElement passwordTextBox;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3")
    private WebElement errorMsg;

    @FindBy(className = "error-button")
    private WebElement errorBtn;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        driver.get("https://saucedemo.com");
    }

    public void doLogin(int usrindex) {
        String users = usersList.getText();

        List<String> usr = List.of(users.split("\n"));
        String ValidUser = usr.get(usrindex);

        String Password = passwords.getText();
        List<String> pwd = List.of(Password.split("\n"));
        String ValidPassword = pwd.get(1);

        usernameTextbox.sendKeys(ValidUser);
        passwordTextBox.sendKeys(ValidPassword);
        loginButton.click();
    }

    public void validateLoginSuccessful() {
        String titleValue = title.getText();

        Assert.assertEquals(titleValue, "Products", "Title should be 'Products'");
        Assert.assertTrue(title.isDisplayed(), "Title should be displayed");
        Assert.assertTrue(logout.isEnabled(), "Logout link should be enabled");
    }

    public void validateLoginFail() {
        String errorMessage = errorMsg.getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.", "Error message text is not as expected.");
        Assert.assertTrue(errorMsg.isDisplayed());
    }
}
