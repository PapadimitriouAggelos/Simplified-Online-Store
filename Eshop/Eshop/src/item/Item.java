package item;

/*
Αφορά ένα προϊόν και περιλαμβάνει πεδία name (string), price (double), description (string), stock (int), 
id (int) που αντιπροσωπεύουν το όνομα, την τιμή σε Ευρώ, την περιγραφή, τη διαθέσιμη ποσότητα και 
τον κωδικό του προϊόντος αντίστοιχα. Η κλάση Item δεν μπορεί να έχει στιγμιότυπα. 
*/
abstract public class Item 
{
    //attributes
    private String name;
    private int id;
    private String description;
    private double price;
    private int stock;
    
    
    //constructor
    public Item(String name, int id, String description, double price, int stock) 
    {
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    
    
    //setters
    public void setName(String name) 
    {
        this.name = name;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }

    public void setStock(int stock) 
    {
        this.stock = stock;
    }
    
    
    //getters
    public String getName() 
    {
        return name;
    }

    public int getId() 
    {
        return id;
    }

    public String getDescription() 
    {
        return description;
    }

    public double getPrice() 
    {
        return price;
    }

    public int getStock() 
    {
        return stock;
    }
    
    
    //μέθοδο String getBasicInfo(), που επιστρέφει τις προηγούμενες πληροφορίες
    public String getBasicInfo()
    {
        return "Προιόν: "+name+", ID: "+id+", Περιγραφή: "+description+", Τιμή: "+price+", Απόθεμα: "+stock;
    }
    
    
    //μεθόδο String getDetails()
    abstract String getDetails();
    
    
    //μέθοδο String toString() (@override) που θα καλεί τις άλλες δύο, ώστε να τυπώνει τις συνολικά 
    //διαθέσιμες πληροφορίες ενός προϊόντος κατάλληλα μορφοποιημένες
    @Override
    public String toString()
    {
       return this.getBasicInfo()+", "+this.getDetails();
    }
    
    
    abstract public String kindOfItem();
    
    
}
