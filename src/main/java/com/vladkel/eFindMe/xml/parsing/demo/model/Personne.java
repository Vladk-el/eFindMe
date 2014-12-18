package com.vladkel.eFindMe.xml.parsing.demo.model;

public class Personne {
	
	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	
	public Personne(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void showYourself(){
		System.out.println(this.getId());
		System.out.println("\t" + this.getNom());
		System.out.println("\t" + this.getPrenom());
		System.out.println("\t" + this.getAdresse());
	}
	
}
