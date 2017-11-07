/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.controller;

import com.dicka.springboot.RestService.dao.TransaksiDetilDAO;
import com.dicka.springboot.RestService.entities.TransaksiDetil;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author java-spring
 */
@Service
@RestController("transaksiDetil")
@RequestMapping(value = "/api")
public class ControllerJsonTransaksiDetils {
    
    private TransaksiDetilDAO transaksiDetilDAO;
    
    private static final Logger LOGGER= 
            LoggerFactory.getLogger(ControllerJsonTransaksiDetils.class);
    
    @Inject
    public ControllerJsonTransaksiDetils(TransaksiDetilDAO transaksiDetilDAO){
        this.transaksiDetilDAO=transaksiDetilDAO;
    }
    
    @GetMapping(value = "/detils")
    public ResponseEntity<List<TransaksiDetil>> findAllTransaksi(){
        List<TransaksiDetil> listdetils = transaksiDetilDAO.listTransaksiDetils();
        if(listdetils.isEmpty()){
            LOGGER.debug("data tidak ada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<TransaksiDetil>>(listdetils, HttpStatus.OK);
    }
}
