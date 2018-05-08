/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Database.ConnectionSingleton;
import Database.OrderDAO;
import Database.OrderManager;
import Models.Order;
import Models.User;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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

    }

    @Override
    protected void postInit() {
        
    }
    
}
