package gamarket;

public class Move {

	// Pokemon attribute move-set, each Pokemon can hold 4 moves at most. 
	private String moveName; 
	private String moveType;
	private String moveTypeCategory; 
	private int moveDmg; 

	public Move(String moveName, String moveType, String moveTypeCategory, int moveDmg)
	{
		this.moveName = moveName;
		this.moveType = moveType;
		this.moveTypeCategory = moveTypeCategory;
		this.moveDmg = moveDmg; 
	}

	// setters for Pokemon attribute move-set, each Pokemon can hold 4 moves at most. 
	public void setMoveName(String thisMoveName) { this.moveName = thisMoveName; }
	public void setMoveType(String thisMoveType) { this.moveType = thisMoveType; }
	public void setMoveCategory(String thisMoveTypeCat) { this.moveTypeCategory = thisMoveTypeCat; }
	public void setMoveDmg(int thisMoveDmg) { this.moveDmg = thisMoveDmg; }

	// getters for Pokemon attribute move-set, each Pokemon can hold 4 moves at most. 
	public String getMoveName() { return moveName; }
	public String getMoveType() { return moveType; }
	public String getMoveTypeCat() { return moveTypeCategory; }
	public int getMoveDamage() { return moveDmg; }

} 