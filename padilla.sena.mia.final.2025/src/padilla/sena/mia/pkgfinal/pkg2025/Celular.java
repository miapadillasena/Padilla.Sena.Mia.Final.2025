
package padilla.sena.mia.pkgfinal.pkg2025;


public class Celular extends Producto implements Conectable {

    private int bateriaMAh; 
    private int pixelesCamara; 

// Constructores 
    public Celular(int bateriaMAh, int pixelesCamara, String nombre, double precio, String marca, Condicion condicion) {
        super(nombre, precio, marca, condicion);
        this.bateriaMAh = bateriaMAh;
        this.pixelesCamara = pixelesCamara;
    }

    public Celular(int bateriaMAh, int pixelesCamara, String nombre, double precio, String marca) {
        super(nombre, precio, marca, Condicion.NUEVO);
        this.bateriaMAh = bateriaMAh;
        this.pixelesCamara = 12; //12MP por defecto 
    }

    public Celular(String nombre, String marca) {
        super(nombre, 0.0, marca, Condicion.NUEVO);
        this.bateriaMAh = 4000; // Por defecto
        this.pixelesCamara = 12;
    } 
    
    @Override // Conectable
    public void conectarAInternet(String red) {
        System.out.println("Smartphone:  " + nombre + " conectado a 4G/5G y red: " + red);
    }

    @Override // Detalles
    public String detallesEspecificos() {
        return "Smartphone con Batería: " + bateriaMAh + "mAh y Cámara: " + pixelesCamara + "MP";
    }    
}
