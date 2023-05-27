package eshop;

import exception.BuyerAlreadyExistException;
import exception.CartIsEmptyException;
import exception.ItemAlreadyExistException;
import item.Item;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.Buyer;
import user.Owner;

/* Αντιστοιχεί στο ηλεκτρονικό κατάστημα και περιλαμβάνει πεδίο name, πεδίο owner τύπου Owner, 
μια λίστα με τα διαθέσιμα προϊόντα itemsList και μια λίστα με τους πελάτες του καταστήματος buyersList. */
public class Eshop 
{
    //attributes
    private String name;
    private Owner owner;
    
    
    //λίστα με τα διαθέσιμα προϊόντα itemsList
    public ArrayList <Item> itemsList=new ArrayList <Item> ();
    
    //λίστα με τους πελάτες του καταστήματος buyersList
    public ArrayList <Buyer> buyersList=new ArrayList <Buyer> ();
    
    
    //constructor
    public Eshop(String name, Owner owner)
    {
        this.name=name;
        this.owner=owner;
    }
    
    
    //setters
    public void setName(String name)
    {
        this.name=name;
    }
    
    public void setOwner(Owner owner)
    {
        this.owner=owner;
    }
    
    
    //getters
    public String getName()
    {
        return name;
    }
    
    public Owner getOwner()
    {
        return owner;
    }
    
    
    //addItem(): προσθέτει ένα προϊόν στο κατάστημα. Γίνεται χειρισμός εξαίρεσης αν υπάρχει ήδη
    public void addItem(Item item) throws ItemAlreadyExistException
    {
        boolean flag=true;
        
        for(Item k:itemsList)
            if(item.getId()==k.getId())
                flag=false;
        
        if(flag)
        {
            itemsList.add(item);
        }
        else
            throw new ItemAlreadyExistException("Το προιόν "+item.getName()+" υπάρχει ήδη στο κατάστημα!");
    }
    
    
    //getItemById(): ανακτά ένα Item από την itemsList με βάση τον κωδικό του
    public Item getItemById(int id)
    {
        for(Item item:itemsList)
            if(id==item.getId())
                return item;
        
        return null;
    }
    

    //removeItem(): αφαιρεί ένα προϊόν
    public void removeItem(int id)
    {
        for(Item item:itemsList)
            if(id==item.getId())
                itemsList.remove(item);
    }
    
    
    //addBuyer(): προσθέτει έναν υποψήφιο πελάτη στο κατάστημα. Γίνεται χειρισμός εξαίρεσης, αν υπάρχει ήδη.
    public void addBuyer(Buyer buyer) throws BuyerAlreadyExistException
    {
        boolean flag=true;
        
        for(Buyer b:buyersList)
            if(buyer.getEmail().equals(b.getEmail()))
                flag=false;
        
        if(flag)
        {
            buyersList.add(buyer);
        }
        else
            throw new BuyerAlreadyExistException("Ο πελάτης "+buyer.getName()+" υπάρχει ήδη στο κατάστημα!");
    }
    
    
    //removeBuyer(): αφαιρεί έναν πελάτη από το κατάστημα
    public void removeBuyer(String email)
    {
        for(Buyer buyer:buyersList)
            if(email.equals(buyer.getEmail()))
                buyersList.remove(buyer);
    }
    
    
    //updateItemStock(): τροποποιεί τη διαθέσιμη ποσότητα ενός προϊόντος
    public void updateItemStock(int id, int quantity)
    {
        for(Item item:itemsList)
            if(id==item.getId())
                item.setStock(item.getStock()+quantity);
    }
    

    //showCategories (): Εμφανίζει τις υπάρχουσες κλάσεις των προϊόντων (pen, ...)
    public void showCategories() 
    {
        int sumNotebook=0, sumPaper=0, sumPen=0, sumPencil=0;
        
        for(Item item:itemsList)
        {
            if(item.kindOfItem().equals("Notebook"))
                sumNotebook++;
            
            if(item.kindOfItem().equals("Paper"))
                sumPaper++;
            
            if(item.kindOfItem().equals("Pen"))
                sumPen++;
            
            if(item.kindOfItem().equals("Pencil"))
                sumPencil++;
        }
        
        System.out.println("1. Pen ("+sumPen+")");
        System.out.println("2. Pencil ("+sumPencil+")");
        System.out.println("3. Notebook ("+sumNotebook+")");
        System.out.println("4. Paper ("+sumPaper+")");
    }
    
    
    //showProductsInCategory(): Εμφανίζει λίστα με τα προϊόντα μιας συγκεκριμένης κατηγορίας
    public void showProductsInCategory()
    {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Πληκτρολογήστε το προιόν που επιθυμείτε για την εμφάνιση των συγκεκριμένων προιόντων: ");
        String ans=sc.next();
        
        System.out.println("Λίστα προιόντων: ");
        
        for(Item item:itemsList)
            if(ans.equals(item.kindOfItem()))
                System.out.println(item.toString());
    }
    
    
    //showProduct(): Εμφανίζει τις συνολικές πληροφορίες για ένα προϊόν
    public void showProduct(int id)
    {
        Scanner sc=new Scanner(System.in);
        
        for(Item item:itemsList)
            if(id==item.getId())
                System.out.println(item.toString());
       
    }

    //checkStatus(): Εμφανίζει τους πελάτες, τους πόντους και την κατηγορία τους
    public void checkStatus()
    {
        int sum=0;
        
        for(Buyer buyer:buyersList)
        {
            sum++;
            System.out.println(sum+". Όνομα: "+buyer.getName()+", Email: "+buyer.getEmail()+", Πόντοι: "+buyer.getBonus()+", Κατηγορία: "+buyer.getBuyerCategory());
        }
            
    }
    
    //μέθοδος που επιστρέφει έναν Buyer με βάση το email του
    public Buyer getBuyerByEmail(String email)
    {
        for(Buyer buyer:buyersList)
            if(email.equals(buyer.getEmail()))
                return buyer;
        
        return null;
    }
}
