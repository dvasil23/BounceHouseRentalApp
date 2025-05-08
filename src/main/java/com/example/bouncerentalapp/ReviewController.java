package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.CustomerDAO;
import com.example.bouncerentalapp.dao.ProductRatingDAO;
import com.example.bouncerentalapp.model.Customer;
import com.example.bouncerentalapp.model.ProductRating;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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




}
