package agh.ics.oop;

class InfinityMap extends AbstractWorldMap{
    int grassWidth;
    int grassHeight;
    public InfinityMap(int height, int width, double jungleRatio, int startEnergy, int plantEnergy){
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
        return true;
    }
    @Override
    public Vector2d forceInBounds(Vector2d position) {
        //polnocny wchod
        if (position.x>width && position.y>height){
            position=new Vector2d(-1,-1);
        }
        //poludniowy wschod
        else if (position.x>width && position.y<0){
            position=new Vector2d(-1,height-1);
        }
        //polnocny zachod
        else if (position.x<0 && position.y>height){
            position =new Vector2d(width-1,-1);
        }
        else if(position.x<0 && position.y<0){
            position = new Vector2d(width-1,height-1);
        }
        //wychodze na wschód
        else if (position.x>width){
            position=new Vector2d(-1, position.y);
        }
        //wychdoze na zachód
        else if (position.x<0){
            position=new Vector2d(width-1, position.y);
        }
        //wychodze na północ
        else if(position.y>height){
            position=new Vector2d(position.x, -1);
        }
        //wychodze na południe
        else if(position.y<0){
            position=new Vector2d(position.x, height-1);
        }
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
