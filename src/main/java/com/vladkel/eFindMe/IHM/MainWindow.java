package com.vladkel.eFindMe.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.vladkel.eFindMe.IHM.Controller.CurrentUser;
import com.vladkel.eFindMe.IHM.Controller.SearchUserAutoComplete;
import com.vladkel.eFindMe.IHM.View.AddUserView;
import com.vladkel.eFindMe.IHM.model.UrlFindModel;
import com.vladkel.eFindMe.IHM.model.UrlFindTableModel;
import com.vladkel.eFindMe.graph.Graph;
import com.vladkel.eFindMe.graph.parsingxml.GraphXML;
import com.vladkel.eFindMe.search.engine.conf.SearchEngineConfs;
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
	
	private JLabel urlsToLookForLabel = new JLabel("Vos urls :");
	
	private DefaultListModel modelUrlsToLookFor = new DefaultListModel();
	private JList listUrlsToLookFor = new JList(modelUrlsToLookFor);
    private JButton addUrl = new JButton("+");
    private JButton deleteUrl = new JButton("-");

	private JLabel urlsFindLabel = new JLabel("Urls trouvés :");
	private JTable tableUrlsFind = new JTable();
    JScrollPane jspUrlsFind = new JScrollPane(tableUrlsFind);
    
    private JButton update = new JButton("Modifier");
	
	private Graph graph = new Graph();
	
	public MainWindow()
	{
		getCurrentUser();
		
		mainFrame.setTitle("eFindMe");
		mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);	
		Toolkit outil = mainFrame.getToolkit();
		mainFrame.setSize(outil.getScreenSize()); 
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		mainFrame.setVisible(true);
		
		mainFrame.setLayout(new BorderLayout());

		initToolBar();
		initUserDetail();
	    			    
		mainFrame.getContentPane().add(graph, BorderLayout.CENTER);
		mainFrame.setVisible(true);
		events();
	}
	
	private void getCurrentUser()
	{
		// Check the last used user
		CurrentUser.getInstance().setCurrentUser(new SearchEngineConfs().getUsers().get("1"));
	}
	
	public MainWindow GetInstance()
	{
		return this;
	}
	
	private void initUserDetail()
	{
		setUser();
						
		userDetail.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 4,mainFrame.MAXIMIZED_VERT));
		
		userDetail.setLayout(new GridBagLayout());
		userDetail.setBorder(BorderFactory.createLineBorder(Color.black));		
		GridBagConstraints gbc = new GridBagConstraints();
		
		idTextField.setEditable(false);
		idTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
		nameTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
		firstNameTextField.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/10,20));
	
		detailLabel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height / 13));
		detailLabel.setFont(new Font("Arial", Font.BOLD, 22));	
		
		urlsToLookForLabel.setFont(new Font("Arial", Font.BOLD, 14));		
		urlsFindLabel.setFont(new Font("Arial", Font.BOLD, 14));	
		
		listUrlsToLookFor.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,100));
		
	    jspUrlsFind.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/5,Toolkit.getDefaultToolkit().getScreenSize().height/3));
	    
	    update.setBackground(Color.WHITE);
   
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
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(urlsToLookForLabel, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 6;
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
	    gbc.gridy = 8;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(pan, gbc);

	    gbc.gridx = 0;
	    gbc.gridy = 10;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(urlsFindLabel, gbc);
	    	    
	    gbc.gridx = 0;
	    gbc.gridy = 11;
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(jspUrlsFind, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 13;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.anchor = GridBagConstraints.EAST;
	    gbc.insets = new Insets(15,0,0,0);
	    userDetail.add(update, gbc);
	     
		mainFrame.getContentPane().add(userDetail, BorderLayout.WEST);
	}
	
	public void setUser()
	{
		GraphXML.getInstance().getDataXml(Integer.parseInt(CurrentUser.getInstance().getUser().getId()));
  		graph.initGraph();
		
	    SearchUserAutoComplete autoSuggestor = new SearchUserAutoComplete(searchUserTextField, mainFrame, null, Color.WHITE, Color.BLACK, Color.RED, 1f);
        List<String> users = new ArrayList<String>();
        
	    for(Integer i = 0 ; i < new SearchEngineConfs().getUsers().size() ; i++)
	    {
	    	 User u = new SearchEngineConfs().getUsers().get(i.toString());    	 
	    	 users.add(u.getFirstname() + " " + u.getName());
	    }
        
        autoSuggestor.setDictionary(users);
		
		User user = CurrentUser.getInstance().getUser();
		
		idTextField.setText(user.getId());
		nameTextField.setText(user.getName());
		firstNameTextField.setText(user.getFirstname());
		
		List<UrlFindModel> urlsFind = new ArrayList<UrlFindModel>();
		modelUrlsToLookFor.clear();
		
		for(Url url : user.getUrlsToLookFor()) modelUrlsToLookFor.addElement(url);
		
		for(Entry<Integer, Url> value : GraphXML.getInstance().getUrls().entrySet()) {
			
		    Url url = value.getValue();
		    
			UrlFindModel ufm = new UrlFindModel(url.getUrl(),new ArrayList<String>());
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
		    	  AddUserView auv = new AddUserView(GetInstance());
		      }
		    });
		
		searchUserButton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){

		    	  for(Integer i = 0 ; i < new SearchEngineConfs().getUsers().size() ; i++)
		  	      {
		  	    	 User u = new SearchEngineConfs().getUsers().get(i.toString());
		  	    	 
		  	    	 if(searchUserTextField.getText().replaceAll(" ","").equals((u.getFirstname() + " " + u.getName()).replaceAll(" ","")))
		  	    	 {
		  	    		 CurrentUser.getInstance().setCurrentUser(new SearchEngineConfs().getUsers().get(i.toString()));
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
	}
		
	public static void main(String[] args) {
		
		MainWindow mw = new MainWindow();
	}
}