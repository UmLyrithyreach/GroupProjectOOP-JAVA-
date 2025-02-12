package Class;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int idCounter = 1;
    private int orderId;
    private Employee employee;
    private String orderDate;
    private double totalAmount;
    private String paymentMethod;
    private List<OrderDetail> orderDetails;

    public Order(Employee employee, String orderDate, String paymentMethod) {
        this.orderId = idCounter++;
        this.employee = employee;
        this.orderDate = orderDate;
        this.totalAmount = 0.0;
        this.paymentMethod = paymentMethod;
        this.orderDetails = new ArrayList<>();
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        totalAmount += orderDetail.getSubTotal();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }


    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        for (OrderDetail od : orderDetails) {
            details.append("\n    ").append(od.toString());
        }

        return "Order{" +
                "ID=" + orderId +
                ", Employee=" + employee.getName() +
                ", OrderDate=" + orderDate +
                ", Total Amount=$" + totalAmount +
                ", PaymentMethod=" + paymentMethod +
                ", OrderDetails=" + details +
                '}';
    }
}

