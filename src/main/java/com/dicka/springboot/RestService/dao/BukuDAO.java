/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Buku;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface BukuDAO {
    
    boolean ifIsExist(Buku buku);
    
    Buku insertBuku(Buku buku);
    
    Buku updateBuku(Buku buku);
    
    void deleteBuku(Buku buku);
    
    Buku findOneBukuGetById(String idbuku);
    
    List<Buku> findAllBuku();
}
