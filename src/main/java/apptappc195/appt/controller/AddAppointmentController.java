package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.dao.DBContacts;
import apptappc195.appt.dao.DBCustomer;
import apptappc195.appt.dao.DBUsers;
import apptappc195.appt.model.Contacts;
import apptappc195.appt.model.Customer;
import apptappc195.appt.model.Users;
import  apptappc195.appt.dao.*;
import  apptappc195.appt.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    @FXML
    public TextField addApptId;
    @FXML
    public TextField addApptTitle;
    @FXML
    public TextField addApptDesc;
    @FXML
    public TextField addApptLoc;
    @FXML
    public ComboBox<Contacts> ContactComboBox;
    @FXML
    public TextField addApptType;
    @FXML
    public DatePicker dateDPicker;
    @FXML
    public ComboBox<Customer> custIDComboBox;
    @FXML
    public ComboBox<Users> userIDComboBox;
    @FXML
    public ComboBox<LocalTime> startTimeComboBox;
    @FXML
    public ComboBox<LocalTime> endTimeComboBox;
    @FXML
    public Button addApptSave;
    @FXML
    public Button addApptExit;

    /**
     * Method for saving data and passing on to appointments.
     * @param actionEvent
     */
    public void OnClickSave(ActionEvent actionEvent) throws IOException {

        String title = addApptTitle.getText();
        String description = addApptDesc.getText();
        String location = addApptLoc.getText();
        Contacts contacts = ContactComboBox.getValue();
        String type = addApptType.getText();
        LocalDate date = dateDPicker.getValue();
        Customer customerId = custIDComboBox.getValue();
        Users userId = userIDComboBox.getValue();
        LocalTime startTime = startTimeComboBox.getValue();
        LocalTime endTime = endTimeComboBox.getValue();
        LocalDateTime start = LocalDateTime.of(date, startTime);
        LocalDateTime end = LocalDateTime.of(date, endTime);

        //Appointment alerts
    try {
        if (Objects.equals(title, "") || title == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment title cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(description, "") || description == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment description cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(location, "") || location == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment location cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (contacts == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Contact ID cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (Objects.equals(type, "") || type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment type cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment date cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (customerId == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer ID cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (userId == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "User ID cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (startTime == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment start time cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (endTime == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment end time cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (startTime.isAfter(endTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment start time cannot come after end time.");
            alert.showAndWait();
            return;
        }

        if (endTime.isBefore(startTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment end time cannot come before start time.");
            alert.showAndWait();
            return;
        }

        for (Appointments a: DBAppointments.getAllAppointments()) {

            if (a.getStartTime().isBefore(start) && a.getEndTime().isAfter(start)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There are overlapping appointments found. Please correct before continuing.");
                alert.showAndWait();
                return;
            }
            if (a.getStartTime().isAfter(start) && a.getStartTime().isBefore(end)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There are overlapping appointments found. Please correct before continuing.");
                alert.showAndWait();
                return;
            }
            if (a.getStartTime().equals(start)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There are overlapping appointments found. Please correct before continuing.");
                alert.showAndWait();
                return;
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save new appointment data?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            DBAppointments.createAppointment( title, description, location, contacts.getContactId(), type, start, end, customerId.getCustId(), userId.getUserId());

            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Appointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }

    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "All numeric fields must have an appropriate value.");
        alert.showAndWait();
    }
    }

    /**
     * Method to exit to appointments table.
     * @param actionEvent
     * @throws IOException
     */
    public void OnClickExit(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit to appointments?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if(confirm.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Appointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Initialize method for passing data to combo boxes. Times will change based on user's system default time zone.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ContactComboBox.setItems(DBContacts.getAll());
        custIDComboBox.setItems(DBCustomer.getAllCustomers());
        userIDComboBox.setItems(DBUsers.getAll());

        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();

        LocalTime begin = LocalTime.of(8, 0);

        dateDPicker.setValue(LocalDate.now());
        LocalDate beginningDate = dateDPicker.getValue();
        LocalDateTime ldt = LocalDateTime.of(beginningDate, begin);
        ZonedDateTime startESTdt = ldt.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localdt = startESTdt.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime start = LocalTime.of(localdt.getHour(), localdt.getMinute());


        LocalTime end = LocalTime.of(22, 0);

        dateDPicker.setValue(LocalDate.now());
        LocalDate dateEnd = dateDPicker.getValue();
        LocalDateTime ldtEnd = LocalDateTime.of(dateEnd, end);
        ZonedDateTime endESTdt = ldtEnd.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localdtEnd = endESTdt.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime endBH = LocalTime.of(localdtEnd.getHour(), localdtEnd.getMinute());

        while (start.isBefore(endBH)) {
            timeList.add(start);
            start = start.plusMinutes(15);
        }


        startTimeComboBox.setItems(timeList);
        endTimeComboBox.setItems(timeList);
    }
}

