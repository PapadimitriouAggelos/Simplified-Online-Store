package item;

/*
Κληρονομεί από την κλάση Item και έχει επιπλέον color (string), tipSize (μέγεθος μύτης σε mm, double). 
Υλοποιεί την getDetails()
*/
public class Pen extends Item
{
    //attributes
    private String color;
    private double tipSize;
    
    
    //constructor
    public Pen(String name, int id, String description, double price, int stock, String color, double tipSize)
    {
        super(name, id, description, price, stock);
        
        this.color=color;
        this.tipSize=tipSize;
    }
    
    
    //setters
    public void setColor(String color) 
    {
        this.color = color;
    }

    public void setTipSize(double tipSize) 
    {
        this.tipSize = tipSize;
    }
    
    
    //getters
    public String getColor() 
    {
        return color;
    }

    public double getTipSize() 
    {
        return tipSize;
    }
    
    
    //υλοποίηση της getDetails()
    @Override
    public String getDetails()
    {
        return "Χρώμα: "+color+", Μέγεθος: "+tipSize;
    }
    
    
    //υλοποίηση της kindOfItem()
    @Override
    public String kindOfItem()
    {
        return "Pen";
    }
    
}
