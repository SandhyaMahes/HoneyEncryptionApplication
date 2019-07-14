/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

/**
 *
 * @author Sandhya
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DB_ACCESS {

    Connection con;
    PreparedStatement pst;
    Statement st, st1;
    ResultSet rs;

    public DB_ACCESS() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_secure", "root", "root");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("Error Inside DataBase class :\nError in DataBaseConstructor\n" + e);
        }
    }

    public Connection Connect_DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://172.17.1.111:3306/chat_secure", "root", "root");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("Error Inside DataBase class :\nError in DataBaseConstructor\n" + e);
        }
        return con;
    }
   
    public int ClosE() {
        int i = 0;
        try {
            con.close();
        } catch (Exception ex) {
            System.out.println("Error Insert DataBase class " + ex);

        }
        return i;
    }

    public int Insert(String Query) {
        int i = 0;
        try {
            con = new DB_ACCESS().con;
            st1 = (Statement) con.createStatement();
            st1.executeUpdate("set global max_connections=200");
            st = con.createStatement();
            i = st.executeUpdate(Query);
            st.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error Insert DataBase class " + ex);

        }
        return i;
    }

    public int Update(String Query) {
        int i = 0;
        try {
            con = new DB_ACCESS().con;
            st1 = (Statement) con.createStatement();
            st1.executeUpdate("set global max_connections=200");
            pst = con.prepareStatement(Query);
            i = pst.executeUpdate(Query);
            pst.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error Insert DataBase class " + ex);

        }
        return i;
    }

    public int Delete(String Query) {
        int i = 0;
        try {
            con = new DB_ACCESS().con;
            st1 = (Statement) con.createStatement();
            st1.executeUpdate("set global max_connections=200");
            pst = con.prepareStatement(Query);
            i = pst.executeUpdate(Query);
            pst.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error Insert DataBase class " + ex);

        }
        return i;
    }

    public ResultSet Select(String Query) {

        try {
            if (st != null && con != null) {
                st.close();
                con.close();
            }
            con = new DB_ACCESS().con;
            st1 = (Statement) con.createStatement();
            st1.executeUpdate("set global max_connections=200");
            st = con.createStatement();
            rs = st.executeQuery(Query);

        } catch (Exception e) {
            System.out.println("Error Select DataBase class " + e);
        }
        return rs;
    }
}
