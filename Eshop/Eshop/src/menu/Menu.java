package menu;

import eshop.Eshop;
import eshop.ShoppingCart;
import exception.BuyerAlreadyExistException;
import exception.CartIsEmptyException;
import exception.NotEnoughStockException;
import item.Item;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.Buyer;
import user.Owner;

public class Menu 
{
    //μέθοδος που χαρακτηρίζει το Μενού του Buyer
    public void buyerMenu(Eshop shop, String email, ShoppingCart cart)
    {
        Scanner sc=new Scanner(System.in);
        
        Buyer buyer;
        buyer=shop.getBuyerByEmail(email);
        
        System.out.println("Καλησπέρα "+buyer.getName());
        System.out.println("Πληροφορίες πελάτη: ");
        System.out.println("Όνομα: "+buyer.getName()+", Email: "+buyer.getEmail()+", Πόντοι: "+buyer.getBonus()+", Κατηγορία: "+buyer.getBuyerCategory());
        
        System.out.println();
        
        do
        {
            System.out.println("=====MENU=====");
            System.out.println("1. Browse Store");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Back");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            System.out.println();
            
            System.out.println("Παρακαλώ πληκτρολογήστε μια γραμμή από το Μενού: ");
            int ans=sc.nextInt();
            
            
           //Browse Store
            if(ans==1)
            {
                System.out.println("===== Eshop "+shop.getName()+" =====");
                
                System.out.println();
                
                shop.showCategories();
                
                shop.showProductsInCategory();
                
                System.out.println("Δώστε το id του προιόντος: ");
                int id=sc.nextInt();
                
                shop.showProduct(id);
                
                System.out.println();
                
                System.out.println("Θέλετε να προσθέσετε το προιόν στο καλάθι? Πληκρολογήστε Y(yes) ή N(no): ");
                String ans2=sc.next();
                
                if(ans2.equals("Y") || ans2.equals("y"))
                {
                    System.out.println("Συμπληρώστε την αιτούμενη ποσότητα: ");
                    int posotita=sc.nextInt();
                    
                    for(Item item:shop.itemsList)
                      if(id==item.getId())
                      {
                          try
                          {
                              buyer.placeOrder(item, posotita);
                          }
                          catch(NotEnoughStockException ex)
                          {
                              System.err.println(ex.getMessage());
                          }
                      }
                }
            }
            
            
            //View Cart
            if(ans==2)
            {
                try 
                {
                    cart.showCart(buyer);
                } 
                catch (CartIsEmptyException ex) 
                {
                    System.err.println(ex.getMessage());
                }
                
                System.out.println();
                
                do
                {
                    System.out.println("=====MENU=====");
                    System.out.println("1. Επιλέξτε μια γραμμή από την παραγγελία");
                    System.out.println("2. Επιλογή αδειάσματος καλαθιού");
                    System.out.println("3. Checkout");
                    System.out.println("4. Back");
                    
                    System.out.println();
                    
                    System.out.println("Παρακαλώ πληκτρολογήστε μια γραμμή από το Μενού: ");
                    int ans2=sc.nextInt();
                    
                    //Επιλέξτε μια γραμμή από την παραγγελία
                    if(ans2==1)
                    {
                        System.out.println("Πληκτρολογήστε την γραμμή που επιθυμείτε από το καλάθι αγορών:");
                        int grammi=sc.nextInt();
                        
                        for(int i=1; i<=cart.orderList.size(); i++)
                            if(grammi==i)
                            {
                                do
                                {
                                    System.out.println("=====MENU=====");
                                    System.out.println("1. Διαγράψτε τη συγκεκριμένη παραγγελία");
                                    System.out.println("2. Αλλάξτε την ποσότητα της συγκεκριμένης παραγγελίας");
                                    System.out.println("3. Back");

                                    System.out.println();

                                    System.out.println("Παρακαλώ πληκτρολογήστε μια γραμμή από το Μενού: ");
                                    int ans3=sc.nextInt();

                                    //Διαγράψτε τη συγκεκριμένη παραγγελία
                                    if(ans3==1)
                                    {
                                        cart.removeItemOrdered(cart.orderList.get(i).getItem().getId());
                                    }

                                    //Αλλάξτε την ποσότητα της συγκεκριμένης παραγγελίας
                                    if(ans3==2)
                                    {
                                        System.out.println("Παρακαλούμε εισάγετε ποσότητα: ");
                                        int q=sc.nextInt();
                                        
                                        try 
                                        {
                                            cart.changeItemOrderedQuantity(shop, cart.orderList.get(i).getItem().getId(), q);
                                        } 
                                        catch (NotEnoughStockException ex) 
                                        {
                                            System.err.println(ex.getMessage());
                                        }
                                    }

                                    //Back
                                    if(ans3==3)
                                    {
                                        break;
                                    }

                                }
                                while(true);
                            }
                    }
                    
                    //Επιλογή αδειάσματος καλαθιού
                    if(ans2==2)
                    {
                        cart.orderList.clear();
                    }
                    
                    //Checkout
                    if(ans2==3)
                    {
                        try 
                        {
                            cart.checkout(buyer);
                        }
                        catch (CartIsEmptyException ex) 
                        {
                            System.err.println(ex.getMessage());
                        }
                    }
                    
                    //Back
                    if(ans2==4)
                    {
                        break;
                    }
            
                }
                while(true);
            }
            
            //Checkout
            if(ans==3)
            {
                try 
                {
                    cart.checkout(buyer);
                }
                catch (CartIsEmptyException ex) 
                {
                    System.err.println(ex.getMessage());
                }
            }
            
            //Back
            if(ans==4)
                break;
            
            //Logout
            if(ans==5)
            {
                System.out.println("Θέλετε να αποσυνδεθείτε από το κατάστημα? Πληκτρολογήστε Y(yes) ή N(no) αντίστοιχα:");
                String ans2=sc.next();
                
                if(ans2.equals("Y") || ans2.equals("y"))
                {
                    System.out.println("Επιτυχής έξοδος από το κατάστημα");
                    break;
                }
            }
            
            //Exit
            if(ans==6)
               System.exit(0);
        }
        while(true);
    }
    
    
    //μέθοδος που χαρακτηρίζει το Μενού του Owner
    public void ownerMenu(Eshop shop, String email)
    {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Καλησπέρα "+shop.getOwner().getName());
        System.out.println("Πληροφορίες Owner: ");
        System.out.println("Όνομα: "+shop.getOwner().getName()+", Email: "+shop.getOwner().getEmail());
        
        System.out.println();
        
        do
        {
            System.out.println("=====MENU=====");
            System.out.println("1. Browse Store");
            System.out.println("2. Check Status");
            System.out.println("3. Back");
            System.out.println("4. Logout");
            System.out.println("5. Exit");

            System.out.println();
            
            System.out.println("Παρακαλώ πληκτρολογήστε μια γραμμή από το Μενού: ");
            int ans=sc.nextInt();
            
            //Browse Store
            if(ans==1)
            {
                System.out.println("===== Eshop "+shop.getName()+" =====");
                
                System.out.println();
                
                shop.showCategories();
                
                shop.showProductsInCategory();
                
                System.out.println("Δώστε το id του προιόντος: ");
                int id=sc.nextInt();
                
                shop.showProduct(id);
                
                System.out.println();
                
                System.out.println("Θέλετε να τροποποιήσετε το συγκεκριμένο προιόν? Πληκρολογήστε Y(yes) ή N(no): ");
                String ans2=sc.next();
                
                if(ans2.equals("Y") || ans2.equals("y"))
                {
                    System.out.println("Παρακαλούμε εισάγετε ποσότητα: ");
                    int quantity=sc.nextInt();
                    
                    shop.updateItemStock(id, quantity);
                }
                
            }
            
            
            //Check Status
            if(ans==2)
            {
                shop.checkStatus();
                
                System.out.println("Επιλέξτε έναν συγκεκριμένο πελάτη πληκτρολογώντας τον αριθμό της λίστας: ");
                int grammi=sc.nextInt();
                
                for(int i=1; i<=shop.buyersList.size(); i++)
                    if(grammi==i)
                    {
                        try 
                        {
                            shop.buyersList.get(i).getMyCart().showCart(shop.buyersList.get(i));
                        } 
                        catch (CartIsEmptyException ex) 
                        {
                            System.err.println(ex.getMessage());
                        }
                        
                        System.out.println("Θέλετε να διαγράψετε τον συγκεκριμένο πελάτη? Πληκτρολογήστε Y(yes) ή N(no): ");
                        String ans2=sc.next();
                        
                        if(ans2.equals("y") || ans2.equals("Y"))
                            shop.buyersList.remove(shop.buyersList.get(i));
                    }
            }
            
            //Back
            if(ans==3)
                break;
            
            //Logout
            if(ans==4)
            {
                System.out.println("Θέλετε να αποσυνδεθείτε από το κατάστημα? Πληκτρολογήστε Y(yes) ή N(no) αντίστοιχα:");
                String ans3=sc.next();
                
                if(ans3.equals("Y") || ans3.equals("y"))
                {
                    System.out.println("Επιτυχής έξοδος από το κατάστημα");
                    break;
                }
            }
            
            //Exit
            if(ans==5)
                System.exit(0);
            
        }
        while(true);
    }
    
