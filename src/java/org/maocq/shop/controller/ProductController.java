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
public class ProductController {

    @Autowired
    private JsonTransformer jsonTransformer;


    @RequestMapping(value = "/products/{idProduct}", method = RequestMethod.GET, produces = "application/json")
    public void shopIp(HttpServletRequest httpRequest,
                       HttpServletResponse httpServletResponse,
                       @PathVariable("idProduct") int idProduct)
                       throws IOException {

        int id = idProduct;
        ProductModel Product = new ProductModel();
        ResultSet result  = Product.find(id);
        
        Product product = new Product();
              
        if (result == null) {
            //
        } else {            
            try {
                result.next();
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getInt("price"));
            } catch (SQLException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }                
        
        String jsonResponse = jsonTransformer.toJson(product);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(jsonResponse);

    }

    @RequestMapping(value = "/products/add/{name}/{description}/{price}/{shop}", method = RequestMethod.GET, produces = "application/json")
    public void shopAdd(HttpServletRequest httpRequest,
                       HttpServletResponse httpServletResponse,
                       @PathVariable("name") String name,
                       @PathVariable("description") String description,
                       @PathVariable("price") int price,
                       @PathVariable("shop") int shop)
                       throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setShop(shop);
        
        ProductModel Product = new ProductModel();
        int status = Product.add(product);

        String jsonResponse = jsonTransformer.toJson(product);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(jsonResponse);

    }

    @RequestMapping(value = "/products/{idProduct}/delete", method = RequestMethod.GET, produces = "application/json")
    public void shopDelete(HttpServletRequest httpRequest,
                       HttpServletResponse httpServletResponse,
                       @PathVariable("idProduct") int idProduct)
                       throws IOException {

               
        ProductModel Product = new ProductModel();
        int status = Product.delete(idProduct);
        
        String jsonResponse = "{'status': 'success'}";
        
        if (status == 0) {
            jsonResponse = "{'status': 'error'}";
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");        

    }

}