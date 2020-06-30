package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        int indexFilm = -1;
        int indexLanguage = -1;
        
        boolean isRegistered = verify.verifyFilm(film.getTitle());

        if (!isRegistered) {
            try {
                // Verificar y registrar todos los actores
                for (Actor actor : actors) {
                    int indexActor = registerActor(actor);
                    if (indexActor != -1) {
                        indexs.add(indexActor);
                    }
                }
                
                String language = film.getLanguage();
                
                boolean isLanguageRegistered = verify.verifyLanguaje(language);
                
                if(!isLanguageRegistered){
                    // Registrar el lenguaje
                    PreparedStatement statement = db._connection.prepareStatement("INSERT INTO language(name) VALUES (?)");
                    statement.setString(1, language);
                    
                    int rowsUpdated = statement.executeUpdate();
                    
                    // Se busca el ultimo id del idioma registrada
                    statement = db._connection.prepareStatement("SELECT language_id FROM language ORDER BY(language_id) DESC LIMIT 1;");
                    ResultSet resultSet = statement.executeQuery();
                    
                    while(resultSet.next()){
                        indexLanguage = resultSet.getInt("language_id");
                    }
                }
                else{
                    // Se busca el ultimo id del idioma registrada
                    PreparedStatement statement = db._connection.prepareStatement("SELECT language_id FROM language WHERE name=?;");
                    statement.setString(1, language);
                    
                    ResultSet resultSet = statement.executeQuery();
                    
                    while(resultSet.next()){
                        indexLanguage = resultSet.getInt("language_id");
                    }                
                }
                
                // Registrar la pelicula
                PreparedStatement statement = db._connection.prepareStatement("INSERT INTO film(title, language_id, rental_duration, rental_rate, replacement_cost) VALUES (?,?,?,?,?);");
                statement.setString(1, film.getTitle());
                statement.setInt(2, indexLanguage);
                statement.setInt(3, film.getRentalDuration());
                statement.setDouble(4, film.getRentalRate());
                statement.setDouble(5, film.getReplacementCost());

                int rowsUpdated = statement.executeUpdate();

                // Se busca el ultimo id de la pelicula registrada
                statement = db._connection.prepareStatement("SELECT film_id FROM film ORDER BY(film_id) DESC LIMIT 1;");
                ResultSet resultSet = statement.executeQuery();

                // Guardamos el indice en el arreglo de indices
                while (resultSet.next()) {
                    indexFilm = resultSet.getInt("film_id");
                }

                // Agregar los actores a la pelicula
                registerActorsInFilm(indexs, indexFilm);

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }
        }

    }

    public void registerActorsInFilm(ArrayList<Integer> idxActors, int idxFilm) {
        for (Integer idxActor : idxActors) {
            try {
                PreparedStatement statement = db._connection.prepareStatement("INSERT INTO film_actor(actor_id, film_id) VALUES(?,?);");
                statement.setInt(idxActor, idxFilm);

                int rowsUpdated = statement.executeUpdate();

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
