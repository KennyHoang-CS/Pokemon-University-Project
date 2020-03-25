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
        this.gridXMax = 6;
        this.gridYMax = 6;
        this.gridState = generateGrid(gridXMax, gridYMax);
    }
    /**
     * set up grid of x, y demensions and start player at playerStartX, playerStartY
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
        String result = "Grid demensions:" + Integer.toString(this.gridXMax) + "," + Integer.toString(this.gridYMax) +"\n";
        result +="Player position is:" + this.playerX + "," + this.playerY +"\n";
        result += "The grid State: \n";
        for(int i = 0; i < this.gridXMax; i++) {
            //ArrayList<Tile> row = new ArrayList<Tile>();
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
        Tile tileAtXY = getTile(x, y);
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
    public void save(String saveToFileName) {
		File file = new File(saveToFileName + "_gridData.txt");
		try {
			FileWriter writer = new FileWriter(file);
			
			writer.write(this.toString());
			
			//writer.write(str);
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
     * Grid demensions:xMax,yMax\n
     * Player position is:playerX,playerY\n
     * The grid State:\n
     * tileValue grid space separated 
     *  @param savedFileName - name of file wish to retreive
     */
    public void loadData(String savedFileName) {
        //add so easy to find and for gitignore
		File file = new File(savedFileName+ "_gridData.txt"); 
		String lineString;
		Scanner sc;
		try {
			sc = new Scanner(file); 
			while (sc.hasNextLine()){
                lineString = sc.nextLine();
                String[] splitString = lineString.split(":");
                
                switch(splitString[0]) {
                    case "Grid demensions":{
                        String[] demensions = splitString[1].split(",");
                        this.gridXMax = Integer.parseInt(demensions[0]);
                        this.gridYMax = Integer.parseInt(demensions[1]);
                        this.gridState = generateGrid(this.gridXMax, this.gridYMax);
                        break;
                    } 
                    case "Player position is": {
                        String[] demensions = splitString[1].split(",");
                        this.playerX = Integer.parseInt(demensions[0]);
                        this.playerY = Integer.parseInt(demensions[1]);
                        break;
                    } 
                    case "The grid State": {
                        for(int i = 0; i < this.gridYMax; i++) {
                            lineString = sc.nextLine();
                            String[] rowArr = lineString.split(" ");
                            for(int j = 0; j < this.gridXMax; j++) {
                                Tile.Type newType = Tile.charToType(rowArr[j].charAt(0));
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

	
}