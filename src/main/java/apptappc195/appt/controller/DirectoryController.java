package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class DirectoryController implements Initializable {
    public Button directoryCustData;
    public Button directoryAppts;
    public Button directoryReports;
    public Button directoryExit;

    LocalDateTime currentTime = LocalDateTime.now();

    /**
     * Will navigate to customer data upon click.
     * @param actionEvent
     */
    public void OnClickCustData(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/CustomerData.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer Data");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Will navigate to appointments upon click.
     * @param actionEvent
     */
    public void OnClickAppts(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Appointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Will navigate to reports upon click.
     * @param actionEvent
     */
    public void OnClickReports(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Reports.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Exits the application.
     * @param actionEvent
     */
    @FXML
        public void OnClickExit(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit the application?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.get() == ButtonType.OK) {
                System.exit(0);
            }
        }

        public void initialize(URL location, ResourceBundle resources) {

            for (Appointments a : DBAppointments.getAllAppointments()) {

                LocalDateTime apptTime = a.getStartTime();
                LocalDateTime alertingTime = currentTime.plusMinutes(15);

                if (apptTime.isAfter(currentTime) && apptTime.isBefore(alertingTime)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is an upcoming appointment with ID of " + a.getApptId() + ", " +
                            "and a date and time of " + a.getStartTime() + ".");
                    alert.showAndWait();
                    return;
                }

                else if (apptTime.isAfter(alertingTime)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "There are no upcoming appointments.");
                    alert.showAndWait();
                    return;
                }

            }
        }
}


