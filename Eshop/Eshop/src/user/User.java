package user;

/*
Αναπαριστά έναν χρήστη και χαρακτηρίζεται από το όνομά του και το email του. Έχει ως υποκλάσεις τις Buyer και Owner. 
Δεν μπορεί να έχει στιγμιότυπα.
*/
abstract public class User 
{
    //attributes
    private String name;
    private String email;
    
    //constructor
    public User(String name, String email)
    {
        this.name=name;
        this.email=email;
    }
    
    //setters
    public void setName(String name)
    {
        this.name=name;
    }
    
    public void setEmail(String email)
    {
        this.email=email;
    }
    
    //getters
    public String getName()
    {
        return name;
    }
    
    public String getEmail()
    {
        return email;
    }
}
