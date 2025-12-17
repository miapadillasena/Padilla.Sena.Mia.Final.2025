
package padilla.sena.mia.pkgfinal.pkg2025;

public class Accesorios extends Producto {
    
    private String tipoAccesorio; 
    private boolean original; 

    // Constructor. 

    public Accesorios(String tipoAccesorio, boolean original, String nombre, double precio, String marca, Condicion condicion) {
        super(nombre, precio, marca, condicion);
        this.tipoAccesorio = tipoAccesorio;
        this.original = original;
    }

    public Accesorios(String tipoAccesorio, boolean original, String nombre, double precio, String marca) {
        super(nombre, precio, marca);
        this.tipoAccesorio = tipoAccesorio;
        this.original = original;
    }

    public Accesorios(String tipoAccesorio, boolean original, String nombre, String marca) {
        super(nombre, marca);
        this.tipoAccesorio = tipoAccesorio;
        this.original = false;
    }
    
    @Override
    public String detallesEspecificos() {
         return "Accesorio: " + tipoAccesorio + " - " + (original ? "Original" : "Genérico");
    }
    
}
