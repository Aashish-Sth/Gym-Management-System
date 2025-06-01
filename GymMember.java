public abstract class GymMember//class name
{
    protected int id;//declaring protected variable
    
     public int getId()//creating getter/acesssor method
    {
        return this.id;
    }

    protected String name;//declaring protected variable
    
    public String getName()//creating getter/acesssor method
    {
        return this.name;
    }
    
    protected String location;//declaring protected variable
    
    public String getLocation()//creating getter/acesssor method
    {
        return this.location;
    }
    
    protected String phone;//declaring protected variable
    
    public String getPhone()//creating getter/acesssor method
    {
        return this.phone;
    }
    
    protected String email;//declaring protected variable
    
    public String getEmail()//creating getter/acesssor method
    {
        return this.email;
    }
    
    protected String gender;//declaring protected variable
    
    public String getGender()//creating getter/acesssor method
    {
        return this.gender;
    }
    
    protected String DOB;//declaring protected variable
    
    public String getDOB()//creating getter/acesssor method
    {
        return this.DOB;
    }
    
    protected String membershipStartDate;//declaring protected variable
    
    public String getMembershipStartDate()//creating getter/acesssor method
    {
        return this.membershipStartDate;
    }
    
    protected int attendance;//declaring protected variable
    
    public int getAttendance()//creating getter/acesssor method
    {
        return this.attendance;
    }
    
    protected double loyaltyPoints;//declaring protected variable
    
    public double getLoyaltyPoints()//creating getter/acesssor method
    {
        return this.loyaltyPoints;
    }
    
    protected boolean activeStatus;//declaring protected variable
    
    public boolean getActiveStatus()//creating getter/acesssor method
    {
        return this.activeStatus;
    }
    
    //Constructor of this class
    public GymMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate)
    {
        //assigning values from parameters
        this.id=id;
        this.name=name;
        this.location=location;
        this.phone=phone;
        this.email=email;
        this.gender=gender;
        this.DOB=DOB;
        this.membershipStartDate=membershipStartDate;
        
        //initializing as instructed
        this.attendance=0;
        this.loyaltyPoints=0;
        this.activeStatus=false;
    }
    
    //abstract method to be implemented in sub classes
    public abstract void markAttendance(); 
    
    //method to activate/renew membership
    public void activateMembership()
    {
        this.activeStatus=true;
    }
    
    //method to deactivate/pause membership
    public void deactivateMembership()
    {
        if (activeStatus)//executes only if the membership is activated
        {
            this.activeStatus=false;
        }
    }
    
    //method to reset the member's info
    public void resetMember()
    {
        this.activeStatus=false;
        this.attendance=0;
        this.loyaltyPoints=0;
    }
    
    //method to display all the information about the member
    public void display()
    {
        System.out.println("1.ID: "+id+"\n2.Name: "+name+"\n3.Location: "+location);
        System.out.println("4.Phone no: "+phone+"\n5.Email: "+email+"\n6.Gender:"+gender);
        System.out.println("7.DOB: "+DOB+"\n8.Membership Start Date: "+membershipStartDate);
        System.out.println("9.Attendance: "+attendance+"\n10.Loyalty Points: "+loyaltyPoints);
        System.out.println("11.Active Status: "+activeStatus);
    }
}
