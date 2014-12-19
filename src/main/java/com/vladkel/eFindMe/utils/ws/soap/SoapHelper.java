package com.vladkel.eFindMe.utils.ws.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.xml.ws.BindingProvider;

public class SoapHelper {

	private static final Logger logger = LoggerFactory.getLogger(SoapHelper.class);
	
	protected int timeOut = 10000;

    protected String user;

    protected String pwd;
    
    protected String url;
    
    protected Object servicePort;
	
	public SoapHelper(){
		
	}
	
	public SoapHelper(Object servicePort, String url){
		this.servicePort = servicePort;
		this.url = url;
	}
	
	public SoapHelper(int timeOut, Object servicePort, String url){
		this.timeOut = timeOut;
		this.servicePort = servicePort;
		this.url = url;
	}
	
	public SoapHelper(String user, String pwd, int timeOut, Object servicePort, String url){
		this.user = user;
        this.pwd = pwd;
        this.timeOut = timeOut;
        this.servicePort = servicePort;
		this.url = url;
	}
	
	public SoapHelper(String user, String pwd, Object servicePort, String url){
		this.user = user;
        this.pwd = pwd;
        this.servicePort = servicePort;
		this.url = url;
	}
	
	public void setContext() {

		Map<String, Object> requestContext = ((BindingProvider) servicePort).getRequestContext();

		if (user != null) {
			requestContext.put(BindingProvider.USERNAME_PROPERTY, user);
		}
		if (pwd != null) {
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);
		}
		if (url != null) {
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		}
		if(timeOut > 0){
			// internal
			requestContext.put("com.sun.xml.internal.ws.connect.timeout", timeOut);
			requestContext.put("com.sun.xml.internal.ws.request.timeout", timeOut);
			
			// non-internal
			requestContext.put("com.sun.xml.ws.connect.timeout", timeOut);
			requestContext.put("com.sun.xml.ws.request.timeout", timeOut);
		}
		
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
    
    public void setServicePort(Object servicePort) {
        this.servicePort = servicePort;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    
}
