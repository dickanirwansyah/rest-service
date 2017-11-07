/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.controller;

import com.dicka.springboot.RestService.dao.TransaksiDAO;
import com.dicka.springboot.RestService.dao.TransaksiDetilDAO;
import com.dicka.springboot.RestService.entities.Transaksi;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author java-spring
 */
@RestController("transaksi")
@Service
@RequestMapping(value = "/api")
public class ControllerJsonTransaksi {
    
    private TransaksiDAO transaksiDAO;
    
    private TransaksiDetilDAO transaksiDetilDAO;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerJsonTransaksi.class);
    
    @Inject
    public ControllerJsonTransaksi(TransaksiDAO transaksiDAO, TransaksiDetilDAO transaksiDetilDAO){
        this.transaksiDAO = transaksiDAO;
        this.transaksiDetilDAO=transaksiDetilDAO;
    }
    
    @GetMapping(value = "/getDetils")
    public ResponseEntity<List<Transaksi>>findTransaksigetDetils(){
        LOGGER.debug("menampilkan transaksi dan transaksi detils");
        List<Transaksi> listdetils = transaksiDAO.findTransaksiAndDetils();
        if(listdetils.isEmpty()){
            LOGGER.debug("data tidak ada !");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Transaksi>>(listdetils, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertTransaksi")
    public ResponseEntity<Transaksi>insertTransaksi(@RequestBody Transaksi transaksi, UriComponentsBuilder builder){
        
        LOGGER.debug("insert transaksi dengan kode : "+transaksi.getIdtransaksi());
        if(transaksiDAO.ifExistTransaksi(transaksi)){
            LOGGER.debug("transaksi sudah ada");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        transaksiDAO.insertTransaksi(transaksi);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertTransaksi/{idtransaksi}")
                .buildAndExpand(transaksi.getIdtransaksi()).toUri());
        return new ResponseEntity(transaksi, headers, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/transaksis")
    public ResponseEntity<List<Transaksi>>findAllTransaksi(){
        List<Transaksi>listtransaksis= transaksiDAO.findAllTransaksi();
        if(listtransaksis.isEmpty()){
            LOGGER.debug("data transaksi tidak ada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Transaksi>>(listtransaksis, HttpStatus.OK);
    }
}
