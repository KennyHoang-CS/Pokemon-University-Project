package gamarket;

public class PokeBall extends Item {
    private double catchRate = .70;
    public PokeBall () {
        this.description = "Use to catch Pokemon";
        this.cost = 1.50;
    }

    public double getCatchRate () {
        return this.catchRate;
    }

    public void use (Pokemon targetPokemon) {
        //return true;
    }
}