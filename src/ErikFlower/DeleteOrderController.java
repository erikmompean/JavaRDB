/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErikFlower;

import Database.OrderDAO;
import Database.OrderManager;
import static ErikFlower.Controller.scanner;
import Models.Flower;
import Models.Order;
import Models.User;
import Utils.AppUtils;
import Utils.Outputs;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Erik
 */
public class DeleteOrderController extends Controller{
    HashMap<Integer, Order> orders;
    OrderDAO orderDAO;
    boolean allRight = true;
    Order selectedOrder;
    public DeleteOrderController(Scanner scanner, User mainUser) {
        super(scanner, mainUser);
    }

    @Override
    protected void preInit() {
        orderDAO = new OrderManager(connection);
        orders = orderDAO.getAllOders(mainUser.getId());
    }

    @Override
    protected void init() {
        allRight = true;
        int orderToDelete;
       
        showAllOrders();
        
        do {
            System.out.println("Que pedido desea eliminar? ");
            orderToDelete = AppUtils.selectInt(scanner, true);
            selectedOrder = getOrder(orderToDelete);
            
            if (orderToDelete == -1){
                allRight = false;
                return;
            }
            else if (selectedOrder == null)
                Outputs.selectValidOption();
            
        } while (selectedOrder == null);
        
        
        
    }

    @Override
    protected void postInit() {
        if (allRight){
            
            orderDAO.deleteOrder(selectedOrder.getId());
            System.out.println("Pedido borrado correctamente");
        }
    }
    
    private void showAllOrders() {
        Iterator it = orders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Order order = (Order) pair.getValue();
            System.out.println(order.toMyString());
        }
    }

    private Order getOrder(int orderToDelete) {
        return orders.get(orderToDelete);
    }
}
