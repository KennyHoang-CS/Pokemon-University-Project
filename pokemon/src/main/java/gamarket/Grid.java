package gamarket;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * default constructor mainly used for testing
     */
    Grid () {
        this.playerX = 0;
        this.playerY = 0;
        this.gridXMax = 10;
        this.gridYMax = 10;
        this.gridState = generateGrid(gridXMax, gridYMax);
    }
    /**
     * set up grid of x, y dimensions and start player at playerStartX, playerStartY
     * @param xMax any valid int
     * @param yMax any valid int
     * @param playerStartX any int i | i > 0 && i < xMax
     * @param playerStartY any int i | i > 0 && i < yMax
     */
    Grid (int xMax, int yMax, int playerStartX, int playerStartY ) {
        this.gridXMax = xMax;
        this.gridYMax = yMax;
        this.playerX = playerStartX;
        this.playerY = playerStartY;
        this.gridState = generateGrid(xMax, yMax);
    }
    /**
     * @return the string representation of the Grid as a string
     *  tell player (x, y) position
     *  prints tile types and a P for the player position
     */
    @Override
    public String toString() {
        String result = "Grid dimensions:" + Integer.toString(this.gridXMax) + "," + Integer.toString(this.gridYMax) +"\n";
        result +="Player position is:" + this.playerX + "," + this.playerY +"\n";
        result += "The grid State: \n";
        for(int i = 0; i < this.gridXMax; i++) {
            for(int j = 0; j < this.gridYMax; j++) {
                Tile tileAtIJ = this.gridState.get(i).get(j);
                if(this.playerX == j && this.playerY == i) {
                    result += tileAtIJ + "P "; 
                }
                else
                 {
                    
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
        Tile old = getTile(this.playerX, this.playerY);
        old.togglePlayer();
        this.playerX = playerX;
        this.playerY = playerY;
        Tile newPosition = getTile(playerX, playerY);
        newPosition.togglePlayer();

    }

    /**
     * Checks if location can be moved to. 
     * Player cannot move out of the grid or to tiles that are not permeable 
     * @param x
     * @param y
     * @return True then player can move to spot false if not 
     */
    public boolean canMove (int x, int y) {
        if ( x < 0 || y < 0) {
            return false;
        }
        if ( x >= gridXMax || y >= gridYMax) {
            return false; 
        } 
        Tile tileAtXY = getTile(x, y);
        if (tileAtXY.getIsPermeable() == false) {
            return false;
        }

        return true;
    }

    /**
     * Checks if location can be interacted with
     * @param x
     * @param y
     * @return True then player can interact with the tile ahead
     */
    public boolean canInteract (int x, int y) {
        if ( x < 0 || y < 0) {
            return false;
        }
        if ( x >= gridXMax || y >= gridYMax) {
            return false;
        }
        Tile tileAtXY = getTile(x, y);
        if (tileAtXY.getType() == Type.STORENPC || tileAtXY.getType() == Type.NURSENPC ) {
            return true;
        }
        return false;
    }

    public Type getType(int x, int y){
        Tile tileAtXY = getTile(x, y);
        return tileAtXY.getType();

    }


    /**
     * utility function to generate grid of given x, y dimensions 
     * @param x max width of the grid
     * @param y max height of the grid
     * @return
     */
    private ArrayList<ArrayList<Tile>> generateGrid (int x , int y) {
        ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < x; i++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            for(int j = 0; j < y; j++) {
                Tile newTile = new Tile(Type.UNRECOGNIZED, false);
                row.add(newTile);
            }
            grid.add(row);
        }
        return grid;
    }
    /**
     * Change Tile at x, y to a new type
     * @param x valid x cordinate
     * @param y valid y cordinate
     * @param newType must be valid or will assign Unrecognized 
     */
	public void changeTile(int x, int y, Type newType) {
        getTile(x,y).setType(newType);
    }

    /**
     * Utility Function for retrieving Tiles at X,Y 
     * @param x valid x grid coordinate 
     * @param y valid y gird coordinate
     * @return The tile at X,Y
     */
    public Tile getTile(int x, int y) {
		return this.gridState.get(x).get(y);
	}
    
    /**
     * Converts grid to a string and saves to the given filename.
     * Appends _gridData.txt to filename
     * @param saveToFileName - name wanted for gridData file
     */
    public void save(String saveToFileName, boolean test) {
        File file;
        if(test){
            file = new File("./databaseFiles/gridFiles/" + saveToFileName + "_gridData.txt");
        }else{
            file = new File("./pokemon/databaseFiles/gridFiles/" + saveToFileName + "_gridData.txt");
        }
		try {
			FileWriter writer = new FileWriter(file);
			
			writer.write(this.toString());

			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }

    /**
     * loads and transforms current grid to data for filename given to
     * function. Appends _gridData.txt to filename to help gitignore
     * assumes that file exists and is properly formatted.
     * Proper Fomat 
     * Grid dimensions:xMax,yMax\n
     * Player position is:playerX,playerY\n
     * The grid State:\n
     * tileValue grid space separated 
     *  @param savedFileName - name of file wish to retreive
     */
    public void loadData(String savedFileName, boolean test) {
        //add so easy to find and for gitignore
        File file;
        if(test){
            file = new File("./databaseFiles/gridFiles/" + savedFileName+ "_gridData.txt");
        }else{
            file = new File("./pokemon/databaseFiles/gridFiles/" + savedFileName+ "_gridData.txt");
        }
        String lineString;
		Scanner sc;
		try {
			sc = new Scanner(file); 
			while (sc.hasNextLine()) {
                lineString = sc.nextLine();
                String[] splitString = lineString.split(":");
                
                switch(splitString[0]) {
                    case "Grid dimensions":{
                        String[] dimensions = splitString[1].split(",");
                        this.gridXMax = Integer.parseInt(dimensions[0]);
                        this.gridYMax = Integer.parseInt(dimensions[1]);
                        this.gridState = generateGrid(this.gridXMax, this.gridYMax);
                        break;
                    } 
                    case "Player position is": {
                        String[] dimensions = splitString[1].split(",");
                        this.playerX = Integer.parseInt(dimensions[0]);
                        this.playerY = Integer.parseInt(dimensions[1]);
                        break;
                    } 
                    case "The grid State": {
                        for(int i = 0; i < this.gridYMax; i++) {
                            lineString = sc.nextLine();
                            String[] rowArr = lineString.split(" ");
                            for(int j = 0; j < this.gridXMax; j++) {
                                Tile.Type newType = Tile.charToType(rowArr[j]);
                                changeTile(i, j, newType); 
                            }
                        }
                        break;
                    }
                    default:
                }

				
			}
			sc.close();
		} catch (Exception e) {
            System.out.println(e);
			System.out.println("error reading file");
		}		
	}

    public int getYMax() {
        return gridYMax;
    }

    public int getXMax() {
        return gridXMax;
    }
}