package com.example.bouncerentalapp;

import com.example.bouncerentalapp.dao.OrderAuditDAO;
import com.example.bouncerentalapp.model.OrderAudit;
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

public class OrderAuditController
{
    @FXML
    private TableView<OrderAudit>orderAuditTableView;

    @FXML
    private TableColumn<OrderAudit, Integer> orderIDCol;

    @FXML
    private TableColumn<OrderAudit, Integer> productIDCol;

    @FXML
    private TableColumn<OrderAudit, Float> orderTotalCol;

    @FXML
    private TableColumn<OrderAudit, String> actionTypeCol;

    @FXML
    private TableColumn<OrderAudit, LocalDate> actionDateCol;

    @FXML
    public void initialize() {
        orderIDCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        orderTotalCol.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
        actionTypeCol.setCellValueFactory(new PropertyValueFactory<>("actionType"));
        actionDateCol.setCellValueFactory(new PropertyValueFactory<>("actionDate"));


        orderAuditTableView.getItems().setAll(OrderAuditDAO.getOrderAudits());
    }

    private void signOutToMain(){
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


}
