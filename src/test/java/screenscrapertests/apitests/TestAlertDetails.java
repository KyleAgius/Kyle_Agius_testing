package screenscrapertests.apitests;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.screenscraper.api.AlertDetails;
import org.screenscraper.api.PostManager;

import java.io.IOException;

import static org.mockito.Matchers.anyObject;

public class TestAlertDetails {

    AlertDetails details;

    @BeforeEach
    public void setup() throws IOException {
       details = new AlertDetails();
    }

    @Test
    public void testAlertType(){
        //Setup
        details.setAlertType(3);

        //Execute
        Assertions.assertEquals(3, details.getAlertType());
    }

    @Test
    public void testAlertTitle() {
        //Setup
        details.setTitle("sample");

        //Execute
        Assertions.assertEquals("sample", details.getTitle());
    }

    @Test
    public void testAlertDesc() {
        //Setup
        details.setDesc("sample");

        //Execute
        Assertions.assertEquals("sample", details.getDesc());
    }

    @Test
    public void testAlertURL() {
        //Setup
        details.setUrl("sample");

        //Execute
        Assertions.assertEquals("sample", details.getUrl());
    }

    @Test
    public void testAlertImage() {
        //Setup
        details.setImageSrc("sample");

        //Execute
        Assertions.assertEquals("sample", details.getImageSrc());
    }

    @Test
    public void testAlertPoster() {
        //Setup
        details.setPostedBy("sample");

        //Execute
        Assertions.assertEquals("sample", details.getPostedBy());
    }

    @Test
    public void testAlertPrice() {
        //Setup
        details.setPrice(5);

        //Execute
        Assertions.assertEquals(5, details.getPrice());
    }

    @Test
    public void testJsonCreation() {
        //Setup
        details.setAlertType(3);
        details.setTitle("sampleTitle");
        details.setDesc("sampleDesc");
        details.setUrl("sampleUrl");
        details.setImageSrc("sampleImg");
        details.setPostedBy("samplePosted");
        details.setPrice(5);

        //Execute
        Assertions.assertEquals(
                "{'postedBy':'samplePosted','alertType':3,'heading':'sampleTitle','priceInCents':5,'imageUrl':'sampleImg','description':'sampleDesc','url':'sampleUrl'}",
                details.toJson().replace('"', '\''));
    }

}
