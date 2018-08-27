package com.MortyraSky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LogoutPage {
    // .menu__group > li:nth-child(2)  - logoutBtn
    public LogoutPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(css = ".listing-item__info")
    private List<WebElement> nameFiles;

    @FindBy(css = ".user2_hide-name > a[tabindex='0']")
    private WebElement userMenu;

    @FindBy(css = ".menu__group > li:nth-child(2)")
    private WebElement logoutBtn;

    String oldHref;


    public void logoutFromDisk(){
        //driver.navigate().back();
        //nameFiles.get(findFile(folderName)).click();
        oldHref = driver.getCurrentUrl();
        userMenu.click();
        logoutBtn.click();

    }
    public boolean resLogout(){
        boolean res = true;
        String currentHref = driver.getCurrentUrl();
        if (!currentHref.equals(oldHref))
            res = false;

        return res;
    }

    public int findFile(String fileName){

        int index = -1;

        for (int i = 0; i < nameFiles.size(); i++)
            if (nameFiles.get(i).getText().equals(fileName)){
                index = i;
                break;
            }

        return index;
    }

}
