package gamarket;

public abstract class Item {
    String description = "Unknown Item";
    double buyCost =  0;
    double sellCost = 0;
    String type;

    public String getDescription () {
        return this.description;
    }

    public double getBuyCost () {
        return this.buyCost;
    }

    public double getSellCost () {
        return this.sellCost;
    }
    
    public void use () {}

	public String getType() {
        return this.type;
    }
}