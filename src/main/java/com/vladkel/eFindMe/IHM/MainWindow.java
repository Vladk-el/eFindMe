package com.vladkel.eFindMe.IHM;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.vladkel.eFindMe.IHM.Controller.SearchUserAutoComplete;
import com.vladkel.eFindMe.IHM.View.AddUrlView;
import com.vladkel.eFindMe.IHM.View.AddUserView;
import com.vladkel.eFindMe.IHM.View.BePatientView;
import com.vladkel.eFindMe.IHM.model.UrlFindModel;
import com.vladkel.eFindMe.IHM.model.UrlFindTableModel;
import com.vladkel.eFindMe.IHM.model.UserFindTableModel;
import com.vladkel.eFindMe.graph.Graph;
import com.vladkel.eFindMe.graph.parsingxml.GraphXML;
import com.vladkel.eFindMe.search.engine.SearchEngine;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;

public class MainWindow 
{
	private JFrame mainFrame = new JFrame();
	private JToolBar toolBar = new JToolBar();
	private JButton addUser = new JButton("Ajouter un utilisateur");
	private JButton consultUsers = new JButton("Consulter les utilisateurs");
    private JTextField searchUserTextField = new JTextField();
	private JButton searchUserButton = new JButton("Go");
	
	private JPanel userDetail = new JPanel();

	private JLabel detailLabel = new JLabel("Informations sur le client");

	private JLabel idLabel = new JLabel("Indice :");
	private JTextField idTextField = new JTextField();
	
	private JLabel nameLabel = new JLabel("Nom :");
	private JTextField nameTextField = new JTextField();
	
	private JLabel firstNameLabel = new JLabel("Prénom :");
	private JTextField firstNameTextField = new JTextField();

	private JLabel emailLabel = new JLabel("Email :");
	private JTextField emailTextField = new JTextField();
	
	private JLabel urlsToLookForLabel = new JLabel("Vos urls :");
	
	private DefaultListModel<Url> modelUrlsToLookFor = new DefaultListModel<Url>();
	private JList<Url> listUrlsToLookFor = new JList<Url>(modelUrlsToLookFor);
    private JButton addUrl = new JButton("+");
    private JButton deleteUrl = new JButton("-");

	private JLabel urlsFindLabel = new JLabel("Urls trouvés :");
	private JTable tableUrlsFind = new JTable();
    JScrollPane jspUrlsFind = new JScrollPane(tableUrlsFind);

	private JButton search = new JButton("Lancer la recherche");
	private JButton delete = new JButton("Supprimer");
    private JButton update = new JButton("Modifier");
	
	private Graph graph = new Graph();
	
	private	SearchUserAutoComplete autoSuggestor = new SearchUserAutoComplete(searchUserTextField, mainFrame, null, Color.WHITE, Color.BLACK, Color.RED, 1f);
	
	private static MainWindow instance = null;
	public static MainWindow getInstance() {
			
		if(instance == null) {
			instance = new MainWindow();
	    }
			
		return instance;
	}	
	
	// Select User
	private JPanel selectUser = new JPanel();
	private JLabel chooseUser = new JLabel("Selectionner le client");
	private JTable tableUsersFind = new JTable();
    JScrollPane jspUsersFind = new JScrollPane(tableUsersFind);
    private JButton buttonSelectUser = new JButton("Valider");

	@SuppressWarnings("static-access")
	public MainWindow()
	{
		SearchEngine.getInstance().getConfs().loadUsers();

		// getCurrentUser();
		
		mainFrame.setTitle("eFindMe");
		mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);	
		Toolkit outil = mainFrame.getToolkit();
		mainFrame.setSize(outil.getScreenSize()); 
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		mainFrame.setVisible(true);
		
		mainFrame.setLayout(new BorderLayout());

		initToolBar();
	    initSelectUser();
	    			    
