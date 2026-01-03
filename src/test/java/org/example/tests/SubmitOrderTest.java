package org.example.tests;

import org.example.commonpages.Header;
import org.example.testutility.Base;
import org.example.webpages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends Base {
    WebDriver driver;
    String jsonFilePath = "C:\\Users\\shami\\IdeaProjects\\JavaSeleniumTestAutomationFramework\\src\\test\\java\\org\\example\\data\\datafile\\submitorder.json";



   @Test( dataProvider = "getData", groups = {"regression"} )

    public void orderTest(HashMap<String ,String> input) throws IOException {


        driver = initializeDriver();

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


        //user is on the payment page
        PaymentPage paymentPage =  cartPage.clickOnCheckoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group .input.txt.text-validated")));
        paymentPage.selectCountry(input.get("country"));
        ThankYouPage thanks = paymentPage.clickOnPlaceOrderButton();


        //user is on the thank you page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        String thankYouMessageText = thanks.getThankYouMessageTextForWebElement();
        Assert.assertTrue(input.get("thankYouMessage").equalsIgnoreCase(thankYouMessageText));

       //user is clicking on header
        header.clickOnHeaderMenu(header.getNavigationOptions(), "Sign Out");




    }
    @Test(dependsOnMethods = {"orderTest"})
    public void orderHistoryTest()  {
        try {
            driver = initializeDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I am dependent on OrderTests");
        Assert.assertTrue(true);

    }


    @DataProvider
    public Object [][] getData() throws IOException {
       List<HashMap<String,String>> list = readDataFromJsonFileToMap(jsonFilePath);
       return new Object[][] {{list.get(0)},{list.get(1)}};

    }





}
