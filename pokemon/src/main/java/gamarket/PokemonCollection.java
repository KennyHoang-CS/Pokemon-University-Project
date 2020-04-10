package gamarket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Represents a collection of Pokemon class objects. 
 * The PokemonCollection is a collection that can hold more 
 * than one Pokemon class object. 
 */
public class PokemonCollection {
		
	/** The current number of POKEMONs in the Pokemon Collection. */
	private int numPOKEs;
	
	/** The current number of POKEMONS in the player team. */
	private int numTEAM; 
		
	/** The array to contain the entire Pokemon that exists in the game */
	private Pokemon[] pokeArray;
		
	/** 
	 * A constructor. It sets the number of Pokemons 'numPOKEs' to zero. 
	 * It allocates the Pokemon array 'pokeArray' to size 7. 
	 */
	public PokemonCollection() {
		numPOKEs = 0;
		numTEAM = 0; 
		pokeArray = new Pokemon[7];		
	}
		
	/**
	 * The public function 'addPokemon' of void type.
	 * It adds a new Pokemon class object to the Pokemon Collection. 
	 * @param myPoke This Pokemon identity, offensive, and defensive status.
	 * It should have id, name, gender, type, level, attack, special attack, speed,
	 * health, defense, and special defense. 
	 */
	public void addPokemon(Pokemon myPoke) 
	{
		
		try {	// running time error prevention.   
				// double the size of the array if the current array is full. 
				if(numPOKEs == pokeArray.length-1)
				{
					pokeArray = Arrays.copyOf(pokeArray, pokeArray.length * 2);
				}
				pokeArray[numPOKEs++] = myPoke;	
			}catch(NumberFormatException e)
			{
				System.out.println("Error has occurred from addPokemon() function ...");
			}
	}
		
	/**
	 * A public function loadData of type void. 
	 * The function uses a BufferedReader to read in data separated by commas in a given text file.
	 * @param filename This is the data text file name (pokedata.txt). 
	 * The file should have id, name, gender, type, level, attack, special attack, speed, health, 
	 * defense, special defense. 
	 */
	public void loadData(String filename) 
	{
		// create a buffered reader to read in text.
	    BufferedReader br;
		      
	    try {
	    	// to read in data from the file.
		        br = new BufferedReader(new FileReader(filename));
		        String line = br.readLine();
			         
		       /** read line by line with each pokemon: id, name, gender, type, level, 
		        	HP, Attack, Defense, Sp. Atk, Sp. Def, Speed separated by commas.
		       **/
		        while(line != null && !line.isEmpty())
		        {
		        	String[] pokeData = line.split(",");
	               
		        	// this will read in Identification Stats: ID, name, gender, type
		        	Stats.IdentifyStats myIS = new Stats().new IdentifyStats(Integer.parseInt(pokeData[0]),
		        															  pokeData[1],
		        															  pokeData[2],
		        															  pokeData[3],
		        															  Integer.parseInt(pokeData[4]));
		        	
		        	// this will read in Offensive Stats: attack, sp. attack, speed
		        	Stats.OffensiveStats myOS = new Stats().new OffensiveStats(Integer.parseInt(pokeData[5]),
	                														   Integer.parseInt(pokeData[6]),
	                														   Integer.parseInt(pokeData[7]));
	               
	                // this will read in Defensive Stats: health, defense, sp. defense
		        	Stats.DefensiveStats myDS = new Stats().new DefensiveStats(Integer.parseInt(pokeData[8]),
	                														   Integer.parseInt(pokeData[9]),
	                														   Integer.parseInt(pokeData[10]));
		        	
	                // create the Pokemon object with all the stats we just read in: Identification, Offensive, Defensive stats. 
	                Pokemon myPoke = new Pokemon(myIS, myOS, myDS);
					
	                // add the pokemon object to the pokeArray. 
		        	addPokemon(myPoke);  	         
	                line = br.readLine();
		        }
		        // close the buffered reader.
		        br.close();
		        }catch(IOException e) {
			           e.printStackTrace();
			    }
			}
	
	/**
	 * A public function getNumPokes of type int. 
	 * The function returns the total number of Pokemons. 
	 * @return this total number of Pokemons. 
	 */
	public int getNumPokes()
	{
		return numPOKEs; 
	}
	
	/**
	 * A public function printCollection of type void.
	 * The function uses a for loop to traverse the Pokemon array 
	 * and prints out the Pokemon Collection via its Pokemon class 
	 * object status: identity, offensive, and defensive. 
	 */
	public void printCollection()
	{
		for(int i = 0; i < numPOKEs; i++)
		{
			System.out.println(pokeArray[i].toString());
		}
	}
	
	/** Helper Function.
	 * Returns a Pokemon at the specified index. 
	 * @return this Pokemon. 
	 */
	public Pokemon getPokemonAtIndex(int i)
	{
		return pokeArray[i];
	}
} 
