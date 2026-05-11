package mountainSorting;

import java.util.ArrayList;
import java.util.List;

public class SortMountains {
    static void main() {
        go();
    }

    public static void go(){
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(new Mountain("Longs", 14255));
        mountains.add(new Mountain("Elbert", 14433));
        mountains.add(new Mountain("Maroon", 14156));
        mountains.add(new Mountain("Castle", 14265));
        System.out.println("as entered:\n" + mountains);


        mountains.sort((a, b) ->a.getName().compareTo(b.getName()) );
        System.out.println(mountains);

        mountains.sort((a,b)->String.valueOf(a.getHeight()).compareTo(String.valueOf(b.getHeight())));
        System.out.println(mountains);
    }
}