		mainFrame.getContentPane().add(graph, BorderLayout.CENTER);
		mainFrame.setVisible(true);
		events();		
	}
	
	@SuppressWarnings("unused")
	private void getCurrentUser()
	{
		// Check the last used user
		SearchEngine.getInstance().currentUser = SearchEngine.getInstance().getConfs().getUsers().get("1");
	}
	
	@SuppressWarnings("static-access")
	private void initSelectUser()
	{
		selectUser.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4,mainFrame.MAXIMIZED_VERT));
		
		selectUser.setLayout(new GridBagLayout());
		selectUser.setBorder(BorderFactory.createLineBorder(Color.black));		
		GridBagConstraints gbc = new GridBagConstraints();
		
		chooseUser.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height / 13));
		chooseUser.setFont(new Font("Arial", Font.BOLD, 22));
		
	    jspUsersFind.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height/2));
				
		List<User> usersFind = new ArrayList<User>();
		
		for(Integer i = 0 ; i < SearchEngine.getInstance().getConfs().getUsers().size() ; i++)
	    {
	    	 User u = SearchEngine.getInstance().getConfs().getUsers().get(i.toString());    	 
	    	 usersFind.add(u);
	    }
		
		tableUsersFind.setModel(new UserFindTableModel(usersFind));

		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    selectUser.add(chooseUser, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(15,0,0,0);
	    selectUser.add(jspUsersFind, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(15,0,0,0);
	    selectUser.add(buttonSelectUser, gbc);
	     
		mainFrame.getContentPane().add(selectUser, BorderLayout.WEST);
	}
	
	@SuppressWarnings("static-access")
	private void initUserDetail()
	{		
		selectUser.setVisible(false);
		setUser();
						
		userDetail.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4,mainFrame.MAXIMIZED_VERT));
		
		userDetail.setLayout(new GridBagLayout());
		userDetail.setBorder(BorderFactory.createLineBorder(Color.black));		
		GridBagConstraints gbc = new GridBagConstraints();
		
		idTextField.setEditable(false);
		idTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
		nameTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
		firstNameTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
		emailTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
	
		detailLabel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height / 13));
		detailLabel.setFont(new Font("Arial", Font.BOLD, 22));	
		
		urlsToLookForLabel.setFont(new Font("Arial", Font.BOLD, 14));		
		urlsFindLabel.setFont(new Font("Arial", Font.BOLD, 14));	
		
		listUrlsToLookFor.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,100));
		
	    jspUrlsFind.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height/3));

	    
	    
		search.setBackground(Color.WHITE);
	    update.setBackground(Color.WHITE);
	    delete.setBackground(Color.WHITE);
   
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    userDetail.add(detailLabel, gbc);
		
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(0,0,0,0);
	    userDetail.add(idLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(0,20,0,0);
	    userDetail.add(idTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(5,0,0,0);
	    userDetail.add(nameLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(5,20,0,0);
	    userDetail.add(nameTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(5,0,0,0);
	    userDetail.add(firstNameLabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 4;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(5,20,0,0);
	    userDetail.add(firstNameTextField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5,0,0,0);
		userDetail.add(emailLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,20,0,0);
		userDetail.add(emailTextField, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 6;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(urlsToLookForLabel, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 7;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(listUrlsToLookFor, gbc);
	    
	    JPanel pan = new JPanel();
	    pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
	    pan.add(addUrl);
	    
	    pan.add(Box.createHorizontalStrut(10)); 
	    pan.add(deleteUrl);

	    gbc.gridx = 0;
	    gbc.gridy = 9;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(pan, gbc);

	    gbc.gridx = 0;
	    gbc.gridy = 11;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(urlsFindLabel, gbc);
	    	    
	    gbc.gridx = 0;
	    gbc.gridy = 12;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(jspUrlsFind, gbc);


		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15,0,0,0);
		userDetail.add(search, gbc);
	    
		gbc.gridx = 1;
	    gbc.gridy = 14;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(update, gbc);

		gbc.gridx = 1;
	    gbc.gridy = 14;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(delete, gbc);
	     
		mainFrame.getContentPane().add(userDetail, BorderLayout.WEST);
	}
	
	public void setUser()
	{
		GraphXML.getInstance().getDataXml(Integer.parseInt(SearchEngine.getInstance().currentUser.getId()));
  		  		
		graph.initGraph();
		
        List<String> users = new ArrayList<String>();
        
	    for(Integer i = 0 ; i < SearchEngine.getInstance().getConfs().getUsers().size() ; i++)
	    {
	    	 User u = SearchEngine.getInstance().getConfs().getUsers().get(i.toString());
	    	 users.add(u.getFirstname() + " " + u.getName());
	    }

        autoSuggestor.setDictionary(users);

		User user = SearchEngine.getInstance().currentUser;
		
		idTextField.setText(user.getId());
		nameTextField.setText(user.getName());
		firstNameTextField.setText(user.getFirstname());
		emailTextField.setText(user.getEmail());
		
		List<UrlFindModel> urlsFind = new ArrayList<UrlFindModel>();
		modelUrlsToLookFor.clear();
		
		for(Url url : user.getUrlsToLookFor()) 
			modelUrlsToLookFor.addElement(url);
		
		for(Url url : SearchEngine.getInstance().currentUser.getUrls().values()){
			UrlFindModel ufm = new UrlFindModel(url.getUrl(), url.getTrust().toString());
		    urlsFind.add(ufm);
		}
			
		tableUrlsFind.setModel(new UrlFindTableModel(urlsFind));
		
  		mainFrame.revalidate();
	}
	
	private void initToolBar()
	{
		toolBar.setFloatable(false);
		
	    addUser.setBackground(Color.WHITE);
	    consultUsers.setBackground(Color.WHITE);
	    
	    toolBar.addSeparator();
	    toolBar.add(addUser);
	    toolBar.addSeparator();
	    toolBar.add(consultUsers);
	    toolBar.add(Box.createHorizontalGlue());
	    
	    searchUserTextField.setMaximumSize(new Dimension(200,20));
	    searchUserTextField.setPreferredSize(new Dimension(200,20));
	    searchUserTextField.setToolTipText("Rechercher un utilisateur");
	    searchUserTextField.setText("Rechercher un utilisateur ...");
	    
	    searchUserButton.setMaximumSize(new Dimension(50,20));
	    searchUserButton.setPreferredSize(new Dimension(50,20));
	            
        toolBar.add(searchUserTextField);
        toolBar.add(searchUserButton);

	    mainFrame.getContentPane().add(toolBar, BorderLayout.NORTH);  
	}
	
	private void events()
	{
		addUser.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  @SuppressWarnings("unused")
				AddUserView auv = new AddUserView();
		      }
		    });
		
		searchUserButton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){

		    	  for(Integer i = 0 ; i <  SearchEngine.getInstance().getConfs().getUsers().size() ; i++)
		  	      {
		  	    	 User u = SearchEngine.getInstance().getConfs().getUsers().get(i.toString());
		  	    	 
		  	    	 if(searchUserTextField.getText().replaceAll(" ","").equals((u.getFirstname() + " " + u.getName()).replaceAll(" ","")))
		  	    	 {
		  	    		 SearchEngine.getInstance().currentUser = SearchEngine.getInstance().getConfs().getUsers().get(i.toString());
		  	    		 setUser();  
		  	    	 }
		  	      }
		      }
		});
		
		searchUserTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	if(searchUserTextField.getText().equals("Rechercher un utilisateur ..."))
		    		searchUserTextField.setText("");  
            }
        });

		final MainWindow self = this;

		addUrl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("addUrl pressed");
				new AddUrlView(self);
			}
		});
		
		buttonSelectUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableUsersFind.convertRowIndexToModel(tableUsersFind.getSelectedRow());
	            UserFindTableModel model = (UserFindTableModel)tableUsersFind.getModel();

	            User user = model.getRowAt(row);
	            
	            SearchEngine.getInstance().currentUser = user;
	            
	            selectUser.setVisible(false);
				userDetail.setVisible(true);
				initUserDetail();
				
				mainFrame.validate();
				mainFrame.repaint();
			}
		});

		deleteUrl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("deleteUrl pressed");

				int index = listUrlsToLookFor.getSelectedIndex();

				if(index != -1){
					String message = "Etes vous sûr de vouloir supprimer l'url '" +
							((Url)modelUrlsToLookFor.getElementAt(index)).getName() +
							"'?";
					String title = "Supprimer une url";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION)
						modelUrlsToLookFor.remove(index);
				}
			}
		});

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(isReadyToUpdate()){

					String message = "Etes vous sûr de vouloir enregistrer les modifications aportées ?";
					String title = "Enregistrer les modifications ?";
					int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

					if(reply == JOptionPane.YES_OPTION){

						SearchEngine.getInstance().currentUser.removeSelfXMLFiles();

						User user = new User();

						user.setId(SearchEngine.getInstance().currentUser.getId());
						user.setName(nameTextField.getText());
						user.setFirstname(firstNameTextField.getText());
						user.setEmail(emailTextField.getText());

						for(int i = 0; i < listUrlsToLookFor.getModel().getSize(); i++){
							user.getUrlsToLookFor().add((Url)modelUrlsToLookFor.getElementAt(i));
							System.out.println(((Url)modelUrlsToLookFor.getElementAt(i)).getName() + " added to current user");
						}

						/**
						 * We don't save the urls, so we need to launch a new search
						 */

						user.writeSelfXMLFiles();
						SearchEngine.getInstance().updateConf();
						SearchEngine.getInstance().currentUser = SearchEngine.getInstance().getConfs().getUsers().get(user.getId());
						initUserDetail();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Veuillez remplir au moins les champs Nom et Prénom pour modifier un utilisateur."
					);
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String message = "Etes vous sûr de vouloir supprimer l'utilisateur ?";
				String title = "Supprimer l'utilisateur ?";
				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

				if(reply == JOptionPane.YES_OPTION){

					SearchEngine.getInstance().currentUser.removeSelfXMLFiles();

					@SuppressWarnings("static-access")
					User user = new SearchEngine().getInstance().currentUser;

					user.removeSelfXMLFiles();
					SearchEngine.getInstance().updateConf();
					
					showSelectUser();
					
				}
			}
		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchForCurrentUser();
			}
		});
		
		consultUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSelectUser();
			}
		});
	}
	
	public void showSelectUser(){
		selectUser.setVisible(true);
		userDetail.setVisible(false);
	    initSelectUser();

		mainFrame.validate();
		mainFrame.repaint();
	}

	private boolean isReadyToUpdate(){
		return firstNameTextField.getText().length() > 0 &&
				nameTextField.getText().length() > 0
				;
	}

	public void searchForCurrentUser(){

		System.out.println("searchForCurrentUser()");

		try{
			@SuppressWarnings("unused")
			BePatientView bePatient = new BePatientView();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/*
	 * Getters and Setters
	 */

	public DefaultListModel<Url> getModelUrlsToLookFor(){
		return modelUrlsToLookFor;
	}

}