package gamarket;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class BagTest {
    @Test
    public void potionTest(){
        Player player = new Player(false, "david", "password", true);
        player.getPokeTeam().getPokemonAtIndex(0).getDefensiveStats().setHPCurrent(254);
        int originalPotionAmt = player.getBag().getQty("potions");

        player.getBag().usePotion(player.getPokeTeam().getPokemonAtIndex(0));
        originalPotionAmt--;

        assertEquals(274, player.getPokeTeam().getPokemonAtIndex(0).getDefensiveStats().getHPCurrent());
        assertEquals(originalPotionAmt, player.getBag().getQty("potions"));
    }

    @Test
    public void loadTest(){
        Bag bag = new Bag();
        bag.loadData("david",true);
        assertEquals(5, bag.getQty("pokeball"));
        assertEquals(3, bag.getQty("potion"));
    }

    @Test
    public void itemAddRemoveSaveTest(){
        Bag bag = new Bag();
        bag.loadData("david", true);

        bag.addItem("pokeball",5);
        bag.removeItem("potion", 3);

        bag.saveData("test",true);
        bag.loadData("test", true);

        assertEquals(10, bag.getQty("pokeballs"));
        assertEquals(0, bag.getQty("potions"));

        File file = new File("./databaseFiles/bagFiles/test_bag.txt");
        file.delete();
    }


}
