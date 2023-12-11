package View_Package;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


/**
 * Eine benutzerdefinierte Tabelle, die von der Klasse {@link javax.swing.JTable} erbt und die Datenverwaltung für die Anzeige übernimmt.
 *
 *@author Rafik
 *@version 1.0.0
 */
public class Table extends JTable {
    /**
     * Das Standard-TableModel für die Tabelle, das die Datenstruktur der Tabelle verwaltet.
     */
    private DefaultTableModel default_Table_Model;

    /**
     * Erstellt eine Tabelle mit angegebenen Spaltenüberschriften.
     * @param columnNames Ein String Array von Spaltenüberschriften für die Tabelle.
     *
     */
   public Table(String[] columnNames){

       default_Table_Model = new DefaultTableModel(new String [][]{}, columnNames);
       setModel(default_Table_Model);
       setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Erlaubt die Auswahl einzelner Zeilen
       setDefaultEditor(Object.class,null); // Deaktiviert das Editieren der Zellen
   }

    /**
     * Aktualisiert die Daten in der Tabelle mit neuen Datensätzen.
     * Falls bereits Datensätze vorhanden sind, werden diese gelöscht, bevor die neuen Datensätze eingefügt werden.
     * @param table Eine Liste von String-Arrays, die die neuen Datensätze repräsentieren, die in die Tabelle eingefügt werden sollen.
     */
    public  void updateData(ArrayList<String[]> table ){

        if(default_Table_Model.getRowCount()>0){

            for (int i = default_Table_Model.getRowCount() - 1; i > -1; i--) {
                default_Table_Model.removeRow(i);
            }
        }
        for ( String [] row :table) {
            default_Table_Model.addRow(row);
        }
    }
    /**
     * Gibt das Standard-TableModel für diese Tabelle zurück.
     * @return Das Standard-TableModel für die Tabelle.
     */
    public DefaultTableModel getDefault_Table_Model() {
        return default_Table_Model;
    }
}
