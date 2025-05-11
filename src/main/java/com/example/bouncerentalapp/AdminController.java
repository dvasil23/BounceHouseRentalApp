package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.UpcomingOrdersDAO;
import com.example.bouncerentalapp.model.UpcomingOrders;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AdminController
{
    @FXML
    private TableView<UpcomingOrders>orders;

    @FXML
    private TableColumn<UpcomingOrders, LocalDate> orderDate;

    @FXML
    private TableColumn<UpcomingOrders, LocalDate> startDate;

    @FXML
    private TableColumn<UpcomingOrders, Integer> productID;

    @FXML
    private TableColumn<UpcomingOrders, String> productName;

    @FXML
    private TableColumn<UpcomingOrders, Integer> quantityTaken;

    @FXML
    private TableColumn<UpcomingOrders, Float> orderTotal;

    @FXML
    public void initialize() {
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityTaken.setCellValueFactory(new PropertyValueFactory<>("quantityTaken"));
        orderTotal.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));

        orders.getItems().setAll(UpcomingOrdersDAO.getAllUpcomingOrders());
    }
    @FXML
    private void adminSignOut(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) orders.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToCustomers(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/customer-view.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setTitle("customer info");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
