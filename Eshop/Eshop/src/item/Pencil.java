package item;

import enumeration.Type;

/*
Κληρονομεί από την κλάση Item και έχει επιπλέον tipSize (μέγεθος μύτης σε mm, double) και type: {H, B, HB}. 
Υλοποιεί την getDetails()
*/
public class Pencil extends Item
{
    //attributes
    private double tipSize;
    private Type type;
    
    
    //constructor
    public Pencil(String name, int id, String description, double price, int stock, double tipSize, Type type)
    {
        super(name, id, description, price, stock);
        
        this.type=type;
        this.tipSize=tipSize;
    }
    
    
    //setters
    public void setType(Type type) 
    {
        this.type = type;
    }

    public void setTipSize(double tipSize) 
    {
        this.tipSize = tipSize;
    }
    
    
    //getters
    public Type getType() 
    {
        return type;
    }

    public double getTipSize() 
    {
        return tipSize;
    }
    
    
    //υλοποίηση της getDetails()
    @Override
    public String getDetails()
    {
        return "Μέγεθος: "+tipSize+", Τύπος: "+type;
    }
    
    
    //υλοποίηση της kindOfItem()
    @Override
    public String kindOfItem()
    {
        return "Pencil";
    }
}
