package Controller_Package;

import Model_Package.Model;
import View_Package.Haupt_View;
import View_Package.Login_View;
import View_Package.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
/**
 * Der Controller bildet die Kontrolllogik zwischen der Hauptansicht (Haupt_View), dem Login-Fenster (Login_View)
 * und dem Datenmodell (Model) ab. Er koordiniert die Interaktionen zwischen der Benutzeroberfläche und der Datenbank.
 *
 * @author Rafik
 * @version 1.0.0
 */
public class Controller {
    /**
     * Eine Instanz der Klasse {@link Model_Package.Model}.
     */
    private Model model;
    /**
     * Eine Instanz der Klasse {@link View_Package.Haupt_View}.
     */
    private Haupt_View haupt_view;
    /**
     * Eine Instanz der Klasse {@link View_Package.Login_View}.
     */
    private Login_View loginView;

    /**
     * Konstruktor für den Controller, der eine Verbindung zwischen dem Login-Fenster (Login_View), der Hauptansicht (Haupt_View) und dem Datenmodell (Model) herstellt.
     * @param lv Eine Instanz des Login-Fensters (Login_View), das zur Benutzeranmeldung verwendet wird.
     * @param m Eine Instanz des Datenmodells (Model), das den Zugriff auf die Datenbank ermöglicht.
     */
    public Controller(Login_View lv, Model m) {

        this.model = m;
        this.loginView = lv;
        this.haupt_view = new Haupt_View();
        addAllHandler();
    }
    /**
     * Fügt allen relevanten UI-Elementen die entsprechenden Handler (Listener) hinzu, um auf Benutzerinteraktionen zu reagieren.
     * Dies umfasst die Menüpunkte der Hauptansicht und die Elemente der verschiedenen Panels (Raum, Arzt, Patient, Termin, Home).
     * Jeder Handler wird den entsprechenden UI-Elementen zugewiesen, um auf Benutzeraktionen wie Klicks oder Änderungen zu reagieren.
     */
    public void addAllHandler(){
        //Menus
        addHandlerToItemsHome(new MenuHomeHandler());
        addHandlerToItemsRaum(new MenuRaumHandler());
        addHandlerToItemsArzt(new MenuArztHandler());
        addHandlerToItemsPatient(new MenuPatientHandler());
        addHandlerToItemsTermin(new MenuTerminHandler());

        //Login
        loginView.getPanel_login().getBtn_login().addActionListener(new BtnLoginHandler());

        //Raum
        haupt_view.getPanel_raum().getBtn_add().addActionListener(new BtnAddRaumHandler());
        haupt_view.getPanel_raum().getBtn_update().addActionListener(new BtnUpdateRaumHandler());
        haupt_view.getPanel_raum().getBtn_delete().addActionListener(new BtnDeleteRaumHandler());
        haupt_view.getPanel_raum().getTable_Raum().addMouseListener(new PR_TR_MouseListener());

        //Arzt
        haupt_view.getPanel_arzt().getBtn_add().addActionListener(new BtnAddArztHandler());
        haupt_view.getPanel_arzt().getBtn_clear().addActionListener(new BtnDeleteArztHandler());
        haupt_view.getPanel_arzt().getBtn_update().addActionListener(new BtnUpdateArztHandler());
        haupt_view.getPanel_arzt().getTable_Arzt().addMouseListener(new PA_TA_MouseListener());

        //Patient
        haupt_view.getPanel_patient().getBtn_clear().addActionListener(new BtnDeletePatientHandler());
        haupt_view.getPanel_patient().getBtn_add().addActionListener(new BtnAddPatientHandler());
        haupt_view.getPanel_patient().getBtn_update().addActionListener(new BtnUpdatePatientHandler());
        haupt_view.getPanel_patient().getArztTable().addMouseListener(new PP_TA_MouseListener());
        haupt_view.getPanel_patient().getTable_Patient().addMouseListener(new PP_TP_MouseListener());

        //Termin
        haupt_view.getPanel_termin().getBtn_suchen().addActionListener(new BtnSuchenHandler());
        haupt_view.getPanel_termin().getTablePatientSuche().addMouseListener(new PT_TP_MouseListener());
        haupt_view.getPanel_termin().getBtn_delete().addActionListener(new BtnDeleteTerminHandler());
        haupt_view.getPanel_termin().getBtn_add().addActionListener(new BtnAddTerminHandler());
        haupt_view.getPanel_termin().getDc_datum().addPropertyChangeListener(new DatumChangeListener());

    }

    /**
     * Bindet einen ActionListener an die Menüpunkte des Hauptmenüs.
     *
     * @param a Der ActionListener, der auf die Auswahl der Menüpunkte "Programm beenden", "Abmelden" und "Zurück zur Homepage" reagiert.
     * @see View_Package.Menu_Bar
     */
    public void addHandlerToItemsHome(ActionListener a) {
        haupt_view.getMenu_bar().getItem_exit().addActionListener(a);
        haupt_view.getMenu_bar().getItem_abmelden().addActionListener(a);
        haupt_view.getMenu_bar().getItem_back_homepage().addActionListener(a);
    }
    /**
     * Bindet einen ActionListener an die Menüpunkte des Raummenüs.
     *
     * @param a Der ActionListener, der auf die Auswahl der Menüpunkte "Raum einfügen", "Raum löschen", "RaumNr editieren" und "Raumliste" reagiert.
     * @see View_Package.Menu_Bar
     */
    public void addHandlerToItemsRaum(ActionListener a){
        haupt_view.getMenu_bar().getItem_raum_list().addActionListener(a);
        haupt_view.getMenu_bar().getItem_add_raum().addActionListener(a);
        haupt_view.getMenu_bar().getItem_delete_raum().addActionListener(a);
        haupt_view.getMenu_bar().getItem_update_raum().addActionListener(a);
    }
    /**
     * Bindet einen ActionListener an die Menüpunkte des Arztmenüs.
     *
     * @param a Der ActionListener, der auf die Auswahl der Menüpunkte "Arzt einfügen", "Arzt löschen", "Arztdaten ändern" und "Arzt-Liste" reagiert.
     * @see View_Package.Menu_Bar
     */
    public void addHandlerToItemsArzt(ActionListener a){
        haupt_view.getMenu_bar().getItem_arzt_list().addActionListener(a);
        haupt_view.getMenu_bar().getItem_delete_arzt().addActionListener(a);
        haupt_view.getMenu_bar().getItem_update_arzt().addActionListener(a);
        haupt_view.getMenu_bar().getItem_add_arzt().addActionListener(a);
    }
    /**
     * Bindet einen ActionListener an die Menüpunkte des Patient-Menüs.
     *
     * @param a Der ActionListener, der auf die Auswahl der Menüpunkte "Patient einfügen", "Patient löschen", "Patientendaten ändern" und "Patient-Liste" reagiert.
     * @see View_Package.Menu_Bar
     */
    public void addHandlerToItemsPatient(ActionListener a){
        haupt_view.getMenu_bar().getItem_update_patient().addActionListener(a);
        haupt_view.getMenu_bar().getItem_add_patient().addActionListener(a);
        haupt_view.getMenu_bar().getItem_delete_patient().addActionListener(a);
        haupt_view.getMenu_bar().getItem_list_patient().addActionListener(a);
    }
    /**
     * Bindet einen ActionListener an die Menüpunkte des Terminmenüs.
     *
     * @param a Der ActionListener, der auf die Auswahl der Menüpunkte "Termin vergeben", "Termin stornieren" und "Termin Liste" reagiert.
     * @see View_Package.Menu_Bar
     */
    public void addHandlerToItemsTermin(ActionListener a){
        haupt_view.getMenu_bar().getItem_add_termin().addActionListener(a);
        haupt_view.getMenu_bar().getItem_drop_termin().addActionListener(a);
        haupt_view.getMenu_bar().getItem_list_termin().addActionListener(a);
    }
    /**
     * Holt Daten aus der Datenbank basierend auf einer SQL-Abfrage und der Anzahl der Spalten.
     *
     * @param selectQuery     Die SQL-Abfrage, um Daten aus der Datenbank abzurufen.
     * @param columnNumbers   Die Anzahl der Spalten, die aus der Abfrage zurückgegeben werden sollen.
     * @return                Eine ArrayList von String-Arrays, die die abgerufenen Daten darstellen.
     *                        Jedes String-Array repräsentiert eine Zeile (einen Datensatz) aus der Datenbank.
     * @see Model_Package.Model#select_DB(String, int)
     */
    public ArrayList<String[]> getData(String selectQuery ,int columnNumbers){
        ArrayList<String[]> data = null;
        try {
            data = model.select_DB(selectQuery,columnNumbers);
        } catch (ClassNotFoundException ex) {
            haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
        } catch (SQLInvalidAuthorizationSpecException ex) {
            haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
        } catch (SQLException ex) {
            haupt_view.displayErrorMessage("SQL Fehler : " + ex.getErrorCode() + ": " + ex.getMessage());
        }
        return data;
    }
    /**
     * Ruft Daten aus der Datenbank basierend auf der angegebenen Abfrage ab und gibt sie als Liste von Zeichenketten zurück.
     * Jede Zeichenkette in der Liste enthält Werte, die aus der Datenbank abgerufen wurden.
     *
     * @param selectQuery Die SQL-Abfrage, um Daten aus der Datenbank abzurufen.
     * @return Eine Liste von Zeichenketten, die Werte aus der Datenbank repräsentieren.
     * @see Model_Package.Model#select_DB(String)
     */
    public ArrayList<String> getData(String selectQuery ){
        ArrayList<String> data = null;
        try {
            data = model.select_DB(selectQuery);
        } catch (ClassNotFoundException ex) {
            haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
        } catch (SQLInvalidAuthorizationSpecException ex) {
            haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
        } catch (SQLException ex) {
            haupt_view.displayErrorMessage("SQL Fehler : " + ex.getErrorCode() + ": " + ex.getMessage());
        }
        return data;
    }
    /**
     * Aktualisiert die angegebene Tabelle mit Daten aus der Datenbank.
     *
     * @param table         Die Tabelle, die aktualisiert werden soll.
     * @param selectQuery   Die SQL-Abfrage, um Daten aus der Datenbank abzurufen.
     * @param columnNumbers Die Anzahl der Spalten, die in der Abfrage zurückgegeben werden.
     * @see #getData(String, int)
     * @see View_Package.Table#updateData(ArrayList)
     */
    public void updateTable(Table table, String selectQuery, int columnNumbers) {
        table.updateData(getData(selectQuery, columnNumbers));
    }
    /**
     * Zeigt die Raumnummern an, die den freien Räumen der Arztpraxis entsprechen (Räume, die noch keinem Arzt zugewiesen sind).
     */
    public void displayFreeRoomNumbers() {
        haupt_view.getPanel_arzt().setDCBModel(getData(model.SELECT_F_RAUM_NR));
    }

