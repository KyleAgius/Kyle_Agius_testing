package org.api;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertPageObject {
    WebDriver driver;
    WebDriverWait wait;
    WebElement alert;

    public AlertPageObject(WebDriver inp_Driver, WebDriverWait inp_wait, WebElement inp_alert){
        driver = inp_Driver;
        wait = inp_wait;
        alert = inp_alert;
    }

    public String GetAlertIconURL(){
        WebElement elem = GetByXPath("./tbody/tr[1]/td/h4/img");
        String result = elem.getAttribute("src");
        result = result.substring(37);
        return result;
    }

    public String GetHeading(){
        WebElement elem = GetByXPath("./tbody/tr[1]/td/h4");
        return elem.getText();
    }

    public String GetDesc(){
        WebElement elem = GetByXPath("./tbody/tr[3]/td");
        return elem.getText();
    }

    public String GetImageURL(){
        WebElement elem = GetByXPath("./tbody/tr[2]/td/img");
        return elem.getAttribute("src");
    }

    public String GetPrice(){
        WebElement elem = GetByXPath("./tbody/tr[4]/td");
        return elem.getText();
    }

    public String GetProductURL(){
        WebElement elem = GetByXPath("./tbody/tr[5]/td/a");
        return elem.getAttribute("href");
    }

    public WebElement GetByXPath(String xpath){
        try {
            return alert.findElement(By.xpath(xpath));
        }catch (TimeoutException e){
            return null;
        }
    }
}
