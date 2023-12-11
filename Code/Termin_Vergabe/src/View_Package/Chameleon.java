package View_Package;
/**
 * Das Chameleon-Interface ermöglicht Panels, ihre Ansicht dynamisch zu ändern und Platzhalter zu setzen.
 * Panels, die dieses Interface implementieren, können die Sichtbarkeit ihrer Komponenten basierend auf verschiedenen Modi ändern.
 */
public interface Chameleon {
    /**
     * Setzt Platzhalter für die Eingabefelder oder andere Komponenten im Panel.
     */
    public void setPlaceHolder();
    /**
     * Ändert die Sichtbarkeit der GUI-Komponenten basierend auf dem übergebenen Modus.
     *
     * @param modus Ein String-Wert, der den Modus angibt.
     */
    public void changeVisibility(String modus);

}
