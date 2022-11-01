package org.screenscraper;

import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.storescraper.AssetPageObject;
import org.screenscraper.storescraper.UnityStorePageObject;

import java.util.List;

public class ScreenScraper {

    UnityStorePageObject store_object;
    AlertApiInteraction alert_object;
    AssetPageObject asset_object;
    public ScreenScraper(UnityStorePageObject inp_store, AlertApiInteraction inp_api, AssetPageObject inp_asset){
        store_object = inp_store;
        alert_object = inp_api;
        asset_object = inp_asset;
    }
    public void ScrapeAlerts(String query, int qty, String userID) {
        store_object.SearchStore(query);
        List<String> assetURLs = store_object.GetProductLinksFromStore(qty);

        for (String url: assetURLs) {
            if(asset_object.GetPageFromURL(url)){
                AlertDetails details = asset_object.GetDetails();

                details.setPostedBy(userID);
                alert_object.UploadAlert(details.toJson());
            }
        }
    }
}
