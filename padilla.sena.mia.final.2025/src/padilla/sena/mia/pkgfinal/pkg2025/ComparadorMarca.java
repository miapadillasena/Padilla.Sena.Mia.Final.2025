
package padilla.sena.mia.pkgfinal.pkg2025;
import java.util.Comparator; 

public class ComparadorMarca implements Comparator<Producto>{
    
    @Override
    public int compare(Producto p1, Producto p2) {
        return p1.getMarca().compareToIgnoreCase(p2.getMarca());
    }    
}
