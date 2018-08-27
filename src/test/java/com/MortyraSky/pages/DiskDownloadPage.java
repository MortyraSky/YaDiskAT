package com.MortyraSky.pages;
import com.MortyraSky.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DiskDownloadPage {

    public DiskDownloadPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy(css = ".listing-item__info")
    private List<WebElement> nameFiles; // имена фалов в созданной директории

    @FindBy(css = ".version-row__version-size")
    private WebElement sizeFile;

    @FindBy(css = ".menu__group_paddingless_yes:nth-child(5) > div:nth-child(1)")
    private WebElement informationTab;

    @FindBy(css = ".versions-dialog__header > button")
    private WebElement closeWinBtn;

    @FindBy(css = ".resources-action-bar__side-right > :nth-child(2)")
    private WebElement downloadBtn;

    @FindBy(css = ".resources-action-bar__close > div > button") // переделать этот локатор, а то хрень какая-то (0_0)
    private WebElement closeHoverWindowBtn;

    double sizeFileOnServer, sizeFileOnClient;


    public void getSizeFileOnServer(String fileName){

        Actions actions = new Actions(driver);
        actions.contextClick(nameFiles.get(findFile(fileName))).build().perform(); // клик ПКМ по необходимому файлу
        informationTab.click();
        String temp = sizeFile.getText().substring(0,2) + "." + sizeFile.getText().substring(3,4);
        sizeFileOnServer = Double.parseDouble(temp);
        closeWinBtn.click();
    }

    public void getSizeFileOnClient(String fileName, String pathname){
        File myFile = new File(pathname + fileName);
        sizeFileOnClient = converterToKB(myFile.length());

    }
    public void downloadFileFromDisk(String fileName){
        getSizeFileOnServer(fileName);
        nameFiles.get(findFile(fileName)).click();
        downloadBtn.click();
        BaseTest.waitForElements(1);
        closeHoverWindowBtn.click();

    }

    public boolean resultDownloadedFile(String fileName, String pathname){
        boolean res = false;
        getSizeFileOnClient(fileName, pathname);
        if (sizeFileOnServer == sizeFileOnClient)
            res = true;

        deleteDownloadedFile(fileName,pathname); // удаление скаченного файла для возврат к первоначальным настройкам!!!
        return res;
    }

    public void deleteDownloadedFile(String fileName, String pathname){
        File myFile = new File(pathname + fileName);
        try {
            myFile.delete();

        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public double converterToKB(long numeric){

        int i = (int)numeric;
        String str = String.valueOf(i); // перевод из целого в строку
        double ddd = Double.parseDouble(str); // из строки в дабл
        double sizeFile = ddd / 1024; // перевод байтов в кБайты
        System.out.println("File size in KB: " + sizeFile);
        double newFileSize = new BigDecimal(sizeFile).setScale(1, RoundingMode.HALF_UP).doubleValue();
        System.out.println("New file size in double: " + newFileSize);
        return  newFileSize;
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
