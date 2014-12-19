package com.vladkel.eFindMe.utils.ws.https;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class PostHelper extends com.vladkel.eFindMe.utils.ws.http.PostHelper{


    public PostHelper() {
        super();
    }


    public PostHelper(int timeOut) {
        super(timeOut);
    }

    @Override
    protected HttpClient getClient() {

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeOut);
        HttpConnectionParams.setSoTimeout(httpParams, timeOut);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        HttpClient client = new DefaultHttpClient(httpParams);
        client = HttpsClientWrapper.wrapClient(client);
        return client;
    }
}
