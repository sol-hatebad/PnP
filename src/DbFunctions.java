import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {

    //Connect to DB
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            // conn = DriverManager.getConnection("jdbc:postgresql://192.168.20.125:5432/" + dbname, user, pass);
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/" + dbname, user, pass);
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

    // Create Table with name entered in Main
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

    // Fill table (name entered in Main) with data
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

    // Read the table (name entered in Main)
    public void read_data(Connection conn, String tbl_name){
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", tbl_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("charid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getInt("age")+" ");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //Update name
    public void update_name(Connection conn, String tbl_name, String old_name, String new_name){
        Statement statement;
        try {
            String query = String.format("update %s set name='%s' where name='%s'", tbl_name, new_name, old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void createCharacter (Connection conn, String tbl_name){
        Statement statement;
        try{
            String query = "create table "+tbl_name+"(" +
                    "charid SERIAL PRIMARY KEY, " +
                    "name varchar(200)," +
                    "gender varchar(200), " +
                    "age INT, " +
                    "stature varchar(200), " +
                    "religion varchar(200), " +
                    "job varchar(200), " +
                    "maritalStatus varchar(200)," +
                    "id_a_skillset INT," +
                    "id_k_skillset INT," +
                    "id_s_skillset INT);";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("table created");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void fill_Characters (Connection conn, String tbl_name, String name, String gender, int age, String stature, String religion, String job, String maritalStatus, int id_a_skillset, int id_k_skillset, int id_s_skillset){
        Statement statement;
        try{
            String query = String.format("insert into %s(name,gender,age,stature,religion,job,maritalStatus,id_a_skillset,id_k_skillset,id_s_skillset) values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",tbl_name,name,gender,age,stature,religion,job,maritalStatus,id_a_skillset,id_k_skillset,id_s_skillset);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void read_characters(Connection conn, String tbl_name){
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", tbl_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println("|charid        : " + rs.getString("charid")+" ");
                System.out.println("|name          : " + rs.getString("name")+" ");
                System.out.println("|gender        : " + rs.getString("gender")+" ");
                System.out.println("|age           : " + rs.getInt("age")+" ");
                System.out.println("|stature       : " + rs.getString("stature")+" ");
                System.out.println("|religion      : " + rs.getString("religion")+" ");
                System.out.println("|job           : " + rs.getString("job")+" ");
                System.out.println("|maritalStatus : " + rs.getString("maritalStatus")+" ");
                System.out.println("--------------------------------------------");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}