package agh.ics.oop;

public class Cage extends AbstractWorldMap{
    int grassWidth;
    int grassHeight;
    public Cage(int height, int width, double jungleRatio, int startEnergy, int plantEnergy){
        this.height=height;
        this.plantEnergy=plantEnergy;
        this.grassHeight=height;
        this.grassWidth=width;
        this.width=width;
        this.startEnergy=startEnergy;
        int x1 = (this.width-((int) (width*jungleRatio)))/2;
        int y1 = (this.height-((int) (height*jungleRatio)))/2;
        this.jungleLower=new Vector2d(x1,y1);
        this.jungeleUpper=new Vector2d(this.width- x1-1,this.height-y1-1);
        this.sawanna=0;
        this.jungle=0;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (grassWidth<=position.x){
            return false;
        }
        else if (position.x<0){
            return false;
        }
        else if (this.grassHeight<=position.y){
            return false;
        }
        else return position.y >= 0;
    }
    @Override
    public Vector2d forceInBounds(Vector2d position) {
        return position;
    }
    @Override
    public Vector2d borderDown() {
        return null;
    }
    @Override
    public Vector2d borderUP() {
        return null;
    }
}
