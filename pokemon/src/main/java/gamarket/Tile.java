package gamarket;

class Tile {
    private boolean isPermeable;
    public enum Type {
        GRASS, ROAD
    } ; 
    private Type tileType;

    Tile(Type tType, boolean isPermeable){
        this.tileType = tType;
        this.isPermeable = isPermeable;
    }
    
    @Override
    public String toString() {
        switch (this.tileType) {
            case GRASS: return "G";

            case ROAD: return "R";

            //unrecognized or undefined
            default: return "U"; 
        }
    }

    public boolean getIsPermeable () {
        return this.isPermeable;
    }
    public Type getType () {
        return this.tileType;
    }
}