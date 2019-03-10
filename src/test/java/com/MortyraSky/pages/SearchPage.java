package com.MortyraSky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }
    private WebDriver driver;

    @FindBy(id = "text")
    private WebElement inputField;

    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;

    @FindBy(css = ".entity-search__header")
    private WebElement result;

    public void navigate(String URL){
        driver.get(URL);
    }

    public void searchQuery(String query){
        inputField.sendKeys(query);
        searchBtn.click();
    }

    public boolean resultSearch(String query){
        return result.getText().equals(query);
    }

}
