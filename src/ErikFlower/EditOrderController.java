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
import com.sun.istack.internal.Nullable;
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
    private OrderDAO orderDAO;
    FlowerDAO flowerDAO;
    
    public EditOrderController(Scanner scanner, User mainUser) {
        super(scanner, mainUser);
    }

    @Override
    protected void preInit() {
        orderDAO = new OrderManager(connection);
        flowerDAO = new FlowerManager(connection);
        
        orders = orderDAO.getAllOders(mainUser.getId());
    }

    @Override
    protected void init() {
        allRight = true;
        Order selectedOrder;
        int orderToUpdate;
        
        showAllOrders();
        do {
            Outputs.selectOrderToUpdate();
            orderToUpdate = AppUtils.selectInt(scanner, true);
            selectedOrder = getOrder(orderToUpdate);
            
            if (orderToUpdate == -1){
                return;
            }
            else if (selectedOrder == null)
                Outputs.selectValidOption();
            
        } while (selectedOrder == null);
        
        int wantUpdate;
        
        do {
            selectedOrder = getOrder(orderToUpdate);
            
            Outputs.whatWantUpdate();
            wantUpdate = AppUtils.selectInt(scanner, false);
            switch(wantUpdate){
                case 1:
                    updateNumber(selectedOrder, null);
                    break;
                case 2:
                    updateAddress(selectedOrder);
                    break;
                case 3:
                    updateFlower(selectedOrder);
                    break;
                case 4:
                    return;
                    
            }
        } while (wantUpdate != 4);
  
    }
    
    @Override
    protected void postInit() {
    }
    
    private Order getOrder(int orderSelected) {
        return orders.get(orderSelected);
    }

    private void updateNumber(Order selectedOrder, @Nullable Integer quantity) {
        int stock = flowerDAO.getStock(selectedOrder.getFlowerType().getId());
        
        if(quantity == null){
            Outputs.howMuch();
            quantity = AppUtils.selectInt(scanner, true);
        }

        if(quantity == -1)
            return;
        
        int newStock;
        
        if(quantity > selectedOrder.getNumOfItems()){
          newStock = stock - quantity;
          if (newStock < 0){
              Outputs.soldOut(stock);
          } else {
            flowerDAO.updateStock(selectedOrder.getFlowerType().getId(), newStock);
            orderDAO.editOrder(selectedOrder.getId(), quantity, selectedOrder.getAddress(), selectedOrder.getFlowerType().getId());
            Outputs.numberSuccefullChanged();
          }
        }else if (quantity < selectedOrder.getNumOfItems()) {
            newStock = stock + selectedOrder.getNumOfItems() - quantity;
            
            flowerDAO.updateStock(selectedOrder.getFlowerType().getId(), newStock);
            orderDAO.editOrder(selectedOrder.getId(), quantity, selectedOrder.getAddress(), selectedOrder.getFlowerType().getId());
            Outputs.numberSuccefullChanged();
        }
    }

    private void updateAddress(Order selectedOrder) {
        Outputs.addNewAdress();
        String newAddress = scanner.nextLine();
        
        orderDAO.editOrder(
                selectedOrder.getId(),
                selectedOrder.getNumOfItems(),
                newAddress,
                selectedOrder.getFlowerType().getId()
        );
        
        Outputs.addressSuccefullChanged();
    }

    private void updateFlower(Order selectedOrder) {
        Flower selectedFlower;
        int flowerOption;
        
        showAllFlowers();

        do {
            Outputs.selectFlower();
            flowerOption = AppUtils.selectInt(scanner, true);
            selectedFlower = getFlower(flowerOption, flowers);
            
            if (flowerOption == -1){
                allRight = false;
                return;
            }
            else if (selectedFlower == null)
                Outputs.selectValidOption();
            
        } while (selectedFlower == null);
        int quantity;
        boolean quantityRight = true;
        
        System.out.println(selectedFlower.toString());
        
        do {
            Outputs.youHaveTochangeNumberOnUpdate(selectedFlower.getStock());
            quantity = AppUtils.selectInt(scanner, true);

            if (quantity == -1) {
                return;
            }else if(quantity > selectedFlower.getStock()){
                quantityRight = false;
            }
        } while (!quantityRight);
        
        updateFirstFlower(
                selectedOrder.getFlowerType().getId(),
                selectedOrder.getFlowerType().getStock()
        );
        
        selectedOrder.setFlowerType(selectedFlower);
        
        updateNumber(selectedOrder, quantity);
    }
    
    private void showAllFlowers() {
        flowers = flowerDAO.getAll();
        Iterator it = flowers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getValue().toString());
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
    
    private Flower getFlower(int flowerSelected, HashMap<Integer, Flower> flowers) {
        return flowers.get(flowerSelected);
    }

    private void updateFirstFlower(int id, int stockToAdd) {
        int actualFlowerStock = flowerDAO.getStock(id);
        int allStock = actualFlowerStock + stockToAdd;
        flowerDAO.updateStock(id, allStock);
    }
}
