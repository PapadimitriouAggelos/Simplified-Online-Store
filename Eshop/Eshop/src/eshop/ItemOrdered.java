package eshop;

import item.Item;

/*
Αποτελείται από δύο πεδία, ένα Item item και ένα int quantity και συμβολίζει την παραγγελία 
για ένα συγκεκριμένο Item σε συγκεκριμένη ποσότητα. Προαιρετικά, μπορείτε να εξετάσετε τη χρήση Map 
στη ShoppingCart και να παραλείψετε την υλοποίηση αυτής της κλάσης
*/
public class ItemOrdered 
{
    //attributes
    private Item item;
    private int quantity;
    
    
    //constructor
    public ItemOrdered(Item item, int quantity)
    {
        this.item=item;
        this.quantity=quantity;
    }
    
    
    //setters
    public void setItem(Item item)
    {
        this.item=item;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
    
    
    //getters
    public Item getItem()
    {
        return item;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
}
