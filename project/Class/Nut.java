package Class;
public class Nut {
    private String name;
    private String type;
    private double pricePerKg;
    private String origin;
    private int stockQuantity;

    public Nut() {}

    public Nut(String name, String type, double pricePerKg, String origin, int stockQuantity) {
        this.name = name;
        this.type = type;
        this.pricePerKg = pricePerKg;
        this.origin = origin;
        this.stockQuantity = stockQuantity;
    }
    //Getters and setters... (As in the previous example)
    //Example
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    
    
}