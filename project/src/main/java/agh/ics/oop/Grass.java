package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d position;
    public int getGrassEnergy() {
        return grassEnergy;
    }
    private int grassEnergy;
    public Grass(Vector2d position,int plantEnergy){
        this.position=position;
        this.grassEnergy=plantEnergy;
    }
    @Override
    public Vector2d getPosition() {
        return position;
    }
    public String toString() {
        return "*";
    }
    @Override
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }



}
