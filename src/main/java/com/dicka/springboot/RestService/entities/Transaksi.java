/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "transaksi", 
        catalog = "rest")
@NamedNativeQueries({
    @NamedNativeQuery(
     name = "findTransaksiGetTransaksiDetils", 
     query = "SELECT * FROM transaksi inner join transaksi_detil "
             + "on(transaksi.idtransaksi=transaksi_detil.idtransaksi)", 
     resultClass = Transaksi.class)
})
public class Transaksi implements Serializable{
    
    @Id @Column(name = "idtransaksi", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idtransaksi;
    
    @Column(name = "tanggal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @JsonIgnore
    @OneToMany(mappedBy = "transaksi", cascade = CascadeType.ALL)
    private Set<TransaksiDetil> transaksiDetils = new HashSet<TransaksiDetil>();
    
    public Set<TransaksiDetil> getTransaksiDetils(){
        return transaksiDetils;
    }
    
    public void setTransaksiDetils(TransaksiDetil transaksiDetil){
        this.transaksiDetils = transaksiDetils;
    }
    
    public String getIdtransaksi(){
        return idtransaksi;
    }
    
    public void setIdtransaksi(String idtransaksi){
        this.idtransaksi = idtransaksi;
    }
    
    public Date getTanggal(){
        return tanggal;
    }
    
    public void setTanggal(Date tanggal){
        this.tanggal = tanggal;
    }
}
