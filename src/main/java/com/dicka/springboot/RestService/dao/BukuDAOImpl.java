/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Buku;
import com.dicka.springboot.RestService.exception.AlreadyException;
import com.dicka.springboot.RestService.repository.BukuRepository;
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
public class BukuDAOImpl implements BukuDAO{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BukuDAOImpl.class);
    
    private BukuRepository bukuRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Inject
    public BukuDAOImpl(BukuRepository bukuRepository){
        this.bukuRepository=bukuRepository;
    }

    @Override
    public boolean ifIsExist(Buku buku) {
      return findOneBukuGetById(buku.getIdbuku()) !=null;
    }

    @Override
    public Buku insertBuku(Buku buku) {
      LOGGER.debug("insert buku dengan kode : "+buku.getIdbuku());
      Buku existing = bukuRepository.findOne(buku.getIdbuku());
      if(existing!=null){
          throw new AlreadyException
        (String.format("buku sudah ada dengan idbuku = %s", buku.getIdbuku()));
      }
      return bukuRepository.save(buku);
    }

    @Override
    public Buku updateBuku(Buku buku) {
     LOGGER.debug("update buku dengan kode : "+buku.getIdbuku());
     if(!entityManager.contains(buku))
         buku  = entityManager.merge(buku);
     return buku;
    }

    @Override
    public void deleteBuku(Buku buku) {
      LOGGER.debug("delete buku dengan kode : "+buku.getIdbuku());
      bukuRepository.delete(buku);
    }

    @Override
    public Buku findOneBukuGetById(String idbuku) {
     LOGGER.debug("menampilkan buku dengan kode : "+idbuku);
     return bukuRepository.findOne(idbuku);
    }

    @Override
    public List<Buku> findAllBuku() {
      LOGGER.info("menampilkan data buku");
      return bukuRepository.findAll();
    }
    
}
