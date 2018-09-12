package softwareproj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class JNumberTextField extends JTextField {
    @Override
    public void processKeyEvent(KeyEvent ev) {
        if ( Character.isDigit(ev.getKeyChar()) || ev.getKeyChar() == KeyEvent.VK_BACK_SPACE 
            || ev.getKeyCode() == KeyEvent.VK_LEFT || ev.getKeyCode() == KeyEvent.VK_RIGHT 
            || ev.getKeyChar() == KeyEvent.VK_PERIOD || ev.getKeyCode() == KeyEvent.VK_ENTER) {
            super.processKeyEvent(ev);
        }
        //ev.consume();
    }
    
    public JNumberTextField(){
        super();
    }
    
    public JNumberTextField(String s){
        super(s);
    }
    
    public JNumberTextField(String s , int n){
        super(s,n);
    }
}

class ButtonsPanelBlock extends JPanel {
    JButton item;
    
    public ButtonsPanelBlock(String s){
        super();
        init(s);
    }

    private void init(String s){
        item = new JButton(s);
        
        setPreferredSize(new Dimension(150,60));
        item.setPreferredSize(new Dimension(150,35));
        
        setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
        
        add(item);
    }
}

public class Controller extends JFrame  implements ActionListener{
    //Main Panels
    private JPanel eastPnl = new JPanel(new BorderLayout(5,5));
    private JPanel southPnl = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
    private JPanel northPnl = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
    private JPanel itemsPnl = new JPanel(new GridLayout(5,1,20,15));
    
    
    //Center Panel members
    private JPanel sandwichesRow1 = new JPanel(new GridLayout(1,4,20,0));
    private JPanel sandwichesRow2 = new JPanel(new GridLayout(1,4,20,0));
    private JPanel sandwichesRow3 = new JPanel(new GridLayout(1,4,20,0));
    
    private JPanel friesPnl = new JPanel(new GridLayout(1,4,20,0));
    private JPanel drinksPnl = new JPanel(new GridLayout(1,4,20,0));
    
    private ButtonsPanelBlock[] sandwichesItemsPnl = new ButtonsPanelBlock[12];
    private ButtonsPanelBlock[] friesItemsPnl = new ButtonsPanelBlock[4];
    private ButtonsPanelBlock[] drinksItemsPnl = new ButtonsPanelBlock[4];
    
    
    //North Panel Members
    private JRadioButton deliveryCheck = new JRadioButton("Delivery");
    private JRadioButton takeawayCheck = new JRadioButton("Takeaway");
    private JLabel quantityTxtLbl = new JLabel("Quantity");
    private JNumberTextField quantityTxt = new JNumberTextField("1",3);
    private boolean takeaway = true;
    
    
    //South Panel Members
    private JButton confirmOrder = new JButton("Confirm");
    private JButton cancelOrder = new JButton("Cancel");
    private JRadioButton cashCheck = new JRadioButton("Cash");
    private JRadioButton creditCheck = new JRadioButton("Credit Card");
    private boolean credit = true;
    
    
    //East Panel Members
    Order order;
    private static Customer customer;
    private double amt;
    private int quantity;
    private boolean pCon;
    private JPanel eastNorthPnl = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
    private JPanel eastCenterPnl = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
    private JPanel eastSouthPnl = new JPanel(new GridLayout(2,1));
    private JPanel eastSouthRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel eastSouthRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel[] quantityLbl = new JLabel[12];
    private JLabel[] priceLbl = new JLabel[12];
    private JLabel[] tPriceLbl = new JLabel[12];
    private JLabel[] nameLbl = new JLabel[12];
    private JLabel subTotalLbl = new JLabel("Sub Total: 0");
    private JLabel taxLbl = new JLabel("Tax: 0");
    private JLabel totalLbl = new JLabel("Total: 0");
    private JLabel custAmoutLbl = new JLabel("Customer Amount");
    private JLabel changeLbl = new JLabel("Change: 0");
    private JNumberTextField custAmountTxt = new JNumberTextField("0.0",5);
    
    private int itemCount = 1;
    
    private Item menuItems = new Item();
    private KitchenScreen kitchenScreen = new KitchenScreen();
    
    public Controller(){
        init();
    }
    
    private void init(){
        setTitle("Krusty Krab Resturant");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout(10,0));
        
        //Center
        for(int i=0; i<sandwichesItemsPnl.length; i++){
            sandwichesItemsPnl[i] = new ButtonsPanelBlock(menuItems.getSandwichesNames(i));
            sandwichesItemsPnl[i].item.addActionListener(this);
            if(i<4)
                sandwichesRow1.add(sandwichesItemsPnl[i]);
            else if(i<8)
                sandwichesRow2.add(sandwichesItemsPnl[i]);
            else if(i<12)
                sandwichesRow3.add(sandwichesItemsPnl[i]);
        }
        sandwichesRow1.setBorder(BorderFactory.createTitledBorder("Small Sandwiches"));
        sandwichesRow2.setBorder(BorderFactory.createTitledBorder("Medium Sandwiches"));
        sandwichesRow3.setBorder(BorderFactory.createTitledBorder("Special Sandwiches"));
        
