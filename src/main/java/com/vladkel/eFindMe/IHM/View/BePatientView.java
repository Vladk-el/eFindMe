package com.vladkel.eFindMe.IHM.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by eliott on 09/02/15.
 */
public class BePatientView extends JFrame {

    // http://www.golfllnnews.com/wp-content/uploads/2013/04/Pictures-loading.jpg
    // http://files.dametenebra.com/eFindMe/Loading-Gif-5.gif
    // https://www.shipco.com/include/images/shipco/loading1.gif

    public BePatientView() {
        Image image = null;
        try {
            URL url = new URL("http://files.dametenebra.com/eFindMe/Loading-Gif-5.gif");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Veuillez patienter durant la recherche de l'utilisateur");
        setSize(300, 300);
        setLocationRelativeTo(null);
        JLabel label = new JLabel(new ImageIcon(image));
        add(label);
        setVisible(true);
    }
}