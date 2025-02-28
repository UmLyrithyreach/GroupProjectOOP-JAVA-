package Class;

import java.time.LocalDate;
import java.util.ArrayList;

public final class Membership extends Customer{
    private static int nextID = 1;
    private final int membershipID;
    private static final ArrayList<Customer> membershipList = new ArrayList<>();  // Membership ID to identify what id of the membership that the user own (extra: final is like const once the value is set it can't be change)
    private String membershipType;  //Bronze? Silver? Gold?
    private String membershipStatus;    //Expired? Canceld? Continue?
    private LocalDate startDate;       //Subscribe when?
    private LocalDate expiredDate;     //Expired When?
    private LocalDate renewalDate;     //Renew when?
    public int pointBalance = 0;

    public Membership(String name, String phone, String address, String membershipType, 
    String membershipStatus, LocalDate startDate, LocalDate expiredDate,
    LocalDate renewalDate, int pointBalance) {
        super(name, phone, address);
        this.membershipID = nextID++;
        this.membershipType = membershipType;
        this.membershipStatus = membershipStatus;
        this.startDate = startDate;
        this.expiredDate = expiredDate;
        this.renewalDate = renewalDate;
        this.pointBalance = pointBalance;
        addMember();
    }

    @Override
    public int getMembershipID(){
        return this.membershipID;
    }

    // public String dateValidation(LocalDate start, LocalDate expired, LocalDate renewal){
    //     start = LocalDate.now();
    //     expired = start.plusYears(1);
    //     renewal = expired.minusDays(30);
    // }    
    
    public ArrayList<Customer> GetAllMembers(){return membershipList;}   //print out all members who have membership
    public void addMember() {       // prevent data leaked since MembershipID originally a private method(Copilot, Chatgpt)
        membershipList.add(this);
    }
    
    // public boolean isMembership(){
    //     for (Customer c : membershipList){
    //         if(c.getMembershipID() == this.membershipID){
    //             return true;
    //         }
    //     }
    //     return false;
    // }        I'll think about this later

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "\n------------------------Membership ID: " + this.membershipID + "------------------------"+ "\n"
        + "Name: " + this.getName() + "\n"
        + "Phone Number: " + this.phoneNumber() + "\n" 
        + "Address: " + this.getDeliveryAddress() + "\n"
        + "Membership Type: " + this.membershipType + "\n"
        + "Membership status: " + this.membershipStatus + "\n"
        + "Start date: " + this.startDate + "\n"
        + "Expired date: " + this.expiredDate + "\n"
        + "Renewal date: " + this.renewalDate + "\n"
        + "Points: " + this.pointBalance + "\n";
    }

    public static void main(String[] args) {
        new Membership("Um Lyrithyreach" , "09592780", "Street1067", "Bronze", "Actvie", LocalDate.now(), LocalDate.now().plusYears(1), LocalDate.now().minusDays(30), 100);
        new Membership("Hein Schmát" , "09592780", "Street1067", "Bronze", "Actvie", LocalDate.now(), LocalDate.now().plusYears(1), LocalDate.now().minusDays(30), 100);
        System.out.println(Membership.membershipList);
    }
}