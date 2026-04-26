package grupofp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase utilitaria para gestionar el EntityManagerFactory de JPA.
 * Sigue el patrón Singleton para evitar crear múltiples factories.
 */
public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("DoctoradosPU");

    private JPAUtil() {}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
