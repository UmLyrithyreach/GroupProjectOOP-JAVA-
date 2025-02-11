package Class;
import java.util.ArrayList;

public class Supplier {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private ArrayList<Clothes> suppliedClothes;

    public Supplier(int id, String name, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.suppliedClothes = new ArrayList<>();
    }

    public Supplier(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = "Blank";
        this.address = "Blank";
        this.suppliedClothes = new ArrayList<>();
    }

    public void addClothes(Clothes clothes) {
        suppliedClothes.add(clothes);
    }

    @Override
    public String toString() {
        String result = "ID: " + this.id + "\n";
        result += "Name: " + this.name + "\n";
        result += "Phone Number: " + this.phoneNumber + "\n";
        result += "Email: " + this.email + "\n";
        result += "Address: " + this.address + "\n";
        result += "Supplied Clothes:\n";

        if (suppliedClothes.isEmpty()) {
            result += "No clothes supplied.\n";
        } else {
            for (Clothes clothes: suppliedClothes) {
                result += clothes.toString() + "\n";
            }
        }
        
        return result;
    }
}
