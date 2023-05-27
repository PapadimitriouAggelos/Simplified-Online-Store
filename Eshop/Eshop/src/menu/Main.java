package menu;

import enumeration.Type;
import eshop.Eshop;
import exception.BuyerAlreadyExistException;
import exception.ItemAlreadyExistException;
import exception.NotEnoughStockException;
import item.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.*;

/*
Στη main αρχικά θα δημιουργήσετε ένα αντικείμενο EShop και θα εισάγετε 3 είδη από την κάθε κλάση προϊόντων, 
έτσι ώστε να είναι αντιπροσωπευτικά, έναν Owner, δύο Buyers και μια παραγγελία για τον καθένα, με αρκετά είδη 
στο ShoppingCart.
*/
public class Main 
{
    public static void main(String[] args) 
    {
        Owner owner=new Owner("Angelos", "1");
        
        Buyer buyer1=new Buyer("Giorgos", "2");
        Buyer buyer2=new Buyer("Olympia", "3");
        
        Eshop shop=new Eshop("BookStore", owner);
        
        shop.buyersList.add(buyer1);
        shop.buyersList.add(buyer2);
        
        //String name, int id, String description, double price, int stock, int sections
        Notebook notebook1=new Notebook("Notebook1", 1, "For children", 10, 100, 200);
        Notebook notebook2=new Notebook("Notebook2", 2, "For adults", 20, 100, 200);
        Notebook notebook3=new Notebook("Notebook3", 3, "For all", 30, 100, 200);
        
        //String name, int id, String description, double price, int stock, int weight, int pages
        Paper paper1=new Paper("Paper1", 4, "For children", 40, 100, 100, 100);
        Paper paper2=new Paper("Paper2", 5, "For adults", 50, 100, 100, 100);
        Paper paper3=new Paper("Paper3", 6, "For all", 60, 100, 100, 100);
        
        //String name, int id, String description, double price, int stock, String color, double tipSize
        Pen pen1=new Pen("Pen1", 7, "For children", 70, 100, "Red", 300);
        Pen pen2=new Pen("Pen2", 8, "For adults", 80, 100, "White", 300);
        Pen pen3=new Pen("Pen3", 9, "For all", 90, 100, "Black", 300);
        
        //String name, int id, String description, double price, int stock, double tipSize, Type type
        Pencil pencil1=new Pencil("Pencil1", 10, "For children", 100, 200, 200, Type.B);
        Pencil pencil2=new Pencil("Pencil2", 11, "For adults", 110, 200, 200, Type.H);
        Pencil pencil3=new Pencil("Pencil3", 12, "For all", 120, 200, 200, Type.HB);
        
        try
        {
            shop.addItem(notebook1);
            shop.addItem(notebook2);
            shop.addItem(notebook3);
            shop.addItem(paper1);
            shop.addItem(paper2);
            shop.addItem(paper3);
            shop.addItem(pen1);
            shop.addItem(pen2);
            shop.addItem(pen3);
            shop.addItem(pencil1);
            shop.addItem(pencil2);
            shop.addItem(pencil3);
        }
        catch(ItemAlreadyExistException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        try 
        {
            buyer1.placeOrder(notebook1, 10);
            buyer1.placeOrder(paper1, 10);
            buyer1.placeOrder(pen1, 10);
            buyer1.placeOrder(pencil1, 10);
            
            buyer2.placeOrder(notebook2, 10);
            buyer2.placeOrder(paper2, 10);
            buyer2.placeOrder(pen2, 10);
            buyer2.placeOrder(pencil2, 10);
            buyer2.placeOrder(notebook3, 10);
            buyer2.placeOrder(paper3, 10);
            buyer2.placeOrder(pen3, 10);
            buyer2.placeOrder(pencil3, 10);
            
        }
        catch (NotEnoughStockException ex) 
        {
            System.err.println(ex.getMessage());
        }
        
        Menu menu=new Menu();
        
        menu.login(shop);
    }
    
}
