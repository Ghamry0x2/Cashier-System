package softwareproj;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;

public class Customer extends JFrame{   
    private JPanel pnlNorth = new JPanel();
    private JLabel lblCustomerType = new JLabel("Customer Type: ");
    
    private JRadioButton rbtnOld = new JRadioButton("Old");
    private JRadioButton rbtnNew = new JRadioButton("New");
    private ButtonGroup groupCustomerType = new ButtonGroup();
    
    private JPanel pnlOld = new JPanel();
    private JPanel pnlNew = new JPanel();
    private JPanel pnlOldSearchBar = new JPanel();
    
    private JLabel lblSearch = new JLabel("Search : ");
    private JLabel lblOldCustomer = new JLabel();
    private JLabel lblNewCustomer = new JLabel();
    
    private JLabel lblName = new JLabel("Name : ");
    private JLabel lblPhoneNumber = new JLabel("Phone Number : ");
    private JLabel lblAddress = new JLabel("Address : ");
    
    private JNumberTextField txtSearch = new JNumberTextField("Enter Phone Number Here...");
    private JTextField txtName = new JTextField("Enter Name Here...");
    private JNumberTextField txtPhoneNumber = new JNumberTextField("Enter Phone Number Here...");
    private JTextField txtAddress = new JTextField("Enter Address Here...");
    
    private JButton btnSearch = new JButton("Search");
    private JButton btnSelect = new JButton("Select");
    private JButton btnOk = new JButton("Ok");
    
    
    private Customer caller = null;
    private String name = "None";
    private String phoneNum = "None";
    private String address = "None";

    private static ArrayList<Customer> callers = new ArrayList();
    private static boolean firstCall = true;
    
    public Customer(){
        init();
        if(firstCall) {
            try {
                FileReader fr = new FileReader("Customers.csv");
                BufferedReader br = new BufferedReader(fr);
                String[] ss;
                String s = br.readLine();
                while(s != null && !s.equals("")){
                    System.out.println(s);
                    ss = s.split(",");
                    callers.add(new Customer(ss));
                    s = br.readLine();
                }
                br.close();
                fr.close();
                firstCall = false;
            }
            catch(IOException e){

            }
            for(Customer c: callers)
                System.out.println(c.name+"\t"+c.phoneNum+"\t"+c.address+"\t");
        }
        
    }
    
    public Customer(String[] ss){
        name = ss[0];
        phoneNum = ss[1];
        address = ss[2];
    }
    
