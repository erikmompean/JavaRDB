/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Models.User;
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

    public Controller(Scanner scanner, User mainUser) {
        this.scanner = scanner;
        this.mainUser = mainUser;
        
        preInit();
        init();
        postInit();
    }

    protected abstract void preInit();

    protected abstract void init();

    protected abstract void postInit();
    
    
    
}
