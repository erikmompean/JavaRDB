/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Flower;
import Models.Order;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Erik
 */
public class OrderManager implements OrderDAO{

    private final Connection instance;

    public OrderManager(Connection instance) {
        this.instance = instance;
    }    
    
    @Override
    public HashMap<Integer, Order> getAllOders(int userId) {
        
        HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
        
        try {
            PreparedStatement pstmt = instance.prepareStatement("SELECT `order`.*, flower.flower_id, flower.name "
                    + "FROM `order` "
                    + "LEFT JOIN flower "
                    + "ON `order`.`flower_id` = flower.flower_id "
                    + "ORDER BY `order`.`order_id`");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("order_id");
                int numOfItems = rs.getInt("number_of_items");
                String address = rs.getString("address");
                String flowerName = rs.getString("name");
                
                Flower flower = new Flower(flowerName);
                Order order = new Order(id, numOfItems,flower, address);
                orders.put(order.getId(), order);
            }   
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
        
        return orders;
    }
    
    @Override
    public boolean createOrder(int numberOfItems, String address, int flowerId, int userId) {
        PreparedStatement pstmt;
        try {
            pstmt = instance.prepareStatement("INSERT INTO `order`( `number_of_items`, `address`, `flower_id`, `user_id`) VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, numberOfItems);
            pstmt.setString(2, address);
            pstmt.setInt(3, flowerId);
            pstmt.setInt(4, userId);
            int updatedRows = pstmt.executeUpdate();
            
            return updatedRows == 1;
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }

        return false;
    }
    
    @Override
    public boolean editOrder(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteOrder(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




}
