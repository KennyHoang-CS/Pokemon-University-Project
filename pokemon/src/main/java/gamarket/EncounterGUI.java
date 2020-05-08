package gamarket;
 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.File;
//import gamarket.test.playerTest;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
 
public class EncounterGUI{
    private Button move1, move2, move3, move4;
    private boolean done, runStatus;
    private Button hp1, hp2; 
    private Button fight, bag, pokemon, run, box; 
    private GridPane gp, poke, menu, textlols, playerSide, wildSide; 
    private VBox battleMenu, fightMenu;  
    private Text text;
    static Pokemon wildPokemon, playerPokemon;
    private String wildPokePath, playerPokePath, attackerStatus, input;
    private static Encounter aEncounter;
    static boolean wasMove1Used, wasMove2Used, wasMove3Used, wasMove4Used;
    private ImageView enemyPoke, playerPoke;
    private VBox pokemonMenu;
    private Button pokemons[];
    private String path, filePath;
    protected static EncounterGUI encounterGui;
    private SceneController sceneController;

    public EncounterGUI(Pokemon wildPokemon, Pokemon playerPokemon, Encounter aEncounter) {
        this.wildPokemon = wildPokemon;
        this.playerPokemon = playerPokemon;
        this.aEncounter = aEncounter;
        pokemons = new Button[6];
    }
    
    public static synchronized EncounterGUI getInstance() {
        if (encounterGui == null) {
            encounterGui = new EncounterGUI(wildPokemon, playerPokemon, aEncounter);
        }
        return encounterGui;
    }

    public void setSceneController(Stage window){ this.sceneController = SceneController.getInstance(window);
        System.out.println("scene controller"+ sceneController);}
 
