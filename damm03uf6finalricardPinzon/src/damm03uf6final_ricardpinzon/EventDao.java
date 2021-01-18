/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damm03uf6final_ricardpinzon;

import java.util.List;

/**
 *
 * @author riki
 */
public interface EventDao {

    // Retorna true si es crea l’event correctament
    boolean create(Event event) throws Exception;

    boolean Create(String nom, String descripcio) throws Exception;

    // Retorna true si s’actualitza l’event correctament
    boolean update(Event event) throws Exception;

    // Retorna true si s’elimina l’event correctament
    boolean delete(Event event) throws Exception;

    boolean Delete(int id) throws Exception;

    //Retorna Event si troba el id.
    Event find(int id) throws Exception;

    //Retorna una llista d'events de sistema: System==true.
    List<Event> readAllSystemEvents() throws Exception;

    List<Event> readAll() throws Exception;
}
