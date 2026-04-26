package grupofp.vista;

import grupofp.controlador.Controlador;
import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Pedido;
import grupofp.modelo.PedidoNoCancelableException;

import java.util.List;
import java.util.Scanner;

/**
 * Vista MVC - Menú interactivo por consola.
 * Recoge la entrada del usuario y llama al Controlador.
 * No accede directamente a los DAOs ni a JPA.
 */
public class Vista {

    private final Controlador controlador;
    private final Scanner sc = new Scanner(System.in);

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerInt();
            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuArticulos();
                case 3 -> menuPedidos();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // ── MENÚ PRINCIPAL ───────────────────────────────────────────────────────

    private void mostrarMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     TIENDA - Grupo Doctorados ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Gestión de Clientes       ║");
        System.out.println("║  2. Gestión de Artículos      ║");
        System.out.println("║  3. Gestión de Pedidos        ║");
        System.out.println("║  0. Salir                     ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Opción: ");
    }

    // ── CLIENTES ─────────────────────────────────────────────────────────────

    private void menuClientes() {
        int op;
        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Buscar cliente por email");
            System.out.println("3. Listar todos los clientes");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerInt();
            switch (op) {
                case 1 -> añadirCliente();
                case 2 -> buscarCliente();
                case 3 -> listarClientes();
                case 4 -> eliminarCliente();
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    private void añadirCliente() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        controlador.agregarCliente(email, nombre);
        System.out.println("✔ Cliente añadido correctamente.");
    }

    private void buscarCliente() {
        System.out.print("Email del cliente: ");
        String email = sc.nextLine();
        Cliente c = controlador.buscarClientePorEmail(email);
        if (c != null) System.out.println(c);
        else System.out.println("Cliente no encontrado.");
    }

    private void listarClientes() {
        List<Cliente> clientes = controlador.getClientes();
        if (clientes.isEmpty()) System.out.println("No hay clientes.");
        else clientes.forEach(System.out::println);
    }

    private void eliminarCliente() {
        System.out.print("Email del cliente a eliminar: ");
        String email = sc.nextLine();
        boolean ok = controlador.eliminarCliente(email);
        System.out.println(ok ? "✔ Cliente eliminado." : "Cliente no encontrado.");
    }

    // ── ARTÍCULOS ────────────────────────────────────────────────────────────

    private void menuArticulos() {
        int op;
        do {
            System.out.println("\n--- ARTÍCULOS ---");
            System.out.println("1. Añadir artículo");
            System.out.println("2. Buscar artículo por código");
            System.out.println("3. Listar todos los artículos");
            System.out.println("4. Eliminar artículo");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerInt();
            switch (op) {
                case 1 -> añadirArticulo();
                case 2 -> buscarArticulo();
                case 3 -> listarArticulos();
                case 4 -> eliminarArticulo();
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    private void añadirArticulo() {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        controlador.agregarArticulo(codigo, desc, precio);
        System.out.println("✔ Artículo añadido correctamente.");
    }

    private void buscarArticulo() {
        System.out.print("Código del artículo: ");
        String codigo = sc.nextLine();
        Articulo a = controlador.buscarArticuloPorCodigo(codigo);
        if (a != null) System.out.println(a);
        else System.out.println("Artículo no encontrado.");
    }

    private void listarArticulos() {
        List<Articulo> articulos = controlador.getArticulos();
        if (articulos.isEmpty()) System.out.println("No hay artículos.");
        else articulos.forEach(System.out::println);
    }

    private void eliminarArticulo() {
        System.out.print("Código del artículo a eliminar: ");
        String codigo = sc.nextLine();
        boolean ok = controlador.eliminarArticulo(codigo);
        System.out.println(ok ? "✔ Artículo eliminado." : "Artículo no encontrado.");
    }

    // ── PEDIDOS ──────────────────────────────────────────────────────────────

    private void menuPedidos() {
        int op;
        do {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("1. Crear pedido");
            System.out.println("2. Listar todos los pedidos");
            System.out.println("3. Pedidos de un cliente");
            System.out.println("4. Eliminar pedido");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerInt();
            switch (op) {
                case 1 -> crearPedido();
                case 2 -> listarPedidos();
                case 3 -> pedidosPorCliente();
                case 4 -> eliminarPedido();
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    private void crearPedido() {
        System.out.print("Email del cliente: ");
        String email = sc.nextLine();
        System.out.print("Código del artículo: ");
        String codigo = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = leerInt();
        boolean ok = controlador.crearPedido(email, codigo, cantidad);
        System.out.println(ok ? "✔ Pedido creado correctamente." :
                               "Error: cliente o artículo no encontrado.");
    }

    private void listarPedidos() {
        List<Pedido> pedidos = controlador.getPedidos();
        if (pedidos.isEmpty()) System.out.println("No hay pedidos.");
        else pedidos.forEach(System.out::println);
    }

    private void pedidosPorCliente() {
        System.out.print("Email del cliente: ");
        String email = sc.nextLine();
        List<Pedido> pedidos = controlador.getPedidosPorCliente(email);
        if (pedidos.isEmpty()) System.out.println("No hay pedidos para ese cliente.");
        else pedidos.forEach(System.out::println);
    }

    private void eliminarPedido() {
        System.out.print("Número del pedido a eliminar: ");
        int numero = leerInt();
        try {
            boolean ok = controlador.eliminarPedido(numero);
            System.out.println(ok ? "✔ Pedido eliminado correctamente." :
                                   "Pedido no encontrado.");
        } catch (PedidoNoCancelableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ── UTILIDAD ─────────────────────────────────────────────────────────────

    private int leerInt() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
