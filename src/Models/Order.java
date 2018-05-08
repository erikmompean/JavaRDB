/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Erik
 */
public class Order {
    private int id;
    private int numOfItems;
    private Flower flowerType;
    private User receiver;
    private String address;

    public Order() {
        
    }

    public Order(int id, int numOfItems, Flower flowerType, User receiver, String address) {
        this.id = id;
        this.numOfItems = numOfItems;
        this.flowerType = flowerType;
        this.receiver = receiver;
        this.address = address;
    }
    
    public Order(int id, int numOfItems, Flower flowerType, String address) {
        this.id = id;
        this.numOfItems = numOfItems;
        this.flowerType = flowerType;
        this.receiver = receiver;
        this.address = address;
    }
    
    public int getId() {
        return id;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public Flower getFlowerType() {
        return flowerType;
    }

    public void setFlowerType(Flower flowerType) {
        this.flowerType = flowerType;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String toMyString() {
        return "Id: " + id + "\n" +
                "Cantidad: " + numOfItems + "\n" +
                "Flor: " + flowerType.getName() + "\n" +
                "Direccion: " + address + "\n" +
                "---------------------";
    }
    
}