    /*
    Διαμορφώστε κατάλληλο μενού σε command line όπου αρχικά θα ζητείται το email. Το σύστημα ταυτοποιεί τον χρήστη.
    Αν το mail που χρησιμοποιήθηκε δεν αντιστοιχεί στον Owner ή σε κάποιον χρήστη που βρίσκεται στη buyersList, 
    ερωτάται να επιθυμεί να εγγραφεί και προστίθεται στην buyersList ως νέος Buyer. Στη συνέχεια 
    του εμφανίζεται το εξής μενού επιλογών, ανάλογα αν είναι Buyer ή Owner:
    */
    //μέθοδος που διαχειρίζεται το Μενού του χρήστη
    public void login(Eshop shop)
    {
        Scanner sc=new Scanner(System.in);
        
        do
        {
            
            boolean flag=true;

            System.out.println("Εισάγετε το email σας: ");
            String email=sc.next();

            for(Buyer buyer:shop.buyersList)
                if(email.equals(buyer.getEmail()))
                {
                    flag=false;
                    buyerMenu(shop, email, buyer.getMyCart());
                }

            if(email.equals(shop.getOwner().getEmail()))
            {
                flag=false;
                ownerMenu(shop, email);
            }

            if(flag)
            {
                System.out.println("Δεν είστε εγγεγραμμένος στο κατάστημα");
                System.out.println("Για εγγραφή στο κατάστημα πατήστε Y(yes) ή N(no) αντίστοιχα: ");
                String ans4=sc.next();

                if(ans4.equals("Y") || ans4.equals("y"))
                {
                    System.out.println("Πληκτρολογήστε το όνομα σας: ");
                    String name=sc.next();

                    System.out.println("Πληκτρολογήστε το email σας: ");
                    String emailagain=sc.next();

                    Buyer b=new Buyer(name, emailagain);

                    try 
                    {
                        shop.addBuyer(b);
                    } 
                    catch (BuyerAlreadyExistException ex) 
                    {
                        System.err.println(ex.getMessage());
                    }
                }

            }
        }
        while(true);
    }
    
}
