/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Database.ConnectionSingleton;
import Utils.Outputs;
import Database.UserDAO;
import Database.UserManager;
import Models.User;
import Utils.AppUtils;
import static ErikFlower.Controller.scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class ErikFlower {

    /**
     * @param args the command line arguments
     */
    private static Scanner sc = new Scanner(System.in);   
    private static Connection dbConnection;
    private static UserDAO uDAO;
    
    public static void main(String[] args) {
        startConnection();
        Outputs.welcome();
        firstMenu();

    }
    
    private static void firstMenu(){
        int welcomeOption;
        do {
            Outputs.firstMenu();
            welcomeOption = AppUtils.selectInt(sc, false);
            firstMenuSelector(welcomeOption);
        } while (welcomeOption > 2 || welcomeOption < 1);

    }
    
    private static void firstMenuSelector(int option){
        switch(option){
            case 1:{
                login();
                break;
            }
            case 2:{
                register();
            }
        }
    }
    
    private static void login(){
        Outputs.loginEmail();
        String email = sc.nextLine();
        Outputs.loginPassword();
        String password = sc.nextLine();
        checkLogin(email, password);
    }
    
    private static void register(){
        Outputs.registerEmail();
        String email = sc.nextLine();
        
        if(uDAO.checkIfExists(email)){
            Outputs.emailExist();
            firstMenu();
        }
        
        Outputs.registerPassword();
        String password = sc.nextLine();
        Outputs.registerRePassword();
        String rePassword = sc.nextLine();
        

        if(password.equals(rePassword)){
            crateAccount(email, password);
        }else {
            Outputs.passwordsNotEqual();
            firstMenu();
        }
        
    }

    private static void checkLogin(String email, String password) {
        try {
            User loggedUser = uDAO.login(email, password);
            handleLogin(loggedUser);
                
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
    }
    
    private static void startConnection(){
        try {
            dbConnection = ConnectionSingleton.getConnection();           
            uDAO = new UserManager(dbConnection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
    }

    private static void crateAccount(String email, String password) {
        boolean registerSuccessful = uDAO.register(email, password);
        if(registerSuccessful){
            Outputs.registerSuccessful();
            firstMenu();
        } else {
            Outputs.registerFail();
            firstMenu();
        }
    }

    private static void handleLogin(User loggedUser) {
        if (loggedUser != null){
            Outputs.loginSucces();
            new App(loggedUser, sc);
            
        }else {
            Outputs.loginFail();
            firstMenu();
        }
    }

}