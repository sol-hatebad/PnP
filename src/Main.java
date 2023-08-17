import java.sql.Connection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("pnphelper","postgres","Pauschri1992");
        // db.createTable(conn, "sheet");
        db.insert_row(conn, "sheet", "Osiah", "halfdemon");
    }
}
