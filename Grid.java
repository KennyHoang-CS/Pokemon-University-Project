import java.util.ArrayList;

public class Grid {
    private int playerX;
    private int playerY;
    private ArrayList<ArrayList<Tile>> gridState;
    //private gridValue


    Grid () {
        this.playerX = 3;
        this.playerY = 0; 
        this.gridState = generateGrid(6, 5);
    }

    public static void main(String[] args) {
		
		Grid testGrid = new Grid();
        
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