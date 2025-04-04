package Class;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    int orderId;
    static int orderCounter = 1;
    int employeeId;
    String employeeName;
    ArrayList<Clothes> orderClothes; // name and pricePerUnit of product
    ArrayList<Integer> quantity;
    LocalDate orderDate;
    String paymentMethod;


    public Order(Employee employee, ArrayList<Clothes> clothesList,ArrayList<Integer> quantity, LocalDate orderDate, String paymentMethod) {
        this.orderId = orderCounter++;
        this.employeeId = employee.getEmployeeID();
        this.employeeName = employee.getName();
        this.orderClothes = clothesList;
        this.orderDate = LocalDate.now();
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
    }

    public String generateClothesReceipt(ArrayList<Clothes> clothesList, ArrayList<Integer> quantity) {
        String receipt = "";
        for (int i = 0; i < clothesList.size(); i++) {
            receipt += "\n\t\t" + clothesList.get(i).name + 
            ", Price per unit: $" + clothesList.get(i).price +
            ", Quantity: " + quantity.get(i) +
            ", Price: $" + calculateSubAmount(quantity.get(i), clothesList.get(i).price);
            // "\n\t\t";
        }
        return receipt;
    }


    public double calculateSubAmount(int quantity, double pricePerUnit) {
        return quantity * pricePerUnit;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0;
        int index = 0;
        for (Clothes clothes : orderClothes) {
            totalAmount += calculateSubAmount(quantity.get(index), clothes.price);
            index++;
        }
        return totalAmount;
    }

    @Override
    public String toString() {
        return "\n\n=================================== Order Summary ===================================" +
                "\nOrderId: " + orderId +
                "\nTaked order by employee's Id: " + employeeId +
                "\nTaked order by employee's name: " + employeeName + 
                "\nPurchased Item:" + generateClothesReceipt(orderClothes, quantity) +
                "\n====================================================================================="+
                "\nDate of the order: " + orderDate +
                "\nPaid by: " + paymentMethod+
                "\n=====================================================================================";
    }
}

