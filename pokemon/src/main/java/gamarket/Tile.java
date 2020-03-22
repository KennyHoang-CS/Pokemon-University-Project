package gamarket;

class Tile {
    private boolean isPermeable;

    /**
     * note if new type is added make changes to toString and setPermeable
     */
    public enum Type {
        GRASS, ROAD, HOUSE, TREE, CUTTABLE_TREE, WATER, UNSURFABLE, WHIRLPOOL
    } ; 
    private Type tileType;

    Tile(Type tType){
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
            case HOUSE: return "H";
            case TREE: return "T";
            case CUTTABLE_TREE: return "C";
            case WATER: return "W";
            case UNSURFABLE: return "U";
            case WHIRLPOOL: return "L";
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
     * sets type based on 
     * @param type
     */
    public void setPermeable (Type type) {
        switch (type) {
            case GRASS: this.isPermeable = true; break;
            case ROAD: this.isPermeable = true; break;
            case HOUSE: this.isPermeable = false; break;
            case TREE: this.isPermeable = false; break;
            case CUTTABLE_TREE: this.isPermeable = false; break;
            case WATER: this.isPermeable = false; break;
            case UNSURFABLE: this.isPermeable = false; break;
            case WHIRLPOOL: this.isPermeable = false; break;
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
}