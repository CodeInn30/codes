import java.util.*;
import java.sql.*;
public class a2q1sa {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/java";
    String user = "postgres";
    String password = "12345";
    
    try {
      Class.forName("org.postgresql.Driver");
      Connection con = DriverManager.getConnection(url, user, password);
      Statement stmt = con.createStatement();
      String Query = "SELECT * FROM students";
      ResultSet rs = stmt.executeQuery(Query);
      System.out.println("Students Details \nID :\t Name : \t Address : \n");
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        System.out.println(String.format("| %s | %s | %s |", id, name, address));
      }
      con.close();
      stmt.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
