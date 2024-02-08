package apptappc195.appt.dao;

import apptappc195.appt.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;



public class DBAppointments {

    /**
     * Method to retrieve all entries from the Appointments table.
     */
    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> apptsData = FXCollections.observableArrayList();

        try {

            //sql statement
              String sql = "SELECT * FROM appointments";
            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);


            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results
                int apptId = rs.getInt( "Appointment_ID");
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_Id");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");

               Appointments a = new Appointments(apptId, custId, userId, title, description, location, contactId, type, start.toLocalDateTime(), end.toLocalDateTime());
               apptsData.add(a);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //print stack trace when error occurs
        }

        return apptsData; //return the list
    }

    public static int createAppointment (String title, String description, String location, int contactId, String type, LocalDateTime start, LocalDateTime end, int custId, int userId) {

        int rowsAffected = 0;

        try {
            //Writing the sql
            String sql = "INSERT INTO appointments (Title, Description, Location, Contact_Id, Type, Start, End, Customer_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setInt(4, contactId);
            ps.setString(5, type);
            ps.setTimestamp(6, Timestamp.valueOf(start));
            ps.setTimestamp(7, Timestamp.valueOf(end));
            ps.setInt(8, custId);
            ps.setInt(9, userId);


            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rowsAffected;
    }

    public static int updateAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int apptId) {

        int rowsAffected = 0;

        try {
            //Writing the sql
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ? WHERE Appointment_ID =?";

            //Prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, apptId);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rowsAffected;
    }

    public static int deleteAppointment(int appointmentId) {

        int rowsAffected = 0;

        try {

            //Writing the sql
            String sql = "DELETE FROM appointments WHERE Appointment_Id = ?";

            //Prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, appointmentId);


            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rowsAffected;
    }

    /**
     * Method to retrieve appointment types.
     */
    public static ObservableList<Appointments> getType() {

        ObservableList<Appointments> apptsData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT appointments.Type FROM appointments";
            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);


            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results

                String type = rs.getString("Type");


                Appointments a = new Appointments(type);
                apptsData.add(a);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //print stack trace when error occurs
        }

        return apptsData; //return the list
    }
}

