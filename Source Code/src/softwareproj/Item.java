package softwareproj;

import java.util.ArrayList;

public class Item {
    private String name;
    private double price;
    
    private static ArrayList<Item> sandwichesMenu = new ArrayList(12);
    private static ArrayList<Item> friesMenu = new ArrayList(4);
    private static ArrayList<Item> drinksMenu = new ArrayList(4);

    public Item(){
        //Intiallizng sandwiches menu
        sandwichesMenu.add(new Item("The Big Krusty",30));
        sandwichesMenu.add(new Item("Chicken Chikoo",35)); 
        sandwichesMenu.add(new Item("Double Krusty Krab",40)); 
        sandwichesMenu.add(new Item("Krusty Special",45)); 
        sandwichesMenu.add(new Item("Chicken Bomb",50)); 
        sandwichesMenu.add(new Item("Party of Krusty",55)); 
        sandwichesMenu.add(new Item("Honey Moon",60)); 
        sandwichesMenu.add(new Item("Triple Beef",65)); 
        sandwichesMenu.add(new Item("Final Destination",70)); 
        sandwichesMenu.add(new Item("Cow Boy",75));
        sandwichesMenu.add(new Item("Fire Alarm",80));
        sandwichesMenu.add(new Item("Country",85));
        
        //Intiallizng fries menu
        friesMenu.add(new Item("Small Fries",10));
        friesMenu.add(new Item("Medium Fries",15)); 
        friesMenu.add(new Item("Large Fries",20)); 
        friesMenu.add(new Item("Family Fries",25)); 

        //Intiallizng drinks menu
        drinksMenu.add(new Item("Pepsi",10));
        drinksMenu.add(new Item("Mirinda Apple",10)); 
        drinksMenu.add(new Item("Mirinda Orange",10)); 
        drinksMenu.add(new Item("Seven Up",10));
    }
    
    public Item(String n, double p){
        name = n;
        price = p;
    }

    public double getPrice() {
        return price;
    }
    
    public String getSandwichesNames(int i) {
        if(i<12)
            return sandwichesMenu.get(i).name;
        else return "no item";
    }
    
    public String getFriesNames(int i){
        if(i<4)
            return friesMenu.get(i).name;
        else return "no item";
    }
    
    public String getDrinksNames(int i){
        if(i<4)
            return drinksMenu.get(i).name;
        else
            return "no item";
    }
    
    public Item getItem(String s){
        for(int i=0; i<12; i++){
            if(s.equals(sandwichesMenu.get(i).name))
                return sandwichesMenu.get(i);
        }
        for(int i=0; i<4; i++){
            if(s.equals(friesMenu.get(i).name))
                return friesMenu.get(i);
        }
        for(int i=0; i<4; i++){
            if(s.equals(drinksMenu.get(i).name))
                return drinksMenu.get(i);
        }
        return new Item("not found",0);
    }

    public String getName() {
        return name;
    }
    
    public String toKitchenScreen() {
        String s = "";
        
        s = name;
        return s;
    }
    
}
