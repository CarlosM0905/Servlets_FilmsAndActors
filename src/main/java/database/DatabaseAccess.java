package database;


import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
    public Connection _connection;

    public void initialize(){
        try {
            DriverManager.registerDriver(new Driver());
            _connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
        }
        catch (SQLException e){
            System.out.println("Error al registrar el controlador" + e.getMessage());
        }
    }
}

