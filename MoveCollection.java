import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MoveCollection {
	
	/** The current number of MOVEs in the array */
	private int numMoves; 
	
	/** The array to contain the MOVEs */
	private Move moveArray[]; 
	
	/** The name of the data file that contains MOVEs-set data */
	private String sourceName;
		
	/** Boolean flag to indicate whether the MOVEs-set collection was
		    modified since it was last saved. */
	private boolean modified;
	
	public MoveCollection()
	{
		numMoves = 0; 
		moveArray = new Move[7];
	}
	
	// A method to add/modify a move to the array. 
	public void addOrModifyMove(String name, String type, String category, String damage)
	{
		try {	// running time error prevention.   
				// double the size of the array if the current array is full. 
			if(numMoves == moveArray.length-1)
			{
				moveArray = Arrays.copyOf(moveArray, moveArray.length * 2);
			}
				
			// change the Strings back into type Integer. 
			int myDmg = Integer.parseInt(damage);
			boolean moveExists = false; 
				
			// traverse the array to see if the MOVE already "exists" in the collection.
			for(int i = 0; i < numMoves && moveExists != true; i++)
				{
					// if both MOVEs are equal, then the MOVE "exists". 
					if(moveArray[i].getMoveName().compareToIgnoreCase(name) == 0)
					{
						// proceed to modify the move-type, move-type-category, move-damage. 
						moveArray[i].setMoveType(type);
						moveArray[i].setMoveCategory(category);
						moveArray[i].setMoveDmg(myDmg);
						moveExists = true; 
						modified = true;	// modified is true because move-type, move-category, move-damage was changed. 
					}
				}

				// if the new MOVE does NOT already exist in the collection, then add the new MOVE to the array. 
				if(moveExists == false) 
				{
					Move newMove = new Move(name, type, category, myDmg);
					moveArray[numMoves++] = newMove;
				}
				}catch(NumberFormatException e)
				{
					System.out.println("Error has occurred from MoveCollection() function...");
				}
			}
		
	// A method to load in data from a given file.
	public void loadData(String filename) 
	{
		// create a buffered reader to read in text.
	    BufferedReader br;
	      
	    try {
	         // to read in data from the file.
	         br = new BufferedReader(new FileReader(filename));
	         sourceName = filename;
	         String line = br.readLine();
	          
	         // read line by line with each token: move-name, move-type, move-type-category, move-damage separated by commas.
	         while(line != null && !line.isEmpty())
	         {
	              String[] moveData = line.split(",");
	              addOrModifyMove(moveData[0], moveData[1], moveData[2], moveData[3]);
	              line = br.readLine();
             }
	          
	         // close the buffered reader.
	         br.close();
	       }catch(IOException e) {
	           e.printStackTrace();
	       }
    }

	// A method to print out all the MOVE object data
	public String toString()
	{
		String temp = "";
			
		for(int i = 0; i < numMoves; i++)
		{
			temp += moveArray[i].getMoveName() + "/" +
					moveArray[i].getMoveType() + "/" +
					moveArray[i].getMoveTypeCat() + "/" +
					Integer.toString(moveArray[i].getMoveDamage()) + "\n";
		}
	return temp;
	}
		
	public int getNumMoves()
	{
		return numMoves;
	}
		
		
} // end of MoveCollection class
