/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.service;

import com.dicka.springboot.RestService.dao.KategoriDAO;
import com.dicka.springboot.RestService.entities.Kategori;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service
public class KategoriServiceImpl implements KategoriService{
    
    @Autowired
    private KategoriDAO kategoriDAO;

    @Override
    public boolean insertKategori(Kategori kategori) {
      if(kategoriDAO.ifKategoriIsExist(kategori.getNama())){
          return false;
      }else{
          kategoriDAO.insertKategori(kategori);
          return true;
      }
    }

    @Override
    public boolean updateKategori(Kategori kategori) {
      if(kategoriDAO.ifKategoriIsExist(kategori.getNama())){
          return false;
      }else{
          kategoriDAO.updateKategori(kategori);
          return true;
      }
    }

    @Override
    public void deleteKategori(Kategori kategori) {
     kategoriDAO.deleteKategori(kategori);
    }

    @Override
    public Kategori findOneKategori(String idkategori) {
      return kategoriDAO.findOneKategori(idkategori);
    }

    @Override
    public List<Kategori> findAllKategori() {
      return kategoriDAO.findAllKategoris();
    }
    
}
