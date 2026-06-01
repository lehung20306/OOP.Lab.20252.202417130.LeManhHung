package hust.soict.globalict.aims.screen;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.*;

public class StoreScreen extends JFrame{
    private Store store;
    private Cart cart;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        
        JMenuItem menuAddBook = new JMenuItem("Add Book");
        menuAddBook.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new AddBookScreen(store);
            }
        });
        smUpdateStore.add(menuAddBook);

        JMenuItem menuAddCd = new JMenuItem("Add CD");
        menuAddCd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new AddCDScreen(store);
            }
        });

        smUpdateStore.add(menuAddCd);
        JMenuItem menuAddDvd = new JMenuItem("Add DVD");
        menuAddDvd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new AddDVDScreen(store);
            }
        });
        smUpdateStore.add(menuAddDvd);

        menu.add(smUpdateStore);
        JMenuItem viewStoreMenu = new JMenuItem("View store");
        viewStoreMenu.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new StoreScreen(store, cart);
                dispose();
            }
        });
        menu.add(viewStoreMenu);
        
        JMenuItem viewCartMenu = new JMenuItem("View cart");
        viewCartMenu.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new CartScreen(store, cart);
                dispose();
            }
        });
        menu.add(viewCartMenu);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout (header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground (Color.CYAN);

        JButton btnCart = new JButton("View cart");
        btnCart.setPreferredSize(new Dimension (100, 50));
        btnCart.setMaximumSize (new Dimension(100, 50));

        btnCart.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new CartScreen(store, cart);
                dispose();
            }
        });

        header.add(Box.createRigidArea (new Dimension (10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(btnCart);
        header.add(Box.createRigidArea (new Dimension (10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 3, 2, 2));

        java.util.ArrayList<Media> mediaInStore = store.getItemsInStore();
        
        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media, this.cart);
            center.add(cell);
        }
        
        return center;
    }

    public StoreScreen (Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }
}