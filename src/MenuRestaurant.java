import java.util.ArrayList;
import java.util.Scanner;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadDemedida;

    public Ingrediente(String nombre, double cantidad, String unidadDemedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadDemedida = unidadDemedida;
    }

    public String mostrarInfo() {
        return nombre + ": " + cantidad + " " + unidadDemedida;
    }
}

class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public boolean isEsBebida() {
        return esBebida;
    }

    public String mostrarInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Plato: " + nombreCompleto + ", Precio: " + precio + ", Es Bebida: " + esBebida + "\nIngredientes:\n");
        for (Ingrediente ing : ingredientes) {
            info.append(" - " + ing.mostrarInfo() + "\n");
        }
        return info.toString();
    }
}

public class MenuRestaurant {
    private ArrayList<Plato> platosMenu;
    private Scanner scanner;

    public MenuRestaurant() {
        platosMenu = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregarPlato() {
        System.out.print("Ingrese el nombre del plato: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("¿Es bebida? true(verdadero)/false(falso): ");
        boolean esBebida = scanner.nextBoolean();
        scanner.nextLine();

        Plato plato = new Plato(nombre, precio, esBebida);

        if (!esBebida) {
            while (true) {
                System.out.print("Ingrese el nombre del ingrediente (o 'fin' para terminar): ");
                String nombreIngrediente = scanner.nextLine();
                if (nombreIngrediente.equalsIgnoreCase("fin") && plato.isEsBebida() == false) {
                    break;
                }
                System.out.print("Ingrese la cantidad: ");
                double cantidad = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Ingrese la unidad de medida: ");
                String unidadMedida = scanner.nextLine();
                plato.agregarIngrediente(new Ingrediente(nombreIngrediente, cantidad, unidadMedida));
            }
        }

        platosMenu.add(plato);
    }

    public void mostrarMenu() {
        for (Plato plato : platosMenu) {
            System.out.println(plato.mostrarInfo());
        }
    }

    public static void main(String[] args) {
        MenuRestaurant menu = new MenuRestaurant();
        System.out.print("¿Cuántos platos desea ingresar? ");
        int n = menu.scanner.nextInt();
        menu.scanner.nextLine();

        for (int i = 0; i < n; i++) {
            menu.agregarPlato();
        }

        menu.mostrarMenu();
        menu.scanner.close();
    }
}
