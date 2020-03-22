package gamarket;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
/**
 * Unit test for simple App.
 */
public class GridTest 
{
    public static int[] startSpot = {0, 0};

    @Test
    public void playerStartPostiion_is_0_0 () {
        Grid testGrid = new Grid();
        assertArrayEquals(startSpot, testGrid.getPlayerPosition());
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

    @Test
    public void player_Cant_Move_off_Grid () {
        Grid testGrid = new Grid();
        testGrid.updateGrid("w");
        assertArrayEquals(startSpot, testGrid.getPlayerPosition());

        testGrid.updateGrid("a");
        assertArrayEquals(startSpot, testGrid.getPlayerPosition());
    }

    @Test
    public void player_Cant_Move_into_nonPerme () {
        Grid testGrid = new Grid(); 
        testGrid.changeTile(1, 0, Tile.Type.TREE);
        testGrid.updateGrid("d");
        assertArrayEquals(startSpot, testGrid.getPlayerPosition());
    }
}
