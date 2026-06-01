package hust.soict.globalict.aims.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.media.Book;

public class AddBookController {
    private Store store;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfAuthors;
    @FXML
    private TextField tfCost;
    @FXML
    private Button btnAdd;

    public AddBookController(Store store) {
        super();
        this.store = store;
    }

    @FXML
    private void initialize() {
        btnAdd.setOnAction(event -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());

                Book book = new Book(title, category, cost);
                
                String authorsText = tfAuthors.getText();
                if (!authorsText.isEmpty()) {
                    String[] authors = authorsText.split(",");
                    for (String author : authors) {
                        book.addAuthor(author.trim());
                    }
                }

                store.addMedia(book);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Book '" + title + "' has been added to the store!");
                alert.showAndWait();

                tfTitle.clear();
                tfCategory.clear();
                tfAuthors.clear();
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