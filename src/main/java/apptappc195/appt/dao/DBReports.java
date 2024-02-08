package apptappc195.appt.dao;

import apptappc195.appt.model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBReports {


    /**
     * Method to retrieve info for first (type and month) report.
     */
    public static ObservableList<Reports> getTypeMonthReport() {

        ObservableList<Reports> reportsData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT type, monthname(start) as month_name, count(*) as total from appointments\n" +
                    "group by type, month_name;";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);


            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //get results
                String type = rs.getString("type");
                String monthName = rs.getString("month_name");
                int total = rs.getInt("total");


                Reports r = new Reports(type, monthName, total);
                reportsData.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //print stack trace when error occurs
        }

        return reportsData; //return the list
    }

    /**
     * Method to retrieve info for second (contacts) report.
     */
    public static ObservableList<Reports> getContactsReport(int contactId) {

        ObservableList<Reports> reportsData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM appointments WHERE contact_ID = ?";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //get results
                int apptID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int custID = rs.getInt("Customer_ID");

                Reports r = new Reports(apptID, title, type, description, start.toLocalDateTime(), end.toLocalDateTime(), custID);
                reportsData.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //print stack trace when error occurs
        }

        return reportsData; //return the list
    }

    /**
     * Method to retrieve info for additional (locations) report.
     */
    public static ObservableList<Reports> getLocationsReport() {

        ObservableList<Reports> reportsData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT Location, count(Location) as total FROM client_schedule.appointments\n" +
                    "GROUP BY Location;";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);


            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //get results
                String location = rs.getString("location");
                int total = rs.getInt("total");


                Reports r = new Reports(location, total);
                reportsData.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //print stack trace when error occurs
        }

        return reportsData; //return the list
    }
}


