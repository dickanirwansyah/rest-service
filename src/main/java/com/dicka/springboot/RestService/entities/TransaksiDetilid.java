/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author java-spring
 */
@Embeddable
public class TransaksiDetilid implements Serializable{
 
    @Column(name = "idtransaksi", nullable = false)
    private String idtransaksi;
    
    @Column(name = "idbuku", nullable = false)
    private String idbuku;
    
    public TransaksiDetilid(){
        
    }
    
    public TransaksiDetilid(String idtransaksi, String idbuku){
        this.idtransaksi = idtransaksi;
        this.idbuku = idbuku;
    }
    
    public String getIdtransaksi(){
        return idtransaksi;
    }
    
    public void setIdtransaksi(String idtransaksi){
        this.idtransaksi = idtransaksi;
    }
    
    public String getIdbuku(){
        return idbuku;
    }
    
    public void setIdbuku(String idbuku){
        this.idbuku = idbuku;
    }
}
