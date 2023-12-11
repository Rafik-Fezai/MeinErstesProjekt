  
    DROP TABLE Arzt           CASCADE CONSTRAINTS ; -- Absicherung bei wiederholtem Ausführen 
    DROP TABLE Patient        CASCADE CONSTRAINTS ; -- Absicherung bei wiederholtem Ausführen 
    DROP TABLE Termin         CASCADE CONSTRAINTS ; -- Absicherung bei wiederholtem Ausführen 
    DROP TABLE Vertretung     CASCADE CONSTRAINTS ; -- Absicherung bei wiederholtem Ausführen 
    DROP TABLE Benutzer       CASCADE CONSTRAINTS ; -- Absicherung bei wiederholtem Ausführen 
    DROP TABLE Raum           CASCADE CONSTRAINTS ;-- Absicherung bei wiederholtem Ausführen 
    
    DROP SEQUENCE seq_arzt ;     -- Absicherung bei wiederholtem Ausführen 
    DROP SEQUENCE seq_patient ;  -- Absicherung bei wiederholtem Ausführen 
    DROP SEQUENCE seq_termin;    -- Absicherung bei wiederholtem Ausführen 
    

-- Erstetellung der Raum-Tabelle

    CREATE TABLE Raum (
     
          raum_nr  VARCHAR2(3),
          status   VARCHAR2(7)
    );
    
    
-- Erstellung der Arzt-Tabelle

    CREATE TABLE Arzt (
    
          arzt_id           NUMBER (10)  ,
          anrede            VARCHAR2(4) ,
          titel             VARCHAR2(11) ,
          vorname           VARCHAR2(30) ,
          nachname          VARCHAR2(30) ,
          raum_nr           VARCHAR2(3),
          beruf             VARCHAR2(11) 
    );
    
    
-- Erstellung der Vertretungstabelle 

    CREATE TABLE  Vertretung (
    
         vertretungs_id    NUMBER(10)  ,
         vertreter_id      NUMBER(10)  , 
         arzt_id           NUMBER(10)  ,
         startdatum        DATE ,
         enddatum          DATE ,
         Grund             VARCHAR2 (2)
    );
    
    
-- Erstellung der Patient-Tabelle

     CREATE TABLE  Patient (
    
         patient_id         NUMBER(10)  ,
         anrede             VARCHAR2(4) ,
         vorname            VARCHAR2(30) ,
         nachname           VARCHAR2(30) ,
         telefon            VARCHAR2(14) ,
         geburtsdatum       DATE ,
         arzt_id            NUMBER (10)
    );
    

--Erstellung der Termin-Tabelle 

     CREATE TABLE Termin (
    
         termin_id          NUMBER(10) ,
         datum              DATE ,
         uhrzeit            VARCHAR2(5),
         status             VARCHAR2 (2),
         patient_id         NUMBER(10),
         arzt_id            NUMBER(10)
    );
    
--Erstellung der Benutzer-Tabelle 

     CREATE TABLE Benutzer (
    
        benutzername       VARCHAR2 (30) ,
        passwort           VARCHAR2(128)
        
        );
      
-- Einfügen der Primärschlüssel 

    ALTER TABLE Raum                ADD CONSTRAINT  PK_Raum                PRIMARY KEY  (raum_nr);    
    ALTER TABLE Arzt                ADD CONSTRAINT  PK_Arzt                PRIMARY KEY  (arzt_id);
    ALTER TABLE Termin              ADD CONSTRAINT  PK_Termin              PRIMARY KEY  (termin_id);
    ALTER TABLE Patient             ADD CONSTRAINT  PK_Patient             PRIMARY KEY  (patient_id);
    ALTER TABLE Vertretung          ADD CONSTRAINT  PK_Vertretung          PRIMARY KEY  (vertretungs_id);  
    ALTER TABLE Benutzer            ADD CONSTRAINT  PK_Benutzer            PRIMARY KEY  (benutzername);
    

-- Einfügen der Fremdschlüssel

    ALTER TABLE Termin           ADD CONSTRAINT  FK_Termin_Patient        FOREIGN KEY (patient_id)     REFERENCES Patient (patient_id);
    ALTER TABLE Termin           ADD CONSTRAINT  FK_Termin_Arzt           FOREIGN KEY (arzt_id)        REFERENCES Arzt    (arzt_id);
    ALTER TABLE Patient          ADD CONSTRAINT  FK_Patient_Betreuer      FOREIGN KEY (arzt_id)        REFERENCES Arzt    (arzt_id);
    ALTER TABLE Vertretung       ADD CONSTRAINT  FK_Vertretung_Vertreter  FOREIGN KEY (vertreter_id)   REFERENCES Arzt    (arzt_id);
    ALTER TABLE Vertretung       ADD CONSTRAINT  FK_Vertretung_Arzt       FOREIGN KEY (arzt_id)        REFERENCES Arzt    (arzt_id);
    ALTER TABLE Arzt             ADD CONSTRAINT  FK_Arzt_Raum             FOREIGN KEY (raum_nr)        REFERENCES Raum    (raum_nr);

