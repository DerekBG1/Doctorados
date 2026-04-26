package grupofp.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Datos {

    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private List<Pedido> pedidos;
    private int contadorPedidos;

    public Datos() {
        clientes = new ArrayList<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();
        contadorPedidos = 1;

        cargarDatosIniciales();
    }

    public void cargarDatosIniciales() {
        articulos.add(new Articulo("A1", "Teclado", 50f, 10f, 30));
        articulos.add(new Articulo("A2", "Ratón", 25f, 5f, 15));
        articulos.add(new Articulo("A3", "Monitor", 200f, 15f, 45));

        clientes.add(new ClienteEstandar("Juan", "Calle Sol 12", "11111111A", "juan@gmail.com"));
        clientes.add(new ClientePremium("Ana", "Avenida Luna 8", "22222222B", "ana@gmail.com"));
    }

    public void anadirArticulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, int preparacion) {
        articulos.add(new Articulo(codigo, descripcion, precioVenta, gastosEnvio, preparacion));
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void anadirCliente(String nombre, String domicilio, String nif, String email, int tipo) {
        if (tipo == 2) {
            clientes.add(new ClientePremium(nombre, domicilio, nif, email));
        } else {
            clientes.add(new ClienteEstandar(nombre, domicilio, nif, email));
        }
    }

    public Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        for (Articulo a : articulos) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        for (Pedido p : pedidos) {
            if (p.getNumeroPedido() == numeroPedido) {
                return p;
            }
        }
        return null;
    }

    public boolean crearPedido(String email, String codigoArticulo, int cantidad) {
        Cliente cliente = buscarClientePorEmail(email);
        Articulo articulo = buscarArticuloPorCodigo(codigoArticulo);

        if (cliente == null || articulo == null || cantidad <= 0) {
            return false;
        }

        Pedido pedido = new Pedido(contadorPedidos, cliente, articulo, cantidad, LocalDateTime.now());
        pedidos.add(pedido);
        contadorPedidos++;
        return true;
    }

    public boolean eliminarPedido(int numeroPedido) throws PedidoNoCancelableException {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);

        if (pedido == null) {
            return false;
        }

        if (pedido.getCantidad() > 3) {
            throw new PedidoNoCancelableException("No se puede eliminar");
        }

        pedidos.remove(pedido);
        return true;
    }


}
