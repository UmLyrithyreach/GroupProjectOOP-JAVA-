package Class;
public class Customer {
    private String name;
    private String contactInfo;

    public Customer(){}
    public Customer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public String getContactInfo() { 
        return contactInfo; 
    }
    public void setContactInfo(String contactInfo) { 
        this.contactInfo = contactInfo; 
    }
}