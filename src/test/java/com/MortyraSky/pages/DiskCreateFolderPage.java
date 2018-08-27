package com.MortyraSky.pages;

import com.MortyraSky.tests.BaseTest;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DiskCreateFolderPage {

    public DiskCreateFolderPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy(xpath = "//*[@class='sidebar__buttons']/descendant::button")
    private WebElement createBtn;

    @FindBy(css = ".create-resource-popup-with-anchor__create-items > button:nth-child(1)")
    private WebElement createFolderBtn;

    @FindBy(css = "input[value='Новая папка']")
    private WebElement renameFileInputField;

    @FindBy(css = ".confirmation-dialog__footer > button")
    private WebElement saveBtn;

    @FindBy(css = ".clamped-text")
    private List<WebElement> filesNames;


    public void createFolder(String nameFolder){
        createBtn.click();
        createFolderBtn.click();
        renameFileInputField.clear();
        renameFileInputField.sendKeys(nameFolder);
        saveBtn.click();
        BaseTest.waitForElements(1);
    }

    public boolean findFolder(String nameFolder){
        boolean res = false;
        BaseTest.waitElements(filesNames);
        BaseTest.waitForElements(1);
        for (int i = 0; i < filesNames.size(); i++){
            if(filesNames.get(i).getText().equals(nameFolder))
                res = true;
        }
        return res;
    }
}
