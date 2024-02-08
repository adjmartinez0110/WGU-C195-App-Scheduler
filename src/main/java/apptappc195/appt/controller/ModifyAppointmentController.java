package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.dao.DBContacts;
import apptappc195.appt.model.Customer;
import apptappc195.appt.model.Users;
import apptappc195.appt.dao.DBCustomer;
import apptappc195.appt.dao.DBUsers;
import apptappc195.appt.model.Appointments;
import apptappc195.appt.model.Contacts;
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

public class ModifyAppointmentController implements Initializable {

    @FXML
    public TextField modApptID;
    @FXML
    public TextField modApptTitle;
    @FXML
    public TextField modApptDesc;
    @FXML
    public TextField modApptLoc;
    @FXML
    public TextField modApptType;
    @FXML
    public Button modApptSave;
    @FXML
    public Button modApptExit;
    @FXML
    public ComboBox<Contacts> contactComboBox;
    @FXML
    private ComboBox<LocalTime> startTimeComboBox;
    @FXML
    private ComboBox<LocalTime> endTimeComboBox;
    @FXML
    public ComboBox<Users> userIDComboBox;
    @FXML
    public ComboBox<Customer> custIDComboBox;
    @FXML
    public DatePicker dateDPicker;

    public static Appointments toModAppt = null;

    public void OnClickSave(ActionEvent actionEvent) throws IOException {


        String title = modApptTitle.getText();
        String description = modApptDesc.getText();
        String location = modApptLoc.getText();
        Contacts contacts = contactComboBox.getValue();
        String type = modApptType.getText();
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

                if (a.getApptId() == toModAppt.getApptId()) {
                    continue;
                }
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
                DBAppointments.updateAppointment(title, description, location, type, start, end, toModAppt.getApptId());

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


    public void OnClickExit(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit to appointments?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Appointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactComboBox.setItems(DBContacts.getAll());
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

        try {
            modApptID.setText((String.valueOf(toModAppt.getApptId())));
            modApptTitle.setText(toModAppt.getTitle());
            modApptDesc.setText(toModAppt.getDescription());
            modApptLoc.setText(String.valueOf(toModAppt.getLocation()));
            modApptType.setText(toModAppt.getType());

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All numeric fields must have an appropriate" +
                    " value.");
            alert.showAndWait();
        }
    }
}
