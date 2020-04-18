package gamarket;

import java.util.*;

public class Driver {

    public static void main(String args[]){

        Player player = new Player(true, "joe", "password");
        player.setEmail("joe@email.com");
        player.saveData();


        player = new Player(false, "david", "password");

        int badgeAmt = player.getBadges();
        badgeAmt--;
        player.setBadges(badgeAmt);

        player.saveData();

    }
}
