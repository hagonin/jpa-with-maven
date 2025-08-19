package com.banque.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "livret_a")
@DiscriminatorValue("LIVRET_A")
public class LivretA extends Compte {
    
    @Column(name = "taux", precision = 5, scale = 2)
    private BigDecimal taux;
    
    public LivretA() {
        super();
    }
    
    public LivretA(String numero, BigDecimal solde, BigDecimal taux) {
        super(numero, solde);
        this.taux = taux;
    }
    
    public BigDecimal getTaux() {
        return taux;
    }
    
    public void setTaux(BigDecimal taux) {
        this.taux = taux;
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-15s | %-12s | %-20s | %-8s |", 
            getId() != null ? getId() : "NULL",
            getNumero() != null ? getNumero() : "NULL",
            getSolde() != null ? getSolde() : "NULL",
            getBanque() != null ? getBanque().getNom() : "NULL",
            taux != null ? taux + "%" : "NULL");
    }
}