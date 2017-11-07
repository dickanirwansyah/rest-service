/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "transaksi_detil", 
        catalog = "rest")
public class TransaksiDetil implements Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "idtransaksi", column = @Column(name = "idtransaksi", nullable = false)),
        @AttributeOverride(name = "idbuku", column = @Column(name = "idbuku", nullable = false))    
    })
    private TransaksiDetilid id;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idtransaksi", nullable = false, insertable = false, updatable = false)
    private Transaksi transaksi;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idbuku", nullable = false, insertable = false, updatable = false)
    private Buku buku;
    
    public TransaksiDetil(){
        
    }
    
    public TransaksiDetil(TransaksiDetilid id, Transaksi transaksi, Buku buku){
        this.id = id;
        this.transaksi = transaksi;
        this.buku = buku;
    }
    
    public TransaksiDetilid getId(){
        return id;
    }
    
    public void setTransaksiDetilid(TransaksiDetilid id){
        this.id = id;
    }
    
    public Transaksi getTransaksi(){
        return transaksi;
    }
    
    public void setTransaksi(Transaksi transaksi){
        this.transaksi = transaksi;
    }
    
    public Buku getBuku(){
        return buku;
    }
    
    public void setBuku(Buku buku){
        this.buku = buku;
    }
}
