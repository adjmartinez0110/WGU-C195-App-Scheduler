package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.model.Division;
import apptappc195.appt.dao.DBCountry;
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

public class AddCustomerController implements Initializable {
    @FXML
    public TextField addCustName;
    @FXML
    public TextField addCustPostal;
    @FXML
    public TextField addCustAddress;
    @FXML
    public TextField addCustNumber;
    @FXML
    public ComboBox<Division> addCustState;
    @FXML
    public ComboBox<Country> addCustCountry;
    @FXML
    public TextField addCustID;
    @FXML
    public Button addCustSave;
    @FXML
    public Button addCustExit;


    /**
     * Method for selecting customer country and state/province.
     *
     * @param event
     */
    public void OnClickCountry(ActionEvent event) {
        Country country = addCustCountry.getValue();
        addCustState.setItems(DBDivision.getCountryDivisions(country.getCountryId()));
    }

    /**
     * Method for saving and storing the customer data.
     * @param actionEvent
     */
    public void OnClickSave(ActionEvent actionEvent) throws IOException {
        String customerName = addCustName.getText();
        String address = addCustAddress.getText();
        String postal = addCustPostal.getText();
        String phone = addCustNumber.getText();
        Division state = addCustState.getValue();
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save new customer data?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            DBCustomer.createCustomer(customerName, address, postal, phone, divisionId);

            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/CustomerData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customer Data");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Exits to Customer Data Table.
     * @param actionEvent
     * @throws IOException
     */
    public void OnClickExit(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit to customer data?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/CustomerData.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customer Data");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Populates country and state combo boxes upon initializing.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addCustCountry.setItems(DBCountry.getAll());

    }
}
