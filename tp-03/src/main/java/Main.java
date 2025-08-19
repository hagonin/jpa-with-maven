
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp-03");
             EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Client cyril = new Client("Mariotte", "Sona");
            Emprunt emprunt = new Emprunt(LocalDate.now(), LocalDate.of(2025, 8, 19), 1, cyril);
            Livre livreDeRecette = em.find(Livre.class, 5);
            if (livreDeRecette != null) {
                emprunt.addLivre(livreDeRecette);
//                emprunt.getLivres().add(livreDeRecette);
//                livreDeRecette.getEmprunts().add(emprunt);
            }
            Livre germinal = em.find(Livre.class, 2);
            if (germinal != null) {
                emprunt.addLivre(germinal);
//                emprunt.getLivres().add(germinal);
//                germinal.getEmprunts().add(emprunt);
            }

            em.persist(cyril);
            em.getTransaction().commit();
        }
    }
}