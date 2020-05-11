package gamarket;

import org.junit.Test;
import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void purchaseTest(){
        Player player = new Player(false, "david", "password", true);
        int qty = player.getBag().getQty("potion");

        Store store = new Store(player.getBag(), player.getMoney());
        boolean purchased = store.purchase("potion",2);

        assertEquals(true, purchased);
        assertEquals(qty+2, player.getBag().getQty("potion"));
    }

    @Test
    public void sellTest(){
        Player player = new Player(false, "david", "password", true);
        int qty = player.getBag().getQty("potion");

        Store store = new Store(player.getBag(), player.getMoney());
        boolean sold = store.sell("potion",2);

        assertEquals(true, sold);
        assertEquals(qty-2, player.getBag().getQty("potions"));
    }
}
