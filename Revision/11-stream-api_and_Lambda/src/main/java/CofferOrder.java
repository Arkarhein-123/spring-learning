import java.util.List;
import java.util.stream.Collectors;

public class CofferOrder {
    static void main() {
        List<String> coffees = List.of("Cappuccino",
                "Americano", "Espresso","Cortado", "Mocha",
                "Cappuccino", "Flat White","Latte");

        List<String> coffeesEndingInO = coffees.stream()
                .filter(s -> s.endsWith("o"))
                .collect(Collectors.toList());
        System.out.println("Coffee Ending In O : "+ coffeesEndingInO);
    }
}
