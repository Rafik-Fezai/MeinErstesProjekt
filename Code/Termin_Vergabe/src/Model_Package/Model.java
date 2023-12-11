package Model_Package;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Diese Klasse beinhaltet verschiedene SQL-Abfragen, die für die Datenbankoperationen verwendet werden.
 * Sie bietet Methoden zum Ausführen von Datenbankabfragen, Durchführen von DML-Operationen
 * und zur Verarbeitung von Ergebnismengen.
 *
 * @author Rafik
 * @version 1.0.0
 */
public class Model {

    public static final String INSERT_RAUM_QUERY = "INSERT INTO raum (raum_nr ,status ) VALUES ('%s','%s')";
    public static  final String SELECT_RAUM_TABLE ="SELECT * FROM raum ";
    public static final String UPDATE_RAUM_NR = "UPDATE raum SET raum_nr='%s' WHERE raum_nr ='%s' ";
    public static final String DELETE_RAUM ="DELETE FROM raum WHERE raum_nr= '%s' ";
    public static final  String SELECT_ARZT = "SELECT TO_CHAR(arzt_id),anrede,titel,vorname,nachname,raum_nr,beruf FROM arzt";
    public static final String SELECT_F_RAUM_NR = "SELECT raum_nr FROM raum WHERE status ='frei'";

    public static final String INSERT_ARZT = "INSERT INTO Arzt (arzt_ID, anrede ,titel , vorname, nachname, raum_nr, beruf) VALUES (seq_arzt.NEXTVAL ,'%s','%s','%s','%s','%s','%s')";
    public static final String DELETE_ARZT ="DELETE FROM arzt WHERE arzt_id='%s'";

    public static final String UPDATE_ARZT =   "  UPDATE arzt set anrede='%s' ,titel = '%s' ,vorname= '%s' ,nachname ='%s' ,raum_nr = '%s' ,beruf = '%s' WHERE arzt_id = %s ";
    public  static final String SELECT_PATIENT = "SELECT TO_CHAR(patient_id) ,anrede ,vorname,nachname ,telefon ,TO_CHAR(geburtsdatum ,'DD.MM.YYYY') ,arzt_id FROM  patient ";
    public static final String DELETE_PATIENT = "DELETE FROM patient WHERE patient_id = %s";
    public static final String INSERT_PATIENT = "INSERT INTO patient (patient_id, anrede, vorname, nachname, telefon, geburtsdatum, arzt_id ) VALUES (seq_patient.NEXTVAL,'%s','%s','%s', '%s' , TO_DATE('%s','DD.MM.YYYY'), %s )";
    public static final String UPDATE_PATIENT = " UPDATE patient SET anrede ='%s' ,vorname ='%s' ,nachname ='%s' ,telefon ='%s' ,geburtsdatum =TO_DATE ('%s','DD.MM.YYYY'),arzt_id =%s WHERE patient_id =%s";
    public static final String SELECT_TERMIN_DATEN = "SELECT * FROM termin_daten ORDER BY TO_DATE(datum,'DD.MM.YYYY') DESC ,TO_DATE(uhrzeit ,'HH24:MI') DESC";
    public static final String PATIENT_SUCHEN  = "SELECT * FROM patient_suche WHERE geburtsdatum = '%s'";
    public static final String TERMIN_SUCHEN = " SELECT * FROM termin_daten WHERE p_geburtsdatum ='%s' AND TO_DATE(datum,'DD.MM.YYYY')  >= TO_DATE(SYSDATE ,'DD.MM.YYYY') ORDER BY  TO_DATE(datum,'DD.MM.YYYY') DESC ,TO_DATE(uhrzeit ,'HH24:MI') DESC";
    public static final  String DELETE_TERMIN = "DELETE FROM termin WHERE termin_id = %s";

    public static final String SELECT_UHRZEIT = "SELECT DISTINCT uhrzeit FROM termin WHERE uhrzeit in (SELECT uhrzeit FROM termin WHERE datum = TO_DATE('%s', 'DD.MM.YYYY')AND patient_id = %s)OR uhrzeit IN (SELECT uhrzeit FROM termin WHERE datum = TO_DATE('%s', 'DD.MM.YYYY') AND arzt_id = %s)";
    public static final String INSERT_TERMIN = "INSERT INTO Termin (termin_id, datum, uhrzeit, status, patient_id, arzt_id) VALUES (seq_termin.NEXTVAL , TO_DATE('%s', 'DD.MM.YYYY'),'%s', null, %s, %s)";

