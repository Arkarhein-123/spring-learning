package comparator;

import java.util.List;

public class SongV2Main {
    static void main() {
        List<SongV2> songV2List = MockSong2.getSongs();
        System.out.println("======= Before Sorting =======");
        System.out.println(songV2List);
        System.out.println("**********************************************");

        System.out.println("======= After Sorting By Artist Name =======");
//        ArtistCompare songCompare = new ArtistCompare(); no need to use this when lambda comes into play
        songV2List.sort((a, b) -> a.getArtist().compareTo(b.getArtist()) );
        System.out.println(songV2List);
        System.out.println("**********************************************");

        System.out.println("======== After Sortng By Title Name ========");
        songV2List.sort((a,b) ->a.getTitle().compareTo(b.getTitle()));
        System.out.println(songV2List);
        System.out.println("**********************************************");

        System.out.println("======== After Sortng By BPM ========");
        songV2List.sort((a,b) -> Integer.toString(a.getBpm()).compareTo(Integer.toString(b.getBpm())));
        System.out.println(songV2List);
        System.out.println("**********************************************");

    }
}
