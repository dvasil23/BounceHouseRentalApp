package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.RentalProductDAO;
import com.example.bouncerentalapp.model.RentalProduct;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

}