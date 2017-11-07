/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Product;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface ProductDAO {
    
    boolean isExist(Product product);
    
    Product insertProduct(Product product);
    
    Product getByIdProduct(String idproduct);
    
    Product updateProduct(Product product);
    
    void deleteProduct(Product product);
    
    List<Product> findAllProduct();
}
