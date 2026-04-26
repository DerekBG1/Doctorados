package grupofp.controlador;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Datos;
import grupofp.modelo.Pedido;
import grupofp.modelo.PedidoNoCancelableException;


import java.util.List;

public class Controlador {

    private Datos datos;

    public Controlador() {
        datos = new Datos();
    }

    public void anadirArticulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, int preparacion) {
        datos.anadirArticulo(codigo, descripcion, precioVenta, gastosEnvio, preparacion);
    }

    public void anadirCliente(String nombre, String domicilio, String nif, String email, int tipo) {
        datos.anadirCliente(nombre, domicilio, nif, email, tipo);
    }

    public Cliente buscarClientePorEmail(String email) {
        return datos.buscarClientePorEmail(email);
    }

    public boolean crearPedido(String email, String codigoArticulo, int cantidad) {
        return datos.crearPedido(email, codigoArticulo, cantidad);
    }
    public boolean eliminarPedido(int numeroPedido) throws PedidoNoCancelableException {
        return datos.eliminarPedido(numeroPedido);
    }


    public List<Articulo> getArticulos() {
        return datos.getArticulos();
    }

    public List<Pedido> getPedidos() {
        return datos.getPedidos();
    }
}
