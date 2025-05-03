package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.RentalProductDAO;
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

import java.io.IOException;
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

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getProductName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleFloatProperty(data.getValue().getPrice()).asObject());
        availableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()).asObject());
        productTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                RentalProduct selectedProduct = productTable.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    showProductPopup(selectedProduct);
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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }



    private void showProductPopup(RentalProduct product) {
        Stage popupStage = new Stage();
        popupStage.setTitle("Product Details");

        // Image (Replace with real image loading logic)
        ImageView imageView = new ImageView(new Image("file:images/" + product.getProductID() + ".png"));
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);

        // Info Labels
        Label nameLabel = new Label("Product: " + product.getProductName());
        Label categoryLabel = new Label("Category: " + product.getCategoryID());
        Label dimensionsLabel = new Label("Dimensions: " + product.getDimensions());
        Label priceLabel = new Label("Price: $" + product.getPrice());
        Label quantityLabel = new Label("Quantity: " + product.getQuantitySelected());

        // Increase Quantity Button
        Button increaseBtn = new Button("+");
        increaseBtn.setOnAction(e -> {
            product.setQuantitySelected(product.getQuantitySelected() + 1);
            quantityLabel.setText("Quantity: " + product.getQuantitySelected());
        });
        // Increase Quantity Button
        Button decreaseBtn = new Button("-");
        decreaseBtn.setOnAction(e -> {
            product.setQuantitySelected(product.getQuantitySelected() - 1);
            quantityLabel.setText("Quantity: " + product.getQuantitySelected());
        });

        // Checkout Button
        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setOnAction(e -> {
            popupStage.close();
            goToCheckout(product);
        });

        // Exit Button
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> popupStage.close());

        HBox buttonBox = new HBox(10, increaseBtn, decreaseBtn, checkoutBtn, closeBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, imageView, nameLabel, categoryLabel, dimensionsLabel, priceLabel, quantityLabel, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 450);
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL); // Prevent background clicks
        popupStage.showAndWait();
    }

    private void goToCheckout(RentalProduct product) {
        // Pass the product to your checkout controller via constructor, setter, or singleton
        // Then load the checkout scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkout-view.fxml"));
            Parent root = loader.load();

            // Optionally pass the selected product
            CheckoutController controller = loader.getController();
            controller.setSelectedProduct(product);

            Stage stage = new Stage();
            stage.setTitle("Checkout");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}