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
public class SeeOrdersController extends Controller{
    private Connection connection;
    
    public SeeOrdersController(Scanner scanner, User mainUser) {
        super(scanner, mainUser);
    }

    @Override
    protected void preInit() {
        startConnection();
    }

    @Override
    protected void init() {
        
    }

    @Override
    protected void postInit() {
        
    }
    
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
