package screenscrapertests.objecttests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.screenscraper.storescraper.UnityStorePageObject;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

public class TestUnityStorePageObject {
    WebDriver mock_driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        //Create Mock Drivers
        mock_driver = Mockito.mock(WebDriver.class);
        wait = Mockito.mock(WebDriverWait.class);
    }

    @Test
    public void testSearchingTheStore() throws Exception {
        //Setup
        WebElement result = Mockito.mock(WebElement.class);
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(result);
        UnityStorePageObject obj = new UnityStorePageObject(mock_driver, wait);

        //Execute
        obj.SearchStore("Cake");

        //Verify
        Mockito.verify(result).sendKeys("Cake");
    }

    @Test
    public void testScrapingProductGridForOneValue(){
        //Setup
        List<WebElement> grid_mock = Mockito.mock(List.class);
        WebElement asset_mock = Mockito.mock(WebElement.class);

        Mockito.when(mock_driver.findElements(By.xpath(anyString()))).thenReturn(grid_mock);
        Mockito.when(grid_mock.get(anyInt())).thenReturn(asset_mock);
        Mockito.when(asset_mock.getAttribute("href")).thenReturn("DummyURL");

        UnityStorePageObject obj = new UnityStorePageObject(mock_driver, wait);

        //Execute
        List<String> results = obj.GetProductLinksFromStore(1);

        //Verify
        Mockito.verify(asset_mock, Mockito.times(1)).getAttribute("href");
        Assertions.assertEquals("[DummyURL]",results.toString());
    }

    @Test
    public void testScrapingProductGridForMultipleValues(){
        //Setup (same as previous)
        List<WebElement> grid_mock = Mockito.mock(List.class);
        WebElement asset_mock = Mockito.mock(WebElement.class);

        Mockito.when(mock_driver.findElements(By.xpath(anyString()))).thenReturn(grid_mock);
        Mockito.when(grid_mock.get(anyInt())).thenReturn(asset_mock);
        Mockito.when(asset_mock.getAttribute("href")).thenReturn("DummyURL");

        UnityStorePageObject obj = new UnityStorePageObject(mock_driver, wait);

        //Execute
        List<String> results = obj.GetProductLinksFromStore(3);

        //Verify
        Mockito.verify(asset_mock, Mockito.times(3)).getAttribute("href");
        Assertions.assertEquals("[DummyURL, DummyURL, DummyURL]",results.toString());
    }

    @Test
    public void testScrapingEmptyProductGrid(){
        //Setup
        List<WebElement> grid_mock = Mockito.mock(List.class);

        Mockito.when(mock_driver.findElements(By.xpath(anyString()))).thenReturn(grid_mock);
        Mockito.when(grid_mock.get(anyInt())).thenReturn(null);

        UnityStorePageObject obj = new UnityStorePageObject(mock_driver, wait);

        //Execute
        List<String> results = obj.GetProductLinksFromStore(3);

        //Verify
        Mockito.verify(grid_mock, Mockito.times(1)).get(anyInt());
        Assertions.assertEquals("[]",results.toString());
    }

    @Test
    public void testScrapingGridForMoreValuesThanFound(){
        //Setup
        List<WebElement> grid_mock = Mockito.mock(List.class);
        WebElement asset_mock = Mockito.mock(WebElement.class);

        Mockito.when(mock_driver.findElements(By.xpath(anyString()))).thenReturn(grid_mock);

        Mockito.when(grid_mock.get(0)).thenReturn(asset_mock);
        Mockito.when(grid_mock.get(1)).thenReturn(asset_mock);

        Mockito.when(asset_mock.getAttribute("href")).thenReturn("DummyURL");

        UnityStorePageObject obj = new UnityStorePageObject(mock_driver, wait);

        //Execute
        List<String> results = obj.GetProductLinksFromStore(4);

        //Verify
        Mockito.verify(asset_mock, Mockito.times(2)).getAttribute("href");
        Assertions.assertEquals("[DummyURL, DummyURL]",results.toString());
    }
}
