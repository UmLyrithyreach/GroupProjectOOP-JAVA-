package Class;

public class Person {
    String name;
    int age;
    String gender;
    String phone;
    String email;
    String address;

    // Constructor for full initialization
    public Person (String name, int age, String gender, String phone, String email, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Constructor for Customer
    public Person (String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.age = 0;
        this.gender = "Unknown";
        this.email = "Unknown";
        this.address = "Unknown";
    }

    @Override
    public String toString() {
        return ("Name: " + this.name +
                "\nAge: " + this.age +
                "\nGender: " + this.gender +
                "\nPhone: " + this.phone +
                "\nEmail: " + this.email +
                "\nAddress: " + this.address);
    }


}
