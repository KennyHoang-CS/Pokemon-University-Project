package gamarket;

import java.util.*;

public class Driver {

    public static void main(String args[]){

        Player player = new Player(true, "joe", "password");
        player.setEmail("joe@email.com");
        player.saveData();


        //player = new Player(false, "david", "password");
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("pokemon/databaseFiles/pokedata.txt");
        Encounter test = new Encounter(player, collection);
        test.battle();
        int badgeAmt = player.getBadges();
        badgeAmt--;
        player.setBadges(badgeAmt);

        player.saveData();

    }
}
