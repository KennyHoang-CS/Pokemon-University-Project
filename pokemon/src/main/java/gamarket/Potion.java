package gamarket;

public class Potion extends Item {
    
    private int healAmount = 20;

    public Potion () {
        this.description = "Heal target Pokemon 20 Hp";
    }

    public int getHealAmount () { 
        return this.healAmount;
    }

    public void use (Pokemon targetPokemon) {
        // int hp = targetPokemon.getCurrentHp();
        // targetPokemon.setCurrentHp(hp + 20);
    }
}