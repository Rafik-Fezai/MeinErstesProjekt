package Controller_Package;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameter;
import de.jollyday.ManagerParameters;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

/**
 * Die Klasse IPO bietet verschiedene statische Hilfsmethoden gemäß dem Eingabe-Verarbeitung-Ausgabe-Prinzip (Input Process Output, IPO)
 * für Eingabeprüfungen, Datumsumwandlungen und Feiertagsabfragen
 *
 *@author Rafik
 *@version 1.0.0
 */
public class IPO {

    /**
     * Verschlüsselt das eingegebene Passwort mithilfe des SHA-512-Algorithmus,
     * der zur Secure Hash Algorithm 2 (SHA-2) Familie gehört
     *
     * @param password Das zu verschlüsselnde Passwort als String.
     * @return Das verschlüsselte Passwort als Hexadezimal-Zeichenkette (128 Zeichen lang) in Großbuchstaben
     *         oder null, falls der Algorithmus nicht verfügbar ist.
     */
    public static String hashPassword(String password)  {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(password.getBytes());
            return DatatypeConverter.printHexBinary(hashBytes).toUpperCase();
        }catch (NoSuchAlgorithmException noSuchAlgorithmException){
            return  null;
        }
    }
    /**
     * Überprüft, ob der übergebene Name den angegebenen Regeln entspricht.
     * Der Name darf nur aus Buchstaben bestehen und eine Länge zwischen 2 und 30 Zeichen haben. Die Buchstaben
     * können sowohl Groß- als auch Kleinbuchstaben sein und können zusätzlich die Sonderzeichen Ä, Ö, Ü, ä, ö, ü und ß
     * enthalten.
     *
     * @param name Der zu überprüfende Name als String.
     * @return true, wenn der Name den angegebenen Regeln entspricht, ansonsten false.
     */
    public static  Boolean check_Name(String name){
        String regEx = "^[A-Za-zÄÖÜäöüß]{2,30}$";
        return  name.matches(regEx);
    }

    /**
     * Überprüft, ob die übergebene Telefonnummer den angegebenen Regeln entspricht.
     * Die Telefonnummer muss mit einer 0 beginnen und darf insgesamt 10 bis 14 Ziffern enthalten.
     *
     * @param nummer Die zu überprüfende Telefonnummer als String.
     * @return true, wenn die Telefonnummer den angegebenen Regeln entspricht, ansonsten false.
     */
    public static Boolean check_Telefon(String nummer){
        String regEx ="^0[0-9]{9,13}$";
        return nummer.matches(regEx);
    }
    /**
     * Überprüft, ob die übergebene Raumnummer den angegebenen Regeln entspricht.
     * Die Raumnummer muss mit einem 'R' beginnen, gefolgt von genau zwei Ziffern.
     *
     * @param raumNr Die zu überprüfende Raumnummer als String.
     * @return true, wenn die Raumnummer den angegebenen Regeln entspricht, ansonsten false.
     */
    public static  Boolean check_RaumNr(String raumNr){
        String regEx = "^R[0-9]{2}$";
        return  raumNr.matches(regEx);
    }

    /**
     * Konvertiert das übergebene Datum in das Format "dd.MM.yyyy".
     *
     * @param date Das zu formatierende Datum.
     * @return Eine Zeichenkette, die das formatierte Datum im Format "dd.MM.yyyy" repräsentiert.
     */
    public static String getFormattedDate(Date date) {

        SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = dateformat.format(date);
        return formattedDate;
    }

    /**
     * Teilt das übergebene String-Datum in Tag, Monat und Jahr auf und gibt sie als Integer-Array zurück.
     *
     * @param formattedDate Das String-Datum im Format "dd.MM.yyyy".
     * @return Ein Integer-Array mit Tag, Monat und Jahr.
     */
    public static int[] splitDate(String formattedDate) {
        String[] parts = formattedDate.split("\\.");

        int[] splitDate = new int[3];
        splitDate[0] = Integer.parseInt(parts[0]);//Tag
        splitDate[1] = Integer.parseInt(parts[1]);//Monat
        splitDate[2] = Integer.parseInt(parts[2]); //Jahr
        return splitDate;
    }


    /**
     * Gibt das übergebene LocalDate als formatiertes Datum im String-Format "dd.MM.yyyy" zurück.
     *
     * @param localDate Das LocalDate-Objekt, das in ein String-Datum umgewandelt werden soll.
     * @return Das formatierte Datum als String im Format "dd.MM.yyyy".
     */
    public static String getFormattedDate(LocalDate localDate) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = dateTimeFormatter.format(localDate);
        return formattedDate;
    }
    /**
     * Überprüft, ob der übergebene Datumswert ein Wochenendtag ist.
     *
     * @param date Das Datum, dessen Wochentag überprüft werden soll.
     * @return Den Wochentag des Datums (SUNDAY, SATURDAY) oder null, falls es sich um einen Werktag handelt.
     */
    public static DayOfWeek weekendChecker(Date date) {
        int[] splitDate = splitDate(getFormattedDate(date));
        LocalDate localDate = LocalDate.of(splitDate[2], splitDate[1], splitDate[0]);

        if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return DayOfWeek.SUNDAY;
        } else if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return DayOfWeek.SATURDAY;
        } else {
            return null;  // Werktag
        }
    }
    /**
     * Überprüft, ob das übergebene Datum einem Feiertag in Deutschland, insbesondere im Bundesland Nordrhein-Westfalen,
     * entspricht.
     *
     * @param date Das Datum, das auf einen Feiertag überprüft werden soll.
     * @return Die Beschreibung des Feiertags, wenn das Datum ein Feiertag ist ; ansonsten null.
     */
    public static String holidayChecker(Date date) {
        String inputDate = getFormattedDate(date);
        int year = splitDate(inputDate)[2];

        ManagerParameter parameter = ManagerParameters.create("de");
        HolidayManager manager = HolidayManager.getInstance(parameter);
        Set<Holiday> feiertage = manager.getHolidays(year, "nw");

        for (Holiday holiday : feiertage) {
            if (getFormattedDate(holiday.getDate()).equals(inputDate)) {
                return holiday.getDescription();
            }
        }
        return  null;
    }

    /**
     * Konvertiert einen übergebenen String in ein Date-Objekt im Format "dd.MM.yyyy".
     *
     * @param dateString Der zu konvertierende String im Format "dd.MM.yyyy".
     * @return Das konvertierte Datum als Date-Objekt oder null, wenn der übergebene String kein gültiges Datum ist.
     */
    public static Date convert_String_to_Date(String  dateString) {

        SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = dateformat.parse(dateString);
            return date ;
        }catch (ParseException parseException){
            return  null;
        }
    }
    /**
     * Überprüft, ob in der übergebenen ComboBox mindestens ein verfügbares Element vorhanden ist, wobei "--" als Platzhalter
     *  nicht berücksichtigt wird.
     *
     * @param comboBox Die ComboBox, die die Elemente enthält.
     * @return `true`, wenn mehr als ein Element (ohne "--") in der ComboBox existiert, ansonsten `false`.
     *
     */
    public static boolean itemExists(JComboBox comboBox) {
        boolean itemExists = false;
        if (comboBox.getModel().getSize() > 1) {
            itemExists = true;
        }
        return itemExists;
    }



}

