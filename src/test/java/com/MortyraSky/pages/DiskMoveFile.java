package com.MortyraSky.pages;

import com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DiskMoveFile {
    public DiskMoveFile(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy(css = "#nb-3")
    private WebElement dialogMoveBtn;

    @FindBy(css = ".listing-item__info")
    private List<WebElement> nameFiles;

    @FindBy(css = "span:nth-child(3) > div > button")
    private WebElement toMoveBtn;

    public void moveToFolderFile(String fileName, String folderName){
        nameFiles.get(findFile(fileName)).click();
        BaseTest.waitForElement(toMoveBtn);
        toMoveBtn.click();
        WebElement nameDirectory = driver.findElement(By.cssSelector("div[title='"+folderName+"']"));
        nameDirectory.click();
        dialogMoveBtn.click();

    }

    public boolean checkFileInFolder(String fileName, String folderName){
        boolean res = false;

        Actions actions = new Actions(driver);
        actions.doubleClick(nameFiles.get(findFile(folderName))).build().perform();
        BaseTest.waitForElements(1);

        if(findFile(fileName) != -1)
            res = true;

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
