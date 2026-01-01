package org.example.commonpages;

import org.example.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Header extends Utility {

    WebDriver driver ;
    public Header(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".btn.btn-custom")
    List<WebElement> navigationOptions;

    public List<WebElement> getNavigationOptions() {
        return navigationOptions;
    }

    public void clickOnHeaderMenu(List<WebElement> elements , String nameOfButton){
        elements.stream()
                .filter(option->option.getText()
                .contains(nameOfButton))
                .findFirst()
                .orElse(null)
                .click();

    }


}
