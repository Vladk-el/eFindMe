package com.vladkel.eFindMe.xml.parsing.user;

import java.util.Map;

import com.vladkel.eFindMe.search.engine.model.User;
import com.vladkel.eFindMe.search.engine.xml.get.GetUsers;

public class GetUsersIT {

	public static void main(String [] args){
		
		Map<String, User> users = new GetUsers().getUsers();
	}
}
