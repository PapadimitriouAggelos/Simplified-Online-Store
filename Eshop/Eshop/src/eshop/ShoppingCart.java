package eshop;

import enumeration.BuyerCategory;
import exception.CartIsEmptyException;
import exception.NotEnoughStockException;
import item.Item;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.Buyer;

//Καλάθι αγορών του χρήστη
public class ShoppingCart 
{    
    //Έχει μια λίστα με αντικείμενα ItemOrdered orderList
    public ArrayList <ItemOrdered> orderList=new ArrayList <ItemOrdered> ();
    
    //constructor
    public ShoppingCart()
    {
        
    }
    
    
    /* addItemOrdered(): Δημιουργεί και προσθέτει ένα αντικείμενο ItemOrdered στη λίστα. Προηγείται έλεγχος αν υπάρχει 
    διαθέσιμο stock. Αν υπάρχει, η αιτούμενη ποσότητα αφαιρείται από το stock του προϊόντος (το προϊόν δεσμεύεται). 
    Αν δεν υπάρχει, εγείρεται εξαίρεση και δεν δημιουργείται αντικείμενο. Πριν από τη δημιουργία αντικειμένου, 
    ελέγχεται αν υπάρχει άλλο αντικείμενο με το ίδιο Item στη λίστα. Αν υπάρχει, τότε απλώς ανανεώνεται το quantity 
    του υπάρχοντος. Καλείται από την placeOrder() της Buyer. */
    public void addItemOrdered(Item item, int quantity) throws NotEnoughStockException
    {
        if(item.getStock()>=quantity)
        {
            for(ItemOrdered k:orderList)
                if(item.getId()==k.getItem().getId())
                    k.setQuantity(k.getQuantity()+quantity);
            
            ItemOrdered io=new ItemOrdered(item, quantity);
            
            orderList.add(io);
            
            item.setStock(item.getStock()-quantity);
        }
        else
            throw new NotEnoughStockException("Το stock για το προιόν "+item.getName()+" δεν επαρκεί!");
    }
    
    
    //removeItemOrdered(): Αφαιρεί ένα αντικείμενο ItemOrdered από μια θέση της λίστας. Ενημερώνει το stock.
    public void removeItemOrdered(int id)
    {
        for(ItemOrdered item:orderList)
            if(id==item.getItem().getId())
            {
                item.getItem().setStock(item.getItem().getStock()+item.getQuantity());
                
                orderList.remove(item);
                
                break;
            }
    }
    
    
    /* changeItemOrderedQuantity(): Για ένα αντικείμενο σε μια θέση της λίστας τροποποιεί το quantity και ενημερώνει 
    το stock (updateItemStock()). Προηγείται έλεγχος αν υπάρχει διαθέσιμο stock */
    public void changeItemOrderedQuantity(Eshop shop, int id, int q) throws NotEnoughStockException
    {
        for(ItemOrdered item:orderList)
            if(id==item.getItem().getId())
            {
                if(item.getItem().getStock()>=q)
                {
                    item.setQuantity(q);
                    shop.updateItemStock(item.getItem().getId(), q);
                }
                else
                    throw new NotEnoughStockException("Το stock για το προιόν "+item.getItem().getName()+" δεν επαρκεί!");
            }
            
    }
            
    
    /* showCart(): Εμφανίζει τα περιεχόμενα του καλαθιού αγορών, δηλαδή τα προϊόντα, τις ποσότητες και τις τιμές τους, 
    το σύνολο και τα μεταφορικά */
    public void showCart(Buyer buyer) throws CartIsEmptyException
    {
        if(orderList.size()>0)
        {
            int sum=0;
            
            System.out.println("Καλάθι αγορών: ");
            
            for(ItemOrdered item:orderList)
            {
                sum++;
                
                System.out.println(sum+". Προιόν: "+item.getItem().getName()+", Ποσότητα: "+item.getQuantity()+", Τιμή: "+item.getItem().getPrice()*item.getQuantity()+" $");
            }
            
            System.out.println();
            
            System.out.println("Καθαρή αξία παραγγελίας: "+this.calculateNet()+" $");
            System.out.println("Μεταφορικά παραγγελίας: "+this.calculateCourierCost(buyer)+" $");
            System.out.println("Σύνολο: "+(this.calculateNet()+this.calculateCourierCost(buyer))+" $");
        }
        else
           throw new CartIsEmptyException("Το καλάθι αγορών είναι άδειο!"); 
    }
    
    
    //clearCart(): Καλεί τη removeItemOrdered() και αφαιρεί όλα τα αντικείμενα από τη λίστα
    public void clearCart()
    {
        orderList.clear();
    }
    
    
    /* checkout(): Καλεί την showCart(). Ζητά επιβεβαίωση για πληρωμή και εφόσον είναι θετική, καλεί την awardBonus() 
    του Buyer και διαγράφει τα περιεχόμενα της λίστας (δεν αλλάζει όμως το stock). Οι πόντοι υπολογίζονται 
    σε ποσοστό 10% επί της αξίας της παραγγελίας (χωρίς τα μεταφορικά) και είναι ακέραιος αριθμός */
    public void checkout(Buyer buyer) throws CartIsEmptyException
    {
        Scanner sc=new Scanner(System.in);
        
        if(orderList.size()>0)
        {
            showCart(buyer);
            
            System.out.println("Θέλετε να επιβεβαιώσετε την παραγγελία σας? Πατήστε Y(yes) ή N(no) αντίστοιχα: ");
            String ans=sc.next();
            
            if(ans.equals("Y")||ans.equals("y"))
            {
                buyer.awardBonus();
                
                for(ItemOrdered item:orderList)
                    orderList.remove(item);
            }
        }
        else
            throw new CartIsEmptyException("Το καλάθι αγορών είναι άδειο!");
    }
    
    
    //calculateNet(): Επιστρέφει την αξία της παραγγελίας
    public double calculateNet() throws CartIsEmptyException
    {
        double kostos=0;
        
        if(orderList.size()>0)
        {
           for(ItemOrdered item:orderList)
               kostos+=item.getItem().getPrice()*item.getQuantity();
           
           return kostos;
        }
        else
            throw new CartIsEmptyException("Το καλάθι αγορών είναι άδειο!");
    }
    
