package org.screenscraper.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.screenscraper.api.AlertDetails;

public class AssetPageObject {
    WebDriver store_driver;
    WebDriverWait wait;

    public AssetPageObject(WebDriver inp_Driver, WebDriverWait inp_wait){
        store_driver = inp_Driver;
        wait = inp_wait;
    }

    public boolean GetPageFromURL(String url){
        try{
            store_driver.get(url);
        }catch (InvalidArgumentException e){
            return false;
        }
        return true;
    }

    public String GetProductName(){
        WebElement nameHeader = GetByXPath("//*[@id=\"main-layout-scroller\"]/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/h1");
        if(nameHeader == null){
            return "Title-Not-Found";
        }
        return nameHeader.getText();
    }

    public String GetProductDesc(){
        WebElement descDiv = GetByXPath("//*[@id=\"main-layout-scroller\"]/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[3]/div[1]");
        if(descDiv == null){
            return "Desc-Not-Found";
        }
        return descDiv.getText();
    }

    public String GetProductImageURL(){
        WebElement imageThumb = GetByXPath("//*[@id=\"image-strip-page\"]/div//div//div[contains(@class,'screenshot')]");
        if(imageThumb == null){
            return "";
        }
        String result = imageThumb.getCssValue("background-image");
        return result.substring(5,result.length()-2);
    }

    public int GetProductPrice(){
        WebElement price = GetByXPath("//*[@id=\"main-layout-scroller\"]/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div");
        if(price == null){ return 0; }
        int priceInCents = 0;
        if(price.getText() != "FREE"){
            priceInCents = (int)(Double.valueOf(price.getText().substring(1))*100);
        }
        return priceInCents;
    }

    public AlertDetails GetDetails(){
        AlertDetails dataFound = new AlertDetails();

        dataFound.setAlertType(6);
        dataFound.setTitle(this.GetProductName());
        dataFound.setDesc(this.GetProductDesc());
        dataFound.setUrl(store_driver.getCurrentUrl());
        dataFound.setImageSrc(this.GetProductImageURL());
        dataFound.setPrice(this.GetProductPrice());

        return  dataFound;
    }

    public WebElement GetByXPath(String xpath){
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        }catch (TimeoutException e){
            return null;
        }
    }

}
