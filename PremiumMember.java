 public class PremiumMember extends GymMember
{
    private final double premiumCharge;//declaring private instance variable
    
    public double getPremiumCharge()//creating getter/acesssor method
    {
        return this.premiumCharge;
    }
    
    private String personalTrainer;//declaring private instance variable
    
    public String getPersonalTrainer()//creating getter/acesssor method
    {
        return this.personalTrainer;
    }
    
    private boolean isFullPayment;//declaring private instance variable
    
    public boolean getIsFullPayment()//creating getter/acesssor method
    {
        return this.isFullPayment;
    }
    
    private double paidAmount;//declaring private instance variable
    
    public double getPaidAmount()//creating getter/acesssor method
    {
        return this.paidAmount;
    }
    
    private double discountAmount;//declaring private instance variable
    
    public double getDiscountAmount()//creating getter/acesssor method
    {
        return this.discountAmount;
    }
    
    //Constructor of this class
    public PremiumMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate,String personalTrainer)
    {    
        //refering to parent class constructor
        super(id,name,location,phone,email,gender,DOB,membershipStartDate);
        
        //initializing values as instructed
        this.premiumCharge=50000;
        this.isFullPayment=false;
        this.paidAmount=0;
        this.discountAmount=0;
        
        //assigning values from parameters
        this.personalTrainer=personalTrainer;
    }
    
    //method to check the ammount paid by the member
    public String payDueAmount(double paidAmount)
    {
        //default output
        String out="";
        //self declared variable for calculation
        double remainingAmount=this.premiumCharge-this.paidAmount;
        //executes if all the dues have been cleared
        if(this.isFullPayment==true)
        {
            out=calculateDiscount();
        }
        else 
        {
            //executes if the paid ammount is larger than the total required sum
            if((this.paidAmount+paidAmount)>premiumCharge)
            {
                out="You are paying "+(paidAmount-remainingAmount)+" extra then required please only pay the require sum";
            }
            else
            {
                this.paidAmount=this.paidAmount+paidAmount;
                remainingAmount=this.premiumCharge-this.paidAmount;
                //executes if all the dues were paid this time
                if(remainingAmount == 0)
                {
                    this.isFullPayment = true;
                    out= calculateDiscount();;
                }
                
                //executes if dues are paid partially
                else
                {
                out="Your payment has sucessfully been received, You now have "+remainingAmount+" as due amount";
                }
            }
            //executes to check if all the dues have been cleared
            if(this.paidAmount == this.premiumCharge)
            {
                this.isFullPayment=true;
            }
        }
        return out;
    }
    
    //method to calculate discounts
    public String calculateDiscount()
    {
        String disc="";
        
        //executes only if all the dues have been cleared rewarfing with a discount
        if(this.isFullPayment == true)
        {
            this.discountAmount=this.premiumCharge*0.1;
            this.paidAmount = this.premiumCharge - this.discountAmount;
            disc="Clearing all your dues has earned a discount of 10% you can have "+this.discountAmount+" of the total plans charge back";
        }
        else
        {
            disc="Sorry there are no discounts for now :(.\nHint💡: Clear dues;)";
        }
        return disc;
    }
    
    //method to reset a members details to default;
    public void revertPremiumMember()
    {
        resetMember();//resets member data
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }
    
    @Override//implementing parent class abstract method
    //method to mark attendancce
    public void markAttendance()
    {
        this.attendance++;//increases by 1
        this.loyaltyPoints+=10;//increases by 10
    }
    
    //method to display all information of the member
    @Override
    public void display()
    {
        super.display();
        System.out.println("12.Personal Trainer: "+personalTrainer+"\n13.Paid amount: "+paidAmount);
        System.out.println("14.Full Payment: "+isFullPayment);
        if(isFullPayment)
        {
            System.out.println("15.Remaining Amount: 0.0");
            System.out.println("16.Discount: "+discountAmount);
        }
        else
        {
            System.out.println("15.Remaining Amount: "+(premiumCharge-paidAmount));
        }
    }
}
