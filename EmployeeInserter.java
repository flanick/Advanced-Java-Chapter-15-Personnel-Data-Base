import java.sql.*;

/**
 * This program inserts a row into hte
 * PersonnelDB database's Employee table
 */

public class EmployeeInserter {
    public static void main(String[] args) {

        //create a named constant for the URL
        //NOTE:  this value is specific for Java DB
        final String DB_URL = "jdbc:derby:PersonnelDB";

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //create a statement object
            Statement stmt = conn.createStatement();

            //create a string with an INSERT statement
            String sqlStatement = "INSERT INTO Employee VALUES  ('6', 'Damon Flanick', 'Cutest Cat', 23.00)";

            //send the statement to the DBMS
            int rows = stmt.executeUpdate(sqlStatement);

            //display the results
            System.out.println(rows + " row(s) added to the table.");

            //close the connection
            conn.close();
        }
        catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
