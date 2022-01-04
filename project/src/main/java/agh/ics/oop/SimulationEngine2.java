package agh.ics.oop;

import java.util.*;

public class SimulationEngine2 implements IEngine{
    private final IWorldMap engineMap;
    @Override
    public IWorldMap getEngineMap() {
        return engineMap;
    }
    //co z animals?
    public SimulationEngine2(int height,int width,int moveEnergy,int plantEnergy,double jungleRatio,int StartEnergy,int animalCount){
        this.engineMap= new InfinityMap(height,width,jungleRatio,StartEnergy,plantEnergy);
        int[] Genes = new int[30];
        for (int i=0;i<animalCount;i++){
            for (int j=0;j<30;j++){
                Genes[j]=(int) (Math.random()*(7));
            }
            Arrays.sort(Genes);
            Vector2d position = new Vector2d((int) (Math.random()*(this.engineMap.getWidth())),(int) (Math.random()*(this.engineMap.getHeight())));
            while (this.engineMap.isOccupied(position)){
                position = new Vector2d((int) (Math.random()*(this.engineMap.getHeight())),(int) (Math.random()*(this.engineMap.getHeight())));
            }
            Animal newKind = new Animal(this.engineMap,position,Genes,StartEnergy,moveEnergy);
            this.engineMap.placeAnimal(newKind);
        }
    }
    @Override
    public void run() {//sawanna nie działa
        //for iles ruchu + usuwanie
        Map<Vector2d, List<Animal>> newAnimals = new HashMap<>();//nowa hash mapa obbecnie pusta
        for (List<Animal> list:engineMap.getAnimals().values()){
            List<Animal> toUpdate1 = new ArrayList<>();
            for (Animal animal:list){
                if (animal.isDead()){//usuwa martwe
                    if (this.engineMap.getGraveyard().get(animal.getPosition())!=null){
                        this.engineMap.getGraveyard().get(animal.getPosition()).add(animal);
                    }
                    else{
                        List<Animal> toAdd = new ArrayList<>();
                        toAdd.add(animal);
                        this.engineMap.getGraveyard().put(animal.getPosition(),toAdd);
                    }
                    this.engineMap.deleteDead(animal);
                }
                else{//robi ruch
                    Vector2d old = animal.getPosition();
                    animal.move();
                    animal.positionChanged(old, animal.getPosition());
                    toUpdate1.add(animal);
                }
            }
            for (Animal update : toUpdate1) {
                if (newAnimals.get(update.getPosition()) != null) {
                    newAnimals.get(update.getPosition()).add(update);
                } else {
                    List<Animal> toAdd = new ArrayList<>();
                    toAdd.add(update);
                    newAnimals.put(update.getPosition(), toAdd);
                }
            }
            this.engineMap.setAnimals(newAnimals);
        }
        for (List<Animal> list:engineMap.getAnimals().values()){
            engineMap.eating(list);
        }
        int k=0;
        for (List<Animal> list:engineMap.getAnimals().values()){
            engineMap.reproducing(list);
            k+=list.size();
        }
        //System.out.print(k);
        //System.out.print(" ");
        //rozmnazanie
        //sadzenie
        //System.out.println(engineMap.getJungle());
        engineMap.spawnGrass();//nie widzi ze dzungla jest zapelninoa
        //System.out.println(engineMap);
    }

}
