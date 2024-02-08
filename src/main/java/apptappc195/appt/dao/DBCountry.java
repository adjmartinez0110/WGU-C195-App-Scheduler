package apptappc195.appt.dao;

import apptappc195.appt.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountry {
    public static ObservableList<Country> getAll() {
        ObservableList<Country> list = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * from countries";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                String countryName = rs.getString("Country");
                int countryId = rs.getInt("Country_ID");

                Country d = new Country(countryId, countryName);
                list.add(d);
            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return list; //return the list
    }


        public static Country findCountryByID(String Country) {

            try {

                //sql statement
                String sql = "SELECT * from countries WHERE country = ?";

                //Prepared Statement
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setString(1, Country);

                //make the query and store the results
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {

                    String countryName = rs.getString("Country");
                    int countryId = rs.getInt("Country_ID");

                    Country d = new Country(countryId, countryName);
                    return d;
                }

            } catch (SQLException e) {
                e.printStackTrace();  //print stack trace when error occurs
            }

            return null;
        }
}
