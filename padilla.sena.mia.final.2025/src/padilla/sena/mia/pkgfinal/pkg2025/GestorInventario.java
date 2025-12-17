
package padilla.sena.mia.pkgfinal.pkg2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator; 
import java.util.function.Consumer; 

public class GestorInventario<T extends Comparable<T>> implements IGestion<T>, Iterable<T> {
    
    private List<T> elementos; 
    
    public GestorInventario (){
        this.elementos = new ArrayList<>();     
    }
    
    // CRUD
    @Override
    public void agregar(T elemento) {
        elementos.add(elemento);
        System.out.println("Elemento agregado exitosamente.");
    } 
    
    @Override
    public void eliminar(T elemento) throws ProductoNoEncontradoExcepcion{
        if (elementos.contains(elemento)) {
            elementos.remove(elemento);
            System.out.println("Elemento eliminado exitosamente.");
        } else {
            throw new ProductoNoEncontradoExcepcion("Error: No se puede eliminar. El producto no se encuentra en el inventario.");
        }
    }
    
    @Override
    public void actualizar(int index, T elemento) throws IndiceInvalidoExcepcion {
        if (index >= 0 && index < elementos.size()) {
            elementos.set(index, elemento);
            System.out.println("Elemento actualizado en posición " + index);
        } else {
            throw new IndiceInvalidoExcepcion("Error: El índice " + index + " no es válido para la lista actual.");
        }
    }
//  Para leer
    @Override
    public List<T> listar() {
        return new ArrayList<>(elementos); 
    } 

//  Iterator 
    @Override
    public Iterator<T> iterator() {
        return new MiIterador();
    } 
    
//  Clase interna privada para el iterador
    private class MiIterador implements Iterator<T> {
        private int indiceActual = 0;

        @Override
        public boolean hasNext() {
            return indiceActual < elementos.size();
        }
        @Override
        public T next() {
            return elementos.get(indiceActual++);
        }     
    }  
    public void ordenarPorDefecto() {
        Collections.sort(elementos);
        System.out.println("Lista ordenada por defecto (orden natural).");
    } 
    
    public void ordenar(Comparator<T> criterio) {
        elementos.sort(criterio);
        System.out.println("Lista ordenada por criterio personalizado.");
    }    

//  Interface funcional (Consumer)     
    public void aplicarAccion(Consumer<T> accion) {
        for (T elemento : elementos) {
            accion.accept(elemento); // Ejecuta la acción sobre el elemento actual
        }
        System.out.println("Acción masiva aplicada a todos los elementos.");
        } 
 
//  Wildcard
    public void copiarA(List<? super T> destino) { // Copiar elementos a una lista externa
    destino.addAll(elementos);
    }
    
    public void cargarDesde(List<? extends T> origen) { // Cargar elementos desde una lista de derivados
    elementos.clear();
    elementos.addAll(origen);
       }   
}

    
