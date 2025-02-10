package Class;
public class OrderItem {
    private Cloth cloth;
    private int quantity;

    public OrderItem(){} // default constructor

    public OrderItem(Cloth cloth, int quantity) { // parameter constructor
        this.cloth = cloth;
        this.quantity = quantity;
    }

    // future implementation
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
