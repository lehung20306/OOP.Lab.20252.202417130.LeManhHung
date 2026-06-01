package hust.soict.globalict.aims.store;
import hust.soict.globalict.aims.media.Media;
import java.util.List;
import java.util.ArrayList;

public class Store {
    private List<Media> itemsInStore = new ArrayList<Media>();

    public ArrayList<Media> getItemsInStore() {
        return (ArrayList<Media>) itemsInStore;
    }
        
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Items:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("***************************************************");
    }
    
    public void addMedia(Media item) {
        if (itemsInStore.contains(item)) {
            System.out.println("The item " + item.getTitle() + " is already in the store.");
        } 
        else {
            itemsInStore.add(item);
            System.out.println("The item " + item.getTitle() + " has been added.");
        }
    }

    public void addMedia(Media[] itemList) {
    for (Media item : itemList) {
            this.addMedia(item);
        }
    }

    public void addMedia(Media item1, Media item2) {
        this.addMedia(item1);
        this.addMedia(item2);
    }

    public void removeMedia(Media item) {
        if (itemsInStore.remove(item)) {
            System.out.println("The item " + item.getTitle() + " has been removed");
        } else {
            System.out.println("The item is not in the store");
        }
    }

    public Media searchByTitle(String title) {
        for (Media item : itemsInStore) {
            if (item.isMatch(title)) {
                return item;
            }
        }
        return null;
    }
}
