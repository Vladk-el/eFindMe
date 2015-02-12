package com.vladkel.eFindMe.IHM.View;

import com.vladkel.eFindMe.IHM.MainWindow;
import com.vladkel.eFindMe.search.engine.SearchEngine;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;


public class BePatientView extends JFrame {

    // http://www.golfllnnews.com/wp-content/uploads/2013/04/Pictures-loading.jpg
    // http://files.dametenebra.com/eFindMe/Loading-Gif-5.gif
    // https://www.shipco.com/include/images/shipco/loading1.gif

    public BePatientView() {
        URL url = null;
        try {
            url = new URL("http://files.dametenebra.com/eFindMe/Loading-Gif-5.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Veuillez patienter durant la recherche de l'utilisateur");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setAlwaysOnTop (true);
        setUndecorated(true);
        JLabel label = new JLabel(new ImageIcon(url), JLabel.CENTER);
        /*ImageIcon image = new ImageIcon(getClass().getResource("/img/load.gif"));
        JLabel label = new JLabel(image, JLabel.CENTER);*/
        add(label);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        
        final BePatientView self = this;

        Thread t = new Thread(new Runnable() {
            @Override public void run() {
                SearchEngine.getInstance().search(SearchEngine.getInstance().currentUser.getId());
                SearchEngine.getInstance().currentUser.writeSelfXMLFiles();
                SearchEngine.getInstance().updateConf();
                MainWindow.getInstance().setUser();
                self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
            }
        });
        t.start();

        /*SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                SearchEngine.getInstance().search(SearchEngine.getInstance().currentUser.getId());
                SearchEngine.getInstance().currentUser.writeSelfXMLFiles();
                SearchEngine.getInstance().updateConf();
                MainWindow.getInstance().setUser();
                self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
            }
        }) ;*/
		
        
        // http://stackoverflow.com/questions/11648696/why-gif-animation-doesnt-animate-when-using-it-in-paintcomponent
    }
}