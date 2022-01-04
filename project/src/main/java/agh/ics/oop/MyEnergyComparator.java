package agh.ics.oop;

import java.util.Comparator;

class MyEnergyComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getAnimalEnergy()- o2.getAnimalEnergy();
    }
}
