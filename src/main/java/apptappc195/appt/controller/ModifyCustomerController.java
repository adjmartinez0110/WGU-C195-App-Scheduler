package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBCountry;
import apptappc195.appt.model.Customer;
import apptappc195.appt.model.Division;
import apptappc195.appt.dao.DBCustomer;
import apptappc195.appt.dao.DBDivision;
import apptappc195.appt.model.Country;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    @FXML
    public TextField modCustName;
    @FXML
    public TextField modCustPostal;
    @FXML
    public TextField modCustAddress;
    @FXML
    public TextField modCustNumber;
    @FXML
    public ComboBox<Division> modCustState;
    @FXML
    public ComboBox<Country> modCustCountry;
    @FXML
    public TextField modCustID;
    @FXML
    public Button modCustSave;
    @FXML
    public Button modCustExit;

    public static Customer toModCustomer = null;

    public void OnClickCountry(ActionEvent event) {
        Country country = modCustCountry.getValue();
        modCustState.setItems(DBDivision.getCountryDivisions(country.getCountryId()));
    }

    public void OnClickSave(ActionEvent actionEvent) throws IOException {

        String customerName = modCustName.getText();
        String address = modCustAddress.getText();
        String postal = modCustPostal.getText();
        String phone = modCustNumber.getText();
        Division state = modCustState.getValue();
        int customerId = Integer.parseInt(modCustID.getText());
        int divisionId = state.getDivisionId();


        if (Objects.equals(customerName, "") || customerName == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer name cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(address, "") || address == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Address cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(postal, "") || postal == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Postal code cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(phone, "") || phone == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer phone cannot be empty.");
            alert.showAndWait();
            return;
        }


        if (state == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer state cannot be empty.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save updated customer data?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
          //  DBCustomer.createCustomer(customerName, address, postal, phone, divisionId);
            DBCustomer.updateCustomer(customerName, address, postal, phone, divisionId, customerId);
            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/CustomerData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customer Data");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void OnClickExit(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit to customer data?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if(confirm.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/CustomerData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customer Data");
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modCustCountry.setItems(DBCountry.getAll());
        modCustID.setText(Integer.toString(toModCustomer.getCustId()));
        modCustName.setText(toModCustomer.getCustName());
        modCustAddress.setText(toModCustomer.getAddress());
        modCustNumber.setText(toModCustomer.getPhone());
        modCustPostal.setText(toModCustomer.getPostalCode());

        modCustCountry.setItems(DBCountry.getAll());
        modCustCountry.setValue(DBCountry.findCountryByID(toModCustomer.getCountry()));

        modCustState.setItems(DBDivision.getAll());
        modCustState.setValue(DBDivision.findDivisionByID(toModCustomer.getDivision()));

    }
}
