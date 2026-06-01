package hust.soict.globalict.aims.screen;

import java.io.IOException;
import javax.swing.JFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import hust.soict.globalict.aims.store.Store;

public class AddDVDScreen extends JFrame {
    private Store store;

    public AddDVDScreen(Store store) {
        super();
        this.store = store;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Add DVD");
        this.setVisible(true);
        this.setSize(600, 400);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/hust/soict/globalict/aims/screen/add_dvd.fxml"));
                    
                    AddDVDController controller = new AddDVDController(store);
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
