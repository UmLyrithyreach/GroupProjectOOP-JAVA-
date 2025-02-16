package Class;

import java.util.ArrayList;
import java.util.List;

public class Order extends Transaction {
    private static int orderCounter = 1; // Separate counter for Orders
    private Employee employee;
    private String orderDate;
    private String paymentMethod;
    private List<OrderDetail> orderDetails;

    public Order(Employee employee, String orderDate, String paymentMethod) {
        super(0.0); // Calls Transaction constructor
        this.id = orderCounter++; // Assign unique ID for Order
        this.employee = employee;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.orderDetails = new ArrayList<>();
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        totalAmount += orderDetail.getSubTotal(); // Inherited from Transaction
    }


    @Override
    public String toString() {
        return "Order {\n" +
                "\n\tID=" + id + // `id` inherited from Transaction
                ", \n\tEmployee=" + employee.name +
                ", \n\tOrderDate=" + orderDate +
                ", \n\tTotal Amount=$" + totalAmount +
                ", \n\tPaymentMethod=" + paymentMethod +
                ", \n\tOrderDetails=" + orderDetails +
                "\n}";
    }
}

