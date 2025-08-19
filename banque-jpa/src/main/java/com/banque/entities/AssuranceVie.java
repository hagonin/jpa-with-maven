package com.banque.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assurance_vie")
@DiscriminatorValue("ASSURANCE_VIE")
public class AssuranceVie extends Compte {
    
    @Column(name = "date_fin")
    private LocalDate dateFin;
    
    @Column(name = "taux", precision = 5, scale = 2)
    private BigDecimal taux;
    
    public AssuranceVie() {
    }
    
    public AssuranceVie(String numero, BigDecimal solde, LocalDate dateFin, BigDecimal taux) {
        super(numero, solde);
        this.dateFin = dateFin;
        this.taux = taux;
    }
    
    public LocalDate getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    public BigDecimal getTaux() {
        return taux;
    }
    
    public void setTaux(BigDecimal taux) {
        this.taux = taux;
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-15s | %-12s | %-20s | %-12s | %-8s |", 
            getId() != null ? getId() : "NULL",
            getNumero() != null ? getNumero() : "NULL",
            getSolde() != null ? getSolde() : "NULL",
            getBanque() != null ? getBanque().getNom() : "NULL",
            dateFin != null ? dateFin : "NULL",
            taux != null ? taux + "%" : "NULL");
    }
}