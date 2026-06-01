package hust.soict.globalict.aims.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.media.CompactDisc;

public class AddCDController {
    private Store store;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfArtist;
    @FXML
    private TextField tfDirector;
    @FXML
    private TextField tfCost;
    @FXML
    private Button btnAdd;

    public AddCDController(Store store) {
        super();
        this.store = store;
    }

    @FXML
    private void initialize() {
        btnAdd.setOnAction(event -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                String artist = tfArtist.getText();
                String director = tfDirector.getText();
                float cost = Float.parseFloat(tfCost.getText());

                CompactDisc cd = new CompactDisc(title, category, cost, director, 0, artist, new java.util.ArrayList<>());
                store.addMedia(cd);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("CD '" + title + "' has been added to the store!");
                alert.showAndWait();

                tfTitle.clear();
                tfCategory.clear();
                tfArtist.clear();
                tfDirector.clear();
                tfCost.clear();
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid number for Cost!");
                alert.showAndWait();
            }
        });
    }
}