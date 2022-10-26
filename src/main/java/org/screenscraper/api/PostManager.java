package org.screenscraper.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostManager {
    HttpClient httpClient;
    public PostManager(HttpClient inp_client){
        httpClient = inp_client;
    }

    public String SendPost(String urlString, String jsonData) {
        try{
            HttpPost post = new HttpPost(urlString);

            if(jsonData != ""){
                StringEntity requestEntity = new StringEntity(jsonData,ContentType.APPLICATION_JSON);
                post.setEntity(requestEntity);
            }

            HttpResponse response = httpClient.execute(post);

            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            return "ClientProtocolException";
        } catch (IOException e) {
            return "IOException";
        }
    }

    public String Delete(String url) {
        try{
            HttpDelete delete = new HttpDelete(url);
            HttpResponse response = httpClient.execute(delete);
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            return "ClientProtocolException";
        } catch (IOException e) {
            return "IOException";
        }

    }
}
