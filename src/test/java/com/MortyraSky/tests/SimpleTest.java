package com.MortyraSky.tests;

import com.MortyraSky.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {

    private final String URL = "https://yandex.ru/";
    private final String QUERY = "Россия";

    SearchPage searchPage;

    @Test
    public void searchTest(){
        searchPage = new SearchPage(driver);
        searchPage.navigate(URL);
        searchPage.searchQuery(QUERY);
        Assert.assertTrue(searchPage.resultSearch(QUERY));

    }
}
