package View_Package;

import javax.swing.*;
/**
 * Diese Klasse repräsentiert die Menüleiste der Anwendung und enthält verschiedene Menüs und Menüpunkte
 * für die Navigation und Interaktion mit der Anwendung.
 *
 *@author Rafik
 *@version 1.0.0
 */
public class Menu_Bar extends JMenuBar {
    private JMenu menu_arzt;
    private JMenuItem item_delete_arzt;
    private JMenuItem item_add_arzt;
    private JMenuItem item_update_arzt;
    private JMenuItem item_arzt_list;
    private JMenu menu_termin;
    private JMenuItem item_add_termin;
    private JMenuItem item_drop_termin;
    private JMenuItem item_list_termin;
    private JMenu menu_patient;
    private JMenuItem item_delete_patient;
    private JMenuItem item_add_patient;
    private JMenuItem item_update_patient;
    private JMenuItem item_list_patient;
    private JMenu menu_home;
    private JMenuItem item_back_homepage;
    private JMenuItem item_exit;
    private JMenuItem item_abmelden;
    private JMenu menu_raum;
    private JMenuItem item_add_raum ;
    private JMenuItem item_delete_raum ;
    private  JMenuItem item_update_raum ;
    private  JMenuItem item_raum_list;

    /**
     * Erstellt eine benutzerdefinierte Menüleiste für die Anwendung.
     *
     * Sie enthält verschiedene Menüpunkte und wird in der Anwendung als Navigationsleiste verwendet, um auf verschiedene Funktionen zuzugreifen.
     */
    public Menu_Bar(){

        //Submenüs für die Homepage
        item_back_homepage = new JMenuItem("Zurück zur Homepage");
        item_abmelden = new JMenuItem("Abmelden");
        item_exit = new JMenuItem("Programm beenden");

        //Menü für die Homepage
        menu_home = new JMenu("Home");
        menu_home.add(item_back_homepage);
        menu_home.add(item_abmelden);
        menu_home.add(item_exit);

        //Submenüs für den Arzt (menu-items)
        item_add_arzt = new JMenuItem("Arzt einfügen");
        item_delete_arzt = new JMenuItem("Arzt löschen");
        item_update_arzt = new JMenuItem("Arztdaten ändern");
        item_arzt_list = new JMenuItem("Arzt-Liste");

        //Menü für den Arzt
        menu_arzt = new JMenu("Arzt");
        menu_arzt.add(item_add_arzt);
        menu_arzt.add(item_delete_arzt);
        menu_arzt.add(item_update_arzt);
        menu_arzt.add(item_arzt_list);

        //Submenüs für den Patienten (Menu-items)
        item_add_patient = new JMenuItem("Patient einfügen");
        item_delete_patient = new JMenuItem("Patient löschen");
        item_update_patient = new JMenuItem("Patientendaten ändern");
        item_list_patient = new JMenuItem("Patient-Liste");

        //Menu für den Patienten
        menu_patient = new JMenu("Patient");
        menu_patient.add(item_add_patient);
        menu_patient.add(item_delete_patient);
        menu_patient.add(item_update_patient);
        menu_patient.add(item_list_patient);

        //Submenüs für den Termin (Menu-items)
        item_add_termin = new JMenuItem("Termin vergeben");
        item_drop_termin = new JMenuItem("Termin stornieren");
        item_list_termin = new JMenuItem("Termin-Liste");

        //Menu für den Termin
        menu_termin = new JMenu("Termin");
        menu_termin.add(item_add_termin);
        menu_termin.add(item_drop_termin);
        menu_termin.add(item_list_termin);

        //Submenü für den Raum
        item_add_raum = new JMenuItem("Raum einfügen");
        item_delete_raum = new JMenuItem("Raum löschen");
        item_update_raum = new JMenuItem("RaumNr editieren");
        item_raum_list =new JMenuItem("Raumliste");

        //Menü Raum
        menu_raum = new JMenu("Raum");
        menu_raum.add(item_add_raum);
        menu_raum.add(item_delete_raum);
        menu_raum.add(item_update_raum);
        menu_raum.add(item_raum_list);

        //Menus einfügen
        add(menu_home);
        add(menu_termin);
        add(menu_arzt);
        add(menu_patient);
        add(menu_raum);

    }
    // Getter-Methoden für die einzelnen Items.
    public JMenuItem getItem_delete_arzt() {
        return item_delete_arzt;
    }
    public JMenuItem getItem_add_arzt() {
        return item_add_arzt;
    }
    public JMenuItem getItem_update_arzt() {
        return item_update_arzt;
    }
    public JMenuItem getItem_arzt_list() {
        return item_arzt_list;
    }
    public JMenuItem getItem_add_termin() {
        return item_add_termin;
    }
    public JMenuItem getItem_drop_termin() {
        return item_drop_termin;
    }
    public JMenuItem getItem_list_termin() {
        return item_list_termin;
    }
    public JMenuItem getItem_delete_patient() {
        return item_delete_patient;
    }
    public JMenuItem getItem_add_patient() {
        return item_add_patient;
    }
    public JMenuItem getItem_update_patient() {
        return item_update_patient;
    }
    public JMenuItem getItem_list_patient() {
        return item_list_patient;
    }
    public JMenuItem getItem_back_homepage() {
        return item_back_homepage;
    }
    public JMenuItem getItem_exit() {
        return item_exit;
    }
    public JMenuItem getItem_abmelden() {
        return item_abmelden;
    }
    public JMenuItem getItem_add_raum() {
        return item_add_raum;
    }
    public JMenuItem getItem_delete_raum() {
        return item_delete_raum;
    }
    public JMenuItem getItem_update_raum() {
        return item_update_raum;
    }
    public JMenuItem getItem_raum_list() {
        return item_raum_list;
    }
}
