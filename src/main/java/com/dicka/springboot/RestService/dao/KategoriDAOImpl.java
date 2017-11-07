/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Kategori;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author java-spring
 */
@Transactional
@Repository
public class KategoriDAOImpl implements KategoriDAO{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void insertKategori(Kategori kategori) {
      entityManager.persist(kategori);
    }

    @Override
    public void updateKategori(Kategori kategori) {
      Kategori kat = findOneKategori(kategori.getIdkategori());
      kat.setNama(kategori.getNama());
      entityManager.flush();
    }

    @Override
    public void deleteKategori(Kategori kategori) {
      entityManager.remove(kategori);
    }

    @Override
    public Kategori findOneKategori(String idkategori) {
      return entityManager.find(Kategori.class, idkategori);
    }

    @Override
    public List<Kategori> findAllKategoris() {
      String jpql = "FROM Kategori as category ORDER BY category.idkategori DESC";
      return (List<Kategori>)entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public boolean ifKategoriIsExist(String nama) {
     String jpql = "FROM Kategori as category WHERE category.nama = ?";
     int count = entityManager.createQuery(jpql)
             .setParameter(1, nama).getResultList().size();
     return count > 0 ? true : false;
    }
    
}
