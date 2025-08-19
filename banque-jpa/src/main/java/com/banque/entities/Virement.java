package com.banque.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "virement")
@DiscriminatorValue("VIREMENT")
public class Virement extends Operation {
    
    @Column(name = "beneficiaire", nullable = false)
    private String beneficiaire;
    
    public Virement() {
    }
    
    public Virement(LocalDateTime date, BigDecimal montant, String motif, String beneficiaire) {
        super(date, montant, motif);
        this.beneficiaire = beneficiaire;
    }
    
    public String getBeneficiaire() {
        return beneficiaire;
    }
    
    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-20s | %-12s | %-20s | %-15s | %-20s |", 
            getId() != null ? getId() : "NULL",
            getDate() != null ? getDate().toString().substring(0, 19) : "NULL",
            getMontant() != null ? getMontant() : "NULL",
            getMotif() != null ? getMotif() : "NULL",
            getCompte() != null ? getCompte().getNumero() : "NULL",
            beneficiaire != null ? beneficiaire : "NULL");
    }
}