    private void init(){
        this.setTitle("Select Customer");
        this.setBounds(300, 300, 600, 250);
        this.setResizable(false);
        //this.setIconImage(new ImageIcon(getClass().getResource("/icons/SpongeBob.png")).getImage());
        
        Container c = this.getContentPane();
        
        //-----Allignment-----
        pnlNorth.add(lblCustomerType);
        pnlNorth.add(rbtnOld);
        pnlNorth.add(rbtnNew);
        
        groupCustomerType.add(rbtnOld);
        groupCustomerType.add(rbtnNew);
        rbtnOld.setSelected(true);
        
        c.add(pnlNorth, BorderLayout.NORTH);
        
        pnlOldSearchBar.add(lblSearch);
        txtSearch.setPreferredSize(new Dimension(200, 30));
        pnlOldSearchBar.add(txtSearch);
        pnlOldSearchBar.add(btnSearch);
        btnSelect.setEnabled(false);
        pnlOldSearchBar.add(btnSelect);
        
        pnlOld.setLayout(new GridLayout(2, 1));
        pnlOld.add(pnlOldSearchBar);
        lblOldCustomer.setHorizontalAlignment(JLabel.CENTER);
        pnlOld.add(lblOldCustomer);
        c.add(pnlOld, BorderLayout.CENTER);
        
        pnlNew.setLayout(null);
        
        lblName.setBounds(100,5,100,30);
        lblPhoneNumber.setBounds(100,45,100,30);
        lblAddress.setBounds(100,85,100,30);
        
        pnlNew.add(lblName);
        pnlNew.add(lblPhoneNumber);
        pnlNew.add(lblAddress);
        
        txtName.setBounds(210,5,300,30);
        txtPhoneNumber.setBounds(210,45,300,30);
        txtAddress.setBounds(210,85,300,30);
        
        pnlNew.add(txtName);
        pnlNew.add(txtPhoneNumber);
        pnlNew.add(txtAddress);
        
        btnOk.setBounds(260,125,100,30);
        lblNewCustomer.setBounds(380,125,200,30);
        pnlNew.add(btnOk);
        pnlNew.add(lblNewCustomer);
        
        
        //-----Action Listeners-----
        
        rbtnOld.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
       
                    pnlNew.setVisible(false);
                    c.add(pnlOld, BorderLayout.CENTER);
                    pnlOld.setVisible(true);
            }
        });
        
        rbtnNew.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
       
                    pnlOld.setVisible(false);
                    c.add(pnlNew, BorderLayout.CENTER);
                    pnlNew.setVisible(true);
            }
        });
        
        txtSearch.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( txtSearch.getText().equals("Enter Phone Number Here...") )
                    txtSearch.setText("");
            }
        });
        
        txtName.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( txtName.getText().equals("Enter Name Here...") )
                    txtName.setText("");
            }
        });
        
        txtPhoneNumber.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( txtPhoneNumber.getText().equals("Enter Phone Number Here...") )
                    txtPhoneNumber.setText("");
            }
        });
        
        txtAddress.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( txtAddress.getText().equals("Enter Address Here...") )
                    txtAddress.setText("");
            }
        });
        
        btnOk.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    
                lblNewCustomer.setVisible(false);
                    
                if(txtName.getText().equals("") || txtName.getText().equals("Enter Name Here...") || 
                   txtPhoneNumber.getText().equals("") || txtPhoneNumber.getText().equals("Enter Phone Number Here...") ||
                   txtAddress.getText().equals("") || txtAddress.getText().equals("Enter Address Here...")){
                        lblNewCustomer.setText("Please enter valid data");
                }else{
                        Caller(txtName.getText(), txtPhoneNumber.getText(), txtAddress.getText());
                        caller = Customer.this;
                        addNewCustomer(caller);
                        lblNewCustomer.setText("Customer is added successfully");
                }
                
                lblNewCustomer.setVisible(true);
            }
        });
        
        btnSearch.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
       
                lblOldCustomer.setVisible(false);
                
                caller = searchOldCustomer();
                
                if(txtSearch.getText().equals("Enter Phone Number Here...")){
                    lblOldCustomer.setText("Please enter customer's phone number");
                }
                else if (txtSearch.getText().length() != 11){
                    lblOldCustomer.setText("Please enter a valid phone number");
                }
                else if(caller != null){
                    lblOldCustomer.setText(caller.name);
                    btnSelect.setEnabled(true);
                }
                else{
                    lblOldCustomer.setText("Customer Not Found");
                    btnSelect.setEnabled(false);
                }
                
                lblOldCustomer.setVisible(true);
            }
        });
        
        btnSelect.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    lblOldCustomer.setText("Customer is selected");
            }
        });
    }

    public void Caller(String name, String phoneNum, String address) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
    }
    
    public Customer searchOldCustomer(){
        for (int i = 0; i < callers.size(); i++) {
            if( callers.get(i).phoneNum.equals(txtSearch.getText()) )
                return callers.get(i);
        }
        for (int i = 0; i < callers.size(); i++) {
            if( callers.get(i).phoneNum.equals(txtPhoneNumber.getText()) )
                return callers.get(i);
        }
        return null;
    }
    
    public static void addNewCustomer(Customer caller){
        callers.add(caller);
        try {
            FileWriter fw = new FileWriter("Customers.csv", true);
            PrintWriter pw = new PrintWriter(fw, true);
            pw.println(caller.name + "," + caller.phoneNum + "," + caller.address);
            pw.close();
            fw.close();
        }
        catch(IOException e){
            
        }
    }
    
    public Customer getCaller(){
        return caller;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    

    
    
    @Override
    public String toString() {
        String s;
        
        s = "Name : " + name + "\nPhone Number : " + phoneNum + "\nAddress : " + address + "\n";
        return s;
    }
}
