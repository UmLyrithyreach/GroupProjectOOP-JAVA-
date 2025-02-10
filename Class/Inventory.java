package Class;
public class Inventory {
    private Cloth cloth;
    private int quantity;
    
    public Inventory(Cloth cloth, int quantity) {
        this.cloth = cloth;
        this.quantity = quantity;
    }

    public Cloth getNut() { 
        return cloth; 
    }
    public void setNut(Cloth cloth) { 
        this.cloth = cloth; 
    }
    public int getQuantity() { 
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity; 
    }
}