    /* calcluateCourierCost(): Υπολογίζει το κόστος μεταφορικών το οποίο είναι το 2% της αξίας της παραγγελίας, 
    αλλά δεν μπορεί να είναι μικρότερο από 3 ευρώ. Ελέγχει σε ποια κατηγορία είναι ο πελάτης (Buyer). 
    Αν είναι στην κατηγορία gold, δεν πληρώνει μεταφορικά, ενώ αν είναι στην κατηγορία silver έχει 50% έκπτωση 
    στα μεταφορικά, ακόμα κι αν το ποσό που προκύπτει είναι μικρότερο από 3 ευρώ */
    public double calculateCourierCost(Buyer buyer) throws CartIsEmptyException
    {
        double metaforika=0;
        
        if(orderList.size()>0)
        {
            if(buyer.getBuyerCategory()==BuyerCategory.BRONZE)
            {
                if(this.calculateNet()*0.02<3)
                    metaforika=3;
                else
                    metaforika=this.calculateNet()*0.02;
            }
            
            if(buyer.getBuyerCategory()==BuyerCategory.SILVER)
            {
                metaforika=(this.calculateNet()*0.02)/2;
            }
            
            if(buyer.getBuyerCategory()==BuyerCategory.GOLD)
                metaforika=0;
            
            return metaforika;
        }
        else
            throw new CartIsEmptyException("Το καλάθι αγορών είναι άδειο!");
    }
    
    
    //μέθοδος που επιστρέφει το μέγεθος του καλαθιού αγορών
    public int getCartSize()
    {
        return orderList.size();
    }
    
}
