
package padilla.sena.mia.pkgfinal.pkg2025;
import java.io.*; 
import java.util.List; 
import java.util.ArrayList; 


public class GestorArchivos {
    
// Guardar binarios
        public static void guardarBinario(String archivo, List<Producto> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
            System.out.println("Datos serializados guardados en: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al guardar binario: " + e.getMessage());
        }
    }
        
        public static List<Producto> cargarBinario(String archivo) {
        List<Producto> lista = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            lista = (List<Producto>) ois.readObject();
            System.out.println("Datos cargados desde binario correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe, se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar binario: " + e.getMessage());
        }
        return lista;
    }
        
// Archivos CSV
        public static void guardarCSV(String archivo, List<Producto> lista) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("Nombre,Marca,Precio,Condicion"); // Encabezado
            for (Producto p : lista) {
                writer.println(p.toCSV()); // Datos
            }
            System.out.println("Datos exportados a CSV: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al escribir CSV: " + e.getMessage());
        }
    }
// TXT 
        public static void exportarReporte(String archivo, List<Producto> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("=== REPORTE DE INVENTARIO ===");
            writer.newLine();
            writer.write("Fecha de generación: " + java.time.LocalDate.now());
            writer.newLine();
            writer.write("Total de productos: " + lista.size());
            writer.newLine();
            writer.write("-------------------------------------------------");
            writer.newLine();
            
            for (Producto p : lista) {
                writer.write(String.format("Producto: %-20s | Marca: %-15s | Precio: $%.2f", 
                                            p.getNombre(), p.getMarca(), p.getPrecio()));
                writer.newLine();
                writer.write("   -> Detalles: " + p.detallesEspecificos());
                writer.newLine();
                writer.write("-------------------------------------------------");
                writer.newLine();
            }
            System.out.println("Reporte exportado a TXT: " + archivo);
        } catch (IOException e) {
            System.err.println("Error al exportar reporte: " + e.getMessage());
        }
    }
}
