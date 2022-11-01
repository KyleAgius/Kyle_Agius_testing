package screenscrapertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.ScreenScraper;
import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.storescraper.AssetPageObject;
import org.screenscraper.storescraper.UnityStorePageObject;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;

public class TestScreenScraper {
    UnityStorePageObject store_object;
    AlertApiInteraction alert_object;
    AssetPageObject asset_object;
    ScreenScraper scraper;

    @BeforeEach
    public void setup() {
        store_object = Mockito.mock(UnityStorePageObject.class);
        alert_object = Mockito.mock(AlertApiInteraction.class);
        asset_object = Mockito.mock(AssetPageObject.class);

        scraper = new ScreenScraper(store_object, alert_object, asset_object);
    }

    @Test
    public void testEmptyScrape() {
        //Setup
        List<String> sampleURLs = new ArrayList<String>();
        Mockito.when(store_object.GetProductLinksFromStore(0)).thenReturn(sampleURLs);

        //Execute
        scraper.ScrapeAlerts("test", 0, "sample");

        //Verify
        Mockito.verify(store_object, Mockito.times(1)).SearchStore("test");
        Mockito.verify(store_object, Mockito.times(1)).GetProductLinksFromStore(0);
        Mockito.verify(asset_object, Mockito.times(0)).GetPageFromURL(anyString());
    }

    @Test
    public void testScrapingInvalidPage() {
        //Setup
        List<String> sampleURLs = new ArrayList<String>();
        sampleURLs.add("SampleURL");
        Mockito.when(store_object.GetProductLinksFromStore(1)).thenReturn(sampleURLs);
        Mockito.when(asset_object.GetPageFromURL(anyString())).thenReturn(false);

        //Execute
        scraper.ScrapeAlerts("test", 1, "sample");

        //Verify
        Mockito.verify(store_object, Mockito.times(1)).SearchStore("test");
        Mockito.verify(store_object, Mockito.times(1)).GetProductLinksFromStore(1);
        Mockito.verify(asset_object, Mockito.times(1)).GetPageFromURL("SampleURL");
        Mockito.verify(asset_object, Mockito.times(0)).GetDetails();
    }

    //@Test
    public void testInvalidAPICall() {

    }

    @Test
    public void testScrapingOneProduct() {
        //Setup
        List<String> sampleURLs = new ArrayList<String>();
        sampleURLs.add("SampleURL");
        Mockito.when(store_object.GetProductLinksFromStore(1)).thenReturn(sampleURLs);
        Mockito.when(asset_object.GetPageFromURL(anyString())).thenReturn(true);

        AlertDetails sampleData = Mockito.mock(AlertDetails.class);
        Mockito.when(asset_object.GetDetails()).thenReturn(sampleData);
        Mockito.when(sampleData.toJson()).thenReturn("SampleJSON");

        //Execute
        scraper.ScrapeAlerts("test", 1, "sample");

        //Verify
        Mockito.verify(store_object, Mockito.times(1)).SearchStore("test");
        Mockito.verify(store_object, Mockito.times(1)).GetProductLinksFromStore(1);
        Mockito.verify(asset_object, Mockito.times(1)).GetPageFromURL("SampleURL");
        Mockito.verify(sampleData, Mockito.times(1)).setPostedBy("sample");
        Mockito.verify(alert_object, Mockito.times(1)).UploadAlert("SampleJSON");

    }


    @Test
    public void testScrapingMultipleProducts() {
        //Setup
        List<String> sampleURLs = new ArrayList<String>();

        sampleURLs.add("SampleURL");
        sampleURLs.add("SampleURL");
        sampleURLs.add("SampleURL");

        Mockito.when(store_object.GetProductLinksFromStore(3)).thenReturn(sampleURLs);
        Mockito.when(asset_object.GetPageFromURL(anyString())).thenReturn(true);

        AlertDetails sampleData = Mockito.mock(AlertDetails.class);
        Mockito.when(asset_object.GetDetails()).thenReturn(sampleData);
        Mockito.when(sampleData.toJson()).thenReturn("SampleJSON");

        //Execute
        scraper.ScrapeAlerts("test", 3, "sample");

        //Verify
        Mockito.verify(store_object, Mockito.times(1)).SearchStore("test");
        Mockito.verify(store_object, Mockito.times(1)).GetProductLinksFromStore(3);
        Mockito.verify(asset_object, Mockito.times(3)).GetPageFromURL("SampleURL");
        Mockito.verify(sampleData, Mockito.times(3)).setPostedBy("sample");
        Mockito.verify(alert_object, Mockito.times(3)).UploadAlert("SampleJSON");
    }
}
