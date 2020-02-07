package estructurasdinamicas;

public class EstructuraUnica<T> extends Estructura<T> {
    @Override
    public int put(T v, int pos) {
        if(contains(v)>-1)
            return length();
        return super.put(v, pos); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int unshift(T v) {
        if(contains(v)>-1)
            return length();
        return super.unshift(v); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int push(T v) {
        if(contains(v)>-1)
            return length();
        return super.push(v); //To change body of generated methods, choose Tools | Templates.
    }
}
