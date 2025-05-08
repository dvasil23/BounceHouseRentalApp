package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.CustomerDAO;
import com.example.bouncerentalapp.dao.RentalAddressDAO;
import com.example.bouncerentalapp.model.Customer;
import com.example.bouncerentalapp.model.RentalAddress;
import com.example.bouncerentalapp.model.RentalProduct;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;


    @FXML
    private VBox addressBox;

    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipCodeField;

    @FXML
    private VBox finalOrderBox;

    private int insertedCustomerId = -1;

    private int customerID;

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

    @FXML
    private void handleSubmit() {
        System.out.println("successfully submitted!");

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneNumberField.getText();

        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zip = zipCodeField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        Customer customer = new Customer(0, firstName, lastName, phone);
        insertedCustomerId  = CustomerDAO.insertCustomerAndReturnId(customer);

        if (insertedCustomerId > 0) {
            System.out.println("Customer inserted successfully.");
            RentalAddress address = new RentalAddress(0, insertedCustomerId, city, state, zip);
            boolean addressInserted = RentalAddressDAO.insertAddress(address, street);

            if (addressInserted) {
                System.out.println("Customer and address inserted successfully.");
                // Proceed to next step (e.g., show confirmation screen)
            } else {
                System.out.println("Failed to insert address.");
            }

        } else {
            System.out.println("Failed to insert customer.");
        }



    }

}
