package com.MortyraSky.pages;

import com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DiskUploadFile {

    public DiskUploadFile(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(css = "div > input[type='file']")
    private WebElement downloadInputField;

    @FindBy(css = ".b-dialog-upload__actions > button:nth-child(4)")
    private WebElement closeDownloadWindowBtn;

    @FindBy(css = ".clamped-text")
    private List<WebElement> filesNames;

    public void downloadFile(String path){
        downloadInputField.sendKeys(path);
        BaseTest.waitForElement(closeDownloadWindowBtn);
        closeDownloadWindowBtn.click();
        BaseTest.waitElements(filesNames);

    }

    public boolean findDownloadFile(String fileName){
        boolean res = false;
        for (int i = 0; i < filesNames.size(); i++){
            if(filesNames.get(i).getText().equals(fileName))
                res = true;
        }
        return res;
    }

}
