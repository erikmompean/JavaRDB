/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Order;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Erik
 */
public interface OrderDAO {
    HashMap<Integer, Order> getAllOders(int userId);
    boolean createOrder(int numberOfItems, String address, int flowerId, int userId);
    boolean editOrder(int id);
    boolean deleteOrder(int id);
    
}
