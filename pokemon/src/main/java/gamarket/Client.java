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
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });

        new AnimationTimer()
        {
            public void handle(long currentNanoTime) {

                if (input.contains("W")){
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                    grid.updateGrid("w");
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                }else if(input.contains("A")){
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                    System.out.println("left");
                    grid.updateGrid("a");
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                }else if (input.contains("S")) {
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                    grid.updateGrid("s");
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                }else if(input.contains("D")) {
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                    grid.updateGrid("d");
                    System.out.println("Position: " + grid.getPlayerPosition()[0] + ", " + grid.getPlayerPosition()[1]);
                }
            }
        }.start();


        primaryStage.show();

    }

    //public static Client getInstance(){ return client; }


    public GridPane gameInterface(){
        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #a3a3a3;");
        root.setPrefSize(800,800);

        root.setHgap(1.0);
        root.setVgap(1.0);

        grid = new Grid();
        grid.setPlayerPosition(10,10);
        Tile tile;

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                tile = grid.getTile(x,y);
                root.add(tile, x, y);
            }
        }

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
        Random random = new Random();
        int rand = random.nextInt(5);
        double encounterType = encounterTable[rand];
        int encounterFormula = (int) Math.floor(encnounterChance/encounterType);
        rand = random.nextInt(encnounterChance);
        int position[];
/*
        switch(move){
            case "W":
                grid.updateGrid("W");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
            case "A":
                grid.updateGrid("A");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
            case "S":
                grid.updateGrid("S");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
            case "D":
                grid.updateGrid("D");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
        }
        */
    }

    public void encouter(){

    }


}