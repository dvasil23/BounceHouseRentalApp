package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.ProductCategoryDAO;
import com.example.bouncerentalapp.dao.ProductImageDAO;
import com.example.bouncerentalapp.dao.ProductRatingDAO;
import com.example.bouncerentalapp.dao.RentalProductDAO;
import com.example.bouncerentalapp.model.ProductImage;
import com.example.bouncerentalapp.model.ProductRating;
import com.example.bouncerentalapp.model.RentalProduct;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class HelloController
{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<RentalProduct> productTable;

    @FXML
    private TableColumn<RentalProduct, String> nameColumn;

    @FXML
    private TableColumn<RentalProduct, Float> priceColumn;

    @FXML
    private TableColumn<RentalProduct, Integer> availableColumn;

    private LocalDate start;

    private LocalDate end;

    //private int amountChosen = 1;


    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getProductName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleFloatProperty(data.getValue().getPrice()).asObject());
        availableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()).asObject());
        productTable.setOnMouseClicked(event -> {
            //when row is double click sho what it is
            if (event.getClickCount() == 2) {
                RentalProduct selectedProduct = productTable.getSelectionModel().getSelectedItem();
                start = startDatePicker.getValue();
                end = endDatePicker.getValue();
                if (selectedProduct != null) {
                    showProductPopup(selectedProduct);
                }
                else{
                    System.out.println("selectedProduct is null!");
                }
            }
        });
    }



    @FXML
    protected void onCheckAvailabilityClick() {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (start == null || end == null) {
            showAlert("Please select both start and end dates.");
            return;
        }

        List<RentalProduct> products = RentalProductDAO.getAvailableProducts(start, end);
        productTable.setItems(FXCollections.observableArrayList(products));
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void handleViewReviews() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/reviews-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Product Reviews");
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showProductPopup(RentalProduct product) {
        Stage popupStage = new Stage();
        popupStage.setTitle("Product Details");


        String imageUrl = ProductImageDAO.getImageUrlByProductId(product.getProductID());
        InputStream stream = getClass().getResourceAsStream("/" + imageUrl);

        if(stream == null){
            System.out.println("stream is null");
        }
        ImageView imageView = new ImageView(new Image(stream));

        //getting image path and making image view
        InputStream is = getClass().getResourceAsStream("/" + imageUrl);

        if (imageUrl != null) {
            //imageView = new ImageView(new Image(getClass().getResourceAsStream("/" + product.getImageURL())));
            //System.out.println("image made");
            imageView = new ImageView(new Image(is));
            System.out.println("Trying to load: " + getClass().getResource("/" + product.getImageURL()));

        } else {
            imageView = new ImageView("images/default.png"); // Or use a default image
            System.out.println("No image found for product ID: " + product.getProductID());
            System.out.println("Trying to load: " + getClass().getResource("/" + product.getImageURL()));
        }

        imageView.setFitHeight(150);
        imageView.setFitWidth(150);


        Label nameLabel = new Label("Product: " + product.getProductName());

        String category = ProductCategoryDAO.getCategory(product.getCategoryID());

        Label categoryLabel = new Label("Category: " + category);
        Label dimensionsLabel = new Label("Dimensions: " + product.getDimensions());
        Label priceLabel = new Label("Price: $" + product.getPrice());
        Label quantityLabel = new Label("Quantity: " + product.getQuantitySelected());

        // increase Quantity Button
        Button increaseBtn = new Button("+");
        increaseBtn.setOnAction(e -> {
            product.setQuantitySelected(product.getQuantitySelected() + 1);
            quantityLabel.setText("Quantity: " + product.getQuantitySelected());
        });
        // decrease Quantity Button
        Button decreaseBtn = new Button("-");
        decreaseBtn.setOnAction(e -> {
            if(product.getQuantitySelected() > 1){
                product.setQuantitySelected(product.getQuantitySelected() - 1);
                quantityLabel.setText("Quantity: " + product.getQuantitySelected());
                }});



        // Checkout Button
        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setOnAction(e -> {
            popupStage.close();
            goToCheckout(product,start,end);
        });

        // Exit Button
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> popupStage.close());

        HBox buttonBox = new HBox(10, increaseBtn, decreaseBtn, checkoutBtn, closeBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(imageView,nameLabel, categoryLabel, dimensionsLabel, priceLabel, quantityLabel, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 450);
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    private void goToCheckout(RentalProduct product, LocalDate start, LocalDate end) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/checkout-view.fxml"));
            Parent root = loader.load();



            CheckoutController controller = loader.getController();
            controller.setSelectedProduct(product);
            controller.setSelectedStartDate(start);
            controller.setSelectedEndDate(end);

            Stage stage = new Stage();
            stage.setTitle("Checkout");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}