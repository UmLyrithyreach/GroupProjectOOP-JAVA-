package Class;
public class OrderItem {
    private Nut nut;
    private int quantity;

    public OrderItem(){} // default constructor

    public OrderItem(Nut nut, int quantity) { // parameter constructor
        this.nut = nut;
        this.quantity = quantity;
    }

    // future implementation
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
