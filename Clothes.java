import java.util.ArrayList;

public class Clothes {
    private int id;
    private String name;
    private String brand;
    private ArrayList<String> sizes;
    private double price;
    private int stock;

    public Clothes(int id, String name, String brand, String size, double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.sizes = new ArrayList<>();
        this.price = price;
        this.stock = stock;
    }

    public Clothes(int id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.sizes = new ArrayList<>();
        this.price = 0.0;
        this.stock = 0;
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

    public double applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            return (this.price - this.price * (percentage / 100));
        }
        return 0;
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