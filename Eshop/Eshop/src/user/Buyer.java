package user;

import enumeration.BuyerCategory;
import eshop.ItemOrdered;
import eshop.ShoppingCart;
import exception.CartIsEmptyException;
import exception.NotEnoughStockException;
import item.Item;

/* Υποκλάση της User. Διαθέτει πεδίο int bonus το οποίο αποθηκεύει τους πόντους που κερδίζει ο πελάτης 
από τις αγορές του και πεδίο buyerCategory: {BRONZE, SILVER, GOLD} που αντιπροσωπεύει ορισμένα προνόμια. 
Επίσης έχει ως πεδίο ένα αντικείμενο της κλάσης ShoppingCart που αναπαριστά το καλάθι αγορών του  */
public class Buyer extends User
{
    //attributes
    private int bonus; //αποθηκεύει τους πόντους που κερδίζει ο πελάτης
    private BuyerCategory buyerCategory;
    private ShoppingCart myCart;
    
    
    //constructor
    public Buyer(String name, String email)
    {
        super(name, email);
        
        bonus=0;
        buyerCategory=BuyerCategory.BRONZE;
        myCart=new ShoppingCart();
    }
    
    
    //getters
    public BuyerCategory getBuyerCategory()
    {
        return buyerCategory;
    }
    
    public ShoppingCart getMyCart()
    {
        return myCart;
    }
    
    public int getBonus()
    {
        return bonus;
    }
    
    
    /* awardBonus(): Αρχικά οι πόντοι είναι μηδέν. Κάθε φορά που πληρώνεται μια παραγγελία αλλάζει το σύνολο των πόντων 
    bonus (προστίθενται οι επιπλέον πόντοι), και τροποποιείται το buyerCategory, αν χρειάζεται. */
    public int awardBonus() throws CartIsEmptyException
    {
        if(myCart.getCartSize()>0)
        {
            return bonus += (int)(myCart.calculateNet()*0.1);
        }
        else
            throw new CartIsEmptyException("Το καλάθι αγορών είναι άδειο!");
    }
    
    
    /* setbuyerCategory(): Η κατηγορία πελάτη εξαρτάται από το σύνολο των αγορών που έχει κάνει.  
    Αρχικά είναι bronze. Μετά τους 100 πόντους γίνεται silver και μετά τους 200 γίνεται gold */
    public void setBuyerCategory() throws CartIsEmptyException
    {
        if(myCart.getCartSize()>0)
        {
            if(awardBonus()>100 && awardBonus()<=200)
                buyerCategory=BuyerCategory.SILVER;
        
            if(awardBonus()>200)
                buyerCategory=BuyerCategory.GOLD;
        }
        else
            throw new CartIsEmptyException("Το καλάθι αγορών είναι άδειο!");
    }
    
    
    /* placeOrder(): Μέθοδος η οποία τοποθετεί ένα συγκεκριμένο προϊόν με συγκεκριμένη ποσότητα στο καλάθι αγορών 
    του χρήστη. H placeOrder δημιουργεί αντικείμενα τύπου ItemOrdered και τα προσθέτει στο καλάθι, καλώντας 
    τις αντίστοιχες μεθόδους της ShoppingCart */
    public void placeOrder(Item item, int quantity) throws NotEnoughStockException
    {
        if(item.getStock()>=quantity)
        {
            ItemOrdered io=new ItemOrdered(item, quantity);
            
            myCart.addItemOrdered(item, quantity);
        }
        else
            throw new NotEnoughStockException("Το stock για το προιόν "+item.getName()+" δεν επαρκεί!");
    }
    
}
