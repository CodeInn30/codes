// Import required packages
import java.util.*;      // For Scanner class
import java.sql.*;       // For JDBC classes

// Main class
public class a2q2sa {

    public static void main(String[] args) {

        // Database connection details
        String url = "jdbc:postgresql://localhost:5432/java"; // Database URL
        String user = "postgres";                             // Database username
        String pass = "12345";                                 // Database password

        try {
            // Step 1: Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Step 2: Establish connection to database
            Connection con = DriverManager.getConnection(url, user, pass);

            // Step 3: Create Statement object to execute queries
            Statement stmt = con.createStatement();

            // Scanner object to take user input
            Scanner sc = new Scanner(System.in);

            // Taking input from user
            System.out.println("Enter ID:");
            int id = sc.nextInt();
            sc.nextLine();  // Consume leftover newline

            System.out.println("Enter NAME:");
            String name = sc.nextLine();

            System.out.println("Enter ADDRESS:");
            String address = sc.nextLine();

            System.out.println("Enter CONTACT NO:");
            String contact = sc.nextLine();

            // Step 4: Create INSERT query using String.format()
            String insertQuery = String.format(
                    "INSERT INTO employees VALUES(%d, '%s', '%s', '%s');",
                    id, name, address, contact
            );

            // SELECT query to fetch all records
            String selectQuery = "SELECT * FROM employees";

            // Step 5: Execute INSERT query
            stmt.executeUpdate(insertQuery);

            // Step 6: Execute SELECT query
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Display table header
            System.out.println("\nEmployees Details");
            System.out.println("ID\tName\tAddress\tContact No");
            System.out.println("-------------------------------------------");

            // Step 7: Retrieve data from ResultSet
            while (rs.next()) {
                int Id = rs.getInt("id");
                String Name = rs.getString("name");
                String Address = rs.getString("address");
                String Contact = rs.getString("contact");

                System.out.println(Id + "\t" + Name + "\t" + Address + "\t" + Contact);
            }

            // Step 8: Get ResultSet Metadata (Information about table columns)
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            System.out.println("\nColumn Count: " + resultSetMetaData.getColumnCount());
            System.out.println("First Column Name: " + resultSetMetaData.getColumnName(1));
            System.out.println("First Column Data Type: " + resultSetMetaData.getColumnTypeName(1));

            // Step 9: Get Database Metadata (Information about database & driver)
            DatabaseMetaData metaData = con.getMetaData();
            System.out.println("\nDatabase Product Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());

            // Step 10: Close all resources
            rs.close();
            stmt.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
