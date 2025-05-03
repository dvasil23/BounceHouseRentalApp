package com.example.bouncerentalapp;

import com.example.bouncerentalapp.model.RentalProduct;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CheckoutController
{
    @FXML
    private Label productNameLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label dimensionsLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Button confirmOrderButton;

    private RentalProduct selectedProduct;

    public void setSelectedProduct(RentalProduct product) {
        this.selectedProduct = product;
        updateUI();
    }

    private void updateUI() {
        if (selectedProduct != null) {
            productNameLabel.setText("Product: " + selectedProduct.getProductName());
            categoryLabel.setText("Category: " + selectedProduct.getCategoryID());
            dimensionsLabel.setText("Dimensions: " + selectedProduct.getDimensions());
            priceLabel.setText("Price: $" + selectedProduct.getPrice());
            quantityLabel.setText("Quantity: " + selectedProduct.getQuantitySelected());
        }
    }

    @FXML
    private void onConfirmOrderClick() {
        // Implement order creation logic here (e.g., insert into orders/order_products)
        System.out.println("Order confirmed for " + selectedProduct.getQuantitySelected() + " units of " + selectedProduct.getProductName());
    }
}
