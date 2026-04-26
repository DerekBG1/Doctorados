package grupofp.persistencia;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Pedido;
import grupofp.modelo.PedidoNoCancelableException;
import grupofp.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * DAO para la entidad Pedido.
 * Gestiona las operaciones CRUD usando JPA/Hibernate.
 * Incluye la regla de negocio: pedidos con cantidad > 3 no se pueden eliminar.
 */
public class PedidoDAO {

    // CREATE - recibe los objetos Cliente y Articulo ya gestionados por JPA
    public boolean guardar(String emailCliente, String codigoArticulo, int cantidad) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Cliente cliente = em.find(Cliente.class, emailCliente);
            Articulo articulo = em.find(Articulo.class, codigoArticulo);

            if (cliente == null || articulo == null) {
                return false;
            }

            Pedido pedido = new Pedido(cliente, articulo, cantidad);
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // READ por número de pedido
    public Pedido buscarPorNumero(int numeroPedido) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Pedido.class, numeroPedido);
        } finally {
            em.close();
        }
    }

    // READ todos
    public List<Pedido> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JOIN FETCH para cargar cliente y articulo en la misma consulta
            TypedQuery<Pedido> query = em.createQuery(
                "SELECT p FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.articulo",
                Pedido.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // READ pedidos de un cliente concreto (JPQL)
    public List<Pedido> listarPorCliente(String emailCliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                "SELECT p FROM Pedido p WHERE p.cliente.email = :email",
                Pedido.class
            );
            query.setParameter("email", emailCliente);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // DELETE - aplica regla de negocio: cantidad > 3 → no cancelable
    public boolean eliminar(int numeroPedido) throws PedidoNoCancelableException {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Pedido pedido = em.find(Pedido.class, numeroPedido);
            if (pedido == null) {
                return false;
            }
            if (pedido.getCantidad() > 3) {
                throw new PedidoNoCancelableException(
                    "No se puede eliminar el pedido " + numeroPedido +
                    " porque tiene " + pedido.getCantidad() + " unidades (máximo 3)."
                );
            }
            em.getTransaction().begin();
            em.remove(pedido);
            em.getTransaction().commit();
            return true;
        } catch (PedidoNoCancelableException e) {
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
