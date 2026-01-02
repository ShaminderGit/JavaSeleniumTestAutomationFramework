package org.example.tests;

import org.example.commonpages.Header;
import org.example.testutility.Base;
import org.example.webpages.CartPage;
import org.example.webpages.LandingPage;
import org.example.webpages.ProductCatalogPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ErrorValidationTest extends Base {

    String email = "thisis@gmail.com";
    String password = "Qwerty@1234";
    String correctPaassword ="Qwerty@123";
    String message = "Incorrect email or password.";
    String productName = "ADIDAS ORIGINAL";

    @Test
    public void loginErrorValidation() throws IOException {

        WebDriver driver = initializeDriver();
        LandingPage landingPage =new LandingPage(driver);
        landingPage.enterEmailId(email);
        landingPage.enterPassword(password);
        landingPage.clickOnLoginButton();
        String text = landingPage.getErrorMessage();
        Assert.assertEquals(text,message);
        tearDown();



    }

    @Test
    public void productErrorValidation() throws IOException {

        WebDriver driver = initializeDriver();


        //user is on the landing page
        LandingPage landingPage =new LandingPage(driver);
        landingPage.enterEmailId(email);
        landingPage.enterPassword(correctPaassword);
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
        tearDown();


    }

}
