/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.ArrayList;
import Models.Flower;
import java.util.HashMap;

/**
 *
 * @author Erik
 */
public interface FlowerDAO {
    HashMap<Integer, Flower> getAll();
    boolean updateStock(int id, int stockToUpdate);
}
