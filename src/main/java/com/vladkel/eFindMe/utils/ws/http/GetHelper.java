package com.vladkel.eFindMe.utils.ws.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetHelper {

    private static final Logger log = LoggerFactory.getLogger(GetHelper.class);

    protected int timeOut = 5000;

    private String user;

    private String pwd;

    
    public GetHelper() {

    }

    public GetHelper(int timeOut) {
        this.timeOut = timeOut;
    }

    public GetHelper(String user,String pwd, int timeOut) {
        this.user = user;
        this.pwd = pwd;
        this.timeOut = timeOut;
    }

    public GetHelper(String user,String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setAuth(String user,String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    private HttpResponse get(String url, Map<String,String> headers) {

        HttpClient httpClient = getClient();
        HttpGet get = new HttpGet(url);

        try {
        	
            if(user!=null && pwd!=null)
                get.addHeader(new BasicScheme().authenticate(new UsernamePasswordCredentials(user, pwd), get));

            for(String headerName:headers.keySet())
                get.addHeader(headerName, headers.get(headerName));

            HttpResponse response = httpClient.execute(get);
            return response;

        } catch (IOException e) {
            log.error("", e);
        } catch (AuthenticationException e) {
            log.error("", e);
        }

        return null;
    }

    protected HttpClient getClient() {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeOut);
        HttpConnectionParams.setSoTimeout(httpParams, timeOut);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        return new DefaultHttpClient(httpParams);
    }


    public String get(String url, Map<String,String> headers, boolean doLog) {
            return get(url,headers,null,null,null,doLog);
    }

    public String get(String url, Map<String,String> headers, String companyKey, String wsKey, String requestKey) {
        return get(url,headers,companyKey,wsKey,requestKey,true);
    }

    public String get(String url, Map<String,String> headers, String companyKey, String wsKey, String requestKey, boolean doLog) {

        String responseBody = null;

        try {

            HttpResponse response = get(url,headers);
            if(response!=null) {
                int statusCode = response.getStatusLine().getStatusCode();

                if(statusCode==200) {
                    HttpEntity entity = response.getEntity();
                    if(entity != null) {
                        responseBody = EntityUtils.toString(entity, "UTF-8");
                        EntityUtils.consume(entity);
                    }
                }
                else
                    log.error("error on {} status {}",url,statusCode);
                
              //if(doLog)
                // TODO create log

            }


        } catch (Exception e) {
            log.error("", e);
        }

        return responseBody;
    }

    public InputStream getAsStream(String url, Map<String,String> headers, boolean doLog) {
        return getAsStream(url,headers,null,null,null,doLog);
    }

    public InputStream getAsStream(String url, Map<String,String> headers, String companyKey, String wsKey, String requestKey) {
        return getAsStream(url,headers,companyKey,wsKey,requestKey,true);
    }

    public InputStream getAsStream(String url, Map<String,String> headers, String companyKey, String wsKey, String requestKey, boolean doLog) {

        InputStream responseBody = null;

        try {

            HttpResponse response = get(url,headers);
            int statusCode = response.getStatusLine().getStatusCode();

            if(statusCode==200) {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    responseBody = entity.getContent();
                }
            }
            else
                log.error("error on {} status {}",url,statusCode);

            //if(doLog)
                // TODO create log


        } catch (Exception e) {
            log.error("", e);
        }

        return responseBody;
    }



}
