package grupofp;

import grupofp.controlador.Controlador;
import grupofp.util.JPAUtil;
import grupofp.vista.Vista;

/**
 * Punto de entrada de la aplicación.
 * Inicializa el Controlador y la Vista, y lanza el menú principal.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando conexión con la base de datos...");
        try {
            Controlador controlador = new Controlador();
            Vista vista = new Vista(controlador);
            vista.iniciar();
        } finally {
            JPAUtil.cerrar();
            System.out.println("Conexión cerrada.");
        }
    }
}
