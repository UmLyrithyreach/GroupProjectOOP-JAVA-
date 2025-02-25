package Class;

import java.util.ArrayList;

public class Shop {
    static ArrayList<Clothes> clothesList = new ArrayList<>();

    public static void addClothes(Clothes item) {
        clothesList.add(item);
    }

    public void displayClothes() {
        for (Clothes clothes: clothesList) {
            System.out.println(clothes);
        }
    }

    // Search by name
    public Clothes searchByName(String name) {
        for (Clothes clothes: clothesList) {
            if (clothes.name.equalsIgnoreCase(name)) {
                return clothes;
            }
        }
        return null; 
    }

    // Search by brand
    public ArrayList<Clothes> searchByBrand(String brand) {
        ArrayList<Clothes> sameBrand = new ArrayList<>();
        for (Clothes clothes: clothesList) {
            if (clothes.brand.equalsIgnoreCase(brand)) {
                sameBrand.add(clothes);
            }
        }
        return sameBrand; 
    }

    // Search by ID
    public Clothes searchById(int id) {
        for (Clothes clothes: clothesList) {
            if (clothes.id == id) {
                return clothes;
            }
        }
        return null;
    }
}
