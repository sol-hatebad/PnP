import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://192.168.20.125:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTable (Connection conn, String tbl_name){
        Statement statement;
        try{
            String query = "create table "+tbl_name+"(charid SERIAL, name varchar(200),gender varchar(200),primary key (charid));";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("table created");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void insert_row(Connection conn, String tbl_name, String name, String gender){
        Statement statement;
        try{
            String query = String.format("insert into %s(name,gender) values('%s','%s');",tbl_name,name,gender);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch (Exception e){
            System.out.println(e);
        }


    }
}