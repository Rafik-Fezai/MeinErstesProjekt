package View_Package;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 * Das Panel_Raum erbt von {@link javax.swing.JPanel} und beinhaltet Komponenten für die Raumverwaltung.
 * Es dient als Container für diese Elemente und zeigt sie an, wenn das Panel als ContentPane verwendet wird.
 * Die Klasse bietet Methoden zur Änderung der Sichtbarkeit der Komponenten für verschiedene Verwaltungszwecke.
 */
public class Panel_Raum extends JPanel implements Chameleon{
    private JLabel lb_raum_nr ;
    private JLabel lb_status ;
    private JTextField tf_raum_nr ;
    private JComboBox  cb_status ;
    private JButton btn_add ;
    private JButton btn_delete;
    private JLabel  lb_AlteRaumNr ;
    private JTextField tf_AlteRaumNr ;
    private  JButton btn_update ;
    private JScrollPane scrollPane;
    private Table raumTable ;
    /**
     * Konstruktor für Panel_Raum. Initialisiert die grafischen Komponenten für die Raumverwaltung.
     */
    public Panel_Raum(){

        setLayout(null);
        setBorder(new LineBorder(Color.BLACK));

        // Label alte Raumnummer beim Update
        lb_AlteRaumNr = new JLabel("Alte Raum-Nr:");
        lb_AlteRaumNr.setBounds(30,30,100,25);
        add(lb_AlteRaumNr);

        //Textfeld alte Raumnummer beim Update
        tf_AlteRaumNr= new JTextField();
        tf_AlteRaumNr.setBounds(250,30,100,25);
        tf_AlteRaumNr.setEditable(false);
        add(tf_AlteRaumNr);

        //Label Raumnummer beim Select und Delete /neue Raumnummer beim Update
        lb_raum_nr = new JLabel("Raum-Nr:");
        lb_raum_nr.setBounds(30,90,100,25);
        add(lb_raum_nr);

        //Textfeld Raum
        tf_raum_nr = new JTextField(20);
        tf_raum_nr.setBounds(250,90,100,25);
        add(tf_raum_nr);

        //Label Status
        lb_status = new JLabel("Staus:");
        lb_status.setBounds(30,150,100,25);
        add(lb_status);

        //Combobox Status
        String[] status ={"frei"};
        cb_status = new JComboBox(status);
        cb_status.setBounds(250,150,100,25);
        add(cb_status);

        //Button add
        btn_add = new JButton("Raum einfügen");
        btn_add.setBounds(30,210,350,25);
        add(btn_add);

        //Button Clear
        btn_delete = new JButton("Selektierten Raum löschen");
        btn_delete.setBounds(30,270,350,25);
        add(btn_delete);

        //Button update
        btn_update = new JButton("Raum aktualisieren");
        btn_update.setBounds(30,390,350,25);
        add(btn_update);

        // JTable
        raumTable =new Table(new String []{"Raum-Nr","Status"});

        //ScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(450,30,500,700);
        scrollPane.setViewportView(raumTable);
        add(scrollPane);
    }
    /**
     * Ändert die Sichtbarkeit der Komponenten basierend auf dem übergebenen Modus.
     *
     * @param modus Der Modus, der angibt, welche Komponenten sichtbar sein sollen:
     *              "add" steht für das Hinzufügen von Rauminformationen,
     *              "delete" steht für das Löschen von Rauminformationen,
     *              "update" steht für das Aktualisieren von Rauminformationen,
     *              "list" steht für das Anzeigen einer Liste von Rauminformationen.
     */
    @Override
    public void changeVisibility(String modus ){
        if (modus.equals("add")){

            lb_raum_nr.setVisible(true);
            tf_raum_nr.setVisible(true);
            lb_status.setVisible(true);
            cb_status.setVisible(true);
            btn_add.setVisible(true);
            btn_delete.setVisible(false);
            scrollPane.setVisible(false);
            btn_update.setVisible(false);
            lb_AlteRaumNr.setVisible(false);
            tf_AlteRaumNr.setVisible(false);
            lb_raum_nr.setText("Raum-Nr:");

        } else if (modus.equals("delete")) {

            lb_raum_nr.setVisible(false);
            tf_raum_nr.setVisible(false);
            lb_status.setVisible(false);
            cb_status.setVisible(false);
            btn_add.setVisible(false);
            btn_delete.setVisible(true);
            scrollPane.setVisible(true);
            btn_update.setVisible(false);
            lb_AlteRaumNr.setVisible(false);
            tf_AlteRaumNr.setVisible(false);

        }else if(modus.equals("update")){

            lb_raum_nr.setVisible(true);
            tf_raum_nr.setVisible(true);
            lb_status.setVisible(false);
            cb_status.setVisible(false);
            btn_add.setVisible(false);
            btn_delete.setVisible(false);
            scrollPane.setVisible(true);
            btn_update.setVisible(true);
            lb_AlteRaumNr.setVisible(true);
            tf_AlteRaumNr.setVisible(true);
            lb_raum_nr.setText("Neue Raum-Nr:");
            setPlaceHolder();
        }
        else if (modus.equals("list")){

            lb_raum_nr.setVisible(false);
            tf_raum_nr.setVisible(false);
            lb_status.setVisible(false);
            cb_status.setVisible(false);
            btn_add.setVisible(false);
            btn_delete.setVisible(false);
            scrollPane.setVisible(true);
            btn_update.setVisible(false);
            lb_AlteRaumNr.setVisible(false);
            tf_AlteRaumNr.setVisible(false);
        }
    }
    /**
     * Bereitet die Eingabefelder für eine neue Eingabe vor, indem der aktuelle Text gelöscht wird.
     */
    @Override
    public void setPlaceHolder (){
        tf_AlteRaumNr.setText("");
        tf_raum_nr.setText("");
    }
    //Getter Methoden.
    public JTextField getTf_AlteRaumNr() {
        return tf_AlteRaumNr;
    }
    public JButton getBtn_update() {
        return btn_update;
    }
    public JTextField getTf_raum_nr() {
        return tf_raum_nr;
    }
    public JComboBox getCb_status() {
        return cb_status;
    }
    public JButton getBtn_add() {
        return btn_add;
    }
    public JButton getBtn_delete() {
        return btn_delete;
    }
    public Table getTable_Raum() {
        return raumTable;
    }
    public DefaultTableModel getTable_Model_Raum() {
        return raumTable.getDefault_Table_Model();
    }


}