    public  static final String LOGIN_DATA = "SELECT * FROM benutzer ";

    /**
     * Die Liste der möglichen Termin-Uhrzeiten (PTA - Possible Appointments Times).
     * Jeder Termin wird in 15-Minuten-Intervallen geplant.
     * Die Öffnungszeiten der Arztpraxis sind von 8:00 Uhr bis 12:00 Uhr und von 14:00 Uhr bis 17:00 Uhr.
     */
    public static final ArrayList<String> P_A_T = new ArrayList<>(Arrays.asList("8:00", "8:15", "8:30", "8:45", "9:00", "9:15", "9:30", "9:45",
            "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30",
            "11:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15",
            "15:30", "15:45", "16:00", "16:15", "16:30", "16:45"));




    /**
     * Diese Methode führt eine SELECT-Abfrage aus und gibt eine Liste mit Daten zurück. Sie ist geeignet,
     * wenn die SELECT-Abfrage nur Daten einer einzigen Spalte zurückgibt.
     *
     * @param select_query Die SQL-Abfrage, die ausgeführt werden soll.
     * @return Eine Liste mit Ergebnissen aus der Datenbank.
     * @throws SQLException Falls ein Fehler bei der Datenbankabfrage auftritt.
     * @throws ClassNotFoundException Falls der Treiber für die Datenbank nicht gefunden wird.
     */
    public ArrayList<String> select_DB(String select_query)throws SQLException, ClassNotFoundException {

        ResultSet rs = null;
        ArrayList<String> liste = new ArrayList<String>();

        Statement stmt = DB_Connection.getConnection().createStatement();
        rs = stmt.executeQuery(select_query);

        while (rs.next()) {
            liste.add(rs.getString(1));
        }
        DB_Connection.closeConnection(DB_Connection.getConnection());
        return liste;
    }

    /**
     * Führt eine SELECT-Abfrage aus und gibt eine Tabelle mit Daten zurück.
     *
     * @param select_query Die auszuführende SQL-Abfrage.
     * @param anzahlSpalten Die erwartete Anzahl von Spalten, die von der SELECT-Abfrage zurückgegeben werden sollen.
     * @return Eine Tabelle mit Daten aus der Datenbank repräsentiert als Liste von String-Arrays.
     * @throws SQLException Falls ein Fehler bei der Datenbankabfrage auftritt.
     * @throws ClassNotFoundException Falls der Treiber für die Datenbank nicht gefunden wird.
     */
    public ArrayList<String[]> select_DB(String select_query , int anzahlSpalten) throws SQLException, ClassNotFoundException {


        ResultSet rs = null;
        ArrayList<String[]> tabelle = new ArrayList<String[]>();

        Statement stmt = DB_Connection.getConnection().createStatement();
        rs = stmt.executeQuery(select_query);

        while (rs.next()) {
            String []datensatz = new String[anzahlSpalten];
            for (int i = 1; i <=anzahlSpalten; i++) {
                datensatz[i-1]= rs.getString(i);
            }
            tabelle.add(datensatz);
        }
        DB_Connection.closeConnection(DB_Connection.getConnection());
        return tabelle;
    }



    /**
     * Führt eine DML-Operation aus und gibt die Anzahl der geänderten Zeilen zurück.
     *
     * @param query Die DML-Operation, die ausgeführt werden soll.
     * @return Die Anzahl der Zeilen, die von der Operation geändert wurden.
     * @throws SQLException Falls ein Fehler bei der Datenbankabfrage auftritt.
     * @throws ClassNotFoundException Falls der Treiber für die Datenbank nicht gefunden wird.
     */
    public int dmlOperation (String query )throws SQLException ,ClassNotFoundException{

        Statement stmt =DB_Connection.getConnection().createStatement();
        int rows_Changed  =stmt.executeUpdate(query);
        stmt.close();
        DB_Connection.closeConnection(DB_Connection.getConnection());

        return rows_Changed;

    }





}
