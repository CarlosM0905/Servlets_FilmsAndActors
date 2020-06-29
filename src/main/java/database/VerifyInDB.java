package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyInDB {

    public DatabaseAccess db = new DatabaseAccess();

    public VerifyInDB() {
        this.db.initialize();
    }

    public boolean verifyFilm(String title) {
        int counter = 0;
        title = title.toUpperCase();
        try {
            PreparedStatement statement = db._connection.prepareStatement("SELECT COUNT(film_id) AS total FROM film WHERE title=?");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                counter = resultSet.getInt("total");
            }
            return counter > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear la sentencia" + e.getMessage());
            return false;
        }
    }

    public boolean verifyActor(String firstname, String lastname) {
        int counter = 0;
        firstname = firstname.toUpperCase();
        lastname = lastname.toUpperCase();

        try {
            PreparedStatement statement = db._connection.prepareStatement("SELECT COUNT(actor_id) AS total FROM actor WHERE first_name=? AND last_name=?");
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                counter = resultSet.getInt("total");
            }
            return counter > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear la sentencia");
            return false;
        }
    }

    public boolean verifyCategory(String name) {
        int counter = 0;
        if(name.length() > 2){
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        try {
            PreparedStatement statement = db._connection.prepareStatement("SELECT COUNT(category_id) AS total FROM category WHERE name=?;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                counter = resultSet.getInt("total");
            }
            return counter > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear la sentencia");
            return false;
        }
    }

    public boolean verifyLanguaje(String name) {
        int counter = 0;
        if(name.length() > 2){
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        try {
            PreparedStatement statement = db._connection.prepareStatement("SELECT COUNT(language_id) AS total FROM language WHERE name=?;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                counter = resultSet.getInt("total");
            }
            return counter > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear la sentencia");
            return false;
        }
    }
}
