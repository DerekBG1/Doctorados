package grupofp.modelo;

/**
 * Excepción lanzada cuando se intenta eliminar un pedido
 * con cantidad superior a 3 unidades.
 */
public class PedidoNoCancelableException extends Exception {

    public PedidoNoCancelableException(String msg) {
        super(msg);
    }
}
