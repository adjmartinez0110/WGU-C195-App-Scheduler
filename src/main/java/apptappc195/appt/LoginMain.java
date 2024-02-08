package apptappc195.appt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;

public class LoginMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("/apptappc195/views/LoginMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        Locale userLocale = Locale.getDefault();

        if (userLocale.getLanguage().equals(Locale.FRENCH.getLanguage())) {
            Locale.setDefault(new Locale("fr"));
        }
        else {
            Locale.setDefault(Locale.ENGLISH);
        }
        launch();
    }
}