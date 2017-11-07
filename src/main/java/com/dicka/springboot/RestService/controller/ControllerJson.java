/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.controller;

import com.dicka.springboot.RestService.entities.Kategori;
import com.dicka.springboot.RestService.entities.Product;
import com.dicka.springboot.RestService.service.KategoriService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author java-spring
 */
@RestController
@RequestMapping(value = "/api")
public class ControllerJson {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerJson.class);
    
    @Autowired
    private KategoriService kategoriService;
    
    
    @GetMapping(value = "/kategoris")
    public ResponseEntity<List<Kategori>>findAllKategoris(){
        List listkategori = kategoriService.findAllKategori();
        
        if(listkategori.isEmpty()){
            LOGGER.info("list kategori tidak ada");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Kategori>>(listkategori, HttpStatus.OK);
    }
    
    @GetMapping(value = "/kategoris/{idkategori}")
    public ResponseEntity<Kategori>findOneKategori(@PathVariable String idkategori){
        
        Kategori kategori = kategoriService.findOneKategori(idkategori);
        
        if(kategori == null){
            LOGGER.info("idkategori tidak ada");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<Kategori>(kategori, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertkategori")
    public ResponseEntity<Void>inserKategori(@RequestBody Kategori kategori, 
            UriComponentsBuilder builder){
        
        LOGGER.info("insert kategori");
        
        boolean valid_duplicated = kategoriService.insertKategori(kategori);
        if(valid_duplicated == false){
            LOGGER.info("nama kategori sudah ada !");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder
                .path("/insertkategori?idkategori={idkategori}")
                .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/updatekategori")
    public ResponseEntity<Void>updateKategori(@RequestBody Kategori kategori, 
            UriComponentsBuilder builder){
        
        LOGGER.info("update kategori");
        
        boolean valid_duplicated = kategoriService.updateKategori(kategori);
        if(valid_duplicated == false){
            LOGGER.info("nama kategori sudah ada !");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/updatekategori?idkategori={idkategori}")
        .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deletekategori/{idkategori}")
    public ResponseEntity<Void>deleteKategori(@PathVariable String idkategori){
        
        LOGGER.info("delete kategori");
        Kategori kategori = kategoriService.findOneKategori(idkategori);
        kategoriService.deleteKategori(kategori);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
