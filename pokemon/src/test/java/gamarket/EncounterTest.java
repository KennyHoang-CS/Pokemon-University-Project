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
    public void defaultContructorTest () {
        Player testPlayer = new Player(false, "david", "password");
        Encounter testEncounter = new Encounter(testPlayer);
        Pokemon wild = testEncounter.getWildPokemon();
        String wildString  = wild.toString("other");
        boolean isAvalidPokemon = false;
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("./databaseFiles/pokedata.txt");
        for( int i =0; i < collection.getNumPokes() ; i++) {
            Pokemon pokemonAtIndex = collection.getPokemonAtIndex(i);
            String iString = pokemonAtIndex.toString("other");
            if(wildString.equals(iString)) {
                isAvalidPokemon = true;
            }
        }

        assertEquals(true, isAvalidPokemon);
    }
}
