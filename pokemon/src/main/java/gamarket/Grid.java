package gamarket;
import java.util.ArrayList;

import gamarket.Tile.Type;

/**
 * Grid class object that represents the board that the player is on.
 * Is made consists of tiles and keeps track of player position.
 */
public class Grid {
    private int playerX;
    private int playerY;
    private ArrayList<ArrayList<Tile>> gridState;
    private int gridXMax;
    private int gridYMax;

    Grid () {
        this.playerX = 0;
        this.playerY = 0; 
        this.gridXMax = 6;
        this.gridYMax = 6;
        this.gridState = generateGrid(gridXMax, gridYMax);
    }

    /**
     * @return the string representation of the Grid as a string
     *  tell player (x, y) position
     *  prints tile types and a P for the player position
     */
    @Override
    public String toString() {
        int rows = this.gridState.size();
        int cols = this.gridState.get(0).size();
        String result = "Player position is (" + this.playerX + ", " + this.playerY +") \n";
        result += "The grid State: \n";
        for(int i = 0; i < rows; i++) {
            //ArrayList<Tile> row = new ArrayList<Tile>();
            for(int j = 0; j < cols; j++) {

                if(this.playerX == j && this.playerY == i) {
                    result += "P "; 
                }
                else
                 {
                    Tile tileAtIJ = this.gridState.get(i).get(j);
                    result += tileAtIJ.toString() + " " ;
                }
                
            }
            result += "\n" ;
        }
        return result;
    }
    /**
     * Way to move the player. First checks if player can move in the choosen direction
     * w=up, s=down, a=left, d=right does nothing if invalid input
     * @param direction "wsda commands moves char in direction"
     */
    public void updateGrid(String direction) {
        switch (direction) {
            case "s":
                if(canMove(this.playerX, this.playerY+1)) {
                    setPlayerPosition(this.playerX, this.playerY+1);
                }
                break;
            case "w":
                if(canMove(this.playerX, this.playerY-1)) {
                    setPlayerPosition(this.playerX, this.playerY-1);
                }
                break;
            case "a":
                if(canMove(this.playerX-1, this.playerY)) {
                    setPlayerPosition(this.playerX-1, this.playerY);
                }
                break;
            case "d":
                if(canMove(this.playerX+1, this.playerY)) {
                    setPlayerPosition(this.playerX+1, this.playerY);
                }
                break;
            default:
                //invalid input
                break;
        }
    }
    /**
     * @return the playerPosition as an array [x, y]
     */
    public int[] getPlayerPosition() {
        int [] playerPosition = {this.playerX, this.playerY};
        return playerPosition;
    }


    /**
     * set players new x,y cordinate    
     * @param playerX x cordinate
     * @param playerY y cordinate
     *  
     */
    public void setPlayerPosition(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    /**
     * Checks if location can be moved to. 
     * Player cannot move out of the grid or to tiles that are not permeable 
     * @param x
     * @param y
     * @return True then player can move to spot false if not 
     */
    private boolean canMove (int x, int y) {
        if ( x < 0 || y < 0) {
            return false;
        }
        if ( x > gridXMax || y > gridYMax) {
            return false; 
        } 
        Tile tileAtXY = this.gridState.get(x).get(y);
        if (tileAtXY.getIsPermeable() == false) {
            return false;
        }

        return true;
    }

    /**
     * utility function to generate grid of given x, y demensions 
     * @param x max width of the grid
     * @param y max height of the grid
     * @return
     */
    private ArrayList<ArrayList<Tile>> generateGrid (int x , int y) {
        ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < x; i++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            for(int j = 0; j < y; j++) {
                Tile newTile = new Tile(Tile.Type.ROAD);
                row.add(newTile);
            }
            grid.add(row);
        }
        return grid;
    }
    /**
     * change Tile at x, y to a new type
     * @param x valid x cordinate
     * @param y valid y cordinate
     * @param newType
     */
	public void changeTile(int x, int y, Type newType) {
        Tile tileAtXY = this.gridState.get(x).get(y);
        tileAtXY.setType(newType);
	}
}