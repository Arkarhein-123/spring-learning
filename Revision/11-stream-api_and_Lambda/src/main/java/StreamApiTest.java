import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTest {
    static void main() {
        List<String> greet = List.of("Hello" , " I am ","your ","father..");
        System.out.println(greet);
//        Stream<String> limit =  greet.stream()
//                .limit(2);
//        System.out.println(limit);
//        List<String> greetBack = limit.collect(Collectors.toList());
//        System.out.println(greetBack);

        List<String> result = greet.stream()
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("Result : " + result);


        List<String> result2 = greet.stream()
                .sorted((s1, s2) ->s1.compareToIgnoreCase(s2))
                .skip(2)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("Result 2 : "+result2);
    }
}
