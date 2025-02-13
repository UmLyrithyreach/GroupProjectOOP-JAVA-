package Class;

public class OrderDetail extends Transaction {
    private static int orderDetailCounter = 1; // Separate counter for OrderDetails
    private Order order;
    private Clothes product;
    private int quantity;
    private double pricePerUnit;
    private double discount;

    public OrderDetail(Order order, Clothes product, int quantity, double discount) {
        super(0.0); // Calls Transaction constructor (totalAmount starts at 0)
        this.id = orderDetailCounter++; // Assign unique ID for OrderDetail
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = product.price;
        this.discount = discount;
        this.totalAmount = calculateSubTotal(); // Inherited from Transaction
    }

    public double calculateSubTotal() {
        return (pricePerUnit * quantity) - discount;
    }

    public double getSubTotal() {
        return totalAmount; // using totalAmount from Transaction
    }
    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderDetailID=" + id + // `id` inherited from Transaction
                ", OrderID=" + order.getId() + // call the getId() from transaction
                ", Product=" + product.name +
                ", Quantity=" + quantity +
                ", PricePerUnit=" + pricePerUnit +
                ", Discount=" + discount +
                ", SubTotal=" + totalAmount + // `totalAmount` inherited from Transaction
                '}';
    }
}
