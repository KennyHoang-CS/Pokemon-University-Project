package gamarket;
import java.util.ArrayList;

public class Grid {
    private int playerX;
    private int playerY;
    private ArrayList<ArrayList<Tile>> gridState;
    //private gridValue
    private int gridXMax;
    private int gridYMax;

    Grid () {
        this.playerX = 3;
        this.playerY = 0; 
        this.gridXMax = 6;
        this.gridYMax = 6;
        this.gridState = generateGrid(gridXMax, gridYMax);
    }

    public static void main(String[] args) {
		
		Grid testGrid = new Grid();
        
        System.out.print(testGrid.toString());
        testGrid.updateGrid("d");
        System.out.println("new grid");
        System.out.print(testGrid.toString());
        testGrid.updateGrid("s");
        System.out.println("new grid");
        System.out.print(testGrid.toString());
        testGrid.updateGrid("a");
        System.out.println("new grid");
        System.out.print(testGrid.toString());
        testGrid.updateGrid("w");
        System.out.println("new grid");
        System.out.print(testGrid.toString());
        
	}
    @Override
    public String toString() {
        int rows = this.gridState.size();
        int cols = this.gridState.get(0).size();
        String result = "";
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
     * @param playerX x cordinate
     * @param playerY y cordinate
     *  set players new x,y cordinate    
     */
    public void setPlayerPosition(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }
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
    private ArrayList<ArrayList<Tile>> generateGrid (int x , int y) {
        ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < x; i++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            for(int j = 0; j < y; j++) {
                Tile newTile = new Tile(Tile.Type.ROAD, true);
                row.add(newTile);
            }
            grid.add(row);
        }
        return grid;
    }
}