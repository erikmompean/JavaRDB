/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.User;
import java.sql.SQLException;

/**
 *
 * @author Erik
 */
public interface UserDAO {
    boolean register(String email, String password);
    User login(String email, String password) throws SQLException;
    boolean checkIfExists(String email);
}
