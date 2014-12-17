package com.vladkel.eFindMe.facebook;

import java.util.ArrayList;
import java.util.List;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.User;

public class FacebookIT {
	
	public static Facebook facebook = new FacebookFactory().getInstance();
	
	
	public static void main(String[] args){
		
		//searchUsers();
		
		//getNews();
		
		getUser();
		
	}

	public static void searchUsers(){
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
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(User u : users){
			System.out.println(u.getId() + " ==> " + u);
		}
	}
	
	public static void getNews(){
		
		try {
			ResponseList<Post> feed = facebook.getHome();
			
			for(Post post : feed){
				System.out.println("From : " + post.getFrom().getName() + "(" + post.getFrom().getId() + ")");
				System.out.println(post.getName());
				System.out.println(post.getDescription());
				System.out.println();
			}
				
				
				
				
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getUser(){
		
		// Caro ==> 10205571992638526
		try {
			
			String caro = "10205571992638526";
			
			User user = facebook.getUser(caro);
			
			System.out.println(user);
			
			
			//ResponseList<Post> posts = facebook.getFeed("187446750783");
			
			
			//ResponseList<Post> posts = facebook.getPosts("10205571992638526");
			
			//System.out.println(posts);
			
			/*for(Post post : posts){
				System.out.println("From : " + post.getFrom().getName() + "(" + post.getFrom().getId() + ")");
				System.out.println(post.getName());
				System.out.println(post.getDescription());
				System.out.println();
			}*/
							
				
				
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
