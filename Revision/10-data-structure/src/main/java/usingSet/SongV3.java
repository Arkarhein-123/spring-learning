package usingSet;

import java.util.Objects;

public class SongV3 {
    private String title;
    private String artist;
    private int bpm;

    public SongV3(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getBpm() {
        return bpm;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        SongV3 songV3 = (SongV3) o;
        return title.equals(songV3.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}

