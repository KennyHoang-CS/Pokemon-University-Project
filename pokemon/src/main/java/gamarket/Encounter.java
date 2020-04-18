package gamarket;

public class Encounter {
    private Pokemon wildPokemon;
    private Pokemon activePlayerPokemon;
    private Team playerTeam; 
    
    Encounter (Player player) {
        this.activePlayerPokemon = getPlayerActivePokemon();
        this.wildPokemon = generateWildPokemon();
    }

    private Pokemon getPlayerActivePokemon() {
        //TODO actually take from Player's team 
        //
        
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("./databaseFiles/pokedata.txt");
        return collection.getPokemonAtIndex(4);
    }

    public static void main(String args[]) {
        // Encounter test = new Encounter();
        // Pokemon wild = test.getWildPokemon();

        // System.out.println(wild.toString("other"));

    }

    private Pokemon generateWildPokemon() {
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("./databaseFiles/pokedata.txt");
        int randomInt =  (int) (Math.random() * ( collection.getNumPokes() - 0 ));
        Pokemon generated =  collection.getPokemonAtIndex(randomInt);
        return generated;
    }

    public void fight() {
        
    }

    public void  catchPokemon () {

    }

    public void switchPokemon () {

    }
    
    public void useItem (Item choosenItem) {
        choosenItem.use();
    }

    public void run () {
        
    }
    public Pokemon getWildPokemon () {
        return this.wildPokemon;
    } 
}