package gamarket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneralPokemonTest {
    
    @Test
    public void testLoadData()
    {
        MoveCollection mc = new MoveCollection(true);
        mc.loadData(true);
        PokemonCollection pc = new PokemonCollection(mc, true);
        pc.loadData(true);
        
        // Check if pokemon names got loaded correctly. 
        String p1 = pc.getPokemonAtIndex(0).getIdentStats().getName();
        assertEquals("Flareon", p1.toString().trim());
        p1 = pc.getPokemonAtIndex(1).getIdentStats().getName();
        assertEquals("Gengar", p1.toString().trim());
        p1 = pc.getPokemonAtIndex(2).getIdentStats().getName();
        assertEquals("Pikachu", p1.toString().trim());
        p1 = pc.getPokemonAtIndex(3).getIdentStats().getName();
        assertEquals("Poliwrath", p1.toString().trim());
        p1 = pc.getPokemonAtIndex(4).getIdentStats().getName();
        assertEquals("Sandslash", p1.toString().trim());
    }

    @Test 
    public void checkMoves()
    {
        MoveCollection mc = new MoveCollection(true);
        mc.loadData(true);
        PokemonCollection pc = new PokemonCollection(mc, true);
        pc.loadData(true);

        // check if the first pokemon move-set got loaded correctly.
        Pokemon p1 = pc.getPokemonAtIndex(0);
        assertEquals("Fire Fang", p1.getMove(0).getMoveName().toString().trim());
        assertEquals("Flare Blitz", p1.getMove(1).getMoveName().toString().trim());
        assertEquals("Slam", p1.getMove(2).getMoveName().toString().trim());
        assertEquals("Quick Attack", p1.getMove(3).getMoveName().toString().trim());
    }
}