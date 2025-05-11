package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.CustomerInfoDAO;
import com.example.bouncerentalapp.model.CustomerInfo;
import javafx.collections.FXCollections;
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

public class CustomerController
{
    @FXML
    private TableView<CustomerInfo>customers;

    @FXML
    private TableColumn<CustomerInfo, Integer> orderIDCol;

    @FXML
    private TableColumn<CustomerInfo, String> productNameCol;

    @FXML
    private TableColumn<CustomerInfo, String> streetCol;

    @FXML
    private TableColumn<CustomerInfo, String> cityCol;

    @FXML
    private TableColumn<CustomerInfo, String> stateCol;

    @FXML
    private TableColumn<CustomerInfo, String> zipCodeCol;

    @FXML
    private TableColumn<CustomerInfo, String> firstNameCol;

    @FXML
    private TableColumn<CustomerInfo, String> lastNameCol;

    @FXML
    private TableColumn<CustomerInfo, String> phoneNumberCol;

    @FXML
    private TableColumn<CustomerInfo, LocalDate> startDateCol;

    @FXML
    public void initialize() {
        orderIDCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));


        customers.setItems(FXCollections.observableArrayList(CustomerInfoDAO.getCustomerInfo()));
    }

    @FXML
    private void signOut(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("main");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seeOrderAudit(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bouncerentalapp/order_audit-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("order audit");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
