package estructurasdinamicas;

import java.util.Objects;

public class Nodo<T> implements INodo<T> {
    private T value;
    public Nodo<T> sig;
    public Nodo<T> ant;

    private Nodo(){}
    
    public Nodo(T value) {
        this.value = value;
        sig=ant=null;
    }
    
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value=value;
    }

    @Override
    public String toString() {
        return "Nodo{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean result=false;
        if(obj!=null){
            if(obj instanceof Nodo){
                Nodo otro=(Nodo)obj;
                if(value.equals(otro.value)){
                    result=true;
                }
            }
        }
        return result;
    }

    
    
    @Override
    public int compareTo(INodo<T> o) {
        int result = 1;
        if (o != null) {
            try {
                result = (Integer) value.getClass().getMethod("compareTo", o.getValue().getClass()).invoke(value, o.getValue());
                //value.compareTo(o.getValue())
            } catch (Exception e) {
                result = Objects.compare(value, o.getValue(), null);
            }
        }
        return result;
    }
    
}
