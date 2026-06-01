package hust.soict.globalict.aims.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

public class CartScreenController {
    private Cart cart;
    private Store store;
    private javax.swing.JFrame stage;

    @FXML
    private javafx.scene.control.MenuItem menuViewStore;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblTotalCost;

    @FXML
    private javafx.scene.control.TextField tfFilter;

    @FXML
    private javafx.scene.control.RadioButton radioBtnFilterId;

    @FXML
    private javafx.scene.control.RadioButton radioBtnFilterTitle;

    @FXML
    private javafx.scene.control.Button btnPlaceOrder;

    @FXML
    private javafx.scene.control.MenuItem menuAddBook;

    @FXML
    private javafx.scene.control.MenuItem menuAddCd;

    @FXML
    private javafx.scene.control.MenuItem menuAddDvd;

    public CartScreenController(Store store, Cart cart, javax.swing.JFrame stage) {
        super();
        this.store = store;
        this.cart = cart;
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));

        javafx.collections.transformation.FilteredList<Media> filteredData = 
            new javafx.collections.transformation.FilteredList<>(this.cart.getItemsOrdered(), p -> true);

        tblMedia.setItems(filteredData);

        tfFilter.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredData.setPredicate(media -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (radioBtnFilterTitle.isSelected()) {
                        if (media.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                            return true; 
                        }
                    } else if (radioBtnFilterId.isSelected()) {
                        if (String.valueOf(media.getId()).contains(lowerCaseFilter)) {
                            return true; 
                        }
                    }
                    return false;
                });
            }
        });

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new javafx.beans.value.ChangeListener<Media>() {
                @Override
                public void changed(javafx.beans.value.ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }
            });

        btnRemove.setOnAction(event -> {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            if (media != null) {
                cart.removeMedia(media);
            }
        });

        btnPlay.setOnAction(event -> {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            if (media != null && media instanceof hust.soict.globalict.aims.media.Playable) {
                try {
                    ((hust.soict.globalict.aims.media.Playable) media).play();
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                    alert.setTitle("Playing Media");
                    alert.setHeaderText(null);
                    alert.setContentText("Playing: " + media.getTitle());
                    alert.showAndWait();
                    
                } catch (hust.soict.globalict.aims.exception.PlayerException e) {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Illegal DVD Length");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    e.printStackTrace();
                }
            }
        });

        cart.getItemsOrdered().addListener(new javafx.collections.ListChangeListener<Media>() {
            @Override
            public void onChanged(Change<? extends Media> c) {
                updateTotalCost();
            }
        });

        updateTotalCost();

        btnPlaceOrder.setOnAction(event -> {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Order Notification");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been placed successfully!");
            alert.showAndWait();
            
            cart.getItemsOrdered().clear();
            
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        });

        menuViewStore.setOnAction(event -> {
            new StoreScreen(store, cart);
            stage.dispose();
        });

        menuAddBook.setOnAction(event -> {
            new AddBookScreen(store);
        });

        menuAddCd.setOnAction(event -> {
            new AddCDScreen(store);
        });

        menuAddDvd.setOnAction(event -> {
            new AddDVDScreen(store);
        });
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof hust.soict.globalict.aims.media.Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    void updateTotalCost() {
        lblTotalCost.setText(cart.totalCost() + " $");
    }

}