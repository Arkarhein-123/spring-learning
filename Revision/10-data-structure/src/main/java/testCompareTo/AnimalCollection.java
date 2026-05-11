package testCompareTo;

import java.util.ArrayList;
import java.util.List;

public class AnimalCollection {
    static List<Animal> animals = new ArrayList<>();
    public static List<Animal> getAllAnimals(){
        animals.add(new Animal("Kyaw","me"));
        animals.add(new Animal("Htun","me"));
        animals.add(new Animal("Bo Bo","me"));
        return animals;
    }
}
