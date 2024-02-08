package apptappc195.appt.controller;

import apptappc195.appt.LoginMain;
import apptappc195.appt.dao.DBAppointments;
import apptappc195.appt.model.Appointments;
import apptappc195.appt.model.Users;
import apptappc195.appt.dao.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.time.Instant;


public class LoginMainController implements Initializable {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("lang");

    @FXML
    private Label Welcome;

    @FXML
    private Label Enter;
    @FXML
    private Label your;
    @FXML
    private Label credentials;
    @FXML
    private Label to;
    @FXML
    private Label initiate;
    @FXML
    public Label session;

    @FXML
    private Label User;
    @FXML
    private Label Password;

    @FXML
    private Button Login;
    @FXML
    public Button loginExit;

    @FXML
    private Label ZoneInfo;

    @FXML
    public TextField userNameBox;

    @FXML
    public TextField passwordBox;

    ZoneId zoneId = ZoneId.systemDefault();


    /**
     * Will move to the next screen if correct credentials are entered.
     *
     * @param event
     */
    @FXML
    void OnActLogin(ActionEvent event) throws IOException {

        String userName = userNameBox.getText();
        String password = passwordBox.getText();
        LocalDate currentDate = LocalDate.now();
        Instant login_timestamp = Instant.now();

        ObservableList<Users> allUsers = DBUsers.getAll();
        boolean userExists = false;

        try {
            if (Objects.equals(userName, "") || userName == null) {

                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Le nom d'utilisateur ne peut pas être vide.");  //french
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Username cannot be empty.");
                    alert.showAndWait();
                }
            }

            if (Objects.equals(password, "") || password == null) {

                if (Locale.getDefault().getLanguage().equals("fr")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Le mot de passe ne peut pas être vide.");  //french
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Password cannot be empty.");
                    alert.showAndWait();
                }
            }

            if (userName.trim().length() > 0 && password.trim().length() > 0) {
                for (Users user : allUsers) {
                    if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                        userExists = true;

                        try {
                            FileWriter theWriter = new FileWriter("login_activity.txt", true);
                            BufferedWriter theBuffWriter = new BufferedWriter(theWriter);

                            //Record to append to
                            String record = "User " + userName + " has successfully logged in on " + currentDate + " with timestamp of " + login_timestamp + ".";

                            theBuffWriter.write(record);
                            theBuffWriter.newLine();
                            theBuffWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                            FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/Directory.fxml"));
                            Scene scene = new Scene(fxmlLoader.load());
                            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            stage.setTitle("Directory");
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }

                if (!userExists) {

                    if (Locale.getDefault().getLanguage().equals("fr")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Identifiant invalide");  //french
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Login.");
                        alert.showAndWait();

                        try {
                            FileWriter theWriter = new FileWriter("login_activity.txt", true);
                            BufferedWriter theBuffWriter = new BufferedWriter(theWriter);

                            //Record to append to
                            String record = "There was an invalid login attempt of user " + userName + " on " + currentDate + " with timestamp of " + login_timestamp + ".";

                            theBuffWriter.write(record);
                            theBuffWriter.newLine();
                            theBuffWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exits the application.
     * @param actionEvent
     */
    @FXML
    public void OnActExit(ActionEvent actionEvent) {
        if (Locale.getDefault().getLanguage().equals("fr")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Souhaitez-vous quitter l'application?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.get() == ButtonType.OK) {
                System.exit(0);
            }
        }
        else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to exit the application?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            System.exit(0);
        }
        }
    }

    /**
     * Method for initializing the Login Main screen. Will display page in French depending on local system's language settings upon login.
     *
     * FUTURE ENHANCEMENT: There are several pieces of code scattered throughout that could prove to be redundant or unnecessary. As my understanding of the topic increases,
     * I can come back and improve the application by removing those bits of code and finding ways to make the application quicker and more efficient.
     *
     * LAMBDAS: Both lambdas in this program are found in the Appointments controller in the OnActMonth and OnActWeek methods respectively.
     * These are done to make the program more streamlined.
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {


        ZoneInfo.setText(zoneId.toString());

        if (Locale.getDefault().getLanguage().equals("fr")) {

            Welcome.setText(resourceBundle.getString("Welcome"));
            Enter.setText(resourceBundle.getString("Enter"));
            your.setText(resourceBundle.getString("your"));
            credentials.setText(resourceBundle.getString("credentials"));
            to.setText(resourceBundle.getString("to"));
            initiate.setText(resourceBundle.getString("initiate"));
            session.setText(resourceBundle.getString("session"));

            User.setText(resourceBundle.getString("User"));
            Password.setText(resourceBundle.getString("Password"));

            Login.setText(resourceBundle.getString("Login"));
            loginExit.setText(resourceBundle.getString("Exit"));

        }
    }
}






