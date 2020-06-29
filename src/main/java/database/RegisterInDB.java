package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Actor;
import model.Film;

public class RegisterInDB {

    public DatabaseAccess db;
    private final VerifyInDB verify;

    public RegisterInDB() {
        db = new DatabaseAccess();
        verify = new VerifyInDB();
        db.initialize();
    }

    public void registerFilm(Film film, Actor[] actors) {
        ArrayList<Integer> indexs = new ArrayList<>();
        
        boolean isRegistered = verify.verifyFilm(film.getTitle());

        if (!isRegistered) {
            try {
                // Verificar y registrar todos los actores
                for (Actor actor: actors) {
                    int index = registerActor(actor);
                    if(index != -1){
                        indexs.add(index);
                    }
                }
                
                PreparedStatement statement = db._connection.prepareStatement("INSERT INTO film(title, language_id, rental_duration, rental_rate, replacement_cost) VALUES (?,?,?,?,?);");
                statement.setString(1, film.getTitle());
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }
        }

    }

    public int registerActor(Actor actor) {

        int index = -1;
        // Verificar si el actor esta registrado
        boolean isRegistered = verify.verifyActor(actor.getFirstName(), actor.getLastName());
        // Si no esta registrado
        if (!isRegistered) {
            try {
                // Se inserta en la BD al actor
                PreparedStatement statement = db._connection.prepareStatement("INSERT INTO actor(first_name, last_name) VALUES(?,?);");
                statement.setString(1, actor.getFirstName().toUpperCase());
                statement.setString(2, actor.getLastName().toUpperCase());

                int rowsUpdated = statement.executeUpdate();

                // Se busca el ultimo id del actor registrado
                statement = db._connection.prepareStatement("SELECT actor_id FROM actor ORDER BY(actor_id) DESC LIMIT 1;");
                ResultSet resultSet = statement.executeQuery();

                // Guardamos el indice en el arreglo de indices
                while (resultSet.next()) {
                    index = resultSet.getInt("actor_id");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }
        }
        return index;
    }
}
