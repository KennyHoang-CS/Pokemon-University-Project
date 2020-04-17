package gamarket;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.File;

class Tile extends StackPane {
    private boolean isPermeable;
    private ImageView tileImg;
    private ImageView playerImg;
    private boolean player;

    /**
     * note if new type is added make changes to toString and setPermeable
     */
    public enum Type {
        GRASS, ROAD, HOUSE, TREE, CUTTABLE_TREE, WATER, UNSURFABLE, WHIRLPOOL, UNRECOGNIZED
    } ; 
    private Type tileType;

    Tile(Type tType){
        this.tileType = tType;
        setPermeable(tType);

        File file;
        Image image;

        switch(tType){
            case GRASS:
                file = new File("./pokemon/imgs/grass.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case ROAD:
                file = new File("./pokemon/imgs/road.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case HOUSE:
                file = new File("./pokemon/imgs/house.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case TREE:
                file = new File("./pokemon/imgs/tree.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case CUTTABLE_TREE:
                file = new File("./pokemon/imgs/cuttable_tree.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case WATER:
                file = new File("./pokemon/imgs/water.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case UNSURFABLE:
                file = new File("./pokemon/imgs/unsurfable.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case WHIRLPOOL:
                file = new File("./pokemon/imgs/whirlpool.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case UNRECOGNIZED:
                file = new File("./pokemon/imgs/unrecognized.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
        }

        tileImg.setFitHeight(40);
        tileImg.setFitWidth(40);
        getChildren().addAll(tileImg);
    }

    Tile(Type tType, boolean player){
        this.player = player;
        this.tileType = tType;
        setPermeable(tType);


        File file = new File("./pokemon/imgs/player.png");
        Image image =  new Image(file.toURI().toString());
        playerImg = new ImageView(image);
        switch(tType){
            case GRASS:
                file = new File("./pokemon/imgs/grass.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case ROAD:
                file = new File("./pokemon/imgs/road.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case HOUSE:
                file = new File("./pokemon/imgs/house.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case TREE:
                file = new File("./pokemon/imgs/tree.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case CUTTABLE_TREE:
                file = new File("./pokemon/imgs/cuttable_tree.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case WATER:
                file = new File("./pokemon/imgs/water.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case UNSURFABLE:
                file = new File("./pokemon/imgs/unsurfable.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case WHIRLPOOL:
                file = new File("./pokemon/imgs/whirlpool.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
            case UNRECOGNIZED:
                file = new File("./pokemon/imgs/unrecognized.png");
                image = new Image(file.toURI().toString());
                tileImg= new ImageView(image);
                break;
        }

        tileImg.setFitHeight(40);
        tileImg.setFitWidth(40);
        playerImg.setFitWidth(40);
        playerImg.setFitHeight(40);
        getChildren().addAll(tileImg, playerImg);
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
     * based on type set is permeable value
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
    
    /**
     * Takes char representation of type and returns the coresponding Type
     * @param typeChar 
     * @return Type 
     */
	public static Type charToType(char typeChar) {
        switch(typeChar) {
            case 'G': return Type.GRASS;
            case 'R': return Type.ROAD;
            case 'H': return Type.HOUSE;
            case 'T': return Type.TREE;
            case 'C': return Type.CUTTABLE_TREE;
            case 'W': return Type.WATER;
            case 'U': return Type.UNSURFABLE;
            case 'L': return Type.WHIRLPOOL;
            default: return Type.UNRECOGNIZED;
        }    
	}

	public ImageView getIv(){
	    return tileImg;
    }
}