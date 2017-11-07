/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dicka.springboot.RestService.repository;

import com.dicka.springboot.RestService.entities.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author java-spring
 */
@RepositoryRestResource
public interface TransaksiRepository extends JpaRepository<Transaksi, String>{
   
}
