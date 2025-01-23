package Class;
public class Inventory {
    private Nut nut;
    private int quantity;
    
    public Inventory(Nut nut, int quantity) {
        this.nut = nut;
        this.quantity = quantity;
    }

    public Nut getNut() { 
        return nut; 
    }
    public void setNut(Nut nut) { 
        this.nut = nut; 
    }
    public int getQuantity() { 
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity; 
    }
}