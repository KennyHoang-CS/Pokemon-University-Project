package gamarket;


import javafx.application.Application;
import javafx.stage.Stage;

import java.util.*;

public class Client extends Application {
    private int encounterChance = 200;
    private double encounterTable[] = {10, 8.5,6.75,3.33,1.25};
    private String startInput;
    private String interFaceInput;
    public StartMenuGUI startMenuGUI;

    private Grid grid;
    private Tile tile;
    private Player player;

    static Client client = new Client ();
    private Client(){
        //constructor
    }
    public static Client getInstance(){
        return client;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void startMenu(){
        this.startMenuGUI = new StartMenuGUI();
    }


    public void gameInterface(){

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
        Random random = new Random();
        int rand = random.nextInt(5);
        double encounterType = encounterTable[rand];
        int encounterFormula = (int) Math.floor(encounterChance/encounterType);
        rand = random.nextInt(encounterChance);
        int position[] = grid.getPlayerPosition();
        tile = grid.getTile(position[0], position[1]);

        if(tile.getType() == Tile.Type.GRASS){
            if(rand == encounterFormula){
                encouter();
            }
        }
    }


}