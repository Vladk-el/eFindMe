package com.vladkel.eFindMe.bing.search;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.vladkel.eFindMe.bing.results.BingResult;
import com.vladkel.eFindMe.bing.results.Result;

public class BingSearch {
	
	private static final Logger log = LoggerFactory.getLogger(BingSearch.class);

	private static final String accountKey = ":D8r66P1vy8imTCg4WHSAOShXeUqewc2CqZkacJEGWsU";
	
	private String query;
	
	private HttpClient httpclient;
	
	
	/**
	 * Constructors
	 */
	
	public BingSearch(){
		super();
		setQuery("xbox");
		setHttpclient(new DefaultHttpClient());
	}
	
	public BingSearch(String query){
		super();
		setQuery(query);
		setHttpclient(new DefaultHttpClient());
	}
	
	
	/**
	 * Methods
	 */
	
	public List<Result> getResults(){
		
		byte[] accountKeyBytes = Base64.encodeBase64(accountKey.getBytes());
		String accountKeyEnc = new String(accountKeyBytes);
		
		try {
			String bingURL = "https://api.datamarket.azure.com/Bing/SearchWeb/Web?Query=%27" + 
					 URLEncoder.encode(query, "UTF-8") + 
					 "%27&$format=json";
            HttpGet httpget = new HttpGet(bingURL);
            httpget.setHeader("Authorization", "Basic " + accountKeyEnc);

            log.info("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            log.info("----------------------------------------");
            log.info(responseBody);
            log.info("----------------------------------------");
            
            BingResult br = new Gson().fromJson(responseBody, BingResult.class);
            
            log.info("BingResult => " + br);
            
            if(br != null){
            	httpclient.getConnectionManager().shutdown();
            	return br.getD().getResultsAsList();
            }

        } catch (ClientProtocolException e) {
        	log.error("ClientProtocolException : ", e);
		} catch (IOException e) {
			log.error("IOException : ", e);
		} finally {
            httpclient.getConnectionManager().shutdown();
        }
		
		return null;
	}

	
	
	
	
	/**
	 * Getters and setters
	 */
	

	public static String getAccountkey() {
		return accountKey;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public HttpClient getHttpclient() {
		return httpclient;
	}


	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
}
