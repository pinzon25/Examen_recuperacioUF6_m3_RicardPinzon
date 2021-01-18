/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damm03uf6final_ricardpinzon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riki
 */
public class Damm03uf6final_ricardPinzon implements EventDao {

    static Damm03uf6final_ricardPinzon t = new Damm03uf6final_ricardPinzon();
    List<Event> llistaEvents = new ArrayList<>();
    static List<Event> llistaEventsSystemTrue = new ArrayList<>();
    static List<Event> llistaDeEventsFinal = new ArrayList<>();
    static Event e;
    static Event ee;
    static Event e1;
    static Event e2;
    static Event e3;
    static Connection c;
    static SimpleDateFormat sdf = null;
    static java.util.Date d = null;
    static java.sql.Date sqlDate = null;
    ResultSet rs;
    boolean trobat;

    public static void main(String[] args) throws SQLException, Exception {
        c = new Connexio().getConexio();

        //-----------LECTURA----------
        obteTotsElsEvents();

        //---------ESCRIPTURA---------     
        //CREATE PER PARAMETRES DE NOM I DESCRIPCIO.FUNCIONA BÉ
       // System.out.println("Creat? " + t.Create("Examen", "Examen de recuperacio de M3UF6"));
        //CREATE MITJANÇANT PARAMETRE DE EVENT. FUNCIONA BÉ     
        /*e1 = new Event("Lluita", "combat entre dos contrincants", converteixData("2021-01-16"), true);
        t.create(e1);

        e2 = new Event("Carrera", "cursa entre dos cotxes", converteixData("2017-05-27"), false);
        t.create(e2);

        e3 = new Event("intimidacio", "acollonir a algu", converteixData("2012-18-3"), false);
        t.create(e3);*/
        
        
        //---------ELIMINACIO---------
        try {
              //FUNCIONA BÉ
             //t.Delete(1);
        } catch (NullPointerException e) {
            System.out.println("no es troba l'event que vols eliminar.");
        }

        //---------ACTUALITZACIO------
        try {
           // t.update(t.find(2));

        } catch (NullPointerException e) {
            System.out.println("no es troba l'event que vols actualitzar.");
        }
        //----------------------------

        //IMPRIMEIX ELS EVENTS QUE SON SYSTEM TRUE. //FUNCIONA BÉ
        imprimeixEventsTrue();

    }

    public static java.util.Date converteixData(String data) throws ParseException {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d;
        d = sdf.parse(data);
        System.out.println("imprimint la data formatada " + d);
        return d;
    }

    public static java.sql.Date converteixDataSql(java.util.Date data) throws ParseException {

        sqlDate = new java.sql.Date(data.getTime());

        return sqlDate;
    }

    @Override //Retorna true si l'event s'ha creat correctament.
    public boolean create(Event event) throws Exception {
        PreparedStatement ps = null;
        String s = "INSERT INTO Event(nom, descripcio, data_event, system_event)" + "VALUES(?,?,?,?)";
        boolean creat = false;

        try {
            ps = c.prepareStatement(s);

            ps.setString(1, event.getNom());
            ps.setString(2, event.getDescripcio());
            ps.setDate(3, converteixDataSql(event.getData()));
            ps.setBoolean(4, event.isSystem());
            ps.execute();

            creat = true;
        } catch (SQLException e) {
            Logger.getLogger(Damm03uf6final_ricardPinzon.class.getName()).log(Level.SEVERE, null, e);
        }
        return creat;
    }

    @Override //Retorna true si l'event s'ha creat correctament, la data es la actual i System es false.
    public boolean Create(String nom, String descripcio) throws Exception {
        PreparedStatement ps = null;
        String s = "INSERT INTO Event(nom, descripcio, data_event, system_event)" + "VALUES(?,?,?,?)";
        boolean creat = false;

        try {
            ps = c.prepareStatement(s);

            ps.setString(1, nom);
            ps.setString(2, descripcio);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setBoolean(4, false);
            ps.execute();

            creat = true;
        } catch (SQLException e) {
            Logger.getLogger(Damm03uf6final_ricardPinzon.class.getName()).log(Level.SEVERE, null, e);
        }
        return creat;
    }

