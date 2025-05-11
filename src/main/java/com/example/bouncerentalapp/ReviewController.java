package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.CustomerDAO;
import com.example.bouncerentalapp.dao.OrderDAO;
import com.example.bouncerentalapp.dao.ProductRatingDAO;
import com.example.bouncerentalapp.dao.RentalReviewDAO;
import com.example.bouncerentalapp.model.Customer;
import com.example.bouncerentalapp.model.Order;
import com.example.bouncerentalapp.model.ProductRating;
import com.example.bouncerentalapp.model.RentalReview;
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

    private int orderId;

    public void setSelectedOrderId(int orderId){
        this.orderId=orderId;
    }

    @FXML
    public void initialize() {
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        averageRatingCol.setCellValueFactory(new PropertyValueFactory<>("averageRating"));
        reviewTextCol.setCellValueFactory(new PropertyValueFactory<>("reviewText"));

        List<ProductRating> reviews = ProductRatingDAO.getAllRatings();
        reviewTable.getItems().setAll(reviews);
        loadReviewTable();
    }

    @FXML
    private void loadReviewTable() {
        //need to update the table
        List<ProductRating> reviews = ProductRatingDAO.getAllRatings();
        reviewTable.getItems().setAll(reviews);
    }

    @FXML
    public void handleClose(){
        Stage stage = (Stage) reviewTable.getScene().getWindow();
        stage.close();


    }

    @FXML
    private void goToMain(){

    }

    @FXML
    private void ratingPopUp(){
        Stage stage = new Stage();
        Label label = new Label("enter your review");
        TextField reviewText = new TextField();
        Label ratingLabel = new Label("enter your rating out of 10");
        TextField ratingField = new TextField();


        Button btn = new Button("submit your review");
        btn.setOnAction(e ->{
            String text = reviewText.getText();
            String ratingString = ratingField.getText().trim();
            int rating = Integer.parseInt(ratingString);

                if(text.isEmpty()){
                    System.out.println("text field cant be empty");
                }
                else if(rating < 0 || rating > 10){
                    System.out.println("invalid rating");
                }
                else{
                    getOrderId(orderId);
                    RentalReviewDAO.insertReview(order.getOrderID(), order.getCustomerID(), text,rating, order.getProductID());
                    loadReviewTable();

                    stage.close();
                }
        });

        HBox buttonBox = new HBox(10, btn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, reviewText, ratingLabel, ratingField, buttonBox);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 450);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void getOrderId(int orderId){

        System.out.println("Entered Order ID: " + orderId);
        order = OrderDAO.getOrderByOrderId(orderId);
        System.out.println("Fetched Order: " + (order != null ? order.getOrderID() : "null"));

        if (order != null) {
            System.out.println("Order found: " + order.getOrderID());
            //ratingPopUp();  // proceed to review
        } else {
            System.out.println("Invalid order ID: " + orderId);
            System.out.println("No order found with ID: " + orderId);
        }



    }




}
