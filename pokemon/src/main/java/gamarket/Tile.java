package gamarket;

class Tile {
    private boolean isPermeable;
    private boolean player;

    /**
     * note if new type is added make changes to toString and setPermeable
     */
    public enum Type {
        GRASS, ROAD, HOUSEUL,HOUSEUR,HOUSEBL,HOUSEBR, TREE,
        MARTUL, MARTUR, MARTBL, MARTBR, CUTTABLE_TREE,
        WATERUL, WATERUR, WATERBL, WATERBR, UNSURFABLE, WHIRLPOOL,
        STORENPC, NURSENPC, UNRECOGNIZED, PCENTERUL, PCENTERUR, PCENTERBL, PCENTERBR
    }

    private Type tileType;

    Tile(Type tType, boolean player){
        this.player = player;
        this.tileType = tType;
        setPermeable(tType);
    }
    /**
     * returns string representaion of Tile
     * First letter of Type. Exception Whirlpool is L
     * Unrecognized types is U
     */
    @Override
    public String toString() {
        switch (this.tileType) {
            case GRASS: return "G";
            case ROAD: return "R";
            case HOUSEUL: return "HUL";
            case HOUSEUR: return "HUR";
            case HOUSEBL: return "HBL";
            case HOUSEBR: return "HBR";
            case MARTUL: return "MUL";
            case MARTUR: return "MUR";
            case MARTBL: return "MBL";
            case MARTBR: return "MBR";
            case TREE: return "T";
            case CUTTABLE_TREE: return "C";
            case WATERUL: return "WUL";
            case WATERUR: return "WUR";
            case WATERBL: return "WBL";
            case WATERBR: return "WBR";
            case PCENTERUL: return "NUL";
            case PCENTERUR: return "NUR";
            case PCENTERBL: return "NBL";
            case PCENTERBR: return "NBR";
            case UNSURFABLE: return "US";
            case WHIRLPOOL: return "L";
            case STORENPC: return "S";
            case NURSENPC: return "B";
            //unrecognized or undefined
            default: return "U"; 
        }
    }

    /**
     * Returns whether tile can be moved into
     * @return True can be moved into or False can't be moved into
     */
    public boolean getIsPermeable () {
        return this.isPermeable;
    }

    /**
     * based on type set is permeable value
     * @param type
     */
    public void setPermeable (Type type) {
        switch (type) {
            case GRASS: this.isPermeable = true; break;
            case ROAD: this.isPermeable = true; break;
            case HOUSEUL: this.isPermeable = false; break;
            case HOUSEUR: this.isPermeable = false; break;
            case HOUSEBL: this.isPermeable = false; break;
            case HOUSEBR: this.isPermeable = false; break;
            case MARTUL: this.isPermeable = false; break;
            case MARTUR: this.isPermeable = false; break;
            case MARTBL: this.isPermeable = false; break;
            case MARTBR: this.isPermeable = false; break;
            case TREE: this.isPermeable = false; break;
            case CUTTABLE_TREE: this.isPermeable = false; break;
            case WATERUL: this.isPermeable = false; break;
            case WATERUR: this.isPermeable = false; break;
            case WATERBL: this.isPermeable = false; break;
            case WATERBR: this.isPermeable = false; break;
            case PCENTERUL: this.isPermeable = false; break;
            case PCENTERUR: this.isPermeable = false; break;
            case PCENTERBL: this.isPermeable = false; break;
            case PCENTERBR: this.isPermeable = false; break;
            case UNSURFABLE: this.isPermeable = false; break;
            case WHIRLPOOL: this.isPermeable = false; break;
            case STORENPC: this.isPermeable = false; break;
            case NURSENPC: this.isPermeable = false; break;
            default: this.isPermeable = false;
        }
    }
    /**
     * Getter for tileType
     * @return Type of the Tile
     */
    public Type getType () {
        return this.tileType;
    }

    /**
     * change type to the input type 
     * and makes change to Permable
     * @param newType
     */
	public void setType(Type newType) {
        this.tileType = newType;
        setPermeable(newType);
    }
    
    /**
     * Takes char representation of type and returns the coresponding Type
     * @param typeChar 
     * @return Type 
     */
	public static Type charToType(String typeChar) {
	    if(typeChar.contains("P")){
	        typeChar = typeChar.substring(0,typeChar.indexOf("P"));
        }
        switch(typeChar) {
            case "G": return Type.GRASS;
            case "R": return Type.ROAD;
            case "HUL": return Type.HOUSEUL;
            case "HUR": return Type.HOUSEUR;
            case "HBL": return Type.HOUSEBL;
            case "HBR": return Type.HOUSEBR;
            case "MUL": return Type.MARTUL;
            case "MUR": return Type.MARTUR;
            case "MBL": return Type.MARTBL;
            case "MBR": return Type.MARTBR;
            case "T": return Type.TREE;
            case "C": return Type.CUTTABLE_TREE;
            case "WUL": return Type.WATERUL;
            case "WUR": return Type.WATERUR;
            case "WBL": return Type.WATERBL;
            case "WBR": return Type.WATERBR;
            case "NUL": return Type.PCENTERUL;
            case "NUR": return Type.PCENTERUR;
            case "NBL": return Type.PCENTERBL;
            case "NBR": return Type.PCENTERBR;
            case "US": return Type.UNSURFABLE;
            case "L": return Type.WHIRLPOOL;
            case "B": return Type.NURSENPC;
            case "S": return Type.STORENPC;
            default: return Type.UNRECOGNIZED;
        }    
	}

    public boolean hasPlayer() { return this.player; }

    public void togglePlayer() { this.player = !this.player; }

}