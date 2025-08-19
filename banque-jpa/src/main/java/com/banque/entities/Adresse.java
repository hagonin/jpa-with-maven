package com.banque.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public record Adresse(String numero, String rue, String codePostal, String ville) {
    
    public Adresse() {
        this("", "", "", "");
    }
    
    @Override
    public String toString() {
        return numero + " " + rue + ", " + codePostal + " " + ville;
    }
}