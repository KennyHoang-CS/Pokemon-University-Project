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
		
	/** The current number of POKEMONs in the array */
	private int numPOKEs;
		
	/** The array to contain the POKEMONs */
	private static Pokemon[] pokeArray; 
	private MoveCollection mc;
	
	/** 
	 * A constructor. It sets the number of Pokemons 'numPOKEs' to zero. 
	 * It allocates the Pokemon array 'pokeArray' to size 7. 
	 */
	public PokemonCollection(MoveCollection mc) {
		numPOKEs = 0;
		pokeArray = new Pokemon[7];	
		this.mc = mc; 
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
				System.out.println("Error has occurred from addOrModifyPokemon() function ...");
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
	 * What it does: the function uses a for loop to traverse the Pokemon array 
	 * and prints out the Pokemon Collection via its Pokemon class 
	 * object status: identity, offensive, and defensive. 
	 */
	public void printCollection()
	{
		for(int i = 0; i < numPOKEs; i++)
		{
			System.out.println(pokeArray[i].toString("other"));
		}
	}
	
	/**
	 * A public function printThemMoves of type void.
	 * What it does: it prints out the Pokemon's name along with its four moves.
	 * @return nothing. 
	 */
	public void printThemMoves()
	{
		for(int i = 0; i < numPOKEs; i++)
		{
			System.out.println(pokeArray[i].getIdentStats().getName() + "'s Move-Set:");
			pokeArray[i].printPokemonMoves();
			System.out.println(); 
		}
	}
	
	/**
	 * A public function fillDefaultMoves of type void.
	 * What it does: the function gives each Pokemon its default moves. 
	 * @return nothing. 
	 */
	public void setDefaultMoves()
	{	
		// Flareon
		Move m1 = mc.getMoveAtIndex(10);	// Fire Fang
		Move m2 = mc.getMoveAtIndex(9);		// Flare blitz
		Move m3 = mc.getMoveAtIndex(3); 	// Slam
		Move m4 = mc.getMoveAtIndex(4);		// Quick Attack 
		pokeArray[0].setDefaultMoves(m1, m2, m3, m4);
		
		// Gengar
		m1 = mc.getMoveAtIndex(8);			// Phantom Force
		m2 = mc.getMoveAtIndex(13);			// Physic
		m3 = mc.getMoveAtIndex(7);  		// Drain Punch
		m4 = mc.getMoveAtIndex(5);			// Shadow Ball
		pokeArray[1].setDefaultMoves(m1, m2, m3, m4);
		
		// Pikachu
		m1 = mc.getMoveAtIndex(0);			// Electric Ball	
		m2 = mc.getMoveAtIndex(1);			// Thunder
		m3 = mc.getMoveAtIndex(4);			// Quick Attack	
		m4 = mc.getMoveAtIndex(3);			// Slam
		pokeArray[2].setDefaultMoves(m1, m2, m3, m4);
		
		// Poliwrath 
		m1 = mc.getMoveAtIndex(2);			// Water Pulse	
		m2 = mc.getMoveAtIndex(12);			// Ice Punch	
		m3 = mc.getMoveAtIndex(13);			// Physic
		m4 = mc.getMoveAtIndex(14);			// Earthquake
		pokeArray[3].setDefaultMoves(m1, m2, m3, m4);
		
		// Sandslash
		m1 = mc.getMoveAtIndex(16);			// Slash	
		m2 = mc.getMoveAtIndex(17);			// Drill Run
		m3 = mc.getMoveAtIndex(18);			// Rock Slide
		m4 = mc.getMoveAtIndex(12);			// Bite
		pokeArray[4].setDefaultMoves(m1, m2, m3, m4);
	}
	
	/** Helper Function.
	 * Returns a Pokemon at the specified index. 
	 * @return this Pokemon. 
	 */
	public static Pokemon getPokemonAtIndex(int i)
	{
		return pokeArray[i];
	}
} 
