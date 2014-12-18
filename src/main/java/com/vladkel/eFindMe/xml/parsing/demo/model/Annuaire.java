package com.vladkel.eFindMe.xml.parsing.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Annuaire {

	private List<Personne> personnes;
	
	
	public Annuaire(){
		super();
		setPersonnes(new ArrayList<Personne>());
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}
	
	public void showYourself(){
		for(Personne p : personnes){
			p.showYourself();
		}
	}
}