    @Override //RETORNA TRUE SI L'ELEMENT S'HA BORRAT, PASSEM COM A PARAMETRE L'EVENT.
    public boolean delete(Event event) throws Exception {
        PreparedStatement ps = null;
        Boolean del = false;
        String delete = "DELETE FROM Event WHERE id=?";

        System.out.println("Impressio del event dintre del metode delete " + event);
        try {
            ps = c.prepareStatement(delete);
            ps.setInt(1, event.getId());
            ps.execute();

            del = true;

        } catch (SQLException ex) {

            Logger.getLogger(Damm03uf6final_ricardPinzon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return del;
    }

    @Override //RETORNA TRUE SI L'ELEMENT S'HA BORRAT, PASSEM COM A PARAMETRE EL ID DEL EVENT.
    public boolean Delete(int id) throws Exception {
        boolean trobat = false;
        
        for (int i = 0; i < llistaDeEventsFinal.size(); i++) {
            if (llistaDeEventsFinal.get(i).getId() == id) {
                e = llistaDeEventsFinal.get(i);
                System.out.println("impressio de e dintre del Delete"+e);
                t.delete(e);
                trobat = true;                
                break;
            }
        }
        
        
        return trobat;
    }

    @Override
    public Event find(int id) throws Exception {
        for (int i = 0; i < llistaDeEventsFinal.size(); i++) {
            if (llistaDeEventsFinal.get(i).getId() == id) {
                e = llistaDeEventsFinal.get(i);
                break;
            }
        }
        return e;
    }

    @Override //Retorna tots els events.
    public List<Event> readAll() throws Exception {
        Statement st = null;

        String query = "SELECT * FROM Event";

        try {
            st = c.createStatement();
            rs = st.executeQuery(query);

            Event event;

            while (rs.next()) {
                event = new Event(rs.getString("nom"), rs.getString("descripcio"),
                        rs.getDate("data_event"), rs.getBoolean("system_event"));
                event.setId(rs.getInt("id"));
                llistaEvents.add(event);
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return llistaEvents;
    }

    @Override //retorna una llista d'events de sistema que siguin TRUE.
    public List<Event> readAllSystemEvents() throws Exception {
        Statement st = null;

        String query = "SELECT * FROM Event WHERE system_event=true";

        try {
            st = c.createStatement();
            rs = st.executeQuery(query);

            Event event;

            while (rs.next()) {
                event = new Event(rs.getString("nom"), rs.getString("descripcio"),
                        rs.getDate("data_event"), rs.getBoolean("system_event"));
                event.setId(rs.getInt("id"));
                llistaEventsSystemTrue.add(event);
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return llistaEventsSystemTrue;
    }

    @Override //Retorna true si l'event s'ha actualitzat correctament.
    public boolean update(Event event) throws Exception {
        PreparedStatement ps = null;
        Boolean upd = false;

        String update = "UPDATE Event SET nom=?, descripcio=?, data_event=?, system_event=? WHERE id=?";

        try {
            ps = c.prepareStatement(update);
            ps.setString(1, "Guerra");
            ps.setString(2, "Destruccio total");
            sqlDate = converteixDataSql(event.getData());
            ps.setDate(3, sqlDate);
            ps.setBoolean(4, true);
            ps.setInt(5, event.getId());
            ps.execute();

            upd = true;

        } catch (SQLException ex) {
            Logger.getLogger(Damm03uf6final_ricardPinzon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return upd;

    }

    public static void imprimeixEventsTrue() throws Exception {
        llistaEventsSystemTrue = t.readAllSystemEvents();
        for (Iterator it = llistaEventsSystemTrue.iterator(); it.hasNext();) {
            System.out.println("Elements que son true " + it.next());
        }
        System.out.println("--------------------\n");
    }

    public static void obteTotsElsEvents() throws Exception {
        llistaDeEventsFinal = t.readAll();
        for (Iterator it = llistaDeEventsFinal.iterator(); it.hasNext();) {
            System.out.println("Event : " + it.next());
        }
    }

    /*public static Event obteEventAborrar(int id) {
        
        for (int i = 0; i < llistaDeEventsFinal.size(); i++) {
            if (llistaDeEventsFinal.get(i).getId() == id) {
                e = llistaDeEventsFinal.get(i);
                break;
            }
        }
        return e;
    }*/
    public static Event obteEventAactualitzar(int id, Event ev) throws ParseException {

        for (int i = 0; i < llistaDeEventsFinal.size(); i++) {
            if (llistaDeEventsFinal.get(i).getId() == id) {
                ee = llistaDeEventsFinal.get(i);
                break;
            }
        }

        /*ee.setNom(ev.getNom());
        ee.setDescripcio(ev.getDescripcio());
        ee.setData(ev.getData());
        ee.setSystem(ev.isSystem());*/
        return ee;
    }
}
