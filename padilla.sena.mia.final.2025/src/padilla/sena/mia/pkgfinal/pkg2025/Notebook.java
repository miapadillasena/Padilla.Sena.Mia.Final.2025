
package padilla.sena.mia.pkgfinal.pkg2025;


public class Notebook extends Producto implements Conectable{
    
    private int memoria;
    private String tipoDisco; 

    public Notebook(int memoria, String tipoDisco, String nombre, double precio, String marca, Condicion condicion) {
        super(nombre, precio, marca, condicion);
        this.memoria = memoria;
        this.tipoDisco = tipoDisco;
    }

    public Notebook(int memoria, String tipoDisco, String nombre, double precio, String marca) {
        super(nombre, precio, marca);
        this.memoria = memoria;
        this.tipoDisco = tipoDisco;
    }

    public Notebook(int memoria, String tipoDisco, String nombre, String marca) {
        super(nombre, marca);
        this.memoria = memoria;
        this.tipoDisco = tipoDisco;
    }

    
    
    @Override
    public void conectarAInternet(String red) {
        System.out.println("Conectando Notebook " + nombre + " a la red: " + red);
    } 
    
    @Override
    public String detallesEspecificos() {
        return "Especificaciones: " + memoria + "GB RAM, Disco " + tipoDisco;
    }
}
