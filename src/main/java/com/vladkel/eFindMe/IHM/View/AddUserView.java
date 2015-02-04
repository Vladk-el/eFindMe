package com.vladkel.eFindMe.IHM.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import com.vladkel.eFindMe.IHM.MainWindow;
import com.vladkel.eFindMe.search.engine.conf.SearchEngineConfs;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;

public class AddUserView extends JFrame{

	private JButton saveUser = new JButton("Enregistrer");

	private JLabel addUserLabel = new JLabel("Ajouter un utilisateur");
	
	private JLabel nameLabel = new JLabel("Nom :");
	private JTextField nameTextField = new JTextField();
	
	private JLabel firstNameLabel = new JLabel("Prénom :");
	private JTextField firstNameTextField = new JTextField();
	
	private JLabel emailLabel = new JLabel("Email :");
	private JTextField emailTextField = new JTextField();
	
	private JLabel addurlsToLookForLabel = new JLabel("Ajouter une url :");
	private JLabel nameUrlLabel = new JLabel("Nom :");
	private JTextField nameUrlTextField = new JTextField();
	
	private JLabel descriptionUrlLabel = new JLabel("Description :");
	private JTextField descriptionUrlTextArea = new JTextField();
	
	private JLabel urlLabel = new JLabel("Url :");
	private JTextField urlTextField = new JTextField();
	
	private JLabel urlsToLookForLabel = new JLabel("Vos urls :");
	
	public DefaultListModel modelUrlsToLookFor = new DefaultListModel();
	private JList listUrlsToLookFor = new JList(modelUrlsToLookFor);
    private JButton addUrl = new JButton("Ajouter");
    private JButton deleteUrl = new JButton("-");
    
    private MainWindow mainWindow;
    private User userToCreate;
	
	public AddUserView(MainWindow mainWindow) {
		setLayout();
		events();
		
		userToCreate = new User();
		this.mainWindow = mainWindow;
		
		this.setBackground(Color.WHITE);
		this.setTitle("Ajouter un utilisateur");
		this.setSize(new Dimension(350,620));
		this.setVisible(true);
	}
	
	private void setLayout()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		addUserLabel.setPreferredSize(new Dimension(300,24));
		addUserLabel.setFont(new Font("Arial", Font.BOLD, 22));	
		
		nameTextField.setPreferredSize(new Dimension(150,20));
		firstNameTextField.setPreferredSize(new Dimension(150,20));
		emailTextField.setPreferredSize(new Dimension(150,20));
		urlTextField.setPreferredSize(new Dimension(150,20));
		nameUrlTextField.setPreferredSize(new Dimension(150,20));
		descriptionUrlTextArea.setPreferredSize(new Dimension(150,20));
		
		addurlsToLookForLabel.setFont(new Font("Arial", Font.BOLD, 16));	
		
		urlsToLookForLabel.setFont(new Font("Arial", Font.BOLD, 16));		
		listUrlsToLookFor.setPreferredSize(new Dimension(300,200));
		   
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.insets = new Insets(15,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(addUserLabel, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(15,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(nameLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(nameTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,0,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(firstNameLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(firstNameTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,0,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(emailLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(emailTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(10,0,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(addurlsToLookForLabel, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 6;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,0,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(nameUrlLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 6;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(nameUrlTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 7;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,0,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(descriptionUrlLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 7;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(descriptionUrlTextArea, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 8;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,0,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(urlLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 8;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.CENTER;
	    this.add(urlTextField, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 9;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(5,5,0,0);
	    gbc.anchor = GridBagConstraints.EAST;
	    this.add(addUrl, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 10;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(10,0,0,0);
	    gbc.anchor = GridBagConstraints.WEST;
	    this.add(urlsToLookForLabel, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 11;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(10,0,0,0);
	    this.add(listUrlsToLookFor, gbc);

	    gbc.gridx = 0;
	    gbc.gridy = 13;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(10,0,0,0);
	    this.add(deleteUrl, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 14;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(10,5,0,0);
	    this.add(saveUser, gbc);
	}
	
	private void events()
	{
		addUrl.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  Url url = new Url();
		    	  
		    	  Integer indice = userToCreate.getUrlsToLookFor().size();
		    	  
		    	  System.out.println("Indice url : " + indice.toString());
		    	  
		    	  url.setId(indice.toString());
		    	  url.setDescription(descriptionUrlLabel.getText());
		    	  url.setName(nameUrlTextField.getText());
		    	  url.setUrl(urlTextField.getText());
		    	  
		    	 userToCreate.getUrlsToLookFor().add(url);
		      }
		    });
		
		saveUser.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){

		    	  Integer indice = new SearchEngineConfs().getUsers().size();
		    	  
		    	  userToCreate.setId(indice.toString());
		    	  userToCreate.setFirstname(firstNameLabel.getText());
		    	  userToCreate.setName(nameTextField.getText());
		    	  userToCreate.setEmail(emailTextField.getText());
		    	  
		    	  User.createUser(userToCreate);
		      }
		    });

		/**
		 * Not implemented yet
		 * 	deleteUrl
		 */
	}
}
