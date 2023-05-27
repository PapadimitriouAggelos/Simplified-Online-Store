package user;

/*
Υποκλάση της User. Αναπαριστά ένα χρήστη που είναι ο ιδιοκτήτης του eshop. 
Διαθέτει ένα επιπλέον πεδίο boolean isAdmin true
*/
public class Owner extends User
{
    //attributes
    private boolean isAdmin=false;
    
    //constructor
    public Owner(String name, String email)
    {
        super(name, email);
        
        this.isAdmin=true;
    }
    
    //setter
    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin=isAdmin;
    }
    
    //getter
    public boolean getIsAdmin()
    {
        return isAdmin;
    }
}
