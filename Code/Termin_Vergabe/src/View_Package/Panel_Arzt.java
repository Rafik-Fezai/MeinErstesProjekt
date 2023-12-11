package View_Package;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Das Panel_Arzt ist ein JPanel, das eine Benutzeroberfläche zur Verwaltung von Arztdaten bereitstellt.
 * Es ist als Container für GUI-Elemente konzipiert und wird als ContentPane in einem JFrame verwendet,
 * um die verschiedenen Komponenten zur Anzeige und Bearbeitung von Arztdaten darzustellen.
 */
public class Panel_Arzt extends JPanel implements Chameleon {

    private JLabel lb_arztID;
    private JTextField tf_arztID ;
    private JLabel lb_titel ;
    private JLabel lb_anrede;
    private JLabel lb_vorname ;
    private JLabel lb_nachname ;
    private JLabel lb_raumNr ;
    private JLabel lb_beruf;
    private  JTextField tf_vorname;
    private  JTextField tf_nachname ;
    private JComboBox cb_raumNr;
    private  JComboBox  cb_beruf ;
    private JComboBox cb_anrede;
    private JComboBox cb_titel;
    private JButton btn_add ;
    private JButton btn_clear ;
    private JButton btn_update ;
    private JScrollPane scrollPane;
    private  Table  arztTable;

