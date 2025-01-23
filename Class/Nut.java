package Class;
public class Nut {
    private String name;
    private String type;
    private double pricePerKg;
    private String origin;
    private int stockQuantity;

    // No Have to put Public Nut that has no value

    public Nut(String name, String type, double pricePerKg, String origin, int stockQuantity) {
        this.name = name;
        this.type = type;
        this.pricePerKg = pricePerKg;
        this.origin = origin;
        this.stockQuantity = stockQuantity;
    }

    // this is use to clarify or call the private datatypes

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

    public double getPricePerKg() { 
        return pricePerKg; 
    }
    public void setPricePerKg(double pricePerKg) { 
        this.pricePerKg = pricePerKg; 
    }

    public String getOrigin() { 
        return origin; 
    }
    public void setOrigin(String origin) { 
        this.origin = origin; 
    }

    public int getStockQuantity() { 
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) { 
        this.stockQuantity = stockQuantity; 
    }
}