package org.screenscraper;

import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.api.PostManager;
import org.screenscraper.pageobjects.AssetPageObject;
import org.screenscraper.pageobjects.UnityStorePageObject;

import java.io.IOException;
import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owner\\Desktop\\University\\Year3_Sem1\\CPS3230 - Fundamentals of Software Testing\\assignment_part_1\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 20);

        ScreenScraper ss = new ScreenScraper();
        ss.SetAlertObject(new AlertApiInteraction(new PostManager(HttpClients.createDefault())));
        ss.SetStoreObject(new UnityStorePageObject(driver, wait));
        ss.SetAssetObject(new AssetPageObject(driver, wait));

        ss.ScrapeAlerts("Fish", 1, "f9d4119e-c37e-4af5-9a52-eec8744ff433");

        driver.quit();

       // System.out.println(new AlertApiInteraction(new PostManager(HttpClients.createDefault())).ClearAlerts("f9d4119e-c37e-4af5-9a52-eec8744ff433"));
    }
}
