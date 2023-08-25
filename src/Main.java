import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("pnphelper","postgres","Pauschri1992");
        // db.createTable(conn, "sheet");
        //db.insert_row(conn, "sheet", "Schnee", "female");
        //db.read_data(conn,"sheet");
        //db.update_name(conn, "sheet","Osiah","Oziah");
        //db.read_data(conn,"sheet");
        db.createCharacter(conn, "Characters");
        db.fill_Characters(conn, "Characters","Relaxo","male",28,"fluffy","worships sleep","jobless","in love with food and sleep",1,1,1);
        db.fill_Characters(conn, "Characters","Osiah","male/demon",16,"masculine","none","mercenary","single",2,2,2);
        db.fill_Characters(conn, "Characters","Schnee","female",23,"skinny","strictly celtic","guardian of shirtak","married with my religion",3,3,3);
        db.read_characters(conn,"Characters");
    }
}