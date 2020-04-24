package gamarket;

public class Potion extends Item {
    
    private int healAmount = 20;

    public Potion () {
        this.description = "Heal target Pokemon 20 Hp";
        this.type = "Potion";
    }

    public String getType() {
        return this.type;
    }

    public int getHealAmount () { 
        return this.healAmount;
    }

    public void use (Pokemon targetPokemon) {
        int hp = targetPokemon.getDefensiveStats().getHPCurrent();
        targetPokemon.getDefensiveStats().setHPCurrent(hp + 20);
    }
}