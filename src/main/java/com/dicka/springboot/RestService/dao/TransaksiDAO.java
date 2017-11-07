/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.Transaksi;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface TransaksiDAO {
    
    Transaksi insertTransaksi(Transaksi transaksi);
    
    Transaksi updateTransaksi(Transaksi transaksi);
    
    boolean ifExistTransaksi(Transaksi transaksi);
    
    Transaksi findOneTransaksiByIdtransaksi(String idtransaksi);
    
    List<Transaksi> findAllTransaksi();
    
    List<Transaksi> findTransaksiAndDetils();
}
