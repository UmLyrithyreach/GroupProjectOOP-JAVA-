package Class;

import java.util.ArrayList;

public final class Membership extends Customer{
    private static int nextID = 1;
    private int MembershipID;
    private static final ArrayList<Membership> MembershipList = new ArrayList<>();  // Membership ID to identify what id of the membership that the user own (extra: final is like const once the value is set it can't be change)
    private String MembershipType;  //Bronze? Silver? Gold?
    private String MembershipStatus;    //Expired? Canceld? Continue?
    private String StartDate;       //Subscribe when?
    private String ExpiredDate;     //Expired When?
    private String renewalDate;     //Renew when?
    public int pointBalance = 0;

    public Membership(String name, String phone, String address, String membershipType, 
    String membershipStatus, String startDate, String expiredDate,
    String renewalDate, int pointBalance) {
        super(name, phone, address);
        this.MembershipID = nextID++;
        this.MembershipType = membershipType;
        this.MembershipStatus = membershipStatus;
        this.StartDate = startDate;
        this.ExpiredDate = expiredDate;
        this.renewalDate = renewalDate;
        this.pointBalance = pointBalance;
        addMember();
    }

    public int getMembershipID(){
        return this.MembershipID;
    }
    public ArrayList<Membership> GetAllMembers(){return MembershipList;}   //print out all members who have membership
    public void addMember() {       // prevent data leaked since MembershipID originally a private method(Copilot, Chatgpt)
        if (!isMembership()) {
            MembershipList.add(this);
            System.out.println("Membership added successfully!");
        } else {
            System.out.println("User is already a member.");
        }
    }
    
    public boolean isMembership(){
        for (Membership m : MembershipList){
            if(m.getMembershipID() == this.MembershipID){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "\n------------------------Membership ID: " + this.MembershipID + "------------------------"+ super.toString();
    }
}