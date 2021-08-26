import java.util.Scanner;
import java.sql.*;

/**
 * This program lets the user insert a row into hte
 * PersonnelDB database's Employee table
 */

public class EmployeeInserterWithScanner {
    public static void main(String[] args) {
        String empID;        //to hold the Employee ID
        String name;         //to hold the Employee name
        String position;     //to hold the employee's position
        double payRate;      //to hold the hourly pay rate

        //create a named constant for the URL
        //NOTE:  this value is specific for Java DB
        final String DB_URL = "jdbc:derby:PersonnelDB";

        //create a scanner object for keyboard input
        Scanner keyboard = new Scanner(System.in);

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //get the data for the new employee
            System.out.print("Enter the Employee ID of the new employee: ");
            empID = keyboard.nextLine();
            System.out.print("Enter the Name of the new employee: ");
            name = keyboard.nextLine();
            System.out.print("Enter the Position of the new employee: ");
            position = keyboard.nextLine();
            System.out.print("Enter the hourly pay rate for the new employee: ");
            payRate = keyboard.nextDouble();

            //create a statement object
            Statement stmt = conn.createStatement();

            //create a string with an INSERT statment
            String sqlStatement = "INSERT INTO Employee VALUES ('" + empID +"', '" + name + "', '" + position + "'," + payRate + ")";

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
