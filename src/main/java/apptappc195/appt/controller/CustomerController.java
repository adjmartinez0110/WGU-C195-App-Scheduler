package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.dao.DBUsers;
import apptappc195.appt.model.Appointments;
import apptappc195.appt.model.Customer;
import apptappc195.appt.dao.DBCustomer;
import apptappc195.appt.model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    public TableView<Customer> custDataTable;
    @FXML
    public Button CustDataExit;
    @FXML
    public TableColumn custID;
    @FXML
    public TableColumn custName;
    @FXML
    public TableColumn postal;
    @FXML
    public TableColumn address;
    @FXML
    public TableColumn phone;
    public TableColumn divisionId;
    public TableColumn countryId;
    @FXML
    private Button addCustData;
    @FXML
    private Button modCustData;
    @FXML
    private Button delCustData;

    private Customer selectedCustomer;

    /**
     * Method to direct to add a new customer.
     * @param actionEvent
     * @throws IOException
     */
    public void OnClickAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/AddCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Will delete customer if and only if the customer has no apppointments or they have been deleted.
     * @param actionEvent
     * @throws IOException
     */
    public void OnClickMod(ActionEvent actionEvent) throws IOException {

        selectedCustomer = custDataTable.getSelectionModel().getSelectedItem();
        ModifyCustomerController.toModCustomer = selectedCustomer;

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer.");
            alert.showAndWait();
        } else {

            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/ModifyCustomer.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Customer");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void OnClickDel(ActionEvent actionEvent) {

        selectedCustomer = custDataTable.getSelectionModel().getSelectedItem();
        ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer.");
            alert.showAndWait();
        }

         else {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected customer? All related appointments will also be deleted.");
                Optional<ButtonType> confirm = alert2.showAndWait();
                if (confirm.get() == ButtonType.OK) {

                    DBCustomer.deleteCustomer(selectedCustomer.getCustId());
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Customer with ID " + selectedCustomer.getCustId() + " deleted.");
                    alert3.showAndWait();
                    custDataTable.setItems(DBCustomer.getAllCustomers());
                }
            }
        }


    public void OnClickExit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Directory.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Directory");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //setting up table columns
        custID.setCellValueFactory(new PropertyValueFactory<>("custId"));
        custName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        postal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionId.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryId.setCellValueFactory(new PropertyValueFactory<>("country"));

        custDataTable.setItems(DBCustomer.getAllCustomers());
    }


}
