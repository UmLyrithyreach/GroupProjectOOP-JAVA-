import Class.Cloth;
import Class.Supplier;

public class Main {
    public static void main(String[] args) {
        Cloth duh = new Cloth(null, null, 0, null, 0);
        System.out.println(duh.to_String());
        Supplier supplier = new Supplier("Duh", "1234567890");
        System.out.println(supplier.to_String());
    }
}