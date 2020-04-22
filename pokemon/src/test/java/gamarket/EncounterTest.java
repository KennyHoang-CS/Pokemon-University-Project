package gamarket;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for Encounter class
 */
public class EncounterTest 
{
    public static int[] startSpot = {0, 0};

    @Test
    public void wildGeneratedPokemonIsValid () {
        Player testPlayer = new Player(false, "david", "password");
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("./databaseFiles/pokedata.txt");
        Encounter testEncounter = new Encounter(testPlayer, collection);
        Pokemon wildTest = testEncounter.getWildPokemon();
        boolean wildIsValid = isAvalidPokemon(wildTest);

        assertEquals(true, wildIsValid);
    }
    @Test
    public void resolveAttack_Gives_Expected_Value () {
        Player testPlayer = new Player(false, "david", "password");
        PokemonCollection collection = gPokemonCollection();
        Encounter testEncounter = new Encounter(testPlayer, collection);
        Pokemon testAttacker = collection.getPokemonAtIndex(2);
        Move move0 = new Move("tackle", "normal", "physical", 20);
        
    }

    public PokemonCollection gPokemonCollection () {
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("./databaseFiles/pokedata.txt");
        return collection;
    }
    // @Test
    // public void 

    public boolean isAvalidPokemon(Pokemon poke) {
        boolean isAvalidPokemon = false;
        String pokeString = poke.toString("other");
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("./databaseFiles/pokedata.txt");
        for( int i =0; i < collection.getNumPokes() ; i++) {
            Pokemon pokemonAtIndex = collection.getPokemonAtIndex(i);
            String iString = pokemonAtIndex.toString("other");
            if(pokeString.equals(iString)) {
                isAvalidPokemon = true;
            }
        }
        return isAvalidPokemon;
    }
}
