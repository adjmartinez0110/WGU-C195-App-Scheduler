package apptappc195.appt.dao;

import apptappc195.appt.model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

    /**
     * Method to retrieve all entities from the Contacts table.
     * @return
     */
    public static ObservableList<Contacts> getAll() {
        ObservableList<Contacts> list = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * from contacts";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts c = new Contacts(contactId, contactName, email);
                list.add(c);
            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return list; //return the list
    }



}

