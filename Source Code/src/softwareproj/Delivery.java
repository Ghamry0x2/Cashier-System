package softwareproj;

public class Delivery extends Order{
    Customer c;
    
    public Delivery(Customer cu) {
        c = cu;
        if(c == null) {
            c = new Customer();
            c.setName("None");
            c.setAddress("None");
            c.setPhoneNum("None");
        }
        //System.out.println(c.getName()+"\n"+c.getPhoneNum()+"\n"+c.getAddress());
    }

    public Customer getC() {
        return c;
    }
}
