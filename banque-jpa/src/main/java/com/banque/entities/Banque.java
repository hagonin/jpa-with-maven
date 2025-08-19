package com.banque.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "banque")
public class Banque implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @OneToMany(mappedBy = "banque", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Compte> comptes;
    {
        comptes = new HashSet<>();
    }
    
    public Banque() {
    }
    
    public Banque(String nom) {
        this.nom = nom;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Set<Compte> getComptes() {
        return comptes;
    }
    
    public void setComptes(Set<Compte> comptes) {
        this.comptes = comptes;
    }
    
    public void addCompte(Compte compte) {
        comptes.add(compte);
        compte.setBanque(this);
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-20s |", 
            id != null ? id : "NULL", 
            nom != null ? nom : "NULL");
    }
}