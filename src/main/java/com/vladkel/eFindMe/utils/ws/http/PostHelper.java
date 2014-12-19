package com.vladkel.eFindMe.utils.ws.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostHelper {

    private static final Logger log = LoggerFactory.getLogger(PostHelper.class);

    protected int timeOut = 5000;

    private String user;

    private String pwd;

    public PostHelper() {

    }

    public PostHelper(int timeOut) {
        this.timeOut = timeOut;
    }

    public PostHelper(String user,String pwd, int timeOut) {
        this.user = user;
        this.pwd = pwd;
        this.timeOut = timeOut;
    }

    public PostHelper(String user,String pwd) {
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


    public String post(String url, String requestBody, Map<String,String> headers) {
        return post(url, requestBody, headers, null, null, null,false);
    }


    public String post(String url, String requestBody,Map<String,String> headers, String companyKey, String wsKey, String requestKey) {
        return post(url,requestBody,headers,companyKey,wsKey,requestKey,true);
    }


    public String post(String url, String requestBody, Map<String,String> headers, String companyKey, String wsKey, String requestKey,  boolean doLog) {

        String responseBody = null;


        HttpClient httpClient = getClient();

        HttpPost post = new HttpPost(url);

        try {

            if(user!=null && pwd!=null)
                    post.addHeader(new BasicScheme().authenticate(new UsernamePasswordCredentials(user, pwd), post));

            post.setEntity(new StringEntity(requestBody));

            for(String headerName:headers.keySet())
                post.addHeader(headerName, headers.get(headerName));

            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();


            if(statusCode==200) {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    responseBody = EntityUtils.toString(entity);
                    EntityUtils.consume(entity);
                }
            }
            else
                log.error("error on {} status {}",url,statusCode);

          //if(doLog)
            // TODO create log
            
        } catch (ClientProtocolException e) {
            log.error("",e);
        } catch (UnsupportedEncodingException e) {
            log.error("",e);
        } catch (IOException e) {
            log.error("",e);
        } catch (AuthenticationException e) {
            log.error("",e);
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;

    }

    protected HttpClient getClient() {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeOut);
        HttpConnectionParams.setSoTimeout(httpParams, timeOut);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        return new DefaultHttpClient(httpParams);
    }

}
