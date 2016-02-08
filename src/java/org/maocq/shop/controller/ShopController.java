package org.maocq.shop.controller;

import org.maocq.shop.json.JsonTransformer;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.maocq.shop.model.Product;
import org.maocq.shop.model.ProductModel;
import org.maocq.shop.model.Shop;
import org.maocq.shop.model.ShopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ShopController {
    
    @Autowired
    private JsonTransformer jsonTransformer;
    
    
    @RequestMapping(value = "/shops/{idShop}", method = RequestMethod.GET, produces = "application/json")
    public void shopIp(HttpServletRequest httpRequest, 
                       HttpServletResponse httpServletResponse, 
                       @PathVariable("idShop") int idShop) 
                       throws IOException {
                        
        int id = idShop;               
        ShopModel Shop = new ShopModel();
        ResultSet result  = Shop.find(id);
        
        Shop shop = new Shop();
        if (result == null) {
            //
        } else {            
            try {
                result.next();
                shop.setId(result.getInt("id"));
                shop.setName(result.getString("name"));
                shop.setLocation(result.getString("location"));
                
                //Products
                ProductModel Product = new ProductModel();
                ResultSet resProduct  = Product.forShop(idShop);        
                ArrayList<Product> products = new ArrayList<Product>();
                                
                if (result == null) {
                    //
                } else {            
                    try {
                        while (resProduct.next()) {
                            products.add(new Product(
                                    resProduct.getInt("id"), 
                                    resProduct.getString("name"), 
                                    resProduct.getString("description"), 
                                    resProduct.getInt("price")
                            ));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
                shop.setProducts(products);
                
            } catch (SQLException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }        
        
        String jsonResponse = jsonTransformer.toJson(shop);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(jsonResponse);
                        
    }
    
    
    @RequestMapping(value = "/shops", method = RequestMethod.GET, produces = "application/json")
    public void shops(HttpServletRequest httpRequest, 
                       HttpServletResponse httpServletResponse) 
                       throws IOException {
                
        ShopModel Shop = new ShopModel();
        ResultSet result  = Shop.all();        
        ArrayList<Shop> shops = new ArrayList<Shop>();
               
        if (result == null) {
            //
        } else {            
            try {
                while (result.next()) {
                    shops.add(new Shop(
                            result.getInt("id"), 
                            result.getString("name"), 
                            result.getString("location")
                    ));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
                
        String jsonResponse = jsonTransformer.toJson(shops);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(jsonResponse);
                        
    }
    
    
    @RequestMapping(value = "/shops/add/{name}/{location}", method = RequestMethod.GET, produces = "application/json")
    public void shopAdd(HttpServletRequest httpRequest, 
                       HttpServletResponse httpServletResponse,
                       @PathVariable("name") String name,
                       @PathVariable("location") String location)
                       throws IOException {
                        
        Shop shop = new Shop();
        shop.setName(name);
        shop.setLocation(location);
        
        ShopModel Shop = new ShopModel();
        int status = Shop.add(shop);
                
        String jsonResponse = jsonTransformer.toJson(shop);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");        
        httpServletResponse.getWriter().println(jsonResponse);
                        
    }
    
    
    
    @RequestMapping(value = "/shops/{idShop}/delete", method = RequestMethod.GET, produces = "application/json")
    public void shopDelete(HttpServletRequest httpRequest, 
                       HttpServletResponse httpServletResponse, 
                       @PathVariable("idShop") int idShop) 
                       throws IOException {
                        
        ShopModel Shop = new ShopModel();
        int status = Shop.delete(idShop);
        
        String jsonResponse = "{'status': 'success'}";
        
        if (status == 0) {
            jsonResponse = "{'status': 'error'}";
        }
                
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                        
    }    
        
}
