package Class;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    int orderId;
    static int orderCounter = 0;
    Employee employeeId;
    Employee employeeName;
    ArrayList<Clothes> orderClothes; // name and pricePerUnit of product
    ArrayList<Integer> quantity;
    LocalDate orderDate;
    String paymentMethod;



    public Order(Employee employeeId, ArrayList<Clothes> clothesList,ArrayList<Integer> quantity, LocalDate orderDate, String paymentMethod) {
        this.orderId = orderCounter++;
        this.employeeId = employeeId;
        this.employeeId = employeeName;
        this.orderClothes = clothesList;
        this.orderDate = LocalDate.now();
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
    }


    public double calculateSubAmount(int quantity, double pricePerUnit) {
        return quantity * pricePerUnit;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0;
        for (Clothes clothes : orderClothes) {
            totalAmount += calculateSubAmount(quantity, clothes.price);
        }
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order: {" +
                "\n\t OrderId: " + orderId +
                "\n\t Taked order by employee's Id: " + employeeId.getEmployeeID() +
                "\n\t Taked order by employee's name: " + employeeName.getName() +  
                "\n\t Date of the order: " + orderDate +
                "\n\t Order quantity: " + quantity +
                "\n\t Paid by: " + paymentMethod;
    }
}

