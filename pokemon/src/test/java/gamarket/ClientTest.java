package gamarket;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class ClientTest
{
    Client client;
    @Test
    public void startUpTest () {
        Client client = Client.getInstance();
        client.startMenu();
        client.startMenuGUI.setUsername("david");
        client.startMenuGUI.setPassword("password1");
        client.startMenuGUI.verifyUser("david", "password1");
        assertEquals(true, client.startMenuGUI.getVerified());

        client.startMenuGUI.setUsername("david");
        client.startMenuGUI.setPassword("password1");
        client.startMenuGUI.verifyUser("david", "password5");
        assertEquals(false, client.startMenuGUI.getVerified());

        client.startMenuGUI.setUsername("david");
        client.startMenuGUI.setPassword("password1");
        client.startMenuGUI.verifyUser("davidddd", "password1234");
        assertEquals(false, client.startMenuGUI.getVerified());
    }

}
