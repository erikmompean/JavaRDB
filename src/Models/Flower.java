/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.sun.istack.internal.Nullable;



/**
 *
 * @author Erik
 */
public class Flower {
    private int id;
    private String name;
    private String description;
    private int stock;
    
    public Flower(int id, String name,@Nullable String description, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        if (description != null){
            this.description = description;
        }
    }

    public Flower(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public Flower(String name){
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }
    
    @Override
    public String toString() {
        if(description != null){
            if(stock > 0){
                return "Id: " + id + "\n" +
                        "Nombre: " + name + "\n" +
                        "Descripcion: " + name + "\n" +
                        "Stock: " + stock + "\n" +
                        "---------------------";
            }else{
                return "Id: " + id + "\n" +
                        "Nombre: " + name + "\n" +
                        "Descripcion: " + name + "\n" +
                        "Stock: Agotado" + "\n" +
                        "---------------------";
            }
        
        } else {
            if(stock > 0){
                return "Id: " + id + "\n" +
                        "Nombre: " + name + "\n" +
                        "Stock: " + stock + "\n" +
                        "---------------------";
            }else{
                return "Id: " + id + "\n" +
                        "Nombre: " + name + "\n" +
                        "Stock: Agotado" + "\n" +
                        "---------------------";
            }
        }
   
    }
   

}
