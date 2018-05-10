/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Flower;
import Models.User;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik
 */
public class FlowerManager implements FlowerDAO{
    private final Connection instance;

    public FlowerManager(Connection instance) {
        this.instance = instance;
    }    
    @Override
    public HashMap<Integer, Flower> getAll() {
        HashMap<Integer, Flower> flowers = new HashMap<Integer, Flower>();
        
        try {
            PreparedStatement pstmt = instance.prepareStatement("SELECT * FROM flower ORDER BY flower_id ASC");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("flower_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int stock = rs.getInt("stock");
                
                Flower flower = new Flower(id, name, description, stock);
                flowers.put(flower.getId(), flower);
            }   
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
        
        return flowers;
    }

    @Override
    public boolean updateStock(int id, int stockToUpdate) {
        PreparedStatement pstmt;
        try {
            pstmt = instance.prepareStatement("UPDATE `flower` SET `stock` = ? WHERE `flower`.`flower_id` = ?");
            pstmt.setInt(1, stockToUpdate);
            pstmt.setInt(2, id);
            int updatedRows = pstmt.executeUpdate();
            
            return updatedRows == 1;
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            ex.printStackTrace();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }

        return false;
    }

    @Override
    public int getStock(int id) {
        //
        int stock = -1;
        try {
            PreparedStatement pstmt = instance.prepareStatement("SELECT stock From flower WHERE flower_id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            stock = rs.getInt("stock");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Outputs.sqlExceptionMessage();
            ConnectionSingleton.closeConnection();
            System.exit(0);
        }
        
        return stock;
    }

    
}
