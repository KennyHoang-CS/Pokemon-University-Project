package gamarket;

public abstract class Item {
    String description = "Unknown Item";
    double cost =  0;
    
    public String getDescription () {
        return this.description;
    }

    public double getCost () {
        return this.cost;
    }
    
    public void use () {}
}