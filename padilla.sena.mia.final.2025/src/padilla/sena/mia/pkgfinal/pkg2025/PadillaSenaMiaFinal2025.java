package padilla.sena.mia.pkgfinal.pkg2025;
import java.util.ArrayList; 
import java.util.List; 


public class PadillaSenaMiaFinal2025 {

    public static void main(String[] args) {
        
       System.out.println(" Sistema de gestion de productos electronicos ");
       
       Notebook laptop = new Notebook(
               16, 
               "SSD 1TB", 
               "Legion 5", 
               120000.00, 
               "Lenovo", 
               Condicion.NUEVO             
        ); 
        Accesorios mouse = new Accesorios ( 
               "Mouse Gamer", 
               true, 
               "G502 Hero", 
               5000.00, 
               "Logitech", 
               Condicion.USADO
       ); 
        
//      Se guardan en el inventario los nuevos productos         
        List<Producto> inventario;
        inventario = new ArrayList<>();
        inventario.add(laptop);
        inventario.add(mouse);
        
       GestorInventario<Producto> miTienda = new GestorInventario<>(); 
//      Contructores para agregar 
        miTienda.agregar(new Notebook(16, "SSD 1TB", "Legion 5", 120000.00, "Lenovo", Condicion.NUEVO));
        miTienda.agregar(new Accesorios("Mouse Gamer", true, "G502 Hero", 5000.00, "Logitech", Condicion.USADO)); 
        miTienda.agregar(new Celular("A54" , "Samsung")); 
        miTienda.agregar(laptop);
        miTienda.agregar(mouse);
         
        

//      Recorrer inventario básico
        System.out.println("INVENTARIO");
        for (Producto p : inventario) {
            p.mostrarInformacion();
            System.out.println(p.detallesEspecificos());
//      Aplica descuentos           
            p.aplicarDescuentos(10);
//      Verificamos si es apta para redes wifi
        if (p instanceof Conectable) {
               ((Conectable) p).conectarAInternet("Wifi x");
           }
        }
        
//      Muestra el inventario limpio      
        System.out.println("LISTA ORIGINAL");
        mostrarInventario(miTienda);
        
//      Orden
        miTienda.ordenarPorDefecto();
        System.out.println("ORDENADO POR NOMBRE"); //Comparable
        mostrarInventario(miTienda);
       
        miTienda.ordenar(new ComparadorPrecio());
        System.out.println("ORDENADO POR PRECIO"); // Comparator
        mostrarInventario(miTienda);
        
        miTienda.ordenar(new ComparadorMarca());
        System.out.println("ORDENADO POR MARCA"); // Comparator
        mostrarInventario(miTienda);
        
//      Excepciones
        
        try {
           Producto productoFantasma = new Celular("Fantasma", "MarcaX");
           miTienda.eliminar(productoFantasma); // Esto lanzará error
       } catch (ProductoNoEncontradoExcepcion e) {
           System.err.println("Excepción capturada: " + e.getMessage());
       }
        
        try {
           miTienda.actualizar(99, new Celular("Update", "Fail")); // Esto lanzará error
       } catch (IndiceInvalidoExcepcion e) {
           System.err.println("Excepción capturada: " + e.getMessage());
       }
    
//  Persistencia   
        List<Producto> listaParaGuardar = miTienda.listar();
        
        GestorArchivos.guardarBinario("inventario_backup.dat", listaParaGuardar);
        GestorArchivos.guardarCSV("productos.csv", listaParaGuardar);
        GestorArchivos.exportarReporte("reporte_final.txt", listaParaGuardar);

        List<Producto> listaRecuperada = GestorArchivos.cargarBinario("inventario_backup.dat");
        System.out.println("Productos recuperados: " + listaRecuperada.size());
        
// Interface funcional
        System.out.println("Precios antes del aumento:");
        mostrarInventario(miTienda);
        
        miTienda.aplicarAccion( p -> {
           double precioActual = p.getPrecio();
           double nuevoPrecio = precioActual * 1.15;
           p.aplicarDescuentos(-15); // Descuento negativo = Aumento
       });
        
        System.out.println("\nPrecios DESPUÉS del aumento del 15%:");
        mostrarInventario(miTienda);
        
//  Wildcars 
//  Super
    List<Object> destino = new ArrayList<>();
    miTienda.copiarA(destino);
      
 // Extends
    List<Notebook> soloNotebooks = new ArrayList<>();
    soloNotebooks.add(new Notebook(8, "HDD", "IdeaPad", 300000.0, "Lenovo", Condicion.USADO));
    miTienda.cargarDesde(soloNotebooks);
    System.out.println("--- CARGADO SOLO NOTEBOOKS ---");
    mostrarInventario(miTienda);
    
    } 
    public static void mostrarInventario(GestorInventario<Producto> inventario) {
        for (Producto p : inventario) {
           System.out.println(p.getMarca() + " " + p.getNombre() + " - $" + p.getPrecio() + " (" + p.condicion + ")");
            }
     }
    
 }
