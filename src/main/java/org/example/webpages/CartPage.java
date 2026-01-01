package org.example.webpages;

import org.example.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends Utility {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver , this);

    }

    @FindBy(css=".cartWrap .items .infoWrap h3")
    List<WebElement> itemsInCart;

    @FindBy(css=".totalRow button")
    WebElement checkOutButton;

    public WebElement getCheckOutButton() {
        return checkOutButton;
    }

    public List<WebElement> getItemsInCart() {
        return itemsInCart;
    }

    public boolean isProductInCart(List<WebElement> itemsInCart, String productName) {
        return itemsInCart.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public PaymentPage clickOnCheckoutButton(){
           getCheckOutButton().click();
           return new PaymentPage(driver);
    }





}
