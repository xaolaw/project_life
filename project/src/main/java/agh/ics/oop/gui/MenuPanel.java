package agh.ics.oop.gui;

import agh.ics.oop.IEngine;
import agh.ics.oop.SimulationEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    //map 1
    public TextField heightText=new TextField(),
            widthText=new TextField(),
            moveEnergyText=new TextField(),
            plantEnergyText=new TextField(),
            startEnergyText=new TextField(),
            jungleRatioText=new TextField(),
            animalCountText=new TextField();
    //map2
    public TextField heightText2=new TextField(),
            widthText2=new TextField(),
            moveEnergyText2=new TextField(),
            plantEnergyText2=new TextField(),
            startEnergyText2=new TextField(),
            jungleRatioText2=new TextField(),
            animalCountText2=new TextField();
    public MenuPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //labely danych dla jednej mapy
        JLabel heightLabel = new JLabel("Insert Height of the map: ");
        JLabel widthLabel = new JLabel("Insert Width of the map: ");
        JLabel moveEnergyLabel = new JLabel("Insert Move Energy of the Animal: ");
        JLabel plantEnergyLabel = new JLabel("Insert Plant energy of the plant: ");
        JLabel jungleRatioLabel = new JLabel("Insert Jungle Ratio of the jungle: ");
        JLabel startEnergyLabel = new JLabel("Insert start energy of the animal: ");
        JLabel animalCountLabel = new JLabel("Insert number of animals: ");
        //pola tekstowe
        //height
        heightText.setText("10");
        heightLabel.setLabelFor(heightText);
        //width
        widthText.setText("10");
        heightLabel.setLabelFor(widthText);
        //moveEnergy
        moveEnergyText.setText("1");
        moveEnergyLabel.setLabelFor(moveEnergyText);
        //plantEnergyText
        plantEnergyText.setText("10");
        plantEnergyLabel.setLabelFor(plantEnergyText);
        //jungleRatioText
        jungleRatioText.setText("0.5");
        jungleRatioLabel.setLabelFor(jungleRatioText);
        //startEnergyText
        startEnergyText.setText("100");
        startEnergyLabel.setLabelFor(startEnergyText);
        //animalCount
        animalCountText.setText("10");
        animalCountLabel.setLabelFor(animalCountText);

        //panele

        //height
        JPanel heightPanel = new JPanel();
        //heightPanel.setLayout(new BoxLayout(heightPanel,BoxLayout.X_AXIS));
        heightPanel.add(heightLabel);
        heightPanel.add(heightText);
        //width
        JPanel wdithPanel = new JPanel();
        wdithPanel.add(widthLabel);
        wdithPanel.add(widthText);
        //moveEnergy
        JPanel moveEnergyPanel = new JPanel();
        moveEnergyPanel.add(moveEnergyLabel);
        moveEnergyPanel.add(moveEnergyText);
        //plantEnergy
        JPanel plantEnergyPanel = new JPanel();
        plantEnergyPanel.add(plantEnergyLabel);
        plantEnergyPanel.add(plantEnergyText);
        //jungleRatio
        JPanel jungleRatioPanel = new JPanel();
        jungleRatioPanel.add(jungleRatioLabel);
        jungleRatioPanel.add(jungleRatioText);
        //startEnergy
        JPanel startEnergyPanel = new JPanel();
        startEnergyPanel.add(startEnergyLabel);
        startEnergyPanel.add(startEnergyText);
        //animalCount
        JPanel animalCountPanel = new JPanel();
        animalCountPanel.add(animalCountLabel);
        animalCountPanel.add(animalCountText);

        //MainPanel

        JLabel map1Label = new JLabel("First map variables");
        JPanel map1Panel = new JPanel();
        map1Panel.add(map1Label);

        //testing
        JCheckBox reproducing = new JCheckBox("Magic reproducing");
        JPanel reproducingPanel = new JPanel();
        reproducingPanel.add(reproducing);

        //second
     //labely danych dla jednej mapy
     JLabel heightLabel2 = new JLabel("Insert Height of the map: ");
     JLabel widthLabel2 = new JLabel("Insert Width of the map: ");
     JLabel moveEnergyLabel2 = new JLabel("Insert Move Energy of the Animal: ");
     JLabel plantEnergyLabel2 = new JLabel("Insert Plant energy of the plant: ");
     JLabel jungleRatioLabel2 = new JLabel("Insert Jungle Ratio of the jungle: ");
     JLabel startEnergyLabel2 = new JLabel("Insert start energy of the animal: ");
     JLabel animalCountLabel2 = new JLabel("Insert number of animals: ");
     //pola tekstowe
     //height
     heightText2.setText("10");
     heightLabel2.setLabelFor(heightText2);
     //width
     widthText2.setText("10");
     widthLabel2.setLabelFor(widthText2);
     //moveEnergy
     moveEnergyText2.setText("1");
     moveEnergyLabel2.setLabelFor(moveEnergyText2);
     //plantEnergyText
     plantEnergyText2.setText("10");
     plantEnergyLabel2.setLabelFor(plantEnergyText2);
     //jungleRatioText
     jungleRatioText2.setText("0.5");
     jungleRatioLabel2.setLabelFor(jungleRatioText2);
     //startEnergyText
     startEnergyText2.setText("100");
     startEnergyLabel2.setLabelFor(startEnergyText2);
     //animalCount
     animalCountText2.setText("10");
     animalCountLabel2.setLabelFor(animalCountText2);

     //panele

     //height
     JPanel heightPanel2 = new JPanel();
     //heightPanel.setLayout(new BoxLayout(heightPanel,BoxLayout.X_AXIS));
     heightPanel2.add(heightLabel2);
     heightPanel2.add(heightText2);

     //width
     JPanel wdithPanel2 = new JPanel();
     wdithPanel2.add(widthLabel2);
     wdithPanel2.add(widthText2);
     //moveEnergy
     JPanel moveEnergyPanel2 = new JPanel();
     moveEnergyPanel2.add(moveEnergyLabel2);
     moveEnergyPanel2.add(moveEnergyText2);
     //plantEnergy
     JPanel plantEnergyPanel2 = new JPanel();
     plantEnergyPanel2.add(plantEnergyLabel2);
     plantEnergyPanel2.add(plantEnergyText2);
     //jungleRatio
     JPanel jungleRatioPanel2 = new JPanel();
     jungleRatioPanel2.add(jungleRatioLabel2);
     jungleRatioPanel2.add(jungleRatioText2);
     //startEnergy
     JPanel startEnergyPanel2 = new JPanel();
     startEnergyPanel2.add(startEnergyLabel2);
     startEnergyPanel2.add(startEnergyText2);
     //animalCount
     JPanel animalCountPanel2 = new JPanel();
     animalCountPanel2.add(animalCountLabel2);
     animalCountPanel2.add(animalCountText2);

     //MainPanel

     JLabel map1Label2 = new JLabel("Second map variables");
     JPanel map1Panel2 = new JPanel();
     map1Panel2.add(map1Label2);

     //testing
     JCheckBox reproducing2 = new JCheckBox("Magic reproducing");
     JPanel reproducingPanel2 = new JPanel();
     reproducingPanel2.add(reproducing2);

     add(map1Panel);
     add(heightPanel);
     add(wdithPanel);
     add(moveEnergyPanel);
     add(plantEnergyPanel);
     add(jungleRatioPanel);
     add(startEnergyPanel);
     add(animalCountPanel);
     add(reproducingPanel);
     add(map1Panel2);
     add(heightPanel2);
     add(wdithPanel2);
     add(moveEnergyPanel2);
     add(plantEnergyPanel2);
     add(jungleRatioPanel2);
     add(startEnergyPanel2);
     add(animalCountPanel2);
     add(reproducingPanel2);


        //submit przycisk
        JButton start = new JButton("Submit");
        start.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        add(buttonPanel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        IEngine engine = new SimulationEngine(
                Integer.parseInt(heightText.getText()),
                Integer.parseInt(widthText.getText()),
                Integer.parseInt(moveEnergyText.getText()),
                Integer.parseInt(plantEnergyText.getText()),
                Double.parseDouble(jungleRatioText.getText()),
                Integer.parseInt(startEnergyText.getText()),
                Integer.parseInt(animalCountText.getText()),
                true

        );
     IEngine engine2 = new SimulationEngine(
             Integer.parseInt(heightText2.getText()),
             Integer.parseInt(widthText2.getText()),
             Integer.parseInt(moveEnergyText2.getText()),
             Integer.parseInt(plantEnergyText2.getText()),
             Double.parseDouble(jungleRatioText2.getText()),
             Integer.parseInt(startEnergyText2.getText()),
             Integer.parseInt(animalCountText2.getText()),
             false
     );
        new MapSimulation2(engine,engine2);
    }
}
