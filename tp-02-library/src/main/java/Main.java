import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        
        try {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();
            
            System.out.println(" Connected to MariaDB database jpa_tp02");
            
            // Find livre by id
            Livre livre = em.find(Livre.class, 1);
            if (livre != null) {
                System.out.println(" Livre found: " + livre);
            } else {
                System.out.println(" No livre found with id 1");
            }
            
            // Find another livre
            Livre livre2 = em.find(Livre.class, 3);
            if (livre2 != null) {
                System.out.println(" Livre found: " + livre2);
            } else {
                System.out.println(" No livre found with id 3");
            }
            
        } catch (Exception e) {
            System.err.println(" Connection failed:");
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}