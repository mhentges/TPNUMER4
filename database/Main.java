/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author ychabcho
 */
import java.sql.*;

public class Main
{
  Connection conn;

  public static void main(String[] args)
  {
    new Main();
  }

  public Main()
  {
    try
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String url = "jdbc:mysql://localhost/coffeebreak";
      conn = DriverManager.getConnection(url, "root", "root");
      doTests();
      conn.close();
    }
    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
    catch (SQLException ex)           {System.err.println(ex.getMessage());}
  }



  private void doTests()
  {
    doSelectTest();

    doInsertTest();  doSelectTest();
    doUpdateTest();  doSelectTest();
    doDeleteTest();  doSelectTest();
  }

  private void doSelectTest()
  {
    System.out.println("[OUTPUT FROM SELECT]");
    String query = "SELECT product, price FROM coffees";
    try
    {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next())
      {
        String s = rs.getString("product");
        float n = rs.getFloat("price");
        System.out.println(s + "   " + n);
      }
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }

  private void doInsertTest()
  {
    System.out.print("\n[Performing INSERT] ... ");
    try
    {
      Statement st = conn.createStatement();
      st.executeUpdate("INSERT INTO coffees " +
                       "VALUES ('chocolat', 5),('cappucino',6)");
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }

  private void doUpdateTest()
  {
    System.out.print("\n[Performing UPDATE] ... ");
    try
    {
      Statement st = conn.createStatement();
      st.executeUpdate("UPDATE coffees SET PRICE=6 WHERE product='chocolat'");
      st.executeUpdate("UPDATE coffees SET PRICE=7 WHERE product='cappucino'");
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }

  private void doDeleteTest()
  {
    System.out.print("\n[Performing DELETE] ... ");
    try
    {
      Statement st = conn.createStatement();
       st.executeUpdate("DELETE FROM coffees WHERE price>6");
       st.executeUpdate("TRUNCATE TABLE coffees");
    }
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }
}
