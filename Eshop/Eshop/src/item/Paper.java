package item;

/*
Κληρονομεί από την κλάση Item και έχει επιπλέον weight (σε γραμμάρια, int) και pages (int)
*/
public class Paper extends Item
{
    //attributes
    private int weight;
    private int pages;
    
    
    //constructor
    public Paper(String name, int id, String description, double price, int stock, int weight, int pages)
    {
        super(name, id, description, price, stock);
        
        this.pages=pages;
        this.weight=weight;
    }
    
    
    //setter
    public void setWeight(int weight) 
    {
        this.weight = weight;
    }

    public void setPages(int pages) 
    {
        this.pages = pages;
    }

    
    //getter
    public int getWeight() 
    {
        return weight;
    }

    public int getPages() 
    {
        return pages;
    }

    
    //υλοποίηση της getDetails()
    @Override
    public String getDetails()
    {
        return "Βάρος: "+weight+", Αριθμός Σελίδων: "+pages;
    }
    
    
    //υλοποίηση της kindOfItem()
    @Override
    public String kindOfItem()
    {
        return "Paper";
    }
    
}
