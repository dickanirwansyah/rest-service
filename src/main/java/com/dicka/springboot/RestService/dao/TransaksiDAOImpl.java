/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Transaksi;
import com.dicka.springboot.RestService.exception.AlreadyException;
import com.dicka.springboot.RestService.repository.TransaksiRepository;
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
@Validated
@Transactional
public class TransaksiDAOImpl implements TransaksiDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TransaksiDAOImpl.class);
    
    private TransaksiRepository transaksiRepository;
    
    @Inject
    public TransaksiDAOImpl(TransaksiRepository transaksiRepository){
        this.transaksiRepository = transaksiRepository;
    }

    @Override
    public Transaksi insertTransaksi(Transaksi transaksi) {
      LOGGER.debug("insert transaksi : "+transaksi.getIdtransaksi());
      Transaksi existing = transaksiRepository.findOne(transaksi.getIdtransaksi());
      if(existing!=null){
          throw new AlreadyException(String.format("transaksi sudah ada !", transaksi.getIdtransaksi()));
      }
      return transaksiRepository.save(transaksi);
    }

    @Override
    public Transaksi updateTransaksi(Transaksi transaksi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaksi findOneTransaksiByIdtransaksi(String idtransaksi) {
      LOGGER.debug("getbyid transaksi");
      return transaksiRepository.findOne(idtransaksi);
    }

    @Override
    public List<Transaksi> findAllTransaksi() {
      LOGGER.debug("menampilkan data transaksi api");
      return transaksiRepository.findAll();
    }

    @Override
    public List<Transaksi> findTransaksiAndDetils() {
      List<Transaksi> transaksis = entityManager
              .createNamedQuery("findTransaksiGetTransaksiDetils", Transaksi.class)
              .getResultList();
      return transaksis;
    }

    @Override
    public boolean ifExistTransaksi(Transaksi transaksi) {
      return findOneTransaksiByIdtransaksi(transaksi.getIdtransaksi())!=null;
    }
    
}
