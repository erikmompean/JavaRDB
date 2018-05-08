/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik
 */
public class ConnectionSingleton {
    private static final String url="jdbc:mysql://localhost:3306/floreserik";
    private static final String username="root";
    private static final String pass="";

    private static Connection instance;
    
    private ConnectionSingleton(){}
    
    public static Connection getConnection() throws SQLException {
        if (instance == null){
                instance = DriverManager.getConnection(url, username, pass);
                System.out.println("Base de datos abierta");
        }
        return instance;
    }
    
    public static void closeConnection() {
        if(instance != null){
            try {
                instance.close();
            } catch (SQLException ex) {
                System.out.println("Wrong Connection");
            }
        }
    }
    
   
}
