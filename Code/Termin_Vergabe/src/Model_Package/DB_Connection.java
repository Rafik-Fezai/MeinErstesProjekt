package Model_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Stellt eine Verbindung zur Oracle-Datenbank her und bietet Methoden zum Öffnen und Schließen einer Verbindung.
 * @author Rafik
 * @version 1.0.0
 */
public class DB_Connection {

    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    public static final String USER = "C##CESAR";
    public static final String PASSWORD = "0000";

    /**
     * Öffnet eine Verbindung zur Datenbank, indem zuerst der JDBC-Treiber geladen wird, der den Datenaustausch
     * zwischen der Java-Anwendung und der Datenbank ermöglicht, und anschließend wird die Verbindung hergestellt.
     *
     * @return Eine Verbindung zur Datenbank.
     * @throws SQLException Falls ein Fehler bei der Verbindung zur Datenbank auftritt.
     * @throws ClassNotFoundException Falls der Treiber für die Datenbank nicht gefunden wird.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    /**
     * Schließt die angegebene Datenbankverbindung, sofern sie bereits geöffnet wurde.
     *
     * @param connection Die zu schließende Verbindung.
     * @throws SQLException Falls ein Fehler beim Schließen der Verbindung auftritt.
     */
    public static void closeConnection(Connection connection) throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }
}