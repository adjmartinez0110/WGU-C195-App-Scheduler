package apptappc195.appt.dao;

import apptappc195.appt.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {

    /**
     * Method for get all info from Users table.
     * @return
     */
    public static ObservableList<Users> getAll() {
        ObservableList<Users> list = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * from users";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users u = new Users(userId, userName, password);
                list.add(u);
            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return list; //return the list
    }

    /**
     * Method for getting just the user ID.
     * @param userID
     * @return
     */
    public static ObservableList<Users> getUserID(int userID) {
        ObservableList<Users> list = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * from users";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,userID);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users u = new Users(userId, userName, password);
                list.add(u);
            }


        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return list; //return the list
    }
}
