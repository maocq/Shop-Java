package org.maocq.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopModel extends Conection{
    
    public int add(Shop shop){
        int amended = 0;
        try {
            String query = "INSERT INTO shop (name, location) VALUES ('"+shop.getName()+"', '"+shop.getLocation()+"')";
            
            state = cnn.createStatement();
            amended = state.executeUpdate(query);
                        
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return amended;
    }
    
    
    public ResultSet all(){
        try {
            String query = "SELECT * FROM shop";
            state = cnn.createStatement();
            
            res = state.executeQuery(query);
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    
    public ResultSet find(int id){
        try {
            String query = "SELECT * FROM shop WHERE id = " + id + " LIMIT 1";
            state = cnn.createStatement();
            
            res = state.executeQuery(query);
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    
    public int delete(int idShop){
        int amended = 0;
        try {
           
            String query = "DELETE FROM shop WHERE id ="+ idShop;           
            state = cnn.createStatement();
            amended = state.executeUpdate(query);
            
            if(amended != 0) {
                query = "DELETE FROM products WHERE shop_id ="+ idShop;
                state.executeUpdate(query);
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return amended;
    }
        
}
