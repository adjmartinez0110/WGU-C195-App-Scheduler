package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.dao.DBContacts;
import apptappc195.appt.dao.DBReports;
import apptappc195.appt.model.Appointments;
import apptappc195.appt.model.Contacts;
import apptappc195.appt.model.Reports;
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
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    public TableView TableReport;
    public TableView ContactTReport;
    public TableView LocationReport;

    public TableColumn<Reports, String> type;
    public TableColumn<Reports, Integer> total;
    public TableColumn<Reports, String> month_name;

    public TableColumn<Reports, String>  description;
    public TableColumn<Reports, Integer> custID;
    public TableColumn<Reports, String>  endDTime;
    public TableColumn<Reports, String>  startDTime;
    public TableColumn<Reports, String>  type2;

    public TableColumn<Reports, String>  title;
    public TableColumn<Reports, Integer> apptID;

    public TableColumn<Reports, String> location;
    public TableColumn<Reports, Integer> total2;
    public Button apptExit;
    public ComboBox<Contacts> contactComboBox;


    /**
     * Method for controlling change in combo box.
     * @param actionEvent
     */
    @FXML
    private void onContactChange(ActionEvent actionEvent) {
        Contacts contact = contactComboBox.getValue();
        ContactTReport.setItems(DBReports.getContactsReport(contact.getContactId()));
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
     * Initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactComboBox.setItems(DBContacts.getAll());
        TableReport.setItems(DBReports.getTypeMonthReport());
        LocationReport.setItems(DBReports.getLocationsReport());


        //Type and month table
        type.setCellValueFactory(new PropertyValueFactory<Reports, String>("type"));
        month_name.setCellValueFactory(new PropertyValueFactory<Reports, String>("month_name"));
        total.setCellValueFactory(new PropertyValueFactory<Reports, Integer>("total"));

        //Contacts table
        apptID.setCellValueFactory(new PropertyValueFactory<Reports, Integer>("apptId"));
        title.setCellValueFactory(new PropertyValueFactory<Reports, String>("title"));
        type2.setCellValueFactory(new PropertyValueFactory<Reports, String>("type"));
        description.setCellValueFactory(new PropertyValueFactory<Reports, String>("description"));
        startDTime.setCellValueFactory(new PropertyValueFactory<Reports, String>("startTime"));
        endDTime.setCellValueFactory(new PropertyValueFactory<Reports, String>("endTime"));
        custID.setCellValueFactory(new PropertyValueFactory<Reports, Integer>("custId"));

        //Additional table
        location.setCellValueFactory(new PropertyValueFactory<Reports, String>("location"));
        total2.setCellValueFactory(new PropertyValueFactory<Reports, Integer>("total"));



    }
}
