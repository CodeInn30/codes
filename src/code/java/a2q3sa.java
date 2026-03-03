import java.util.*;
import java.sql.*;

public class a2q3sa {
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
        System.out.println("Student " + i + " Details");
        System.out.println("Enter a ID");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter a Name");
        String name = sc.nextLine();
        System.out.println("Enter a Address");
        String address = sc.nextLine();
        String insertQuery = String.format("INSERT INTO students VALUES (%s, '%s', '%s');",id, name, address);
        stmt.executeUpdate(insertQuery);
      }
      String selectQuery = "SELECT * FROM students";
      ResultSet rs = stmt.executeQuery(selectQuery);
      System.out.println("Students Details \nID :\t Name : \t Address : \n");
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        System.out.println(String.format("| %s | %s | %s |", id, name, address));
      }
      con.close();
      stmt.close();
      sc.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
