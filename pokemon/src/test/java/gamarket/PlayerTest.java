package gamarket;

import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    @Test
    public void newPlayer(){
        Player player = new Player(true,"name", "password");
        assertEquals(0, player.getBadges());
        assertEquals(0, player.getTotalPokemon());
        assertEquals(0.0, player.getMoney());
        assertEquals(0, player.getPokeTeam().getNumOfPokesInTeam());
    }

    @Test
    public void returningPlayer(){
        Player player = new Player(false,"david", "password", true);
        assertEquals(5, player.getBadges());
        assertEquals(5, player.getTotalPokemon());
        assertEquals(123.45, player.getMoney());
        assertEquals(2, player.getPokeTeam().getNumOfPokesInTeam());
    }

    @Test
    public void savePlayer() {
        Player player = new Player(true, "name", "password");
        player.saveData(true);

        Player playerTest = new Player(true, "name", "password");
        assertEquals("name", playerTest.getName());
        assertEquals("password", playerTest.getPassword());

        File file = new File("./databaseFiles/userProfiles/name_profile.txt");
        file.delete();
    }


}
