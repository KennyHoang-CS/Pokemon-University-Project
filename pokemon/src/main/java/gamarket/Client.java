package gamarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class Client extends Application {
    private int encnounterChance = 200;
    private double encounterTable[] = {10, 8.5,6.75,3.33,1.25};
    private String startInput;
    private String interFaceInput;
    private StartMenuGUI startMenu;
    private Grid grid;
    private Player player;
    private GridPane root;
    Stage window;

    //static Client client = new Client ();

    public Client(){
        /*
        window = new Stage();
        window.setTitle("Pokemon");

        StartMenuGUI start = new StartMenuGUI();
        start.launch();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);


        Scene scene = new Scene(startMenu(), 300,100);
        window.setScene(scene);
        window.show();
        */

    }

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pokemon: East Bay");
        //startMenu = new StartMenuGUI();
        Scene scene = new Scene(gameInterface());
        primaryStage.setScene(scene);
        System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e){
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });

        new AnimationTimer() {
            private long lastUpdate;

            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }

            public void handle(long currentNanoTime) {
                long elapsedNanoSeconds = currentNanoTime - lastUpdate;

                double elapsedSeconds = elapsedNanoSeconds / 1000000000.0;
                if(elapsedSeconds >= .1) {
                    lastUpdate = currentNanoTime;
                    if (input.contains("W")) {
                        updateGUI("w");
                        grid.updateGrid("w");
                        System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                    } else if (input.contains("A")) {
                        updateGUI("a");
                        grid.updateGrid("a");
                    } else if (input.contains("S")) {
                        updateGUI("s");
                        grid.updateGrid("s");
                    } else if (input.contains("D")) {
                        updateGUI("d");
                        grid.updateGrid("d");
                    }else if(input.contains("E")){
                        System.out.println("menu selected");
                    }
                }
            }
        }.start();


        primaryStage.show();
    }

    //public static Client getInstance(){ return client; }


    public GridPane gameInterface(){
        root = new GridPane();
        root.setStyle("-fx-background-color: #a3a3a3;");
        root.setPrefSize(800,800);

        root.setHgap(1.0);
        root.setVgap(1.0);

        grid = new Grid();
        grid.setPlayerPosition(0,0);

        TileGUI tile;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                tile = new TileGUI(grid.getTile(x,y));
                root.add(tile, x, y);
            }
        }
        int location = grid.getPlayerPosition()[0] + (grid.getPlayerPosition()[1] * grid.getXMax());
        System.out.println("location: "+ location);

        return root;

    }

    public void save(){
    //implemented in player class?
    }

    public void quitSave(){

    }

    public void displayTeam(){

    }

    public void encounterCheck(String move){
        switch(move){
            case "W":
                grid.updateGrid("W");
                tileCheck();
                break;
            case "A":
                grid.updateGrid("A");
                tileCheck();
                break;
            case "S":
                grid.updateGrid("S");
                tileCheck();
                break;
            case "D":
                grid.updateGrid("D");
                tileCheck();
                break;
        }
        
    }

    public void encouter(){

    }

    private void tileCheck(){
        // Random random = new Random();
        // int rand = random.nextInt(5);
        // double encounterType = encounterTable[rand];
        // int encounterFormula = (int) Math.floor(encounterChance/encounterType);
        // rand = random.nextInt(encounterChance);
        // int position[] = grid.getPlayerPosition();
        // tile = grid.getTile(position[0], position[1]);

        // if(tile.getType() == Tile.Type.GRASS){
        //     if(rand == encounterFormula){
        //         encouter();
        //     }
        // }
    }

    private void updateGUI(String direction){
        int location = grid.getPlayerPosition()[0] + (grid.getPlayerPosition()[1] * grid.getYMax());
        TileGUI player = (TileGUI)root.getChildren().get(location);
        player.toggleHasPlayer();
        System.out.println("OG Location: "+location);

        switch (direction){
            case "w":
                location += grid.getYMax() - 1 ;
                break;
            case "a":
                location--;
                break;
            case "s":
                location -= grid.getYMax()- 1;
                break;
            case "d":
                location++;
                break;
        }
        System.out.println("Location: "+location +"\n");

        player = (TileGUI)root.getChildren().get(location);
        player.toggleHasPlayer();
    }


}