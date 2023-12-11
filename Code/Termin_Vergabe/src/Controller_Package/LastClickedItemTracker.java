package Controller_Package;
/**
 * Die `LastClickedItemTracker`-Klasse dient der Verfolgung des zuletzt geklickten Elements (MenuItem) in der Anwendung.
 * Sie stellt Methoden zum Festlegen und Abrufen des letzten geklickten Items bereit.
 */
public class LastClickedItemTracker {
    /**
     * Statische Variable zur Speicherung des letzten geklickten Items
     */
    private static String lastClickedItem = "";

    /**
     * Diese Methode setzt das zuletzt geklickte Item.
     * @param item Das zuletzt geklickte Item, das gespeichert werden soll.
     */
    public static void setLastClickedItem(String item) {
        lastClickedItem = item;
    }

    /**
     * Diese Methode gibt das zuletzt geklickte Item zurück.
     * @return Das zuletzt geklickte Item, das gespeichert wurde.
     */
    public static String getLastClickedItem() {
        return lastClickedItem;
    }
}