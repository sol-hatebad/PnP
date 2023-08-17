import java.sql.Connection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("pnphelper","postgres","Pauschri1992");
        // db.createTable(conn, "sheet");
        //db.insert_row(conn, "sheet", "Schnee", "female");
        //db.read_data(conn,"sheet");
        //db.update_name(conn, "sheet","Osiah","Oziah");
        //db.read_data(conn,"sheet");
        //db.createCharacter(conn, "Characters");
        db.fill_Characters(conn, "Characters","Relaxo","Male",28,"fluffy","worships sleep","jobless","in love with food and sleep");
        db.read_characters(conn,"Characters");
    }
}
