/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class AppUtils {
    public static int selectInt(Scanner sc, boolean isExit){
        int number = -1; 
        boolean isNumber = true;
        int limitNumber = isExit ? -1 : 0;
        
        do{
            try {
                number = Integer.parseInt(sc.nextLine());
                
                if(isExit){
                    if (number < limitNumber){
                        System.err.println("El numero ha de ser valido");
                        isNumber = false;
                    }
                }

                    
            } catch (NumberFormatException e) {
                System.err.println("Porfavor escriba un numero");
                isNumber = false;
            }
        }while(!isNumber);
        
        
        return number;
    }
}
