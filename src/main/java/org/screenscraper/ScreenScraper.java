package org.screenscraper;

import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.pageobjects.AssetPageObject;
import org.screenscraper.pageobjects.UnityStorePageObject;

import java.util.List;

public class ScreenScraper {
    UnityStorePageObject store_object;
    AlertApiInteraction alert_object;
    AssetPageObject asset_object;

    public void SetStoreObject(UnityStorePageObject inp_object){
        store_object = inp_object;
    }
    public void SetAlertObject(AlertApiInteraction inp_object){
        alert_object = inp_object;
    }
    public void SetAssetObject(AssetPageObject inp_object){
        asset_object = inp_object;
    }

    public void ScrapeAlerts(String query, int qty, String userID) {
        store_object.SearchStore(query);
        List<String> assetURLs = store_object.GetProductLinksFromStore(qty);

        for (String url: assetURLs) {
            if(asset_object.GetPageFromURL(url)){
                AlertDetails details = asset_object.GetDetails();

                details.setPostedBy(userID);
                System.out.println(alert_object.UploadAlert(details.toJson()));
            }
        }
    }
}
