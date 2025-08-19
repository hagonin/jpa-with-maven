package com.banque.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "prenom", nullable = false)
    private String prenom;
    
    @Column(name = "date_naissance")
    private LocalDate dateNaissance;
    
    @Embedded
    private Adresse adresse;
    
    @ManyToMany(mappedBy = "clients", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Compte> comptes ;
    {
        comptes = new HashSet<>();
    }
    
    public Client() {
    }
    
    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Client(String nom, String prenom, LocalDate dateNaissance, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
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
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public Adresse getAdresse() {
        return adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public Set<Compte> getComptes() {
        return comptes;
    }
    
    public void setComptes(Set<Compte> comptes) {
        this.comptes = comptes;
    }
    
    public void addCompte(Compte compte) {
        if (compte != null && !comptes.contains(compte)) {
            comptes.add(compte);
            if (!compte.getClients().contains(this)) {
                compte.getClients().add(this);
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-15s | %-15s | %-12s | %-30s |", 
            id != null ? id : "NULL",
            nom != null ? nom : "NULL",
            prenom != null ? prenom : "NULL",
            dateNaissance != null ? dateNaissance : "NULL",
            adresse != null ? adresse.toString() : "NULL");
    }
}