package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
    Map<Vector2d, List<Animal>> Animals = new HashMap<>();//Mapa animali
    Map<Vector2d,Grass> Grasses = new HashMap<>();//Mapa roślin (jedna roślina na jednej pozycji)
    Map<Vector2d,List<Animal>> Graveyard = new HashMap<>();//cmentarz zwierząt
    int startEnergy,sawanna,jungle,plantEnergy,height,width;
    Vector2d jungleLower;
    Vector2d jungeleUpper;
    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getJungle() {
        return jungle;
    }
    public Map<Vector2d, List<Animal>> getAnimals() {
        return Animals;
    }
    @Override
    public Map<Vector2d, List<Animal>> getGraveyard() {
        return Graveyard;
    }
    @Override
    public void setAnimals(Map<Vector2d, List<Animal>> animals) {
        Animals = animals;
    }
    @Override
    public boolean isJungle(Vector2d position) {
        return position.precedes(jungeleUpper) && position.follows(jungleLower);
    }
    //dodaje animala do listy animali o tej samej pozycji
    @Override
    public boolean placeAnimal(Animal newAnimal) {
        Animals.computeIfAbsent(newAnimal.getPosition(), k -> new ArrayList<>());//nieinstnieje lista na tej pozycjji
        Animals.get(newAnimal.getPosition()).add(newAnimal);
        newAnimal.addObserver(this);
        if (this.isJungle(newAnimal.getPosition())) {
            jungle += 1;
        }
        else{
            sawanna+=1;
        }
        return true;
    }
    public Object animalAt(Vector2d position){
        return Animals.get(position);
    }
    public Grass grassAt(Vector2d position){
        return Grasses.get(position);
    }
    //sprawdzam czy znajduje się jakiś obiekt na danej pozycji
    @Override
    public Object objectAt(Vector2d position) {
        if (Animals.get(position)!=null){
            //for(Animal animal : Animals.get(position)){//temp
                /*System.out.print("[");
                System.out.print(animal.toString());
                System.out.print(animal.getPosition().toString());
                System.out.println("]");*/
           // }
            Collections.sort(Animals.get(position));
            return Animals.get(position).get(0);
        }
        else if (Grasses.get(position)!=null){
            return Grasses.get(position);
        }
        return null;
    }
    //zwracam wartość typu boolean o okupowanej pozycji
    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position)!=null;
    }
    //rozmnażanie metodą naturalną
    @Override
    public void reproducing(List<Animal> list){
        if (list.size()<=1){
            return;
        }
        List<Animal> toReproduce = new ArrayList<>(); //lista animali o najwiekszej energi
        Animal toReproduceWeaker = null;//slabszy osobnik
        Collections.sort(list);
        toReproduce.add(list.get(0));
        int index = 1;
        if (list.size()>1){
            while (list.size()>index && toReproduce.get(0).getAnimalEnergy()==list.get(index).getAnimalEnergy()){//szukanie animali o najwiekszej energi
                toReproduce.add(list.get(index));
                index+=1;
            }
        }
        if (index<list.size()){
            toReproduceWeaker=list.get(index);//animal z 2 naj energia
        }
        if (toReproduce.get(0).getAnimalEnergy()>=0.5*startEnergy){//warunek pomnozenia pierwszy
            boolean beginSpawn =false;
            Animal lover1=null;
            Animal lover2=null;
            if (toReproduce.size()<2 && Objects.requireNonNull(toReproduceWeaker).getAnimalEnergy()>=0.5*startEnergy){//jeśli największa energia ma jeden animal to łacze go z 2 najwiekszą energią
                lover1 = toReproduce.get(0);
                lover2 = toReproduceWeaker;
                beginSpawn = true;
            }
            else if(toReproduce.size()>=2){//losuje dwa animaly z listy
                lover1 = toReproduce.get((int) (Math.random() * (toReproduce.size())));
                lover2 = toReproduce.get((int) (Math.random() * (toReproduce.size())));
                while (lover2.equals(lover1)){
                    lover2 = toReproduce.get((int) (Math.random() * (toReproduce.size())));
                }
                beginSpawn = true;
            }
            //teraz sie rozmnazaja
            if (beginSpawn && lover1 != null){//jeśli są lovery
                Animal better,worse;
                int lover1Contribution = (int) (0.25 * lover1.getAnimalEnergy());
                int lover2Contribution = (int) (0.25 * lover2.getAnimalEnergy());
                int babyEnergy = lover1Contribution+lover2Contribution+1; //energia dziecka to 1/4 sumy energi rodziców
                double leftChance = lover1Contribution*1.0/(lover1Contribution+lover2Contribution);//>0.5 mówi że lover1 ma większy udział
                if (leftChance > 0.5) {
                    better = lover1;
                    worse = lover2;
                } else {
                    better = lover2;
                    worse = lover1;
                }
                //losujemy strone genotypu silniejszego osobnika
                double r = Math.random();
                int[] babyGenes = new int[30];
                int j;
                if (r<0.5){//została wylosowana lewa strona
                    for (j=0;j<leftChance*30;j++){
                        babyGenes[j]=better.getGenes()[j];
                    }
                    for (j=j;j<30;j++){
                        babyGenes[j]=worse.getGenes()[j];
                    }
                }
                else{
                    for (j=0;j<30-leftChance*30;j++){
                        babyGenes[j]=worse.getGenes()[j];
                    }
                    for (j=j;j<30;j++){
                        babyGenes[j]=better.getGenes()[j];
                    }
                }
                lover1.addEnergy(-lover1Contribution);
                lover2.addEnergy(-lover2Contribution);
                Animal baby = new Animal(this,lover1.getPosition(),babyGenes,babyEnergy,lover1.getMoveEnergy());//dziecko sie rodzi
                this.placeAnimal(baby);
                //System.out.println("Narodzilem sie");

            }
        }

    }
    // oraz spożywanie
    @Override
    public void eating(List<Animal> list){
        List<Animal> toEat = new ArrayList<>();
        Collections.sort(list);
        toEat.add(list.get(0));
        int index = 1;//do iteraxji listy
        if (list.size()>1){
            while (index<list.size() && toEat.get(0).getAnimalEnergy() == list.get(index).getAnimalEnergy()) {
                toEat.add(list.get(index));
                index += 1;
            }
        }
        /*jedzenie trawy
         */
        Grass i = grassAt(list.get(0).getPosition());//i jako prawdopodobny grass
        if (i != null) {//czy istniej grass na pozycjach tych animalow?
            int energyAdded = i.getGrassEnergy();
            if (toEat.size() > 1) {//wiecej niz jedno zwierze to...
                energyAdded = (int) (i.getGrassEnergy() / toEat.size());
            }
            for (Animal hungry : toEat) {//animale otrzymuja energie
                hungry.addEnergy(energyAdded);
            }
            this.Grasses.remove(i.getPosition());//trawa zostaje zjedzona
            if (isJungle(i.getPosition())){//odejmuje trawe z pol
                jungle-=1;
            }
            else {
                sawanna+=1;
            }
        }
    }
    //utworzenie trawy w dzungli i sawannie
    @Override
    public Map<Vector2d, Grass> getGrasses() {
        return Grasses;
    }
    @Override
    public void spawnGrass() {
       /*
       sadzenie roślniy w dzungli
        */
        int jungleSize =(jungeleUpper.x+1-jungleLower.x) *(jungeleUpper.y+1-jungleLower.y);
        int Size =this.height*this.width;
        if (this.jungle<jungleSize){//mozna zasadzic rosline
            //int r = (int) (Math.random() * (upper - lower)) + lower;
            int x1 = (int) (Math.random() * (this.jungeleUpper.x+1 - this.jungleLower.x)) + this.jungleLower.x;
            int y1 = (int) (Math.random() * (this.jungeleUpper.y+1 - this.jungleLower.y)) + this.jungleLower.y;
            Vector2d positon = new Vector2d(x1,y1);
            while (this.isOccupied(positon)){
                x1 = (int) (Math.random() * (this.jungeleUpper.x+1 - this.jungleLower.x)) + this.jungleLower.x;
                y1 = (int) (Math.random() * (this.jungeleUpper.y+1- this.jungleLower.y)) + this.jungleLower.y;
                //.out.println(positon);
                positon = new Vector2d(x1,y1);
            }
            jungle+=1;//dodaje rosliny
            Grasses.put(positon,new Grass(positon,plantEnergy));
        }
        /*System.out.print(jungleSize);
        System.out.print(" ");
        System.out.print(sawanna);
        System.out.print(" ");
        System.out.println(Size-jungleSize);*/
        if (this.sawanna<Size-jungleSize){
            int x1 = (int) (Math.random() * (this.width));
            int y1 = (int) (Math.random() * (this.height));
            Vector2d positon = new Vector2d(x1,y1);
            while (this.isOccupied(positon) || this.isJungle(positon)){//czy jest w dzungli oraz czy jest zajeta
                x1 = (int) (Math.random() *  (this.width));
                y1 = (int) (Math.random() *  (this.height));
                positon = new Vector2d(x1,y1);
                //System.out.println(positon);
            }
            sawanna+=1;//dodaje rosliny
            Grasses.put(positon,new Grass(positon,plantEnergy));
        }
    }
    //usuwanie umarłych
    @Override
    public void deleteDead(Animal dead) {
        if (isJungle(dead.getPosition())){
            jungle=jungle-1;
        }
        else {
            sawanna=sawanna-1;
        }
    }
    @Override
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0,0),new Vector2d(width-1,height-1));
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if (this.isJungle(oldPosition) && !this.isJungle(newPosition)){//wychodze z dżungli
            jungle-=1;
            sawanna+=1;
        }
        else if(!this.isJungle(oldPosition) && this.isJungle(newPosition)){//wychodzę z sawanny
            jungle+=1;
            sawanna-=1;

        }
    }
    @Override
    public void energyChanged(int energy, Animal animal) {
        for (Animal i: Animals.get(animal.getPosition())){
            if (animal.equals(i)){
                i.addEnergy(energy);
            }
        }
    }
}
