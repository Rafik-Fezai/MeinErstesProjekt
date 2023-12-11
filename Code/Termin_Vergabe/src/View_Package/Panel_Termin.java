package View_Package;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Date;
import java.util.Locale;
/**
 * Das Panel_Termin erbt von {@link javax.swing.JPanel} ,implementiert die {@link View_Package.Chameleon}-Schnittstelle für Sichtbarkeitsänderungen
 * und bietet eine Benutzeroberfläche zur Erfassung und Verwaltung von Terminen.
 *
 * Es handelt sich um einen Container für GUI-Elemente, die angezeigt werden, wenn das Panel als ContentPane in einer
 * von JFrame abgeleiteten Klasse, festgelegt wird.
 */
public class Panel_Termin extends JPanel implements Chameleon {

    private JLabel lb_geburtsdatum ;
    private JDateChooser dc_geburtsdatum ;
    private JButton btn_suchen;
    private JLabel lb_patient_id;
    private JLabel lb_arzt_id;
    private JLabel lb_datum;
    private JLabel lb_uhrzeit;
    private JTextField tf_patient_id;
    private JTextField tf_arzt_id;
    private JDateChooser dc_datum;
    private JComboBox cb_uhrzeit;
    private JButton btn_add;
    private JButton btn_delete;
    private JScrollPane scrollPaneTermin;
    private  JScrollPane scrollPanePatientSuche;
    private Table tableTermin;
    private Table tablePatientSuche;
    /**
     * Konstruktor für das Panel_Termin. Erstellt eine Benutzeroberfläche für die Terminverwaltung.
     * Enthält Elemente zur Eingabe und Anzeige von Termininformationen sowie Tabellen zur Anzeige bestehender Termine und zur Suche nach Patienten.
     */
    public Panel_Termin() {

        setLayout(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        //Label Geburtsdatum
        lb_geburtsdatum = new JLabel("Geburtsdatum");
        lb_geburtsdatum.setBounds(30,30,100,25);
        add(lb_geburtsdatum);

        //DateChooser für das Geburtsdatum
        dc_geburtsdatum = new JDateChooser();
        dc_geburtsdatum.setBounds(130,30,100,25);
        dc_geburtsdatum.setLocale(Locale.GERMANY);//Sowohl die Sprache als auch das Datum-Format für das entsprechende Land wird festgelegt.
        dc_geburtsdatum.setMaxSelectableDate(new Date());// Heute
        add(dc_geburtsdatum);

        //JTextFieldDateEditor für das Geburtsdatum
        JTextFieldDateEditor dateEditorGD = (JTextFieldDateEditor) dc_geburtsdatum.getDateEditor();
        dateEditorGD.setEditable(false);

        //Button Suche nach Geburtsdatum (Vergabe)
        btn_suchen = new JButton("Suchen");
        btn_suchen.setBounds(250,30,100,25);
        add(btn_suchen);

        //Label Patient-ID
        lb_patient_id = new JLabel("Patient-ID:");
        lb_patient_id.setBounds(30, 90, 100, 25);
        add(lb_patient_id);

        //Textfeld Patient-ID
        tf_patient_id = new JTextField(20);
        tf_patient_id.setBounds(130, 90, 100, 25);
        tf_patient_id.setEditable(false);
        add(tf_patient_id);

        // Label Arzt-ID
        lb_arzt_id = new JLabel("Arzt-ID:");
        lb_arzt_id.setBounds(30, 150, 100, 25);
        add(lb_arzt_id);

        // Textfeld Arzt-ID
        tf_arzt_id = new JTextField(20);
        tf_arzt_id.setBounds(130, 150, 100, 25);
        tf_arzt_id.setEditable(false);
        add(tf_arzt_id);

        //Label Datum
        lb_datum = new JLabel("Datum:");
        lb_datum.setBounds(30, 210, 100, 25);
        add(lb_datum);

        //DateChooser Datum
        dc_datum = new JDateChooser();
        dc_datum.setBounds(130, 210, 100, 25);
        dc_datum.setMinSelectableDate(new Date());
        add(dc_datum);


        //JTextFieldDateEditor für das Datum
        JTextFieldDateEditor dateEditorD = (JTextFieldDateEditor) dc_datum.getDateEditor();
        dateEditorD.setEditable(false);

        //Label Uhrzeit
        lb_uhrzeit = new JLabel("Uhrzeit:");
        lb_uhrzeit.setBounds(30, 270, 100, 25);
        add(lb_uhrzeit);

        //Combobox Uhrzeit
        cb_uhrzeit = new JComboBox();
        cb_uhrzeit.setBounds(130, 270, 100, 25);
        add(cb_uhrzeit);

        //Button Termin vergeben
        btn_add = new JButton("Termin vergeben");
        btn_add.setBounds(30, 330, 220, 25);
        add(btn_add);

        //Button Termin stornieren
        btn_delete = new JButton("Termin stornieren");
        btn_delete.setBounds(30, 390, 220, 25);
        add(btn_delete);

        // Table Termin
        tableTermin = new Table(new String[]{"Termin-ID", "Datum", "Uhrzeit", "Patient-ID", "Patient-Name", "Geburtsdatum" ,"Arzt"});

        //ScrollPane  scrollPaneTermin
        scrollPaneTermin = new JScrollPane();
        scrollPaneTermin.setBounds(380, 30, 600, 690);
        scrollPaneTermin.setViewportView(tableTermin);
        add(scrollPaneTermin);

        //Table   patient_Suche_Table
        tablePatientSuche =new Table(new String []{"Patient-ID","Patient","Geburtsdatum","Betreuer-ID","Betreuer"});

        //ScrollPane patient_Suche_Table
        scrollPanePatientSuche = new JScrollPane();
        scrollPanePatientSuche.setBounds(380,30,600,690);
        scrollPanePatientSuche.setViewportView(tablePatientSuche);
        add(scrollPanePatientSuche);
    }

    /**
     * Ändert die Sichtbarkeit der GUI-Elemente basierend auf dem übergebenen Modus.
     *
     * @param modus Der Modus, der angibt, welche GUI-Elemente sichtbar sein sollen ("add", "delete" oder "list").
     *              - "add": Zeigt GUI-Elemente an, um einen neuen Termin hinzuzufügen.
     *              - "delete": Zeigt GUI-Elemente an, um einen bestehenden Termin zu löschen.
     *              - "list": Zeigt GUI-Elemente an, um eine Liste bestehender Termine anzuzeigen.
     *              Die Methode passt die Sichtbarkeit entsprechend an und stellt Platzhalter-Werte ein.
     */
    @Override
    public void changeVisibility(String modus ){
        switch (modus){
            case "add" :
                lb_geburtsdatum.setVisible(true);
                lb_patient_id.setVisible(false);
                lb_arzt_id.setVisible(false);
                lb_datum.setVisible(false);
                lb_uhrzeit.setVisible(false);
                dc_geburtsdatum.setVisible(true);
                tf_patient_id.setVisible(false);
                tf_arzt_id.setVisible(false);
                dc_datum.setVisible(false);
                cb_uhrzeit.setVisible(false);
                btn_suchen.setVisible(true);
                btn_add.setVisible(false);
                btn_delete.setVisible(false);
                scrollPanePatientSuche.setVisible(false);
                scrollPaneTermin.setVisible(false);
                setPlaceHolder();
                break;

            case "delete":
                lb_geburtsdatum.setVisible(true);
                lb_patient_id.setVisible(false);
                lb_arzt_id.setVisible(false);
                lb_datum.setVisible(false);
                lb_uhrzeit.setVisible(false);
                dc_geburtsdatum.setVisible(true);
                tf_patient_id.setVisible(false);
                tf_arzt_id.setVisible(false);
                dc_datum.setVisible(false);
                cb_uhrzeit.setVisible(false);
                btn_suchen.setVisible(true);
                btn_add.setVisible(false);
                btn_delete.setVisible(false);
                scrollPanePatientSuche.setVisible(false);
                scrollPaneTermin.setVisible(false);
                setPlaceHolder();
                break;

            case "list":
                lb_geburtsdatum.setVisible(false);
                lb_patient_id.setVisible(false);
                lb_arzt_id.setVisible(false);
                lb_datum.setVisible(false);
                lb_uhrzeit.setVisible(false);
                dc_geburtsdatum.setVisible(false);
                tf_patient_id.setVisible(false);
                tf_arzt_id.setVisible(false);
                dc_datum.setVisible(false);
                cb_uhrzeit.setVisible(false);
                btn_suchen.setVisible(false);
                btn_add.setVisible(false);
                btn_delete.setVisible(false);
                scrollPanePatientSuche.setVisible(false);
                scrollPaneTermin.setVisible(true);
                break;
        }
    }
    /**
     * Diese Methode setzt Platzhalterwerte für die entsprechenden UI-Elemente im Termin-Panel zurück.
     * Dabei wird das Datum im Geburtsdatum-Chooser und im Datum-Chooser auf null gesetzt.
     * Ebenso werden die Felder für die Patienten-ID und die Arzt-ID geleert.
     */
    @Override
    public void setPlaceHolder(){
        dc_geburtsdatum.setDate(null);
        tf_patient_id.setText("");
        tf_arzt_id.setText("");
        dc_datum.setDate(null);
    }
    /**
     * Diese Methode ändert die Sichtbarkeit der Elemente im Termin-Panel, nachdem der Benutzer
     * einen Patienten aus der Suchtabelle ausgewählt hat.
     * die übergebenen Patient- und Arzt-IDs werden in die zugehörigen Textfelder übernommen
     *
     * @param patientID Die ausgewählte Patienten-ID, die im entsprechenden Textfeld angezeigt werden soll.
     * @param arztID    Die ausgewählte Arzt-ID, die im entsprechenden Textfeld angezeigt werden soll.
     *
     */
    public void changeVisibilityAfterPicking (String patientID ,String arztID){
        tf_patient_id.setText(patientID);
        tf_arzt_id.setText(arztID);
        lb_patient_id.setVisible(true);
        lb_arzt_id.setVisible(true);
        tf_patient_id.setVisible(true);
        tf_arzt_id.setVisible(true);
        lb_datum.setVisible(true);
        dc_datum.setVisible(true);
        lb_geburtsdatum.setVisible(false);
        dc_geburtsdatum.setVisible(false);
        btn_suchen.setVisible(false);
        scrollPanePatientSuche.setVisible(false);
    }
    //Getter-Methoden
    public JLabel getLb_geburtsdatum() {return lb_geburtsdatum;}
    public JDateChooser getDc_geburtsdatum() {return dc_geburtsdatum;}
    public JButton getBtn_suchen() {return btn_suchen;}
    public JLabel getLb_uhrzeit() {return lb_uhrzeit;}
    public JTextField getTf_patient_id() {return tf_patient_id;}
    public JTextField getTf_arzt_id() {return tf_arzt_id;}
    public JDateChooser getDc_datum() {return dc_datum;}
    public JComboBox getCb_uhrzeit() {return cb_uhrzeit;}
    public JButton getBtn_add() {return btn_add;}
    public JButton getBtn_delete() {return btn_delete;}
    public JScrollPane getScrollPaneTermin() {return scrollPaneTermin;}
    public JScrollPane getScrollPanePatientSuche() {return scrollPanePatientSuche;}
    public Table getTableTermin() {return tableTermin;}
    public Table getTablePatientSuche() {return tablePatientSuche;}


}
