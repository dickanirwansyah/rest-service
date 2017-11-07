/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "kategori", 
        catalog = "rest")
public class Kategori implements Serializable{
    
    @Id @Column(name = "idkategori", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idkategori;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @JsonIgnore
    @OneToMany(mappedBy = "kategori")
    @Column(nullable = true)
    private Set<Product> products = new HashSet<Product>();
    
    public Set<Product> getProducts(){
        return products;
    }
    
    public void setProducts(Set<Product> products){
        this.products = products;
    }
    
    public String getIdkategori(){
        return idkategori;
    }
    
    public void setIdkategori(String idkategori){
        this.idkategori = idkategori;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
}
