/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Database.ConnectionSingleton;
import Models.User;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public abstract class Controller {
    /**
     * The autenticated user
     */
    protected static User mainUser;
    /**
     * Scanner for an input purposess
     */
    protected static Scanner scanner;

    protected Connection connection;
    public Controller(Scanner scanner, User mainUser) {
        this.scanner = scanner;
        this.mainUser = mainUser;
        
        startConnection();
        preInit();
        init();
        postInit();
    }

    protected abstract void preInit();

    protected abstract void init();

    protected abstract void postInit();
    
    private void startConnection(){
        try {
            connection = ConnectionSingleton.getConnection();           
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
    }
    
}
