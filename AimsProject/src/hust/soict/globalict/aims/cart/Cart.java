package hust.soict.globalict.aims.cart;
import hust.soict.globalict.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public ObservableList<Media> getItemsOrdered() {
    return itemsOrdered;
}

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    
    public void addMedia(Media item) {
        if (itemsOrdered.contains(item)) {
            System.out.println("The item " + item.getTitle() + " is already in the cart.");
        } else {
            itemsOrdered.add(item);
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
        if (itemsOrdered.remove(item)) {
            System.out.println("The item " + item.getTitle() + " has been removed");
        } else {
            System.out.println("The item is not in the cart");
        }
    }

    public void sortByTitle() {
        java.util.Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart has been sorted by title.");
        print();
    }

    public void sortByCost() {
        java.util.Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart has been sorted by cost.");
        print();
    }

    public float totalCost() {
        float total = 0.0f;
        for (Media item : itemsOrdered) {
            total += item.getCost();
        }
        return total;
    }

    public Media searchById(int id) {
        for (Media item : itemsOrdered) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media item : itemsOrdered) {
            if (item.isMatch(title)) {
                return item;
            }
        }
        return null;
    }

    public void empty(){
        itemsOrdered.clear();
    }
}
