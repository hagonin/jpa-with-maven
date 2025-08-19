package com.banque.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "compte")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_compte", discriminatorType = DiscriminatorType.STRING)
public class Compte implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero", nullable = false, unique = true)
    private String numero;
    
    @Column(name = "solde", nullable = false, precision = 15, scale = 2)
    private BigDecimal solde;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banque_id")
    private Banque banque;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "compte_client",
        joinColumns = @JoinColumn(name = "compte_id"),
        inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Set<Client> clients;
    
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> operations ;
    {
        clients = new HashSet<>();
        operations = new HashSet<>();
    }
    
    public Compte() {
    }
    
    public Compte(String numero, BigDecimal solde) {
        this.numero = numero;
        this.solde = solde;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public BigDecimal getSolde() {
        return solde;
    }
    
    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }
    
    public Banque getBanque() {
        return banque;
    }
    
    public void setBanque(Banque banque) {
        this.banque = banque;
    }
    
    public Set<Client> getClients() {
        return clients;
    }
    
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
    
    public Set<Operation> getOperations() {
        return operations;
    }
    
    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }
    
    public void addOperation(Operation operation) {
        operations.add(operation);
        operation.setCompte(this);
    }
    
    public void addClient(Client client) {
        if (client != null && !clients.contains(client)) {
            clients.add(client);
            if (!client.getComptes().contains(this)) {
                client.getComptes().add(this);
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-15s | %-12s | %-20s |", 
            id != null ? id : "NULL",
            numero != null ? numero : "NULL",
            solde != null ? solde : "NULL",
            banque != null ? banque.getNom() : "NULL");
    }
}