package gamarket;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
/**
 * Unit test for simple App.
 */
public class ClientTest
{
    Client client;
    @Test
    public void startUpTest () {
        client = Client.getInstance();
        client.startMenu();
        client.startMenu.setUsername("david");
        client.startMenu.setPassword("password1");
        client.startMenu.verifyUser("david", "password1");
        assertEquals(true, client.startMenu.getVerified());

        client.startMenu.setUsername("david");
        client.startMenu.setPassword("password1");
        client.startMenu.verifyUser("david", "password5");
        assertEquals(false, client.startMenu.getVerified());

        client.startMenu.setUsername("david");
        client.startMenu.setPassword("password1");
        client.startMenu.verifyUser("davidddd", "password1234");
        assertEquals(false, client.startMenu.getVerified());
    }

}
