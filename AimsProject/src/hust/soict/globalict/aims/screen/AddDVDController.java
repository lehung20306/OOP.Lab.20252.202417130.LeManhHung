package hust.soict.globalict.aims.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.media.DigitalVideoDisc;

public class AddDVDController {
    private Store store;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfDirector;
    @FXML
    private TextField tfLength;
    @FXML
    private TextField tfCost;
    @FXML
    private Button btnAdd;

    public AddDVDController(Store store) {
        super();
        this.store = store;
    }

    @FXML
    private void initialize() {
        btnAdd.setOnAction(event -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                String director = tfDirector.getText();
                int length = Integer.parseInt(tfLength.getText());
                float cost = Float.parseFloat(tfCost.getText());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, cost, director, length);
                store.addMedia(dvd);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("DVD '" + title + "' has been added to the store!");
                alert.showAndWait();

                tfTitle.clear();
                tfCategory.clear();
                tfDirector.clear();
                tfLength.clear();
                tfCost.clear();
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numbers for Length and Cost!");
                alert.showAndWait();
            }
        });
    }
}