    public GridPane display() {
        
        gp = new GridPane();
        poke = new GridPane();
        Soundtrack.stopMusic();
        Soundtrack.loadMusic("wild_encounter.wav");
        Soundtrack.startMusic();
        fightMenu = new VBox(18);
 
        // Set up wild pokemon front image. 
        this.getFrontPokeImage();
        // Display player's pokemon, temporary set-up, will use a function to set playerPokePath. 
        playerPokePath = this.whatPokeBackImage(playerPokemon.getIdentStats().getName());
        // Background image for the pokemon wild encounter. 
        filePath = "./pokemon/imgs/battle_background.png";
        File file = new File(filePath);
        String path3 = file.toURI().toString();
        Image image3 = new Image(path3);
        ImageView background = new ImageView(image3);
        background.setFitHeight(300);
        background.setFitWidth(300);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, 
                                                    BackgroundSize.AUTO, false, false, true, true);
        gp.setBackground(new Background(new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT,
                           BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
 
        // Image for the fight button. 
        filePath = "./pokemon/imgs/fight_button_image.png";
        File test = new File(filePath);
        String x = test.toURI().toString();
        ImageView a = new ImageView(x);
        a.setFitHeight(45);
        a.setFitWidth(90);
 
        // Image for the bag button. 
        filePath = "./pokemon/imgs/bag_image.png";
        File file2 = new File(filePath);
        String f2 = file2.toURI().toString();
        ImageView b = new ImageView(f2);
        b.setFitHeight(45); 
        b.setFitWidth(90);
 
        // Image for the pokemon button. 
        filePath = "./pokemon/imgs/pokeball.png";
        File file3 = new File(filePath);
        String f3 = file3.toURI().toString();
        ImageView c = new ImageView(f3);
        c.setFitHeight(45);
        c.setFitWidth(80);
 
        // Image for the run button. 
        filePath = "./pokemon/imgs/running_icon.png";
        File file4 = new File(filePath);
        String f4 = file4.toURI().toString();
        ImageView d = new ImageView(f4);
        d.setFitHeight(65);
        d.setFitWidth(120);
 
        // Wild Pokemon image
        enemyPoke = new ImageView();
        enemyPoke.setImage(this.getImage(wildPokePath));
 
        // Player Pokemon image
        playerPoke = new ImageView();
        playerPoke.setImage(this.getImage(playerPokePath));
 
        // Image resizing for pokemons 
        enemyPoke.setFitHeight(175);
        enemyPoke.setFitWidth(175);
        playerPoke.setFitHeight(175);
        playerPoke.setFitWidth(175);
 
        // BUttons
        fight = new Button("Fight", a);
        bag = new Button("Bag", b);
        pokemon = new Button("Pokemon", c);
        run = new Button("Run", d);
 
        // Sync the Player's pokemon moves into the buttons.
        move1 = new Button(); 
        move1.setOnAction(e -> {this.move1();});
        move2 = new Button(); 
        move2.setOnAction(e -> {this.move2();});
        move3 = new Button(); 
        move3.setOnAction(e -> {this.move3();});
        move4 = new Button(); 
        move4.setOnAction(e -> {this.move4();});
        // Style them moves with CSS.
        this.styleMoveButtons();
     
        // Create the battle menu to display the fight, bag, pokemon, run buttons. 
        battleMenu = new VBox(18);
        battleMenu.getChildren().addAll(fight, bag, pokemon, run);
        fightMenu.getChildren().addAll(move1, move2, move3, move4);
        VBox.setMargin(fight, new Insets(0, 0, 0, 75));
        VBox.setMargin(bag, new Insets(0, 0, 0, 75));
        VBox.setMargin(pokemon, new Insets(0, 0, 0, 75));
        VBox.setMargin(run, new Insets(0, 0, 0, 75));
 
        // Customizing the battle menu with CSS
        fight.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-border-color: rgb(0,0,0);" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                + "-fx-background-color: salmon;" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: #333;");
        bag.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-border-color: wheat;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                + "-fx-background-color: white;" + "-fx-font-size: 32px;" + "-fx-text-align: center;" 
                + "-fx-text-base-color: #333;");
        pokemon.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-background-color: white;" + "-fx-border-color: springgreen;" + "-fx-border-radius: 300px;"
                + "-fx-border-width: 7px;" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: #333;");
         run.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-border-color: dodgerblue;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                + "-fx-background-color: white;" + "-fx-font-size: 32px;" + "-fx-text-align: center;" 
                + "-fx-text-base-color: #333;");
 
 
        // MENU STUFF
        menu = new GridPane();
 
        // Set up the battle menu buttons action events. 
        fight.setOnAction(e -> {this.fight();});
        bag.setOnAction(e -> {this.bag();});
        pokemon.setOnAction(e -> {this.pokemon();});
        run.setOnAction(e -> {this.run();});
 
        // Create the CSS style mouse events for the battle menu. 
        fight.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                fight.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-border-color: salmon;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: #333;");
            }});
        fight.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                fight.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-border-color: rgb(0,0,0);" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                + "-fx-background-color: salmon;" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: #333;");
            }});
        bag.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                bag.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                        + "-fx-border-color: wheat;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                        + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                        + "-fx-text-base-color: white;");
            }});
        bag.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                bag.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                        + "-fx-border-color: wheat;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                        + "-fx-background-color: white;" + "-fx-font-size: 32px;" + "-fx-text-align: center;" 
                        + "-fx-text-base-color: #333;");
            }});
        pokemon.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                pokemon.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                        + "-fx-background-color: rgb(0,0,0);" + "-fx-border-color: springgreen;"
                        + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;" + "-fx-font-size: 32px;"
                        + "-fx-text-align: center;" + "-fx-text-base-color: white;");
            }});
        pokemon.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                pokemon.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                        + "-fx-background-color: white;" + "-fx-border-color: springgreen;"
                        + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;" + "-fx-font-size: 32px;"
                        + "-fx-text-align: center;" + "-fx-text-base-color: #333;");
            }});
        run.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                run.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                        + "-fx-border-color: dodgerblue;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                        + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                        + "-fx-text-base-color: white;");
            }});
        run.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                run.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                        + "-fx-border-color: dodgerblue;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                        + "-fx-background-color: white;" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                        + "-fx-text-base-color: #333;");
            }});
 
        // Create the CSS style mouse events for fight menu: move 1, move 2, move 3, move 4. 
        move1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move1.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-color: rgb(0,0,0);" + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
                + EncounterGUI.setColor(0) + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move1.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;" + "-fx-border-color: slategray;"
                + EncounterGUI.setColor(0) + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move2.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-color: rgb(0,0,0);" + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
                + EncounterGUI.setColor(1) + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move2.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"  + "-fx-border-color: slategray;"
                + EncounterGUI.setColor(1) + "-fx-font-size: 32px;" + "-fx-text-align: center;" 
                + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move3.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + EncounterGUI.setColor(2) + "-fx-border-color: rgb(0,0,0);"
                + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;" + "-fx-font-size: 32px;"
                + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move3.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + EncounterGUI.setColor(2)  + "-fx-border-color: slategray;"
                + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;" + "-fx-font-size: 32px;"
                + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move4.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move4.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-color: rgb(0,0,0);" + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
                + EncounterGUI.setColor(3) + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        }});
        move4.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                move4.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"  + "-fx-border-color: slategray;"
                + EncounterGUI.setColor(3) + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        }}); 
        
        // The initial text when the pokemon wild encounter begins. 
        text = new Text();
        text.setText("A wild " + wildPokemon.getIdentStats().getName() + " appears!" + "\nPlayer: Lets go "
                + playerPokemon.getIdentStats().getName() + "!");
        text.setFont(Font.font("Old-town fantasy", FontWeight.BOLD, FontPosture.REGULAR, 32));
        done = false;
        battleMenu.setVisible(true);
        fightMenu.setVisible(false);     
        
        // Initiate the status bars using Pokemon's name, level, and health. 
        hp1 = new Button(this.updateHP1());
        hp2 = new Button(this.updateHP2());
 
       // Set up the container to hold the player's pokemon and wild pokemon images and info bars. 
        playerSide = new GridPane();     
        wildSide = new GridPane();
        playerSide.add(playerPoke, 0, 2);  
        playerSide.add(hp1, 0, 0);       
        wildSide.add(enemyPoke, 0, 2);
        wildSide.add(hp2, 0, 0);
 
        // Adding the pokemon to the GUI 
        poke.add(wildSide, 1, 1);     
        poke.add(playerSide, 0, 2);     
        poke.setHgap(425);
        poke.setVgap(145);
        poke.setPadding(new Insets(15, 0, 0, 550)); 
 
        // Set the maximum height for the move buttons. 
        move1.setMaxHeight(70);
        move2.setMaxHeight(70);
        move3.setMaxHeight(70);
        move4.setMaxHeight(70);
 
        // Create the big textbox to update real-time text upon the pokemon wild encounter. 
        StackPane mySP = new StackPane(); 
        box = new Button();
        box.setStyle("-fx-pref-width: 1350px; " + "-fx-pref-height: 250px;"
                    + "-fx-border: none; "  + "-fx-background-color: gray;" + "-fx-font-size: 32px;"
                    + "-fx-text-align: center;" + "-fx-text-base-color: white;");
        mySP.getChildren().add(box);
        box.setPadding(new Insets(125, 0,0,0));
        mySP.setPadding(new Insets(185, 0,0,0));            // Responsible for text box positioning. 
 
        // The container to hold our text. 
        Group textbox = new Group();
        text.setStroke(Color.BLACK);
        text.setFill(Color.WHITE);
        textbox.getChildren().add(text);
        
        // Add the textbox that contains our text to the wild pokemon encounter GUI. 
        textlols = new GridPane();
        textlols.getChildren().add(textbox);
        textlols.setPadding(new Insets(25, 0, 0, 110));
 
        mySP.getChildren().add(textlols);
        battleMenu.setPadding(new Insets(30, 0, 0, 230));
        fightMenu.setPadding(new Insets(50, 0, 0, 185));
        
        // Add everything to the main GUI container: the pokemons, menu, and text box. 
        gp.add(poke, 0, 0);
        gp.add(menu, 1, 1);
        gp.add(mySP, 0, 1);
        gp.setStyle("-fx-alignment: center-right;");
        
        // Style them health bars and add them to the main GridPane GUI 'gp'.
        StackPane playerPokeHealthBar = new StackPane();        // The player pokemon. 
        playerPokeHealthBar.getChildren().add(hp2);
        gp.add(playerPokeHealthBar, 1, 0);
        playerPokeHealthBar.setPadding(new Insets(0, 0, 532, 300));
        hp2.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
            + "-fx-background-color: white;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
            + "-fx-font-size: 24px;" + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
 
        StackPane wildPokeHealthBar = new StackPane();          // The wild pokemon. 
        wildPokeHealthBar.getChildren().add(hp1);
        gp.add(wildPokeHealthBar, 0, 1);
        wildPokeHealthBar.setPadding(new Insets(0, 1035, 160, 0));  
        hp1.setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
            + "-fx-background-color: white;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;" 
            + "-fx-font-size: 24px;" + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
 
        // Creating the pokemon party 
        pokemonMenu = new VBox(7); 
        pokemonMenu.setPadding(new Insets(0, 0, 0, 305));
        this.createPokemonPartyUI();
        this.styleThemPokemonButtons();
        pokemonMenu.setVisible(false);
        menu.getChildren().addAll(battleMenu, fightMenu, pokemonMenu);
        
        return gp;
    }
 
    /**
     * Updates the Player's pokemon health, name, and level. 
     */
    public String updateHP1()
    {
        // update the player's pokemon status bars: name, level, health.
        return playerPokemon.getIdentStats().getName() +
                "       Lv." + Integer.toString(playerPokemon.getIdentStats().getLevel()) 
                + "\n    " + Integer.toString(playerPokemon.getDefensiveStats().getHPCurrent()) + " / "
                + Integer.toString(playerPokemon.getDefensiveStats().getHP()) + " HP";
    }
 
    /**
     * Updates the Wild's pokemon health, name, and level. 
     */
    public String updateHP2()
    {
        // update the wild's pokemon status bars: name, level, health. 
        return wildPokemon.getIdentStats().getName() +
                "       Lv." + Integer.toString(wildPokemon.getIdentStats().getLevel()) 
                + "\n    " + Integer.toString(wildPokemon.getDefensiveStats().getHPCurrent()) + " / "
                + Integer.toString(wildPokemon.getDefensiveStats().getHP()) + " HP";
    }
 
    /**
     * Switches the Player's pokemon with another pokemon that is in the player's team.
     * @param e this is the Pokemon you are switching with. 
     */
    public void pokemonSwitch(ActionEvent e)
    {
        if(e.getSource() == pokemons[0])
        {
            // switch the active pokemon's image to the pokemon's image in pokemons[0].
            playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[0].getText())));
            // update the fight moves menu. 
            playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, 1);
            playerPokemon = aEncounter.getPlayerActivePokemon();
            this.styleMoveButtons();
            // update the pokemon status bars: name, level, health.
            hp1.setText(this.updateHP1());
            // change visibility of menus. 
            battleMenu.setVisible(true);
            pokemonMenu.setVisible(false);
        }
        if(e.getSource() == pokemons[1])
        {
           // switch the active pokemon's image to the pokemon's image in pokemons[0].
            playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[1].getText())));
            // update the fight moves menu. 
            playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, 2);
            playerPokemon = aEncounter.getPlayerActivePokemon();
            this.styleMoveButtons();
            // update the pokemon status bars: name, level, health.
            hp1.setText(this.updateHP1());
            // change visibility of menus. 
            battleMenu.setVisible(true);
            pokemonMenu.setVisible(false);
        }
        if(e.getSource() == pokemons[2])
        {
           // switch the active pokemon's image to the pokemon's image in pokemons[0].
            playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[2].getText())));
            // update the fight moves menu. 
            playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, 3);
            playerPokemon = aEncounter.getPlayerActivePokemon();
            this.styleMoveButtons();
            // update the pokemon status bars: name, level, health.
            hp1.setText(this.updateHP1());
            // change visibility of menus. 
            battleMenu.setVisible(true);
            pokemonMenu.setVisible(false);
        }
        if(e.getSource() == pokemons[3])
        {
            // switch the active pokemon's image to the pokemon's image in pokemons[0].
            playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[3].getText())));
            // update the fight moves menu. 
            playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, 4);
            playerPokemon = aEncounter.getPlayerActivePokemon();
            this.styleMoveButtons();
            // update the pokemon status bars: name, level, health.
            hp1.setText(this.updateHP1());
            // change visibility of menus. 
            battleMenu.setVisible(true);
            pokemonMenu.setVisible(false);
        }
        if(e.getSource() == pokemons[4])
        {
            // switch the active pokemon's image to the pokemon's image in pokemons[0].
            playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[4].getText())));
            // update the fight moves menu. 
            playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, 5);
            playerPokemon = aEncounter.getPlayerActivePokemon();
            this.styleMoveButtons();
            // update the pokemon status bars: name, level, health.
            hp1.setText(this.updateHP1());
            // change visibility of menus. 
            battleMenu.setVisible(true);
            pokemonMenu.setVisible(false);
        }
        if(e.getSource() == pokemons[5])
        {
            // switch the active pokemon's image to the pokemon's image in pokemons[0].
            playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[5].getText())));
            // update the fight moves menu. 
            playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, 6);
            playerPokemon = aEncounter.getPlayerActivePokemon();
            this.styleMoveButtons();
            // update the pokemon status bars: name, level, health.
            hp1.setText(this.updateHP1());
            // change visibility of menus. 
            battleMenu.setVisible(true);
            pokemonMenu.setVisible(false);
        }
    }
 
    /**
     * What it does: creates an Image object with the source path.
     * @param source this should be the url of the image's location.
     * @return this image with the Pokemon. 
     */
    public Image getImage(String source) {
        Image img = new Image(source);
        return img;
    }
 
    /**
     * What it does: it locates the Player's pokemon url path source used to create an image on the GUI.
     * @param pokeName this should be the Pokemon's name to be displayed.
     * @return the url path of the Player's pokemon image. 
     */
    public String whatPokeBackImage(String pokeName)
    { 
        if(pokeName.equals("Flareon"))
        {
            path = "https://vignette.wikia.nocookie.net/pokemon/images/d/d5/FlareonBackXY.gif/revision/latest?cb=20140721002515";
        }
        else if(pokeName.equals("Pikachu"))
        {
            path = "https://vignette.wikia.nocookie.net/pokemon/images/5/5b/Pikachu_Back_XY.gif/revision/latest?cb=20141009080948";
        }
        else if(pokeName.equals("Poliwrath"))
        {
            path = "https://vignette.wikia.nocookie.net/misdreavous525sofficialtppage/images/6/6c/Poliwrath_XY_back.gif/revision/latest?cb=20150621195202";
        }
        else if(pokeName.equals("Gengar"))
        {
            path = "https://vignette.wikia.nocookie.net/pokemon/images/8/8b/Gengar_Back_XY.gif/revision/latest?cb=20140901010942";
        }
        else if(pokeName.equals("Sandslash"))
        {
            path = "https://vignette.wikia.nocookie.net/misdreavous525sofficialtppage/images/0/01/Sandslash_XY_back.gif/revision/latest?cb=20151107164632";
        } else {} // do nothing 
        return path; 
    }
 
    /**
     * What it does: displays the fight menu consisting of the Player's active pokemon's move-set and
     *               updates the text-box of the wild encounter status. 
     */
    public void fight()
    {
        // Get the active pokemon and display the battle status on the GUI. 
        playerPokemon = aEncounter.getPlayerActivePokemon();
        String bla = "Displaying " + playerPokemon.getIdentStats().getName() + "'s moves.";
        text.setText(bla + aEncounter.getAttackStatus());
        
        // Update move buttons with active pokemon's move-set along with CSS styling. 
        this.updateMoves(); 
 
        battleMenu.setVisible(false);           // hide the battle menu while fight menu is active. 
        fightMenu.setVisible(true);             // show the fight menu while battle menu is hidden.
        wasMove1Used = false;                   // The following flags are used to help the 
        wasMove2Used = false;                   //      fight calculation used in Encounter class. 
        wasMove3Used = false;
        wasMove4Used = false;
    }
 
    public void updateMoves() {
        // Update the active pokemon's move-set on the GUI. 
        move1.setText(playerPokemon.getMove(0).getMoveName());
        move2.setText(playerPokemon.getMove(1).getMoveName());
        move3.setText(playerPokemon.getMove(2).getMoveName());
        move4.setText(playerPokemon.getMove(3).getMoveName());
    }

    public void styleMoveButtons()
    {
        // Customizing the move buttons with CSS
        move1.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-color: slategray;" + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
                + EncounterGUI.setColor(0) + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        move2.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-color: slategray;" + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
                + EncounterGUI.setColor(1) + "-fx-font-size: 32px;" + "-fx-text-align: center;" 
                + "-fx-text-base-color: rgb(0,0,0);");
        move3.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + EncounterGUI.setColor(2) + "-fx-border-color: slategray;" + "-fx-border-radius: 400px;"
                + "-fx-border-width: 7px;" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
                + "-fx-text-base-color: rgb(0,0,0);");
        move4.setStyle("-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
                + "-fx-border-color: slategray;" + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
                + EncounterGUI.setColor(3) + "-fx-font-size: 32px;" + "-fx-text-align: center;" 
                + "-fx-text-base-color: rgb(0,0,0);");
    }

    /**
     * Shows the Player's item on the GUI via text-box. Currently it is hard coded
     * to use a pokeball.
     */
    public void bag()
    {
        // Display the items on the text-box. 
        String catchStatus = aEncounter.bag("1");
        text.setText(aEncounter.displayItems() + "\n" + catchStatus + "\n" + aEncounter.getAttackStatus());
        fightMenu.setVisible(false);
        
        System.out.println("Catch Status: " + catchStatus);
       
        // If wild pokemon was caught. 
        if(catchStatus.equals("Caught wild " + wildPokemon.getIdentStats().getName()))
        {
            Soundtrack.stopMusic();
            Soundtrack.loadMusic("in_game1.wav");
            Soundtrack.startMusic();
            sceneController.returnScene();
        } else {    // pokemon wasnt caught. 
            battleMenu.setVisible(true);
            fightMenu.setVisible(false);
            pokemonMenu.setVisible(false);
        }
    }
 
    /**
     * Initializes the pokemon switch buttons from Pokemon 1-6 in Player's Party. 
     */
    public void pokemon()
    {
        // Hide the other menus while Pokemon menu is active. 
        battleMenu.setVisible(false);
        fightMenu.setVisible(false);
        pokemonMenu.setVisible(true);
       
        // Creating the pokemon party. 
         int teamSize = aEncounter.getPlayerTeam().getNumOfPokesInTeam();
         if (teamSize >= 1) // temporary should be teamSize > 1
         {
             for (int i = 0; i < teamSize; i++) // temproary should be int i = 1
             {
                 if(aEncounter.getPlayerTeam().getPokemonAtIndex(i).getIdentStats().getName() != "")
                 {
                   pokemons[i].setText(aEncounter.getPlayerTeam().getPokemonAtIndex(i).getIdentStats().getName()); 
                   pokemons[i].setOnAction(e -> {this.pokemonSwitch(e);});
                 }
             }
         }
 
        // Update the text-box.  
        aEncounter.setAttacker(false);
        String temp = aEncounter.getAttackStatus();
        text.setText(temp);
    }
 
    /**
     * Exits the wild pokemon encounter if "run is successful". 
     */
    public void run()
    {
        String runOutcome = aEncounter.run();
        text.setText(runOutcome);
        if (runOutcome.equals("Run away was unsuccessful")) {
            text.setText(runOutcome + "\n" + aEncounter.getAttackStatus());
            battleMenu.setVisible(false);
            this.updateMoves();
            fightMenu.setVisible(true);
        }
 
        // if run was successful, exit the battle.
        if (!(runOutcome.equals("Run away was unsuccessful"))) {
            // code here to switch back into previous screen.
            Soundtrack.stopMusic();
            Soundtrack.loadMusic("in_game1.wav");
            Soundtrack.startMusic();
            sceneController.returnScene();
        }
    }
 
    /**
     * Uses the pokemon's first move and calculate the damage.
     */
    public void move1()
    {
        // Determines if the first move was used. 
        playerPokemon = aEncounter.getPlayerTeam().getActivePokemon();
        input = "0";
        wasMove1Used = true;
        text.setText(aEncounter.fight("0", playerPokemon));
 
        // Update pokemons health
        hp1.setText(this.updateHP1());
        hp2.setText(wildPokemon.getIdentStats().getName() +
        "       Lv." + Integer.toString(wildPokemon.getIdentStats().getLevel()) 
        + "\n    " + Integer.toString(wildPokemon.getDefensiveStats().getHPCurrent()) + " / "
        + Integer.toString(wildPokemon.getDefensiveStats().getHP()) + " HP");
 
        // Update text box. 
        if (aEncounter.getAttacker()) {
            attackerStatus = " \n You are attacking.";
        } else {
            attackerStatus = "You are defending.";
        }
        fightMenu.setVisible(false);
        battleMenu.setVisible(true);
    }
    
    /**
     * Uses the pokemon's second move and calculate the damage.
     */
    public void move2()
    {
        // Determine if move 2 was used. 
        input = "1";
        wasMove2Used = true;
        text.setText(aEncounter.fight("1", playerPokemon));
 
        // Update pokemons health
        hp1.setText(playerPokemon.getIdentStats().getName() +
                "       Lv." + Integer.toString(playerPokemon.getIdentStats().getLevel()) 
                + "\n    " + Integer.toString(playerPokemon.getDefensiveStats().getHPCurrent()) + " / "
                + Integer.toString(playerPokemon.getDefensiveStats().getHP()) + " HP");
                hp2.setText(wildPokemon.getIdentStats().getName() +
                "       Lv." + Integer.toString(wildPokemon.getIdentStats().getLevel()) 
                + "\n    " + Integer.toString(wildPokemon.getDefensiveStats().getHPCurrent()) + " / "
                + Integer.toString(wildPokemon.getDefensiveStats().getHP()) + " HP");
 
        battleMenu.setVisible(true);
        fightMenu.setVisible(false);
    }
 
    /**
     * Uses the pokemon's third move and calculate the damage.
     */
    public void move3()
    {
        // Determine if move 3 was used. 
        wasMove3Used = true; 
        text.setText(aEncounter.fight("2", playerPokemon));
 
        // Update pokemons health
        hp1.setText(playerPokemon.getIdentStats().getName() +
            "       Lv." + Integer.toString(playerPokemon.getIdentStats().getLevel()) 
            + "\n    " + Integer.toString(playerPokemon.getDefensiveStats().getHPCurrent()) + " / "
            + Integer.toString(playerPokemon.getDefensiveStats().getHP()) + " HP");
        hp2.setText(wildPokemon.getIdentStats().getName() +
            "       Lv." + Integer.toString(wildPokemon.getIdentStats().getLevel()) 
            + "\n    " + Integer.toString(wildPokemon.getDefensiveStats().getHPCurrent()) + " / "
            + Integer.toString(wildPokemon.getDefensiveStats().getHP()) + " HP");
 
        battleMenu.setVisible(true);
        fightMenu.setVisible(false);
    }
 
    /**
     * Uses the pokemon's fourth move and calculate the damage.
     */
    public void move4()
    {
        // Determine if move 4 was used.
        wasMove4Used = true;
        text.setText(aEncounter.fight("3", playerPokemon));
 
         // Update pokemons health
         hp1.setText(playerPokemon.getIdentStats().getName() +
         "       Lv." + Integer.toString(playerPokemon.getIdentStats().getLevel()) 
         + "\n    " + Integer.toString(playerPokemon.getDefensiveStats().getHPCurrent()) + " / "
         + Integer.toString(playerPokemon.getDefensiveStats().getHP()) + " HP");
         hp2.setText(wildPokemon.getIdentStats().getName() +
         "       Lv." + Integer.toString(wildPokemon.getIdentStats().getLevel()) 
         + "\n    " + Integer.toString(wildPokemon.getDefensiveStats().getHPCurrent()) + " / "
         + Integer.toString(wildPokemon.getDefensiveStats().getHP()) + " HP");
 
         battleMenu.setVisible(true);
         fightMenu.setVisible(false);
         // text.setText(aEncounter.fight(playerPokemon.getMove(3).getMoveName()));
    }
    
    /**
     * Sets up the six pokemon buttons for players to switch their pokemons. 
     */
    public void createPokemonPartyUI()
    {
        for(int i = 0; i < 6; i++)
        {
            pokemons[i] = new Button();
            pokemons[i].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
            + "-fx-border: none; " + "-fx-border-radius: 300px;" 
            + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
            + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
            pokemonMenu.getChildren().add(pokemons[i]);
        }
    }
 
    /**
     * CSS styling for pokemon buttons. 
     */
    public void styleThemPokemonButtons()
    {
       // Pokemon button 0 (actually 1)
        pokemons[0].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
           @Override 
           public void handle(MouseEvent e)
           {  
                pokemons[0].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                     + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                     + "-fx-border-width: 7px;" + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;"
                     + "-fx-text-align: center;" + "-fx-text-base-color: white");
           }
        });
        pokemons[0].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
            @Override 
            public void handle(MouseEvent e)
            {
                pokemons[0].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                    + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                    + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
                    + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
            }
        });
 
        // Pokemon button 1 
        pokemons[1].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override 
            public void handle(MouseEvent e)
            {  
                 pokemons[1].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                      + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                      + "-fx-border-width: 7px;" + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;"
                      + "-fx-text-align: center;" + "-fx-text-base-color: white");
            }
        });
        pokemons[1].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
             @Override 
             public void handle(MouseEvent e)
             {
                 pokemons[1].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                     + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                     + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
                     + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
             }
        });
 
        // Pokemon button 2
        pokemons[2].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override 
            public void handle(MouseEvent e)
            {  
                 pokemons[2].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                      + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                      + "-fx-border-width: 7px;" + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;"
                      + "-fx-text-align: center;" + "-fx-text-base-color: white");
            }
        });
        pokemons[2].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
             @Override 
             public void handle(MouseEvent e)
             {
                 pokemons[2].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                     + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                     + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
                     + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
             }
        });
         
        // Pokemon button 3
        pokemons[3].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override 
            public void handle(MouseEvent e)
            {  
                 pokemons[3].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                      + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                      + "-fx-border-width: 7px;" + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;"
                      + "-fx-text-align: center;" + "-fx-text-base-color: white");
            }
        });
        pokemons[3].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
             @Override 
             public void handle(MouseEvent e)
             {
                 pokemons[3].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                     + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                     + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
                     + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
             }
        });
 
        // Pokemon button 4
        pokemons[4].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override 
            public void handle(MouseEvent e)
            {  
                 pokemons[4].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                      + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                      + "-fx-border-width: 7px;" + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;"
                      + "-fx-text-align: center;" + "-fx-text-base-color: white");
            }
        });
        pokemons[4].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
             @Override 
             public void handle(MouseEvent e)
             {
                 pokemons[4].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                     + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                     + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
                     + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
             }
        });
 
        // Pokemon button 5 
        pokemons[5].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
            @Override 
            public void handle(MouseEvent e)
            {  
                 pokemons[5].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                      + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                      + "-fx-border-width: 7px;" + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;"
                      + "-fx-text-align: center;" + "-fx-text-base-color: white");
            }
        });
        pokemons[5].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
             @Override 
             public void handle(MouseEvent e)
             {
                 pokemons[5].setStyle("-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-pref-height: 300px;"
                     + "-fx-border: none; " + "-fx-border-radius: 300px;" 
                     + "-fx-border-width: 7px;" + "-fx-background-color: white;" + "-fx-font-size: 32px;"
                     + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);");
             }
        });
    }
    
    public boolean getDone() {
        return done;
    }
 
    /**
     * A flag to indiciate if move 1 was used used for fight() calculation in Encounter class. 
     */
    public boolean getMove1UsedStatus() {
        return EncounterGUI.wasMove1Used;
    }
 
    /**
     * A flag to indiciate if move 2 was used used for fight() calculation in Encounter class. 
     */
    public boolean getMove2UsedStatus() {
        return EncounterGUI.wasMove2Used;
    }
 
    /** 
     * A flag to indiciate if move 3 was used used for fight() calculation in Encounter class.  
    */
    public boolean getMove3UsedStatus() {
        return EncounterGUI.wasMove3Used;
    }
 
    /**
     * A flag to indiciate if move 4 was used used for fight() calculation in Encounter class. 
     */
    public boolean getMove4UsedStatus() {
        return EncounterGUI.wasMove4Used;
    }
 
    /**
     * A flag to indicate if run was successful or not. 
     */
    public boolean getRunStatus() {
        return this.runStatus;
    }
 
    /**
     * A CSS function to set the button background color based on their move "types". Example: fire type = red background.
     */
    public static String setColor(int i) {
        String color = "";
        if (playerPokemon.getMove(i).getMoveType().equals("Electric"))
        {
            color = "-fx-background-color: rgb(255,255,51);";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Normal"))
        {
            color = "-fx-background-color: white;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Fire"))
        {
            color = "-fx-background-color: crimson;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Water"))
        {
            color = "-fx-background-color: dodgerblue;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Ghost"))
        {
            color = "-fx-background-color: mediumpurple;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Fighting"))
        {
            color = "-fx-background-color: darkkhaki;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Dark"))
        {
            color = "-fx-background-color: darkslateblue;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Ice"))
        {
            color = "-fx-background-color: skyblue;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Psychic"))
        {
            color = "-fx-background-color: magenta;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Rock"))
        {
            color = "-fx-background-color: chocolate;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Ground"))
        {
            color = "-fx-background-color: sienna;";
        }
        else if(playerPokemon.getMove(i).getMoveType().equals("Poison"))
        {
            color = "-fx-background-color: purple;";
        }
        else{} // do nothing 
        return color; 
    }

    public void getFrontPokeImage()
    {
       // Display the wild pokemon, temporary set-up, will use a function to set wildPokePath.
        if (wildPokemon.getIdentStats().getName().equals("Flareon")) {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/0/0c/Flareon_SS.gif/revision/latest?cb=20200107233812";
        } else if (wildPokemon.getIdentStats().getName().equals("Poliwrath")) {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/0/03/Poliwrath_XY.gif/revision/latest?cb=20150201054011";
        } else if (wildPokemon.getIdentStats().getName().equals("Gengar")) {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/7/7c/Gengar_Shiny_XY.gif/revision/latest?cb=20140901010939";
        } else if (wildPokemon.getIdentStats().getName().equals("Pikachu")) {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/1/18/Pikachu-F_SS.gif/revision/latest?cb=20200107220953";
        } else if (wildPokemon.getIdentStats().getName().equals("Sandslash")) {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/2/24/Sandslash_XY.gif/revision/latest?cb=20150201055633";
        } else {
        } // do nothing
    }

    /**
     * Sends data into the wild encounter scene. 
     */
    public void setData(Pokemon wildPoke, Pokemon playerPoke2, Encounter aEncounter2) {
        this.wildPokemon = wildPoke;
        this.playerPokemon = playerPoke2; 
        this.aEncounter = aEncounter2; 
	}
}
 
 
 

