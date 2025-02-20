package Class;

import java.util.ArrayList;

public final class Membership extends Customer{
    private static int nextID = 1;
    private final int membershipID;
    private static ArrayList<Customer> membershipList = new ArrayList<>();  // Membership ID to identify what id of the membership that the user own (extra: final is like const once the value is set it can't be change)
    private String membershipType;  //Bronze? Silver? Gold?
    private String membershipStatus;    //Expired? Canceld? Continue?
    private String startDate;       //Subscribe when?
    private String expiredDate;     //Expired When?
    private String renewalDate;     //Renew when?
    public int pointBalance = 0;

    public Membership(String name, String phone, String address, String membershipType, 
    String membershipStatus, String startDate, String expiredDate,
    String renewalDate, int pointBalance) {
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

    @Override
    public void buyMembership(){
        new Membership(name, phoneNumber, address, membershipType, membershipStatus, startDate, expiredDate, renewalDate, pointBalance);
    }

    public ArrayList<Customer> GetAllMembers(){return membershipList;}   //print out all members who have membership
    public void addMember() {       // prevent data leaked since MembershipID originally a private method(Copilot, Chatgpt)
        membershipList.add(this);
    }
    
    public boolean isMembership(){
        for (Customer c : membershipList){
            if(c.getMembershipID() == this.membershipID){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "\n------------------------Membership ID: " + this.membershipID + "------------------------"+ super.toString();
    }

    public static void main(String[] args) {
        Membership member = new Membership(null, null, null, null, null, null, null, null, idCounter);
        Customer c1 = new Membership(null, null, null, null, null, null, null, null, idCounter);
        System.out.println(Membership.membershipList); 
    }
}