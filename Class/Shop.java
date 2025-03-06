package Class;

import java.util.ArrayList;

public class Shop {
    static ArrayList<Clothes> clothesList = new ArrayList<>();

    public static void addClothes(Clothes item) {
        clothesList.add(item);
    }

    public static void addClothesToFile(Clothes item) {
        // try (buffered)
    }

    public static void displayClothes() {
        for (Clothes clothes: clothesList) {
            System.out.println(clothes);
        }
    }

    // Search by name
    public static Clothes searchByName(String name) {
        for (Clothes clothes: clothesList) {
            if (clothes.name.equalsIgnoreCase(name)) {
                return clothes;
            }
        }
        return null; 
    }

    // Search by brand
    public static ArrayList<Clothes> searchByBrand(String brand) {
        ArrayList<Clothes> sameBrand = new ArrayList<>();
        for (Clothes clothes: clothesList) {
            if (clothes.brand.equalsIgnoreCase(brand)) {
                sameBrand.add(clothes);
            }
        }
        return sameBrand; 
    }

    // Search by ID
    public static Clothes searchById(int id) {
        for (Clothes clothes: clothesList) {
            if (clothes.id == id) {
                return clothes;
            }
        }
        return null;
    }

    public static int totalClothes() {
        return clothesList.size();
    }

    public static int totalStock() {
        int totalStock = 0;
        for (Clothes clothes: clothesList) {
            totalStock += clothes.stock;
        }
        return totalStock;
    }

    public static void removeClothesById(int id) {
        Clothes clothes = searchById(id);
        if (clothes != null) {
            clothesList.remove(clothes);
            System.out.println("Clothes removed successfully.");
        } else {
            System.out.println("Clothes not found!");
        }
    }
}
