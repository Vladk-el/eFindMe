package com.vladkel.eFindMe.bing.search;

import java.util.List;

import com.vladkel.eFindMe.bing.results.Result;


public class BingSearchIT {

	public static void main(String [] args){
		
		BingSearch bs = new BingSearch("Da Costa Oliveira Diego");
		
		List<Result> results = bs.getResults();
		
		for(Result r : results){
			System.out.println(r);
		}
	}
}
