package Class;

import java.util.ArrayList;

public class Customer extends Person {
    static ArrayList<Customer> customerList = new ArrayList<>();
    static int idCounter = 0;
    int id;
    String deliveryAddress;

    public Customer(String name, String phone, String deliveryAddress) {
        super(name, phone);
        this.id = idCounter++;
        this.deliveryAddress = deliveryAddress;
        customerList.add(this);
    }
    
}