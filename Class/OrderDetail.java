package Class;

public class OrderDetail extends Transaction {
    private static int idCounter = 1;
    private Order order;
    private Clothes product;
    private int quantity;
    private double pricePerUnit;
    private double discount;

    public OrderDetail(Order order, Clothes product, int quantity, double discount) {
        super(idCounter++, 0.0);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = product.getPrice();
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
                ", Product=" + product.getName() +
                ", Quantity=" + quantity +
                ", PricePerUnit=" + pricePerUnit +
                ", Discount=" + discount +
                ", SubTotal=" + totalAmount + // `totalAmount` inherited from Transaction
                '}';
    }
}
