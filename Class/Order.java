package Class;

import java.util.ArrayList;
import java.util.List;

public class Order extends Transaction {
    private static int idCounter = 1;
    private Employee employee;
    private String orderDate;
    private String paymentMethod;
    private List<OrderDetail> orderDetails;

    public Order(Employee employee, String orderDate, String paymentMethod) {
        super(idCounter++, 0.0); // Calls Transaction constructor
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
        return "Order{" +
                "ID=" + id + // `id` inherited from Transaction
                ", Employee=" + employee.getName() +
                ", OrderDate=" + orderDate +
                ", Total Amount=$" + totalAmount +
                ", PaymentMethod=" + paymentMethod +
                ", OrderDetails=" + orderDetails +
                '}';
    }
}

