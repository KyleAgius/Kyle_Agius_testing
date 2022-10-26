package screenscrapertests.apitests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.api.PostManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestAlertApiInteraction {
    PostManager post_manager;
    AlertApiInteraction api;

    @BeforeEach
    public void setup() throws IOException {
        post_manager = Mockito.mock(PostManager.class);
        api = new AlertApiInteraction(post_manager);
    }

    @Test
    public void testUploadAlert(){
        //Setup
        Mockito.when(post_manager.SendPost("https://api.marketalertum.com/Alert", "data")).thenReturn("Result");

        //Execute
        String result = api.UploadAlert("data");

        //Verify
        Assertions.assertEquals("Result", result);
    }

    @Test
    public void testClearAlerts() {
        //Setup
        Mockito.when(post_manager.Delete("https://api.marketalertum.com/Alert?userId=id")).thenReturn("Result");

        //Execute
        String result = api.ClearAlerts("id");

        //Verify
        Assertions.assertEquals("Result", result);
    }

}
