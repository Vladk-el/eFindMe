package com.vladkel.eFindMe.facebook;

import java.util.ArrayList;
import java.util.List;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.User;

public class FacebookIT {

	public static void main(String[] args){
		
		Facebook facebook = new FacebookFactory().getInstance();
		
		String search = "Touzeau";
		String searchFor = "Olivier Touzeau";
		
		List<User> users = new ArrayList<User>();
		
		try {
			
			ResponseList<User> results = facebook.searchUsers(search);
			for(User u : results){
				User user = facebook.getUser(u.getId());
				System.out.println(user);
				if(u.getName().equalsIgnoreCase(searchFor)){
					System.out.println(searchFor + " founded !!!");
					users.add(user);
				}
			}
			
			for(User u : users){
				System.out.println(u.getId() + " ==> " + u);
			}
			
			
			/*facebook.setOAuthAccessToken(facebook.getOAuthAppAccessToken());
			facebook.postStatusMessage("Hello World from Facebook4J.");*/
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
