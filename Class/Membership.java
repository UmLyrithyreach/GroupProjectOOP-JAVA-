package Class;

import java.util.ArrayList;

public class Membership extends Person{
    private static int nextID = 0;
    private int MemberID;
    private static final ArrayList<Membership> MembershipList = new ArrayList<>();  // Membership ID to identify what id of the membership that the user own (extra: final is like const once the value is set it can't be change)
    private String MembershipType;  //Bronze? Silver? Gold?
    private String MembershipStatus;    //Expired? Canceld? Continue?
    private String StartDate;       //Subscribe when?
    private String ExpiredDate;     //Expired When?
    private String renewalDate;     //Renew when?
    private int pointBalance;

    public Membership(String name, String phone, String address, String membershipType, 
    String membershipStatus, String startDate, String expiredDate,
    String renewalDate, int pointBalance) {
        super(name, phone, address);
        this.MemberID = nextID++;
        this.MembershipType = membershipType;
        this.MembershipStatus = membershipStatus;
        this.StartDate = startDate;
        this.ExpiredDate = expiredDate;
        this.renewalDate = renewalDate;
        this.pointBalance = pointBalance;
        addMember();
    }

    public ArrayList<Membership> GetAllMembers(){return MembershipList;}   // Mainly because I want it to be able to declare inside the toString Method it isn't neccessary really
    private void addMember(){MembershipList.add(this);} // prevent data leaked since MembershipID originally a private method(Copilot, Chatgpt)
    
    public String displayAll(){
        Membership member = new Membership(name, phoneNumber, address, MembershipType, MembershipStatus, StartDate, ExpiredDate, renewalDate, pointBalance);
        return member.toString();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Membership ID: " + this.MemberID + super.toString();
    }
}