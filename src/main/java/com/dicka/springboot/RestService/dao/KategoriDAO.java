/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Kategori;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface KategoriDAO {
    
    void insertKategori(Kategori kategori);
    
    void updateKategori(Kategori kategori);
    
    void deleteKategori(Kategori kategori);
    
    Kategori findOneKategori(String idkategori);
    
    List<Kategori> findAllKategoris();
    
    boolean ifKategoriIsExist(String nama);
}
