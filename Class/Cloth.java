package Class;

public class Cloth {
    // Supply
    String product_name;
    int product_ID;
    String product_type;
    String product_origin;
    int stockQuantity;
    // Supplier

    // this is the constructor
    public Cloth(String name, String type, int stockQuantity, String origin, int product_ID) {
        this.product_name = name;
        this.product_type = type;
        this.product_origin = origin;
        this.stockQuantity = stockQuantity;
        this.product_ID = product_ID;
    }
    // this is use to clarify or call the private datatypes
    public String to_String(){
        Supplier supplier = new Supplier(product_name, product_name);
        String text;
        text = "Product Name: " + product_name + "\nProduct ID: " + product_ID + "\nProduct Type: " + product_type + "\nProduct Origin: " + product_origin + "\nStock Quantity: " + stockQuantity +
        "\nSupploer Name: " + supplier.name + "\nSupplier Contact Info: " + supplier.contactInfo;
        return text;
    }

}