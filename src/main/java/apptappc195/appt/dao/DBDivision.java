package apptappc195.appt.dao;

import apptappc195.appt.model.Country;
import apptappc195.appt.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivision {

    /**
     * Method to retrieve all entries from the first level Divisions table.
     * @return
     */
    public static ObservableList<Division> getAll() {
        ObservableList<Division> divData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * from first_level_divisions";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                Division d = new Division(divisionId, divisionName, countryId);
                divData.add(d);
            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return divData; //return the list
    }

    /**
     * Method to retrieve Country Divisions.
     * @param countryId
     * @return
     */
    public static ObservableList<Division> getCountryDivisions(int countryId) {
        ObservableList<Division> divData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * from first_level_divisions WHERE country_ID = ?";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                Division d = new Division(divisionId, divisionName, countryId);
                divData.add(d);
            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return divData; //return the list
    }

    /**
     * Method to retrieve just Division IDs.
     * @param Division
     * @return
     */
    public static int getDivisionId(String Division) {

        int divisionId = 0;

        try {

            //sql statement
            String sql = "SELECT * from first_level_divisions WHERE Division = ?";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, Division);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();


            while(rs.next()) {
                 divisionId = rs.getInt("Division_ID");

            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return divisionId; //return the list
    }

    public static Division findDivisionByID(String Division) {

        try {

            //sql statement
            String sql = "SELECT * from first_level_divisions WHERE division = ?";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, Division);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                String divisionName = rs.getString("Division");
                int divisionId = rs.getInt("Division_ID");
                int countryId = rs.getInt("Country_ID");

                Division d = new Division(divisionId, divisionName, countryId);
                return d;
            }

        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return null;
    }
}