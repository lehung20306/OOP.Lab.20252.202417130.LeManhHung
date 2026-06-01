package hust.soict.globalict.aims.media;

public class Track implements Playable{
    private String title;
    private int length;
    
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws hust.soict.globalict.aims.exception.PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            System.err.println("ERROR: Track length is non-positive!");
            throw new hust.soict.globalict.aims.exception.PlayerException("ERROR: Track length is non-positive!");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) return false;
        Track tmp = (Track) obj;
        return this.title.equals(tmp.getTitle()) && this.length == tmp.getLength();
    }
}
