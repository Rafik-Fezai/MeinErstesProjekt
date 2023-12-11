package View_Package;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Das Panel_Patient erbt von {@link javax.swing.JPanel} und bietet eine Benutzeroberfläche zur Verwaltung von Patientendaten.
 * Es handelt sich um einen Container für diese Elemente, die angezeigt werden,
 * wenn das Panel als ContentPane in einer von JFrame abgeleiteten Klasse, festgelegt wird.
 *
 */
public class Panel_Patient  extends JPanel implements Chameleon{

    private JLabel lb_patientID;
    private JTextField tf_patientID;
    private JLabel lb_anrede;
    private JLabel lb_vorname ;
    private JLabel lb_nachname ;
    private JLabel lb_telefon ;
    private JLabel lb_geburtsdatum ;
    private JLabel lb_betreuer_id ;
    private JComboBox cb_anrede;
    private  JTextField tf_vorname;
    private  JTextField tf_nachname ;
    private JTextField tf_telefon;
    private JDateChooser dc_geburtsdatum;
    private JTextField tf_arztID;
    private JButton btn_add ;
    private JButton btn_clear ;
    private JButton btn_update ;
    private JScrollPane scrollPanePatient;
    private JScrollPane scrollPaneArzt ;
    private Table patientTable ;
    private Table arztTable ;


