package apptappc195.appt.dao;

import apptappc195.appt.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomer {

    /**
     * Method for getting all relevant information from Customers table.
     * @return
     */
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> cData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, first_level_divisions.division, countries.country from customers inner join first_level_divisions on first_level_divisions.Division_ID = customers.Division_ID inner join countries on countries.country_id = first_level_divisions.country_id";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String division = rs.getString("Division");
                String country = rs.getString("Country");

                Customer c = new Customer(customerId, customerName, address, postalCode, phone, division, country);
                cData.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return cData; //return the list
    }

    /**
     * Method to get Customer ID.
     */
    public static ObservableList<Customer> getCustomerID(int customerID) {
        ObservableList<Customer> cData = FXCollections.observableArrayList();

        try {

            //sql statement
            String sql = "SELECT * FROM customers";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1,customerID);

            //make the query and store the results
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String division = rs.getString("Division");
                String country = rs.getString("Country");


                Customer c = new Customer(customerId, customerName, address, postalCode, phone, division, country);
                cData.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();  //print stack trace when error occurs
        }

        return cData; //return the list
    }

    /**
     * This is a method to create a new customer.
     */
    public static int createCustomer (String customerName, String address, String postalCode, String phone, int divisionId) {

        int rowsAffected = 0;

        try {
            //Writing the sql
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

            //Prepared Statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            //add in the data to the statement: customerName, address, postalCode, phone, division.getId\
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rowsAffected;
    }

    public static int updateCustomer (String customerName, String address, String postalCode, String phone, int divisionId, int customerId ) {

        int rowsAffected = 0;

        try {
            //Writing the sql
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_Id = ?";

            //Prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rowsAffected;
    }

    public static int deleteCustomer (int customerId) {

        int rowsAffectedA = 0;
        int rowsAffectedB = 0;

        try {

            String sqlA = "DELETE FROM appointments WHERE Customer_Id = ?";
            String sqlB = "DELETE FROM customers WHERE Customer_Id = ?";

            //Prepared statement
            PreparedStatement psA = JDBC.getConnection().prepareStatement(sqlA);

            psA.setInt(1, customerId);

            rowsAffectedA = psA.executeUpdate();


            //Prepared statement
            PreparedStatement psB = JDBC.getConnection().prepareStatement(sqlB);

            psA.setInt(1, customerId);
            psB.setInt(1, customerId);

            rowsAffectedA = psA.executeUpdate();
            rowsAffectedB = psB.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rowsAffectedB;
    }

}
