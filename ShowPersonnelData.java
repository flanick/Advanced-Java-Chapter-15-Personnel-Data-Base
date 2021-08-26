import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

/**
 * This program displays the Personnel data
 */

public class ShowPersonnelData {
    public static void main(String[] args) {
        //create a named constant for the URL
        //NOTE:  This value is specific for JAva DB
        final String DB_URL = "jdbc:derby:PersonnelDB";

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //Create a statement object
            Statement stmt = conn.createStatement();

            //create a string with a SELECT statement
            String sqlStatement = "SELECT * FROM Employee";

            //Send the statement to the DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            //Display a header for the listing
            System.out.println("\tEmployee ID \t\tName \t\t\tPosition \t\t\t\t\tHourly Pay Rate");
            System.out.println("-----------------------------------------------------------------------------------");

            //display the contents of the result set
            //the result set will have three columns
            while (result.next()){
                System.out.printf("%10s %20s \t\t%30s %.2f\n",
                        result.getString("EmpID"),
                        result.getString("Name"),
                        result.getString("Position"),
                        result.getDouble("HourlyPayRate"));
            }
            //close the connection
            conn.close();
        }
        catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}