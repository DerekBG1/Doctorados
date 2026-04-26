package grupofp.persistencia;

import grupofp.modelo.Cliente;
import grupofp.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * DAO (Data Access Object) para la entidad Cliente.
 * Gestiona las operaciones CRUD usando JPA/Hibernate en lugar de JDBC.
 */
public class ClienteDAO {

    // CREATE
    public void guardar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // READ por email (clave primaria)
    public Cliente buscarPorEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Cliente.class, email);
        } finally {
            em.close();
        }
    }

    // READ todos
    public List<Cliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Cliente> query =
                em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void actualizar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // DELETE
    public boolean eliminar(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, email);
            if (cliente == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
