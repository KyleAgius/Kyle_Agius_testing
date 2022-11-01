package apitests;

import org.apache.http.impl.client.HttpClients;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.api.PostManager;

public class MarketAlertPostService {
    PostManager poster;
    AlertDetails alert;

    public MarketAlertPostService(PostManager inp_poster, AlertDetails inp_alert){
        poster = inp_poster;
        alert = inp_alert;
    }

    public void PostAlert(String id, int alertType, String Title, String desc, int price, String url, String image){
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
}
