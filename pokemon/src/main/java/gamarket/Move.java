package gamarket;

/**
 * Represents the traits within a Move.
 */
public class Move {
	
	/**
	 * The Move's name.
	 */
	private String moveName; 
	
	/**
	 * The Move's type.
	 */
	private String moveType;
	
	/**
	 * The Move's type category.
	 */
	private String moveTypeCategory;
	
	/**
	 * The Move's damage. 
	 */
	private int moveDmg; 
	
	/**
	 * The Move Constructor. 
	 * It changes the new Move traits: name, type, category, and damage.
	 * @param moveName This is the new Move's name. Should be a name for the move(Tackle).
	 * @param moveType This is the new Move's type. Should be a type for the move(Normal).
	 * @param moveTypeCategory This is the new Move's type category. Should be a category for the move(Physical).
	 * @param moveDmg This is the new Move's damage. Should be a damage for the move(Damage). 
	 */
	public Move(String moveName, String moveType, String moveTypeCategory, int moveDmg)
	{
		this.moveName = moveName;
		this.moveType = moveType;
		this.moveTypeCategory = moveTypeCategory;
		this.moveDmg = moveDmg; 
	}
		
	/**
	 * The public function setMoveName of type void.
	 * It changes the Move's name. 
	 * @param thisMoveName This is the new Move's name. Should have the Move's name. 
	 */
	public void setMoveName(String thisMoveName) { this.moveName = thisMoveName; }
	
	/**
	 * The public function setMoveType of type void.
	 * It changes the Move's type.
	 * @param thisMoveType This is the new Move's type. Should have the Move's type.
	 */
	public void setMoveType(String thisMoveType) { this.moveType = thisMoveType; }
	
	/**
	 * The public function setMoveCategory of type void.
	 * It changes the Move's category. 
	 * @param thisMoveTypeCat This is the new Move's Category. Should have the Move's category. 
	 */
	public void setMoveCategory(String thisMoveTypeCat) { this.moveTypeCategory = thisMoveTypeCat; }
	
	/**
	 * The public function setMoveDmg of type void.
	 * It changes the Move's damage. 
	 * @param thisMoveDmg This is the new Move's damage. Should have the Move's damage. 
	 */
	public void setMoveDmg(int thisMoveDmg) { this.moveDmg = thisMoveDmg; }
	
	/**
	 * The public function getMoveName of type String.
	 * It returns the Move's name as a String. 
	 * @return this Move's name. 
	 */
	public String getMoveName() { return moveName; }
	
	/**
	 * The public function getMoveType of type String.
	 * It returns the Move's type as a String
	 * @return this Move's type.
	 */
	public String getMoveType() { return moveType; }
	
	/**
	 * The public function getMoveTypeCat of type String.
	 * It returns the Move's category as a String.
	 * @return this Move's category. 
	 */
	public String getMoveTypeCat() { return moveTypeCategory; }
	
	/**
	 * The public function getMoveDamage of type int.
	 * It returns the Move's damage as an integer.
	 * @return this Move's damage. 
	 */
	public int getMoveDamage() { return moveDmg; }
	
	/**
	 * The public function toString of type String.
	 * It returns the Move traits: name, type, category, and damage as a String. 
	 * @return this Move's traits. 
	 */
	public String toString()
	{
		String temp = this.moveName + " / " +
					  this.moveType + " / " +
				      this.moveTypeCategory + " / " + 
					  this.moveDmg; 
		return temp;
	}
} 

	



