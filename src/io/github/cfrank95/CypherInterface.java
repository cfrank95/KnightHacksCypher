package io.github.cfrank95;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CypherInterface extends JApplet {

    static String imageFileName = "C:\\Users\\Joe\\Pictures\\Customization\\pbi4.jpeg";
    private URL imageSrc;

    public CypherInterface(URL imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void init() {
        try {
            imageSrc = new URL(getCodeBase(), imageFileName);
        } catch (MalformedURLException e) {
        }
        guiInit();
    }

    // Initialize User Interface
    public void guiInit() {
        setLayout(new FlowLayout());
        final ImageCypher cyphImg = new ImageCypher(imageSrc);

        // Create button objects for Encryption/ Decryption
        JButton btnEncrypt = new JButton("Encrypt");
        JButton btnDecrypt = new JButton("Decrypt");

        // JLabel encryptionLabel = new JLabel("Encrypt / Decrypt Selected Image");

        // Event Listeners

        btnEncrypt.addActionListener(e -> {
            cyphImg.cypher();
            cyphImg.repaint();
            // encryptionLabel.setText("Image Encrypted");
        });

        add("Center", cyphImg);

        //Decryption Button
        btnDecrypt.addActionListener(e -> {
            cyphImg.decypher();
            cyphImg.repaint();
            // encryptionLabel.setText("Image Decrypted");
        });

        Dimension cypherSize = cyphImg.getPreferredSize();

        // Add components to the pane
        resize(cypherSize.width, cypherSize.height + 80);
        add("South", btnEncrypt);
        add(btnDecrypt, "South");
    }

}