package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.OrderDAO;
import com.example.bouncerentalapp.dao.PaymentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentController
{
    @FXML
    private TextField cardTypeField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardExpiresField;

    @FXML
    private TextField billingAddressField;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button payBtn;

    private int orderID;

    public void setOrderID(int orderID){
        this.orderID=orderID;
    }

    @FXML
    private void handlePay(){
        String cardType = cardTypeField.getText();
        String cardNumber = cardNumberField.getText();
        String cardExpires = cardExpiresField.getText();
        String billingAddress = billingAddressField.getText();

        if(cardType.isEmpty() || cardNumber.isEmpty() || cardExpires.isEmpty() || billingAddress.isEmpty()){
            showAlert("all fields are required");
        }

        PaymentDAO.insertPayment(orderID,cardType,cardNumber,cardExpires,billingAddress);
        System.out.println("payment sucessfully processed");
        finalPopUp();

    }

    @FXML
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error alert dialog");
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void finalPopUp(){
        Stage popupStage = new Stage();

        Label payLabel = new Label("payment successfully processed!");
        Label label = new Label("your order confirmation number is: " + orderID);

        Button homePageBtn = new Button("go back to home page");
        Button cancelBtn = new Button("cancel order");

        homePageBtn.setOnAction(e -> {
            popupStage.close();
            goToMain();

        });

        cancelBtn.setOnAction(e -> {
            OrderDAO.cancelOrder(orderID);
            goToMain();

        });

        HBox buttonBox = new HBox(10, homePageBtn, cancelBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(payLabel, label, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 450);
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    @FXML
    private void goToMain(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/hello-view.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ev) {
            ev.printStackTrace();
        }
    }
}
