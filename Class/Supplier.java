package Class;

import java.util.ArrayList;

public class Supplier extends Person {
    static int idCounter = 0;
    int id;
    private ArrayList<Clothes> suppliedClothes;

    // Constructor
    public Supplier(String name, String phoneNumber, String address) {
        super(name, phoneNumber, address);
        this.id = idCounter++;
        this.suppliedClothes = new ArrayList<>();
    }

    public void addClothes(Clothes clothes) {
        suppliedClothes.add(clothes);
    }

    // Getter and Setter for id
    public int getId() {
        return this.id;
    }
    
    public void setID(int id) {
        this.id = id;
    }

    // Override Method
    @Override
    public String toString() {
        String result = "ID: " + this.id + "\n";
        result += super.toString();
        result += "\nSupplied Clothes:\n";

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
