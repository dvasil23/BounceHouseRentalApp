package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.CustomerDAO;
import com.example.bouncerentalapp.dao.OrderDAO;
import com.example.bouncerentalapp.dao.RentalAddressDAO;
import com.example.bouncerentalapp.model.Customer;
import com.example.bouncerentalapp.model.Order;
import com.example.bouncerentalapp.model.RentalAddress;
import com.example.bouncerentalapp.model.RentalProduct;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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

    private LocalDate selectedStart;
    private LocalDate selectedEnd;

    private Customer customer;

    private RentalAddress address;

    private int insertedOrderId = -1;

    public void setSelectedProduct(RentalProduct product) {
        this.selectedProduct = product;
        //updateUI();
    }

    public void setSelectedStartDate(LocalDate start){
        this.selectedStart=start;
    }
    public void setSelectedEndDate(LocalDate end){
        this.selectedEnd=end;
    }

    @FXML
    private void showOrderPopUp(Order order) {
        Stage popupStage = new Stage();


        Label orderConfirmation = new Label("Order confirmation");

        Label customerName = new Label("Your name: " + customer.getFirstName() + customer.getLastName());
        Label customerPhoneNum = new Label("your phone number: " + customer.getPhoneNumber());

        Label customerAddr = new Label("address to deliver to: " + address.getStreet() + " " + address.getCity() + " " + address.getState() + " "+ address.getZipCode());

        Label orderedProduct = new Label("product: " + selectedProduct.getProductName());
        Label amountChosen = new Label("amount chosen: " + selectedProduct.getQuantitySelected());
        Label dates = new Label("from: " + selectedStart + ", " + "to: " + selectedEnd);

        Label total = new Label("total " + order.getOrderTotal());

        Button cancelBtn = new Button("cancel order");
        cancelBtn.setOnAction(e -> {
            popupStage.close();
            OrderDAO.cancelOrder(order.getOrderID());
            goToMainScreen();

        });

        Button payBtn = new Button("go to payments");
        payBtn.setOnAction(e -> {
            popupStage.close();
            goToPayScreen();

        });

        HBox buttonBox = new HBox(10, cancelBtn, payBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(orderConfirmation,customerName,customerPhoneNum, customerAddr, orderedProduct, amountChosen, dates, total, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 450);
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    @FXML
    private void goToMainScreen(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/hello-view.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToPayScreen(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/payments-view.fxml"));
            Parent root = loader.load();

            PaymentController controller = loader.getController();
            controller.setOrderID(insertedOrderId);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubmit() {
        System.out.println("successfully submitted!");

        //get customer info
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

        customer = new Customer(0, firstName, lastName, phone);
        insertedCustomerId  = CustomerDAO.insertCustomerAndReturnId(customer);

        if (insertedCustomerId > 0) {
            System.out.println("Customer inserted successfully.");
            address = new RentalAddress(0, insertedCustomerId, city, state, zip, street);
            boolean addressInserted = RentalAddressDAO.insertAddress(address, street);
//if customer and address successfully inserted
            if (addressInserted) {
                System.out.println("Customer and address inserted successfully.");
                //call create order procedure
                insertedOrderId = OrderDAO.createOrderAndReturnOrderId(insertedCustomerId,selectedProduct.getProductID(),selectedStart,selectedEnd,selectedProduct.getQuantitySelected());


                System.out.println("createOrder called!");
                System.out.println("inserted id: " + insertedOrderId);
                Order order = OrderDAO.getOrderByOrderId(insertedOrderId);
                System.out.println("order id: " + order.getOrderID());
                showOrderPopUp(order);
                //pop up with order, customer, and address details, button to cancel order, button to go to payments scene
                //if cancel, go to hello controller
                //if submit, go to payments controller

            } else {
                System.out.println("Failed to insert address.");
            }

        } else {
            System.out.println("Failed to insert customer.");
        }
    }

}
