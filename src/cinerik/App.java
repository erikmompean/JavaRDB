/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerik;

import Database.ConnectionSingleton;
import Database.FlowerDAO;
import Database.FlowerManager;
import Database.UserManager;
import Models.Flower;
import Models.User;
import Utils.AppUtils;
import Utils.Outputs;
import static cinerik.Controller.scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class App {

    private static User mainUser;
    private static Connection connection;
    private static Scanner sc;
    
    public App(User mainUser, Scanner sc) {
        this.mainUser = mainUser;
        this.sc = sc;
        startConnection();
        initialize();
    }

    static private void initialize() {
        int option;
        do {
            Outputs.appMenu();
            option = AppUtils.selectInt(sc, false);
            selectAppMenu(option);
            
            if (option > 5 || option < 1)
                Outputs.selectValidOption();
        } while (option != 5);
    }
    
    private static void selectAppMenu(int option) {
        switch(option){
            case 1:
                makeOrder();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }
    static private void seeMyOrders() {

        
    }
    
    static private void createOrder() {

        
    }
    
    static private void editOrder() {
        
    }
    
    private static void startConnection(){
        try {
            connection = ConnectionSingleton.getConnection();           
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
    }
    
    private static void makeOrder() {
        new MakeOrderController(sc, mainUser);
    }
    
}