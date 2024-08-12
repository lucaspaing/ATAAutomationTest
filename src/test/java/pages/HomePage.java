package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value='hilo']")
    private WebElement sortByPriHiLo;

    @FindBy(className = "active_option")
    private WebElement activeSort;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartIcon;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortByHighToLow() {
        sortDropdown.click();
        sortByPriHiLo.click();
        SoftAssert softAssert = new SoftAssert();
        String activeSortBy = activeSort.getText();
        softAssert.assertEquals(activeSortBy, "Price (high to low)", "Sort option did not change to 'Price (high to low)'");
        softAssert.assertTrue(checkThePricesInDescendingOrder(),"The prices are not sorted in High to Low Value");
        softAssert.assertAll();
    }

    private boolean checkThePricesInDescendingOrder() {
        List<WebElement> prices = itemPrices;
        int size = prices.size();
        for (int i = 0; i < size - 1; i++) {
            String currentPriceText = prices.get(i).getText().replace("$", "").trim();
            String nextPriceText = prices.get(i + 1).getText().replace("$", "").trim();

            try {
                double currentPrice = Double.parseDouble(currentPriceText);
                double nextPrice = Double.parseDouble(nextPriceText);

                if (currentPrice < nextPrice) {
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void addToCartByPrice(Double expectedPrice) {
        List<WebElement> prices = itemPrices;

        for (int i = 0; i < prices.size(); i++) {
            String priceText = prices.get(i).getText().replace("$", "").trim();

            try {
                double price = Double.parseDouble(priceText);
                if (price == expectedPrice) {
                    WebElement button = driver.findElement(By.xpath(
                            String.format("//div[@class='inventory_item'][%d]//button[text()='Add to cart']", i + 1)
                    ));
                    button.click();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void clickHomeCartIcon() {
        cartIcon.click();
    }

}
