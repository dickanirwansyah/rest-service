/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.controller;

import com.dicka.springboot.RestService.dao.BukuDAO;
import com.dicka.springboot.RestService.entities.Buku;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
@Service
@RestController("buku")
@RequestMapping(value = "/api")
public class ControllerJsonBuku {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerJsonBuku.class);
    
    private BukuDAO bukuDAO;
    
    @Inject
    public ControllerJsonBuku(BukuDAO bukuDAO){
        this.bukuDAO = bukuDAO;
    }
    
    @PostMapping(value = "/insertBuku")
    public ResponseEntity<Buku>insertBuku(@RequestBody Buku buku, UriComponentsBuilder builder){
        LOGGER.debug("insert buku dengan kode : "+buku.getIdbuku());
        if(bukuDAO.ifIsExist(buku)){
            LOGGER.debug("maaf nama buku sudah ada dengan kode : "+buku.getIdbuku());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        bukuDAO.insertBuku(buku);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertBuku/{idbuku}")
                .buildAndExpand(buku.getIdbuku()).toUri());
        return new ResponseEntity<>(buku, headers, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/updateBuku/{idbuku}")
    public ResponseEntity<Buku>updateBuku(@RequestBody Buku buku, @PathVariable String idbuku){
        LOGGER.debug("update buku");
        Buku databuku = bukuDAO.findOneBukuGetById(idbuku);
        
        if(databuku == null){
            LOGGER.debug("buku tidak ditemukan !");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        databuku.setJudul(buku.getJudul());
        databuku.setPenerbit(buku.getPenerbit());
        bukuDAO.updateBuku(databuku);
        return new ResponseEntity<>(databuku, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deleteBuku/{idbuku}")
    public ResponseEntity<Buku>deleteBuku(@PathVariable String idbuku){
        LOGGER.debug("delete buku dengan kode : "+idbuku);
        Buku buku = bukuDAO.findOneBukuGetById(idbuku);
        if(buku==null){
            LOGGER.debug("data buku tidak ada");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        bukuDAO.deleteBuku(buku);
        return new ResponseEntity(buku, HttpStatus.OK);
    }
    
    @GetMapping(value = "/bukus")
    public ResponseEntity<List<Buku>>findAllBukus(){
       LOGGER.debug("menampilkan data buku");
       List<Buku> listbuku = bukuDAO.findAllBuku();
       if(listbuku.isEmpty()){
           LOGGER.debug("data buku tidak ada !");
           return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<List<Buku>>(listbuku, HttpStatus.OK);
    }
}
