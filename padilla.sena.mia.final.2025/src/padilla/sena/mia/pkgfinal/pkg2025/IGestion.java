
package padilla.sena.mia.pkgfinal.pkg2025;
import java.util.List; 


public interface IGestion<T> {
    void agregar(T elemento);
    void eliminar(T elemento) throws ProductoNoEncontradoExcepcion;;
    void actualizar(int index, T elemento) throws IndiceInvalidoExcepcion; // Actualizamos por posición
    List<T> listar();
} 
