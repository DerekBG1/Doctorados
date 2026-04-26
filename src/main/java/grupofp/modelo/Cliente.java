package grupofp.modelo;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa un cliente de la tienda.
 * Se mapea a la tabla "cliente" en la base de datos MySQL.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    // Constructor vacío requerido por JPA
    public Cliente() {}

    public Cliente(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "Cliente{email='" + email + "', nombre='" + nombre + "'}";
    }
}
