package grupofp.persistencia;

import grupofp.modelo.Articulo;
import grupofp.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * DAO para la entidad Articulo.
 * Gestiona las operaciones CRUD usando JPA/Hibernate.
 */
public class ArticuloDAO {

    // CREATE
    public void guardar(Articulo articulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(articulo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // READ por código (clave primaria)
    public Articulo buscarPorCodigo(String codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Articulo.class, codigo);
        } finally {
            em.close();
        }
    }

    // READ todos
    public List<Articulo> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Articulo> query =
                em.createQuery("SELECT a FROM Articulo a", Articulo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void actualizar(Articulo articulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(articulo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // DELETE
    public boolean eliminar(String codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Articulo articulo = em.find(Articulo.class, codigo);
            if (articulo == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(articulo);
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
