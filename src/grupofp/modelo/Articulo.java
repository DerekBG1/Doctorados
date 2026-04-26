package grupofp.modelo;

public class Articulo {
    private String codigo;
    private String descripcion;
    private float precioVenta;
    private float gastosEnvio;
    private int preparacion;

    public Articulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, int preparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.preparacion = preparacion;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getGastosEnvio() {
        return this.gastosEnvio;
    }

    public void setGastosEnvio(float gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public int getPreparacion() {
        return this.preparacion;
    }

    public void setPreparacion(int preparacion) {
        this.preparacion = preparacion;
    }

    public String toString() {
        return "Articulo{codigo='" + this.codigo + '\'' + ", descripcion='" + this.descripcion + '\'' + ", precioVenta=" + this.precioVenta + ", gastosEnvio=" + this.gastosEnvio + ", preparacion=" + this.preparacion + '}';
    }
}
