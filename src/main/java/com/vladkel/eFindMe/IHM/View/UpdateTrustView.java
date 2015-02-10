package com.vladkel.eFindMe.IHM.View;

import com.google.gson.Gson;
import com.vladkel.eFindMe.IHM.MainWindow;
import com.vladkel.eFindMe.search.engine.SearchEngine;
import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Trust;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class UpdateTrustView extends JFrame {

	private Url url;
	
    private JLabel displayUrlLabel = new JLabel("");

    private JLabel displayTrustLabel = new JLabel("Modifier la confiance de l'url :");
    private JTextField nameUrlTextField = new JTextField();

    private JButton updateTrust = new JButton("Modifier");
    
    private String[] valueTrust = { "Bad", "Unknown", "Trusted"};
    private JComboBox trustList = new JComboBox(valueTrust);

    public UpdateTrustView(Url url){
    	this.url = url;

    	displayUrlLabel.setText(url.toString());
    	
        setLayout();
        events();

        this.setBackground(Color.WHITE);
        this.setTitle("Modifier url de confiance");
        this.setSize(new Dimension(200,200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setLayout(){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        displayUrlLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(displayUrlLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(displayTrustLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15,5,0,0);
        gbc.anchor = GridBagConstraints.EAST;
        this.add(trustList, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15,5,0,0);
        gbc.anchor = GridBagConstraints.EAST;
        this.add(updateTrust, gbc);
    }

    private void events(){
    	updateTrust.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String trustValue = (String)trustList.getSelectedItem();
				
	    		for(int i = 0 ; i < SearchEngine.getInstance().currentUser.getMatches().size() ; i++)
	    		{
	    			Match match = SearchEngine.getInstance().currentUser.getMatches().get(i);
	    			    			
	    			if(match.getIdLink().equals(url.getId()))
	    			{
	    				switch(trustValue)
	    				{
		    				case "Bad":
		    					match.setTrust(Trust.Bad);
		    					break;
		    					
		    				case "Unknown":
			    				match.setTrust(Trust.Unknown);
			    				break;
		    				
		    				case "Trusted":
		    					match.setTrust(Trust.Trusted);
		    					break;
	    				}
	    				
	    				User u = new User();
	    				u = SearchEngine.getInstance().currentUser;
	    				SearchEngine.getInstance().currentUser.removeSelfXMLFiles();
	    				u.writeSelfXMLFiles();
	    				
	    				MainWindow.getInstance().setUser();
	    			}
	    		}
			}
		});
    }

    private void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
