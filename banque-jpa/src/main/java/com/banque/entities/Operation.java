package com.banque.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_operation", discriminatorType = DiscriminatorType.STRING)
public class Operation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    
    @Column(name = "montant", nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;
    
    @Column(name = "motif")
    private String motif;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compte_id")
    private Compte compte;
    
    public Operation() {
    }
    
    public Operation(LocalDateTime date, BigDecimal montant, String motif) {
        this.date = date;
        this.montant = montant;
        this.motif = motif;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public BigDecimal getMontant() {
        return montant;
    }
    
    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
    
    public String getMotif() {
        return motif;
    }
    
    public void setMotif(String motif) {
        this.motif = motif;
    }
    
    public Compte getCompte() {
        return compte;
    }
    
    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-20s | %-12s | %-20s | %-15s |", 
            id != null ? id : "NULL",
            date != null ? date.toString().substring(0, 19) : "NULL",
            montant != null ? montant : "NULL",
            motif != null ? motif : "NULL",
            compte != null ? compte.getNumero() : "NULL");
    }
}