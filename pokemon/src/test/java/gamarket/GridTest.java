package gamarket;

import org.junit.Test;

import gamarket.Tile.Type;

import static org.junit.Assert.assertEquals;

import java.io.File;

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

    @Test
    public void save_Load_test () {
        Grid testGrid = new Grid(8, 8, 7, 7);
        testGrid.changeTile(3, 2, Type.WATER);

        testGrid.save("./save_Loadtest");
        Grid freshGrid = new Grid();
        freshGrid.loadData("./save_Loadtest");
        
        System.out.println(freshGrid.toString());
        assertEquals(Type.WATER, freshGrid.getTile(3, 2).getType() );
        
        int[] expectedLoc = { 7, 7};
        assertArrayEquals(expectedLoc, freshGrid.getPlayerPosition());

        File file = new File("./save_Loadtest_gridData.txt");
        file.delete();
    }
}
