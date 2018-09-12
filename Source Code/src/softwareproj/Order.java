package softwareproj;
import java.util.ArrayList;

public class Order {
    protected static ArrayList<Order> orders = new ArrayList();
    protected ArrayList<Item> items = new ArrayList();
    protected ArrayList<Integer> quantity = new ArrayList();
    
    
    protected boolean orderState;
    protected double total;
    protected static int orderId;

    public Order() {
        orderId++;
    }
    
    public Order(ArrayList<Item> i, ArrayList<Integer> q){
        items = i;
        quantity = q;
        orders.add(this);
    }
    
    public void addItems(Item i, int q){
        items.add(i);
        quantity.add(q);
        if(i != null)
            total += i.getPrice()*q;
    }
    
    public void addTaxes(){
        total += Math.round(total*14)/100.0;
    }
    
    public double getTaxes(){
        return Math.round(total*14)/100.0;
    }
    
    public void setOrderState(boolean orderState){
        this.orderState = orderState;
    }
    
    public boolean getOrderState(){
        return orderState;
    }
    
    public static ArrayList<Order> getOrdersList(){
        return orders;
    }
    
    public void cancelOrder(){
        for(int i = 0; i<items.size(); i++){
            items.remove(i);
            quantity.remove(i);
        }
        orderId--;
    }
    
    public void saveOrder(Order o){
        orders.add(o);
        
    }

    public double getTotal() {
        return total;
    }
    
    
    @Override
    public String toString() {
        String s="";
        
        /*for (int i = 0; i < items.size(); i++) {
            s += (items.get(i)).toString() + "\n";
        }*/
        
        for (Item item : items) {
            
            s += (item).toString() + "\n";
            
        }
        return s;
    }
    
    public String toKitchenScreen() {
        String s="";
        
        for (int i = 0; i < items.size(); i++) {
            s += (quantity.get(i)) + "      " +(items.get(i)).toKitchenScreen() + "\n";
        }
        return s;
    }
    
}