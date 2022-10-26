package org.screenscraper.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class UnityStorePageObject {
    WebDriver store_driver;
    WebDriverWait wait;

    public UnityStorePageObject(WebDriver inp_Driver, WebDriverWait inp_Wait){
        store_driver = inp_Driver;
        wait = inp_Wait;
        inp_Driver.get("https://assetstore.unity.com/");
    }


    public void SearchStore(String query){
        String xpathToSearchBar = "//input[@data-test='search-bar-input']";
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathToSearchBar)));
        searchField.sendKeys(query);
        searchField.submit();
    }

    public List<String> GetProductLinksFromStore(int qty){
        List<String> results = new ArrayList<>();
        String xpathToGrid = "//*[@id=\"main-layout-scroller\"]/div/div[1]/div[2]/div/div/div/div/div[2]/div[1]/div/div[4]/div/div//div[@class=\"_138_e\"]/a";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathToGrid)));

        List<WebElement> assetGrid = store_driver.findElements(By.xpath(xpathToGrid));
        for (int i = 0; i < qty; i++) {
            if(assetGrid.get(i) == null){
                break;
            }
            results.add(assetGrid.get(i).getAttribute("href"));
        }

        return results;
    }
}
