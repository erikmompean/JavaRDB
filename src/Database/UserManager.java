/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.User;
import Utils.Outputs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik
 */
public class UserManager implements UserDAO{
    private final Connection instance;

    public UserManager(Connection instance) {
        this.instance = instance;
    }    
    
    @Override
    public boolean register(String email, String password){
        PreparedStatement pstmt;
        try {
            pstmt = instance.prepareStatement("INSERT INTO `user`(`email`, `password`) VALUES (?, ?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
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
    public User login(String email, String password) throws SQLException{
        PreparedStatement pstmt = instance.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<User> users = new ArrayList<User>();
        
        while(rs.next()){
            String rEmail = rs.getString("email");
            int rId = rs.getInt("user_id");
            User user = new User(rId, rEmail);
            users.add(user);
        }
        
        if (users.size() == 1)
            return users.get(0);
        else
            return null;
    }
    
    @Override
    public boolean checkIfExists(String email) {
        String rEmail = null;
        
        try {
            PreparedStatement pstmt = instance.prepareStatement("SELECT `email` FROM user WHERE user.email=?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                rEmail = rs.getString("email");
            }
            
        } catch (SQLException ex) {
            Outputs.sqlExceptionMessage();
            return false;
        }
        
        return rEmail != null;
    }
}
