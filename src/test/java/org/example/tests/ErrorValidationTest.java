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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationTest extends Base {


    String jsonFilePath = "C:\\Users\\shami\\IdeaProjects\\JavaSeleniumTestAutomationFramework\\src\\test\\java\\org\\example\\data\\datafile\\submitorder.json";
    String message = "Incorrect email or password.";
    String wrongMessage = "Incorrect email or passwor.";





    @Test(priority = 3 ,dataProvider = "getData")
    public void loginErrorValidationTest(HashMap<String ,String> input) throws IOException {

        WebDriver driver = initializeDriver();
        LandingPage landingPage =new LandingPage(driver);
        landingPage.enterEmailId(input.get("email"));
        landingPage.enterPassword(input.get("wrongPassword"));
        landingPage.clickOnLoginButton();
        String text = landingPage.getErrorMessage();
        Assert.assertEquals(text,message);





    }

    @Test(priority = 1  , groups = {"regression"}  ,dataProvider = "getData")
    public void productErrorValidationTest(HashMap<String ,String> input) throws IOException {

        WebDriver driver = initializeDriver();


        //user is on the landing page
        LandingPage landingPage =new LandingPage(driver);
        landingPage.enterEmailId(input.get("email"));
        landingPage.enterPassword(input.get("correctPassword"));
        landingPage.clickOnLoginButton();

        //user is on the productCatalogPage
        ProductCatalogPage productCatalogPage = landingPage.clickOnLoginButton();
        WebElement shoe = productCatalogPage.getProductByName(input.get("product"));
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
        Boolean match = cartPage.isProductInCart(itemsInCart, input.get("product"));
        Assert.assertTrue(match);




    }


     @Test(priority = 2 ,dataProvider = "getData")
    public void loginErrorFailedForScreenshotTest(HashMap<String ,String> input) throws IOException {

        WebDriver driver = initializeDriver();
        LandingPage landingPage =new LandingPage(driver);
        landingPage.enterEmailId(input.get("email"));
        landingPage.enterPassword(input.get("wrongPassword"));
        landingPage.clickOnLoginButton();
        String text = landingPage.getErrorMessage();
        Assert.assertEquals(text,wrongMessage);





    }

    @DataProvider
    public Object [][] getData() throws IOException {
        List<HashMap<String,String>> list = readDataFromJsonFileToMap(jsonFilePath);
        return new Object[][] {{list.get(0)}};

    }



}
