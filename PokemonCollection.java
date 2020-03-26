import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonCollection {
		
	/** The current number of POKEMONs in the array */
	private int numPOKEs;
		
	/** The array to contain the POKEMONs */
	private Pokemon[] pokeArray;
		
	/** The name of the data file that contains poke data */
	private String sourceName;
		
	/** Boolean flag to indicate whether the Pokemon collection was
		    modified since it was last saved. */
	private boolean modified;
		
	/** PokemonCollection Constructor **/
	public PokemonCollection() {
		numPOKEs = 0;
		pokeArray = new Pokemon[7];
	}
		
	// A method to add/modify a DVD to the array. 
	public void addOrModifyPokemon(String id, String name, String gender, String type, String level,
								   String HP, String attack, String defense, String spATK, 
								   String spDEF, String speed) 
	{
		
		try {	// running time error prevention.   
				// double the size of the array if the current array is full. 
				if(numPOKEs == pokeArray.length-1)
				{
					pokeArray = Arrays.copyOf(pokeArray, pokeArray.length * 2);
				}
				
				// Since we have read in data from a file as all strings, we need to convert them back to the correspondence integer types. 
				int myID = Integer.parseInt(id);
				int myLevel = Integer.parseInt(level);
				int myHP = Integer.parseInt(HP);
				int myAttack = Integer.parseInt(attack);
				int myDefense = Integer.parseInt(defense);
				int mySPATK = Integer.parseInt(spATK);
				int mySPDEF = Integer.parseInt(spDEF);
				int mySpeed = Integer.parseInt(speed);
				
				// A flag to determine if the Pokemon already exists. 
				boolean pokeExists = false; 
				
				// traverse the array to see if the Pokemon already "exists" in the collection.
				for(int i = 0; i < numPOKEs && pokeExists != true; i++)
				{
					// if both Pokemons are equal, then the Pokemon "exists". 
					if(pokeArray[i].getName().compareToIgnoreCase(name) == 0)
					{
						// proceed to modify the attributes. 
						pokeArray[i].setID(myID);
						pokeArray[i].setGender(gender);
						pokeArray[i].setType(type);
						pokeArray[i].setLevel(myLevel);
						pokeArray[i].setHP(myHP);
						pokeArray[i].setATK(myAttack);
						pokeArray[i].setDEF(myDefense);
						pokeArray[i].setSPAtk(mySPATK);
						pokeArray[i].setSPDef(mySPDEF);
						pokeArray[i].setSpeed(mySpeed);
						pokeExists = true; 
						modified = true;	// modified is true because we have modified the Pokemon attributes.
					}
				}
				// if the new Pokemon does NOT already exist in the collection, then add the new Pokemon to the pokeArray. 
				if(pokeExists == false) 
				{
					Pokemon newPoke = new Pokemon(myID, name, gender, type, myLevel, myHP, myAttack, myDefense, mySPATK, mySPDEF, mySpeed);
					pokeArray[numPOKEs++] = newPoke;
				}
				}catch(NumberFormatException e)
				{
					System.out.println("Error has occurred from addOrModifyPokemon() function ...");
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
			         
		       /** read line by line with each pokemon: id, name, gender, type, level, 
		        	HP, Attack, Defense, Sp. Atk, Sp. Def, Speed separated by commas.
		       **/
		        while(line != null && !line.isEmpty())
		        {
		            String[] pokeData = line.split(",");
	                addOrModifyPokemon(pokeData[0], pokeData[1], pokeData[2], pokeData[3], pokeData[4], 
	                 		 			pokeData[5], pokeData[6], pokeData[7], pokeData[8], 
		            		 			pokeData[9], pokeData[10]);			             
		            line = br.readLine();
		        }
			          
		        // close the buffered reader.
		        br.close();
		        }catch(IOException e) {
			           e.printStackTrace();
			    }
			}
			
	// A method to print out all the Pokemon object data
	public String toString()
	{
		String temp = "";
			
		for(int i = 0; i < numPOKEs; i++)
		{
			temp += Integer.toString(pokeArray[i].getID()) + "/" +
	   			   pokeArray[i].getName() + "/" +
				   pokeArray[i].getGender() + "/" +
				   pokeArray[i].getType() + "/" +
				   Integer.toString(pokeArray[i].getLevel()) + "/" +
				   Integer.toString(pokeArray[i].getHP()) + "/" +
				   Integer.toString(pokeArray[i].getATK()) + "/" +
				   Integer.toString(pokeArray[i].getDEF()) + "/" +
				   Integer.toString(pokeArray[i].getSPATK()) + "/" +
				   Integer.toString(pokeArray[i].getSPDEF()) + "/" +
				   Integer.toString(pokeArray[i].getSpeed()) + "\n";
		}
	return temp;
	}
		
	// A method to return the total # of Pokemons
	public int getNumPokes()
	{
		return numPOKEs; 
	}
		
} // end of PokemonCollection class
