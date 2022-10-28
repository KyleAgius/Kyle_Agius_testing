package org.api;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.api.PostManager;

import java.util.ArrayList;
import java.util.List;

public class MarketAlertWebViewer {
    WebDriver driver;
    WebDriverWait wait;

    public MarketAlertWebViewer(WebDriver inp_Driver, WebDriverWait inp_wait){
        driver = inp_Driver;
        wait = inp_wait;
        driver.get("https://www.marketalertum.com/");
    }

    public void LogIn(String userID){
        driver.get("https://www.marketalertum.com/Alerts/Login");
        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserId")));
        loginField.sendKeys(userID);
        loginField.submit();
    }

    public void PostAlert(String id, int alertType, String Title, String desc, int price, String url, String image){
        PostManager poster = new PostManager(HttpClients.createDefault());
        AlertDetails alert = new AlertDetails();

        alert.setPostedBy(id);
        alert.setAlertType(alertType);
        alert.setTitle(Title);
        alert.setDesc(desc);
        alert.setPrice(price);
        alert.setUrl(url);
        alert.setImageSrc(image);

        String alertJson = alert.toJson();

        poster.SendPost("https://api.marketalertum.com/Alert", alertJson);
    }

    public List<AlertPageObject> GetAlerts(int qty){
        driver.get("https://www.marketalertum.com/Alerts/List");

        List<AlertPageObject> alertObj = new ArrayList<>();
        List<WebElement> alerts = driver.findElements(By.xpath("//table"));

        int count = 0;
        for (WebElement element: alerts) {
            if(count >= qty){
                break;
            }

            count++;
            alertObj.add(new AlertPageObject(driver, wait, element));
        }
        return alertObj;
    }


}
