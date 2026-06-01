package hust.soict.globalict.aims;

import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.*;
import hust.soict.globalict.aims.screen.StoreScreen; 

public class Aims {

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f, "Roger Allers", 87));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", 24.95f, "George Lucas", 124));
        store.addMedia(new Book("Java Programming", "Technology", 15.0f));
        store.addMedia(new DigitalVideoDisc("Loi DVD", "Test", 0.0f, "Hung", 0)); 

        new StoreScreen(store,cart);
    }
}