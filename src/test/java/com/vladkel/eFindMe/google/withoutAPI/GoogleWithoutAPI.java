package com.vladkel.eFindMe.google.withoutAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladkel.eFindMe.utils.ws.http.GetHelper;

public class GoogleWithoutAPI {
	
	private static final Logger log = LoggerFactory.getLogger(GoogleWithoutAPI.class);

	public static void main(String[] args) {

		String urlToRead = "https://www.google.fr/?gws_rd=ssl#q=toto";

		//System.out.println(getHTML(urlToRead));
		
		System.out.println(getHTMLWithGetHelper(urlToRead));
	}
	
	public static String getHTMLWithGetHelper(String urlToRead){
		GetHelper helper = new GetHelper(5000);
		
		log.info("url : " + urlToRead);
		
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("Content-type", "text/html");
		
		String response = helper.get(urlToRead, headers, false);
		
		return response;
	}

	public static String getHTML(String urlToRead) {
		URL url; // The URL to read
		HttpURLConnection conn; // The actual connection to the web page
		BufferedReader rd; // Used to read results from the web page
		String line; // An individual line of the web page HTML
		String result = ""; // A long string containing all the HTML
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
