package Class;

public class OrderDetail {
    private static int idCounter = 1;
    private int orderDetailId;
    private Order order;
    private Clothes product; ;
    private int quantity;
    private double pricePerUnit;
    private double discount;
    private double subTotal;

    public OrderDetail(Order order, Clothes product, int quantity, double pricePerUnit, double discount, double subTotal) {
        this.orderDetailId = idCounter++ ;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.pricePerUnit = product.getPrice();
        this.discount = discount;
        this.subTotal = calculateSubTotal();
    }

    public double calculateSubTotal() {
        return (pricePerUnit * quantity) - discount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderDetailID=" + orderDetailId +
                ", OrderID=" + order.getOrderId() +
                ", Product=" + product.getName() +
                ", Quantity=" + quantity +
                ", PricePerUnit=" + pricePerUnit +
                ", Discount=" + discount +
                ", SubTotal=" + subTotal +
                '}';
    }
}
