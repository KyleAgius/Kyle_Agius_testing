package screenscrapertests.apitests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.screenscraper.api.AlertApiInteraction;
import org.screenscraper.api.PostManager;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;

public class TestPostManager {
    HttpClient mock_client;
    HttpResponse mock_response;
    HttpEntity mock_entity;
    PostManager postManager;

    @BeforeEach
    public void setup() throws IOException {
        mock_client = Mockito.mock(HttpClient.class);
        mock_response = Mockito.mock(HttpResponse.class);
        mock_entity = Mockito.mock(HttpEntity.class);
        postManager = new PostManager(mock_client);

        Mockito.when(mock_client.execute(anyObject())).thenReturn(mock_response);
        Mockito.when(mock_response.getEntity()).thenReturn(mock_entity);
        Mockito.when(mock_entity.toString()).thenReturn("Result");
    }

    @Test
    public void testValidPost() throws IOException {
        //Execute
        String result = postManager.SendPost("url", "{'data':'sample'}");

        //Verify
        Mockito.verify(mock_response).getEntity();
    }

    @Test
    public void testEmptyPost() {
        //Execute
        String result = postManager.SendPost("url", "");

        //Verify
        Mockito.verify(mock_response).getEntity();
    }

    @Test
    public void testClientProtocolExceptionInPost() throws IOException {
        //Setup
        Mockito.doThrow(ClientProtocolException.class).when(mock_client).execute(anyObject());

        //Execute
        String result = postManager.SendPost("url", "{'data':'sample'}");

        //Verify
        Assertions.assertEquals("ClientProtocolException", result);
    }

    @Test
    public void testIOExceptionInPost() throws IOException {
        //Setup
        Mockito.doThrow(IOException.class).when(mock_client).execute(anyObject());

        //Execute
        String result = postManager.SendPost("url", "{'data':'sample'}");

        //Verify
        Assertions.assertEquals("IOException", result);
    }

    @Test
    public void testDelete() {
        //Execute
        String result = postManager.Delete("url");

        //Verify
        Mockito.verify(mock_response).getEntity();
    }

    @Test
    public void testClientProtocolExceptionInDelete() throws IOException {
        //Setup
        Mockito.doThrow(ClientProtocolException.class).when(mock_client).execute(anyObject());

        //Execute
        String result = postManager.Delete("url");

        //Verify
        Assertions.assertEquals("ClientProtocolException", result);
    }

    @Test
    public void testIOExceptionInDelete() throws IOException {
        //Setup
        Mockito.doThrow(IOException.class).when(mock_client).execute(anyObject());

        //Execute
        String result = postManager.Delete("url");

        //Verify
        Assertions.assertEquals("IOException", result);
    }
}
