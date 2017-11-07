/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.dao;

import com.dicka.springboot.RestService.entities.TransaksiDetil;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface TransaksiDetilDAO {
    
    TransaksiDetil insertTransaksiDetils(TransaksiDetil transaksiDetil);
    
    List<TransaksiDetil> listTransaksiDetils();
}
