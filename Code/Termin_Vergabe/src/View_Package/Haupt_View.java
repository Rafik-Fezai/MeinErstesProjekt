package View_Package;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeListener;
/**
 * Die Haupt_View-Klasse erbt von {@link javax.swing.JFrame} und repräsentiert das Hauptfenster der Software.
 * Sie verwaltet verschiedene Panels für Arzt, Patient, Termin, Raum und die Menüleiste.
 *
 * @see View_Package.Panel_Arzt
 * @see View_Package.Panel_Patient
 * @see View_Package.Panel_Termin
 * @see View_Package.Panel_Home
 * @see View_Package.Panel_Raum
 * @see View_Package.Menu_Bar
 *
 * @author Rafik
 * @version 1.0.0
 */
public class Haupt_View extends JFrame {

        private Panel_Arzt panel_arzt;
        private Panel_Patient panel_patient;
        private Panel_Termin panel_termin;
        private Panel_Home panel_home;
        private Panel_Raum panel_raum;
        private Menu_Bar menu_bar;

    /**
     * Konstruktor der Haupt_View-Klasse.
     * Erstellt das Hauptfenster der Praxis-Software und initialisiert die verschiedenen Panels und die Menüleiste.
     * Das Hauptfenster ist standardmäßig unsichtbar.
     */
        public Haupt_View() {

            //Frame
            setTitle("Praxis_Software");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(1000, 500, 1000, 800);

            // Panels initialisieren
            panel_arzt = new Panel_Arzt();
            panel_patient =new Panel_Patient();
            panel_home = new Panel_Home() ;
            panel_termin = new Panel_Termin();
            panel_raum = new Panel_Raum();
            setContentPane(panel_home);

            // Menüleiste setzen
            menu_bar = new Menu_Bar();
            setJMenuBar(menu_bar);

            setVisible(false);
        }

    // Getter-Methoden für die einzelnen Panels und die Menüleiste
    public Menu_Bar getMenu_bar() {
        return menu_bar;
    }
    public Panel_Arzt getPanel_arzt() {
        return panel_arzt;
    }
    public Panel_Patient getPanel_patient() {
        return panel_patient;
    }
    public Panel_Termin getPanel_termin() {
        return panel_termin;
    }
    public Panel_Home getPanel_home() {
        return panel_home;
    }
    public Panel_Raum getPanel_raum() {
        return panel_raum;
    }


    /**
     * Zeigt eine Fehlermeldung in einem Dialogfenster an.
     *
     * @param errorMessage Die Fehlermeldung, die angezeigt werden soll.
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    /**
     * Zeigt eine Nachricht in einem Dialogfenster an.
     *
     * @param message Die Nachricht, die angezeigt werden soll.
     */
    public void displayMessage(String message){JOptionPane.showMessageDialog(this,message);}
}

