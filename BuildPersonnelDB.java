import java.sql.*;

/**
 * This program creates a new database of Employee info
 * using Java DB
 */

public class BuildPersonnelDB {

    public static void main(String[] args) {
        final String DB_URL = "jdbc:derby:PersonnelDB;create=true";

        try {
            //create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL);

            //create a statement object
            Statement stmt = conn.createStatement();

            //create the Employee Table
            System.out.println("Creating the Employee Table...");
            stmt.execute("CREATE TABLE Employee (" +
                    "EmpID VarCHAR(10) NOT NULL PRIMARY KEY, " +
                    "Name VARCHAR(30), " +
                    "Position CHAR(30), " +
                    "HourlyPayRate Double )");

            // Insert first employee.
            stmt.execute("INSERT INTO Employee VALUES ( " +
                    "'1', " +
                    "'Jane Doe', " +
                    "'CEO', " +
                    "153.99 )");

            // Insert 2nd employee.
            stmt.execute("INSERT INTO Employee VALUES ( " +
                    "'2', " +
                    "'John Doe', " +
                    "'Vice President', " +
                    "101.99 )");

            // Insert third employee.
            stmt.execute("INSERT INTO Employee VALUES ( " +
                    "'3', " +
                    "'Jake Doe', " +
                    "'IT Officer', " +
                    "97.99 )");

            // Insert 4th employee.
            stmt.execute("INSERT INTO Employee VALUES ( " +
                    "'4', " +
                    "'Amy Doe', " +
                    "'Head Nurse', " +
                    "99.76 )");

            // Insert 5th employee.
            stmt.execute("INSERT INTO Employee VALUES ( " +
                    "'5', " +
                    "'Bailey Doe', " +
                    "'Counselor', " +
                    "87.50 )");

            //CLOSE THE statement and connection
            stmt.close();
            conn.close();
            System.out.println("Done");
        }
        catch (Exception ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
