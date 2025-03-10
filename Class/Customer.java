package Class;
import java.util.ArrayList;

public class Customer extends Person {
    static ArrayList<Customer> customerList = new ArrayList<>();
    static int idCounter = 1;
    int id;
    private String customerAddress;

    // Constructor
    public Customer(String name, String phone, String customerAddress) {
        super(name, phone);
        this.id = idCounter++;
        this.customerAddress = customerAddress;
        addCustomerToList();
    }

    // Setter
    private void addCustomerToList() {customerList.add(this);}
    // public abstract int getMembershipID();
    public String getCustomerAddress() { return this.customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    
    
    @Override
    public String toString() {
        return "\nCustomer ID: " + this.id +
                super.toString() +
                "\nDelivery Address: " + this.customerAddress;
    }

    
}