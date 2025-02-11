package Class;
import java.util.ArrayList;

public class Supplier {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private ArrayList<Clothes> suppliedColthes;

    public Supplier(int id, String name, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.suppliedColthes = new ArrayList<>();
    }

    public Supplier(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = "Blank";
        this.address = "Blank";
        this.suppliedColthes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return ("ID: " + this.id +
            "\n");
    }
}
