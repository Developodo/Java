package estructurasdinamicas;

import java.util.Iterator;


public class Estructura<T> implements IEstructura<T> {
    private Nodo<T> raiz;
    private int size;
    
    //Iterator
    int posicion;

    public Estructura() {
        raiz=null;
        size=0;
        posicion=0;
    }
    
    public int length(){
        return size;
    }

    @Override
    public int push(T v) {
        if(v!=null){
            Nodo<T> nuevo=new Nodo<>(v);
            if(raiz==null){
                raiz=nuevo;
            }else{
                Nodo<T> aux=raiz;
                while(aux.sig!=null){
                    aux=aux.sig;
                }
                //aux apunta o es el último nodo
                aux.sig=nuevo;
                nuevo.ant=aux;
            }
            size++;
            
        }
        return size;
    }

    @Override
    public int unshift(T v) {
        if(v!=null){
            Nodo<T> nuevo=new Nodo<>(v);
            if(raiz!=null){
                nuevo.sig=raiz;
                raiz.ant=nuevo;
            }
            raiz=nuevo;
            size++;
        }
        return size;
    }

    @Override
    public T pop() {
      T result=null;
      if(raiz!=null){
          Nodo<T> aux=raiz;
          while(aux.sig!=null){
              aux=aux.sig;
          }
          result=aux.getValue();
          if(aux.ant!=null){
             aux.ant.sig=null; 
          }else{
              raiz=null;
          }
          aux=null;
          size--;
          //aux es el último elemento  
      }
      return result;
    }

    @Override
    public T shift() {
        T result=null;
        if(raiz!=null){
            result=raiz.getValue();
            raiz=raiz.sig;
            if(raiz!=null){
                raiz.ant=null;
            }
        }
        return result;
    }

    @Override
    public int contains(T v) {
       int pos=-1;
       boolean found=false;
       
       Nodo<T> buscado=new Nodo<>(v);
       Nodo<T> aux=raiz;
       while(aux!=null && !found){
           pos++;
           if(aux.equals(buscado)){
               found=true;
           }
           aux=aux.sig;
       }
       if(!found){
           pos=-1;
       }
       
       return pos;
    }

    @Override
    public T get(int pos) {
       T result=null;
       if(pos>=0 && pos <size){
           Nodo<T> aux=raiz;
           int index=0;
           while(aux!=null && index!=pos){
               index++;
               aux=aux.sig;
           }
           if(index==pos && aux!=null){
               result=aux.getValue();
           }
       }
       return result;
    }
    
    @Override
    public Nodo<T> getElement(int pos) {
       Nodo<T> result=null;
       if(pos>=0 && pos <size){
           Nodo<T> aux=raiz;
           int index=0;
           while(aux!=null && index!=pos){
               index++;
               aux=aux.sig;
           }
           if(index==pos && aux!=null){
               result=aux;
           }
       }
       return result;
    }

    @Override
    public int put(T value, int pos) {
        if(value!=null){
             Nodo<T> nuevo=new Nodo<>(value);
             if(raiz==null){
                 size=push(value);
             }else{
                 if(pos>=0 && pos<size){
                     Nodo<T> enPosicion=getElement(pos);
                     if(enPosicion.ant!=null){
                         nuevo.ant=enPosicion.ant;
                         enPosicion.ant.sig=nuevo;
                     }
                     nuevo.sig=enPosicion;
                     enPosicion.ant=nuevo;
                     if(nuevo.ant==null){
                         raiz=nuevo;
                     }
                     size++;  
                 }
             }
            
        }
        return size;
    }

    @Override
    public int remove(int pos) {
        if(pos>=0 && pos<size){
            Nodo<T> current=getElement(pos);
            if(current!=null){
                if(current.ant!=null){
                    current.ant.sig=current.sig;
                }
                if(current.sig!=null){
                    current.sig.ant=current.ant;
                }
                current=null;
                size--;
            }
        }
        return size;
    }

    @Override
    public int removeElement(T v) {
       if(v!=null){
           int pos=contains(v);
           if(pos!=-1){
               remove(pos);
           }
       }
       return size;
    }
    
    @Override
    public int removeAllElement(T v){
        int originalSize=0;
        while(originalSize!=removeElement(v)){
            originalSize=size;
        }
        return size;
    }

    @Override
    public void reverse() {
       if(raiz!=null && size>1){
           Nodo<T> last;
           Nodo<T> aux;
           last=aux=getElement(size-1);
           do{
               Nodo<T> tmp=aux.ant;
               aux.ant=aux.sig;
               aux.sig=tmp;
               aux=tmp;
           }while(aux !=null);
           raiz=last;
       }
    }

    @Override
    public void sort() {
        if (raiz != null && size > 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < (size - i); j++) {
                    Nodo<T> a = getElement(j - 1);
                    Nodo<T> b = getElement(j);
                    if (a != null && a.compareTo(b) > 0) {

                        //Bridge the prev and next elements
                        if(a.ant!=null){
                            a.ant.sig=a.sig;
                        }
                        if(b.sig!=null){
                            b.sig.ant=b.ant;
                        }
                        
                        //swap elements
                        a.sig = b.sig;
                        b.sig = a;
                        b.ant = a.ant;
                        a.ant = b;
                       
                        //check if a or b is a new root
                        if (a.ant == null) {
                            raiz = a;
                        }
                        if (b.ant == null) {
                            raiz = b;
                        }
                        
                    }
                }
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        posicion=0;
        Nodo<T> current=this.raiz;
        Iterator<T> i = new Iterator<T>() {
            
            @Override
            public boolean hasNext() {
                if (posicion < size && current!=null) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public T next() {
                return get(posicion++);
            }

        };
        return i;
    }
    
    public String toString(){
        String result="";
        Nodo<T> aux=raiz;
        while(aux!=null){
            result+=" > "+aux;
            aux=aux.sig;
        }
        return result;
    }
    
}
