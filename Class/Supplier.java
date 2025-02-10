package Class;
public class Supplier {
    protected String name;
    protected String contactInfo;
    // this is the constructor
    public Supplier(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String to_String(){
        String text;
        text = "Supplier Name: " + name + "\nSupplier Contact Info: " + contactInfo;
        return text;
    }
}