        for(int i=0; i<friesItemsPnl.length; i++){
            friesItemsPnl[i] = new ButtonsPanelBlock(menuItems.getFriesNames(i));
            friesItemsPnl[i].item.addActionListener(this);
            friesPnl.add(friesItemsPnl[i]);
        }
        friesPnl.setBorder(BorderFactory.createTitledBorder("Fries"));
        
        for(int i=0; i<drinksItemsPnl.length; i++){
            drinksItemsPnl[i] = new ButtonsPanelBlock(menuItems.getDrinksNames(i));
            drinksItemsPnl[i].item.addActionListener(this);
            drinksPnl.add(drinksItemsPnl[i]);
        }
        drinksPnl.setBorder(BorderFactory.createTitledBorder("Drinks"));
        drinksPnl.setPreferredSize(new Dimension(0,0));
        
        itemsPnl.add(sandwichesRow1);
        itemsPnl.add(sandwichesRow2);
        itemsPnl.add(sandwichesRow3);
        itemsPnl.add(friesPnl);
        itemsPnl.add(drinksPnl);
        
        
        //North
        quantityTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        takeawayCheck.setSelected(takeaway);
        deliveryCheck.setSelected(!takeaway);
        northPnl.add(deliveryCheck);
        northPnl.add(takeawayCheck);
        northPnl.add(quantityTxtLbl);
        northPnl.add(quantityTxt);
        
        
        //South
        creditCheck.setSelected(credit);
        cashCheck.setSelected(!credit);
        southPnl.add(cashCheck);
        southPnl.add(creditCheck);
        southPnl.add(confirmOrder);
        southPnl.add(cancelOrder);
        
        
        //East
        eastPnl.setPreferredSize(new Dimension(350,0));
        eastPnl.setBorder(BorderFactory.createTitledBorder("Selected Items"));
        
        for(int i=0; i<nameLbl.length; i++){
            nameLbl[i] = new JLabel();
            quantityLbl[i] = new JLabel();
            priceLbl[i] = new JLabel();
            tPriceLbl[i] = new JLabel();
            nameLbl[i].setPreferredSize(new Dimension(150,20));
            quantityLbl[i].setPreferredSize(new Dimension(50,20));
            priceLbl[i].setPreferredSize(new Dimension(40,20));
            tPriceLbl[i].setPreferredSize(new Dimension(40,20));
            if(i>=1){
                eastCenterPnl.add(nameLbl[i]);
                eastCenterPnl.add(quantityLbl[i]);
                eastCenterPnl.add(priceLbl[i]);
                eastCenterPnl.add(tPriceLbl[i]);
            }
        }
        
        nameLbl[0].setText("Product Name");
        quantityLbl[0].setText("Number");
        priceLbl[0].setText("Price");
        tPriceLbl[0].setText("Total");
        
        subTotalLbl.setPreferredSize(new Dimension(120,25));
        taxLbl.setPreferredSize(new Dimension(90,25));
        totalLbl.setPreferredSize(new Dimension(90,25));
        
        custAmountTxt.setEditable(false);
        custAmountTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        changeLbl.setPreferredSize(new Dimension (100,25));
        eastSouthRow1.add(subTotalLbl);
        eastSouthRow1.add(taxLbl);
        eastSouthRow1.add(totalLbl);
        eastSouthRow2.add(custAmoutLbl);
        eastSouthRow2.add(custAmountTxt);
        eastSouthRow2.add(changeLbl);
        
        eastSouthPnl.add(eastSouthRow1);
        eastSouthPnl.add(eastSouthRow2);
        
        eastNorthPnl.add(nameLbl[0]);
        eastNorthPnl.add(quantityLbl[0]);
        eastNorthPnl.add(priceLbl[0]);
        eastNorthPnl.add(tPriceLbl[0]);
        eastPnl.add(eastNorthPnl,BorderLayout.NORTH); 
        eastPnl.add(eastSouthPnl,BorderLayout.SOUTH);
        eastPnl.add(eastCenterPnl);
        

        //add to main container
        c.add(eastPnl,BorderLayout.EAST);
        c.add(northPnl,BorderLayout.NORTH);
        c.add(southPnl,BorderLayout.SOUTH);
        c.add(itemsPnl);

        pack();
        
        //Action Listeners
        creditCheck.addActionListener((ActionEvent e) -> {
            ccCheck();
        });
        
        cashCheck.addActionListener((ActionEvent e) -> {
            ccCheck();
        });
        
