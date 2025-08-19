package com.banque;

import com.banque.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            
            // Créer une banque
            Banque banque = new Banque("Banque BNP Paribas");
            em.persist(banque);
            
            // Créer des adresses
            Adresse adresse1 = new Adresse("907", "Rue de la Las Cazes", "34000", "Montpellier");
            Adresse adresse2 = new Adresse("42", "Rue de Lodge", "69006", "Lyon");
            Adresse adresse3 = new Adresse("23", "Rue de Nouvelle", "34000", "Montpellier");
            
            // Créer des clients
            Client client1 = new Client("Jungel", "Jeannette", LocalDate.of(1982, 2, 19), adresse1);
            Client client2 = new Client("Angeliette", "Hugo", LocalDate.of(1995, 3, 13), adresse2);
            Client client3 = new Client("Jungel", "Romain", LocalDate.of(1976, 12, 17), adresse1);
            Client client4 = new Client("Segon", "Rose", LocalDate.of(1975, 4, 14), adresse3);
            
            em.persist(client1);
            em.persist(client2);
            em.persist(client3);
            em.persist(client4);
            
            // Créer des comptes
            Compte compte1 = new Compte("001234567890", new BigDecimal("1500.50"));
            compte1.setBanque(banque);
            compte1.addClient(client1);
            compte1.addClient(client2); // Compte joint
            
            Compte compte2 = new Compte("001234567891", new BigDecimal("2500.00"));
            compte2.setBanque(banque);
            compte2.addClient(client2);
            
            Compte compte3 = new Compte("001234567892", new BigDecimal("750.25"));
            compte3.setBanque(banque);
            compte3.addClient(client3);
            
            em.persist(compte1);
            em.persist(compte2);
            em.persist(compte3);
            
            // Créer des comptes spécialisés
            LivretA livretA = new LivretA("001234567893", new BigDecimal("5000.00"), new BigDecimal("3.00"));
            livretA.setBanque(banque);
            livretA.addClient(client1);
            
            AssuranceVie assuranceVie = new AssuranceVie("001234567894", new BigDecimal("15000.00"), 
                LocalDate.of(2030, 12, 31), new BigDecimal("4.50"));
            assuranceVie.setBanque(banque);
            assuranceVie.addClient(client3);
            
            em.persist(livretA);
            em.persist(assuranceVie);
            
            // Créer des opérations
            Operation operation1 = new Operation(LocalDateTime.now().minusDays(5), new BigDecimal("100.00"), "Virement entrant");
            operation1.setCompte(compte1);
            
            Operation operation2 = new Operation(LocalDateTime.now().minusDays(3), new BigDecimal("-50.00"), "Retrait DAB");
            operation2.setCompte(compte1);
            
            Operation operation3 = new Operation(LocalDateTime.now().minusDays(1), new BigDecimal("200.00"), "Dépôt chèque");
            operation3.setCompte(compte2);
            
            Operation operation4 = new Operation(LocalDateTime.now(), new BigDecimal("-25.75"), "Achat en ligne");
            operation4.setCompte(compte3);
            
            // Créer des virements
            Virement virement1 = new Virement(LocalDateTime.now().minusDays(2), new BigDecimal("500.00"), 
                "Virement salaire", "Entreprise ABC");
            virement1.setCompte(livretA);
            
            Virement virement2 = new Virement(LocalDateTime.now().minusHours(6), new BigDecimal("-150.00"), 
                "Virement vers compte courant", "Martin Marie");
            virement2.setCompte(assuranceVie);
            
            em.persist(operation1);
            em.persist(operation2);
            em.persist(operation3);
            em.persist(operation4);
            em.persist(virement1);
            em.persist(virement2);
            
            em.getTransaction().commit();
            
            System.out.println("Données insérées avec succès!");
            
            // Afficher quelques données pour vérifier
            System.out.println("\n=== BANQUES ===");
            System.out.println("+-------+----------------------+");
            System.out.println("| ID    | NOM                  |");
            System.out.println("+-------+----------------------+");
            em.createQuery("SELECT b FROM Banque b", Banque.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+----------------------+");
            
            System.out.println("\n=== CLIENTS ===");
            System.out.println("+-------+-----------------+-----------------+--------------+--------------------------------+");
            System.out.println("| ID    | NOM             | PRENOM          | NAISSANCE    | ADRESSE                        |");
            System.out.println("+-------+-----------------+-----------------+--------------+--------------------------------+");
            em.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+-----------------+-----------------+--------------+--------------------------------+");
            
            System.out.println("\n=== COMPTES ===");
            System.out.println("+-------+-----------------+--------------+----------------------+");
            System.out.println("| ID    | NUMERO          | SOLDE        | BANQUE               |");
            System.out.println("+-------+-----------------+--------------+----------------------+");
            em.createQuery("SELECT c FROM Compte c", Compte.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+-----------------+--------------+----------------------+");
            
            System.out.println("\n=== OPÉRATIONS ===");
            System.out.println("+-------+----------------------+--------------+----------------------+-----------------+");
            System.out.println("| ID    | DATE                 | MONTANT      | MOTIF                | COMPTE          |");
            System.out.println("+-------+----------------------+--------------+----------------------+-----------------+");
            em.createQuery("SELECT o FROM Operation o", Operation.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+----------------------+--------------+----------------------+-----------------+");
            
            System.out.println("\n=== LIVRETS A ===");
            System.out.println("+-------+-----------------+--------------+----------------------+----------+");
            System.out.println("| ID    | NUMERO          | SOLDE        | BANQUE               | TAUX     |");
            System.out.println("+-------+-----------------+--------------+----------------------+----------+");
            em.createQuery("SELECT l FROM LivretA l", LivretA.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+-----------------+--------------+----------------------+----------+");
            
            System.out.println("\n=== ASSURANCES VIE ===");
            System.out.println("+-------+-----------------+--------------+----------------------+--------------+----------+");
            System.out.println("| ID    | NUMERO          | SOLDE        | BANQUE               | DATE FIN     | TAUX     |");
            System.out.println("+-------+-----------------+--------------+----------------------+--------------+----------+");
            em.createQuery("SELECT a FROM AssuranceVie a", AssuranceVie.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+-----------------+--------------+----------------------+--------------+----------+");
            
            System.out.println("\n=== VIREMENTS ===");
            System.out.println("+-------+----------------------+--------------+----------------------+-----------------+----------------------+");
            System.out.println("| ID    | DATE                 | MONTANT      | MOTIF                | COMPTE          | BENEFICIAIRE         |");
            System.out.println("+-------+----------------------+--------------+----------------------+-----------------+----------------------+");
            em.createQuery("SELECT v FROM Virement v", Virement.class)
                .getResultList()
                .forEach(System.out::println);
            System.out.println("+-------+----------------------+--------------+----------------------+-----------------+----------------------+");
                
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}