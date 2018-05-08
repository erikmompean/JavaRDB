/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Database.ConnectionSingleton;
import Database.OrderDAO;
import Database.OrderManager;
import Models.Flower;
import Models.Order;
import Models.User;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class SeeOrdersController extends Controller{
    HashMap<Integer, Order> orders;
    public SeeOrdersController(Scanner scanner, User mainUser) {
        super(scanner, mainUser);
    }

    @Override
    protected void preInit() {
        OrderDAO orderDAO = new OrderManager(connection);
        orders = orderDAO.getAllOders(mainUser.getId());
    }

    @Override
    protected void init() {
        showAllOrders();
    }

    @Override
    protected void postInit() {
        
    }
    
    private void showAllOrders() {
        Iterator it = orders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Order order = (Order) pair.getValue();
            System.out.println(order.toMyString());
        }
    }
}
