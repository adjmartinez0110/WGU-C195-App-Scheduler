package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.model.Appointments;
import javafx.collections.FXCollections;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppointmentsController implements Initializable {

    @FXML
    private TableView<Appointments> TableReport;
    @FXML
    public Button addAppt;
    @FXML
    public Button delAppt;
    @FXML
    public Button modAppt;
    @FXML
    public Button apptExit;
    @FXML
    public RadioButton AllRadio;
    @FXML
    public TableColumn<Appointments, Integer> apptID;
    @FXML
    public TableColumn<Appointments, Integer> custID;
    @FXML
    public TableColumn<Appointments, Integer> userID;
    @FXML
    public TableColumn<Appointments, String> title;
    @FXML
    public TableColumn<Appointments, String> description;
    @FXML
    public TableColumn<Appointments, String> location;
    @FXML
    public TableColumn<Appointments, String> type;
    @FXML
    public TableColumn<Appointments, String> startTime;
    @FXML
    public TableColumn<Appointments, String> endTime;
    @FXML
    public TableColumn<Appointments, Integer> contactID;
    @FXML
    private RadioButton MonthRadio;
    @FXML
    private RadioButton WeekRadio;

    private Appointments selectedAppt;



    /**
     * Method for filtering/searching by month. This is the first of two lambda expression that has been implemented
     * within the program. This has been done to make the program more concise by combining both the declaration and the
     * use of the filteredMonthAppts variable.
     * @param event
     */
    @FXML
    public void OnActMonth(ActionEvent event) {

        if(MonthRadio.isSelected()) {
            ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();
            LocalDateTime currentDate = LocalDateTime.now();

            ObservableList<Appointments> filteredMonthAppts = allAppts.filtered(appt ->
                    appt.getStartTime().getMonth() == currentDate.getMonth()
            );

            TableReport.setItems(filteredMonthAppts);
        }
    }


    /**
     * Method for filtering/searching by week. This is the second of two lambda expression that has been implemented
     * within the program. This has been done to make the program more concise by combining both the declaration
     * and the use of the filteredWeekAppts variable.
     * @param event
     */
    @FXML
    void OnActWeek(ActionEvent event) {

        if (WeekRadio.isSelected()) {
            ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();
            LocalDateTime startOfWeek = LocalDateTime.now().minusWeeks(1);
            LocalDateTime endOfWeek = LocalDateTime.now().plusWeeks(1);

            ObservableList<Appointments> filteredWeekAppts = allAppts.filtered(appt ->
                    appt.getStartTime().isAfter(startOfWeek) && appt.getStartTime().isBefore(endOfWeek)
            );

            TableReport.setItems(filteredWeekAppts);
        }
    }

    /**
     * Method for filtering/searching all.
     *
     * @param event
     */
    public void OnActAll(ActionEvent event) {

        ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();
        TableReport.setItems(allAppts);
    }


    /**
     * Method for adding appointment on click.
     *
     * @param event
     */
    @FXML
    public void OnClickAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/AddAppointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method for modifying appointment on click.
     *
     * @param event
     */
    @FXML
    public void OnClickMod(ActionEvent event) throws IOException {

        Appointments modAppt = TableReport.getSelectionModel().getSelectedItem();

        if(modAppt == null) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment.");
          alert.showAndWait();
          return;
        }

        ModifyAppointmentController.toModAppt = modAppt;

        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/ModifyAppointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("Modify Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method for deleting appointment on click.
     *
     * @param event
     */
    public void OnClickDel(ActionEvent event) {

        selectedAppt = TableReport.getSelectionModel().getSelectedItem();

        if(selectedAppt == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected appointment?");
            Optional<ButtonType> confirm = alert.showAndWait();

            if(confirm.get() == ButtonType.OK) {
                DBAppointments.deleteAppointment(selectedAppt.getApptId());
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Appointment with ID " + selectedAppt.getApptId() + " and type " + selectedAppt.getType() + " deleted.");
                alert2.showAndWait();
                TableReport.setItems(DBAppointments.getAllAppointments());
            }
        }
    }



    /**
     * Will exit to directory upon click.
     * @param actionEvent
     * @throws IOException
     */
    public void OnClickExit(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Directory.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Directory");
        stage.setScene(scene);
        stage.show();
    }


        /**
         * Method for initialing Appointments Screen.
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle){

            apptID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("apptId"));
            title.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
            description.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
            location.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
            contactID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("contactId"));
            type.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
            startTime.setCellValueFactory(new PropertyValueFactory<Appointments, String>("startTime"));
            endTime.setCellValueFactory(new  PropertyValueFactory<Appointments, String>("endTime"));
            custID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("custId"));
            userID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userId"));

            TableReport.setItems(DBAppointments.getAllAppointments());






        }

}

