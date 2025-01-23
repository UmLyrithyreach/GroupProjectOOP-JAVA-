package Class;
import java.util.ArrayList; 
import java.util.List;

public class Order {
    private Customer customer; 
    private List<OrderItem> orderItems; 
    
    public Order() {
        this.orderItems = new ArrayList<>();
    }
    public Order(Customer customer) {
        this.customer = customer;
        this.orderItems = new ArrayList<>();
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem); // Add an item to the order
    }


    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems){this.orderItems = orderItems;}
}