        deliveryCheck.addActionListener((ActionEvent e) -> {
            dTChecks();
        });
        
        takeawayCheck.addActionListener((ActionEvent e) -> {
            dTChecks();
        });
        
        cancelOrder.addActionListener((ActionEvent e) -> {
            deliveryCheck.setEnabled(true);
            takeawayCheck.setEnabled(true);
            
            if(order != null && itemCount != 1)
                order.cancelOrder();
            
            cancel();
        });
        
        custAmountTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(order != null){
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    amt = Double.parseDouble(custAmountTxt.getText());
                    double amt2;
                    if(takeaway)
                        amt2 = order.getTotal()+order.getTaxes();
                    else
                        amt2 = order.getTotal()+order.getTaxes() + 10;
                    if(amt>= amt2) {
                        amt2 = amt - amt2;
                        amt2 = Math.round(amt2*100)/100.0;
                        changeLbl.setText("Change: " + amt2);
                        custAmountTxt.setEditable(false);
                        pCon = true;
                    }
                    else
                        JOptionPane.showMessageDialog(new Controller(), "Enter a Correct Amount!");
                    }
                }
            }
        });
        
        custAmountTxt.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( custAmountTxt.getText().equals("0.0") )
                    custAmountTxt.setText("");
            }
        });
        
        quantityTxt.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if( quantityTxt.getText().equals("1") )
                    quantityTxt.setText("");
            }
        });
        
        confirmOrder.addActionListener((ActionEvent e) -> {
            if(order != null && (pCon || credit)){
                order.saveOrder(order);
                deliveryCheck.setEnabled(true);
                takeawayCheck.setEnabled(true);
                Printer p = new Printer(order,takeaway,credit,amt);
                p.setVisible(true);
                System.out.println(order);
                kitchenScreen.updateScreenOrders(order);
                kitchenScreen.setVisible(true);
                cancel();
            }
        });
    }
    
    //Methods
    public void addItem(Item i){
        order.addItems(i, quantity);
        nameLbl[itemCount].setText(i.getName());
        quantityLbl[itemCount].setText(quantity+"");
        priceLbl[itemCount].setText(i.getPrice()+"");
        tPriceLbl[itemCount++].setText(i.getPrice()*quantity+"");
        
    }
    
    public void dTChecks(){
        takeaway = !takeaway;
        takeawayCheck.setSelected(takeaway);
        deliveryCheck.setSelected(!takeaway);
        if(!takeaway) {
            credit  = false;
            creditCheck.setSelected(credit);
            cashCheck.setSelected(!credit);
            custAmountTxt.setEditable(!credit);
            creditCheck.setEnabled(false);
            cashCheck.setEnabled(false);
            customer = new Customer();
            customer.setVisible(true);
        }
    }
    
    public void ccCheck(){
        credit = !credit;
        creditCheck.setSelected(credit);
        cashCheck.setSelected(!credit);
        custAmountTxt.setEditable(!credit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(itemCount == 1){
            if(takeaway)
                order = new Order();
            else
                order = new Delivery(customer.searchOldCustomer());
        }
        deliveryCheck.setEnabled(false);
        takeawayCheck.setEnabled(false);
        if(itemCount < 12) {
            quantity = Integer.parseInt(quantityTxt.getText());
            
            addItem(menuItems.getItem(((JButton)e.getSource()).getText()));

            subTotalLbl.setText("Sub Total: "+order.getTotal());
            taxLbl.setText("Tax: "+order.getTaxes());
            if(!takeaway)
                totalLbl.setText("Total: "+(order.getTaxes()+order.getTotal()+10));
            else
                 totalLbl.setText("Total: "+(order.getTaxes()+order.getTotal()));

            quantityTxt.setText("1");
            
        }
        else
            JOptionPane.showMessageDialog(this, "Max Number of Items Reached!");
    }
    
    public void cancel(){
        order = null;
        custAmountTxt.setText("0.0");
        changeLbl.setText("Change: 0");
        subTotalLbl.setText("Sub Total: 0");
        taxLbl.setText("Tax: 0");
        totalLbl.setText("Total: 0");
        
        credit = true;
        creditCheck.setSelected(credit);
        cashCheck.setSelected(!credit);
        
        takeaway = true;
        takeawayCheck.setSelected(takeaway);
        deliveryCheck.setSelected(!takeaway);
        
        creditCheck.setEnabled(true);
        cashCheck.setEnabled(true);
        takeawayCheck.setEnabled(true);
        deliveryCheck.setEnabled(true);
        
        itemCount = 1;
        for(int i=1; i<nameLbl.length; i++){
            nameLbl[i].setText("");
            quantityLbl[i].setText("");
            priceLbl[i].setText("");
            tPriceLbl[i].setText("");
        }
    }
}
