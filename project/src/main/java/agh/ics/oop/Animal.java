package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement,Comparable<Animal>{
    private Vector2d position;//pozycja zwierzecia
    private int direction;//jego kierunek od 1 do 7
    private IWorldMap map;//mapa
    private int[] Genes;//tablica genow od 1 do 7
    private int animalEnergy;//obecna wartość energi
    private double[] Probability = new double[8];//prawdopodobienstwo poruszania sie zwierzecia
    private int moveEnergy;
    /* gettery

     */
    public int getAnimalEnergy() {
        return animalEnergy;
    }
    public void addEnergy(int add){
        animalEnergy=animalEnergy+add;
    }
    @Override
    public Vector2d getPosition() {
        return position;
    }
    public int getMoveEnergy() {
        return moveEnergy;
    }
    public int getDirection(){
        return direction;
    }
    public String toString(){
        return String.valueOf(animalEnergy);
    }
    public int[] getGenes() {
        return Genes;
    }
    /*
    metody
     */
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    @Override
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    //zwraca czy zwierzę umarło
    public boolean isDead(){
        return this.animalEnergy<moveEnergy;
    }
    //wykonanie ruchu w epoce (jeden dzień)
    public void move(){
        this.animalEnergy-=moveEnergy;
        if (this.animalEnergy<0){
            return;
        }
        double x = Math.random();
        //ruch do przodu
        if (x<this.Probability[0]){
            switch (this.direction){
                case 0 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(0,1))))){
                        this.position=this.position.add(new Vector2d(0,1));
                    }
                }
                case 1 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(1,1))))){
                        this.position=this.position.add(new Vector2d(1,1));
                    }
                }
                case 2 ->{
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(1,0))))){
                        this.position=this.position.add(new Vector2d(1,0));
                    }
                }
                case 3 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(1,-1))))){
                        this.position=this.position.add(new Vector2d(1,-1));
                    }
                }
                case 4 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(0,-1))))){
                        this.position=this.position.add(new Vector2d(0,-1));
                    }
                }
                case 5 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(-1,-1))))){
                        this.position=this.position.add(new Vector2d(-1,-1));
                    }
                }
                case 6 ->{
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(-1,0))))){
                        this.position=this.position.add(new Vector2d(-1,0));
                    }
                }
                default -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(-1,1))))){
                        this.position=this.position.add(new Vector2d(-1,1));
                    }
                }
            }
        }
        else if (x<this.Probability[1]){
            this.direction=(this.direction+1)%8;
        }
        else if (x<this.Probability[1]+this.Probability[2]){
            this.direction=(this.direction+2)%8;
        }
        else if (x<this.Probability[1]+this.Probability[2]+this.Probability[3]){
            this.direction=(this.direction+3)%8;
        }
        else if (x<this.Probability[1]+this.Probability[2]+this.Probability[3]+this.Probability[4]){
            switch (this.direction){
                case 0 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(0,-1))))){
                        this.position=this.position.add(new Vector2d(0,-1));
                    }
                }
                case 1 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(-1,-1))))){
                        this.position=this.position.add(new Vector2d(-1,-1));
                    }
                }
                case 2 ->{
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(-1,0))))){
                        this.position=this.position.add(new Vector2d(-1,0));
                    }
                }
                case 3 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(-1,1))))){
                        this.position=this.position.add(new Vector2d(-1,1));
                    }
                }
                case 4 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(0,1))))){
                        this.position=this.position.add(new Vector2d(0,1));
                    }
                }
                case 5 -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(1,1))))){
                        this.position=this.position.add(new Vector2d(1,1));
                    }
                }
                case 6 ->{
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(1,0))))){
                        this.position=this.position.add(new Vector2d(1,0));
                    }
                }
                default -> {
                    if(this.map.canMoveTo(this.map.forceInBounds(this.position.add(new Vector2d(1,-1))))){
                        this.position=this.position.add(new Vector2d(1,-1));
                    }
                }
            }
        }
        else if (x<this.Probability[1]+this.Probability[2]+this.Probability[3]+this.Probability[4]+this.Probability[5]){
            this.direction=(this.direction+5)%8;
        }
        else if (x<this.Probability[1]+this.Probability[2]+this.Probability[3]+this.Probability[4]+this.Probability[5]+this.Probability[6]){
            this.direction=(this.direction+6)%8;
        }
        else{
            this.direction=(this.direction+7)%8;
        }
    }
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition,Vector2d newPosition){
        for (IPositionChangeObserver i : observers){
            i.positionChanged(oldPosition,newPosition);
        }
    }
    public void energyChanged(int energy){
        for (IPositionChangeObserver i : observers){
            i.energyChanged(energy,this);
        }
    }
    //konstruktor zwierzecia
    public Animal(IWorldMap map,Vector2d initalPosition,int[] Genes,int startEnergy,int moveEnergy) {
        this.moveEnergy=moveEnergy;
        this.map=map;
        this.animalEnergy=startEnergy;
        this.position=initalPosition;
        this.Genes=Genes;
        for (int gene : Genes) {
            switch (gene) {
                case 1 -> Probability[1] += 1;
                case 2 -> Probability[2] += 1;
                case 3 -> Probability[3] += 1;
                case 4 -> Probability[4] += 1;
                case 5 -> Probability[5] += 1;
                case 6 -> Probability[6] += 1;
                case 7 -> Probability[7] += 1;
                default -> Probability[0] += 1;
            }
        }
        for (int i=0;i<8;i++){
            Probability[i]= Probability[i] /32;
        }
        double x = Math.random();
        if (x<Probability[0]){
            this.direction=0;
        }
        else if (x<Probability[1]){
            this.direction=1;
        }
        else if (x<Probability[1]+Probability[2]){
            this.direction=2;
        }
        else if (x<Probability[1]+Probability[2]+Probability[3]){
            this.direction=3;
        }
        else if (x<Probability[1]+Probability[2]+Probability[3]+Probability[4]){
            this.direction=4;
        }
        else if (x<Probability[1]+Probability[2]+Probability[3]+Probability[4]+Probability[5]){
            this.direction=5;
        }
        else if (x<Probability[1]+Probability[2]+Probability[3]+Probability[4]+Probability[5]+Probability[6]){
            this.direction=6;
        }
        else if (x<1-Probability[7]){
            this.direction=7;
        }
    }

    //metoda porównywania animali w liście
    @Override
    public int compareTo(Animal o) {
        return -(this.animalEnergy-o.getAnimalEnergy());
    }
}
