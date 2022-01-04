package agh.ics.oop.gui;


import agh.ics.oop.Animal;
import agh.ics.oop.IEngine;
import agh.ics.oop.IWorldMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DiagramLeft extends JPanel {

    int days=-1;//przebyte dni
    int daysPassed=0;
    int firtsAnimalCount=0;
    ArrayList<Integer> animals = new ArrayList<>();
    ArrayList<Integer> grasses = new ArrayList<>();
    ArrayList<Integer> fileAnimals = new ArrayList<>();
    ArrayList<Integer> fileGrass = new ArrayList<>();

    public MapSimulation2 simulation2;
    public IEngine engine;
    public IWorldMap map;
    public int locationX;
    public int locationY;

    public DiagramLeft(MapSimulation2 simulation2, IEngine engine,int locationX,int locationY){
        this.locationX=locationX;
        this.locationY=locationY;
        this.simulation2=simulation2;
        this.engine=engine;
        this.map= engine.getEngineMap();
        this.setLayout(new FlowLayout());
        int animalCount=0;
        for (List<Animal> list:engine.getEngineMap().getAnimals().values()){
            animalCount+=list.size();
        }
        this.firtsAnimalCount=animalCount;

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize((int) (simulation2.frame.getWidth() * 0.2), simulation2.frame.getHeight() - 38);
        this.setLocation(locationX, locationY);
        //wykres
        g.drawLine(10,38,10,this.getHeight()/2);
        g.drawLine(10,this.getHeight()/2,this.getWidth()-10,this.getHeight()/2);
        g.drawString("days: " + days ,10,20);

        if (simulation2.running_l){
            days+=1;
            int animalCount=0;
            for (List<Animal> list:engine.getEngineMap().getAnimals().values()){
                animalCount+=list.size();
            }
            if (animals.size()%100==0){
                animals.clear();
                grasses.clear();
                daysPassed=days;
            }
            fileAnimals.add(animalCount);
            animals.add(animalCount);
            int grassCount=engine.getEngineMap().getGrasses().size();
            grasses.add(grassCount);
            fileGrass.add(grassCount);
        }
        int poleG = engine.getEngineMap().getHeight()*engine.getEngineMap().getWidth();
        int heiight =(this.getHeight()/2)-2;
        for (int j=0;j<animals.size();j++){
            g.setColor(new Color(182, 102, 16));
            int y = (int) (heiight-(0.25*(animals.get(j)*heiight/firtsAnimalCount)));//heiight-(scale*animals.get(j));
            g.fillOval(10+(j*5),(y),5,5);
            //roslina
            g.setColor(new Color(108, 44, 155));
            int yG = (int) (heiight-((grasses.get(j)*heiight/poleG)));

            g.fillOval(10+(j*5),yG,5,5);
        }

        //legenda
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free",Font.BOLD,50));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Legend:",0,this.getHeight()/2+50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString("Jungle",15,this.getHeight()/2+100);
        g.drawString("Sawanna",0,this.getHeight()/2+150);
        g.drawString("Animal",15,this.getHeight()/2+200);
        g.drawString("Plant",15,this.getHeight()/2+250);
        g.drawString("[l] - Pause",15,this.getHeight()/2+300);
        g.drawString("[1] - save to csv in resources/files",15,this.getHeight()/2+350);
        //jungle
        g.setColor(new Color(42, 117, 4, 255));
        g.fillRect(100,this.getHeight()/2+100-25,30,30);
        //sawanna
        g.setColor(new Color(155, 153, 10, 255));
        g.fillRect(110,this.getHeight()/2+150-25,30,30);
        //animal
        g.setColor(new Color(182, 102, 16));
        g.fillOval(100,this.getHeight()/2+200-25,30,30);
        //grass
        g.setColor(new Color(108, 44, 155));
        g.fillOval(100,this.getHeight()/2+250-25,30,30);
    }
}
