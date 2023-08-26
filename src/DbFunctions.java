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

    // creating the fundamental tables with relations
    public void createBasetables (Connection conn){
        Statement statement;
        try{
            String query_characterbasics = "create table characterbasics(" +
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
            statement.executeUpdate(query_characterbasics);

            System.out.println("table characterbasics created");
            String query_action = "create table actionskills(" +
                    "id_a_skillset SERIAL PRIMARY KEY, " +
                    "actionskill01 varchar(200)," +
                    "actionskill02 varchar(200), " +
                    "actionskill03 varchar(200), " +
                    "actionskill04 varchar(200), " +
                    "actionskill05 varchar(200), " +
                    "actionskill06 varchar(200), " +
                    "actionskill07 varchar(200)," +
                    "actionskill08 varchar(200)," +
                    "actionskill09 varchar(200)," +
                    "actionskill10 varchar(200));";
            statement=conn.createStatement();
            statement.executeUpdate(query_action);
            System.out.println("table actionskills created");

            String query_knowledge = "create table knowledgeskills(" +
                    "id_k_skillset SERIAL PRIMARY KEY, " +
                    "knowledgeskill01 varchar(200)," +
                    "knowledgeskill02 varchar(200), " +
                    "knowledgeskill03 varchar(200), " +
                    "knowledgeskill04 varchar(200), " +
                    "knowledgeskill05 varchar(200), " +
                    "knowledgeskill06 varchar(200), " +
                    "knowledgeskill07 varchar(200)," +
                    "knowledgeskill08 varchar(200)," +
                    "knowledgeskill09 varchar(200)," +
                    "knowledgeskill10 varchar(200));";
            statement=conn.createStatement();
            statement.executeUpdate(query_knowledge);
            System.out.println("table knowledgeskills created");

            String query_socialskills = "create table socialskills(" +
                    "id_s_skillset SERIAL PRIMARY KEY, " +
                    "socialskill01 varchar(200)," +
                    "socialskill02 varchar(200), " +
                    "socialskill03 varchar(200), " +
                    "socialskill04 varchar(200), " +
                    "socialskill05 varchar(200), " +
                    "socialskill06 varchar(200), " +
                    "socialskill07 varchar(200)," +
                    "socialskill08 varchar(200)," +
                    "socialskill09 varchar(200)," +
                    "socialskill10 varchar(200));";
            statement=conn.createStatement();
            statement.executeUpdate(query_socialskills);
            System.out.println("table socialskills created");

            //Adding constraints (PK and FK)
            String query_pkfk_action = "ALTER TABLE characterbasics " +
                    "ADD CONSTRAINT fk_action " +
                    "FOREIGN KEY (id_a_skillset) " +
                    "REFERENCES actionskills (id_a_skillset);";
            statement=conn.createStatement();
            statement.executeUpdate(query_pkfk_action);

            String query_pkfk_knowledge = "ALTER TABLE characterbasics " +
                    "ADD CONSTRAINT fk_knowledge " +
                    "FOREIGN KEY (id_k_skillset) " +
                    "REFERENCES knowledgeskills (id_k_skillset);";
            statement=conn.createStatement();
            statement.executeUpdate(query_pkfk_knowledge);

            String query_pkfk_social = "ALTER TABLE characterbasics " +
                    "ADD CONSTRAINT fk_social " +
                    "FOREIGN KEY (id_s_skillset) " +
                    "REFERENCES socialskills (id_s_skillset);";
            statement=conn.createStatement();
            statement.executeUpdate(query_pkfk_social);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void fill_Characters (Connection conn, String tbl_name, String name, String gender, int age, String stature, String religion, String job, String maritalStatus, int id_a_skillset, int id_k_skillset, int id_s_skillset){
        Statement statement;
        try{
            String query = String.format("insert into %s(name,gender,age,stature,religion,job,maritalStatus,id_a_skillset,id_k_skillset,id_s_skillset) " +
                                        "values('%s','%s','%d','%s','%s','%s','%s','%d','%d','%d');",
                                        tbl_name,name,gender,age,stature,religion,job,maritalStatus,id_a_skillset,id_k_skillset,id_s_skillset);
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