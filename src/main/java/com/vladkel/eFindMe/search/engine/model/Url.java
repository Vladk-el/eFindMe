package com.vladkel.eFindMe.search.engine.model;

public class Url {

	private String id;
	
	private String name;
		
	private String description;
	
	private String url;

    private Trust trust;
		
	public Url(){
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public Trust getTrust() {
        return trust;
    }

    public void setTrust(Trust trust) {
        this.trust = trust;
    }

    public void setTrust(String trust) {
        if(trust.equalsIgnoreCase("Trusted"))
            this.trust = Trust.Trusted;
        else if(trust.equalsIgnoreCase("Bad"))
            this.trust = Trust.Bad;
        else
            this.trust = Trust.Unknown;
    }
	
	public String toString() {
		return url;
	}
}
