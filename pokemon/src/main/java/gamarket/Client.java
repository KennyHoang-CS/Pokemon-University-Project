package gamarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;

public class Client extends Application {
    private Grid grid;
    private Player player;
    private GridPane gameGUI;
    private StackPane stackPane;
    private Stage window;
    private int width = 800;
    private int height = 800;
    private boolean paused;
    private Menu menu;
    private PokemonCollection pokeCollection;
    private MoveCollection moveCollection;
    private SceneController sceneController;

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
        sceneController = new SceneController(window);
        StartMenuGUI startMenu = new StartMenuGUI();
        startMenu.display();
        stackPane = gameInterface(startMenu.getNewUser(),startMenu.getUsername(),startMenu.getPassword());
        Scene scene = new Scene(stackPane);
        window.setScene(scene);
        paused = false;
        menu = Menu.getInstance();
        menu.setPlayer(player);
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
                if (elapsedSeconds >= .1) {
                    lastUpdate = currentNanoTime;
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

                    }
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
    public StackPane gameInterface(boolean newPlayer, String username, String password) {
        loadCollections();
        stackPane = new StackPane();

        gameGUI = new GridPane();
        if (!newPlayer) {
            player = new Player(false, username, password);
            grid = new Grid();
            grid.loadData(username, false);
        } else {
            player = new Player(true, username, password);
            grid = new Grid();
            grid.loadData("new", false);
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

    /**
     * save saves the current players data and grid
     */
    public void save() {
        player.saveData();
        grid.save(player.getName(), false);
    }

    public void displayTeam() {
        //TO-DO
    }

    public void loadCollections() {
        moveCollection = new MoveCollection();
        pokeCollection = new PokemonCollection(moveCollection);
    }

    public void encouter() {
        System.out.println("Pokemon encountered!");
        Encounter aEncounter = new Encounter(player, pokeCollection);

        Soundtrack.stopMusic();
        Soundtrack.loadMusic("wild_encounter.wav");
        Soundtrack.startMusic();
        sceneController.encounterScene();
       // aEncounter.battle();
        Soundtrack.stopMusic();
        Soundtrack.loadMusic("in_game1.wav");
        Soundtrack.startMusic();
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
}

