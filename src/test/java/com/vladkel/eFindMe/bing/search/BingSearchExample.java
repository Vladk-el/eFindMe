package com.vladkel.eFindMe.bing.search;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class BingSearchExample {

	public static void main(String[] args) {

		String accountKey = ":D8r66P1vy8imTCg4WHSAOShXeUqewc2CqZkacJEGWsU";
		byte[] accountKeyBytes = Base64.encodeBase64(accountKey.getBytes());
		String accountKeyEnc = new String(accountKeyBytes);

		String query = "Eliott Laversin";

		HttpClient httpclient = new DefaultHttpClient();
		
		
		
		try {
			String bingURL = "https://api.datamarket.azure.com/Bing/SearchWeb/Web?Query=%27" + 
					 URLEncoder.encode(query, "UTF-8") + 
					 "%27&$format=json";
            HttpGet httpget = new HttpGet(bingURL);
            httpget.setHeader("Authorization", "Basic " + accountKeyEnc);

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");

        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
	}


}