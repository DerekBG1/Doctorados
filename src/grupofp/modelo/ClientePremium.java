package grupofp.modelo;

public class ClientePremium extends Cliente {
    private double cuotaAnual = (double)30.0F;
    private double descuentoEnvio = 0.2;

    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    public double getCuotaAnual() {
        return this.cuotaAnual;
    }

    public double getDescuentoEnvio() {
        return this.descuentoEnvio;
    }

    public String toString() {
        return "ClientePremium{nombre='" + this.getNombre() + '\'' + ", email='" + this.getEmail() + '\'' + ", cuotaAnual=" + this.cuotaAnual + ", descuentoEnvio=" + this.descuentoEnvio + '}';
    }
}
