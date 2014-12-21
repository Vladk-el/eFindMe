package com.vladkel.eFindMe.bing.results;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Results {

	private Result[] results;

	public List<Result> getResultsAsList() {
		return new ArrayList<Result>(Arrays.asList(results));
	}
	
	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}
}