    /**
     * Konstruktor für Panel_Patient. Definiert und initialisiert die grafischen Komponenten für die Patientenverwaltung.
     */
    public Panel_Patient() {

        setLayout(null);
        setBorder(new EmptyBorder(5,5,5,5));

        //Label Patient-ID
        lb_patientID = new JLabel("Patient-ID");
        lb_patientID.setBounds(30,30,100,25);
        add(lb_patientID);

        //Texfeld Patient-ID
        tf_patientID = new JTextField(20);
        tf_patientID.setBounds(130,30,100,25);
        tf_patientID.setEditable(false);
        add(tf_patientID);

        //Label Anrede
        lb_anrede = new JLabel("Anrede:*");
        lb_anrede.setBounds(30,90,100,25);
        add(lb_anrede);

        //Combobox Anrede
        String []  anredeArray ={"--","Herr" ,"Frau"};
        cb_anrede = new JComboBox(anredeArray);
        cb_anrede.setBounds(130,90,100,25);
        add(cb_anrede);

        //Label Vorname
        lb_vorname = new JLabel("Vorname:*");
        lb_vorname.setBounds(30,150,100,25);
        add(lb_vorname);

        //Texfeld Vorname
        tf_vorname = new JTextField(20);
        tf_vorname.setBounds(130,150,100,25);
        add(tf_vorname);

        // Label Nachname
        lb_nachname = new JLabel("Nachname:*");
        lb_nachname.setBounds(30,210,100,25);
        add(lb_nachname);

        //Textfeld Nachname
        tf_nachname = new JTextField(20);
        tf_nachname.setBounds(130,210,100,25);
        add(tf_nachname);

        //Label Telefon
        lb_telefon = new JLabel("Telefon:*");
        lb_telefon.setBounds(30,270,100,25);
        add(lb_telefon);

        //Textfeld Telefon
        tf_telefon = new JTextField(20);
        tf_telefon.setBounds(130,270,100,25);
        add(tf_telefon);

        //Label Geburtsdatum
        lb_geburtsdatum =new JLabel("Geburtsdatum:*");
        lb_geburtsdatum.setBounds(30,330,100,25);
        add(lb_geburtsdatum);

        //DateChooser Geburtsdatum
        dc_geburtsdatum = new JDateChooser();
        dc_geburtsdatum.setBounds(130,330,100,25);
        dc_geburtsdatum.setLocale(Locale.GERMANY);//Sowohl die Sprache als auch das Datum-Format für das entsprechende Land wird festgelegt.
        dc_geburtsdatum.setMaxSelectableDate(new Date());//um sicherzustellen, dass das ausgewählte Geburtsdatum nicht in der Zukunft liegt.
        add(dc_geburtsdatum);

        //JTextFieldDateEditor
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dc_geburtsdatum.getDateEditor();
        dateEditor.setEditable(false);

        //Label Betreuer
        lb_betreuer_id = new JLabel("Betreuer-ID:*");
        lb_betreuer_id.setBounds(30,390,100,25);
        add(lb_betreuer_id);

        //Textfeld Betreuer ID
        tf_arztID = new JTextField(20);
        tf_arztID.setBounds(130,390,100,25);
        tf_arztID.setEditable(false);
        add(tf_arztID);


        //Button hinzufügen
        btn_add = new JButton("Patient einfügen");
        btn_add.setBounds(30,450,220,25);
        add(btn_add);

        //Button Löschen
        btn_clear = new JButton("Patient löschen");
        btn_clear.setBounds(30,510,220,25);
        add(btn_clear);

        //Button Aktualisieren
        btn_update = new JButton("Patientendaten ändern");
        btn_update.setBounds(30,570,220,25);
        add(btn_update);

        // JTable  Patient
        patientTable = new Table(new String []{"Patient-ID","Anrede","Vorname","Nachname","Telefon","Geburtsdatum","Betreuer-ID"});

        //ScrollPane für die Patienten-Tabelle
        scrollPanePatient = new JScrollPane();
        scrollPanePatient.setBounds(280,30,690,690);
        scrollPanePatient.setViewportView(patientTable);
        add(scrollPanePatient);

        // Table Arzt
        arztTable = new Table(new String []{"Arzt-ID","Anrede","Titel","Vorname","Nachname","RaumNr","Beruf"});

        //ScrollPane für die Arzt-Tabelle
        scrollPaneArzt =new JScrollPane();
        scrollPaneArzt.setBounds(280,30,690,690);
        scrollPaneArzt.setViewportView(arztTable);
        add(scrollPaneArzt);

    }
    /**
     * Verändert die Sichtbarkeit der Komponenten basierend auf dem übergebenen Modus.
     * Je nach Modus zeigt oder verbirgt diese Methode verschiedene Elemente des Patienten-Panels.
     * Die Modi "add", "delete", "update" und "list" ändern die Sichtbarkeit entsprechend.
     */
    @Override
    public void changeVisibility(String modus ){
        switch (modus){
            case "add" :
                lb_patientID.setVisible(false);
                tf_patientID.setVisible(false);
                lb_anrede.setVisible(true);
                cb_anrede.setVisible(true);
                lb_vorname.setVisible(true);
                tf_vorname.setVisible(true);
                lb_nachname.setVisible(true);
                tf_nachname.setVisible(true);
                lb_telefon.setVisible(true);
                tf_telefon.setVisible(true);
                lb_geburtsdatum.setVisible(true);
                dc_geburtsdatum.setVisible(true);
                lb_betreuer_id.setVisible(true);
                tf_arztID.setVisible(true);
                btn_add.setVisible(true);
                btn_clear.setVisible(false);
                btn_update.setVisible(false);
                scrollPanePatient.setVisible(false);
                scrollPaneArzt.setVisible(true);
                break;

            case "delete":
                lb_patientID.setVisible(false);
                tf_patientID.setVisible(false);
                lb_anrede.setVisible(false);
                cb_anrede.setVisible(false);
                lb_vorname.setVisible(false);
                tf_vorname.setVisible(false);
                lb_nachname.setVisible(false);
                tf_nachname.setVisible(false);
                lb_telefon.setVisible(false);
                tf_telefon.setVisible(false);
                lb_geburtsdatum.setVisible(false);
                dc_geburtsdatum.setVisible(false);
                lb_betreuer_id.setVisible(false);
                tf_arztID.setVisible(false);
                btn_add.setVisible(false);
                btn_clear.setVisible(true);
                btn_update.setVisible(false);
                scrollPanePatient.setVisible(true);
                scrollPaneArzt.setVisible(false);
                break;

            case "update":
                lb_patientID.setVisible(false);
                tf_patientID.setVisible(false);
                lb_anrede.setVisible(false);
                cb_anrede.setVisible(false);
                lb_vorname.setVisible(false);
                tf_vorname.setVisible(false);
                lb_nachname.setVisible(false);
                tf_nachname.setVisible(false);
                lb_telefon.setVisible(false);
                tf_telefon.setVisible(false);
                lb_geburtsdatum.setVisible(false);
                dc_geburtsdatum.setVisible(false);
                lb_betreuer_id.setVisible(false);
                tf_arztID.setVisible(false);
                btn_add.setVisible(false);
                btn_clear.setVisible(false);
                btn_update.setVisible(false);
                scrollPanePatient.setVisible(true);
                scrollPaneArzt.setVisible(false);
                break;

            case "list":
                lb_patientID.setVisible(false);
                tf_patientID.setVisible(false);
                lb_anrede.setVisible(false);
                cb_anrede.setVisible(false);
                lb_vorname.setVisible(false);
                tf_vorname.setVisible(false);
                lb_nachname.setVisible(false);
                tf_nachname.setVisible(false);
                lb_telefon.setVisible(false);
                tf_telefon.setVisible(false);
                lb_geburtsdatum.setVisible(false);
                dc_geburtsdatum.setVisible(false);
                lb_betreuer_id.setVisible(false);
                tf_arztID.setVisible(false);
                btn_add.setVisible(false);
                btn_clear.setVisible(false);
                btn_update.setVisible(false);
                scrollPanePatient.setVisible(true);
                scrollPaneArzt.setVisible(false);
                break;
        }
    }
    /**
     * Setzt Platzhalter-Werte für die Eingabefelder zurück, um sie für neue Eingaben bereitzustellen.
     * Leert die Eingabefelder für Anrede, Arzt-ID, Vorname, Nachname, Telefon und Geburtsdatum.
     */
    @Override
    public void setPlaceHolder (){
        cb_anrede.setSelectedItem("--");
        tf_arztID.setText("");
        tf_vorname.setText("");
        tf_nachname.setText("");
        tf_telefon.setText("");
        dc_geburtsdatum.setDate(null);
    }
    /**
     * Ändert die Sichtbarkeit der Komponenten des Patienten-Panels, nachdem der Benutzer
     * einen Patienten aus der Patiententabelle ausgewählt hat.
     * Diese Methode zeigt bestimmte Eingabefelder für Patientendaten an und blendet
     * andere Elemente aus, um das Aktualisieren von Patienteninformationen zu ermöglichen.
     */
public void setVisibilityAfterPickingPatient(){

        lb_patientID.setVisible(true) ;
        tf_patientID.setVisible(true);
        lb_anrede.setVisible(true);
        cb_anrede.setVisible(true);
        lb_vorname.setVisible(true);
        tf_vorname.setVisible(true);
        lb_nachname.setVisible(true);
        tf_nachname.setVisible(true);
        lb_telefon.setVisible(true);
        tf_telefon.setVisible(true);
        lb_geburtsdatum.setVisible(true);
        dc_geburtsdatum.setVisible(true);
        lb_betreuer_id.setVisible(true);
        tf_arztID.setVisible(true);
        btn_add.setVisible(false);
        btn_clear.setVisible(false);
        btn_update.setVisible(true);
        scrollPanePatient.setVisible(false);
        scrollPaneArzt.setVisible(true);

    }

    //Getter Methoden.
    public JTextField getTf_patientID() {return tf_patientID;}
    public JComboBox getCb_anrede() {return cb_anrede;}
    public JTextField getTf_vorname() {return tf_vorname;}
    public JTextField getTf_nachname() {return tf_nachname;}
    public JTextField getTf_telefon() {return tf_telefon;}
    public JDateChooser getDc_geburtsdatum() {return dc_geburtsdatum;}
    public JButton getBtn_add() {return btn_add;}
    public JButton getBtn_clear() {return btn_clear;}
    public JButton getBtn_update() {return btn_update;}
    public Table getTable_Patient() {return patientTable;}
    public Table getArztTable() {return arztTable;}
    public JTextField getTf_arztID() {return tf_arztID;}
    public DefaultTableModel getTable_Model_Patient() {return patientTable.getDefault_Table_Model();}

}
