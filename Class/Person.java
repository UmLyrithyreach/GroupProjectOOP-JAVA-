package Class;

import java.util.ArrayList;

public class Person {
    protected String name;
    protected int age;
    protected String gender;
    protected String phoneNumber;
    protected String email;
    protected String address;

    // Constructor for full initialization
    public Person (String name, int age, String gender, String phoneNumber, String email, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Constructor for Customer
    public Person (String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = 0;
        this.gender = "Unknown";
        this.email = "Unknown";
        this.address = "Unknown";
    }

    // Constructor for Supplier
    public Person (String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = 0;
        this.gender = "Unknown";
        this.email = "Unknown";
    }

    // Getter and setter
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String phoneNumber() { return this.phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getAge() { return this.age; }
    public void setAge(int age) { this.age = age;}

    public String getGender() { return this.gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }
    

    public static String toString(String name, int age, String gender, String phoneNumber, String address, String email) {
        return ("\nName: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nPhone Number: " + phoneNumber +
                "\nEmail: " + email +
                "\nAddress: " + address);
    }


}
