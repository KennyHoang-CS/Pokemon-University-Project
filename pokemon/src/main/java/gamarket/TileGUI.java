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
            case HOUSEUL:
                renderTile("HUL");
                break;
            case HOUSEUR:
                renderTile("HUR");
                break;
            case HOUSEBL:
                renderTile("HBL");
                break;
            case HOUSEBR:
                renderTile("HBR");
                break;
            case MARTUL:
                renderTile("MUL");
                break;
            case MARTUR:
                renderTile("MUR");
                break;
            case MARTBL:
                renderTile("MBL");
                break;
            case MARTBR:
                renderTile("MBR");
                break;
            case TREE:
                renderTile("tree");
                break;
            case CUTTABLE_TREE:
                renderTile("cuttable_tree");
                break;
            case WATERUL:
                renderTile("WUL");
                break;
            case WATERUR:
                renderTile("WUR");
                break;
            case WATERBL:
                renderTile("WBL");
                break;
            case WATERBR:
                renderTile("WBR");
                break;
            case PCENTERUL:
                renderTile("NUL");
                break;
            case PCENTERUR:
                renderTile("NUR");
                break;
            case PCENTERBL:
                renderTile("NBL");
                break;
            case PCENTERBR:
                renderTile("NBR");
                break;
            case UNSURFABLE:
                renderTile("unsurfable");
                break;
            case WHIRLPOOL:
                renderTile("whirlpool");
                break;
            case STORENPC:
                renderTile("storeNPC");
                break;
            case NURSENPC:
                renderTile("nurse");
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
    public void renderPlayer (String direction) {
        File file = new File("./pokemon/imgs/player.png");
        switch(direction){
            case "w":
                file = new File("./pokemon/imgs/playerU.png");
                break;
            case "a":
                file = new File("./pokemon/imgs/playerL.png");
                break;
            case "s":
                file = new File("./pokemon/imgs/player.png");
                break;
            case "d":
                file = new File("./pokemon/imgs/playerR.png");
                break;
        }

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
