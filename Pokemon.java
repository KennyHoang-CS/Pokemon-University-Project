
public class Pokemon {
	
	// Pokemon common attributes
	private int ID;
	private String name; 
	private String gender; 
	private String type;
	private int level; 
	
	// Pokemon attribute stats relating to the individual Pokemon. 
	private int hp;
	private int atk;
	private int def;
	private int spatk;
	private int spdef; 
	private int spd;
	
	Pokemon(int thisID, String thisName, String thisGender, String thisType, int thisLevel, int thisHP,
		    int thisATK, int thisDEF, int thisSPATK, int thisSPDEF, int thisSPD)
	{
		this.ID = thisID;
		this.name = thisName;
		this.gender = thisGender;
		this.type = thisType;
		this.level = thisLevel; 
		this.hp = thisHP;
		this.atk = thisATK;
		this.def = thisDEF;
		this.spatk = thisSPATK;
		this.spdef = thisSPDEF;
		this.spd = thisSPD; 
	}
	
	// setters for Pokemon common attributes 
	public void setID(int  thisID) { this.ID = thisID; }
	public void setName(String thisName) { this.name = thisName; }
	public void setGender(String thisGender) { this.gender = thisGender; }
	public void setType(String thisType) { this.type = thisType; }
	public void setLevel(int thisLevel)	{ this.level = thisLevel; }
	
	// setters for Pokemon attribute stats relating to the individual Pokemon.
	public void setHP(int thisHP) {	this.hp = thisHP; }
	public void setSpeed(int thisSPD) { this.spd = thisSPD; }
	public void setATK(int thisATK)	{ this.atk = thisATK; }
	public void setSPAtk(int thisSPAtk) { this.spatk = thisSPAtk; }
	public void setDEF(int thisDEF) { this.def = thisDEF; }
	public void setSPDef(int thisSPDef) { this.spdef = thisSPDef; }
	
	// getters for Pokemon common attributes 
	public int getID() { return ID; }
	public String getName() { return name; }
	public String getGender() { return gender; }
	public String getType() { return type; }
	public int getLevel() { return level; }
	
	// getters for Pokemon attribute stats relating to the individual Pokemon.
	public int getHP() { return hp; }
	public int getSpeed(){ return spd; }
	public int getATK()	{ return atk; }
	public int getSPATK() { return spatk; }
	public int getDEF() { return def; }
	public int getSPDEF() { return spdef; }
	
} // end of Pokemon class
