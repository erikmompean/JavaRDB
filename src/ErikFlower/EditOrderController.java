/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Database.FlowerDAO;
import Database.FlowerManager;
import Database.OrderDAO;
import Database.OrderManager;
import Models.Flower;
import Models.Order;
import Models.User;
import Utils.AppUtils;
import java.io.OutputStream;
import java.util.Scanner;
import Utils.Outputs;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Erik
 */
public class EditOrderController extends Controller{
    private boolean allRight = true;
    private HashMap<Integer, Flower> flowers;
    private HashMap<Integer, Order> orders;
    
    public EditOrderController(Scanner scanner, User mainUser) {
        super(scanner, mainUser);
    }

    @Override
    protected void preInit() {
        OrderDAO orderDAO = new OrderManager(connection);
        FlowerDAO flowerDAO = new FlowerManager(connection);
        
        orders = orderDAO.getAllOders(mainUser.getId());
    }

    @Override
    protected void init() {
        Order selectedOrder;
        int orderToUpdate;
        
        showAllOrders();
        do {
            Outputs.selectOrderToUpdate();
            orderToUpdate = AppUtils.selectInt(scanner, true);
            selectedOrder = getOrder(orderToUpdate);
            
            if (orderToUpdate == -1){
                allRight = false;
                return;
            }
            else if (selectedOrder == null)
                Outputs.selectValidOption();
            
        } while (selectedOrder == null);
        
        int wantUpdate;
        
        do {            
            
            switch(wantUpdate){
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    allRight = false;
                    return;
                    
            }
        } while (wantUpdate != 4);

        
    }
    
    @Override
    protected void postInit() {
        if(allRight){
            
        }
    }
    
    private int selectWhatToUpdate(Order selectedOrder){
        System.out.println(selectedOrder.toMyString());
        Outputs.whatWantUpdate();
        
        return AppUtils.selectInt(scanner, true);
    }
    
    private void showAllOrders() {
        Iterator it = orders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Order order = (Order) pair.getValue();
            System.out.println(order.toMyString());
        }
    }
    
    private Order getOrder(int orderSelected) {
        return orders.get(orderSelected);
    }
}
