import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import javax.swing.JTextArea;

public class GymGUI implements ActionListener,FocusListener 
{
    JFrame mainFrame;//Declaring new JFrame
    
    //declaring necessary panels
    JPanel homePage, attendancePage, activatePage, upgradePage, discountPage, payPage, revertPage, displayPanel, dispInfo, navBar,fileDisplayPannel;
    
    //declaring necessary buttonsp
    JButton regular, premium, clear, submit, markAttendance, back, home, attendance, acOrDeac, upgrade, discounts, pay, revert, discountbtn, payBtn, activatebtn, deactivatebtn, upgradebtn, regularRevertbtn, premiumRevertbtn, display, displayRegular, displayPremium, back2, menu, saveToFile, readFromFile, returnBtn;
    
    //declaring necessary labels
    JLabel title, plan, price, enterPrice, id, name, location, phone, gender, email, dob, startDate, referal, discount, availableDiscount, personalTrainer, mark, attendanceID, activate, deactivate, discountTitle, discountID, payTitle, payID, payDue, activateHead, activateLable, upgradeTitle, upgradePlan, enterUpgradeId, revertTitle, revertID, displayHead, fileLable;
    
    //declaring necessary text fields
    JTextField enterId, enterName, enterLocation, enterPhone, enterEmail, enterReferal, enterPersonalTrainer, enterAttendanceID, activateID, deavtivateID, enterDiscountID, enterPayID, enterDue, upgradeID, enterRevertID;
    
    //declaring necessary combo boxes
    JComboBox Plans, DOBYear, DOBMonth, DOBDay, StartYear, StartMonth, StartDay, selectPlan;
    
    //declaring necessary radio buttons
    JRadioButton male, female;
    
    //declaring button group
    ButtonGroup genderGroup;
    
    
    
    //declaring layered pane
    JLayeredPane layers;
    
    //declaring scroll pane
    JScrollPane scroll;

    
    JTextArea fileDisplay;
    
    /*Global variable used to keep track of the plan of a regular member It is used
    * so that a regular member cannot down grade to a lower tier plan after upgrading
    */
    int preSelected; 
    
    Font bebas;//Declaring new font
    
    /*
       Main Method
     */
    ArrayList<GymMember> members= new ArrayList<>();
    public static void main(String[]args)
    {
        new GymGUI();//instansiating object of gym GUI    
    }
    
