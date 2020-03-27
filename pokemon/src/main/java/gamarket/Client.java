package gamarket;

import java.util.*;

public class Client{
    private int encnounterChance;
    private int encounterGuarantee;
    private String startInput;
    private String interFaceInput;
    StartMenuGUI startMenu;
    private Grid grid;
    private Player player;

    static Client client = new Client ();

    private Client(){
        //constructor
        startMenu();
        if(startMenu.getNewUser()){
            player = new Player(true, startMenu.getUsername(), startMenu.getPassword());
        }
    }

    public static Client getInstance(){
        return client;
    }

    public void startMenu(){
        startMenu = new StartMenuGUI();
    }

    public void gameInterface(){


    }

    public void save(){

    }

    public void quitSave(){

    }

    public void displayTeam(){

    }

    public void encounterCheck(){

    }

    public void encouter(){

    }


}