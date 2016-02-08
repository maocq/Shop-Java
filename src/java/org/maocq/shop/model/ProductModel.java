package org.maocq.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductModel extends Conection{

    public int add(Product product){
        int amended = 0;
        try {
            String query = "INSERT INTO products (name, description, price, shop_id)"
                    + " VALUES ('"+product.getName()+"', '"+product.getDescription()+"', '"+product.getPrice()+"', '"+product.getShop()+"')";

            state = cnn.createStatement();
            amended = state.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return amended;
    }


    public ResultSet forShop(int shopId){
        try {
            String query = "SELECT * FROM products WHERE shop_id = " + shopId;
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
            String query = "SELECT * FROM products WHERE id = " + id + " LIMIT 1";
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
           
            String query = "DELETE FROM products WHERE id ="+ idShop;           
            state = cnn.createStatement();
            amended = state.executeUpdate(query);
                        
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return amended;
    }

}
