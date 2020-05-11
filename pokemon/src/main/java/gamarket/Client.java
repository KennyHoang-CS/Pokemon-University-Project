package gamarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.util.*;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Client extends Application {
    private Grid grid;
    private Player player;
    private GridPane gameGUI;
    private StackPane stackPane;
    private Stage window;
    private boolean paused;
    private Menu menu;
    private PokemonCollection pokeCollection;
    private MoveCollection moveCollection;
    private Team playerTeam;
    private SceneController sceneController;
    private String lastDir;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    /**
     * start begins the application and is the main controller of it
     */
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Pokemon: East Bay");
        window.setResizable(false);
        connectToDB();
        StartMenuGUI startMenu = new StartMenuGUI();
        startMenu.display();
        player = startMenu.getClientPlayer();
        stackPane = gameInterface(startMenu.getNewUser());
        Scene scene = new Scene(stackPane);
        window.setScene(scene);
        sceneController = SceneController.getInstance(window);
        paused = false;
        menu = Menu.getInstance();
        menu.setPlayer(player);
        menu.setTeam(playerTeam);
        menu.renderDisplay();
        menu.setSceneController(window);

        ArrayList<String> input = new ArrayList<String>();
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
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
                if (elapsedSeconds >= .05) {
                    lastUpdate = currentNanoTime;
                    if(sceneController.getActiveScene().equals("encounter")) {
                        return ;
                    }
                    if (input.contains("W")) {
                        updateGUI("w");
                        encounterCheck();
                    } else if (input.contains("A")) {
                        updateGUI("a");
                        encounterCheck();
                    } else if (input.contains("S")) {
                        updateGUI("s");
                        encounterCheck();
                    } else if (input.contains("D")) {
                        updateGUI("d");
                        encounterCheck();
                    } else if (input.contains("E")) {
                        if(stackPane.getChildren().size() < 2){
                            if(paused){
                                stackPane.getChildren().remove(1);
                                stackPane.getChildren().get(0).setDisable(false);
                            }else{
                                stackPane.getChildren().add(menu.display());
                                stackPane.getChildren().get(0).setDisable(true);

                                System.out.println(window.getScene());
                                System.out.println(stackPane.getChildren());
                            }
                        }
                    }else if(input.contains("F")){
                        interactionCheck(lastDir);
                    }
                    input.clear();
                }
            }
        }.start();


        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                save();
                window.close();
                System.exit(0);
            }
        });
        //sceneController = SceneController.getInstance(window);
        System.out.println("scene controller"+ sceneController);
        window.show();
    }

    /**
     * gameInterface loads up the appropriate grid depending on whether the player is new or returning,
     * instantiates the player class and loads in their new or saved information, and creates the GUI.
     *
     * @param newPlayer lets gameInterface know whether we have a new player
     * @param username  is used to instantiate the player
     * @param password  is usded to instantiate the player
     * @return returns the GUI
     */
    public StackPane gameInterface(Boolean newPlayer) {
        loadCollections();
        stackPane = new StackPane();
        gameGUI = new GridPane();
        playerTeam = new Team(moveCollection);
        grid = new Grid();
        if(!newPlayer){
            grid.loadData(player.getName(), false);
            playerTeam.loadFromDb(player.getName());
        }else{
            grid.loadData("new",false);
            player.saveToDB();
            playerTeam.loadTeam("default");
            playerTeam.saveToDb(player.getName());
        }

        TileGUI tile;
        for (int y = 0; y < grid.getYMax(); y++) {
            for (int x = 0; x < grid.getXMax(); x++) {
                tile = new TileGUI(grid.getTile(x, y));
                gameGUI.add(tile, x, y);
            }
        }
        updateGUI("a");
        updateGUI("d");

        stackPane.getChildren().addAll(gameGUI);
        return stackPane;

    }
    public void connectToDB () {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("./pokemon/databaseFiles/pokemoneastbay-firebase-adminsdk-75gh7-d0b842b720.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://pokemoneastbay.firebaseio.com")
            .build();

        FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
    }
    /**
     * save saves the current players data and grid
     */
    public void save() {
        player.saveToDB();
        player.saveData();
        grid.save(player.getName(), false);
    }

    public void loadCollections() {
        moveCollection = new MoveCollection();
        pokeCollection = new PokemonCollection(moveCollection);
    }

    public void encouter() {
        System.out.println("Pokemon encountered!");
        Encounter aEncounter = new Encounter(playerTeam, pokeCollection);
        sceneController.encounterScene(aEncounter.getWildPokemon(), aEncounter.getPlayerActivePokemon(), aEncounter);
        playerTeam.saveToDb(player.getName());
    }

    /**
     * encounterCheck checks the tile the player moved on.
     * If the tile is grass, checks the probablity of encountering a pokemon,
     * and calls the encounter method if a pokemon is encounterd.
     */
    private void encounterCheck() {
        Random random = new Random();
        int rand = random.nextInt(10);
        int rand2 = random.nextInt(10);

        Tile tile = grid.getTile(grid.getPlayerPosition()[0], grid.getPlayerPosition()[1]);

        if (tile.getType() == Tile.Type.GRASS) {
            if (rand == rand2) {
                encouter();
            }
        }

    }

    /**
     * updatesGUI updates the player sprite location on the gui
     *
     * @param direction lets the method know in which direection to move the sprite
     */
    private void updateGUI(String direction) {
        int location = grid.getPlayerPosition()[0] + (grid.getPlayerPosition()[1] * grid.getYMax());
        TileGUI player = (TileGUI) this.gameGUI.getChildren().get(location);
        player.removePlayer();
        int x = grid.getPlayerPosition()[0];
        int y = grid.getPlayerPosition()[1];

        switch (direction) {
            case "w":
                if (grid.canMove(x, y - 1)) {
                    grid.updateGrid("w");
                    location -= grid.getXMax();
                }
                break;
            case "a":
                if (grid.canMove(x - 1, y)) {
                    grid.updateGrid("a");
                    location--;
                }
                break;
            case "s":
                if (grid.canMove(x, y + 1)) {
                    grid.updateGrid("s");
                    location += grid.getXMax();
                }
                break;
            case "d":
                if (grid.canMove(x + 1, y)) {
                    grid.updateGrid("d");
                    location += 1;
                }
                break;
        }
        TileGUI playerNew = (TileGUI) this.gameGUI.getChildren().get(location);
        playerNew.renderPlayer();
    }


    private void interactionCheck(String lastDir){
        int x = grid.getPlayerPosition()[0];
        int y = grid.getPlayerPosition()[1];

        switch (lastDir) {
            case "w":
                if (grid.canInteract(x, y - 1)) {
                    if(grid.getType(x, y-1) == Tile.Type.STORENPC){
                        renderStore();
                    }else{
                        renderNurse();
                    }
                }
                break;
            case "a":
                if (grid.canInteract(x - 1, y)) {
                    if(grid.getType(x-1, y) == Tile.Type.STORENPC){
                        renderStore();
                    }else{
                        renderNurse();
                    }
                }
                break;
            case "s":
                if (grid.canInteract(x, y + 1)) {
                    if(grid.getType(x, y+1) == Tile.Type.STORENPC){
                        renderStore();
                    }else{
                        renderNurse();
                    }
                }
                break;
            case "d":
                if (grid.canInteract(x + 1, y)) {
                    if(grid.getType(x+1, y) == Tile.Type.STORENPC){
                        renderStore();
                    }else {
                        renderNurse();
                    }
                }
                break;
        }
    }

    private void renderStore(){
        Store store = new Store(this.player.getBag(), this.player.getMoney());
        StoreGUI storeGUI = new StoreGUI(store ,this.window);
        this.stackPane.getChildren().add(storeGUI.display());
        stackPane.getChildren().get(0).setDisable(true);
    }
    private void renderNurse(){
        Nurse nurse = new Nurse(this.player.getPokeTeam());
        this.stackPane.getChildren().add(nurse.display());
        stackPane.getChildren().get(0).setDisable(true);
    }
}
