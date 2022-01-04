package agh.ics.oop.gui;

import javax.swing.*;

public class Menu {

    public JFrame menuFrame;

    public Menu(){
        menuFrame = new JFrame("Simualator menu");
        menuFrame.setSize(500, 1000);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
        this.start();
    }
    public void start(){
        menuFrame.add(new MenuPanel());
    }
}
