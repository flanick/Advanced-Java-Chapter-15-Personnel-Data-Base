import java.sql.*;
import java.util.Scanner;

public class EmployeeUpdater {
    public static void main(String[] args) {
        String empID;         //to hold the Employee ID
        double payRate;       // to hold the new pay rate

        //create a named constant for the URL
        //NOTE:  this value is specific for Java DB
        final String DB_URL = "jdbc:derby:PersonnelDB";

        //create a scanner object for keyboard input
        Scanner keyboard = new Scanner(System.in);

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //create a statement object
            Statement stmt = conn.createStatement();

            //Request the employee id from the user and assign response to the empID variable
            System.out.print("Enter the Employee ID that needs updating: ");
            empID = keyboard.nextLine();

            //display the employee's current data
            if (findAndDisplayEmployee(stmt, empID)) {
                //get the new pay rate
                System.out.print("Enter the new pay rate:  ");
                payRate = keyboard.nextDouble();
                keyboard.nextLine();

                //update the employee with the new payrate
                updatePayRate(stmt, empID, payRate);
            } else {
                //the specified employee id was not found
                System.out.println("That employee is not found.");
            }
            //close the connection
            conn.close();
        }
        catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    /**
     * The finaAndDisplayEmployee method finds a specified employee's data and displays it.
     * The stmt parameter is a Statement object for the database.  The empID parameter
     * is the employee ID to search for.  The method returns true or false
     * indicating whether the employee was found.
     */

    public static boolean findAndDisplayEmployee(Statement stmt, String empID) throws SQLException {
        boolean employeeFound;   //flag

        //create a SELECT statement to get the specified
        //row from the employee table
        String sqlStatement = "SELECT * FROM Employee WHERE empID = '" +
                empID + "'";

        //send the SELECT statement to the DBMS
        ResultSet result = stmt.executeQuery(sqlStatement);

        //display the contents of the result set
        if (result.next()){
            //display the employee
            System.out.println("EmpID: " +
                    result.getString("EmpID"));
            System.out.println("Name: " +
                    result.getString("Name"));
            System.out.println("Position: " +
                    result.getString("Position"));
            System.out.println("HourlyPayRate: " +
                    result.getDouble("HourlyPayRate"));

            //set the flag to indicate the employee was found
            employeeFound = true;
        }
        else {
            //Indicate the employee was not found
            employeeFound = false;
        }
        return  employeeFound;

    }
    /**
     * The updatePayRate method updates a specified employee's pay rate.
     * The stmt parameter is a Statement object for the database.
     * empID is the employee ID for the desired employee.
     * payRate is the employee's new pay rate
     */

    public static void updatePayRate(Statement stmt, String empID, double payRate) throws SQLException{
        //create an update statement to update the payrate
        //for the specified employee ID
        String sqlStatement = "UPDATE Employee " +
                "SET HOURLYPAYRATE = " + Double.toString(payRate) +
                "WHERE EmpID = '" + empID + "'";

        //Send the UPDATE statement to the DBMS
        int rows = stmt.executeUpdate(sqlStatement);

        //display the results
        System.out.println(rows + " row(s) updated.");
    }
}
