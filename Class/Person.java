package Class;

public class Person {
    String name;
    int age;
    String gender;
    String phoneNumber;
    String email;
    String address;

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

    //Constructor for Supplier
    public Person (String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = 0;
        this.gender = "Unknown";
        this.email = "Unknown";
    }

    @Override
    public String toString() {
        return ("\nName: " + this.name +
                "\nAge: " + this.age +
                "\nGender: " + this.gender +
                "\nPhone Number: " + this.phoneNumber +
                "\nEmail: " + this.email +
                "\nAddress: " + this.address);
    }


}
