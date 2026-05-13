package mapTest;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static void main() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Arkar",1000);
        scores.put("Hein",400);
        scores.put("Orion",4000);

        System.out.println(scores);
        System.out.println("Score By Hein :"+scores.get("Hein"));
    }
}
