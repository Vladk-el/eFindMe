package com.vladkel.eFindMe.bing.results;

public class Result {

	private Metadata __metadata;
	
	private String ID;
	
	private String Title;
	
	private String Description;
	
	private String DisplayUrl;
	
	private String Url;

	public Metadata get__metadata() {
		return __metadata;
	}

	public void set__metadata(Metadata __metadata) {
		this.__metadata = __metadata;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDisplayUrl() {
		return DisplayUrl;
	}

	public void setDisplayUrl(String displayUrl) {
		DisplayUrl = displayUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	public String toString(){
		return "[" + this.ID + " : " + this.Title + "]";
	}
}
