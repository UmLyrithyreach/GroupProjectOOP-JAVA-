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
    String style;
    int supplierId;

    public Clothes(String name, String brand, String size, double price, int stock, String style, int supplierId) {
        this.id = idCounter++;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.style = style;
        this.supplierId = supplierId;
        this.sizes = new ArrayList<>();
    }

    public Clothes(int id, String name, String brand) {
        this.id = idCounter++;
        this.name = name;
        this.brand = brand;
        this.price = 0.0;
        this.stock = 0;
        this.style = "Unknown";
        this.sizes = new ArrayList<>();
    }

    // Getter Methods
    public static int getIdCounter() { return idCounter; }

    public int getID() { return this.id; }
    
    public String getName() { return name; }

    public int getStock() { return this.stock; }

    public double getPrice() { return this.price; }

    public String getStyle() { return this.style; }

    public int getSupplierId() { return this.supplierId; }

    public ArrayList<String> getSizes() { return this.sizes; }

    public String getSizesString() {
        String sizeString = "";
        for (String size: sizes) {
            sizeString += size + " ";
        }
        return sizeString;
    }

    public String getBrand() { return this.brand; }
    

    // Setter Methods
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
        String info = "====================================\nID: " + this.id
                + "\nName: " + this.name
                + "\nBrand: " + this.brand
                + "\nPrice: $" + this.price
                + "\nStock: " + this.stock
                + "\nSize: " + this.sizes
                + "\n====================================\n";
                
        for (String size: sizes) {
            info += size + " ";
        }
        
        return info;
    }
}