    /*
    Constructor of GymGUI
    */
    public GymGUI()
    {
        try  //Try block to test for any type of exception
        {
            bebas = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("bebas.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("bebas.ttf")));
        }
        catch(IOException | FontFormatException e) //catch block to catch possible exceptions that may occur
        {
            System.out.println("The font file cannot be found");
        }
   
        ImageIcon dumbell = new ImageIcon("dum.png");
        
        //JFrame initialization
        mainFrame = new JFrame("Register");
        mainFrame.setIconImage(dumbell.getImage());//Setting a custom icon for the JFrame
        mainFrame.setResizable(false);
        mainFrame.setSize(1920,1080);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);//Making the cross button actually exit the program
        
        
        
        
        
        
        //NAV BAR
        
        //creating the menu icon / button
        ImageIcon menus = new ImageIcon("menus.png");
        
        
        
        menu = new JButton();
        menu.setIcon(menus);
        menu.setBounds(80,80,100,80);
        menu.setFocusable(false);//removes a focused area off the button text
        menu.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        menu.setBorder(null);//disables the border of the button
        menu.setForeground(Color.white);
        menu.setFont(bebas.deriveFont(35f));
        menu.addActionListener(this);
        
        
        ImageIcon backIcon = new ImageIcon("back.png");
        
        
        back = new JButton("BACK");
        back.setIcon(backIcon);
        back.setBounds(20,80,100,80);
        back.setFocusable(false);//removes a focused area off the button text
        back.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        back.setBorder(null);//disables the border of the button
        back.setForeground(Color.white);
        back.setFont(bebas.deriveFont(35f));
        back.addActionListener(this);
        
        
        
        //Creating the home button in the nav bar
        ImageIcon homePg = new ImageIcon("home.png");
        
        home = new JButton("Home");
        home.setFont(bebas.deriveFont(24f));
        home.setIcon(homePg);
        home.setBounds(20,200,100,80);
        home.setFocusable(false);//removes a focused area off the button text
        home.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        home.setBorder(null);//disables the border of the button
        home.setForeground(new Color(0xB388FF ));
        home.addActionListener(this);
        
        
        
        //Creating the attendance button in the nav bar
        ImageIcon attend = new ImageIcon("attend.png");
        
        attendance = new JButton("Mark Attendance");
        attendance.setFont(bebas.deriveFont(24f));
        attendance.setIcon(attend);
        attendance.setBounds(20,320,200,80);
        attendance.setFocusable(false);//removes a focused area off the button text
        attendance.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        attendance.setBorder(null);//disables the border of the button
        attendance.setForeground(Color.white);
        attendance.addActionListener(this);
        
        
        
        //Creating the activate button in the nav bar
        ImageIcon act = new ImageIcon("act.png");
        
        acOrDeac = new JButton("activate/deactivate");
        acOrDeac.setFont(bebas.deriveFont(24f));
        acOrDeac.setIcon(act);
        acOrDeac.setBounds(30,260,200,80);
        acOrDeac.setFocusable(false);//removes a focused area off the button text
        acOrDeac.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        acOrDeac.setBorder(null);//disables the border of the button
        acOrDeac.setForeground(Color.white);
        acOrDeac.addActionListener(this);
        
        
        
        
        //Creating the upgrade button in the nav bar
        ImageIcon up = new ImageIcon("up.png");
        
        upgrade = new JButton("Upgrade plans");
        upgrade.setFont(bebas.deriveFont(24f));
        upgrade.setIcon(up);
        upgrade.setBounds(5,380,200,80);
        upgrade.setFocusable(false);//removes a focused area off the button text
        upgrade.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        upgrade.setBorder(null);//disables the border of the button
        upgrade.setForeground(Color.white);
        upgrade.addActionListener(this);
        
        
        
        
        //Creating the discount button in the nav bar
        ImageIcon disc = new ImageIcon("disc.png");
        
        discounts = new JButton("discounts");
        discounts.setFont(bebas.deriveFont(24f));
        discounts.setIcon(disc);
        discounts.setBounds(-12,440,200,80);
        discounts.setFocusable(false);//removes a focused area off the button text
        discounts.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        discounts.setBorder(null);//disables the border of the button
        discounts.setForeground(Color.white);
        discounts.addActionListener(this);
        
        
        
        
        //Creating the pay button in the nav bar
        ImageIcon payment = new ImageIcon("pay.png");
        
        
        pay = new JButton("pay dues");
        pay.setFont(bebas.deriveFont(24f));
        pay.setIcon(payment);
        pay.setBounds(-17,500,200,80);
        pay.setFocusable(false);//removes a focused area off the button text
        pay.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        pay.setBorder(null);//disables the border of the button
        pay.setForeground(Color.white);
        pay.addActionListener(this);
        
        
        
        
        //Creating the revert button in the nav bar
        ImageIcon rev = new ImageIcon("rev.png");
        
        revert = new JButton("revert plans");
        revert.setFont(bebas.deriveFont(24f));
        revert.setIcon(rev);
        revert.setBounds(0,560,200,80);
        revert.setFocusable(false);//removes a focused area off the button text
        revert.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        revert.setBorder(null);//disables the border of the button
        revert.setForeground(Color.white);
        revert.addActionListener(this);
        
        
        //Creating the display button in the nav bar
        ImageIcon info = new ImageIcon("info.png");
        
        display = new JButton("Display info");
        display.setFont(bebas.deriveFont(24f));
        display.setIcon(info);
        display.setBounds(-3,620,200,80);
        display.setFocusable(false);//removes a focused area off the button text
        display.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        display.setBorder(null);//disables the border of the button
        display.setForeground(Color.white);
        display.addActionListener(this);
        
        //Creating the display button in the nav bar
        ImageIcon save = new ImageIcon("save.png");
        
        saveToFile = new JButton("Save to File");
        saveToFile.setFont(bebas.deriveFont(24f));
        saveToFile.setIcon(save);
        saveToFile.setBounds(-3,920,200,80);
        saveToFile.setFocusable(false);//removes a focused area off the button text
        saveToFile.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        saveToFile.setBorder(null);//disables the border of the button
        saveToFile.setForeground(Color.white);
        saveToFile.addActionListener(this);
        
        ImageIcon read = new ImageIcon("read.png");
        
        readFromFile = new JButton("Read From File");
        readFromFile.setFont(bebas.deriveFont(24f));
        readFromFile.setIcon(read);
        readFromFile.setBounds(6,965,200,80);
        readFromFile.setFocusable(false);//removes a focused area off the button text
        readFromFile.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        readFromFile.setBorder(null);//disables the border of the button
        readFromFile.setForeground(Color.white);
        readFromFile.addActionListener(this);
        
        
        //Creating nav bar 
        navBar = new JPanel();
        navBar.setBounds(0,0,250,1080);
        navBar.setBackground(new Color(84, 73, 131));
        navBar.setVisible(false);
        navBar.setLayout(null);
        navBar.add(back);
        navBar.add(home);
        navBar.add(attendance);
        navBar.add(acOrDeac);
        navBar.add(upgrade);
        navBar.add(discounts);
        navBar.add(pay);
        navBar.add(revert);
        navBar.add(display);
        navBar.add(saveToFile);
        navBar.add(readFromFile);
        
        /*Code for home page*/
        
        
        homePage = new JPanel(); 
        homePage.setBounds(0,0,1920, 1080);
        homePage.setBackground(new Color(0x2C2638));  
        homePage.setLayout(null);  //removing the default flow layout
        
        title = new JLabel("<html>register <span style='color:#65558F'>new</span></html>");//html is used as java supports it
        title.setBounds(770,50,350,70);
        title.setForeground(Color.white);
        title.setFont(bebas.deriveFont(80f));
        
        //Code for regular member button
        regular = new JButton("REGULAR MEMBER");
        regular.setBounds(485,176,300,50); 
        regular.setFocusable(false);  //removes a focused area off the button text
        regular.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        regular.setBorder(null);  //disables the border of the button
        regular.setForeground(new Color(0x65558F));
        
        regular.addActionListener(this);
        regular.setFont(bebas.deriveFont(35f));
        
        
        //Code for regular premium button
        premium = new JButton("PREMIUM MEMBER");
        premium.setBounds(1115,176,300,50); 
        
        premium.setFocusable(false);//removes a focused area off the button text
        premium.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        premium.setBorder(null);//disables the border of the button
        premium.setForeground(Color.white);
        
        premium.addActionListener(this);
        premium.setFont(bebas.deriveFont(35f));
        
        
        //LABLES and TEXTDFIELDS
        
        //Label to display the selected plan
        plan = new JLabel("SELECTED PLAN :");
        plan.setBounds(330,290,150,50);   
        plan.setForeground(Color.white);
        plan.setFont(bebas.deriveFont(24f));
        
        //JComboBox in order to select a plan
        String [] plans = {"BASIC","STANDARD","DELUXE"};
        Plans = new JComboBox(plans);
        Plans.setBounds(460,295,110,36);   
        Plans.setBackground(new Color(255,255,255,80));
        Plans.setForeground(Color.black);
        Plans.addActionListener(this);
        
        
        //Label to display "Price :"
        price = new JLabel("PRICE :");
        price.setBounds(330,340,150,50);   
        price.setForeground(Color.white);
        price.setFont(bebas.deriveFont(24f));
        
        //Label to display the actual price of the selected plan
        enterPrice = new JLabel("6500");
        enterPrice.setBounds(460,345,110,36);   
        enterPrice.setForeground(new Color(255,255,255,90));
        enterPrice.setFont(bebas.deriveFont(24f));
        
        //Label to display "ID :"
        id = new JLabel("ID :");
        id.setBounds(330,390,150,50);   
        id.setForeground(Color.white);
        id.setFont(bebas.deriveFont(24f));
        
        //JTextField to take input
        enterId = new JTextField();
        enterId.setBounds(460,395,280,36);   
        enterId.setFont(bebas.deriveFont(15f));
        enterId.setText("Enter your ID");
        enterId.setForeground(Color.gray);
        enterId.addFocusListener(this);
        
        
        //Label to display "NAME :"
        name = new JLabel("NAME :");
        name.setBounds(330,450,150,50);   
        name.setForeground(Color.white);
        name.setFont(bebas.deriveFont(24f));
        
        
        
        //JTextField to take input
        enterName = new JTextField();
        enterName.setBounds(460,455,280,36);   
        enterName.setFont(bebas.deriveFont(15f));
        enterName.setText("Enter your name");
        enterName.setForeground(Color.gray);
        enterName.addFocusListener(this);
        
        
        
        //JLabel to display "LOCATION :"
        location = new JLabel("LOCATION :");
        location.setBounds(330,510,150,50);   
        location.setForeground(Color.white);
        location.setFont(bebas.deriveFont(24f));
        
        //JTextField to take input
        enterLocation = new JTextField();
        enterLocation.setBounds(460,515,280,36);   
        enterLocation.setFont(bebas.deriveFont(15f));
        enterLocation.setText("Enter location");
        enterLocation.setForeground(Color.gray);
        enterLocation.addFocusListener(this);
        
        
        
        //JLabel to display "PHONE :"
        phone = new JLabel("PHONE :");
        phone.setBounds(330,570,150,50);   
        phone.setForeground(Color.white);
        phone.setFont(bebas.deriveFont(24f));
        
        //JTextField to take input
        enterPhone = new JTextField();
        enterPhone.setBounds(460,575,280,36);   
        enterPhone.setFont(bebas.deriveFont(15f));
        enterPhone.setText("Enter phone number");
        enterPhone.setForeground(Color.gray);
        enterPhone.addFocusListener(this);
        
        //JLabel to display "GENDER :"
        gender = new JLabel("GENDER :");
        gender.setBounds(330,630,150,50);   
        gender.setForeground(Color.white);
        gender.setFont(bebas.deriveFont(24f));
        
        
        male = new JRadioButton("MALE");
        male.setBounds(460,640,100,36);   
        male.setFont(bebas.deriveFont(24f));
        male.setBackground(null);
        male.setForeground(Color.white);
        male.setFocusable(false);
        male.setSelected(true);
        
        
        
        female = new JRadioButton("FEMALE");
        female.setBounds(560,640,280,36);   
        female.setFont(bebas.deriveFont(24f));
        female.setBackground(null);
        female.setForeground(Color.white);
        female.setFocusable(false);
        
        
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        
        
        email = new JLabel("E-MAIL :");
        email.setBounds(1140,390,150,50);   
        email.setForeground(Color.white);
        email.setFont(bebas.deriveFont(24f));
        
        
        enterEmail = new JTextField();
        enterEmail.setBounds(1270,395,280,36);   
        enterEmail.setFont(bebas.deriveFont(15f));
        enterEmail.setText("Enter your e-mail");
        enterEmail.setForeground(Color.gray);
        enterEmail.addFocusListener(this);
        
        
        
        dob = new JLabel("DOB :");
        dob.setBounds(1140,450,150,50);   
        dob.setForeground(Color.white);
        dob.setFont(bebas.deriveFont(24f));
        
        
        String[] birthYears ={"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010"};
        DOBYear = new JComboBox(birthYears);
        DOBYear.setBounds(1270,455,80,36);    
        DOBYear.setFont(bebas.deriveFont(18f));
        
        
        String[] birthMonths ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        DOBMonth = new JComboBox(birthMonths);
        DOBMonth.setBounds(1370,455,50,36);
        DOBMonth.setFont(bebas.deriveFont(18f));
        
        
        String[] birthDays ={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        DOBDay = new JComboBox(birthDays);
        DOBDay.setBounds(1440,455,50,36);
        DOBDay.setFont(bebas.deriveFont(18f));
        
        
        
        
        
        startDate = new JLabel("START DATE :");
        startDate.setBounds(1140,510,150,50);   
        startDate.setForeground(Color.white);
        startDate.setFont(bebas.deriveFont(24f));
        
        
        
        
        String[] startYears ={"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        StartYear = new JComboBox(startYears);
        StartYear.setBounds(1270,515,80,36);    
        StartYear.setFont(bebas.deriveFont(18f));
        
        
        
        
        String[] startMonths ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        StartMonth = new JComboBox(startMonths);
        StartMonth.setBounds(1370,515,50,36);
        StartMonth.setFont(bebas.deriveFont(18f));
        
        
        
        
        String[] startDays ={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        StartDay = new JComboBox(startDays);
        StartDay.setBounds(1440,515,50,36);
        StartDay.setFont(bebas.deriveFont(18f));
        
        
        
        
        referal = new JLabel("<html>REFERAL<br>SOURCE :</html>");
        referal.setBounds(1140,570,150,50);   
        referal.setForeground(Color.white);
        referal.setFont(bebas.deriveFont(24f));
        
        
        
        
        
        enterReferal = new JTextField();
        enterReferal.setBounds(1270,575,280,36);   
        enterReferal.setFont(bebas.deriveFont(15f));
        enterReferal.setText("Enter referal source");
        enterReferal.setForeground(Color.gray);
        enterReferal.addFocusListener(this);
        
        
        
        
        
        
        
        clear = new JButton("CLEAR ALL");
        clear.setBounds(1470,620,100,40);   
        clear.setForeground(new Color(255,255,255,60));
        clear.setBackground(null);
        clear.setFocusable(false);
        clear.setBorder(null);
        clear.setFont(bebas.deriveFont(18f));
        clear.setContentAreaFilled(false);
        clear.addActionListener(this);
        
        
        
        discount = new JLabel("DISCOUNT :");
        discount.setBounds(330,340,150,50);   
        discount.setForeground(Color.white);
        discount.setFont(bebas.deriveFont(24f));
        discount.setVisible(false);
        
        
        availableDiscount = new JLabel("-- --");
        availableDiscount.setBounds(460,345,110,36);   
        availableDiscount.setForeground(new Color(255,255,255,80));
        availableDiscount.setFont(bebas.deriveFont(24f));
        availableDiscount.setVisible(false);
        
        
        personalTrainer = new JLabel("<html>PERSONAL<br>TRAINER :</html>");
        personalTrainer.setBounds(1140,570,150,50);   
        personalTrainer.setForeground(Color.white);
        personalTrainer.setFont(bebas.deriveFont(24f));
        personalTrainer.setVisible(false);
        
        
        enterPersonalTrainer = new JTextField();
        enterPersonalTrainer.setBounds(1270,575,280,36);   ;
        enterPersonalTrainer.setFont(bebas.deriveFont(15f));
        enterPersonalTrainer.setVisible(false);
        enterPersonalTrainer.setText("Enter trainer name");
        enterPersonalTrainer.setForeground(Color.gray);
        enterPersonalTrainer.addFocusListener(this);
        
        
        
        submit = new JButton("register");
        submit.setBounds(900,750,100,40);   
        submit.setBackground(new Color(0x65558F));
        submit.setForeground(Color.white);
        submit.setFocusable(false);
        submit.setBorder(null);
        submit.setFont(bebas.deriveFont(28f));
        submit.addActionListener(this);
        
        
        
        
        //addinf all the compnents to the homepage
        
        homePage.add(title);
        homePage.add(regular);
        homePage.add(premium);
        homePage.add(plan);
        homePage.add(Plans);
        homePage.add(price);
        homePage.add(enterPrice);
        homePage.add(id);
        homePage.add(enterId);
        homePage.add(name);
        homePage.add(enterName);
        homePage.add(location);
        homePage.add(enterLocation);
        homePage.add(phone);
        homePage.add(enterPhone);
        homePage.add(gender);
        homePage.add(male);
        homePage.add(female);
        homePage.add(email);
        homePage.add(enterEmail);
        homePage.add(dob);
        homePage.add(DOBYear);
        homePage.add(DOBMonth);
        homePage.add(DOBDay);
        homePage.add(startDate);
        homePage.add(StartYear);
        homePage.add(StartMonth);
        homePage.add(StartDay);
        homePage.add(referal);
        homePage.add(enterReferal);
        homePage.add(clear);
        homePage.add(discount);
        homePage.add(availableDiscount);
        homePage.add(personalTrainer);
        homePage.add(enterPersonalTrainer);
        homePage.add(submit);
        
        
        
        //Code for attendance page
        
        attendancePage = new JPanel();
        attendancePage.setBounds(0,0,1920, 1080);   
        attendancePage.setBackground(new Color(0x2C2638));
        attendancePage.setLayout(null);
        
        
        mark = new JLabel("<html><div style='text-align: center;'>MARK <br><span style='color: #65558F;'>ATTENDANCE</span></div></html>");
        mark.setBounds(735,50,450,170);   
        mark.setForeground(Color.white);
        mark.setFont(bebas.deriveFont(80f));
        mark.setHorizontalAlignment(mark.CENTER);
        
        
        
        
        attendanceID = new JLabel("ID :");
        attendanceID.setBounds(750,480,100,80);   
        attendanceID.setFont(bebas.deriveFont(64f));
        attendanceID.setForeground(Color.white);
        
        
        
        
        enterAttendanceID = new JTextField();
        enterAttendanceID.setBounds(870,500,280,42);   
        enterAttendanceID.setFont(bebas);
        enterAttendanceID.setText("Enter your ID");
        enterAttendanceID.setForeground(Color.gray);
        enterAttendanceID.addFocusListener(this);
        
        
        
        
        markAttendance = new JButton("MARK PRESENT");
        markAttendance.setBounds(910,700,150,40);   
        markAttendance.setBackground(new Color(0x65558F));
        markAttendance.setForeground(Color.white);
        markAttendance.setFocusable(false);
        markAttendance.setBorder(null);
        markAttendance.setFont(bebas.deriveFont(28f));
        markAttendance.addActionListener(this);

        
         
        //adding all the components to the attendance page
        attendancePage.add(mark);
        attendancePage.add(attendanceID);
        attendancePage.add(enterAttendanceID);
        attendancePage.add(markAttendance);
        
        
        
        
        //Code for activate page
        activatePage = new JPanel();
        activatePage.setBounds(0,0,1920, 1080);   
        activatePage.setBackground(new Color(0x2C2638));
        activatePage.setLayout(null);
        
        
        activateHead = new JLabel("<html><div style='text-align: center;'>ACTIVATE / <span style='color:#65558F'>DEACTIVATE</span><br>MEMBERSHIP :</div></html>");
        activateHead.setBounds(660,50,600,200);   
        activateHead.setForeground(Color.white);
        activateHead.setFont(bebas.deriveFont(80f));
        
        
        activateLable = new JLabel("Id:");
        activateLable.setBounds(810,460,200,40);   
        activateLable.setFont(bebas.deriveFont(26f));
        activateLable.setForeground(Color.white);
        
        activateID = new JTextField();
        activateID.setBounds(810,500,300,42);   
        activateID.setFont(bebas);
        activateID.setText("Enter your ID");
        activateID.setForeground(Color.gray);
        activateID.addFocusListener(this);
        
        
        
        activatebtn = new JButton("Activate membership");
        activatebtn.setFont(bebas.deriveFont(20f));
        activatebtn.setBounds(650,700,180,60);   
        activatebtn.setFocusable(false);
        activatebtn.setBackground(new Color(0x65558F));
        activatebtn.setForeground(Color.white);
        activatebtn.setBorder(null);
        activatebtn.addActionListener(this);
        
        
        deactivatebtn = new JButton("Deactivate Membership");
        deactivatebtn.setFont(bebas.deriveFont(20f));
        deactivatebtn.setBounds(1080,700,180,60);   
        deactivatebtn.setFocusable(false);
        deactivatebtn.setBackground(new Color(0x65558F));
        deactivatebtn.setForeground(Color.white);
        deactivatebtn.setBorder(null);
        deactivatebtn.addActionListener(this);
        
        
        //adding all the components
        activatePage.add(activateHead);
        activatePage.add(activateLable);
        activatePage.add(activateID);
        activatePage.add(activatebtn);
        activatePage.add(deactivatebtn);
        
        
        
        
        
        //Code for upgrade page
        upgradePage = new JPanel();
        upgradePage.setBounds(0,0,1920, 1080);   
        upgradePage.setBackground(new Color(0x2C2638));
        upgradePage.setLayout(null);
        
        

        upgradeTitle = new JLabel("<html><span style='color:#65558F'>UP</span>GRADE PLAN</html>");
        upgradeTitle.setBounds(780,50,400,150);
        upgradeTitle.setForeground(Color.white);
        upgradeTitle.setFont(bebas.deriveFont(80f));
        
        
        
        upgradePlan = new JLabel("Select a plan:");
        upgradePlan.setBounds(730,350,400,50);
        upgradePlan.setForeground(Color.white);
        upgradePlan.setFont(bebas.deriveFont(50f));
        
        
        
        String [] allPlans = {"BASIC","STANDARD","DELUXE"};
        
        selectPlan = new JComboBox(allPlans);
        selectPlan.setBounds(1030,345,160,60);
        selectPlan.setFont(bebas.deriveFont(40f));
        selectPlan.setSelectedIndex(0);
        preSelected = selectPlan.getSelectedIndex();
        
        
        
        enterUpgradeId = new JLabel("Id:");
        enterUpgradeId.setBounds(810,500,200,40);   
        enterUpgradeId.setFont(bebas.deriveFont(26f));
        enterUpgradeId.setForeground(Color.white);
        
        upgradeID = new JTextField();
        upgradeID.setBounds(810,540,300,42);   
        upgradeID.setFont(bebas);
        upgradeID.setText("Enter your ID");
        upgradeID.setForeground(Color.gray);
        upgradeID.addFocusListener(this);
        
        
        
        upgradebtn = new JButton("Request upgrade");
        upgradebtn.setBounds(860,700,200,50);   
        upgradebtn.setBackground(new Color(0x65558F));
        upgradebtn.setForeground(Color.white);
        upgradebtn.setFocusable(false);
        upgradebtn.setBorder(null);
        upgradebtn.setFont(bebas.deriveFont(28f));
        upgradebtn.addActionListener(this);
        
        
        //Adding all the necessary components
        upgradePage.add(upgradeTitle);
        upgradePage.add(upgradePlan);
        upgradePage.add(selectPlan);
        upgradePage.add(enterUpgradeId);
        upgradePage.add(upgradeID);
        upgradePage.add(upgradebtn);
        
        
        
        //Code for discount page
        discountPage = new JPanel();
        discountPage.setBounds(0,0,1920, 1080);   
        discountPage.setBackground(new Color(0x2C2638));
        discountPage.setLayout(null);
        
        
        discountTitle = new JLabel("<html><div style='text-align:center;'>Check for<br><span style='color:#65558F;'>discounts</span></div></html>");
        discountTitle.setBounds(820,50,300,200);   
        discountTitle.setForeground(Color.white);
        discountTitle.setFont(bebas.deriveFont(80f));
        
        
        discountID = new JLabel("id:");
        discountID.setBounds(750,480,100,80);   
        discountID.setFont(bebas.deriveFont(64f));
        discountID.setForeground(Color.white);
        
        
        
        
        enterDiscountID = new JTextField();
        enterDiscountID.setBounds(870,500,280,42);   
        enterDiscountID.setFont(bebas);
        enterDiscountID.setText("Enter your ID");
        enterDiscountID.setForeground(Color.gray);
        enterDiscountID.addFocusListener(this);
        
        
        
        
        discountbtn = new JButton("Check for discounts");
        discountbtn.setFont(bebas.deriveFont(20f));
        discountbtn.setBounds(870,700,180,80);   
        discountbtn.setFocusable(false);
        discountbtn.setBackground(new Color(0x65558F));
        discountbtn.setForeground(Color.white);
        discountbtn.setBorder(null);
        discountbtn.addActionListener(this);
        
        
        
        //adding discount page
        discountPage.add(discountTitle);
        discountPage.add(discountID);
        discountPage.add(enterDiscountID);
        discountPage.add(discountbtn);
        
        
        
        //code for payPage
        payPage = new JPanel();
        payPage.setBounds(0,0,1920, 1080);   
        payPage.setBackground(new Color(0x2C2638));
        payPage.setLayout(null);
        
        payTitle = new JLabel("<html><div style='text-align:center;'>Pay <span style='color:#65558F;'>due</span><br>amount</div></html>");
        payTitle.setBounds(850,50,300,200);   
        payTitle.setForeground(Color.white);
        payTitle.setFont(bebas.deriveFont(80f));
        
        payID = new JLabel("id:");
        payID.setBounds(750,410,100,80);   
        payID.setFont(bebas.deriveFont(64f));
        payID.setForeground(Color.white);
        
        
        
        
        enterPayID = new JTextField();
        enterPayID.setBounds(870,430,280,42);   
        enterPayID.setFont(bebas);
        enterPayID.setText("Enter your ID");
        enterPayID.setForeground(Color.gray);
        enterPayID.addFocusListener(this);
        
        
        payDue = new JLabel("due:");
        payDue.setBounds(750,550,100,80);   
        payDue.setFont(bebas.deriveFont(64f));
        payDue.setForeground(Color.white);
        
        
        
        
        enterDue = new JTextField();
        enterDue.setBounds(870,570,280,42);   
        enterDue.setFont(bebas);
        enterDue.setText("Enter amount");
        enterDue.setForeground(Color.gray);
        enterDue.addFocusListener(this);
        
        
        
        
        
        
        payBtn = new JButton("pay due");
        payBtn.setFont(bebas.deriveFont(20f));
        payBtn.setBounds(940,800,100,60);   
        payBtn.setFocusable(false);
        payBtn.setBackground(new Color(0x65558F));
        payBtn.setForeground(Color.white);
        payBtn.setBorder(null);
        payBtn.addActionListener(this);
        
        
        
        //adding all the components
        payPage.add(payTitle);
        payPage.add(payID);
        payPage.add(enterPayID);
        payPage.add(payDue);
        payPage.add(enterDue);
        payPage.add(payBtn);
        
        
        
        
        
        //Code for revertPage
        revertPage = new JPanel();
        revertPage.setBounds(0,0,1920, 1080);   
        revertPage.setBackground(new Color(0x2C2638));
        revertPage.setLayout(null);
        
        
        
        
        
        revertTitle = new JLabel("<html><span style='color:#65558F;'>Revert</span> membership</html>");
        revertTitle.setBounds(700,50,550,120);   
        revertTitle.setForeground(Color.white);
        revertTitle.setFont(bebas.deriveFont(80f));
        
        
        
        
        
        revertID = new JLabel("Id:");
        revertID.setBounds(810,430,200,40);   
        revertID.setFont(bebas.deriveFont(26f));
        revertID.setForeground(Color.white);
        
        enterRevertID = new JTextField();
        enterRevertID.setBounds(810,470,300,42);   
        enterRevertID.setFont(bebas);
        enterRevertID.setText("Enter your ID");
        enterRevertID.setForeground(Color.gray);
        enterRevertID.addFocusListener(this);
        
        
        
        regularRevertbtn = new JButton("revert regular member");
        regularRevertbtn.setFont(bebas.deriveFont(20f));
        regularRevertbtn.setBounds(650,700,180,60);   
        regularRevertbtn.setFocusable(false);
        regularRevertbtn.setBackground(new Color(0x65558F));
        regularRevertbtn.setForeground(Color.white);
        regularRevertbtn.setBorder(null);
        regularRevertbtn.addActionListener(this);
        
        
        premiumRevertbtn = new JButton("revert premium member");
        premiumRevertbtn.setFont(bebas.deriveFont(20f));
        premiumRevertbtn.setBounds(1080,700,180,60);   
        premiumRevertbtn.setFocusable(false);
        premiumRevertbtn.setBackground(new Color(0x65558F));
        premiumRevertbtn.setForeground(Color.white);
        premiumRevertbtn.setBorder(null);
        premiumRevertbtn.addActionListener(this);
        
        
        
        
        
        //Adding all the components
        revertPage.add(revertTitle);
        revertPage.add(revertID);
        revertPage.add(enterRevertID);
        revertPage.add(regularRevertbtn);
        revertPage.add(premiumRevertbtn);
        
        
        
        
        //Code for display page
        displayPanel = new JPanel();
        displayPanel.setBounds(0,0,1920, 1080);   
        displayPanel.setBackground(new Color(0x2C2638));
        displayPanel.setLayout(null);
        
        
        
        displayRegular = new JButton("Display Regular Members");
        displayRegular.setFont(bebas.deriveFont(20f));
        displayRegular.setBounds(450,180,180,60);   
        displayRegular.setFocusable(false);
        displayRegular.setBackground(new Color(0x65558F));
        displayRegular.setForeground(Color.white);
        displayRegular.setBorder(null);
        displayRegular.addActionListener(this);
        
        
        
        displayPremium = new JButton("Display Premium Members");
        displayPremium.setFont(bebas.deriveFont(20f));
        displayPremium.setBounds(1300,180,180,60);   
        displayPremium.setFocusable(false);
        displayPremium.setBackground(new Color(0x65558F));
        displayPremium.setForeground(Color.white);
        displayPremium.setBorder(null);
        displayPremium.addActionListener(this);
        
        
        //Panel that is bound to display the info of the members
        dispInfo = new JPanel();
        dispInfo.setPreferredSize(new Dimension(1920,1080));
        dispInfo.setBackground(new Color(0x2C2638));  //setting background colour
        dispInfo.setLayout(null);  //removing the default flow layout
        
        
        
        
        back2 = new JButton();
        back2.setFont(bebas.deriveFont(26f));
        back2.setIcon(backIcon);
        back2.setBounds(35,60,100,80);
        back2.setFocusable(false);//removes a focused area off the button text
        back2.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        back2.setBorder(null);//disables the border of the button
        back2.setForeground(Color.white);
        back2.setFont(bebas.deriveFont(35f));
        back2.addActionListener(this);
        
        
        displayHead = new JLabel("");
        displayHead.setBounds(760,80,400,50);
        displayHead.setForeground(Color.white);
        displayHead.setFont(bebas.deriveFont(60f));
        
        //adding the necessary components
        dispInfo.add(displayHead);
        dispInfo.add(back2);
        
        
        //creating a JScrollPane in order to fit all the information of members
        scroll = new JScrollPane(dispInfo);
        scroll.setBounds(0,0,1920,1080);
        scroll.getVerticalScrollBar().setUnitIncrement(16);//Makes scrolling through the content feel smooth moving 16px per scroll
        scroll.setVisible(false);
        
        
        //adding all the components
        displayPanel.add(displayRegular);
        displayPanel.add(displayPremium);
        displayPanel.add(scroll);
        
        
        fileDisplayPannel = new JPanel();
        fileDisplayPannel.setBounds(0,0,1920,1080);
        fileDisplayPannel.setBackground(new Color(0x2C2638));
        fileDisplayPannel.setLayout(null);
        
        fileLable = new JLabel("Member List");
        fileLable.setFont(bebas.deriveFont(80f));
        fileLable.setForeground(Color.white);
        fileLable.setBounds(735,20,350,70);
        
        fileDisplay = new JTextArea("");
        fileDisplay.setBounds(30,180,1920,1080);
        fileDisplay.setOpaque(false);
        fileDisplay.setEditable(false);
        fileDisplay.setForeground(Color.white);
        /*
         * using monospaced font so that the format specified doesnt get disrupted
           due to the different lengths and spaces of data
           */
        fileDisplay.setFont(new Font("Monospaced",Font.PLAIN,12));
        
        
        
        returnBtn = new JButton();
        returnBtn.setIcon(backIcon);
        returnBtn.setBounds(20,20,100,80);
        returnBtn.setFocusable(false);//removes a focused area off the button text
        returnBtn.setContentAreaFilled(false);//removes the background of the butto *doesnt make it transparent*
        returnBtn.setBorder(null);//disables the border of the button
        returnBtn.setForeground(Color.white);
        returnBtn.setFont(bebas.deriveFont(35f));
        returnBtn.addActionListener(this);
        
        fileDisplayPannel.add(fileDisplay);
        fileDisplayPannel.add(returnBtn);
        fileDisplayPannel.add(fileLable);
        fileDisplayPannel.setVisible(false);
        
        
        
        //Disabling all the unnecessary button so they are not clickable from the Landing page
        submit.setEnabled(true);
        markAttendance.setEnabled(false);
        activatebtn.setEnabled(false);
        deactivatebtn.setEnabled(false);
        upgradebtn.setEnabled(false);
        discountbtn.setEnabled(false);
        payBtn.setEnabled(false);
        premiumRevertbtn.setEnabled(false);
        regularRevertbtn.setEnabled(false);

        
        
        
        //Creating a new layered pane for transitions between layers
        layers= new JLayeredPane();
        layers.setBounds(0,0,1920,1080);   
        
        //Adding all the pages / nav bar
        layers.add(homePage,Integer.valueOf(2));
        layers.add(attendancePage,Integer.valueOf(1));
        layers.add(activatePage,Integer.valueOf(1));
        layers.add(upgradePage,Integer.valueOf(1));
        layers.add(discountPage,Integer.valueOf(1));
        layers.add(payPage,Integer.valueOf(1));
        layers.add(displayPanel,Integer.valueOf(1));
        layers.add(revertPage,Integer.valueOf(1));
        layers.add(navBar,Integer.valueOf(3));
        layers.add(menu,Integer.valueOf(3));
        layers.add(scroll,Integer.valueOf(4));
        layers.add(fileDisplayPannel, Integer.valueOf(1));
        
        //adding the layered pane to the main frame
        mainFrame.add(layers);
        mainFrame.setVisible(true);
    }
    
    //Event handling as per the used action listeners above
    @Override
        public void actionPerformed (ActionEvent e)
        {
            if(e.getSource()==back2)//Toogles the scroll JScrollPane invisible , making it look like a back feature
            {
                scroll.setVisible(false);
                //removes all the components added to the dispInfo pannel for dynamic data display
                dispInfo.removeAll(); 
            }
            
            
            //Displays info of all the added regular members
            if(e.getSource()==displayRegular)
            {
                boolean found=false;
                boolean empty=true;
                //runs if the arraylist is empty
                if(members.isEmpty())
                {
                    JOptionPane.showMessageDialog(activatePage,
                    "Looks like there are no members yet.\nWhy not register a few to begin?",
                    "Empty member list",
                    JOptionPane.WARNING_MESSAGE);
                    empty=false;
                }
                else
                {
                    displayHead.setText("regular members");
                    int j=160,y=0;
                    for(GymMember each:members)
                    {
                        if(each instanceof RegularMember)//checking for regular member objects
                        {
                            
                            JPanel data = new JPanel();
                            data.setBounds(0,j,1920,1000);
                            data.setBackground(new Color(0x2C2638));  //setting background colour
                            data.setLayout(null);  //removing the default flow layout
                            
                            JLabel id = new JLabel("1. ID :");
                            id.setBounds(800,50,150,50);
                            id.setForeground(Color.white);
                            id.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel idValue = new JLabel();
                            idValue.setBounds(1050,50,150,50);
                            idValue.setForeground(Color.white);
                            idValue.setFont(bebas.deriveFont(30f));
                            idValue.setText(String.valueOf(each.getId()));
                            
                            
                            
                            
                            JLabel name = new JLabel("2. Name :");
                            name.setBounds(800,100,150,50);
                            name.setForeground(Color.white);
                            name.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel nameValue = new JLabel();
                            nameValue.setBounds(1050,100,150,50);
                            nameValue.setForeground(Color.white);
                            nameValue.setFont(bebas.deriveFont(30f));
                            nameValue.setText(each.getName());
                            
                            
                            
                            
                            JLabel location = new JLabel("3. Location :");
                            location.setBounds(800,150,150,50);
                            location.setForeground(Color.white);
                            location.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel locationValue = new JLabel();
                            locationValue.setBounds(1050,150,150,50);
                            locationValue.setForeground(Color.white);
                            locationValue.setFont(bebas.deriveFont(30f));
                            locationValue.setText(each.getLocation());
                            
                            
                            
                            
                            
                            JLabel phone = new JLabel("4. Phone :");
                            phone.setBounds(800,200,150,50);
                            phone.setForeground(Color.white);
                            phone.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel phoneValue = new JLabel();
                            phoneValue.setBounds(1050,200,150,50);
                            phoneValue.setForeground(Color.white);
                            phoneValue.setFont(bebas.deriveFont(30f));
                            phoneValue.setText(each.getPhone());
                            
                            
                            
                            
                            JLabel email = new JLabel("5. E-mail :");
                            email.setBounds(800,250,150,50);
                            email.setForeground(Color.white);
                            email.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel emailValue = new JLabel();
                            emailValue.setBounds(1050,250,150,50);
                            emailValue.setForeground(Color.white);
                            emailValue.setFont(bebas.deriveFont(30f));
                            emailValue.setText(each.getEmail());
                            
                            
                            
                            
                            JLabel gender = new JLabel("6. Gender :");
                            gender.setBounds(800,300,150,50);
                            gender.setForeground(Color.white);
                            gender.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel genderValue = new JLabel();
                            genderValue.setBounds(1050,300,150,50);
                            genderValue.setForeground(Color.white);
                            genderValue.setFont(bebas.deriveFont(30f));
                            genderValue.setText(each.getGender());
                            
                            
                            
                            
                            JLabel dob = new JLabel("7. DOB :");
                            dob.setBounds(800,350,150,50);
                            dob.setForeground(Color.white);
                            dob.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel dobValue = new JLabel();
                            dobValue.setBounds(1050,350,150,50);
                            dobValue.setForeground(Color.white);
                            dobValue.setFont(bebas.deriveFont(30f));
                            dobValue.setText(each.getDOB());
                            
                            
                            
                            
                            JLabel member = new JLabel("8. Start date :");
                            member.setBounds(800,400,200,50);
                            member.setForeground(Color.white);
                            member.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel memberValue = new JLabel();
                            memberValue.setBounds(1050,400,150,50);
                            memberValue.setForeground(Color.white);
                            memberValue.setFont(bebas.deriveFont(30f));
                            memberValue.setText(each.getMembershipStartDate());
                            
                            
                            JLabel attendance = new JLabel("9. Attendance :");
                            attendance.setBounds(800,450,200,50);
                            attendance.setForeground(Color.white);
                            attendance.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel attendanceValue = new JLabel();
                            attendanceValue.setBounds(1050,450,150,50);
                            attendanceValue.setForeground(Color.white);
                            attendanceValue.setFont(bebas.deriveFont(30f));
                            attendanceValue.setText(String.valueOf(each.getAttendance()));
                            
                            JLabel loyalty = new JLabel("10. Loyalty Points :");
                            loyalty.setBounds(800,500,200,50);
                            loyalty.setForeground(Color.white);
                            loyalty.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel loyaltyValue = new JLabel();
                            loyaltyValue.setBounds(1050,500,150,50);
                            loyaltyValue.setForeground(Color.white);
                            loyaltyValue.setFont(bebas.deriveFont(30f));
                            loyaltyValue.setText(String.valueOf(each.getLoyaltyPoints()));
                            
                            JLabel active = new JLabel("11. Active :");
                            active.setBounds(800,550,200,50);
                            active.setForeground(Color.white);
                            active.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel activeValue = new JLabel();
                            activeValue.setBounds(1050,550,150,50);
                            activeValue.setForeground(Color.white);
                            activeValue.setFont(bebas.deriveFont(30f));
                            activeValue.setText(String.valueOf(each.getActiveStatus()));
                            
                            
                            //type casing the GymMember object to regular member in order to gain acess of all it methods
                            RegularMember regObj = (RegularMember) each;
                            
                            JLabel referal = new JLabel("12. Referal Source :");
                            referal.setBounds(800,600,220,50);
                            referal.setForeground(Color.white);
                            referal.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel referalValue = new JLabel();
                            referalValue.setBounds(1050,600,550,50);
                            referalValue.setForeground(Color.white);
                            referalValue.setFont(bebas.deriveFont(30f));
                            referalValue.setText(regObj.getReferralSource());
                            
                            
                            JLabel plan = new JLabel("13. Plan :");
                            plan.setBounds(800,650,220,50);
                            plan.setForeground(Color.white);
                            plan.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel planValue = new JLabel();
                            planValue.setBounds(1050,650,550,50);
                            planValue.setForeground(Color.white);
                            planValue.setFont(bebas.deriveFont(30f));
                            planValue.setText(regObj.getPlan());
                            
                            
                            JLabel price = new JLabel("14. Price :");
                            price.setBounds(800,700,220,50);
                            price.setForeground(Color.white);
                            price.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel priceValue = new JLabel();
                            priceValue.setBounds(1050,700,550,50);
                            priceValue.setForeground(Color.white);
                            priceValue.setFont(bebas.deriveFont(30f));
                            priceValue.setText(String.valueOf(regObj.getPrice()));
                            
                            
                            //displays if the removal reason is not empty
                            if(!regObj.getRemovalReason().equals(""))
                            {
                                JLabel remove = new JLabel("15. Removal reason :");
                                remove.setBounds(800,750,220,50);
                                remove.setForeground(Color.white);
                                remove.setFont(bebas.deriveFont(30f));
                            
                            
                                JLabel removeValue = new JLabel();
                                removeValue.setBounds(1050,750,550,50);
                                removeValue.setForeground(Color.white);
                                removeValue.setFont(bebas.deriveFont(30f));
                                removeValue.setText(regObj.getRemovalReason());
                                
                                data.add(remove);
                                data.add(removeValue);
                            }
                            
                            //ading all the components
                            data.add(id);
                            data.add(idValue);
                            data.add(name);
                            data.add(nameValue);
                            data.add(location);
                            data.add(locationValue);
                            data.add(phone);
                            data.add(phoneValue);
                            data.add(email);
                            data.add(emailValue);
                            data.add(gender);
                            data.add(genderValue);
                            data.add(dob);
                            data.add(dobValue);
                            data.add(member);
                            data.add(memberValue);
                            data.add(attendance);
                            data.add(attendanceValue);
                            data.add(loyalty);
                            data.add(loyaltyValue);
                            data.add(active);
                            data.add(activeValue);
                            data.add(referal);
                            data.add(referalValue);
                            data.add(plan);
                            data.add(planValue);
                            data.add(price);
                            data.add(priceValue);
                            
                            
                            //Adding back the heading and back button
                            dispInfo.add(displayHead);
                            dispInfo.add(back2);
                            
                            //adding the pannel containing all the components
                            dispInfo.add(data);
                            
                            //increasing the value of j and y in order to match for the number of members
                            j+=1000;
                            y+=1000;
                            //setting the new prefered size of the pane which is dynamic as per the number of members
                            dispInfo.setPreferredSize(new Dimension(1920,y));
                            found=true;
                        }
                    }
                    //code runs only if the array list isnt event
                    if(empty)
                    {
                        //runs only if regular member objects are presen in the array list
                        if(found)
                        {
                        scroll.setVisible(true);
                        }
                        //message to be displayed if no objects of regular member are found
                        else
                        {
                            JOptionPane.showMessageDialog(displayPanel,
                            "There are no regular members registered",
                            "No regular members found",
                            JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            
            
            //Displays indo of all the added Premium Members
            if(e.getSource()==displayPremium)
            {
                boolean found=false;
                boolean empty=true;
                //runs if the arraylist is empty
                if(members.isEmpty())
                {
                    JOptionPane.showMessageDialog(activatePage,
                    "Looks like there are no members yet.\nWhy not register a few to begin?",
                    "Empty member list",
                    JOptionPane.WARNING_MESSAGE);
                    empty = false;
                }
                else
                {
                    displayHead.setText("premium members");
                    int j=160,y=0;
                    for(GymMember each:members)
                    {
                        if(each instanceof PremiumMember)//checking for objects of premium members
                        {
                            
                            JPanel data = new JPanel();
                            data.setBounds(0,j,1920,1000);
                            data.setBackground(new Color(0x2C2638));  //setting background colour
                            data.setLayout(null);  //removing the default flow layout
                            
                            JLabel id = new JLabel("1. ID :");
                            id.setBounds(800,50,150,50);
                            id.setForeground(Color.white);
                            id.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel idValue = new JLabel();
                            idValue.setBounds(1050,50,150,50);
                            idValue.setForeground(Color.white);
                            idValue.setFont(bebas.deriveFont(30f));
                            idValue.setText(String.valueOf(each.getId()));
                            
                            
                            
                            
                            JLabel name = new JLabel("2. Name :");
                            name.setBounds(800,100,150,50);
                            name.setForeground(Color.white);
                            name.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel nameValue = new JLabel();
                            nameValue.setBounds(1050,100,150,50);
                            nameValue.setForeground(Color.white);
                            nameValue.setFont(bebas.deriveFont(30f));
                            nameValue.setText(each.getName());
                            
                            
                            
                            
                            JLabel location = new JLabel("3. Location :");
                            location.setBounds(800,150,150,50);
                            location.setForeground(Color.white);
                            location.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel locationValue = new JLabel();
                            locationValue.setBounds(1050,150,150,50);
                            locationValue.setForeground(Color.white);
                            locationValue.setFont(bebas.deriveFont(30f));
                            locationValue.setText(each.getLocation());
                            
                            
                            
                            
                            
                            JLabel phone = new JLabel("4. Phone :");
                            phone.setBounds(800,200,150,50);
                            phone.setForeground(Color.white);
                            phone.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel phoneValue = new JLabel();
                            phoneValue.setBounds(1050,200,150,50);
                            phoneValue.setForeground(Color.white);
                            phoneValue.setFont(bebas.deriveFont(30f));
                            phoneValue.setText(each.getPhone());
                            
                            
                            
                            
                            JLabel email = new JLabel("5. E-mail :");
                            email.setBounds(800,250,150,50);
                            email.setForeground(Color.white);
                            email.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel emailValue = new JLabel();
                            emailValue.setBounds(1050,250,150,50);
                            emailValue.setForeground(Color.white);
                            emailValue.setFont(bebas.deriveFont(30f));
                            emailValue.setText(each.getEmail());
                            
                            
                            
                            
                            JLabel gender = new JLabel("6. Gender :");
                            gender.setBounds(800,300,150,50);
                            gender.setForeground(Color.white);
                            gender.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel genderValue = new JLabel();
                            genderValue.setBounds(1050,300,150,50);
                            genderValue.setForeground(Color.white);
                            genderValue.setFont(bebas.deriveFont(30f));
                            genderValue.setText(each.getGender());
                            
                            
                            
                            
                            JLabel dob = new JLabel("7. DOB :");
                            dob.setBounds(800,350,150,50);
                            dob.setForeground(Color.white);
                            dob.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel dobValue = new JLabel();
                            dobValue.setBounds(1050,350,150,50);
                            dobValue.setForeground(Color.white);
                            dobValue.setFont(bebas.deriveFont(30f));
                            dobValue.setText(each.getDOB());
                            
                            
                            
                            
                            JLabel member = new JLabel("8. Start date :");
                            member.setBounds(800,400,200,50);
                            member.setForeground(Color.white);
                            member.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel memberValue = new JLabel();
                            memberValue.setBounds(1050,400,150,50);
                            memberValue.setForeground(Color.white);
                            memberValue.setFont(bebas.deriveFont(30f));
                            memberValue.setText(each.getMembershipStartDate());
                            
                            
                            JLabel attendance = new JLabel("9. Attendance :");
                            attendance.setBounds(800,450,200,50);
                            attendance.setForeground(Color.white);
                            attendance.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel attendanceValue = new JLabel();
                            attendanceValue.setBounds(1050,450,150,50);
                            attendanceValue.setForeground(Color.white);
                            attendanceValue.setFont(bebas.deriveFont(30f));
                            attendanceValue.setText(String.valueOf(each.getAttendance()));
                            
                            JLabel loyalty = new JLabel("10. Loyalty Points :");
                            loyalty.setBounds(800,500,200,50);
                            loyalty.setForeground(Color.white);
                            loyalty.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel loyaltyValue = new JLabel();
                            loyaltyValue.setBounds(1050,500,150,50);
                            loyaltyValue.setForeground(Color.white);
                            loyaltyValue.setFont(bebas.deriveFont(30f));
                            loyaltyValue.setText(String.valueOf(each.getLoyaltyPoints()));
                            
                            JLabel active = new JLabel("11. Active :");
                            active.setBounds(800,550,200,50);
                            active.setForeground(Color.white);
                            active.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel activeValue = new JLabel();
                            activeValue.setBounds(1050,550,150,50);
                            activeValue.setForeground(Color.white);
                            activeValue.setFont(bebas.deriveFont(30f));
                            activeValue.setText(String.valueOf(each.getActiveStatus()));
                            
                            //Type casting GymMember object to premium member in order to gain acess of all its method
                            PremiumMember preObj = (PremiumMember) each;
                            
                            
                            JLabel trainer = new JLabel("12. Personal trainer :");
                            trainer.setBounds(800,600,240,50);
                            trainer.setForeground(Color.white);
                            trainer.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel trainerValue = new JLabel();
                            trainerValue.setBounds(1050,600,150,50);
                            trainerValue.setForeground(Color.white);
                            trainerValue.setFont(bebas.deriveFont(30f));
                            trainerValue.setText(preObj.getPersonalTrainer());
                            
                            JLabel paid = new JLabel("13. Paid amount :");
                            paid.setBounds(800,650,240,50);
                            paid.setForeground(Color.white);
                            paid.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel paidValue = new JLabel();
                            paidValue.setBounds(1050,650,150,50);
                            paidValue.setForeground(Color.white);
                            paidValue.setFont(bebas.deriveFont(30f));
                            paidValue.setText(String.valueOf(preObj.getPaidAmount()));
                            
                            JLabel full = new JLabel("14. Full payment :");
                            full.setBounds(800,700,240,50);
                            full.setForeground(Color.white);
                            full.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel fullValue = new JLabel();
                            fullValue.setBounds(1050,700,150,50);
                            fullValue.setForeground(Color.white);
                            fullValue.setFont(bebas.deriveFont(30f));
                            fullValue.setText(String.valueOf(preObj.getIsFullPayment()));
                            
                            
                            
                            JLabel remain = new JLabel("15. Remaining amount :");
                            remain.setBounds(800,750,240,50);
                            remain.setForeground(Color.white);
                            remain.setFont(bebas.deriveFont(30f));
                            
                            
                            JLabel remainValue = new JLabel();
                            remainValue.setBounds(1050,750,150,50);
                            remainValue.setForeground(Color.white);
                            remainValue.setFont(bebas.deriveFont(30f));
                            if(preObj.getIsFullPayment())//is only displayed if all the fees have been cleared
                            {
                                remainValue.setText(String.valueOf(0.0));
                                JLabel discount = new JLabel("14. Discount amount :");
                                discount.setBounds(800,800,240,50);
                                discount.setForeground(Color.white);
                                discount.setFont(bebas.deriveFont(30f));
                                
                                
                                JLabel discountValue = new JLabel();
                                discountValue.setBounds(1050,800,150,50);
                                discountValue.setForeground(Color.white);
                                discountValue.setFont(bebas.deriveFont(30f));
                                discountValue.setText(String.valueOf(preObj.getDiscountAmount()));
                                
                                data.add(discount);
                                data.add(discountValue);
                            }
                            else
                            {
                                remainValue.setText(String.valueOf((preObj.getPremiumCharge()-preObj.getPaidAmount())));
                            }
                            
                            
                            
                            
                            //adding all the components
                            data.add(id);
                            data.add(idValue);
                            data.add(name);
                            data.add(nameValue);
                            data.add(location);
                            data.add(locationValue);
                            data.add(phone);
                            data.add(phoneValue);
                            data.add(email);
                            data.add(emailValue);
                            data.add(gender);
                            data.add(genderValue);
                            data.add(dob);
                            data.add(dobValue);
                            data.add(member);
                            data.add(memberValue);
                            data.add(attendance);
                            data.add(attendanceValue);
                            data.add(loyalty);
                            data.add(loyaltyValue);
                            data.add(active);
                            data.add(activeValue);
                            data.add(trainer);
                            data.add(trainerValue);
                            data.add(paid);
                            data.add(paidValue);
                            data.add(full);
                            data.add(fullValue);
                            data.add(remain);
                            data.add(remainValue);
                            
                            
                            //Re-adding the headder / back button
                            dispInfo.add(displayHead);
                            dispInfo.add(back2);
                            dispInfo.add(data);
                            
                            //increasing y and j so the screen size and spacing is dynamic as per the number of members
                            j+=1000;
                            y+=1000;
                            
                            //setting new size of ht panel
                            dispInfo.setPreferredSize(new Dimension(1920,y));
                            found=true;
                        }
                    }
                    //runs only is the arraylist ins't empty
                    if(empty)
                    {
                        //runs only if a premium member object is found
                        if(found)
                        {
                        scroll.setVisible(true);
                        }
                        //message to be displayed if no objects of premium member are found
                        else
                        {
                            JOptionPane.showMessageDialog(displayPanel,
                            "There are no premium members registered",
                            "No premium members found",
                            JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                }
            }
            
            /*
             * Specifies that regular member should added when this button is pressed
             * hides all the unecessary premium member fields
             */
            if(e.getSource()==regular)
            {
                premium.setForeground(Color.white);
                regular.setForeground(new Color(0x65558F));
                
                
                plan.setVisible(true);
                Plans.setVisible(true);
                referal.setVisible(true);
                enterReferal.setVisible(true);
                discount.setVisible(false);
                availableDiscount.setVisible(false);
                personalTrainer.setVisible(false);
                enterPersonalTrainer.setVisible(false);
                
                price.setBounds(330,340,150,50);
                enterPrice.setBounds(460,345,110,36);
                Plans.setSelectedIndex(0);
            }
            
            
            /*Specidies that premium member should be added when the button is pressed
              hides all the unecessary regular member fields
             */ 
             if(e.getSource()==premium)
            {
                regular.setForeground(Color.white);
                premium.setForeground(new Color(0x65558F));

                
                plan.setVisible(false);
                Plans.setVisible(false);
                referal.setVisible(false);
                enterReferal.setVisible(false);
                discount.setVisible(true);
                availableDiscount.setVisible(true);
                personalTrainer.setVisible(true);
                enterPersonalTrainer.setVisible(true);
                
                enterPrice.setText("50000");
                enterPrice.setBounds(460,295,110,36);
                
                price.setBounds(330,290,150,50);
            }
            
            
            //Clears all text fields and sets the default place holders , selects default options for Comboboxes and radio buttons
            if(e.getSource()==clear)
            {
                
                enterId.setText("Enter your ID");
                enterId.setForeground(Color.gray);
                
                enterName.setText("Enter your name");
                enterName.setForeground(Color.gray);
                
                enterLocation.setText("Enter location");
                enterLocation.setForeground(Color.gray);
                
                enterPhone.setText("Enter phone number");
                enterPhone.setForeground(Color.gray);
                
                enterEmail.setText("Enter your e-mail");
                enterEmail.setForeground(Color.gray);
                
                if(regular.getForeground().equals(new Color(0x65558F)))
                {
                    Plans.setSelectedIndex(0);
                }
                
                DOBYear.setSelectedIndex(0);
                DOBMonth.setSelectedIndex(0);
                DOBDay.setSelectedIndex(0);
                
                StartYear.setSelectedIndex(0);
                StartMonth.setSelectedIndex(0);
                StartDay.setSelectedIndex(0);
                
                male.setSelected(true);
                
                enterReferal.setText("Enter referal source");
                enterReferal.setForeground(Color.gray);
                
                
                enterPersonalTrainer.setText("Enter trainer name");
                enterPersonalTrainer.setForeground(Color.gray);
            }
            
            
            //Displays price accoring to the plan selected from the combo box
            if(e.getSource()==Plans)
            {
                if(Plans.getSelectedItem()=="BASIC")
                {
                    enterPrice.setText("6500");
                }
                else if(Plans.getSelectedItem()=="STANDARD")
                {
                    enterPrice.setText("12500");
                }
                else
                {
                    enterPrice.setText("18500");
                }
            }
            
            
            //Used to add objects of regular and premium members to the array list
            if(e.getSource()==submit)//Runs when the register button is clicked
            {
                //Main if condition to check whether premium member or regular member is being added 
                /* if the regular member button is purple regular member part of code executes 
                   else the premium member part of the code runs*/
                if(regular.getForeground().equals(new Color(0x65558F)))
                {
                    //If statement to make sure all the necesarry text fields are filled before submission
                    if(enterId.getText().equals("Enter your ID")||
                    enterName.getText().equals("Enter your name")||
                    enterLocation.getText().equals("Enter location")||
                    enterPhone.getText().equals("Enter phone number")||
                    enterEmail.getText().equals("Enter your e-mail")||
                    enterReferal.getText().equals("Enter referal source")||
                    enterId.getText().equals("") || 
                    enterName.getText().equals("") || 
                    enterLocation.getText().equals("") || 
                    enterPhone.getText().equals("") || 
                    enterEmail.getText().equals("") || 
                    enterReferal.getText().equals(""))
                    {
                        //message to be displayed if any textField is found to be empty
                        JOptionPane.showMessageDialog(homePage,"Please fill all the necesarry text fields!!!","Incomplete fill up",JOptionPane.WARNING_MESSAGE);
                    }
                    //moves on if all the fields are filled properly
                    else
                    {
                        //retrives and concatinates the value from the JComboBox
                        String birthDate= DOBYear.getSelectedItem()+"-"+DOBMonth.getSelectedItem()+"-"+DOBDay.getSelectedItem();
                        
                        //retrives and concatinates the value from the JComboBox
                        String startDate=StartYear.getSelectedItem()+"-"+StartMonth.getSelectedItem()+"-"+StartDay.getSelectedItem();
                        
                        //Variable to figure out which Radio button is selected
                        String selectedGender;
                        if(male.isSelected()==true)
                        {
                            selectedGender="Male";
                        }
                        else
                        {
                            selectedGender="Female";
                        }
                        
                        //block to add new records and keep check of duplicates
                        boolean duplicate=false;
                        boolean run=true;
                        boolean ok=false;
                        
                        //runs if the arraylist is empty
                        if(members.isEmpty())
                        {
                            //Creating and adding the object of regular member to the arrayList of GymMember
                            try//try block to test for possible exceptions
                            {
                                if(Plans.getSelectedItem()=="BASIC")
                                {
                                    ok=true;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(homePage,
                                    "When starting out, the Basic plan is the one available for selection.",
                                    "Plan Notice",
                                    JOptionPane.WARNING_MESSAGE);
                                
                                }
                                if(ok)
                                {
                                members.add(new RegularMember(Integer.parseInt(enterId.getText()),enterName.getText(),enterLocation.getText(),enterPhone.getText(),enterEmail.getText(),selectedGender,birthDate,startDate,enterReferal.getText() ));
                                JOptionPane.showMessageDialog(homePage,"Sucessfully registered","Sucess",JOptionPane.INFORMATION_MESSAGE);
                                //Simulates the clear button being clicked to clear all textFields and JcComboBoxes after Submission
                                clear.doClick();
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                run=false;
                            }
                        }
                        else
                        {
                            //itterating for each object present in the arraylist
                            for(GymMember each:members)
                            {
                                //checking for duplicates
                                try
                                {
                                    if((each.getId()==Integer.parseInt(enterId.getText())))
                                    {
                                        duplicate=true;
                                        break;
                                    }
                                    else
                                    {
                                        duplicate=false;
                                        if(Plans.getSelectedItem()=="BASIC")
                                        {
                                            ok=true;
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(homePage,
                                            "When starting out, the Basic plan is the one available for selection.",
                                            "Plan Notice",
                                            JOptionPane.WARNING_MESSAGE);
                                        }
                                    }
                                }
                                catch(NumberFormatException ea)
                                {
                                    JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                    run = false;
                                    break;
                                }
                            }
                            
                            //runs only if no exception arises
                            if(run)
                            {
                                //Message to be displayed if duplicate is to be found
                                if(duplicate)
                                {
                                    JOptionPane.showMessageDialog(homePage,"The entered ID already exist","Duplicate ID detected",JOptionPane.ERROR_MESSAGE);
                                }
                                //runs if there are no duplicates found
                                else
                                {
                                    //runs only when the basic plan is selected
                                    if(ok)
                                    {
                                        JOptionPane.showMessageDialog(homePage,"Sucessfully registered","Sucess",JOptionPane.INFORMATION_MESSAGE);
                                        
                                        
                                        //adding regular member by passing required parameters

                                        members.add(new RegularMember(Integer.parseInt(enterId.getText()),enterName.getText(),enterLocation.getText(),enterPhone.getText(),enterEmail.getText(),selectedGender,birthDate,startDate,enterReferal.getText() ));
                                        //Simulates the clear button being clicked to clear all textFields and JcComboBoxes after Submission
                                        clear.doClick();
                                    }
                                }
                            }
                        }
                    }
                }
                //For premium member
                else
                {
                    //If statement to make sure all the necessary text fields are filled before submission
                    if(enterId.getText().equals("Enter your ID")||
                    enterName.getText().equals("Enter your name")||
                    enterLocation.getText().equals("Enter location")||
                    enterPhone.getText().equals("Enter phone number")||
                    enterEmail.getText().equals("Enter your e-mail")||
                    enterPersonalTrainer.getText().equals("Enter trainer name")||
                    enterId.getText().equals("") || 
                    enterName.getText().equals("") || 
                    enterLocation.getText().equals("") || 
                    enterPhone.getText().equals("") || 
                    enterEmail.getText().equals("") || 
                    enterPersonalTrainer.getText().equals(""))
                    {
                        //message to be displayed if any textField is found to be empty
                        JOptionPane.showMessageDialog(homePage,"Please fill all the necesarry text fields!!!","Incomplete fill up",JOptionPane.WARNING_MESSAGE);
                    }
                    //moves on if all the fields are filled properly
                    else
                    {
                        //retrives and concatenates the value from the JComboBox
                        String birthDate= DOBYear.getSelectedItem()+"-"+DOBMonth.getSelectedItem()+"-"+DOBDay.getSelectedItem();
                        
                        //retrives and concatenates the value from the JComboBox
                        String startDate=StartYear.getSelectedItem()+"-"+StartMonth.getSelectedItem()+"-"+StartDay.getSelectedItem();
                        
                        //Variable to figure out which Radio button is selected
                        String selectedGender;
                        if(male.isSelected()==true)
                        {
                            selectedGender="Male";
                        }
                        else
                        {
                            selectedGender="Female";
                        }
                        
                        //block to add new records and keep check of duplicates
                        boolean duplicate=false;
                        boolean run=true;
                        if(members.isEmpty())
                        {
                            //Creating and adding the object of premium member to the arrayList of GymMember
                            try
                            {
                                members.add(new PremiumMember(Integer.parseInt(enterId.getText()),enterName.getText(),enterLocation.getText(),enterPhone.getText(),enterEmail.getText(),selectedGender,birthDate,startDate,enterPersonalTrainer.getText() ));
                                JOptionPane.showMessageDialog(homePage,"Sucessfully registered","Sucess",JOptionPane.INFORMATION_MESSAGE);
                                //Simulates the clear button being clicked to clear all textFields and JcComboBoxes after Submission
                                clear.doClick();
                            }
                            catch(NumberFormatException ea)
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                run = false;
                            }
                        }
                        else
                        {
                            //iterating for each object present in the arraylist
                            for(GymMember each:members)
                            {
                                try//try block to check for possible exceptions
                                {
                                    //checking for duplicates
                                    if((each.getId()==Integer.parseInt(enterId.getText())))
                                    {
                                        duplicate=true;
                                        break;
                                    }
                                    else
                                    {   
                                        duplicate=false;
                                    }
                                }
                                catch(NumberFormatException ea)//catch block to handel the possible exceptions
                                {
                                    JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                    run = false;
                                    break;
                                }
                            }
                            
                            //runs only if no exceptions arise
                            if(run)
                            {
                                //Message to be displayed if duplicate is to be found
                                if(duplicate)
                                {
                                    JOptionPane.showMessageDialog(homePage,"The entered ID already exist","Duplicate ID detected",JOptionPane.ERROR_MESSAGE);
                                }
                                //runs if there are no duplicates found
                                else
                                {
                                    JOptionPane.showMessageDialog(homePage,"Sucessfully registered","Sucess",JOptionPane.INFORMATION_MESSAGE);
                                   
                                    //Creating and adding the object of premium member to the arrayList of GymMember
                                    members.add(new PremiumMember(Integer.parseInt(enterId.getText()),enterName.getText(),enterLocation.getText(),enterPhone.getText(),enterEmail.getText(),selectedGender,birthDate,startDate,enterPersonalTrainer.getText() ));
                                    
                                    //Simulates the clear button being clicked to clear all textFields and JcComboBoxes after Submission
                                    clear.doClick();
                                }
                            }
                        }
                    }
                }
            }
            
            
            //checks and activates entered id
            if(e.getSource()==activatebtn)
            {
                boolean exists = true;
                //runs if the array list is empty
                if(members.isEmpty())
                    {
                        JOptionPane.showMessageDialog(activatePage,
                        "Looks like there are no members yet.\nWhy not register a few to begin?",
                        "Empty member list",
                        JOptionPane.WARNING_MESSAGE);
                        exists=false;
                    }
                //runs id any of the textfields are left empty
                else if(activateID.getText().equals("")||activateID.getText().equals("Enter your ID"))
                    {
                        JOptionPane.showMessageDialog(activatePage,
                        "Please enter an ID",
                        "Not a valid ID",
                        JOptionPane.ERROR_MESSAGE);
                        exists=false;
                    }
                else
                {
                    for(GymMember each : members)
                    {
                        //checks for objects of regular members only
                        if(each instanceof RegularMember)
                        {
                            try//try block to catch any possible exceptions
                            {
                                //checks if the entered id exists or not
                                if(each.getId() == Integer.parseInt(activateID.getText())) 
                                {
                                    //type casting the GymMember object ot regular member to gain acess to all its methods
                                    RegularMember regObj = (RegularMember) each;
                                    
                                    //runs if the entered id is already active
                                    if(regObj.getActiveStatus())
                                    {
                                        JOptionPane.showMessageDialog(activatePage, "Your ID is already active", "ID already active", JOptionPane.INFORMATION_MESSAGE);
                                        activateID.setText("");
                                        exists = false;
                                        break;
                                    }
                                    else
                                    {
                                        regObj.activateMembership();
                                        JOptionPane.showMessageDialog(activatePage, "ID successfully activated", "ID activated", JOptionPane.INFORMATION_MESSAGE);
                                        activateID.setText("");
                                        exists = false;
                                        break;
                                    }
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage, "The ID field only takes numbers", "Wrong format entered!", JOptionPane.ERROR_MESSAGE);
                                exists = false;
                                break;
                            }
                            
                        }
                        else //for premium members
                        {
                            try//try block to check for possible exceptions
                            {
                                //runs only if the entered id exists
                                if(each.getId() == Integer.parseInt(activateID.getText()))
                                {
                                    //type casting the GymMember object ot premium member to gain acess to all its methods
                                    PremiumMember preObj = (PremiumMember) each;
                                    
                                    //runs if the id is aready active
                                    if(preObj.getActiveStatus())
                                    {
                                        JOptionPane.showMessageDialog(activatePage, "Your ID is already active", "ID already active", JOptionPane.INFORMATION_MESSAGE);
                                        exists = false;
                                        activateID.setText("");
                                        break;
                                    }
                                    else
                                    {
                                        preObj.activateMembership();
                                        JOptionPane.showMessageDialog(activatePage, "ID successfully activated", "ID activated", JOptionPane.INFORMATION_MESSAGE);
                                        exists = false;
                                        activateID.setText("");
                                        break;
                                    }
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage, "The ID field only takes numbers", "Wrong format entered!", JOptionPane.ERROR_MESSAGE);
                                exists = false;
                                 break;
                            }
                        }
                    }
                }
                //runs if the entered id doesnt exist
                if(exists)
                {
                    JOptionPane.showMessageDialog(activatePage, "The entered ID doesn't exist", "ID not found", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            //checks and deactivates entered id
            if(e.getSource()==deactivatebtn)
            {
                boolean exists = true;
                //runs if the arrayList empty
                if(members.isEmpty())
                    {
                        JOptionPane.showMessageDialog(activatePage,
                        "Looks like there are no members yet.\nWhy not register a few to begin?",
                        "Empty member list",
                        JOptionPane.WARNING_MESSAGE);
                        exists=false;
                    }
                //runs f any of the necessary text fields are left empty
                else if(activateID.getText().equals("")||activateID.getText().equals("Enter your ID"))
                    {
                        JOptionPane.showMessageDialog(activatePage,
                        "Please enter an ID",
                        "Not a valid ID",
                        JOptionPane.ERROR_MESSAGE);
                        exists=false;
                    }
                else
                {
                    for(GymMember each : members)
                    {
                        try//try block to check for possible exceptions
                        {
                            //runs only if the entered id exists
                            if(each.getId()==Integer.parseInt(activateID.getText()))
                            {
                                //runs only if the entered id is already active
                                if(each.activeStatus)
                                {   
                                    each.deactivateMembership();
                                    JOptionPane.showMessageDialog(activatePage,"Suscessfully deactivated.","Deactivated",JOptionPane.INFORMATION_MESSAGE);
                                    activateID.setText("");
                                    exists=false;
                                    break;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(activatePage,"The entered ID is already inactive","Inactive ID",JOptionPane.WARNING_MESSAGE);
                                    exists=false;
                                    activateID.setText("");
                                    break;
                                }
                            }
                        }
                        catch(NumberFormatException ea)//catch block to handel the possible exceptions
                        {
                            JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                            exists=false;
                            break;
                        }
                    }
                }
                //runs if the entered id doesnt exist
                if(exists)
                {
                    JOptionPane.showMessageDialog(activatePage,
                    "The entered ID doesnt exist",
                    "ID not found",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            
            //marks attendance of regualr and premium members
            if(e.getSource()==markAttendance)
            {
                //runs if the arraylist is empty
                if(members.isEmpty())
                    {
                        JOptionPane.showMessageDialog(activatePage,
                        "Looks like there are no members yet.\nWhy not register a few to begin?",
                        "Empty member list",
                        JOptionPane.WARNING_MESSAGE);
                    }
                //runs if any of the text fields are empty
                else if(enterAttendanceID.getText().equals("")||enterAttendanceID.getText().equals("Enter your ID"))
                {
                    JOptionPane.showMessageDialog(attendancePage,
                    "Please enter a valid ID",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    boolean exists= false;
                    boolean active= false;
                    for(GymMember each:members)
                    {
                        //checks for objects of regular member 
                        if(each instanceof RegularMember)
                        {
                            try//try block to check for possible exceptions
                            {
                                //runs only if the entered id exists
                                if(each.getId()==Integer.parseInt(enterAttendanceID.getText()))
                                {
                                    //type casting, down casting from gym member to regular member to gain access og the regular memebers methods
                                    RegularMember regObj =(RegularMember) each;
                                    exists=true;
                                    
                                    //runs only if the entered id is active
                                    if(each.getActiveStatus())
                                    {
                                        regObj.markAttendance();
                                    
                                        JOptionPane.showMessageDialog(attendancePage,
                                        "Attendance marked.\n The current attendance of "+each.getId()+" is: "+each.attendance,
                                        "Attendance Updated",
                                        JOptionPane.INFORMATION_MESSAGE);
                                        
                                        active=true;
                                    }
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=true;
                                active=true;
                                break;
                            }
                        }
                        else if(each instanceof PremiumMember) //checks for objects of premium member
                        {
                            try//try block to check for possible exceptions
                            {
                                //runs only if the entered id exists
                                if(each.getId()==Integer.parseInt(enterAttendanceID.getText()))
                                {
                                    PremiumMember preObj =(PremiumMember) each;//type casting, down casting from gym member to regular member to gaina ccess og the regular memebers methods
                                    exists=true;
                                    //runs only if the entered id id active 
                                    if(each.getActiveStatus()==true)
                                    {
                                        preObj.markAttendance();
                                        
                                        JOptionPane.showMessageDialog(attendancePage,
                                        "Attendance marked.\n The current attendance of "+each.getId()+" is: "+each.attendance,
                                        "Attendance Updated",
                                        JOptionPane.INFORMATION_MESSAGE);
                                        
                                        active=true;
                                    }
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=true;
                                active=true;
                                break;
                            }
                        }
                    }
                    
                    //runs if the entered id doesnt exist
                    if(exists==false)
                    {
                        JOptionPane.showMessageDialog(attendancePage,"The entered ID doesnt exist","ID not found",JOptionPane.ERROR_MESSAGE);
                    }
                    //runs if the entered id is not active
                    else if(active==false)
                    {
                        JOptionPane.showMessageDialog(attendancePage,"Your ID has not yet been activated","ID not active",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            
            
            
            //reverts regular members
            if(e.getSource()==regularRevertbtn)
            {
                boolean exists=true;
                boolean premium = false;
                //runs if the arraylist is empty
                if(members.isEmpty())
                    {
                        JOptionPane.showMessageDialog(activatePage,
                        "Looks like there are no members yet.\nWhy not register a few to begin?",
                        "Empty member list",
                        JOptionPane.WARNING_MESSAGE);
                    }
                //runs if any of the text fields are empty
                else if(enterRevertID.getText().equals("")||enterRevertID.getText().equals("Enter your ID"))
                {
                    JOptionPane.showMessageDialog(attendancePage,
                    "Please enter a valid ID",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    for(GymMember each : members)
                    {
                        //checks for the objects of regular member
                        if(each instanceof RegularMember)
                        {
                            try//try block to check for possible exceptions
                            {
                                //runs only if the entered id exists
                                if(each.getId()==Integer.parseInt(enterRevertID.getText()))
                                {
                                    RegularMember regObj = (RegularMember) each;
                                    
                                    String reason;
                                    
                                    reason = JOptionPane.showInputDialog(revertPage,
                                    "Enter Removal Reason:",
                                    "Removal Reason required",
                                    JOptionPane.QUESTION_MESSAGE);
                                    
                                    try////try block to check for possible exceptions
                                    {
                                        //runs if the removal reason is left empty
                                        if(reason.equals("")|| reason.equals(null))
                                        {
                                        JOptionPane.showMessageDialog(revertPage,
                                        "Please enter a reason before submiting",
                                        "Reason not found",
                                        JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        else
                                        {
                                            regObj.revertRegularMember(reason);
                                    
                                            JOptionPane.showMessageDialog(revertPage,
                                            "Sucessfully reverted",
                                            "Revert sucess",
                                            JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        exists=false;
                                        premium=false;
                                        break;
                                    }

                                    catch(NullPointerException ee)//catch block to handel the possible exceptions
                                    {
                                        exists=false;
                                    }
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format entered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                        else if(each instanceof PremiumMember)
                        {
                            try////try block to check for possible exceptions
                            {
                                if(each.getId()==Integer.parseInt(enterRevertID.getText()))
                                {
                                    premium= true;
                                    exists=false;
                                }
                            }
                            catch(NumberFormatException ne)
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format entered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                    }
                    //runs only if the entered id doesn't exist
                    if(exists)
                    {
                        JOptionPane.showMessageDialog(attendancePage,"The entered ID doesnt exist","ID not found",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(premium)
                    {
                        JOptionPane.showMessageDialog(attendancePage,
                        "Premium member ID detected",
                        "You've entered a premium member ID in the regular member slot",
                        JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            
            
            //reverts premium members
            if(e.getSource()==premiumRevertbtn)
            {
                boolean exists=true;
                boolean regular=false;
                //runs if the arraylist is empty
                if(members.isEmpty())
                    {
                        JOptionPane.showMessageDialog(revertPage,
                        "Looks like there are no members yet.\nWhy not register a few to begin?",
                        "Empty member list",
                        JOptionPane.WARNING_MESSAGE);
                    }
                //runs if any of the text fields are left empty
                else if(enterRevertID.getText().equals("")||enterRevertID.getText().equals("Enter your ID"))
                {
                    JOptionPane.showMessageDialog(revertPage,
                    "Please enter a valid ID",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    for(GymMember each : members)
                    {
                        //checks for objects of premium member
                        if(each instanceof PremiumMember)
                        {
                            try//try block to check for possible exceptions
                            {
                                //runs only if the entered if exists
                                if(each.getId()==Integer.parseInt(enterRevertID.getText()))
                                {
                                    PremiumMember preObj = (PremiumMember) each;
                                    
                                    preObj.revertPremiumMember();
                                    
                                    JOptionPane.showMessageDialog(revertPage,
                                    "Sucessfully reverted",
                                    "Revert sucess",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    
                                    exists=false;
                                    regular=false;
                                    break;
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                        else if(each instanceof RegularMember)
                        {
                            try//try block to check for possible exceptions
                            {
                                //runs only if the entered if exists
                                if(each.getId()==Integer.parseInt(enterRevertID.getText()))
                                {
                                    regular = true;
                                    exists = false;
                                }
                            }
                            catch(NumberFormatException ea)//catch block to handel the possible exceptions
                            {
                                JOptionPane.showMessageDialog(homePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                    }
                    //runs only if the entered id doesnt exist
                    if(exists)
                    {
                        JOptionPane.showMessageDialog(attendancePage,"The entered ID doesnt exist","ID not found",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(regular)
                    {
                        JOptionPane.showMessageDialog(attendancePage,
                        "Regular member ID detected",
                        "You've entered a regular member ID in the premium member slot",
                        JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            
            
            //Upgrades plans of regular members if the requirements are met
            if(e.getSource()==upgradebtn)
            {
                boolean exists=true;
                if(members.isEmpty())
                {
                    JOptionPane.showMessageDialog(activatePage,
                    "Looks like there are no members yet.\nWhy not register a few to begin?",
                    "Empty member list",
                    JOptionPane.WARNING_MESSAGE);
                }
                else if(upgradeID.getText().equals("")||upgradeID.getText().equals("Enter your ID"))
                {
                    JOptionPane.showMessageDialog(upgradePage,
                    "Please enter a valid ID",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    for(GymMember each : members)
                    {
                        if(each instanceof RegularMember)
                        {
                            try
                            {
                                if(each.getId()==Integer.parseInt(upgradeID.getText()))
                                {
                                    RegularMember regObj = (RegularMember) each;
                                    int currentlySelected = selectPlan.getSelectedIndex();
                                    if(preSelected>currentlySelected)
                                    {
                                        JOptionPane.showMessageDialog(upgradePage,
                                        "You cant downgrade to a lower plan",
                                        "Downgrading not allowed",
                                        JOptionPane.ERROR_MESSAGE);
                                    }
                                    else
                                    {
                                        String a=regObj.upgradePlan(String.valueOf(selectPlan.getSelectedItem()).toLowerCase());
                                        JOptionPane.showMessageDialog(upgradePage,
                                        a,
                                        "Notice!",
                                        JOptionPane.INFORMATION_MESSAGE);
                                        preSelected=currentlySelected;
                                    }
                                    exists=false;
                                    break;
                                    //System.out.println(String.valueOf(selectPlan.getSelectedItem()).toLowerCase());
                                    //exists=false;
                                }
                            }
                            catch(NumberFormatException ea)
                            {
                                JOptionPane.showMessageDialog(upgradePage,"The ID field only takes numbers","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                        else if(each instanceof PremiumMember)
                        {
                            if(each.getId()==Integer.parseInt(upgradeID.getText()))
                            {
                            JOptionPane.showMessageDialog(upgradePage,
                            "Being a premium member is the highest staus \n So u cannot choose for plans",
                            "Unavailable Upgrade",
                            JOptionPane.INFORMATION_MESSAGE);
                            exists=false;
                            break;
                            }
                        }
                    }
                    if(exists)
                    {
                        JOptionPane.showMessageDialog(upgradePage,"The entered ID doesnt exist","ID not found",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            
            if(e.getSource()==payBtn)
            {
                boolean exists=true;
                if(members.isEmpty())
                {
                    JOptionPane.showMessageDialog(payPage,
                    "Looks like there are no members yet.\nWhy not register a few to begin?",
                    "Empty member list",
                    JOptionPane.WARNING_MESSAGE);
                }
                else if(enterPayID.getText().equals("")||enterPayID.getText().equals("Enter your ID"))
                {
                    JOptionPane.showMessageDialog(payPage,
                    "Please enter a valid ID",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    for(GymMember each: members)
                    {
                        if(each instanceof PremiumMember)
                        {
                            try
                            {
                                if(each.getId()==Integer.parseInt(enterPayID.getText()))
                                {
                                    PremiumMember preObj = (PremiumMember) each;
                                    JOptionPane.showMessageDialog(payPage,
                                    preObj.payDueAmount(Double.parseDouble(enterDue.getText())),
                                    "Notice!",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    exists=false;
                                    break;
                                }
                            }
                            catch(NumberFormatException ea)
                            {
                                JOptionPane.showMessageDialog(upgradePage,"Please enter integer values only in the fields","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                        else if(each instanceof RegularMember)
                        {
                            try
                            {
                                if(each.getId()==Integer.parseInt(enterPayID.getText()))
                                {
                                    JOptionPane.showMessageDialog(payPage,
                                    "Regular member dues are cleared during registration!!",
                                    "Regular member alert",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    exists=false;
                                    break;
                                }
                            }
                            catch(NumberFormatException ea)
                            {
                                JOptionPane.showMessageDialog(discountPage,"Please enter integer values only in the fields","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                    }
                    if(exists)
                    {
                        JOptionPane.showMessageDialog(upgradePage,"The entered ID doesnt exist","ID not found",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            if(e.getSource()==discountbtn)
            {
                boolean exists=true;
                if(members.isEmpty())
                {
                    JOptionPane.showMessageDialog(discountPage,
                    "Looks like there are no members yet.\nWhy not register a few to begin?",
                    "Empty member list",
                    JOptionPane.WARNING_MESSAGE);
                }
                else if(enterDiscountID.getText().equals("")||enterDiscountID.getText().equals("Enter your ID"))
                {
                    JOptionPane.showMessageDialog(discountPage,
                    "Please enter a valid ID",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    for(GymMember each : members)
                    {
                        if( each instanceof PremiumMember)
                        {
                            try
                            {
                                if(each.getId()==Integer.parseInt(enterDiscountID.getText()))
                                {
                                    PremiumMember preObj = (PremiumMember) each;
                                    JOptionPane.showMessageDialog(discountPage,
                                    preObj.calculateDiscount(),
                                    "Notice!",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    exists=false;
                                    break;
                                }
                            }
                            catch(NumberFormatException ea)
                            {
                                JOptionPane.showMessageDialog(discountPage,"Please enter integer values only in the fields","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                        else if(each instanceof RegularMember)
                        {
                            try
                            {
                                if(each.getId()==Integer.parseInt(enterDiscountID.getText()))
                                {
                                    JOptionPane.showMessageDialog(discountPage,
                                    "No discounts are available for Regular members",
                                    "No discounts :(",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    exists=false;
                                    break;
                                }
                            }
                            catch(NumberFormatException ea)
                            {
                                JOptionPane.showMessageDialog(discountPage,"Please enter integer values only in the fields","Wrong format ebtered!",JOptionPane.ERROR_MESSAGE);
                                exists=false;
                                break;
                            }
                        }
                    }
                    if(exists)
                    {
                        JOptionPane.showMessageDialog(discountPage,"The entered ID doesnt exist","ID not found",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            //makes the nav bar visible
            if(e.getSource()==menu)
            {
                navBar.setVisible(true);
            }
            
            //hides the nav bar
            if(e.getSource()==back)
            {
                navBar.setVisible(false);
            }
            
            //puts the home page on top and hides/disables all the other layers and buttons
            if(e.getSource()==home)
            {
                layers.setLayer(homePage,2);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,1);
                
                
                navBar.setVisible(false);
                
                
                display.setForeground(Color.white);
                home.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                discounts.setForeground(Color.white);
                pay.setForeground(Color.white);
                revert.setForeground(Color.white);
                
                submit.setEnabled(true);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the attendance page on top and hides/disables all the other layers and buttons
            if(e.getSource()==attendance)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,2);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,1);
                
                
                navBar.setVisible(false);
                
                
                display.setForeground(Color.white);
                attendance.setForeground(new Color(0xB388FF));
                home.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                discounts.setForeground(Color.white);
                pay.setForeground(Color.white);
                revert.setForeground(Color.white);
                
                
                submit.setEnabled(false);
                markAttendance.setEnabled(true);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the Activate page on top and hides/disables all the other layers and buttons
            if(e.getSource()==acOrDeac)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,2);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,1);
                
                
                navBar.setVisible(false);
                
                
                display.setForeground(Color.white);
                acOrDeac.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                home.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                discounts.setForeground(Color.white);
                pay.setForeground(Color.white);
                revert.setForeground(Color.white);
                
                submit.setEnabled(false);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(true);
                deactivatebtn.setEnabled(true);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the upgrade page on top and hides/disables all the other layers and buttons
            if(e.getSource()==upgrade)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,2);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,1);
                
                
                
                navBar.setVisible(false);
                
                
                
                display.setForeground(Color.white);
                upgrade.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                home.setForeground(Color.white);
                discounts.setForeground(Color.white);
                pay.setForeground(Color.white);
                revert.setForeground(Color.white);
                
                submit.setEnabled(false);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(true);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the discount page on top and hides/disables all the other layers and buttons
            if(e.getSource()==discounts)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,2);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,1);
                
                
                
                navBar.setVisible(false);
                
                
                
                display.setForeground(Color.white);
                discounts.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                home.setForeground(Color.white);
                pay.setForeground(Color.white);
                revert.setForeground(Color.white);
                
                submit.setEnabled(false);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(true);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the pay page on top and hides/disables all the other layers and buttons
            if(e.getSource()==pay)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,2);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,1);
                
                
                
                navBar.setVisible(false);
                
                                
                display.setForeground(Color.white);
                pay.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                discounts.setForeground(Color.white);
                home.setForeground(Color.white);
                revert.setForeground(Color.white);
                
                submit.setEnabled(false);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(true);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the revert page on top and hides/disables all the other layers and buttons
            if(e.getSource()==revert)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,2);
                layers.setLayer(displayPanel,1);
                
                
                
                navBar.setVisible(false);
                
                
                
                
                revert.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                discounts.setForeground(Color.white);
                pay.setForeground(Color.white);
                home.setForeground(Color.white);
                display.setForeground(Color.white);
                
                
                submit.setEnabled(false);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(true);
                regularRevertbtn.setEnabled(true);
                displayRegular.setEnabled(false);
                displayPremium.setEnabled(false);
            }
            
            
            //puts the display page on top and hides/disables all the other layers and buttons
            if(e.getSource()==display)
            {
                layers.setLayer(homePage,1);
                layers.setLayer(attendancePage,1);
                layers.setLayer(activatePage,1);
                layers.setLayer(upgradePage,1);
                layers.setLayer(discountPage,1);
                layers.setLayer(payPage,1);
                layers.setLayer(revertPage,1);
                layers.setLayer(displayPanel,2);
                
                
                
                navBar.setVisible(false);
                
                
                
                
                display.setForeground(new Color(0xB388FF));
                attendance.setForeground(Color.white);
                revert.setForeground(Color.white);
                acOrDeac.setForeground(Color.white);
                upgrade.setForeground(Color.white);
                discounts.setForeground(Color.white);
                pay.setForeground(Color.white);
                home.setForeground(Color.white);
                
                submit.setEnabled(false);
                markAttendance.setEnabled(false);
                activatebtn.setEnabled(false);
                deactivatebtn.setEnabled(false);
                upgradebtn.setEnabled(false);
                discountbtn.setEnabled(false);
                payBtn.setEnabled(false);
                premiumRevertbtn.setEnabled(false);
                regularRevertbtn.setEnabled(false);
                displayRegular.setEnabled(true);
                displayPremium.setEnabled(true);
            
            }
            
            
            if(e.getSource() == saveToFile)
            {
                if(members.isEmpty())
                {
                    JOptionPane.showMessageDialog(mainFrame,
                    "There seems to be no members to be saved to file",
                    "No members found",
                    JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    try
                    {
                        /*
                         * uses string formatting to write data into the text file
                           %- - right aligns text as per the spaces
                           % - left align texts according to the spaces specified
                           */
                        FileWriter writer = new FileWriter("members.txt");
                        writer.write("-".repeat(240)+"\n");
                        writer.write(String.format("%-6s %-15s %-25s %-20s %-25s %-25s %-15s %-15s %-17s %-17s %-17s %-17s %-17s\n",
                        "ID", "Name", "Location", "Phone", "Email", "Start Date", 
                        "Plan", "Attendance", "Loyalty Points", "Active Status", 
                        "Full Payment", "Discount", "Net Paid Amount"
                        ));
                        writer.write("-".repeat(240)+"\n");
                        for(GymMember each : members)
                        {
                            String id, attendance, name, location, phone, email, DOB, membershipStartDate, plan, loyaltyPoints, paidAmount, discountAmount, activeStatus, fullPayment;
                            if(each instanceof RegularMember)
                            {
                                RegularMember regObj = (RegularMember) each;
                                id = String.valueOf(regObj.getId());
                                name = regObj.getName();
                                location = regObj.getLocation();
                                phone = regObj.getPhone();
                                email = regObj.getEmail();
                                membershipStartDate = regObj.getMembershipStartDate();
                                plan = regObj.getPlan();
                                attendance = String.valueOf(regObj.getAttendance());
                                loyaltyPoints = String.valueOf(regObj.getLoyaltyPoints());
                                activeStatus = String.valueOf(regObj.getActiveStatus());
                                
                               writer.write(String.format(
                                "%-6s %-15s %-25s %-20s %-25s %-25s %-15s %-15s %-17s %-17s %-17s %-17s %-17s\n",
                                id, name, location, phone, email, membershipStartDate,
                                plan, attendance, loyaltyPoints, activeStatus,
                                "N/A", "N/A", "N/A"
                               ));
                            }
                            else
                            {
                                PremiumMember preObj = (PremiumMember) each;
                                id = String.valueOf(preObj.getId());
                                name = preObj.getName();
                                location = preObj.getLocation();
                                phone = preObj.getPhone();
                                email = preObj.getEmail();
                                membershipStartDate = preObj.getMembershipStartDate();
                                attendance = String.valueOf(preObj.getAttendance());
                                loyaltyPoints = String.valueOf(preObj.getLoyaltyPoints());
                                activeStatus = String.valueOf(preObj.getActiveStatus());
                                fullPayment = String.valueOf(preObj.getIsFullPayment());
                                discountAmount = String.valueOf(preObj.getDiscountAmount());
                                paidAmount = String.valueOf(preObj.getPaidAmount());
                                
                                writer.write(String.format("%-6s %-15s %-25s %-20s %-25s %-25s %-15s %-15s %-17s %-17s %-17s %-17s %-17s\n",
                                id, name, location, phone, email, membershipStartDate,"N/A", attendance, loyaltyPoints,
                                activeStatus , fullPayment, discountAmount, paidAmount));
                            }
                        }
                        writer.write("-".repeat(240)+"\n");
                        writer.close();
                        JOptionPane.showMessageDialog(mainFrame,
                        "Sucessfully saved in the file!!!",
                        "Sucess",
                        JOptionPane.INFORMATION_MESSAGE);
                    } 
                    catch(IOException ie)
                    {
                        JOptionPane.showMessageDialog(mainFrame,
                        "Couldn't write the details to file",
                        "Failed",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            if(e.getSource() == readFromFile)
            {
                try
                {
                    FileReader reader = new FileReader("members.txt");
                    int data;
                    String content="";
                    //reads the data file till the end 
                    while((data = reader.read()) != -1)
                    {
                        char letter = (char) data;//converting the numbers returned into characters
                        content = content+letter;
                    }
                    //System.out.print(content);
                    fileDisplay.setText(content);
                    fileDisplayPannel.setVisible(true);
                    layers.setLayer(fileDisplayPannel,4);
                    reader.close();
                }
                catch(IOException ie)
                {
                    JOptionPane.showMessageDialog(mainFrame,
                    "It seems the data of members havent been saved even once.\n Hence there is nothing to read from",
                    "Please save before reading",
                    JOptionPane.WARNING_MESSAGE);
                }
            }
            
            if(e.getSource() == returnBtn)
            {
                fileDisplayPannel.setVisible(false);
                layers.setLayer(fileDisplayPannel,1);
            }
        }
    //Clearsthe placeholders in the text fields when focus is gained
    @Override
    /* Focus gained works when components like text fields or other gui coponents are clicked or gain focus
       here it has been used for place holders in text fields*/
    public void focusGained(FocusEvent e)
    {
        if(e.getSource()==enterId)
        {
            if(enterId.getText().equals("Enter your ID"))
            {
                enterId.setText("");
                enterId.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterName)
        {
            if(enterName.getText().equals("Enter your name"))
            {
                enterName.setText("");
                enterName.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterLocation)
        {
            if(enterLocation.getText().equals("Enter location"))
            {
                enterLocation.setText("");
                enterLocation.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterPhone)
        {
            if(enterPhone.getText().equals("Enter phone number"))
            {
                enterPhone.setText("");
                enterPhone.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterEmail)
        {
            if(enterEmail.getText().equals("Enter your e-mail"))
            {
                enterEmail.setText("");
                enterEmail.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterReferal)
        {
            if(enterReferal.getText().equals("Enter referal source"))
            {
                enterReferal.setText("");
                enterReferal.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterPersonalTrainer)
        {
            if(enterPersonalTrainer.getText().equals("Enter trainer name"))
            {
                enterPersonalTrainer.setText("");
                enterPersonalTrainer.setForeground(Color.black);
            }
        }
        
        if(e.getSource()==enterAttendanceID)
        {
            if(enterAttendanceID.getText().equals("Enter your ID"))
            {
                enterAttendanceID.setText("");
                enterAttendanceID.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==activateID)
        {
            if(activateID.getText().equals("Enter your ID"))
            {
                activateID.setText("");
                activateID.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==upgradeID)
        {
            if(upgradeID.getText().equals("Enter your ID"))
            {
                upgradeID.setText("");
                upgradeID.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterDiscountID)
        {
            if(enterDiscountID.getText().equals("Enter your ID"))
            {
                enterDiscountID.setText("");
                enterDiscountID.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterPayID)
        {
            if(enterPayID.getText().equals("Enter your ID"))
            {
                enterPayID.setText("");
                enterPayID.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterDue)
        {
            if(enterDue.getText().equals("Enter amount"))
            {
                enterDue.setText("");
                enterDue.setForeground(Color.black);
            }
        }
        
        
        if(e.getSource()==enterRevertID)
        {
            if(enterRevertID.getText().equals("Enter your ID"))
            {
                enterRevertID.setText("");
                enterRevertID.setForeground(Color.black);
            }
        }
    }
    
    //Places the placeholders back in the text fields when focus is lost
    public void focusLost(FocusEvent e) 
        {
            if(e.getSource()==enterId)
            {
                if(enterId.getText().equals(""))
                {
                    enterId.setText("Enter your ID");
                    enterId.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterName)
            {
                if(enterName.getText().equals(""))
                {
                    enterName.setText("Enter your name");
                    enterName.setForeground(Color.gray);
                }
            }
        
            
            if(e.getSource()==enterLocation)
            {
                if(enterLocation.getText().equals(""))
                {
                    enterLocation.setText("Enter location");
                    enterLocation.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterPhone)
            {
                if(enterPhone.getText().equals(""))
                {
                    enterPhone.setText("Enter phone number");
                    enterPhone.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterEmail)
            {
                if(enterEmail.getText().equals(""))
                {
                    enterEmail.setText("Enter your e-mail");
                    enterEmail.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterReferal)
            {
                if(enterReferal.getText().equals(""))
                {
                    enterReferal.setText("Enter referal source");
                    enterReferal.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterPersonalTrainer)
            {
                if(enterPersonalTrainer.getText().equals(""))
                {
                    enterPersonalTrainer.setText("Enter trainer name");
                    enterPersonalTrainer.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterAttendanceID)
            {
                if(enterAttendanceID.getText().equals(""))
                {
                    enterAttendanceID.setText("Enter your ID");
                    enterAttendanceID.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==activateID)
            {
                if(activateID.getText().equals(""))
                {
                    activateID.setText("Enter your ID");
                    activateID.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==upgradeID)
            {
                if(upgradeID.getText().equals(""))
                {
                    upgradeID.setText("Enter your ID");
                    upgradeID.setForeground(Color.gray);
                }
            }
            
            if(e.getSource()==enterDiscountID)
            {
                if(enterDiscountID.getText().equals(""))
                {
                    enterDiscountID.setText("Enter your ID");
                    enterDiscountID.setForeground(Color.gray);
                }
            }
            
            if(e.getSource()==enterPayID)
            {
                if(enterPayID.getText().equals(""))
                {
                    enterPayID.setText("Enter your ID");
                    enterPayID.setForeground(Color.gray);
                }
            }
            
            
            if(e.getSource()==enterDue)
            {
                if(enterDue.getText().equals(""))
                {
                    enterDue.setText("Enter amount");
                    enterDue.setForeground(Color.gray);
                }
            }
            
            if(e.getSource()==enterRevertID)
            {
                if(enterRevertID.getText().equals(""))
                {
                    enterRevertID.setText("Enter your ID");
                    enterRevertID.setForeground(Color.gray);
                }
            }
        }
}