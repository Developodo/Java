package estructurasdinamicas;

import java.io.Serializable;


public interface IEstructura<T> extends Serializable,Iterable<T> {
    
    /**
     * Insertar un nodo al final de la lista
     * @param v el valor del nodo a insertar
     * @return el nuevo tamaño de la lista
     */
    int push(T v);
    /**
     * Insertar un nodo al inicio de la lista
     * @param v el valor del nodo a insertar
     * @return el nuevo tamaño de la lista
     */
    int unshift(T v);
    /**
     * Devuelve el valor del último nodo de la lista y lo elimina (extrae)
     * @return el valor del último nodo o null si la lista está vacía
     */
    T pop();
    /**
     * Devuelve el valor del primer nodo de la lista y lo elimina (extrae)
     * @return el valor del primer nodo o null si la lista está vacía
     */
    T shift();
    /**
     * Busca el valor pasado como parámetro y devuelve la posición del primer nodo que lo contenga
     * @param v valor a buscar
     * @return posición del nodo que lo contiene o -1 si no existe o la lista está vacía
     */
    int contains(T v);
    /**
     * Devuelve el valor del nodo que se encuentre en la posición indicada
     * @param pos posición del nodo
     * @return su valor o null si no es una posición correcto o la lista está vacía
     */
    T get(int pos);
    /**
     * Igual que get pero obtiene el nodo completo
     * @param pos posición del nodo a devolver
     * @return el nodo indicado por la posición o null en caso de error
     */
    Nodo<T> getElement(int pos);
    /**
     * Insertar un nodo con el valor pasado en la posición indicada
     * @param value el valor del nuevo nodo a insertar
     * @param pos la posición donde se desea insertar
     * @return el nuevo tamaño de lista
     */
    int put(T value, int pos);
    
    /**
     * Elimina el nodo que se encuentre en la posición indicada
     * @param pos posición del nodo a eliminar
     * @return el tamaño de la lista
     */
    int remove(int pos);
    /**
     * Eliminar el nodo que contenga el valor indicado
     * @param v valor del nodo a eliminar
     * @return el tamaño de la lista
     */
    int removeElement(T v);
    /**
     * Igual que remove, pero eliminando todas las coincidencias
     * @param v valor a buscar para eliminar
     * @return el tamaño de la lista
     */
    int removeAllElement(T v);
    /**
     * Invertir el orden de todos los nodos
     */
    void reverse();
    /**
     * Ordenar la lista por el método de la burbuja empleando compareTo de los nodos
     */
    void sort();
    
}
