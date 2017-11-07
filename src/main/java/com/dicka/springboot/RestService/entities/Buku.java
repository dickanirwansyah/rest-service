/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "buku", 
        catalog = "rest")
public class Buku implements Serializable{
    
    @Id @Column(name = "idbuku", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idbuku;
    
    @Column(name = "judul", nullable = false)
    private String judul;
    
    @Column(name = "penerbit", nullable = false)
    private String penerbit;
    
    @JsonIgnore
    @OneToMany(mappedBy = "buku", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Set<TransaksiDetil> transaksiDetils = new HashSet<TransaksiDetil>();
    
    
    public Set<TransaksiDetil> getTransaksiDetils(){
        return transaksiDetils;
    }
    
    public void setTransaksiDetils(Set<TransaksiDetil> transaksiDetils){
        this.transaksiDetils = transaksiDetils;
    }
    
    public String getIdbuku(){
        return idbuku;
    }
    
    public void setIdbuku(String idbuku){
        this.idbuku = idbuku;
    }
    
    public String getJudul(){
        return judul;
    }
    
    public void setJudul(String judul){
        this.judul = judul;
    }
    
    public String getPenerbit(){
        return penerbit;
    }
    
    public void setPenerbit(String penerbit){
        this.penerbit = penerbit;
    }
}
