/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Order;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<Order> getAllOders(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
