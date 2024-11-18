import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subTotal;

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getDescuentoItem() {
        return descuentoItem;
    }

    public void setDescuentoItem(double descuentoItem) {
        this.descuentoItem = descuentoItem;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void calcularSubtotal() {
        this.descuentoItem = this.precioUnitario * 0.1; // 10% de descuento
        this.subTotal = (this.precioUnitario * this.cantidad) - this.descuentoItem;
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private double totalCalculadoFactura;
    private String cliente;
    private ArrayList<DetalleFactura> detallesFactura;

    public Factura() {
        detallesFactura = new ArrayList<>();
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public double getTotalCalculadoFactura() {
        return totalCalculadoFactura;
    }

    public void setTotalCalculadoFactura(double totalCalculadoFactura) {
        this.totalCalculadoFactura = totalCalculadoFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public ArrayList<DetalleFactura> getDetallesFactura() {
        return detallesFactura;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detallesFactura.add(detalle);
        calcularTotal();
    }

    public void calcularTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubTotal();
        }
    }
}

public class Facturacion {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        System.out.print("Ingrese la fecha de la factura: ");
        factura.setFechaFactura(scanner.nextLine());

        System.out.print("Ingrese el número de factura: ");
        factura.setNumeroFactura(scanner.nextLong());

        scanner.nextLine();
        System.out.print("Ingrese el nombre del cliente: ");
        factura.setCliente(scanner.nextLine());

        while (true) {
            System.out.print("Ingrese el código del artículo a facturar: ");
            String codigo = scanner.nextLine();
            DetalleFactura detalle = new DetalleFactura();

            boolean encontrado = false;
            for (String[] articulo : articulos) {
                if (articulo[0].equals(codigo)) {
                    System.out.print("Ingrese la cantidad: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();

                    detalle.setCodigoArticulo(articulo[0]);
                    detalle.setNombreArticulo(articulo[1]);
                    detalle.setPrecioUnitario(Double.parseDouble(articulo[2]));
                    detalle.setCantidad(cantidad);
                    detalle.calcularSubtotal();
                    factura.agregarDetalle(detalle);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("El código ingresado no existe, intente nuevamente.");
            }

            System.out.print("¿Desea agregar otro artículo? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
        }

        System.out.println("Fecha de la factura: " + factura.getFechaFactura());
        System.out.println("Número de factura: " + factura.getNumeroFactura());
        System.out.println("Cliente: " + factura.getCliente());
        System.out.println("\nCódigo Artículo | Nombre Artículo | Cantidad | Precio Unitario | Descuento | Subtotal");
        for (DetalleFactura detalle : factura.getDetallesFactura()) {
            System.out.printf("%s | %s | %d | %.2f | %.2f | %.2f\n",
                    detalle.getCodigoArticulo(),
                    detalle.getNombreArticulo(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getDescuentoItem(),
                    detalle.getSubTotal());
        }
        System.out.printf("\nTotal de la factura: %.2f\n", factura.getTotalCalculadoFactura());
        scanner.close();
    }
}
