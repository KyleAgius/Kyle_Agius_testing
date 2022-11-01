package org.screenscraper;

import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.api.PostManager;
import org.screenscraper.storescraper.AssetPageObject;
import org.screenscraper.storescraper.UnityStorePageObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owner\\Desktop\\University\\Year3_Sem1\\CPS3230 - Fundamentals of Software Testing\\assignment_part_1\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        UnityStorePageObject store = new UnityStorePageObject(driver, wait);
        AlertApiInteraction api = new AlertApiInteraction(new PostManager(HttpClients.createDefault()));
        AssetPageObject asset = new AssetPageObject(driver, wait);

        ScreenScraper ss = new ScreenScraper(store, api, asset);

        ss.ScrapeAlerts("Swords", 3, "f9d4119e-c37e-4af5-9a52-eec8744ff433");

        driver.quit();



        //System.out.println(new AlertApiInteraction(new PostManager(HttpClients.createDefault())).ClearAlerts("f9d4119e-c37e-4af5-9a52-eec8744ff433"));
    }
}
