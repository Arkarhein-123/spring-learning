package usingSet;

import java.util.*;

public class JukeBox {
    public static void main(String[] args) {
        go();
    }

    public static void go(){
        List<SongV3> songList = MockMoreSongs.getSongs();
        System.out.println(songList);

        songList.sort((a,b) -> a.getTitle().compareTo(b.getTitle()));
        System.out.println(songList);

        Set<SongV3> songSet = new TreeSet<>((o1, o2) -> o1.getBpm() - o2.getBpm());
        songSet.addAll(songList);
        System.out.println(songSet);
    }
}
