package com.MortyraSky.pages;

import com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {

    public AuthPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy (css = ".header__login-link")
    private WebElement signInBtn;

    @FindBy(css = "input[name='login']")
    private WebElement inputLoginField;

    @FindBy(css = "input[name='passwd']")
    private WebElement inputPassField;

    @FindBy(css = "button[class='passport-Button']")
    private WebElement enterBtn;

    @FindBy(css = ".user2_hide-name > a[tabindex='0']")
    private WebElement userMenu;

    @FindBy(css = "div >.user-account__name")
    private WebElement currentUserName;


    public void userAuth(String name, String pass){
        BaseTest.waitForElement(signInBtn);
        signInBtn.click();

        inputLoginField.sendKeys(name);
        inputPassField.sendKeys(pass);
        enterBtn.click();
    }

    public boolean resAuth(String name){ // получаем имя пользователя на странице
        boolean resAuth = false;

        userMenu.click();
        String userName = currentUserName.getText();

        if (userName.equals(name))
            resAuth = true;
        else resAuth = false;

        System.out.println("Result of compare: " + resAuth);
        return resAuth;

    }


    public void navigate(String URL){
        driver.get(URL);
    }

}
