package Class;

import java.util.ArrayList;

public class Customer extends Person {
    static ArrayList<Customer> customerList = new ArrayList<>();
    static int idCounter = 0;
    int id;
    private String deliveryAddress;

    // Constructor
    public Customer(String name, String phone, String deliveryAddress) {
        super(name, phone);
        this.id = idCounter++;
        this.deliveryAddress = deliveryAddress;
        customerList.add(this);
    }

    // Method

    public String getDeliveryAddress() { return this.deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    
    @Override
    public String toString() {
        return "ID: " + this.id +
                super.toString() +
                "\nDelivery Address: " + this.deliveryAddress;
    }
}