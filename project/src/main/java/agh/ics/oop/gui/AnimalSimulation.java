package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IEngine;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;

import javax.swing.*;
import java.awt.*;

public class AnimalSimulation extends JPanel  {

    public MapSimulation2 simulation2;
    public IEngine engine;
    public int locationX,locationY;

    public AnimalSimulation(MapSimulation2 simulation2, IEngine engine,int locationY,int locationX){
        this.simulation2=simulation2;
        this.engine=engine;
        this.locationX=locationX;
        this.locationY=locationY;
        this.setLayout(new FlowLayout());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize((int) (simulation2.frame.getWidth()*0.3),simulation2.frame.getHeight()-38);
        this.setLocation(locationX, locationY);
        IWorldMap map = engine.getEngineMap();
        int scaleX=Math.round(this.getWidth()/map.getWidth());//map.getHeight();
        int scaleY=Math.round(this.getHeight()/map.getHeight());//map.getWidth();
        Graphics2D g2d = (Graphics2D) g.create();
        for(int x=0;x<map.getWidth();x++){
            for (int y=0;y< map.getHeight();y++){
                if (map.isJungle(new Vector2d(x,y))){
                    g2d.setColor(new Color(42, 117, 4, 255));
                    g2d.fillRect(x*scaleX,y*scaleY,scaleX,scaleY);
                }
                else {
                    g2d.setColor(new Color(155, 153, 10, 255));
                    g2d.fillRect(x*scaleX,y*scaleY,scaleX,scaleY);
                }
                if (map.isOccupied(new Vector2d(x,y))){
                    //g2d.fillOval(tab[i], tab[i+1], 30, 30);
                    if (map.objectAt(new Vector2d(x,y)) instanceof Animal){
                        g2d.setColor(new Color(182, 102, 16));
                        g2d.fillOval(x*scaleX,y*scaleY,scaleX,scaleY);
                    }
                    else{
                        g2d.setColor(new Color(108, 44, 155));
                        g2d.fillOval(x*scaleX,y*scaleY,scaleX,scaleY);
                    }
                }
            }
        }
        g.setColor(Color.BLACK);
    }
}
