package gamarket;

/**
 * Represents a Pokemon in the Pokemon universe. A Pokemon can have many traits: identify status, offensive status,
 * and defensive status.  
 */
public class Pokemon {
	
	/** 
	 * 	To hold the id, name, gender, type, and level of the Pokemon
	 */
	private Stats.IdentifyStats IS; 
	
	/**
	 * To hold the attack, sp. attack, and speed of the Pokemon
	 */
	private Stats.OffensiveStats OS;
	
	/**
	 * To hold the health, defense, and sp. defense of the Pokemon
	 */
	private Stats.DefensiveStats DS;
	
	/** 
	 * A constructor that changes the identity, offensive, and defensive status of this Pokemon. 
	 * @param IS This is the Pokemon identity status. Should have id, name, gender, type, and level.
	 * @param OS This is the Pokemon offensive status. Should have attack, special attack, and speed.
	 * @param DS This is the Pokemon defensive status. Should have health, defense, and special defense.
	 */
	Pokemon(Stats.IdentifyStats IS, Stats.OffensiveStats OS, Stats.DefensiveStats DS) 
	{
		this.IS = IS;
		this.OS = OS;
		this.DS = DS; 
	}
	
	/**
	 * A constructor that changes the identity, offensive, and defensive status of this Pokemon.
	 * Sets all the private class variables: IS, OS, and DS to null. 
	 */
	Pokemon()
	{
		this.IS = null;
		this.OS = null;
		this.DS = null; 
	}
	
	/**
	 * Changes the identity status of this Pokemon.
	 * @param IS The Pokemon's new identity status. Should have id, name, gender, type, and level. 
	 */
	public void setIdentStats(Stats.IdentifyStats IS) { this.IS = IS; }
	
	/**
	 * Changes the offensive status of this Pokemon.
	 * @param OS The Pokemon's new offensive status. Should have attack, special attack, and speed. 
	 */
	public void setOffeniveStats(Stats.OffensiveStats OS) { this.OS = OS; }
	
	/**
	 * Changes the defensive status of this Pokemon.
	 * @param DS The Pokemon's new defensive status. Should have health, defense, and special defense. 
	 */
	public void setDefensiveStats(Stats.DefensiveStats DS) { this.DS = DS; }

	/**
	 * Gets id, name, gender, type, and level of this Pokemon. 
	 * @return this Pokemon's identity status. 
	 */
	public Stats.IdentifyStats getIdentStats() { return IS; }
	
	/**
	 * Gets attack, special attack, and speed of this Pokemon.
	 * @return this Pokemon's offensive status.
	 */
	public Stats.OffensiveStats getOffeniveStats() { return OS; }
	
	/**
	 * Gets health, defense, and special defense of this Pokemon. 
	 * @return this Pokemon's defensive status. 
	 */
	public Stats.DefensiveStats getDefensiveStats() { return DS; }
	
	/**
	 * The function toString() of the Pokemon class. 
	 * It creates a variable temp of type String to hold the Pokemon's identity, offensive, 
	 * and defensive status. It converts integer data into type String for temp able to contain. 
	 * @return this Pokemon's id, name, gender, type, level, attack, special attack, speed, health
	 * , defense, and special defense.  
	 */
	public String toString()
	{
		String temp = "Pokemon ID: " + Integer.toString(this.IS.getID()) + " / " 
					   + "Name: " + this.IS.getName() + " / "
					   + "Gender: " + this.IS.getGender() + " / "
					   + "Type: " + this.IS.getType() + " / "
				       + "Level: " + Integer.toString(this.IS.getLevel()) + " / " 
				       + "Attack: " + Integer.toString(this.OS.getATK()) + " / "
				       + "Sp. Attack: " + Integer.toString(this.OS.getSPATK()) + " / "
				       + "Speed: " + Integer.toString(this.OS.getSpeed()) + " / "
				       + "Health: " + Integer.toString(this.DS.getHP()) + " / "
				       + "Defense: " + Integer.toString(this.DS.getDEF()) + " / "
					   + "Sp. Defense: " + Integer.toString(this.DS.getSPDEF()) + "\n";
	    
		return temp; 
	}
} 
