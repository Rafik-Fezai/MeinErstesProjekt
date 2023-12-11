package View_Package;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Die Klasse "Login_View" erbt von {@link javax.swing.JFrame} und repräsentiert das Anmeldefenster zur Zugangsberechtigung.
 *
 *@author Rafik
 *@version 1.0.0
 */
public class Login_View extends JFrame {
    private Panel_Login panel_login;
    /**
     * Konstruktor für die Login_View Klasse.
     * Initialisiert das Anmeldefenster.
     */
    public Login_View() {

        //Frame
        setTitle("Start");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(1500, 500, 500, 500);

        //Panel Login
        panel_login = new Panel_Login();
        setContentPane(panel_login);
        setVisible(true);
    }
    /**
     * Zeigt eine Fehlermeldung in einem Dialogfenster an.
     *
     * @param errorMessage Die anzuzeigende Fehlermeldung.
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    public Panel_Login getPanel_login() {
        return panel_login;
    }

    /**
     * "Innere Klasse 'Panel_Login' repräsentiert das Anmeldepanel und ist als Container für die Komponenten konzipiert,
     * die für die Benutzerauthentifizierung erforderlich sind."
     * @author Rafik
     * @version 1.0.0
     */
    public class Panel_Login extends JPanel {
        private JLabel lbl_username;
        private JLabel lbl_password ;
        private JTextField  tf_username;
        private JPasswordField  pf_password ;
        private JButton btn_login ;
        /**
         * Konstruktor der Klasse Panel_Login.
         * Erstellt die grafischen Komponenten für die Anmeldung und lagert sie, um angezeigt zu werden,
         * wenn das Panel als ContentPane für eine Klasse, die von JFrame erbt, gesetzt wird.
         */
        public  Panel_Login (){


            setLayout(null);
            setBorder(new LineBorder(Color.BLACK) );

            Font schriftartA = new Font("Arial", Font.BOLD, 16);
            Font schriftartSS = new Font("Sans Serif",Font.BOLD,13);

            //Label lbl_username
            lbl_username = new JLabel("Benutzername");
            lbl_username.setBounds(50,50,150,25);
            lbl_username.setVisible(true);
            lbl_username.setFont(schriftartA);
            add(lbl_username);

            //Label lbl_password
            lbl_password= new JLabel("Passwort");
            lbl_password.setBounds(50,100,150,25);
            lbl_password.setVisible(true);
            lbl_password.setFont(schriftartA);
            add(lbl_password);

            //Text field tf_username
            tf_username = new JTextField();
            tf_username.setBounds(200,50,150,25);
            tf_username.setVisible(true);
            tf_username.setFont(schriftartSS);
            add(tf_username);

            //Password-field pf_password
            pf_password = new JPasswordField();
            pf_password.setBounds(200,100,150,25);
            pf_password.setVisible(true);
            pf_password.setFont(schriftartA);
            add(pf_password);

            //Button btn_login
            btn_login = new JButton("Anmelden");
            btn_login.setBounds(50,300,300,25);
            btn_login.setVisible(true);
            btn_login.setFont(schriftartA);
            add(btn_login);
        }
        //Getter Methoden.
        public JButton getBtn_login() {
            return btn_login;
        }
        public JTextField getTf_username() {
            return tf_username;
        }
        public JPasswordField getPf_password() {
            return pf_password;
        }

    }
}
