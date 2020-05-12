package gamarket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PokemonTeamTest {
    
    @Test
    public void teamSize()
    {
        MoveCollection mc = new MoveCollection(true);
        mc.loadData(true);
        PokemonCollection pc = new PokemonCollection(mc, true);
        pc.loadData(true);
        Team player = new Team(mc);
        boolean status = false; 

        // Team size can be up to 6. 
        for(int i = 0; i < 6; i++)
        {
            player.addPokemon(pc.getPokemonAtIndex(0));
        }

        if((player.getNumOfPokesInTeam() >= 0) && (player.getNumOfPokesInTeam() < 7))
        {
            status = true; 
        }
         assertEquals(true, status);
    }

    @Test
    public void switchPokemon()
    {
        MoveCollection mc = new MoveCollection(true);
        mc.loadData(true);
        PokemonCollection pc = new PokemonCollection(mc, true);
        pc.loadData(true);
        Team player = new Team(mc);

        // Team size can be up to 6. 
        for(int i = 0; i < 5; i++)
        {
            player.addPokemon(pc.getPokemonAtIndex(0)); // Flareon is index 0
        }
        
        player.addPokemon(pc.getPokemonAtIndex(1)); // Gengar is index 1

        // switch the pokemon.
        player.switchPokemonInTeam(1, 6);

        assertEquals("Gengar", player.getPokemonAtIndex(0).getIdentStats().getName().toString().trim());
        assertEquals("Flareon", player.getPokemonAtIndex(5).getIdentStats().getName().toString().trim());
    }
}