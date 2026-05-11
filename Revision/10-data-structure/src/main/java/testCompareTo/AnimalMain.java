package testCompareTo;

import java.util.Collections;
import java.util.List;

public class AnimalMain {
    static void main() {
        List<Animal> animals = AnimalCollection.getAllAnimals();
        System.out.println(animals);
        Collections.sort(animals);
        System.out.println(animals);
    }
}
