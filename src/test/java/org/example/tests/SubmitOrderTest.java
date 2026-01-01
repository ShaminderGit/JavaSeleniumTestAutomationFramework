package org.example.tests;

import org.example.commonpages.Header;
import org.example.testutility.Base;
import org.example.webpages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends Base {

   @Test
    public void orderTest() throws IOException {

        String email = "thisis@gmail.com";
        String password = "Qwerty@123";
        String productName = "ADIDAS ORIGINAL";
        String thankYouMessage = "THANKYOU FOR THE ORDER.";



        WebDriver driver = initializeDriver();


        //user is on the landing page
        LandingPage landingPage =new LandingPage(driver);
        landingPage.enterEmailId(email);
        landingPage.enterPassword(password);
        landingPage.clickOnLoginButton();

        //user is on the productCatalogPage
        ProductCatalogPage productCatalogPage = landingPage.clickOnLoginButton();
        WebElement shoe = productCatalogPage.getProductByName(productName);
        productCatalogPage.addProductToCart(shoe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        productCatalogPage.waitForElementToAppear(productCatalogPage.getTostContainer());
        productCatalogPage.waitForElementToDisappear(productCatalogPage.getAnimation());

        //user is clicking on header
        Header header = new Header(driver);
        header.clickOnHeaderMenu(header.getNavigationOptions(), "Cart");

        //user is on the cart page
        CartPage cartPage = new CartPage(driver);
        List<WebElement> itemsInCart = cartPage.getItemsInCart();
        Boolean match = cartPage.isProductInCart(itemsInCart, productName);
        Assert.assertTrue(match);


        //user is on the payment page
        PaymentPage paymentPage =  cartPage.clickOnCheckoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group .input.txt.text-validated")));
        paymentPage.selectCountry("India");
        ThankYouPage thanks = paymentPage.clickOnPlaceOrderButton();


        //user is on the thank you page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        String thankYouMessageText = thanks.getThankYouMessageTextForWebElement();
        Assert.assertTrue(thankYouMessage.equalsIgnoreCase(thankYouMessageText));

       //user is clicking on header
        header.clickOnHeaderMenu(header.getNavigationOptions(), "Sign Out");
        driver.close();
        driver.quit();

    }


}
