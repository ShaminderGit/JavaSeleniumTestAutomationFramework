package org.example.webpages;

import org.example.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Utility {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver , this);
    }

   // locators

    @FindBy(id="userEmail")
    WebElement inputEmail;

    @FindBy(id="userPassword")
    WebElement inputPassword;

    @FindBy(id="login")
    WebElement loginButton;

    // actions

    public void enterEmailId(String email){
        inputEmail.sendKeys(email);
    }

    public void enterPassword(String password){
        inputPassword.sendKeys(password);
    }

    public ProductCatalogPage clickOnLoginButton(){

        loginButton.click();
        return new ProductCatalogPage(driver);
    }




}
