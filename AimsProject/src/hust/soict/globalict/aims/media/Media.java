package hust.soict.globalict.aims.media;
import java.util.Comparator;

public abstract class Media {
    private static int nbMedia = 0;
    private String title;
    private String category;
    private float cost;
    private int id;
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media(String title){
        this.title = title;
        nbMedia++;
        this.id = nbMedia;
    }

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbMedia++;
        this.id = nbMedia;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isMatch(String title) {
        return this.title.equalsIgnoreCase(title) || this.title.toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        
        try {
            Media media = (Media) obj;
            return this.title != null && this.title.equalsIgnoreCase(media.getTitle());
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

}
