/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Product;
import com.dicka.springboot.RestService.exception.AlreadyException;
import com.dicka.springboot.RestService.repository.ProductRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import javax.inject.Inject;

/**
 *
 * @author java-spring
 */
@Service
@Validated
@Transactional
public class ProductDAOImpl implements ProductDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);
    
    private ProductRepository productRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    
    @Override
    public boolean isExist(Product product) {
      return getByIdProduct(product.getIdproduct()) !=null;
    }

    @Override
    public Product insertProduct(Product product) {
      LOGGER.info("inser product");
      Product existing = productRepository.findOne(product.getIdproduct());
      if(existing !=null){
          throw new AlreadyException
          (String.format("Product is already with idproduct = %s", product.getIdproduct()));
      }
      return productRepository.save(product);
    }

    @Override
    public Product getByIdProduct(String idproduct) {
     LOGGER.info("menampilkan data idproduct");
     return productRepository.findOne(idproduct);
    }

    @Override
    public Product updateProduct(Product product) {
     LOGGER.info("update product");
     if(!entityManager.contains(product))
         product = entityManager.merge(product);
     return product;
    }

    @Override
    public void deleteProduct(Product product) {
     LOGGER.info("delete product");
     productRepository.delete(product);
    }

    @Override
    public List<Product> findAllProduct() {
      LOGGER.info("menampilkan data products");
      return productRepository.findAll();
    }
    
}
