package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    // Locators for sorting options
    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value='hilo']")
    private WebElement sortByPriHiLo;

    @FindBy(className = "active_option")
    private WebElement activeSort;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortByHighToLow() {
        sortDropdown.click();
        sortByPriHiLo.click();

        String activeSortBy = activeSort.getText();
        Assert.assertEquals(activeSortBy, "Price (high to low)", "Sort option did not change to 'Price (high to low)'");
        Assert.assertTrue(checkThePricesInDescendingOrder(),"The prices are not sorted in High to Low Value");
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
                    return false;  // Prices are not in descending order
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;  // Prices are in descending order
    }



}
