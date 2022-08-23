package com.bosonit.ejs31.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    Integer id;
    @Column(unique = true)
    String username;
    String password;
    boolean admin;


}
