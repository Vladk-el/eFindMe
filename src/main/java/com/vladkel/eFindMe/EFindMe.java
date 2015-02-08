package com.vladkel.eFindMe;

import com.vladkel.eFindMe.IHM.MainWindow;

public class EFindMe {

	/**
	 * # 1 : Ajouter un object de type SearchEngine dans le ocntroleur global de l'application.
	 * 		 Grâce à son objet SearchEngineConfs, il sera capable en temps réel de gérer les utilsateurs.
	 * 		 Tous les objets utilisants les user seront donc connecté à cet objet et actualisés dans le même temps
	 * 
	 * # 2 : Se mettre d'accord sur le comportement du menu "Consulter les utilisateurs"
	 * 		 Page d'accueil ?
	 */
	
	public static void main(String[] args) {

		System.out.println("Hello eFindMe !");

		MainWindow ihm = new MainWindow();
	}
}
