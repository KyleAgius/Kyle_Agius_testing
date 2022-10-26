package org.screenscraper.api;

public class AlertApiInteraction {

    PostManager post_manager;

    public AlertApiInteraction(PostManager inp_post){
        post_manager = inp_post;
    }

    public String UploadAlert(String JsonData) {
        return post_manager.SendPost("https://api.marketalertum.com/Alert", JsonData);
    }

    public String ClearAlerts(String UserID) {
        return post_manager.Delete("https://api.marketalertum.com/Alert?userId=" + UserID);
    }

}