    /**
     * Konstruktor für Panel_Arzt. Definiert und initialisiert die grafischen Komponenten für die Verwaltung der Arztdaten.
     */
    public Panel_Arzt() {

        setLayout(null);
        setBorder(new EmptyBorder(5,5,5,5));

        //Label Arzt-ID
        lb_arztID = new JLabel("Arzt-ID:");
        lb_arztID.setBounds(30,30,100,25);
        add(lb_arztID);

        //Textfeld Arzt-ID
        tf_arztID = new JTextField(20);
        tf_arztID.setBounds(130,30,100,25);
        tf_arztID.setEditable(false);
        add(tf_arztID);

        //Label Anrede
        lb_anrede = new JLabel("Anrede:*");
        lb_anrede.setBounds(30,90,100,25);
        add(lb_anrede);

        //Combobox Anrede
        String [] anredeArry ={"--","Frau","Herr"};
        cb_anrede = new JComboBox(anredeArry);
        cb_anrede.setBounds(130,90,100,25);
        add(cb_anrede);

        // Label Titel
        lb_titel = new JLabel("Titel:*");
        lb_titel.setBounds(30,150,100,25);
        add(lb_titel);

        // Combobox Titel
        String [] titelArray = {"--","Dr.med" ,"Prof.Dr.med"};
        cb_titel = new JComboBox(titelArray);
        cb_titel.setBounds(130,150,100,25);
        add(cb_titel);

        //Label Vorname
        lb_vorname = new JLabel("Vorname:*");
        lb_vorname.setBounds(30,210,100,25);
        add(lb_vorname);

        //Texfeld Vorname
        tf_vorname = new JTextField(20);
        tf_vorname.setBounds(130,210,100,25);
        add(tf_vorname);

        // Label Nachname
        lb_nachname = new JLabel("Nachname:*");
        lb_nachname.setBounds(30,270,100,25);
        add(lb_nachname);

        //Textfeld Nachname
        tf_nachname = new JTextField(20);
        tf_nachname.setBounds(130,270,100,25);
        add(tf_nachname);

        //Label Raum Nummer
        lb_raumNr = new JLabel("RaumNr:*");
        lb_raumNr.setBounds(30,330,100,25);
        add(lb_raumNr);

        //Combobox Raumnummer
        cb_raumNr= new JComboBox();
        cb_raumNr.setBounds(130,330,100,25);
        add(cb_raumNr);

        //Label Beruf
        lb_beruf = new JLabel("Beruf:*");
        lb_beruf.setBounds(30,390,100,25);
        add(lb_beruf);

        //Combobox  Beruf
        String[] berufArray = {"--", "Hausarzt", "Facharzt", "Chirurg", "Kinderarzt", "Gynäkologe", "Augenarzt", "HNO-Arzt", "Radiologe", "Anästhesist", "Psychiater", "Onkologe", "Geriater", "Notarzt"};

        cb_beruf = new JComboBox(berufArray);
        cb_beruf.setBounds(130,390,100,25);
        add(cb_beruf);

        //Button hinzufügen
        btn_add = new JButton("Arzt einfügen");
        btn_add.setBounds(30,450,220,25);
        add(btn_add);

        //Button Löschen
        btn_clear = new JButton("Selektierten Arzt löschen");
        btn_clear.setBounds(30,510,220,25);
        add(btn_clear);

        //Button Aktualisieren
        btn_update = new JButton("Arztdaten ändern");
        btn_update.setBounds(30,570,220,25);
        add(btn_update);

        // JTable
        arztTable = new Table( new String []{"Arzt-ID","Anrede","Titel","Vorname","Nachname","RaumNr","Beruf"});

        //ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(280,30,690,690);
        scrollPane.setViewportView(arztTable);
        add(scrollPane);
    }
    /**
     * Verändert die Sichtbarkeit der Komponenten basierend auf dem übergebenen Modus.
     * Je nach Modus zeigt oder verbirgt diese Methode verschiedene Elemente des Arzt-Panels.
     * Die Modi "add", "delete", "update" und "list" ändern die Sichtbarkeit entsprechend.
     */
    @Override
    public void changeVisibility(String modus ){
       switch (modus){
           case "add" :
              lb_arztID.setVisible(false);
              lb_anrede.setVisible(true);
              lb_titel.setVisible(true);
              lb_vorname.setVisible(true);
              lb_nachname.setVisible(true);
              lb_raumNr.setVisible(true);
              lb_beruf.setVisible(true);
              tf_arztID.setVisible(false);
              cb_anrede.setVisible(true);
              cb_titel.setVisible(true);
              tf_vorname.setVisible(true);
              tf_nachname.setVisible(true);
              cb_raumNr.setVisible(true);
              cb_beruf.setVisible(true);
              btn_add.setVisible(true);
              btn_update.setVisible(false);
              btn_clear.setVisible(false);
              scrollPane.setVisible(false);
              setPlaceHolder();
              break;

           case "delete":
               lb_arztID.setVisible(false);
               lb_anrede.setVisible(false);
               lb_titel.setVisible(false);
               lb_vorname.setVisible(false);
               lb_nachname.setVisible(false);
               lb_raumNr.setVisible(false);
               lb_beruf.setVisible(false);
               tf_arztID.setVisible(false);
               cb_anrede.setVisible(false);
               cb_titel.setVisible(false);
               tf_vorname.setVisible(false);
               tf_nachname.setVisible(false);
               cb_raumNr.setVisible(false);
               cb_beruf.setVisible(false);
               btn_add.setVisible(false);
               btn_update.setVisible(false);
               btn_clear.setVisible(true);
               scrollPane.setVisible(true);
               break;

           case "update":
               lb_arztID.setVisible(false);
               lb_anrede.setVisible(false);
               lb_titel.setVisible(false);
               lb_vorname.setVisible(false);
               lb_nachname.setVisible(false);
               lb_raumNr.setVisible(false);
               lb_beruf.setVisible(false);
               tf_arztID.setVisible(false);
               cb_anrede.setVisible(false);
               cb_titel.setVisible(false);
               tf_vorname.setVisible(false);
               tf_nachname.setVisible(false);
               cb_raumNr.setVisible(false);
               cb_beruf.setVisible(false);
               btn_add.setVisible(false);
               btn_update.setVisible(false);
               btn_clear.setVisible(false);
               scrollPane.setVisible(true);
               break;

           case "list":
               lb_arztID.setVisible(false);
               lb_anrede.setVisible(false);
               lb_titel.setVisible(false);
               lb_vorname.setVisible(false);
               lb_nachname.setVisible(false);
               lb_raumNr.setVisible(false);
               lb_beruf.setVisible(false);
               tf_arztID.setVisible(false);
               cb_anrede.setVisible(false);
               cb_titel.setVisible(false);
               tf_vorname.setVisible(false);
               tf_nachname.setVisible(false);
               cb_raumNr.setVisible(false);
               cb_beruf.setVisible(false);
               btn_add.setVisible(false);
               btn_update.setVisible(false);
               btn_clear.setVisible(false);
               scrollPane.setVisible(true);
               break;
       }
    }
    /**
     * Setzt das Datenmodell für die JComboBox der Raumnummern basierend auf der übergebenen Liste von freien Raumnummern, die noch nicht einem Arzt zugewiesen wurden.
     *
     * @param raumNrList Die Liste der freien Raumnummern, die in die JComboBox eingefügt werden sollen.
     *                   Alle vorhandenen Elemente werden entfernt und die neuen Raumnummern werden hinzugefügt.
     *                   Ein Default-Wert wird als erste Auswahlmöglichkeit hinzugefügt.
     */
    public void setDCBModel(ArrayList<String> raumNrList){
        cb_raumNr.removeAllItems();
        raumNrList.add(0,"--");//Default-Wert
        cb_raumNr.setModel(new DefaultComboBoxModel(raumNrList.toArray()));
    }
    /**
     * Setzt die Auswahl in den JComboBoxen (Anrede, Titel, Beruf, Raumnummer) auf den Default-Wert "--" und leert die Textfelder für Vorname und Nachname.
     */
    @Override
    public void setPlaceHolder (){
        cb_anrede.setSelectedItem("--");
        cb_titel.setSelectedItem("--");
        cb_beruf.setSelectedItem("--");
        cb_raumNr.setSelectedItem("--");
        tf_vorname.setText("");
        tf_nachname.setText("");
    }
    /**
     * Ändert die Sichtbarkeit der Komponenten des Arzt-Panels, nachdem der Benutzer
     * einen Arzt aus der Arzttabelle ausgewählt hat.
     * Diese Methode zeigt bestimmte Komponenten für Arztdaten an und blendet
     * andere Elemente aus, um das Aktualisieren von Arztinformationen zu ermöglichen.
     */
    public void setVisibilityAfterPicking (){
        lb_arztID.setVisible(true);
        lb_anrede.setVisible(true);
        lb_titel.setVisible(true);
        lb_vorname.setVisible(true);
        lb_nachname.setVisible(true);
        lb_raumNr.setVisible(true);
        lb_beruf.setVisible(true);
        tf_arztID.setVisible(true);
        cb_anrede.setVisible(true);
        cb_titel.setVisible(true);
        tf_vorname.setVisible(true);
        tf_nachname.setVisible(true);
        cb_raumNr.setVisible(true);
        cb_beruf.setVisible(true);
        btn_add.setVisible(false);
        btn_update.setVisible(true);
        btn_clear.setVisible(false);
        scrollPane.setVisible(true);
    }

//Getter Methoden.
    public JTextField getTf_arztID() {
        return tf_arztID;
    }
    public JTextField getTf_vorname() {
        return tf_vorname;
    }
    public JTextField getTf_nachname() {
        return tf_nachname;
    }
    public JComboBox getCb_raumNr() {
        return cb_raumNr;
    }
    public JComboBox getCb_beruf() {
        return cb_beruf;
    }
    public JComboBox getCb_anrede() {
        return cb_anrede;
    }
    public JComboBox getCb_titel() {
        return cb_titel;
    }
    public JButton getBtn_add() {
        return btn_add;
    }
    public JButton getBtn_clear() {
        return btn_clear;
    }
    public JButton getBtn_update() {
        return btn_update;
    }
    public Table getTable_Arzt() {
        return arztTable;
    }
    public DefaultTableModel getTable_Model_Arzt() {
        return arztTable.getDefault_Table_Model();
    }

}
