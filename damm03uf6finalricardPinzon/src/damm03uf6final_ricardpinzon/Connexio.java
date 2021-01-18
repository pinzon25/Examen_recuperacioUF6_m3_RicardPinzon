/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damm03uf6final_ricardpinzon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riki
 */
public class Connexio {

    private final String usuari = "administrador";
    private final String contrasenya = "admin_event";
    private final String bbdd = "damm03uf6final";
    private final String url = "jdbc:mysql://localhost:3306/" + bbdd;

    private Connection con = null;

    public Connection getConexio() throws SQLException {

        //Class.forName("com.mysql.cj.jdbc.Driver");
        
        //AQUI HEM MODIFICAT LA SINTAXIS DE LA URL MODIFICANT LA SERVERTIMEZONE, HEM CANVIAT EL SERVERTIMEZONE=UTC A SERVERTIMEZONE=EUROPE/MADRID. 
        //AIXI NO ENS RESTA 1 DIA A LA DATA QUE LI PASSEM.
        con = DriverManager.getConnection(url + "?useUnicode=true&"
                + "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid", usuari, contrasenya);
        return con;
        //System.out.println("Connexio establerta amb la base de dades: " + bbdd + "\n\n");

    }
    /*private static final String bbdd = "damm03uf6final";
    private static final String usuari = "administrador";
    private static final String psw = "admin_event";
    private static final String url = "jdbc:mysql://localhost:3306/" + bbdd;

    private static Connection conn = null;

    public static Connection getConnexio() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "damm03uf6final?useUnicode=true&useJDBCCompliantTimezoneShift=true&"
                        + "useLegacyDatetimeCode=false&serverTimezone=UTC",
                        usuari, psw);
            } catch (SQLException ex) {
                Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }*/
}
