package Class;

public class Shirt extends Clothes{
    String shirtStyle;  // (T-shirt, Dress Shirt, Polo)
    String material;    // Cotton, Linen, 
    String fit;         // Slim, Regular, Loose

    public Shirt(int id, String name, String brand, String size, double price, int stock, String shirtStyle, String material, String fit) {
        super(id, name, brand, size, price, stock);
        this.shirtStyle = shirtStyle;
        this.material = material;
        this.fit = fit;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Shirt Style: " + this.shirtStyle +
                "Material: " + this.material +
                "Fit: " + this.fit;
    }
}
