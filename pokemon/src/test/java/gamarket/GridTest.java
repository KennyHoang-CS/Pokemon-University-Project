package gamarket;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
/**
 * Unit test for simple App.
 */
public class GridTest 
{
    
    @Test
    public void playerStartPostiion_is_0_0 () {
        Grid testGrid = new Grid();
        assertEquals(0, testGrid.getPlayerPosition()[0]);
        assertEquals(0, testGrid.getPlayerPosition()[1]);
    }
    @Test
    public void s_Makes_Player_Go_Down () {
        Grid testGrid = new Grid();
        testGrid.updateGrid("s");
        int[] oneDown = {0, 1};
        assertArrayEquals( oneDown, testGrid.getPlayerPosition());
    }
    
    @Test
    public void w_Makes_Player_Go_Up () {
        Grid testGrid = new Grid();
        testGrid.updateGrid("s");
        testGrid.updateGrid("w");
        int[] oneUp = {0 , 0};
        assertArrayEquals(oneUp, testGrid.getPlayerPosition() );
    }

    @Test
    public void d_Makes_Player_Go_right () {
        Grid testGrid = new Grid();
        testGrid.updateGrid("d");
        int[] oneRight = {1, 0};
        assertArrayEquals(oneRight, testGrid.getPlayerPosition());
    }

    @Test
    public void a_Makes_Player_Go_left () {
        Grid testGrid = new Grid();
        testGrid.updateGrid("d");
        testGrid.updateGrid("a");
        int[] oneRight = {0, 0};
        assertArrayEquals(oneRight, testGrid.getPlayerPosition());
    }
}
