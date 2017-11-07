/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.TransaksiDetil;
import com.dicka.springboot.RestService.repository.TransaksiDetilDAORepository;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author java-spring
 */
@Service
@Transactional
@Validated
public class TransaksiDetilDAOImpl implements TransaksiDetilDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(TransaksiDetilDAOImpl.class);
    
    private TransaksiDetilDAORepository transaksiDetilDAORepository;
    
    @Inject
    public TransaksiDetilDAOImpl(TransaksiDetilDAORepository transaksiDetilDAORepository){
        this.transaksiDetilDAORepository = transaksiDetilDAORepository;
    }

    @Override
    public TransaksiDetil insertTransaksiDetils(TransaksiDetil transaksiDetil) {
       return transaksiDetilDAORepository.save(transaksiDetil);
    }

    @Override
    public List<TransaksiDetil> listTransaksiDetils() {
      LOGGER.debug("menampilkan data transaksi detil");
      return transaksiDetilDAORepository.findAll();
    }
    
}
