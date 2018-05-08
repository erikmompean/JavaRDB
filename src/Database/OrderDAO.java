/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Order;
import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public interface OrderDAO {
    ArrayList<Order> getAllOders(int userId);
    boolean createOrder(int numberOfItems, String address, int flowerId, int userId);
    boolean editOrder(int id);
    boolean deleteOrder(int id);
    
}
