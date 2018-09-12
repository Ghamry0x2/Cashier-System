package softwareproj;
import java.awt.*;
import javax.swing.*;

public class Printer extends JFrame {
    private Order order;
    private Customer customer;
    private boolean takeaway;
    private JPanel NorthPnl = new JPanel();
    private JPanel CenterPnl = new JPanel(new GridLayout(13,1,5,5));
    private JPanel SouthPnl = new JPanel(new GridLayout(3,1));
    
    private JPanel[] centerRow = new JPanel[13];
    private JPanel SouthRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel SouthRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel SouthRow21 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    private JLabel[] quantityLbl = new JLabel[13];
    private JLabel[] priceLbl = new JLabel[13];
    private JLabel[] tPriceLbl = new JLabel[13];
    private JLabel[] nameLbl = new JLabel[13];
    
    private JLabel cName = new JLabel("");
    private JLabel cNumber = new JLabel("");
    private JLabel cAddress = new JLabel("");
    
    private JLabel type = new JLabel();
    private JLabel PaymentType = new JLabel();
    private JLabel id = new JLabel();
    private JLabel subTotalLbl = new JLabel("Sub Total: 0");
    private JLabel taxLbl = new JLabel("Tax: 0");
    private JLabel totalLbl = new JLabel("Total: 0");
    private JLabel custAmoutLbl = new JLabel("Paid Amount");
    private JLabel changeLbl = new JLabel("Change");
    
    private boolean credit;
    
    private double amt;
    
    private int itemCount = 1;

    public Printer(Order o, boolean takeaway, boolean credit, double amt){
        order = o;
        this.amt = amt;
        this.takeaway = takeaway;
        this.credit = credit;
        init();
    }
    
    private void init(){
        setTitle("Invoice");
        setLocation(1040, 0);
        setResizable(false);
        
        Container c = getContentPane();
       
        for(int i=0; i<nameLbl.length; i++){
            nameLbl[i] = new JLabel();
            quantityLbl[i] = new JLabel();
            priceLbl[i] = new JLabel();
            tPriceLbl[i] = new JLabel();
            centerRow[i] = new JPanel();
            centerRow[i].setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
            nameLbl[i].setPreferredSize(new Dimension(150,20));
            quantityLbl[i].setPreferredSize(new Dimension(50,20));
            priceLbl[i].setPreferredSize(new Dimension(40,20));
            tPriceLbl[i].setPreferredSize(new Dimension(40,20));
            
            centerRow[i].add(nameLbl[i]);
            centerRow[i].add(quantityLbl[i]);
            centerRow[i].add(priceLbl[i]);
            centerRow[i].add(tPriceLbl[i]);
            
            CenterPnl.add(centerRow[i]);
        }
        id.setText("Order Number: "+Order.orderId);
        if(!takeaway){
            order = (Delivery)order;
            NorthPnl.setPreferredSize(new Dimension(330,120+30));
            customer = ((Delivery)order).getC();
            type.setText("Order Type: Delivery");
            cName.setText("Name: " + customer.getName());
            cNumber.setText("Number: " + customer.getPhoneNum());
            cAddress.setText("Address: " + customer.getAddress());
            
            NorthPnl.setLayout(new GridLayout(5,1,5,5));
            NorthPnl.add(type);
            NorthPnl.add(id);
            NorthPnl.add(cName);
            NorthPnl.add(cNumber);
            NorthPnl.add(cAddress);
            
            subTotalLbl.setText("Sub Total: "+(order.getTotal()+10));
            taxLbl.setText("Tax: "+order.getTaxes());
            totalLbl.setText("Total: "+(order.getTotal()+order.getTaxes()+10));

            nameLbl[12].setText("Delivery Charge");
            quantityLbl[12].setText("1");
            priceLbl[12].setText("10");
            tPriceLbl[12].setText("10");
        }
        else {
            NorthPnl.setPreferredSize(new Dimension(330,40+30));
            NorthPnl.setLayout(new GridLayout(2,1,5,5));
            type.setText("Order Type: Takeaway");
            subTotalLbl.setText("Sub Total: "+order.getTotal());
            taxLbl.setText("Tax: "+order.getTaxes());
            totalLbl.setText("Total: "+(order.getTotal()+order.getTaxes()));
            NorthPnl.add(id);
            NorthPnl.add(type);
        }
        
        nameLbl[0].setText("Product Name");
        quantityLbl[0].setText("Number");
        priceLbl[0].setText("Price");
        tPriceLbl[0].setText("Total");
        
        for(int i=1; i<=order.items.size(); i++){
            nameLbl[i].setText(order.items.get(i-1).getName());
            quantityLbl[i].setText(order.quantity.get(i-1)+"");
            priceLbl[i].setText(order.items.get(i-1).getPrice()+"");
            tPriceLbl[i].setText((order.quantity.get(i-1)*order.items.get(i-1).getPrice())+"");
        }
        
        if(credit) {
            PaymentType.setText("Paid With Credit Card");
            custAmoutLbl.setText("");
            changeLbl.setText("");
        }
        else {
            PaymentType.setText("Paid in Cash");
            custAmoutLbl.setText("Paid: "+amt);
            double d = order.getTotal()+order.getTaxes();
            d = amt - d;
            d = Math.round(d*100)/100.0;
            if(takeaway)
                changeLbl.setText("Change: " + d);
            else
                changeLbl.setText("Change: " + (d-10));
        }
        subTotalLbl.setPreferredSize(new Dimension(120,25));
        taxLbl.setPreferredSize(new Dimension(90,25));
        totalLbl.setPreferredSize(new Dimension(90,25));
        
        changeLbl.setPreferredSize(new Dimension (100,25));
        custAmoutLbl.setPreferredSize(new Dimension (100,25));
        SouthRow1.add(subTotalLbl);
        SouthRow1.add(taxLbl);
        SouthRow1.add(totalLbl);
        SouthRow21.add(PaymentType);
        SouthRow2.add(custAmoutLbl);
        SouthRow2.add(changeLbl);
        
        SouthPnl.add(SouthRow1);
        SouthPnl.add(SouthRow21);
        SouthPnl.add(SouthRow2);
        
        c.add(NorthPnl,BorderLayout.NORTH);
        c.add(SouthPnl,BorderLayout.SOUTH);
        c.add(CenterPnl);
        
        pack();
    }
}