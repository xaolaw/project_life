package agh.ics.oop.gui;

import agh.ics.oop.IEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MapSimulation2 implements ActionListener, KeyListener {

    public IEngine engine,engine2;
    public Timer timer;
    public JFrame frame;
    public JFrame frame1;
    public JFrame frame2;
    public JPanel main;//skupia rożne elementy symulacji
    public JPanel main2;//skupia rożne elementy symulacji
    public JPanel MAIN;//skupia rożne elementy symulacji
    boolean running_r=false;
    boolean running_l=false;
    public Button fileButton;

    public AnimalSimulation animalSimulation,animalSimulation2;
    public DiagramLeft diagram;
    public DiagramRight diagram2;

    public MapSimulation2(IEngine engine,IEngine engine2){
        this.engine=engine;
        this.engine2=engine2;

        frame = new JFrame("Simulation");
        frame.setSize(2000,1000);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);// spacja pauza

        //mapa infinity
        this.animalSimulation=new AnimalSimulation(this,engine,0,400);
        this.diagram=new DiagramLeft(this,engine,0,0);;
        /*fileButton = new Button("Copy to file");
        fileButton.setLocation(0,0);
        fileButton.setVisible(true);
        JPanel toAdd = new JPanel();
        toAdd.add(fileButton);
        toAdd.setLocation(0,800);*/

        //mapa cage
        this.animalSimulation2 = new AnimalSimulation(this,engine2,0,1400);
        this.diagram2=new DiagramRight(this,engine2,1000,0);


        main=new JPanel();
        main.add(diagram);
        main.add(animalSimulation);
        main.add(diagram2);
        main.add(animalSimulation2);
        frame.add(main);
        startSimulation();

    }
    public void startSimulation(){
        running_r=true;
        running_l=true;
        timer = new Timer(1000,this);//1 sekundy refreshu
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        main.repaint();
        if (running_r){
            engine2.run();
        }
        if (running_l){
            engine.run();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            running_r = !running_r;
        }
        else if (e.getKeyCode() == KeyEvent.VK_L) {
            running_l = !running_l;
        }
        if (e.getKeyCode() == KeyEvent.VK_1 && !running_l){
            File csvFile = new File("src/main/resources/files/MapLeft.csv");
            PrintWriter out = null;
            try {
                out = new PrintWriter(csvFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ArrayList<Integer> animals = diagram.fileAnimals;
            ArrayList<Integer> grasses = diagram.fileGrass;
            if (out!=null){
                out.printf("%s\n","Map Left");
                out.printf("%s; %s; %s\n","era","animals","grasses");
                for (int i=0;i<animals.size();i++){
                    out.printf("%d; %d; %d\n", i,animals.get(i),grasses.get(i));
                }
                out.close();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_2 && !running_r){
            File csvFile = new File("src/main/resources/files/MapRight.csv");
            PrintWriter out = null;
            try {
                out = new PrintWriter(csvFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ArrayList<Integer> animals = diagram2.fileAnimals;
            ArrayList<Integer> grasses = diagram2.fileGrass;
            if (out!=null){
                out.printf("%s\n","Map Right");
                out.printf("%s; %s; %s\n","era","animals","grasses");
                for (int i=0;i<animals.size();i++){
                    out.printf("%d; %d; %d\n", i,animals.get(i),grasses.get(i));
                }
                out.close();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
