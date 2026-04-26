package grupofp.modelo;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa un artículo de la tienda.
 * Se mapea a la tabla "articulo" en la base de datos MySQL.
 */
@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @Column(name = "codigo", length = 20, nullable = false)
    private String codigo;

    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    // Constructor vacío requerido por JPA
    public Articulo() {}

    public Articulo(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Articulo{codigo='" + codigo + "', descripcion='" + descripcion +
               "', precio=" + precio + "}";
    }
}
