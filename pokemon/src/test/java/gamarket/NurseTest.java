package gamarket;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NurseTest {
    @Test
    public void healTest(){
        Player player = new Player(false, "david", "password", true);
        for(int i = 0; i < player.getPokeTeam().getNumOfPokesInTeam(); i++){
            player.getPokeTeam().getPokemonAtIndex(i).getDefensiveStats().setHPCurrent(1);
        }

        Nurse nurse = new Nurse(player.getPokeTeam());
        nurse.heal();

        for(int i = 0; i < player.getPokeTeam().getNumOfPokesInTeam(); i++){
            assertEquals(player.getPokeTeam().getPokemonAtIndex(i).getDefensiveStats().getHP(), player.getPokeTeam().getPokemonAtIndex(i).getDefensiveStats().getHPCurrent());
        }

    }

}
