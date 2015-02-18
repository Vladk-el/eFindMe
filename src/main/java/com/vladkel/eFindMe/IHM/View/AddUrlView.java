package com.vladkel.eFindMe.IHM.View;

import com.vladkel.eFindMe.IHM.MainWindow;
import com.vladkel.eFindMe.search.engine.SearchEngine;
import com.vladkel.eFindMe.search.engine.model.Url;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by eliott on 06/02/15.
 */
public class AddUrlView extends JFrame {

	private static final long serialVersionUID = 2371035244213180842L;

	private MainWindow mainWindow;

    private JLabel addurlsToLookForLabel = new JLabel("Ajouter une url :");

    private JLabel nameUrlLabel = new JLabel("Nom :");
    private JTextField nameUrlTextField = new JTextField();

    private JLabel descriptionUrlLabel = new JLabel("Description :");
    private JTextField descriptionUrlTextArea = new JTextField();

    private JLabel urlLabel = new JLabel("Url :");
    private JTextField urlTextField = new JTextField();

    private JButton addUrl = new JButton("Ajouter");


    public AddUrlView(MainWindow mainWindow){
        setLayout();
        events();

        this.mainWindow = mainWindow;

        this.setBackground(Color.WHITE);
        this.setTitle("Ajouter un utilisateur");
        this.setSize(new Dimension(300,250));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setLayout(){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        addurlsToLookForLabel.setFont(new Font("Arial", Font.BOLD, 16));

        urlTextField.setPreferredSize(new Dimension(150,20));
        nameUrlTextField.setPreferredSize(new Dimension(150,20));
        descriptionUrlTextArea.setPreferredSize(new Dimension(150,20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(addurlsToLookForLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(nameUrlLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(nameUrlTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,0,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(descriptionUrlLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(descriptionUrlTextArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,0,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(urlLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,5,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(urlTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,5,0,0);
        gbc.anchor = GridBagConstraints.EAST;
        this.add(addUrl, gbc);

    }

    private void events(){
        addUrl.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){

                if(isReadyToAddUrl()){
                    Url url = new Url();

                    Integer indice = SearchEngine.getInstance().currentUser.getUrlsToLookFor().size();

                    System.out.println("Indice url : " + indice.toString());

                    url.setId(indice.toString());
                    url.setDescription(descriptionUrlTextArea.getText());
                    url.setName(nameUrlTextField.getText());
                    url.setUrl(urlTextField.getText());

                    mainWindow.getModelUrlsToLookFor().addElement(url);
                    close();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Veuillez remplir les champs Nom, Description et Url pour enregistrer une url."
                    );
                }

            }
        });
    }

    private boolean isReadyToAddUrl(){
        return nameUrlTextField.getText().length() > 0 &&
                descriptionUrlLabel.getText().length() > 0 &&
                urlTextField.getText().length() > 0
                ;
    }

    private void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
