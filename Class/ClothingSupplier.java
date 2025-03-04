package Class;

import java.util.ArrayList;

public class ClothingSupplier extends Person {
    static int idCounter = 1;
    int id;
    public static ArrayList<ClothingSupplier> supplierList = new ArrayList<>();

    // Constructor
    public ClothingSupplier(String name, String phoneNumber, String address) {
        super(name, phoneNumber, address);
        this.id = idCounter++;
    }

    public static ClothingSupplier searchSupplier(String name) {
        for (ClothingSupplier supplier: supplierList) {
            if (supplier.name.equalsIgnoreCase(name)) {
                return supplier;
            }
        }
        return null;
    }

    public static ClothingSupplier searchSupplier(int id) {
        for (ClothingSupplier supplier: supplierList) {
            if (supplier.id == id) {
                return supplier;
            }
        }
        return null;
    }

    public static void removeSupplier(String name) {
        ClothingSupplier supplier = searchSupplier(name);
        if (supplier == null) {
            System.out.println("Supplier not found.");
            return;
        } else {
            supplierList.remove(supplier);
            System.out.println("Supplier removed successfully.");
        }
    }

    public static void removeSupplier(int id) {
        ClothingSupplier supplier = searchSupplier(id);
        if (supplier == null) {
            System.out.println("Supplier not found.");
            return;
        } else {
            supplierList.remove(supplier);
            System.out.println("Supplier removed successfully.");
        }
    }

    public static void displaySuppliers() {
        for (ClothingSupplier supplier: supplierList) {
            System.out.println(supplier);
        }
    }

    public static void addSupplier(ClothingSupplier supplier) {
        supplierList.add(supplier);
    }

    // Getter and Setter for id
    public int getId() {
        return this.id;
    }

    // Override Method
    @Override
    public String toString() {
        String result = "ID: " + this.id + "\n";
        result += super.toString();
        
        return result;
    }
}
