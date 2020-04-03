package gamarket;

import org.junit.Test;

import gamarket.Tile.Type;

import static org.junit.Assert.assertEquals;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
/**
 * Unit test for Encounter class
 */
public class EncounterTest 
{
    public static int[] startSpot = {0, 0};

    @Test
    public void defaultContructorTest () {
        Encounter testEncounter = new Encounter();
    }
}
