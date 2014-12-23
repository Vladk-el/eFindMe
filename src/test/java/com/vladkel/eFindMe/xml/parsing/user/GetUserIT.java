package com.vladkel.eFindMe.xml.parsing.user;

import com.vladkel.eFindMe.search.engine.model.User;
import com.vladkel.eFindMe.search.engine.xml.get.GetUser;

public class GetUserIT {

	public static void main(String [] args){
		String fullName = "eliott_laversin";

		User user = new GetUser(fullName).getUser();
		
		System.out.println(user);
	}
	
}
