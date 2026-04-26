package grupofp.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un pedido.
 * Relaciona Cliente y Articulo mediante claves foráneas (@ManyToOne).
 * Se mapea a la tabla "pedido" en la base de datos MySQL.
 */
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pedido")
    private int numeroPedido;

    @ManyToOne
    @JoinColumn(name = "email_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_articulo", nullable = false)
    private Articulo articulo;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    // Constructor vacío requerido por JPA
    public Pedido() {}

    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public int getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(int numeroPedido) { this.numeroPedido = numeroPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    @Override
    public String toString() {
        return "Pedido{numeroPedido=" + numeroPedido +
               ", cliente=" + cliente +
               ", articulo=" + articulo +
               ", cantidad=" + cantidad +
               ", fechaHora=" + fechaHora + "}";
    }
}