--Einfügen der UNIQUE Constraints , um Duplikate zu vermeiden 

    ALTER TABLE patient          ADD CONSTRAINT  patient_unique_telefon   UNIQUE (telefon); 
    ALTER TABLE termin           ADD CONSTRAINT  patient_termin_duplikate UNIQUE (patient_id,datum,uhrzeit);
    ALTER TABLE termin           ADD CONSTRAINT  arzt_termin_duplikate    UNIQUE (arzt_id,datum,uhrzeit);

        
-- Erstellung von Sequenzen

 CREATE SEQUENCE seq_arzt 
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE 
    CACHE 20 
    NOCYCLE ;
       
 CREATE SEQUENCE seq_patient 
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE 
    CACHE 20 
    NOCYCLE ;
      
        
 CREATE SEQUENCE seq_termin 
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE 
    CACHE 20 
    NOCYCLE ;
    
      
/**

Der Trigger "Raumstatuswechseln" aktualisiert den Raumstatus in der 
"raum"-Tabelle basierend auf Änderungen in der "arzt"-Tabelle.
Beim Einfügen markiert er Räume als 'besetzt', beim Löschen als 'frei' und bei 
einer Raumnummeränderung aktualisiert er den entsprechenden Status.

*/
CREATE OR REPLACE TRIGGER   Raumstatuswechseln 
AFTER INSERT OR DELETE OR UPDATE OF raum_nr ON arzt 
FOR EACH ROW 
DECLARE 
--Keine Variablen erforderlich 
BEGIN 
    IF INSERTING THEN
    
        IF :NEW.raum_nr IS NOT NULL THEN 
            UPDATE raum SET raum.status= 'besetzt' 
            WHERE raum.raum_nr=:NEW.raum_nr;
        END IF ;
        
    ELSIF DELETING THEN 
    
        IF :OLD.raum_nr IS NOT NULL THEN 
            UPDATE raum SET raum.status= 'frei' 
            WHERE raum.raum_nr=:OLD.raum_nr;
        END IF;
        
    
     ELSIF UPDATING ('raum_nr') THEN
        IF (:OLD.raum_nr IS NOT NULL) AND (:NEW.raum_nr IS NOT NULL) AND (:OLD.raum_nr != :NEW.raum_nr) THEN
        
            UPDATE raum SET raum.status = 'frei'
            WHERE raum.raum_nr = :OLD.raum_nr;
            UPDATE raum SET raum.status = 'besetzt'
            WHERE raum.raum_nr = :NEW.raum_nr;
            
        END IF;
    END IF;
END;

/**

Erstellung der View "patient_suche"

*/

CREATE OR REPLACE VIEW patient_suche (patient_id ,patient_name,geburtsdatum ,betreuer_id,betreuer) 
    AS SELECT TO_CHAR(p.patient_id) ,p.nachname ||' '||p.vorname ,TO_CHAR(p.geburtsdatum,'DD.MM.YYYY'), TO_CHAR(a.arzt_id) ,a.titel||' '||a.vorname||' '||a.nachname
    FROM patient p 
    JOIN arzt a  ON a.arzt_id = p.arzt_id
    WITH READ ONLY ;
    
/**

Erstellung der View "termin_daten"

*/

CREATE OR REPLACE VIEW termin_daten(termin_id,datum,uhrzeit,patient_id,patient,p_geburtsdatum,arzt)
    AS SELECT t.termin_id, TO_CHAR(t.datum,'DD.MM.YYYY'), t.uhrzeit, p.patient_id, p.anrede ||' '||p.vorname ||' '||p.nachname ,
    TO_CHAR(p.geburtsdatum ,'DD.MM.YYYY'), a.titel||' '||a.vorname||' '||a.nachname 
    FROM termin t 
    JOIN patient p ON t.patient_id = p.patient_id
    JOIN arzt a ON t.arzt_id = a.arzt_id 
    WITH READ ONLY ;

         
-- Einfügen eines neuen Datensatzes in die Benutzer-Tabelle unter Verwendung des SHA-512-Algorithmus.  

INSERT INTO benutzer (benutzername,passwort)VALUES ('user' ,DBMS_CRYPTO.HASH(UTL_RAW.CAST_TO_RAW('user'), 6));     

