package org.example.webpages;

import org.example.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends Utility {

    WebDriver driver ;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css=".mb-3")
    List<WebElement> products;

    By addToCart = By.cssSelector(".card-body button:last-of-type");



    By tostContainer =  By.cssSelector("#toast-container");



    By animation =  By.cssSelector(".ng-animating");


   public List<WebElement> getProductList(){
       return products;

   }
    public By getTostContainer() {
        return tostContainer;
    }


    public By getAddToCartLocator() {
        return addToCart;
    }


    public By getAnimation() {
        return animation;
    }


    public WebElement getProductByName(String productName){
       WebElement prod = getProductList().stream().
               filter(product->product.findElement(By.cssSelector("h5 b")).
                       getText().
                       equalsIgnoreCase(productName)).
               findFirst().
               orElse(null);
       return prod;
   }


   public void addProductToCart(WebElement product){
       product.findElement(getAddToCartLocator()).click();
   }



}
