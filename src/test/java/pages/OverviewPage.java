package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class OverviewPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement checkoutTitle;

    @FindBy(className = "cart_quantity_label")
    private WebElement cartQuantityLabel;

    @FindBy(className = "cart_desc_label")
    private WebElement cartDescLabel;

    @FindBy(xpath = "//div[@data-test='payment-info-label']")
    private WebElement paymentInfoLabel;

    @FindBy(xpath = "//div[@data-test='payment-info-value']")
    private WebElement paymentInfoValue;

    @FindBy(xpath = "//div[@data-test='shipping-info-label']")
    private WebElement shippingInfoLabel;

    @FindBy(xpath = "//div[@data-test='shipping-info-value']")
    private WebElement shippingInfoValue;

    @FindBy(xpath = "//div[@data-test='total-info-label']")
    private WebElement totalInfoLabel;

    @FindBy(xpath = "//div[@data-test='subtotal-label']")
    private WebElement subtotalLabel;

    @FindBy(xpath = "//div[@data-test='tax-label']")
    private WebElement taxLabel;

    @FindBy(xpath = "//div[@data-test='total-label']")
    private WebElement totalLabel;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(className = "cart_quantity")
    private List<WebElement> inventoryItemQuantities;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> inventoryItemDescriptions;

    @FindBy(className = "item_pricebar")
    private List<WebElement> inventoryItemPrices;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logCheckoutSummary() {
        String newLine = System.getProperty("line.separator");
        String checkoutTitleText = checkoutTitle.getText();
        String qtyText = cartQuantityLabel.getText();
        String descText = cartDescLabel.getText();
        String paymentInfoText = paymentInfoLabel.getText();
        String paymentInfoValueText = paymentInfoValue.getText();
        String shippingInfoText = shippingInfoLabel.getText();
        String shippingInfoValueText = shippingInfoValue.getText();
        String totalInfoText = totalInfoLabel.getText();
        String subtotalText = subtotalLabel.getText();
        String taxText = taxLabel.getText();
        String totalText = totalLabel.getText();

        System.out.println(newLine + checkoutTitleText + newLine);
        System.out.println(qtyText + " " + descText);

        int cartItemSize = cartItems.size();
        for (int i = 0; i < cartItemSize; i++) {
            String cartItemQty = inventoryItemQuantities.get(i).getText();
            String cartItemName = inventoryItemNames.get(i).getText();
            String cartItemDesc = inventoryItemDescriptions.get(i).getText();
            String cartItemPrice = inventoryItemPrices.get(i).getText();
            System.out.println("  " + cartItemQty + " " + cartItemName + newLine + "    " + cartItemDesc + newLine + "    " + cartItemPrice);
        }

        System.out.println(newLine + paymentInfoText + newLine + paymentInfoValueText + newLine);
        System.out.println(shippingInfoText + newLine + shippingInfoValueText + newLine);
        System.out.println(totalInfoText + newLine + subtotalText + newLine + taxText + newLine + totalText);
    }
}
