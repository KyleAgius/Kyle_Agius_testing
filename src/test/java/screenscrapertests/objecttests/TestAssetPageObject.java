package screenscrapertests.objecttests;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.pageobjects.AssetPageObject;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

public class TestAssetPageObject {

    WebDriver mock_driver;
    WebDriverWait wait;
    WebElement component;
    AssetPageObject assetPage;

    @BeforeEach
    public void setup() {
        mock_driver = Mockito.mock(WebDriver.class);
        wait = Mockito.mock(WebDriverWait.class);
        component = Mockito.mock(WebElement.class);
        assetPage = new AssetPageObject(mock_driver, wait);
    }

    @Test
    public void testValidURL(){
        //Setup
        Mockito.doNothing().when(mock_driver).get("Sample");

        //Execute
        boolean result = assetPage.GetPageFromURL("Sample");

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testInvalidURL(){
        //Setup
        Mockito.doThrow(InvalidArgumentException.class).when(mock_driver).get("Sample");

        //Execute
        boolean result = assetPage.GetPageFromURL("Sample");

        //Verify
        Assertions.assertFalse(result);
    }

    @Test
    public void testScrapingProductName(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(component);
        Mockito.when(component.getText()).thenReturn("Sample");

        //Execute
        String result = assetPage.GetProductName();

        //Verify
        Assertions.assertEquals("Sample", result);
    }

    @Test
    public void testScrapingProductNameNotFound(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(null);

        //Execute
        String result = assetPage.GetProductName();

        //Verify
        Assertions.assertEquals("Title-Not-Found", result);
    }

    @Test
    public void testScrapingProductImage(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(component);
        Mockito.when(component.getCssValue("background-image")).thenReturn("-----Sample--");

        //Execute
        String result = assetPage.GetProductImageURL();

        //Verify
        Assertions.assertEquals("Sample", result);
    }

    @Test
    public void testScrapingProductImageNotFound(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(null);

        //Execute
        String result = assetPage.GetProductImageURL();

        //Verify
        Assertions.assertEquals("", result);
    }

    @Test
    public void testScrapingProductDescription(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(component);
        Mockito.when(component.getText()).thenReturn("Sample");

        //Execute
        String result = assetPage.GetProductDesc();

        //Verify
        Assertions.assertEquals("Sample", result);
    }

    @Test
    public void testScrapingProductDescriptionNotFound(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(null);

        //Execute
        String result = assetPage.GetProductDesc();

        //Verify
        Assertions.assertEquals("Desc-Not-Found", result);
    }

    @Test
    public void testScrapingProductPrice(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(component);
        Mockito.when(component.getText()).thenReturn("€10.50");

        //Execute
        int result = assetPage.GetProductPrice();

        //Verify
        Assertions.assertEquals(1050, result);
    }

    @Test
    public void testScrapingProductPriceForFreeProduct(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(component);
        Mockito.when(component.getText()).thenReturn("FREE");

        //Execute
        int result = assetPage.GetProductPrice();

        //Verify
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testScrapingProductPriceNotFound(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(null);

        //Execute
        int result = assetPage.GetProductPrice();

        //Verify
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testCompilingScrapeToObject(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(component);
        Mockito.when(mock_driver.getCurrentUrl()).thenReturn("Sample");
        Mockito.when(component.getText()).thenReturn("FREE");
        Mockito.when(component.getCssValue("background-image")).thenReturn("-----Sample--");

        //Execute
        AlertDetails result = assetPage.GetDetails();

        //Verify
        Assertions.assertEquals(6, result.getAlertType());
        Assertions.assertEquals("FREE", result.getTitle());
        Assertions.assertEquals("FREE", result.getDesc());
        Assertions.assertEquals("Sample", result.getUrl());
        Assertions.assertEquals("Sample", result.getImageSrc());
        Assertions.assertEquals(0, result.getPrice());
    }

    @Test
    public void testXPathElementNotBeingFound(){
        //Setup
        Mockito.when(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(anyString())))).thenReturn(null);

        //Execute
        WebElement result = assetPage.GetByXPath("test");

        //Verify
        Assertions.assertEquals(null, result);
    }

    @Test
    public void testXPathTimeout(){
        //Setup
        Mockito.doThrow(TimeoutException.class).when(wait).until(anyObject());

        //Execute
        WebElement result = assetPage.GetByXPath("test");

        //Verify
        Assertions.assertEquals(null, result);
    }


}
