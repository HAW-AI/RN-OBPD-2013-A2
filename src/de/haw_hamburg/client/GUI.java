/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.haw_hamburg.client;

/**
 *
 * @author patrick
 */
public class GUI extends Thread {
    
    private static GUI gui;
    private GUIView guiView;
    
    private GUI() {
        this.guiView = new GUIView();
    }
    
    public static GUI getGUI() {
        if (GUI.gui == null) {
            GUI.gui = new GUI();
        }
        return GUI.gui;
    }
}
