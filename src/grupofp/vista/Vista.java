package grupofp.vista;

import grupofp.controlador.Controlador;
import grupofp.modelo.Articulo;
import grupofp.modelo.Pedido;
import grupofp.modelo.PedidoNoCancelableException;

import java.util.List;
import java.util.Scanner;

public class Vista {

    private Controlador controlador;
    private Scanner sc;

    public Vista() {
        controlador = new Controlador();
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    anadirArticulo();
                    break;
                case 2:
                    mostrarArticulos();
                    break;
                case 3:
                    anadirCliente();
                    break;
                case 4:
                    crearPedido();
                    break;
                case 5:
                    mostrarPedidos();
                    break;
                case 6:
                    eliminarPedido();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n----- MENÚ -----");
        System.out.println("1. Añadir articulo");
        System.out.println("2. Mostrar articulos");
        System.out.println("3. Añadir cliente");
        System.out.println("4. Crear pedido");
        System.out.println("5. Mostrar pedidos");
        System.out.println("6. Eliminar pedido");
        System.out.println("0. Salir");
    }

    private void anadirArticulo() {
        System.out.print("Código: ");
        String codigo = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Precio de venta: ");
        float precioVenta = Float.parseFloat(sc.nextLine());

        System.out.print("Gastos de envío: ");
        float gastosEnvio = Float.parseFloat(sc.nextLine());

        System.out.print("Tiempo de preparación: ");
        int preparacion = Integer.parseInt(sc.nextLine());

        controlador.anadirArticulo(codigo, descripcion, precioVenta, gastosEnvio, preparacion);
        System.out.println("Artículo añadido correctamente.");
    }

    private void mostrarArticulos() {
        List<Articulo> articulos = controlador.getArticulos();

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos.");
            return;
        }

        for (Articulo a : articulos) {
            System.out.println(a);
        }
    }

    private void anadirCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();

        System.out.print("NIF: ");
        String nif = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Tipo de cliente (1. Estándar / 2. Premium): ");
        int tipo = Integer.parseInt(sc.nextLine());

        controlador.anadirCliente(nombre, domicilio, nif, email, tipo);
        System.out.println("Cliente añadido correctamente.");
    }

    private void crearPedido() {
        System.out.print("Email del cliente: ");
        String email = sc.nextLine();

        System.out.print("Código del artículo: ");
        String codigo = sc.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        boolean creado = controlador.crearPedido(email, codigo, cantidad);

        if (creado) {
            System.out.println("Pedido creado correctamente.");
        } else {
            System.out.println("No se pudo crear el pedido.");
        }
    }

    private void mostrarPedidos() {
        List<Pedido> pedidos = controlador.getPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos.");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }
    private void eliminarPedido() {
        System.out.print("Número del pedido a eliminar: ");
        int numero = Integer.parseInt(sc.nextLine());

        try {
            boolean eliminado = controlador.eliminarPedido(numero);

            if (eliminado) {
                System.out.println("Pedido eliminado correctamente.");
            } else {
                System.out.println("Pedido no encontrado.");
            }

        } catch (PedidoNoCancelableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
