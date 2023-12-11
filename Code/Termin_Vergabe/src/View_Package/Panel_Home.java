package View_Package;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
/**
 * Panel_Home repräsentiert das Start-Panel der Anwendung und enthält das Praxislogo.
 * @author Rafik
 * @version 1.0.0
 */
public class Panel_Home extends JPanel {
    private ImageIcon icon_logo ;
    private  JLabel  lb_logo ;
    /**
     * Konstruktor für Panel_Home. Lagert das Praxislogo, das angezeigt wird,
     * wenn das Panel als ContentPane für eine Klasse, die von {@link javax.swing.JFrame} erbt, gesetzt wird.
     */
    public  Panel_Home(){

        setLayout(new GridLayout(1,1));
        setBorder(new LineBorder(Color.BLACK));

        // Laden des Praxislogos aus der Datei "logo.png"
        icon_logo = new ImageIcon("src/logo.png");

        lb_logo = new JLabel(icon_logo);
        add(lb_logo);
    }
}
