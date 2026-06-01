package hust.soict.globalict.aims.screen;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.*;

public class MediaStore extends JPanel{
    private Cart cart;
    private Media media;

    public MediaStore (Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(""+media.getCost()+" $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(null, media.getTitle() + " has been added to cart.");
            }
        });
        container.add(btnAddToCart);

        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ((Playable) media).play();
                        
                        JDialog dialog = new JDialog();
                        dialog.setTitle("Playing Media");
                        dialog.setSize(300, 150);
                        dialog.setLayout(new BorderLayout());
                        
                        JLabel playLabel = new JLabel("Playing: " + media.getTitle());
                        playLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        dialog.add(playLabel, BorderLayout.CENTER);
                        
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    } catch (hust.soict.globalict.aims.exception.PlayerException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Illegal DVD Length", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            container.add(btnPlay);
        }
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}