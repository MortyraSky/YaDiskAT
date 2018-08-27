package com.MortyraSky.tests;

import com.MortyraSky.pages.*;
import javafx.scene.layout.Priority;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProperties;

public class MainTest extends BaseTest {

    final String URL = "https://disk.yandex.ru/";
    final String USERNAME = ""; // для входа использовать свой логин
    final String PASSWORD = ""; // для входа исп свой пароль
    final String PATH = ""; // путь до файла, который хотим загрузить
    final String DOWNLOADFILENAME = ""; // имя загружаемого файла с раширением
    final String FOLDERNAME = "TRASH"; // название создаваемой папки в Яндекс.Диске
    final String PATHNAME = ""; // папка загрузок браузера Chrome


    AuthPage authPage;
    DiskUploadFile diskUploadFile;
    DiskCreateFolderPage diskCreateFolderPage;
    DiskMoveFile diskMoveFile;
    DiskDownloadPage diskDownloadPage;
    LogoutPage logoutPage;

    @Test(priority = 0)
    public void authTest(){
        authPage = new AuthPage(driver);
        boolean res;

        authPage.navigate(ConfigProperties.getTestProperty("url"));
        authPage.userAuth(USERNAME, PASSWORD);
        res = authPage.resAuth(USERNAME);
        Assert.assertTrue(res);
    }

    @Test(priority = 1)
    public void uploadFileTest(){
        diskUploadFile = new DiskUploadFile(driver);
        boolean res;

        diskUploadFile.downloadFile(PATH);
        res = diskUploadFile.findDownloadFile(DOWNLOADFILENAME);
        Assert.assertTrue(res);

    }

    @Test(priority = 2)
    public void createFolderTest(){
        diskCreateFolderPage = new DiskCreateFolderPage(driver);
        boolean res;

        diskCreateFolderPage.createFolder(FOLDERNAME);
        res = diskCreateFolderPage.findFolder(FOLDERNAME);
        Assert.assertTrue(res);

    }

    @Test(priority = 3)
    public void moveFileTest(){
        diskMoveFile = new DiskMoveFile(driver);
        boolean res;

        diskMoveFile.moveToFolderFile(DOWNLOADFILENAME, FOLDERNAME);
        res = diskMoveFile.checkFileInFolder(DOWNLOADFILENAME, FOLDERNAME);
        Assert.assertTrue(res);

    }

    @Test(priority = 4)
    public void downloadFileTest(){
        diskDownloadPage = new DiskDownloadPage(driver);
        boolean res;

        diskDownloadPage.downloadFileFromDisk(DOWNLOADFILENAME);
        res = diskDownloadPage.resultDownloadedFile(DOWNLOADFILENAME, PATHNAME);
        Assert.assertTrue(res);
    }

    @Test(priority = 5)
    public void logoutFromServicesTest(){
        boolean res;
        logoutPage = new LogoutPage(driver);
        logoutPage.logoutFromDisk();
        res = logoutPage.resLogout();
        Assert.assertFalse(res);

    }
}