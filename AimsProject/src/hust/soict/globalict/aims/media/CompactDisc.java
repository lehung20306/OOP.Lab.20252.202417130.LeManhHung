package hust.soict.globalict.aims.media;
import java.util.List;
import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    public CompactDisc(String title, String category, float cost, String director, int length, String artist,
            List<Track> tracks) {
        super(title, category, cost, director, length);
        this.artist = artist;
        this.tracks = tracks;
    }

    public String getArtist() {
        return artist;
    }
    
    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track " + track.getTitle() + " is already in the list");
        } 
        else {
            tracks.add(track);
            System.out.println("Track " + track.getTitle() + " has been added");
            setLength(getLength() + track.getLength());
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track " + track.getTitle() + " has been removed");
            setLength(getLength() - track.getLength());
        } 
        else {
            System.out.println("Track " + track.getTitle() + " does not exist in the list");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws hust.soict.globalict.aims.exception.PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle() + " by " + this.getArtist());
            System.out.println("Total length: " + this.getLength());
            for (Track track : tracks) {
                try {
                    track.play();
                } catch (hust.soict.globalict.aims.exception.PlayerException e) {
                    throw e;
                }
            }
        } else {
            throw new hust.soict.globalict.aims.exception.PlayerException("ERROR: CD length is non-positive!");
        }
    }
    
    @Override
    public String toString() {
        return "CD - " + getTitle() + " - " + getCategory() + 
            " - " + getArtist() + " - " + getDirector() + 
            " - " + getLength() + ": " + getCost() + " $";
    }
}
