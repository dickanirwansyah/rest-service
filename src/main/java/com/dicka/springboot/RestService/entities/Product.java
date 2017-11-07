/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "product", 
        catalog = "rest")
public class Product implements Serializable{
    
    @Id
    @Column(name = "idproduct", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idproduct;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "jumlah", nullable = false)
    private int jumlah;
    
    @Column(name = "kadaluarsa", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date kadaluarsa;
    
    @ManyToOne
    @JoinColumn(name = "idkategori", nullable = false)
    private Kategori kategori;
    
    
    public String getIdproduct(){
        return idproduct;
    }
    
    public void setIdproduct(String idproduct){
        this.idproduct = idproduct;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama=nama;
    }
    
    public int getJumlah(){
        return jumlah;
    }
    
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
    
    public Date getKadaluarsa(){
        return kadaluarsa;
    }
    
    public void setKadaluarsa(Date kadaluarsa){
        this.kadaluarsa = kadaluarsa;
    }
    
    public Kategori getKategori(){
        return kategori;
    }
    
    public void setKategori(Kategori kategori){
        this.kategori=kategori;
    }
}
