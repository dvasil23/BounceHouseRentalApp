package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.CustomerDAO;
import com.example.bouncerentalapp.dao.OrderDAO;
import com.example.bouncerentalapp.dao.ProductRatingDAO;
import com.example.bouncerentalapp.model.Customer;
import com.example.bouncerentalapp.model.Order;
import com.example.bouncerentalapp.model.ProductRating;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class ReviewController
{
    @FXML
    private TableView<ProductRating> reviewTable;

    @FXML
    private TableColumn<ProductRating, String> productNameCol;

    @FXML
    private TableColumn<ProductRating, Double> averageRatingCol;

    @FXML
    private TableColumn<ProductRating, String> reviewTextCol;

    @FXML
    private TextField reviewField;

    @FXML
    private Button submitReview;

    private Customer customer;

    private Order order;

    @FXML
    public void initialize() {
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        averageRatingCol.setCellValueFactory(new PropertyValueFactory<>("averageRating"));
        reviewTextCol.setCellValueFactory(new PropertyValueFactory<>("reviewText"));

        List<ProductRating> reviews = ProductRatingDAO.getAllRatings();
        reviewTable.getItems().setAll(reviews);
    }

    @FXML
    public void handleClose(){
        // Get the current window (stage) and close it
        Stage stage = (Stage) reviewTable.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void reviewPopUp(){
        Stage popUpStage = new Stage();

    }

    @FXML
    private void makeReviewPopUp(){
        Stage reviewStage = new Stage();

        Label label = new Label("enter your order number");
        TextField orderNum = new TextField();

        Button idSubmit = new Button("confirm order number");
        idSubmit.setOnAction(e ->{

        });

        HBox buttonBox = new HBox(10, idSubmit);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, orderNum, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 450);
        reviewStage.setScene(scene);
        reviewStage.initModality(Modality.APPLICATION_MODAL);
        reviewStage.showAndWait();

    }

    @FXML
    private void getOrderId(int orderId){

        order = OrderDAO.getOrderByOrderId(orderId);

        if(order == null){
            System.out.println("order not found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("order number not found");
            alert.show();
        }



    }




}
