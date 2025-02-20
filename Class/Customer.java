package Class;

import java.util.ArrayList;

public class Customer extends Person {
    static ArrayList<Customer> customerList = new ArrayList<>();
    static int idCounter = 1;
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
    public void buyMembership(){
        Membership member = new Membership(name, name, address, gender, gender, name, name, name, age);
        if(!member.isMembership()){
            member.addMember();
        }
    }

    @Override
    public String toString() {
        return "\nCustomer ID: " + this.id +
                super.toString() +
                "\nDelivery Address: " + this.deliveryAddress;
    }
}