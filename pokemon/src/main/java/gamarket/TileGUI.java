package gamarket;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class TileGUI extends StackPane {
    Tile ogTile;
    ImageView playerImg;
    ImageView tileImg;

    /**
     * Tile constructor renders the tile for the GUI with or without the player
     * @param tile
     */
    TileGUI(Tile tile){
        this.ogTile = tile;
        ogTile.getType();

        File file = new File("./pokemon/imgs/player.png");
        Image image =  new Image(file.toURI().toString());
        playerImg = new ImageView(image);

        switch(ogTile.getType()){
            case GRASS:
                renderTile("grass");
                break;
            case ROAD:
                renderTile("road");
                break;
            case HOUSE:
                renderTile("house");
                break;
            case TREE:
                renderTile("tree");
                break;
            case CUTTABLE_TREE:
                renderTile("cuttable_tree");
                break;
            case WATER:
                renderTile("water");
                break;
            case UNSURFABLE:
                renderTile("unsurfable");
                break;
            case WHIRLPOOL:
                renderTile("whirlpool");
                break;
            case UNRECOGNIZED:
                renderTile("unrecognized");
                break;
        }

        tileImg.setFitHeight(40);
        tileImg.setFitWidth(40);
        if(ogTile.hasPlayer()){
            playerImg.setFitWidth(40);
            playerImg.setFitHeight(40);
            getChildren().addAll(tileImg, playerImg);
        }else{
            getChildren().addAll(tileImg);
        }
    }

    /**
     * renders the player sprite on the GUI
     */
    public void renderPlayer () {
        File file = new File("./pokemon/imgs/player.png");
        Image image =  new Image(file.toURI().toString());
        playerImg = new ImageView(image);

        tileImg.setFitHeight(40);
        tileImg.setFitWidth(40);
        playerImg.setFitWidth(40);
        playerImg.setFitHeight(40);
        getChildren().addAll(playerImg);
    }

    /**
     * removes the player sprit from the GUI
     */
    public void removePlayer () {
        getChildren().remove(playerImg);
    }

    /**
     * renders the tile for the GUI
     * @param type tells the method which tile image to load in
     */
    private void renderTile(String type){
        File file = new File("./pokemon/imgs/"+ type +".png");
        Image image = new Image(file.toURI().toString());
        tileImg= new ImageView(image);
    }

}
