package Class;
//invalid ID, invalid quantity, empty payment method
public class PurchaseException extends Exception {
    public PurchaseException(String message) {
        super(message);
    }
    
}
