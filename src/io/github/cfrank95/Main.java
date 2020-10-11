package io.github.cfrank95;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    // Load image file here
    public static String imageFileName = "C:\\Users\\Joe\\Pictures\\Customization\\pbi4.jpeg";

    // Enter URL Image here
    public static URL imageSrc = null;

    // Begin main()
    public static void main(String args[]) {

        // Create JFrame window object
        JFrame window = new JFrame("Pyctography");

        // Window listener (No error output on exit)
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        try {
            // toURI returns pathname, toURL attempts to access it
            imageSrc = ((new File(imageFileName)).toURI()).toURL();
        } catch (MalformedURLException e) {
            System.out.println("Image not located.");
        }


        CypherInterface cypher = new CypherInterface(imageSrc);
        cypher.guiInit();
        window.add("Center", cypher);
        window.pack();
        window.setVisible(true);
    }  // End main()
}