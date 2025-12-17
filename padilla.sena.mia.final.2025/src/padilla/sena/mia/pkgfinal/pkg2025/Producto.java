
package padilla.sena.mia.pkgfinal.pkg2025;
import java.io.Serializable; 


public abstract class Producto implements Comparable<Producto>, Serializable {
   
    protected String nombre; 
    protected double precio; 
    protected String marca;
    protected Condicion condicion; 

//  Contructor completo
    public Producto(String nombre, double precio, String marca, Condicion condicion) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.condicion = condicion;
    }

//  Contructor sin condicion 
    public Producto(String nombre, double precio, String marca) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.condicion = Condicion.NUEVO; 
    }
         
//  Contructor sobre eleccion del producto 
    public Producto(String nombre, String marca) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = 0.0; 
        this.condicion = Condicion.NUEVO; 
    } 
    
//  Mostrar informacion del producto 
    public void mostrarInformacion (){ 
        System.out.println("Producto: " + marca + " " + nombre + " " + condicion);         
    }
    
//  Metodo para aplicar descuentos.
    public void aplicarDescuentos (double porcentaje){ 
        this.precio -= this.precio * (porcentaje / 100); 
        System.out.println("Precio con descuentos: " + this.precio);
    }
    
// Metodo que describe todos los detalles     
    public abstract String detallesEspecificos(); 

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    } 

    public String getMarca() {
        return marca;  
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public Condicion getCondicion() {
    return condicion;
    } 

    @Override
    public int compareTo(Producto otroProducto) {
        return this.nombre.compareToIgnoreCase(otroProducto.getNombre());
     }
    
    public String toCSV() {
        return nombre + "," + marca + "," + precio + "," + condicion;
        } 

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Producto producto = (Producto) obj; 
    return nombre.equals(producto.nombre) && marca.equals(producto.marca);
    }
}
