module apptappc195.apptappc195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens apptappc195.appt to javafx.fxml;
    exports apptappc195.appt;
    exports apptappc195.appt.controller;
    opens apptappc195.appt.controller to javafx.fxml;
    exports apptappc195.appt.model;
    opens apptappc195.appt.model to javafx.fxml;

}