import java.util.*;
import java.sql.*;

public class a2q4sa {
  public static void main (String[] args) {
    String url = "jdbc:postgresql://localhost:5432/java";
    String user = "postgres";
    String pass = "12345";
    
    try {
      Class.forName("org.postgresql.Driver");
      Connection con = DriverManager.getConnection(url, user, pass);
      Statement stmt = con.createStatement();
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a No to Insert No of records");
      int num = sc.nextInt();
      sc.nextLine();
      for (int i = 1; i <= num; i++) {
        System.out.println("Employees " + i + " Details");
        System.out.println("Enter a ID");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter a Name");
        String name = sc.nextLine();
        System.out.println("Enter a Address");
        String address = sc.nextLine();
        System.out.println("Enter a Contact");
        String contact = sc.nextLine();
        String insertQuery = "INSERT INTO employees VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(insertQuery);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, address);
        ps.setString(4, contact);
        ps.executeUpdate();
      }
      
      String SelectQuery = "SELECT * FROM employees";
      ResultSet rs = stmt.executeQuery(SelectQuery);
      System.out.println("Employees Details \nID :\t Name : \t Address : \t Contact No : \n");
      while (rs.next()) {
        int Id = rs.getInt("id");
        String Name = rs.getString("name");
        String Address = rs.getString("address");
        String Contact = rs.getString("contact");
        System.out.println(String.format("| %s | %s | %s | %s |", Id, Name, Address, Contact));
      }
      con.close();
      stmt.close();
      ps.close();
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
