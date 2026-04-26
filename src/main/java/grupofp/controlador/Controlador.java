package grupofp.controlador;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Pedido;
import grupofp.modelo.PedidoNoCancelableException;
import grupofp.persistencia.ArticuloDAO;
import grupofp.persistencia.ClienteDAO;
import grupofp.persistencia.PedidoDAO;

import java.util.List;

/**
 * Controlador MVC.
 * Actúa como intermediario entre la Vista y los DAOs.
 * No contiene lógica JPA directamente; delega en los DAOs.
 */
public class Controlador {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ArticuloDAO articuloDAO = new ArticuloDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    // ── CLIENTES ─────────────────────────────────────────────────────────────

    public void agregarCliente(String email, String nombre) {
        Cliente cliente = new Cliente(email, nombre);
        clienteDAO.guardar(cliente);
    }

    public Cliente buscarClientePorEmail(String email) {
        return clienteDAO.buscarPorEmail(email);
    }

    public List<Cliente> getClientes() {
        return clienteDAO.listarTodos();
    }

    public boolean eliminarCliente(String email) {
        return clienteDAO.eliminar(email);
    }

    // ── ARTÍCULOS ────────────────────────────────────────────────────────────

    public void agregarArticulo(String codigo, String descripcion, double precio) {
        Articulo articulo = new Articulo(codigo, descripcion, precio);
        articuloDAO.guardar(articulo);
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        return articuloDAO.buscarPorCodigo(codigo);
    }

    public List<Articulo> getArticulos() {
        return articuloDAO.listarTodos();
    }

    public boolean eliminarArticulo(String codigo) {
        return articuloDAO.eliminar(codigo);
    }

    // ── PEDIDOS ──────────────────────────────────────────────────────────────

    public boolean crearPedido(String email, String codigoArticulo, int cantidad) {
        return pedidoDAO.guardar(email, codigoArticulo, cantidad);
    }

    public List<Pedido> getPedidos() {
        return pedidoDAO.listarTodos();
    }

    public List<Pedido> getPedidosPorCliente(String email) {
        return pedidoDAO.listarPorCliente(email);
    }

    public boolean eliminarPedido(int numeroPedido) throws PedidoNoCancelableException {
        return pedidoDAO.eliminar(numeroPedido);
    }
}
