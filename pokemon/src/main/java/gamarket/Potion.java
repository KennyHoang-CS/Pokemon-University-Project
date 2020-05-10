package gamarket;

public class Potion extends Item {
    
    private int healAmount = 20;

    /**
     * makes a new potion
     */
    public Potion () {
        this.description = "Heal target Pokemon \nby 20 HP";
        this.type = "Potion";
        this.buyCost = 300;
        this.sellCost = 150;
    }
    /**
     * returns the type of the item
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return the amount healed by the potion
     */
    public int getHealAmount () { 
        return this.healAmount;
    }
    /**
     * Heals the Target pokemon the heal amount
     * @param targetPokemon
     */
    public void use (Pokemon targetPokemon) {
        int hp = targetPokemon.getDefensiveStats().getHPCurrent();
        targetPokemon.getDefensiveStats().setHPCurrent(hp + 20);
    }
}