    /**
     * ActionListener für den Login-Button. Vergleicht die eingegebenen Benutzerdaten mit den gespeicherten
     * Login-Daten aus der Datenbank. Das eingegebene Passwort wird verschlüsselt und zusammen mit dem
     * Benutzernamen in einem String-Array `userInput` zwischengespeichert. Es werden alle Benutzerdaten aus der
     * Datenbanktabelle 'Benutzer' geholt. Bei Übereinstimmung verbirgt sich das Login-Fenster und wird das Haupt-Fenster sichtbar sein.
     * Andernfalls wird eine Fehlermeldung angezeigt.
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnLoginHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = loginView.getPanel_login().getTf_username().getText();
            String password = String.valueOf(loginView.getPanel_login().getPf_password().getPassword());
            String[] userInput = new String[2];
            userInput[0]= userName;
            userInput[1]=IPO.hashPassword(password);
            ArrayList<String[]> loginDaten = getData(model.LOGIN_DATA,2);

            for (String[] row:loginDaten) {
                if (Arrays.equals(userInput,row)){
                    loginView.dispose();
                    haupt_view.setVisible(true);
                }else {
                    loginView.displayErrorMessage("Falsche Logindaten!");
                }
            }
        }
    }
    /**
     * ActionListener für die Menüoptionen "Programm beenden", "Zurück zur Homepage" und "Abmelden".
     * Reagiert auf Klick-Ereignisse dieser Menüpunkte. Wenn "Programm beenden" ausgewählt wird,
     * wird die Anwendung endgültig beendet. Bei Auswahl von "Zurück zur Homepage"
     * wird das Panel "Home" als ContentPane für das Hauptfenster festgelegt. Bei Auswahl von "Abmelden"
     * wird das Hauptfenster ausgeblendet und das Login-Fenster wieder angezeigt.
     *
     * @see View_Package.Menu_Bar
     * @see View_Package.Panel_Home
     * @see View_Package.Haupt_View
     * @see View_Package.Login_View
     * @author Rafik
     * @version 1.0.0
     */

