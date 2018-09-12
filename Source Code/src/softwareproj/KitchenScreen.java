package softwareproj;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class KitchenScreen extends JFrame  {
    private JTextArea areaOrders = new JTextArea(14, 46);
    private JButton btnDone = new JButton("Done");
    private JPanel scrollPanel = new JPanel();
    private JScrollPane scrollPane;
    
    private String screenOrders = "Orders :\n\n";
    
    private static ArrayList<Order> orders;
    private static int orderTurn = 0;
    
    public KitchenScreen() {
        init();
    }
    
    private void init(){
        this.setTitle("Kitchen's Screen");
        this.setBounds(400, 580, 550, 300);
        //this.setIconImage(new ImageIcon(getClass().getResource("/icons/SpongeBob.png")).getImage());
        
        Container c = this.getContentPane();
        
        areaOrders.setBackground(new Color(235,235,235));
        areaOrders.setEditable(false);
        
        areaOrders.setText(screenOrders);
        
        scrollPanel.add(areaOrders);
        scrollPane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(btnDone,BorderLayout.SOUTH);
        
        orders = Order.getOrdersList();
        
                
        btnDone.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (orderTurn < orders.size()){
                    orders.get(orderTurn).setOrderState(true);
                    orderTurn++;
                    refreshScreen();
                }
            }
        });
    }
    
    public void updateScreenOrders(Order order){
        screenOrders += orders.get(orders.size()-1).toKitchenScreen() + "\n\n";
        areaOrders.setText(screenOrders);
    }
    
    public void refreshScreen(){
        screenOrders = "Orders :\n\n";
        
        if(orderTurn == 0)
            screenOrders = "Orders :\n\n";
        else{
            for (int i = orderTurn; i < orders.size(); i++) {
                screenOrders += orders.get(i).toKitchenScreen() + "\n\n";
            }
        }
        areaOrders.setText(screenOrders);
    }
}