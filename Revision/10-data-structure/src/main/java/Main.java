import java.util.Collections;
import java.util.List;

public class Main {
    static void main() {
        System.out.println(MockSong.getSongs());
        Collections.sort(MockSong.getSongs());
        System.out.println(MockSong.getSongs());

    }

}
