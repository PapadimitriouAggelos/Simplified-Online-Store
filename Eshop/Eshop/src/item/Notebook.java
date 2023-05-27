package item;

/*
Κληρονομεί από την κλάση Item και έχει επιπλέον sections (αριθμός θεμάτων, int). Υλοποιεί την getDetails()
*/
public class Notebook extends Item
{
    //attributes
    private int sections;
    
    
    //constructor
    public Notebook(String name, int id, String description, double price, int stock, int sections)
    {
        super(name, id, description, price, stock);
        
        this.sections=sections;
    }
    
    
    //setter
    public void setSections(int sections) 
    {
        this.sections=sections;
    }

    
    //getter
    public int getSections() 
    {
        return sections;
    }

    
    //υλοποίηση της getDetails()
    @Override
    public String getDetails()
    {
        return "Αριθμός Θεμάτων: "+sections;
    }
    
    
    //υλοποίηση της kindOfItem()
    @Override
    public String kindOfItem()
    {
        return "Notebook";
    }
}
