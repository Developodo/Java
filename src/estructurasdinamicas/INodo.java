package estructurasdinamicas;

import java.io.Serializable;


public interface INodo<T> extends Serializable,Comparable<INodo<T>>{
    T getValue();
    void setValue(T value);
    
}
