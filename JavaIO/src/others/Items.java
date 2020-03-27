
package others;

import java.io.Serializable;


public class Items implements Serializable{

    private static final long serialVersionUID=1L;
    String name;
    double prize;
    
    public Items() {
        name="";
        prize=0;
    }

    public Items(String name, double prize) {
        this.name = name;
        this.prize = prize;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Items{" + "name=" + name + ", prize=" + prize + '}';
    }
   //Items.fromArray({"PC","1200"});  -> instancia de un ITEM
    public static Items fromArray(String[] elementos) {
        if(elementos.length!=2)
            return null;
        return new Items
            (elementos[0],Double.parseDouble(elementos[1]));
    }
    
}
