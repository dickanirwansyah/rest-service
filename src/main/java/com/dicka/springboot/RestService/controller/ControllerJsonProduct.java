/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.controller;

import com.dicka.springboot.RestService.dao.ProductDAO;
import com.dicka.springboot.RestService.entities.Product;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author java-spring
 */
@Service
@RestController("product")
@RequestMapping(value = "/api")
public class ControllerJsonProduct {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerJsonProduct.class);
    
    private ProductDAO productDAO;
    
    @Inject
    public ControllerJsonProduct(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
    
    @PostMapping(value = "/insertProduct")
    public ResponseEntity<Product>insertProduct(@RequestBody Product product, UriComponentsBuilder builder){
        
        LOGGER.debug("proccess insert product with idproduct : "+product.getIdproduct());
        if(productDAO.isExist(product)){
            LOGGER.debug("sorry nama product sudah ada "+product.getIdproduct());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productDAO.insertProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertProduct/{idproduct}")
                .buildAndExpand(product.getIdproduct()).toUri());
        return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>>findAllProducts(){
        LOGGER.info("menampilkan list products");
        List<Product> listproducts = productDAO.findAllProduct();
        if(listproducts.isEmpty()){
            LOGGER.info("Data tidak ada !");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(listproducts, HttpStatus.OK);
    }
}
