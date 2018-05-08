/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerik;

import Database.ConnectionSingleton;
import Database.FlowerDAO;
import Database.FlowerManager;
import Database.OrderDAO;
import Database.OrderManager;
import Models.Flower;
import Models.Order;
import Models.User;
import Utils.AppUtils;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class MakeOrderController extends Controller {

    private Connection connection;
    private Order order;
    private int stock;
    FlowerDAO flowerDAO;
    
    public MakeOrderController(Scanner scanner, User mainUser) {
        super(scanner, mainUser);
    }

    @Override
    protected void preInit() {
        startConnection();
        order = new Order();
        order.setReceiver(mainUser);
    }

    @Override
    protected void init() {
        flowerDAO = new FlowerManager(connection);
        HashMap<Integer, Flower> flowers = flowerDAO.getAll();
        Flower selectedFlower;
        int flowerOption;
       
        showAllFlowers(flowers);
        
        do {
            Outputs.selectFlower();
            flowerOption = AppUtils.selectInt(scanner, true);
            selectedFlower = getFlower(flowerOption, flowers);
            
            if (flowerOption == -1) 
                return;
            else if (selectedFlower == null)
                Outputs.selectValidOption();
            
        } while (selectedFlower == null);
        
        order.setFlowerType(selectedFlower);
        
        Outputs.selectedFlower(selectedFlower.getName());
        int selectQuantity = AppUtils.selectInt(scanner, true);
        
        if(selectQuantity == -1){
            return;
        }else if(selectQuantity == 0){
            Outputs.zeroSelected();
        }
        stock = calculateStock(selectQuantity, selectedFlower);
        
        if(stock < 0){
            Outputs.notEnoughStock();
            return;
        }
        
        
        order.setNumOfItems(selectQuantity);
        
        Outputs.getAddress();
        String selectAddress = scanner.nextLine();
        
        if(selectAddress.equals("")){
            return;
        }
        
        order.setAddress(selectAddress);
        
    }

    @Override
    protected void postInit() {
        
        flowerDAO.updateStock(order.getFlowerType().getId(), stock);
        
        boolean isOrderCreated = executeQuery(
                order.getNumOfItems(), 
                order.getAddress(), 
                order.getFlowerType().getId(), 
                order.getReceiver().getId()
        );
        
        if (isOrderCreated) {
            Outputs.orderCreated();
        } else{
            Outputs.orderNotCreated();
        }
    }

    private Flower getFlower(int flowerSelected, HashMap<Integer, Flower> flowers) {
        return flowers.get(flowerSelected);
    }

    private void showAllFlowers(HashMap<Integer, Flower> flowers) {
        Iterator it = flowers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getValue().toString());
        }
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

    private boolean executeQuery(int numOfItems, String address, int flowerId, int userId) {
        OrderDAO dao = new OrderManager(connection);
        boolean querySuccess = dao.createOrder(numOfItems, address, flowerId, userId);
        return querySuccess;
    }

    private int calculateStock(int selectQuantity, Flower selectedFlower) {
        int result = selectedFlower.getStock() - selectQuantity;
        return result;
    }


}