    public class MenuHomeHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == haupt_view.getMenu_bar().getItem_exit()) {
                System.exit(-1);
            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_back_homepage()) {
                haupt_view.setContentPane(haupt_view.getPanel_home());
            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_abmelden()) {
                haupt_view.dispose();
                loginView.getPanel_login().getPf_password().setText("");
                loginView.setVisible(true);
            }
        }
    }
    /**
     * ActionListener für die Menüoptionen "Raum einfügen", "Raum löschen", "Raumliste" und "RaumNr editieren".
     * Reagiert auf Klick-Ereignisse dieser Menüpunkte. Die Klasse speichert das zuletzt geklickte Element,
     * setzt das Panel "Raum" als ContentPane für das Hauptfenster, aktualisiert Daten und passt die Sichtbarkeit
     * der Komponenten entsprechend an.
     *
     * @see View_Package.Menu_Bar
     * @see View_Package.Panel_Raum
     * @see View_Package.Haupt_View
     * @author Rafik
     * @version 1.0.0
     */
    public class MenuRaumHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == haupt_view.getMenu_bar().getItem_add_raum()) {

                LastClickedItemTracker.setLastClickedItem("item_add_raum");
                haupt_view.setContentPane(haupt_view.getPanel_raum());
                haupt_view.getPanel_raum().changeVisibility("add");
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_update_raum()) {

                LastClickedItemTracker.setLastClickedItem("item_update_raum");
                haupt_view.setContentPane(haupt_view.getPanel_raum());
                haupt_view.getPanel_raum().changeVisibility("update");
                updateTable(haupt_view.getPanel_raum().getTable_Raum(), model.SELECT_RAUM_TABLE, 2);
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_delete_raum()) {

                LastClickedItemTracker.setLastClickedItem("item_delete_raum");
                haupt_view.setContentPane(haupt_view.getPanel_raum());
                haupt_view.getPanel_raum().changeVisibility("delete");
                updateTable(haupt_view.getPanel_raum().getTable_Raum(), model.SELECT_RAUM_TABLE, 2);
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_raum_list()) {

                LastClickedItemTracker.setLastClickedItem("item_raum_list");
                haupt_view.setContentPane(haupt_view.getPanel_raum());
                haupt_view.getPanel_raum().changeVisibility("list");
                updateTable(haupt_view.getPanel_raum().getTable_Raum(), model.SELECT_RAUM_TABLE, 2);
                haupt_view.revalidate(); //das Layout des FrameS neu validieren.
                haupt_view.repaint(); //das Frame neu zeichnen, um sicherzustellen, dass die Änderungen auf der Benutzeroberfläche sichtbar werden.

            }
        }
    }

    /**
     * ActionListener für den Button "Raum einfügen" im Raum-Panel. Bei jedem Klick werden die eingegebene Raumnummer und der Standardstatus aus der Benutzeroberfläche extrahiert.
     * Wenn die Raumnummer im richtigen Format ist, wird versucht, einen neuen Raum in die Datenbank zu speichern. Andernfalls wird eine Fehlermeldung angezeigt.
     * @author Rafik
     * @version 1.0.0
     */

    public class BtnAddRaumHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabeRN = haupt_view.getPanel_raum().getTf_raum_nr().getText().trim();
            String defaultStatus = (String) haupt_view.getPanel_raum().getCb_status().getSelectedItem();
            boolean formatAkzeptiert = IPO.check_RaumNr(eingabeRN);

            String insertQuery = model.INSERT_RAUM_QUERY;
            insertQuery = String.format(insertQuery, eingabeRN, defaultStatus);

            if (formatAkzeptiert) {
                tryToInsert(insertQuery,eingabeRN);
            } else {
                haupt_view.displayErrorMessage("Format nicht akzeptiert! \n Die Raumnummer soll mit dem Großbuchstaben R beginnen, \ngefolgt von genau  2 Ziffern ");
            }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage auszuführen, um einen neuen Raum in die Datenbank einzufügen.
         * Zeigt eine Erfolgsmeldung an, wenn der Raum erfolgreich hinzugefügt wurde, und setzt die Platzhalter der Benutzeroberfläche zurück.
         * Behandelt verschiedene Ausnahmen, einschließlich Treiberprobleme, ungültige Datenbank-Anmeldeinformationen
         * oder das Verletzen des Unique-Constraints "PK_Raum".
         *
         * @param insertQuery Die SQL-Abfrage für das Einfügen eines neuen Raums in die Datenbank.
         * @param eingabeRN   Die eingegebene Raumnummer, um sie in der Fehlermeldung anzuzeigen im Falle einer Duplikation.
         */

        private void tryToInsert(String insertQuery , String eingabeRN){

            try {
                int row_Inserted = model.dmlOperation(insertQuery);
                haupt_view.displayMessage(row_Inserted + "  Raum wurde erfolgreich eingefügt ");
                haupt_view.getPanel_raum().setPlaceHolder();
            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");

            } catch (SQLException ex) {
                //DUP_VAL_ON_INDEX   ORA-00001: (Unique) CONSTRAINT PK_Raum verletzt.
                if (ex.getErrorCode() == 1) {
                    haupt_view.displayErrorMessage(" Die von Ihnen eingegebne Raumnummer :  " + eingabeRN + "  ist bereits vorhanden !");

                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }

    /**
     * Reagiert auf Mausklick-Ereignisse in der Raum-Tabelle (abgekürzt TR) des Raum-Panels (abgekürzt PR).
     * Bei jedem Mausklick auf eine Zeile der Tabelle wird die Raumnummer der ausgewählten Zeile extrahiert
     * und im entsprechenden Textfeld des Raum-Panels angezeigt.
     * Zudem wird der Fokus auf das Textfeld für die neue Raumnummer gesetzt, um die Bearbeitung zu erleichtern.
     * Dieser Schritt bildet den ersten Schritt zur Aktualisierung der Raumdaten. Für den zweiten und letzten Schritt ist die Klasse {@link BtnUpdateRaumHandler} zuständig.
     * @author Rafik
     * @version 1.0.0
     */
    public class PR_TR_MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = haupt_view.getPanel_raum().getTable_Raum().getSelectedRow();
            if (selectedRow != -1) {
                String alteRaumNr = (String) haupt_view.getPanel_raum().getTable_Model_Raum().getValueAt(selectedRow, 0);
                haupt_view.getPanel_raum().getTf_AlteRaumNr().setText(alteRaumNr);
                haupt_view.getPanel_raum().getTf_raum_nr().requestFocus();
            }
        }
    }
    /**
     * ActionListener für den Button "Raum aktualisieren" im Raum-Panel. Bei jedem Klick extrahiert diese Klasse
     * die eingegebene neue Raumnummer und die vorherige Raumnummer aus der Benutzeroberfläche.
     * Sie prüft das Format der Raumnummer und versucht dann, die Raumdaten in der Datenbank zu aktualisieren,
     * sofern gültig und keine Fehler bei der Aktualisierung auftreten.
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnUpdateRaumHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabe_nRN = haupt_view.getPanel_raum().getTf_raum_nr().getText().trim();
            String alteRaumNr = haupt_view.getPanel_raum().getTf_AlteRaumNr().getText();
            boolean formatAkzeptiert = IPO.check_RaumNr(eingabe_nRN);
            String update_query = model.UPDATE_RAUM_NR;
            update_query = String.format(update_query, eingabe_nRN, alteRaumNr);

            if (!alteRaumNr.isEmpty()){
                if (formatAkzeptiert) {
                    if (!eingabe_nRN.equals(alteRaumNr)) {    //Keine Fehlermeldung eigentlich, wenn man versucht einen PK durch ihn selbst zu ersetzen.

                        tryToUpdate(update_query,eingabe_nRN);

                    } else {
                        haupt_view.displayErrorMessage("Die alte und  neue Raumnummer sollten unterschiedliche Werte haben !");
                        haupt_view.getPanel_raum().getTf_raum_nr().requestFocus();
                        haupt_view.getPanel_raum().getTf_raum_nr().selectAll();
                    }
                } else {
                    haupt_view.displayErrorMessage("Format nicht akzeptiert! \n Die Raumnummer soll mit dem Großbuchstaben R beginnen, \ngefolgt von genau  2 Ziffern ");
                    haupt_view.getPanel_raum().getTf_raum_nr().requestFocus();
                    haupt_view.getPanel_raum().getTf_raum_nr().selectAll();
                }
            }else{
                haupt_view.displayErrorMessage("Keine Zeile markiert!\nSelektieren Sie erstmal den Raum ,den Sie aktualisieren möchte");
            }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage auszuführen, um Raumdaten in der Datenbank zu aktualisieren.
         * Wenn die Aktualisierung erfolgreich ist, zeigt sie eine Erfolgsmeldung an und aktualisiert die Benutzeroberfläche.
         * Sie behandelt mögliche Ausnahmen, darunter fehlende Treiber, ungültige Datenbank-Anmeldeinformationen oder das Verletzen des Unique-Constraints "PK_Raum".
         *
         * @param update_query Die SQL-Abfrage für das Aktualisieren der Raumdaten.
         * @param eingabe_nRN Die eingegebene neue Raumnummer für Fehlermeldungen im Falle einer Duplikation.
         */

        private void tryToUpdate(String update_query ,String eingabe_nRN){
            try {
                int rows_Updated = model.dmlOperation(update_query);
                haupt_view.displayMessage(rows_Updated + "  Raum  wurde aktualisiert  ");
                haupt_view.getPanel_raum().setPlaceHolder();
                updateTable(haupt_view.getPanel_raum().getTable_Raum(), model.SELECT_RAUM_TABLE, 2);


            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1) {
                    haupt_view.displayErrorMessage(" Die von Ihnen eingegebne Raumnummer :  " + eingabe_nRN + "  ist bereits vorhanden !");
                    haupt_view.getPanel_raum().getTf_raum_nr().requestFocus();
                    haupt_view.getPanel_raum().getTf_raum_nr().selectAll();

                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }
    /**
     * ActionListener für den Button "Selektierten Raum löschen" im Raum-Panel. Beim Klicken wird der ausgewählte Raum sowohl aus der Datenbank als auch aus der Benutzeroberfläche entfernt.
     * Die erste Voraussetzung für das Löschen ist die Auswahl einer Zeile in der Raumtabelle.
     * Diese Klasse überwacht den Löschvorgang für Raumdaten und behandelt verschiedene auftretende Ausnahmen.
     * @author Rafik
     * @version 1.0.0
     */


    public class BtnDeleteRaumHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int selectedRow = haupt_view.getPanel_raum().getTable_Raum().getSelectedRow();
            if (selectedRow != -1) {

                String raumNr = (String) haupt_view.getPanel_raum().getTable_Model_Raum().getValueAt(selectedRow, 0);
                String delete_query =model.DELETE_RAUM;
                delete_query = String.format(delete_query, raumNr);

                tryToDelete(delete_query,raumNr,selectedRow);

            } else {
                haupt_view.displayErrorMessage("Keine Zeile markiert!");
            }
        }
        /**
         * Versucht, den angegebenen Raum aus der Datenbank zu löschen und die entsprechende Zeile in der Raumtabelle aus der Benutzeroberfläche zu entfernen.
         * Zeigt eine Erfolgsmeldung an, wenn der Raum erfolgreich gelöscht wurde.
         * Behandelt mögliche Exceptions wie Treiberprobleme, ungültige Datenbank-Anmeldeinformationen und Integritätsverletzungen (FK_ARZT_RAUM).
         *
         * @param delete_query Die SQL-Abfrage zum Löschen eines Raums aus der Datenbank.
         * @param raumNr       Die Nummer des zu löschenden Raums.
         * @param selectedRow  Der Index der ausgewählten Zeile, die aus der Raumtabelle entfernt werden muss, damit die Benutzeroberfläche aktuell bleibt.
         */

        private void tryToDelete(String delete_query,String raumNr,int selectedRow){
            try {
                model.dmlOperation(delete_query);
                haupt_view.getPanel_raum().getTable_Model_Raum().removeRow(selectedRow);
                haupt_view.displayMessage("Der Raum mit der Nummer  : " + raumNr + " wurde erfolgreich gelöscht");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                //ORA-02292: Integritäts-Constraint (FK_ARZT_RAUM) verletzt - untergeordneter Datensatz gefunden
                if (ex.getErrorCode() == 2292) {
                    haupt_view.displayErrorMessage(" Der Raum mit der Nummer  :  " + raumNr + " gehört einem Arzt \n Räume, die besetzt sind können nicht gelöscht werden ");
                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }

    /**
     * ActionListener für die Menüoptionen "Arzt einfügen", "Arzt löschen", "Arztdaten ändern" und "Arzt-Liste".
     * Reagiert auf Klick-Ereignisse dieser Menüpunkte. Die Klasse speichert das zuletzt geklickte Element,
     * setzt das Panel "Arzt" als ContentPane für das Hauptfenster, aktualisiert Daten und passt die Sichtbarkeit
     * der Komponenten entsprechend an.
     *
     * @see View_Package.Menu_Bar
     * @see View_Package.Panel_Arzt
     * @see View_Package.Haupt_View
     * @author Rafik
     * @version 1.0.0
     */
    public class MenuArztHandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == haupt_view.getMenu_bar().getItem_add_arzt()) {

                LastClickedItemTracker.setLastClickedItem("item_add_arzt");
                haupt_view.setContentPane(haupt_view.getPanel_arzt());
                haupt_view.getPanel_arzt().changeVisibility("add");
                displayFreeRoomNumbers();
                haupt_view.revalidate();
                haupt_view.repaint();


            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_update_arzt()) {

                LastClickedItemTracker.setLastClickedItem("item_update_arzt");
                haupt_view.setContentPane(haupt_view.getPanel_arzt());
                haupt_view.getPanel_arzt().changeVisibility("update");
                updateTable(haupt_view.getPanel_arzt().getTable_Arzt(), model.SELECT_ARZT, 7);

                haupt_view.revalidate();
                haupt_view.repaint();


            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_delete_arzt()) {

                LastClickedItemTracker.setLastClickedItem("item_delete_arzt");
                haupt_view.setContentPane(haupt_view.getPanel_arzt());
                haupt_view.getPanel_arzt().changeVisibility("delete");
                updateTable(haupt_view.getPanel_arzt().getTable_Arzt(), model.SELECT_ARZT, 7);
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_arzt_list()) {

                LastClickedItemTracker.setLastClickedItem("item_arzt_list");
                haupt_view.setContentPane(haupt_view.getPanel_arzt());
                haupt_view.getPanel_arzt().changeVisibility("list");
                updateTable(haupt_view.getPanel_arzt().getTable_Arzt(), model.SELECT_ARZT, 7);
                haupt_view.revalidate();
                haupt_view.repaint();
            }

        }
    }



    /**
     * ActionListener für den Button "Arzt einfügen" im Arzt-Panel. Überwacht die Benutzereingaben und versucht, einen neuen Arzt in die Datenbank einzufügen.
     * Prüft die Vor- und Nachnamen auf zulässige Formate und die Existenz von freien Räumen in der Arztpraxis, bevor ein neuer Arzt hinzugefügt wird.
     * @author Rafik
     * @version 1.0.0
     */

    public class BtnAddArztHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String anrede = (String) haupt_view.getPanel_arzt().getCb_anrede().getSelectedItem();
            String titel = (String) haupt_view.getPanel_arzt().getCb_titel().getSelectedItem();
            String vorname = haupt_view.getPanel_arzt().getTf_vorname().getText().trim();
            String nachname = haupt_view.getPanel_arzt().getTf_nachname().getText().trim();
            String raumNr = (String) haupt_view.getPanel_arzt().getCb_raumNr().getSelectedItem();
            String beruf = (String) haupt_view.getPanel_arzt().getCb_beruf().getSelectedItem();
            boolean vornameIsValid = IPO.check_Name(vorname);
            boolean nachnameIsValid = IPO.check_Name(nachname);
            boolean raumVorhanden = IPO.itemExists(haupt_view.getPanel_arzt().getCb_raumNr());
            String insert_query = model.INSERT_ARZT;
            insert_query = String.format(insert_query, anrede, titel, vorname, nachname, raumNr, beruf);

            if (raumVorhanden) {
                if (!anrede.equals("--") && !titel.equals("--") && !beruf.equals("--") && !raumNr.equals("--")) {
                    if (vornameIsValid && nachnameIsValid) {
                      tryToInsert(insert_query);
                    } else {
                        haupt_view.displayErrorMessage("Format nicht zulässig für den Vor/Nachnamen!\nNur Buchstaben des deutschen Alphabets sind erlaubt.\nMindestens 2 und maximal 30 Buchstaben sind zulässig.");
                    }
                } else {
                    haupt_view.displayErrorMessage("Die mit (*) markierten Felder sind Pflichtfelder!");
                }
            } else {
                haupt_view.displayErrorMessage("Momentan sind  alle Räume besetzt.\nFügen Sie zuerst einen Raum bevor Sie einen neuen Arzt einfügen.\nStellen Sie sicher,dass der Raum wirklich in der Praxis existiert.");
                haupt_view.getPanel_arzt().setPlaceHolder();
            }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage auszuführen, um einen neuen Arzt in die Datenbank einzufügen.
         * Falls keine Exception abgefangen wurde und nach erfolgreichem Einfügen des Arztes, aktualisiert diese Klasse die freien Raumnummern,
         * setzt die Platzhalter der Benutzeroberfläche zurück und zeigt eine Erfolgsmeldung an.
         * Behandelt mögliche Exceptions wie Treiberprobleme, ungültige Datenbank-Anmeldeinformationen oder allgemeine SQL-Fehler.
         *
         * @param insert_query Die SQL-Abfrage für das Hinzufügen eines neuen Arztes in die Datenbank.
         */

        private void tryToInsert(String insert_query){
            try {
                int row_Inserted = model.dmlOperation(insert_query);
                displayFreeRoomNumbers();
                haupt_view.getPanel_arzt().setPlaceHolder();
                haupt_view.displayMessage(row_Inserted + " Arzt wurde erfolgreich eingefügt ");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
            }
        }
    }

    /**
     * ActionListener für den Button "Selektierten Arzt löschen" aus dem Arzt-Panel. Beim Klicken wird der ausgewählte Arzt sowohl aus der Datenbank als auch aus der Benutzeroberfläche gelöscht.
     * Die erste Voraussetzung für das Löschen ist, dass der Benutzer eine Zeile aus der Arzt-Tabelle ausgewählt hat.
     * Diese Klasse überwacht den Löschvorgang für die Arztdaten, zeigt entsprechende Fehlermeldungen an und behandelt verschiedene auftretende Ausnahmen.
     * @author Rafik
     * @version 1.0.0
     */

    public class BtnDeleteArztHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int selectedRow = haupt_view.getPanel_arzt().getTable_Arzt().getSelectedRow();
            if (selectedRow != -1) {
                String arztID = (String) haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 0);
                String delete_query = model.DELETE_ARZT;
                delete_query = String.format(delete_query, arztID);
                tryToDelete(delete_query,selectedRow,arztID);

            } else {
                haupt_view.displayErrorMessage("Keine Zeile markiert!");
            }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage auszuführen, um einen bestimmten Arzt aus der Datenbank zu löschen.
         * Falls das Löschen aus der Datenbank erfolgreich war, wird die selektierte Zeile auch aus der Arzttabelle gelöscht und eine Erfolgsmeldung angezeigt.
         * Die Klasse behandelt verschiedene Ausnahmen, die während des Löschvorgangs auftreten können, wie Treiberprobleme, ungültige Datenbank-Anmeldeinformationen
         * oder Integritätsverletzungen (FK_TERMIN_ARZT oder FK_PATIENT_BETREUER) .
         *
         * @param delete_query Die SQL-Abfrage für das Entfernen des Arztes aus der Datenbank.
         * @param selectedRow  Der Index der ausgewählten Zeile, die aus der Arzttabelle entfernt werden muss, damit die Benutzeroberfläche aktuell bleibt.
         * @param arztID       Die eindeutige ID des zu löschenden Arztes.
         */

        private void tryToDelete(String delete_query,int selectedRow,String arztID){
            try {
                model.dmlOperation(delete_query);
                haupt_view.getPanel_arzt().getTable_Model_Arzt().removeRow(selectedRow);
                haupt_view.displayMessage("Der Arzt mit der ID : " + arztID + " wurde erfolgreich gelöscht");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 2292) {
                    haupt_view.displayErrorMessage("Der Arzt mit der ID  :  " + arztID + "ist entweder der Betreuer eines bestimmten Patienten oder hat Termine und kann nicht gelöscht werden ");

                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }


    /**
     * Dieser MouseListener reagiert ausschließlich auf Mausklicks in der Arzt-Tabelle (abgekürzt TA) des Arzt-Panels (abgekürzt PA), wenn der Menüpunkt "item_update_arzt" ausgewählt ist.
     * Bei jedem Klick auf einen Eintrag in der Tabelle werden die Daten des ausgewählten Arztes in die Benutzeroberfläche geladen.
     * Dadurch werden die Informationen für eine potenzielle Aktualisierung vorbereitet.
     * Dieser Schritt bildet den ersten Schritt zur Aktualisierung der Arztdaten. Die Klasse {@link BtnUpdateArztHandler} übernimmt den zweiten und letzten Schritt.
     * @author Rafik
     * @version 1.0.0
     */
    public class PA_TA_MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (LastClickedItemTracker.getLastClickedItem().equals("item_update_arzt")) {
                int selectedRow = haupt_view.getPanel_arzt().getTable_Arzt().getSelectedRow();
                if (selectedRow != -1) {
                    haupt_view.getPanel_arzt().setVisibilityAfterPicking();

                    String arztID = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 0).toString();
                    String anrede = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 1).toString();
                    String titel = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 2).toString();
                    String vorname = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 3).toString();
                    String nachname = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 4).toString();
                    String raumNr = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 5).toString();
                    String beruf = haupt_view.getPanel_arzt().getTable_Model_Arzt().getValueAt(selectedRow, 6).toString();

                    haupt_view.getPanel_arzt().getTf_arztID().setText(arztID);
                    haupt_view.getPanel_arzt().getCb_anrede().setSelectedItem(anrede);
                    haupt_view.getPanel_arzt().getCb_titel().setSelectedItem(titel);
                    haupt_view.getPanel_arzt().getTf_vorname().setText(vorname);
                    haupt_view.getPanel_arzt().getTf_nachname().setText(nachname);
                    haupt_view.getPanel_arzt().getCb_beruf().setSelectedItem(beruf);

                    displayFreeRoomNumbers();
                    haupt_view.getPanel_arzt().getCb_raumNr().addItem(raumNr);
                    haupt_view.getPanel_arzt().getCb_raumNr().setSelectedItem(raumNr);
                }
            }
        }
    }
    /**
     * ActionListener für den Button "Arztdaten ändern" im Arzt-Panel. Bei jedem Klick überwacht dieser Listener den Aktualisierungsvorgang der Arztdaten.
     * Er prüft die vollständige und korrekte Ausfüllung aller erforderlichen Felder und versucht dann, die bearbeiteten Arztdaten
     * in der Datenbank zu aktualisieren.
     * @author Rafik
     * @version 1.0.0
     */


    public class BtnUpdateArztHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String arztID = haupt_view.getPanel_arzt().getTf_arztID().getText();
            String anrede = (String) haupt_view.getPanel_arzt().getCb_anrede().getSelectedItem();
            String titel = (String) haupt_view.getPanel_arzt().getCb_titel().getSelectedItem();
            String vorname = haupt_view.getPanel_arzt().getTf_vorname().getText().trim();
            String nachname = haupt_view.getPanel_arzt().getTf_nachname().getText().trim();
            String beruf = (String) haupt_view.getPanel_arzt().getCb_beruf().getSelectedItem();
            String raumNr = (String) haupt_view.getPanel_arzt().getCb_raumNr().getSelectedItem();
            boolean vornameIsValid = IPO.check_Name(vorname);
            boolean nachnameIsValid = IPO.check_Name(nachname);

            String update_query = model.UPDATE_ARZT;
            update_query = String.format(update_query,  anrede, titel, vorname, nachname, raumNr, beruf, arztID);

            if (!anrede.equals("--") && !titel.equals("--") && !beruf.equals("--") && !raumNr.equals("--")) {
                if (vornameIsValid && nachnameIsValid) {
                    tryToUpdate(update_query);
                } else {
                    haupt_view.displayErrorMessage("Format nicht zulässig für den Vor/Nachnamen!\nNur Buchstaben des deutschen Alphabets sind erlaubt.\nMindestens 2 und maximal 30 Buchstaben sind zulässig.");
                }
            } else {
                haupt_view.displayErrorMessage("Die mit (*) markierten Felder sind Pflichtfelder!");
            }
        }
        /**
         * Versucht, die bearbeiteten Arztdaten in der Datenbank zu aktualisieren, basierend auf der angegebenen SQL-Abfrage.
         * Zeigt eine Erfolgsmeldung an, wenn die Aktualisierung erfolgreich war, aktualisiert die Arzt-Tabelle in der Benutzeroberfläche
         * und passt die Sichtbarkeit des Arzt-Panels entsprechend an.
         * Sie behandelt mögliche Ausnahmen, darunter fehlende Treiber, ungültige Datenbank-Anmeldeinformationen oder unerwartete SQL-Fehler.
         *
         * @param update_query Die SQL-Abfrage zur Aktualisierung der Arztdaten in der Datenbank.
         */

        private void tryToUpdate(String update_query){
            try {
                int row_Updated = model.dmlOperation(update_query);
                haupt_view.displayMessage(row_Updated + " Arzt wurde erfolgreich aktualisiert  ");
                updateTable(haupt_view.getPanel_arzt().getTable_Arzt(), model.SELECT_ARZT, 7);
                haupt_view.getPanel_arzt().changeVisibility("update");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
            }
        }
    }
    /**
     * ActionListener für die Menüoptionen "Patient einfügen", "Patient löschen", "Patient-Liste" und "Patientendaten ändern".
     * Reagiert auf Klick-Ereignisse dieser Menüpunkte. Die Klasse speichert das zuletzt geklickte Element,
     * setzt das Panel "Patient" als ContentPane für das Hauptfenster, aktualisiert Daten und passt die Sichtbarkeit
     * der Komponenten entsprechend an.
     *
     *@see View_Package.Menu_Bar
     *@see View_Package.Panel_Patient
     *@see View_Package.Haupt_View
     * @author Rafik
     * @version 1.0.0
     */
    public class MenuPatientHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == haupt_view.getMenu_bar().getItem_add_patient()) {

                LastClickedItemTracker.setLastClickedItem("item_add_patient");
                haupt_view.setContentPane(haupt_view.getPanel_patient());
                haupt_view.getPanel_patient().changeVisibility("add");
                updateTable(haupt_view.getPanel_patient().getArztTable(), model.SELECT_ARZT, 7);
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_delete_patient()) {

                LastClickedItemTracker.setLastClickedItem("item_delete_patient");
                haupt_view.setContentPane(haupt_view.getPanel_patient());
                haupt_view.getPanel_patient().changeVisibility("delete");
                updateTable(haupt_view.getPanel_patient().getTable_Patient(), model.SELECT_PATIENT, 7);
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_list_patient()) {

                LastClickedItemTracker.setLastClickedItem("item_list_patient");
                haupt_view.setContentPane(haupt_view.getPanel_patient());
                haupt_view.getPanel_patient().changeVisibility("list");
                updateTable(haupt_view.getPanel_patient().getTable_Patient(), model.SELECT_PATIENT, 7);
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_update_patient()) {

                LastClickedItemTracker.setLastClickedItem("item_update_patient");
                haupt_view.setContentPane(haupt_view.getPanel_patient());
                haupt_view.getPanel_patient().changeVisibility("update");
                updateTable(haupt_view.getPanel_patient().getTable_Patient(), model.SELECT_PATIENT, 7);
                updateTable(haupt_view.getPanel_patient().getArztTable(), model.SELECT_ARZT, 7);
                haupt_view.revalidate();
                haupt_view.repaint();
            }
        }
    }
    /**
     * ActionListener für den Button "Patienten löschen" aus dem Patienten-Panel.
     * Beim Klicken wird der ausgewählte Patient sowohl aus der Datenbank als auch aus der Benutzeroberfläche gelöscht.
     * Die erste Voraussetzung für das Löschen ist, dass der Benutzer eine Zeile aus der Patienten-Tabelle ausgewählt hat.
     * Diese Klasse überwacht den Löschvorgang für die Patientendaten, zeigt entsprechende Fehlermeldungen an und behandelt verschiedene auftretende Ausnahmen.
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnDeletePatientHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = haupt_view.getPanel_patient().getTable_Patient().getSelectedRow();
            if (selectedRow != -1) {
                String patientID = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 0).toString();
                String delete_query = model.DELETE_PATIENT;
                delete_query = String.format(delete_query, patientID);

                tryToDelete(delete_query,patientID,selectedRow);

            } else {
                haupt_view.displayErrorMessage("Keine Zeile markiert");
            }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage auszuführen, um einen bestimmten Patienten aus der Datenbank zu löschen.
         * Wenn das Löschen aus der Datenbank erfolgreich ist, wird auch die selektierte Zeile aus der Patiententabelle gelöscht und eine Erfolgsmeldung angezeigt.
         * Diese Methode behandelt verschiedene Ausnahmen, die während des Löschvorgangs auftreten können, wie Treiberprobleme, ungültige Datenbank-Anmeldeinformationen
         * oder Integritätsverletzungen (FK_TERMIN_PATIENT).
         *
         * @param delete_query Die SQL-Abfrage zum Entfernen des Patienten aus der Datenbank.
         * @param patientID    Die eindeutige ID des zu löschenden Patienten.
         * @param selectedRow  Der Index der ausgewählten Zeile, die aus der Patiententabelle entfernt werden muss, um die Benutzeroberfläche aktuell zu halten.
         */
        private void tryToDelete(String delete_query ,String patientID,int selectedRow){
            try {
                model.dmlOperation(delete_query);
                haupt_view.getPanel_patient().getTable_Model_Patient().removeRow(selectedRow);
                haupt_view.displayMessage("Der Patient  mit der ID : " + patientID + " wurde erfolgreich gelöscht");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 2292) {
                    haupt_view.displayErrorMessage("Der Patient mit der ID  :  " + patientID + " hat bereits Termine und kann nicht gelöscht werden ");

                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }
    /**
     * Dieser MouseListener reagiert ausschließlich auf Mausklicks in der Arzt-Tabelle (abgekürzt TA) des Patienten-Panels (abgekürzt PP).
     * Bei jedem Klick auf einen Eintrag in der Arzt-Tabelle wird die Arzt-ID des ausgewählten Arztes in das zugehörige Textfeld übernommen.
     * Das Zuweisen eines neuen Patienten zu einem Arzt (Betreuer) ist ein erforderlicher Schritt beim Hinzufügen neuer Patienten.
     * Weitere Schritte fürs Einfügen werden von der Klasse {@link BtnAddPatientHandler} übernommen.
     * Ebenfalls kommt diese Klasse beim Aktualisieren von Patientendaten zum Einsatz, indem sie dem Benutzer ermöglicht, den Betreuer eines Patienten zu wechseln.
     * Fürs Aktualisieren ist die Klasse {@link BtnUpdatePatientHandler} verantwortlich.
     * @author Rafik
     * @version 1.0.0
     */
    public class PP_TA_MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = haupt_view.getPanel_patient().getArztTable().getSelectedRow();
            if (selectedRow != -1) {
                String betreuerID = (String) haupt_view.getPanel_patient().getArztTable().getValueAt(selectedRow, 0);
                haupt_view.getPanel_patient().getTf_arztID().setText(betreuerID);
            }
        }
    }

    /**
     * ActionListener für den Button "Patient hinzufügen" im Patienten-Panel.
     * Überwacht die Benutzereingaben bei jedem Klick, überprüft die vollständige und korrekte Ausfüllung aller erforderlichen Felder
     * und versucht anschließend, einen neuen Patienten in die Datenbank einzufügen.
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnAddPatientHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String anrede = (String) haupt_view.getPanel_patient().getCb_anrede().getSelectedItem();
            String vorname = haupt_view.getPanel_patient().getTf_vorname().getText().trim();
            String nachname = haupt_view.getPanel_patient().getTf_nachname().getText().trim();
            String telefon = haupt_view.getPanel_patient().getTf_telefon().getText().trim();
            Date date = haupt_view.getPanel_patient().getDc_geburtsdatum().getDate();
            String arztID = haupt_view.getPanel_patient().getTf_arztID().getText();
            boolean vornameIsValid = IPO.check_Name(vorname);
            boolean nachnameIsValid = IPO.check_Name(nachname);
            boolean telefonIsValid = IPO.check_Telefon(telefon);

            if (!anrede.equals("--") && !vorname.equals("") && !nachname.equals("") && !telefon.equals("") && date!=null && !arztID.equals("")) {
                if (vornameIsValid && nachnameIsValid) {
                    if (telefonIsValid == true) {
                        String geburtsdatum =IPO.getFormattedDate(date);
                        String insert_query= model.INSERT_PATIENT;
                        insert_query = String.format(insert_query, anrede, vorname, nachname, telefon, geburtsdatum, arztID);
                        tryToInsert(insert_query);
                    } else {
                        haupt_view.displayErrorMessage("Die eingegebene Telefonnummer ist ungültig!\nBitte geben Sie eine Telefonnummer ein, die mit '0' beginnt und zwischen 10 und 13 Ziffern lang ist (ohne die führende '0')");
                    }
                } else {
                    haupt_view.displayErrorMessage("Format nicht zulässig für den Vor/Nachnamen!\nNur Buchstaben des deutschen Alphabets sind erlaubt.\nMindestens 2 und maximal 30 Buchstaben sind zulässig.");
                }
            } else {
                haupt_view.displayErrorMessage("Die mit (*) markierten Felder sind Pflichtfelder!");
            }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage auszuführen.
         * Behandelt mögliche Ausnahmen, die beim Einfügen auftreten können, wie das Fehlen des Treibers, ungültige Datenbank-Anmeldeinformationen
         * oder das Einfügen von Telefonnummer-Duplikaten.
         *
         * @param insert_query Die SQL-Abfrage zum Einfügen des neuen Patienten in die Datenbank.
         */
        private void tryToInsert(String insert_query){
            try {
                int row_Inserted = model.dmlOperation(insert_query);
                haupt_view.getPanel_patient().setPlaceHolder();
                haupt_view.displayMessage(row_Inserted + " Patient wurde erfolgreich eingefügt ");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1) {
                    haupt_view.displayErrorMessage("Es darf keine Duplikate für die Telefonnummer eingegeben werden!");
                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }

    /**
     * Dieser MouseListener reagiert ausschließlich auf Mausklicks in der Patienten-Tabelle (abgekürzt TP) des Patienten-Panels (abgekürzt PP), wenn der Menüpunkt "item_update_patient" ausgewählt ist.
     * Bei jedem Klick auf einen Eintrag in der Tabelle werden die Daten des ausgewählten Patienten in die Benutzeroberfläche geladen.
     * Dadurch werden die Informationen für eine potenzielle Aktualisierung vorbereitet.
     * Dieser Schritt bildet den ersten Schritt zur Aktualisierung der Patientendaten. Die Klasse {@link BtnUpdatePatientHandler} übernimmt den zweiten und letzten Schritt.
     * @author Rafik
     * @version 1.0.0
     */
    public class PP_TP_MouseListener extends  MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(LastClickedItemTracker.getLastClickedItem().equals("item_update_patient")){
                int selectedRow = haupt_view.getPanel_patient().getTable_Patient().getSelectedRow();
                if (selectedRow != -1) {
                    haupt_view.getPanel_patient().setVisibilityAfterPickingPatient();

                    String patientID = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 0).toString();
                    String anrede = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 1).toString();
                    String vorname = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 2).toString();
                    String nachname = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 3).toString();
                    String telefon = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 4).toString();
                    String geburtsdatum = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 5).toString();
                    String arztID = haupt_view.getPanel_patient().getTable_Model_Patient().getValueAt(selectedRow, 6).toString();

                    haupt_view.getPanel_patient().getTf_patientID().setText(patientID);
                    haupt_view.getPanel_patient().getCb_anrede().setSelectedItem(anrede);
                    haupt_view.getPanel_patient().getTf_vorname().setText(vorname);
                    haupt_view.getPanel_patient().getTf_nachname().setText(nachname);
                    haupt_view.getPanel_patient().getTf_telefon().setText(telefon);
                    haupt_view.getPanel_patient().getTf_arztID().setText(arztID);

                    Date date = IPO.convert_String_to_Date(geburtsdatum);
                    haupt_view.getPanel_patient().getDc_geburtsdatum().setDate(date);
                }
            }
        }
    }
    /**
     * ActionListener für den Button "Patientendaten ändern" im Patienten-Panel. Bei jedem Klick überwacht diese Klasse den Aktualisierungsvorgang der Patientendaten.
     * Sie prüft die vollständige und korrekte Ausfüllung aller erforderlichen Felder und versucht dann, die bearbeiteten Patientendaten zu aktualisieren.
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnUpdatePatientHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

                String patientID = haupt_view.getPanel_patient().getTf_patientID().getText();
                String anrede = (String) haupt_view.getPanel_patient().getCb_anrede().getSelectedItem();
                String vorname = haupt_view.getPanel_patient().getTf_vorname().getText().trim();
                String nachname = haupt_view.getPanel_patient().getTf_nachname().getText().trim();
                String telefon = haupt_view.getPanel_patient().getTf_telefon().getText().trim();
                Date date  = haupt_view.getPanel_patient().getDc_geburtsdatum().getDate();
                String arztID = haupt_view.getPanel_patient().getTf_arztID().getText();
                boolean vornameIsValid = IPO.check_Name(vorname);
                boolean nachnameIsValid = IPO.check_Name(nachname);
                boolean telefonIsValid = IPO.check_Telefon(telefon);

                if (!anrede.equals("--") && !vorname.equals("") && !nachname.equals("") && !telefon.equals("") && date!=null && !arztID.equals("--")) {
                    if (vornameIsValid && nachnameIsValid) {
                        if (telefonIsValid == true) {

                            String geburtsdatum = IPO.getFormattedDate(date);
                            String update_query = model.UPDATE_PATIENT;
                            update_query = String.format(update_query,  anrede, vorname, nachname, telefon, geburtsdatum, arztID,patientID);
                            tryToUpdate(update_query);

                        } else {
                            haupt_view.displayErrorMessage("Die eingegebene Telefonnummer ist ungültig!\nBitte geben Sie eine Telefonnummer ein, die mit '0' beginnt und zwischen 10 und 13 Ziffern lang ist (ohne die führende '0')");
                        }
                    } else {
                        haupt_view.displayErrorMessage("Format nicht zulässig für den Vor/Nachnamen!\nNur Buchstaben des deutschen Alphabets sind erlaubt.\nMindestens 2 und maximal 30 Buchstaben sind zulässig.");
                    }
                } else {
                    haupt_view.displayErrorMessage("Die mit (*) markierten Felder sind Pflichtfelder!");
                }
        }
        /**
         * Versucht, die angegebene SQL-Abfrage zur Aktualisierung von Patientendaten in der Datenbank auszuführen.
         * Behandelt mögliche Ausnahmen, die während des Aktualisierungsvorgangs auftreten können, wie das Fehlen des Treibers, ungültige Datenbank-Anmeldeinformationen
         * oder Telefonnummer-Duplikaten.
         * Bei erfolgreicher Aktualisierung wird eine Erfolgsmeldung angezeigt, die Patiententabelle aktualisiert und Sichtbarkeit der Benutzeroberfläche angepasst.
         *
         * @param update_query Die SQL-Abfrage zur Aktualisierung der Patientendaten in der Datenbank.
         */
        private void tryToUpdate(String update_query){
            try {
                int row_Updated = model.dmlOperation(update_query);

                String selectQuery = model.SELECT_PATIENT;
                updateTable(haupt_view.getPanel_patient().getTable_Patient(), selectQuery, 7);

                haupt_view.getPanel_patient().changeVisibility("update");
                haupt_view.displayMessage(row_Updated + " Patient wurde erfolgreich aktualisiert ");

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1) {
                    haupt_view.displayErrorMessage("Es darf keine Duplikate für die Telefonnummer eingegeben werden!");
                } else {
                    haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                }
            }
        }
    }
    /**
     * ActionListener für die Menüoptionen "Termin vergeben", "Termin stornieren" und "Termin-Liste".
     * Reagiert auf Klick-Ereignisse dieser Menüpunkte. Die Klasse speichert das zuletzt geklickte Element,
     * setzt das Panel "Termin" als ContentPane für das Hauptfenster, aktualisiert Daten und passt die Sichtbarkeit
     * der Komponenten entsprechend an.
     * @see View_Package.Menu_Bar
     * @see View_Package.Panel_Termin
     * @see View_Package.Haupt_View
     * @author Rafik
     * @version 1.0.0
     */
    public class MenuTerminHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == haupt_view.getMenu_bar().getItem_add_termin()) {

                LastClickedItemTracker.setLastClickedItem("item_add_termin");
                haupt_view.setContentPane(haupt_view.getPanel_termin());
                haupt_view.getPanel_termin().changeVisibility("add");
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_drop_termin()) {

                LastClickedItemTracker.setLastClickedItem("item_drop_termin");
                haupt_view.setContentPane(haupt_view.getPanel_termin());
                haupt_view.getPanel_termin().changeVisibility("delete");
                haupt_view.revalidate();
                haupt_view.repaint();

            } else if (e.getSource() == haupt_view.getMenu_bar().getItem_list_termin()) {

                LastClickedItemTracker.setLastClickedItem("item_list_termin");
                haupt_view.setContentPane(haupt_view.getPanel_termin());
                updateTable(haupt_view.getPanel_termin().getTableTermin(), model.SELECT_TERMIN_DATEN, 7);
                haupt_view.getPanel_termin().changeVisibility("list");
                haupt_view.revalidate();
                haupt_view.repaint();
            }
        }
    }

    /**
     * ActionListener für den "Suchen"-Button im Termin-Panel, der in zwei verschiedenen Kontexten verwendet wird.
     * Wenn "item_add_termin" ausgewählt ist, werden alle Patienten angezeigt, die an dem gewählten Datum geboren sind.
     * Wenn "item_drop_termin" ausgewählt ist, werden alle geplanten Termine angezeigt, für Patienten, die an dem gewählten Datum geboren sind.
     * Bei fehlender Datumsauswahl oder wenn kein Patient mit dem ausgewählten Geburtsdatum in der Datenbank gefunden wird,
     * werden entsprechende Fehlermeldungen in der Benutzeroberfläche angezeigt.
     * Dies ist der erste Schritt des Termin-Vergabe-Prozesses. Der zweite Schritt ist Aufgabe der Klassen {@link PT_TP_MouseListener}.
     * Für den Termin-Stornierung-Prozess bildet diese Klasse den ersten Schritt. Der zweite und letzte Schritt ist Aufgabe der Klassen {@link BtnDeleteTerminHandler}.
     * @author Rafik
     * @version 1.0.0
     */

    public class BtnSuchenHandler implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (haupt_view.getPanel_termin().getDc_geburtsdatum().getDate() != null) {
                String geburtsdatum = IPO.getFormattedDate(haupt_view.getPanel_termin().getDc_geburtsdatum().getDate());

                if(LastClickedItemTracker.getLastClickedItem().equals("item_drop_termin")){
                    String selectQuery = model.TERMIN_SUCHEN;
                    selectQuery = String.format(selectQuery, geburtsdatum);
                    updateTable(haupt_view.getPanel_termin().getTableTermin(), selectQuery, 7);
                    if (haupt_view.getPanel_termin().getTableTermin().getRowCount() >= 1) {
                        haupt_view.getPanel_termin().getDc_geburtsdatum().setVisible(false);
                        haupt_view.getPanel_termin().getLb_geburtsdatum().setVisible(false);
                        haupt_view.getPanel_termin().getBtn_suchen().setVisible(false);
                        haupt_view.getPanel_termin().getScrollPaneTermin().setVisible(true);
                        haupt_view.getPanel_termin().getBtn_delete().setVisible(true);

                    } else {
                        haupt_view.displayErrorMessage("Es wurden keine Termine gefunden für Patienten ,die am  " + geburtsdatum + "  geboren sind !");
                    }
                }
                if(LastClickedItemTracker.getLastClickedItem().equals("item_add_termin")){
                    String selectQuery = model.PATIENT_SUCHEN;
                    selectQuery = String.format(selectQuery, geburtsdatum);
                    updateTable(haupt_view.getPanel_termin().getTablePatientSuche(), selectQuery, 5);
                    if (haupt_view.getPanel_termin().getTablePatientSuche().getDefault_Table_Model().getRowCount() > 0) {
                        haupt_view.getPanel_termin().getScrollPanePatientSuche().setVisible(true);
                    } else {
                        haupt_view.displayErrorMessage("Es wurde kein Patient gefunden, der am " + geburtsdatum + "geboren ist!");
                    }
                }
            } else {
                haupt_view.displayErrorMessage("Bitte wählen Sie das Geburtsdatum des Patienten,um zum nächsten Schritt zu gelangen");
            }
        }
    }
    /**
     * Dieser MouseListener reagiert ausschließlich auf Mausklicks in der PatientenSuche-Tabelle (abgekürzt TP) des Termin-Panels (abgekürzt PT).
     * Bei jedem Klick auf einen Eintrag in der Tabelle werden die Patient- und Arzt-IDs der ausgewählten Zeile in die zugehörigen Textfelder übernommen und
     * die Sichtbarkeit der Benutzeroberfläche mithilfe der "changeVisibilityAfterPicking"-Methode angepasst.
     * Dieser Schritt bildet den zweiten Schritt des Termin-Vergabe-Prozesses.
     * Der dritte und vorletzte Schritt ist Aufgabe der Klassen {@link DatumChangeListener} .
     *
     * @author Rafik
     * @version 1.0.0
     */
    public class PT_TP_MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = haupt_view.getPanel_termin().getTablePatientSuche().getSelectedRow();
            if (selectedRow != -1) {
                String patientID = (String) haupt_view.getPanel_termin().getTablePatientSuche().getValueAt(selectedRow, 0);
                String arztID = (String) haupt_view.getPanel_termin().getTablePatientSuche().getValueAt(selectedRow, 3);

                haupt_view.getPanel_termin().changeVisibilityAfterPicking(patientID, arztID);
            }
        }
    }

    /**
     * ActionListener für den Button "Termin stornieren" im Termin-Panel. Überwacht den Löschvorgang für ausgewählte Termine aus der Tabelle.
     * Bei Klick auf den Button wird der selektierte Termin (die selektierte Zeile) sowohl aus der Datenbank als auch aus der Benutzeroberfläche entfernt.
     * Dies ist der zweite und letzte Schritt des Termin-Stornierung-Prozesses.
     *
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnDeleteTerminHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = haupt_view.getPanel_termin().getTableTermin().getSelectedRow();
            if (selectedRow != -1) {
                String terminID = (String) haupt_view.getPanel_termin().getTableTermin().getDefault_Table_Model().getValueAt(selectedRow, 0);
                String deleteQuery = model.DELETE_TERMIN;
                deleteQuery = String.format(deleteQuery, terminID);
                tryToDelete(deleteQuery,selectedRow);

            } else {
                haupt_view.displayErrorMessage("Keine Zeile markiert!");
            }
        }
        /**
         * Versucht, den angegebenen Termin aus der Datenbank zu löschen und passt die Benutzeroberfläche entsprechend an.
         * Bei erfolgreichem Löschen des Termins aus der Datenbank wird die entsprechende Zeile auch aus der Benutzeroberfläche entfernt.
         * Die Methode behandelt verschiedene Arten von Ausnahmen, die während des Löschvorgangs auftreten können und passt die Sichtbarkeit im Termin-Panel an.
         *
         * @param deleteQuery   Die SQL-Abfrage zum Löschen des Termins aus der Datenbank.
         * @param selectedRow   Der Index der ausgewählten Zeile in der Termin-Tabelle, die aus der Benutzeroberfläche entfernt werden muss.
         */

        private void tryToDelete (String deleteQuery ,int selectedRow){
            try {
                int rows_deleted = model.dmlOperation(deleteQuery);
                if (rows_deleted == 1) {
                    haupt_view.getPanel_termin().getTableTermin().getDefault_Table_Model().removeRow(selectedRow);
                    haupt_view.displayMessage(rows_deleted + " Termin wurde gelöscht ");
                    haupt_view.getPanel_termin().changeVisibility("delete");
                }

            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
            }
        }
    }
    /**
     * Ein PropertyChangeListener, der auf Änderungen des Termindatums reagiert.
     * Überwacht die Auswahl eines Datums und passt die Benutzeroberfläche entsprechend an.
     * Diese Klasse bietet Funktionalitäten, um die Terminverfügbarkeit anhand des ausgewählten Datums, Patienten- und Arzt-IDs zu prüfen
     * und die Liste der verfügbaren Uhrzeiten dynamisch zu aktualisieren.
     * Sie verhindert die Auswahl von Terminen an Wochenenden oder gesetzlichen Feiertagen in Nordrhein-Westfalen (NRW).
     * Außerdem agiert sie im Voraus, um das Verletzen der beiden Unique-Constraints "patient_termin_duplikate" und "arzt_termin_duplikate" zu verhindern.
     *
     * Dies ist der dritte Schritt des Termin-Vergabe-Prozesses. Der vierte und letzte Schritt ist Aufgabe der Klasse {@link BtnAddTerminHandler}.
     *
     * @author Rafik
     * @version 1.0.0
     */
    public class DatumChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

            if ("date".equals(evt.getPropertyName())) {
                Date date = haupt_view.getPanel_termin().getDc_datum().getDate();

                if (date != null) {
                    if (IPO.weekendChecker(date) == null) {
                        if (IPO.holidayChecker(date) == null) {

                            ArrayList<String> possibleAppointmentTimes = new ArrayList<>(model.P_A_T);
                            String patientID = haupt_view.getPanel_termin().getTf_patient_id().getText();
                            String arztID = haupt_view.getPanel_termin().getTf_arzt_id().getText();
                            ArrayList<String> listToEliminate = timeListToEliminate(IPO.getFormattedDate(date), patientID, arztID);
                            possibleAppointmentTimes.removeAll(listToEliminate);

                            if (!possibleAppointmentTimes.isEmpty()) {
                                String[] cb_times = possibleAppointmentTimes.toArray(new String[possibleAppointmentTimes.size()]);
                                haupt_view.getPanel_termin().getCb_uhrzeit().setModel(new DefaultComboBoxModel(cb_times));
                                haupt_view.getPanel_termin().getCb_uhrzeit().setVisible(true);
                                haupt_view.getPanel_termin().getLb_uhrzeit().setVisible(true);
                                haupt_view.getPanel_termin().getBtn_add().setVisible(true);
                            } else {
                                haupt_view.getPanel_termin().getCb_uhrzeit().setVisible(false);
                                haupt_view.getPanel_termin().getLb_uhrzeit().setVisible(false);
                                haupt_view.getPanel_termin().getBtn_add().setVisible(false);
                                haupt_view.displayErrorMessage("Der Betreuer dieses Patienten ist für den  " + IPO.getFormattedDate(date) + " vollständig gebucht. Bitte ändern Sie das Datum.\nEs besteht noch die Möglichkeit, den Betreuer zu wechseln ( Nicht zu empfehlen! )");
                            }
                        } else {
                            haupt_view.getPanel_termin().getCb_uhrzeit().setVisible(false);
                            haupt_view.getPanel_termin().getLb_uhrzeit().setVisible(false);
                            haupt_view.getPanel_termin().getBtn_add().setVisible(false);
                            haupt_view.displayErrorMessage("Es können keine Termine an gesetzlichen Feiertagen in NRW vergeben werden!\n\n" + IPO.getFormattedDate(date) + "   :  " + IPO.holidayChecker(date));
                        }
                    } else {
                        haupt_view.getPanel_termin().getCb_uhrzeit().setVisible(false);
                        haupt_view.getPanel_termin().getLb_uhrzeit().setVisible(false);
                        haupt_view.getPanel_termin().getBtn_add().setVisible(false);
                        haupt_view.displayErrorMessage("Es können keine Termine am Wochenende  vergeben werden !\nBitte ändern Sie das Datum.");
                    }
                }
            }
        }
        /**
         * Diese Methode erstellt eine Liste von Uhrzeiten, die für die Eliminierung von Terminuhrzeiten in Abhängigkeit vom Datum, der Patienten-ID und der Arzt-ID verwendet wird.
         * Sie verwendet eine Datenbankabfrage, um die Uhrzeiten abzurufen, die bereits für den gewählten Patienten und Arzt an einem bestimmten Datum belegt sind.
         * Die abgerufenen Uhrzeiten dienen dazu, bereits belegte Terminuhrzeiten von den möglichen Terminuhrzeitoptionen {@link Model_Package.Model#P_A_T} auszuschließen.
         *
         * @param datum     Das gewählte Datum.
         * @param patientID Die ID des Patienten.
         * @param arztID    Die ID des Arztes.
         * @return Eine Liste von Uhrzeiten, die eliminiert werden sollen.
         */
        public ArrayList<String> timeListToEliminate(String datum, String patientID, String arztID) {

            String selectQuery = model.SELECT_UHRZEIT;
            selectQuery = String.format(selectQuery, datum, patientID, datum, arztID);
            ArrayList<String> timeListToEliminate;
            timeListToEliminate = getData(selectQuery);
            return timeListToEliminate;
        }
    }
    /**
     * ActionListener für den Button "Termin vergeben" im Termin-Panel.
     * Diese Klasse sammelt die benötigten Daten (Patienten-ID, Arzt-ID, Termin-Datum und -Uhrzeit) und erstellt eine SQL-Abfrage,
     * um einen neuen Termin einzufügen.
     * Obwohl die Klasse {@link DatumChangeListener} im Voraus agiert, um das Verletzen der beiden Unique-Constraints
     * "patient_termin_duplikate" und "arzt_termin_duplikate" zu verhindern, behandelt diese Klasse
     * die genannten Integritätsverletzungen nochmals zur zusätzlichen Sicherung.
     * Dieser Schritt entspricht dem vierten und letzten Schritt des Termin-Vergabe-Prozesses.
     *
     * @author Rafik
     * @version 1.0.0
     */
    public class BtnAddTerminHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String patientID = haupt_view.getPanel_termin().getTf_patient_id().getText();
            String arztID = haupt_view.getPanel_termin().getTf_arzt_id().getText();
            String datum = IPO.getFormattedDate(haupt_view.getPanel_termin().getDc_datum().getDate());
            String uhrzeit = (String) haupt_view.getPanel_termin().getCb_uhrzeit().getSelectedItem();
            String insertQuery = model.INSERT_TERMIN;
            insertQuery = String.format(insertQuery, datum, uhrzeit, patientID, arztID);
            tryToInsert(insertQuery,datum,uhrzeit);

        }
        /**
         * Diese Methode versucht, einen neuen Termin in die Datenbank einzufügen.
         * Sie führt die SQL-Operation zum Einfügen eines Termins durch und behandelt mögliche Ausnahmen,
         * die auftreten können, wie beispielsweise das Fehlen des Treibers, ungültige Datenbank-Anmeldeinformationen
         * oder Integritätsverletzungen.
         * Bei erfolgreichem Einfügen eines neuen Termins wird eine Erfolgsmeldung angezeigt und die Sichtbarkeit des Panels angepasst.
         *
         * @param insertQuery Die SQL-Abfrage zum Einfügen des Termins.
         * @param datum       Das gewählte Datum für den neuen Termin (für Fehlermeldungen).
         * @param uhrzeit     Die gewählte Uhrzeit für den neuen Termin (für Fehlermeldungen).
         */
        private void tryToInsert (String insertQuery,String datum ,String uhrzeit){
            try {
                int rowsInserted = model.dmlOperation(insertQuery);
                haupt_view.displayMessage(rowsInserted+" Termin wurde erfolgreich gebucht.");
                haupt_view.getPanel_termin().changeVisibility("add");
            } catch (ClassNotFoundException ex) {
                haupt_view.displayErrorMessage("Treiber konnte nicht hochgeladen werden ");
            } catch (SQLInvalidAuthorizationSpecException ex) {
                haupt_view.displayErrorMessage("Ungültige Datenbank-Anmeldeinformationen ");
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1){
                    if (ex.getMessage().contains("patient_termin_duplikate")){
                        haupt_view.displayErrorMessage("Der Patient hat schon einen Termin am "+datum +" um "+uhrzeit +"\nÄndern Sie entweder das Datum oder die Uhrzeit");
                    } else if (ex.getMessage().contains("arzt_termin_duplikate")) {
                        haupt_view.displayErrorMessage("Der Arzt hat schon einen Termin am "+datum +" um "+uhrzeit +"\nÄndern Sie entweder das Datum oder die Uhrzeit");
                    }else {
                        haupt_view.displayErrorMessage("SQL Fehler : \n" + ex.getErrorCode() + "\n" + ex.getMessage());
                    }
                }
            }
        }
    }
}




