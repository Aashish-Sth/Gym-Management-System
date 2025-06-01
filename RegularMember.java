public class RegularMember extends GymMember
{
    private final int attendanceLimit;//declaring private instance variable
    
    public int getAttandenceLimit()//creating getter/acesssor method
    {
        return this.attendanceLimit;
    }
    
    private boolean isEligibleForUpgrade;//declaring private instance variable
    
    public boolean getIsEligibleForUpgrade()//creating getter/acesssor method
    {
        return this.isEligibleForUpgrade;
    }
    
    private String removalReason;//declaring private instance variable
    
    public String getRemovalReason()//creating getter/acesssor method
    {
        return this.removalReason;
    }
    
    private String referralSource;//declaring private instance variable
    
    public String getReferralSource()//creating getter/acesssor method
    {
        return this.referralSource;
    }
    
    private String plan;//declaring private instance variable
    
    public String getPlan()//creating getter/acesssor method
    {
        return this.plan;
    }
    
    private double price;//declaring private instance variable
    
    public double getPrice()//creating getter/acesssor method
    {
        return this.price;
    }
    
    //Constructor of this class
    public RegularMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate,String referralSource)
    {    
        //refering to the parent class constructor
        super(id,name,location,phone,email,gender,DOB,membershipStartDate);
        //initializing as instructed
        this.attendanceLimit=30;
        this.isEligibleForUpgrade=false;
        this.removalReason="";
        this.plan="basic";
        this.price=6500;
        //assigning value from parameter
        this.referralSource=referralSource;
    }
    
    @Override//implementing parent class abstract method
    public void markAttendance()//method to mark attendance of members when they show up
    {
       this.attendance++;//increases attendance by 1
       this.loyaltyPoints+=5;//increases loyalty points by 5
    }
    
    //method to display prices of various available plans
    public double getPlanPrice(String plan)
    {
        double price=0;
         switch(plan.toLowerCase()){//switch statement to check according to the case
            case  "basic":
                price= 6500;
                break;
            case "standard":
                price= 12500;
                break;
            case "deluxe":
                price=18500;
                break;
            default:
                price=-1;
        }
        return price;
    }
    
    //method to request for a plan upgrade
    public String upgradePlan(String plan)
    {
        //default return value
        String answer="You are not eligible for upgrading your membership";
        
        //checking if the member is eligible for upgrading membership
        if(getAttendance() >= attendanceLimit)
        {
            isEligibleForUpgrade=true;
        }
        
        //Runs only if member is eligible for upgrading
        if(isEligibleForUpgrade==true)
        {
            if(plan.toLowerCase().equals(this.plan))
            {
                answer="You already are subscribed to the "+plan+" plan";//message if the current plan is selected
            } 
            else if(getPlanPrice(plan.toLowerCase()) == -1)
            {
                answer="The entered class that you wish to upgrade to doesnt exist";//message if non existent class is entered
            }
            else
            {
                this.plan=plan.toLowerCase();
                this.price=getPlanPrice(plan.toLowerCase());
                answer="Your plan has sucessfully been updated";
            }
        }
        return answer;
    }
    
    //method to revert back to regular starting membership
    public void revertRegularMember(String removalReason)
    {
        this.removalReason=removalReason;
        resetMember();//resets member info
        this.isEligibleForUpgrade= false;
        this.plan="basic";
        this.price=getPlanPrice(this.plan);
    }
    
    //display method to display the info of the members
    @Override
    public void display()
    {
        super.display();
        System.out.println("12.Referal Source: "+referralSource+"13.Plan: "+plan+"\n14.Price: "+price);
        if(this.removalReason!="")
        {
            System.out.println("15.Removal Reason: "+removalReason);
        }
    }
}
