package org.maocq.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conection {

    Connection cnn;
    Statement state;
    ResultSet res;

    public Conection() {        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop_db?zeroDateTimeBehavior=convertToNull",
                    "root",
                    "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
