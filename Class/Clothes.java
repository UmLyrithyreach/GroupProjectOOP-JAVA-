package Class;

import java.util.ArrayList;

public class Clothes {
    static int idCounter = 1;
    int id;
    String name;
    String brand;
    ArrayList<String> sizes;
    double price;
    int stock;
    String material;
    String fit;
    String style;
    String preference;

    public Clothes(int id, String name, String brand, String size, double price, int stock, String material, String style, String fit, String preference) {
        this.id = idCounter++;
        this.name = name;
        this.brand = brand;
        this.sizes = new ArrayList<>();
        this.price = price;
        this.stock = stock;
        this.material = material;
        this.style = style;
        this.fit = fit;
        this.preference = preference;
    }

    public Clothes(int id, String name, String brand) {
        this.id = idCounter++;
        this.name = name;
        this.brand = brand;
        this.sizes = new ArrayList<>();
        this.price = 0.0;
        this.stock = 0;
        this.material = "Unknown";
        this.style = "Unknown";
        this.fit = "Unknown";
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void addSize(String size) {
        if (!this.sizes.contains(size)) {
            this.sizes.add(size);
        } else {
            System.out.println("Size exists!");
        }
    }

    public void removeSize(String size) {
        if (this.sizes.contains(size)) {
            this.sizes.remove(size);
        } else {
            System.out.println("Size does not exist!");
        }
    }

    public void increaseStock(int amount) {
        if (amount > 0) {
            this.stock += amount;
        }
    }

    public void decreaseStock(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    public boolean isAvailable() {
        return (this.stock > 0) ? true : false;
    }

    @Override
    public String toString() {
        String info = "ID: " + this.id
                + "\nName: " + this.name
                + "\nBrand: " + this.brand
                + "\nPrice: " + this.price
                + "\nStock: " + this.stock
                + "\nSize: ";
                
        for (String size: sizes) {
            info += size + " ";
        }

        return info;
    }
}