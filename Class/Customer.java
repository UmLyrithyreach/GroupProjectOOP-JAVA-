package Class;

import java.util.TreeMap;

public class Customer {
    private String name;
    private int age;
    private String contactInfo;
    public static TreeMap<Integer, Customer> CustomerID = new TreeMap<>();

    public Customer(String name, String contactInfo, int CustomerID, String Gender, int age) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.age = age;
        Customer.CustomerID.put(CustomerID, this);
    }
    
    // public String getName() { 
    //     return name; 
    // }
    // public void setName(String name) { 
    //     this.name = name; 
    // }
    // public String getContactInfo() { 
    //     return contactInfo; 
    // }
    // public void setContactInfo(String contactInfo) { 
    //     this.contactInfo = contactInfo; 
    // }
}