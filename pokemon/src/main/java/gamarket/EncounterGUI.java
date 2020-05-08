package gamarket;
 
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
//import gamarket.test.playerTest;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
 
public class EncounterGUI{
    private Button move1, move2, move3, move4;
    private boolean runStatus;
    private Button hp1, hp2; 
    private Button fight, bag, pokemon, run, box; 
    private GridPane gp, poke, menu, textlols, playerSide, wildSide; 
    private VBox battleMenu, fightMenu;  
    private Text text;
    static Pokemon wildPokemon, playerPokemon;
    private String wildPokePath, playerPokePath, attackerStatus;
    private static Encounter aEncounter;
    static boolean wasMove1Used, wasMove2Used, wasMove3Used, wasMove4Used;
    private ImageView enemyPoke, playerPoke, fightImage, bagImage, pokemonImage, runImage, background; 
    private VBox pokemonMenu;
    private Button pokemons[];
    private String path;
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
        background = this.getImageView("./pokemon/imgs/battle_background.png", 300, 300);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, 
                                                    BackgroundSize.AUTO, false, false, true, true);
        gp.setBackground(new Background(new BackgroundImage(background.getImage(), BackgroundRepeat.NO_REPEAT,
                           BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
 
        // Image for the fight button. 
        fightImage = this.getImageView("./pokemon/imgs/fight_button_image.png", 45, 90);
        // Image for the bag button. 
        bagImage = this.getImageView("./pokemon/imgs/bag_image.png", 45, 90);
        // Image for the pokemon button. 
        pokemonImage = this.getImageView("./pokemon/imgs/pokeball.png", 45, 80);
        // Image for the run button. 
        runImage = this.getImageView("./pokemon/imgs/running_icon.png", 65, 120);
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
        fight = new Button("Fight", fightImage);
        bag = new Button("Bag", bagImage);
        pokemon = new Button("Pokemon", pokemonImage);
        run = new Button("Run", runImage);
 
        // Sync the Player's pokemon moves into the buttons.
        move1 = new Button(); 
        move1.setOnAction(e -> {this.move("0");});
        move2 = new Button(); 
        move2.setOnAction(e -> {this.move("1");});
        move3 = new Button(); 
        move3.setOnAction(e -> {this.move("2");});
        move4 = new Button(); 
        move4.setOnAction(e -> {this.move("3");});
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
        fight.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: salmon;");
        bag.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: white;");
        pokemon.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: white;");
        run.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: white;");
        
        // MENU STUFF
        menu = new GridPane();
        // Set up the battle menu buttons action events. 
        fight.setOnAction(e -> {this.fight();});
        bag.setOnAction(e -> {this.bag();});
        pokemon.setOnAction(e -> {this.pokemon();});
        run.setOnAction(e -> {this.run();});
 
        // Create the CSS style mouse events for the battle menu. 
        fight.setOnMouseEntered(e -> {this.battleMouseEnterStyle("fight");});
        fight.setOnMouseExited(e -> {this.battleMouseExitStyle("fight");});
        bag.setOnMouseEntered(e -> {this.battleMouseEnterStyle("bag");});
        bag.setOnMouseExited(e -> {this.battleMouseExitStyle("bag");});
        pokemon.setOnMouseEntered(e -> {this.battleMouseEnterStyle("pokemon");});
        pokemon.setOnMouseExited(e -> {this.battleMouseExitStyle("pokemon");});
        run.setOnMouseEntered(e -> {this.battleMouseEnterStyle("run");});
        run.setOnMouseExited(e -> {this.battleMouseExitStyle("run");});
 
        // Create the CSS style mouse events for fight menu: move 1, move 2, move 3, move 4.
        move1.setOnMouseEntered(e -> {this.moveMouseEnterExitStyle("move1", "enter");}); 
        move2.setOnMouseEntered(e -> {this.moveMouseEnterExitStyle("move2", "enter");});
        move3.setOnMouseEntered(e -> {this.moveMouseEnterExitStyle("move3", "enter");});
        move4.setOnMouseEntered(e -> {this.moveMouseEnterExitStyle("move4", "enter");});
        move1.setOnMouseExited(e -> {this.moveMouseEnterExitStyle("move1", "exit");});
        move2.setOnMouseExited(e -> {this.moveMouseEnterExitStyle("move2", "exit");});  
        move3.setOnMouseExited(e -> {this.moveMouseEnterExitStyle("move3", "exit");});  
        move4.setOnMouseExited(e -> {this.moveMouseEnterExitStyle("move4", "exit");});  
       
        // The initial text when the pokemon wild encounter begins. 
        text = new Text();
        text.setText("A wild " + wildPokemon.getIdentStats().getName() + " appears!" + "\nPlayer: Lets go "
                + playerPokemon.getIdentStats().getName() + "!");
        text.setFont(Font.font("Old-town fantasy", FontWeight.BOLD, FontPosture.REGULAR, 32));
        battleMenu.setVisible(true);
        fightMenu.setVisible(false);     
        
        // Initiate the status bars using Pokemon's name, level, and health. 
        hp1 = new Button(this.updateHP(playerPokemon));
        hp2 = new Button(this.updateHP(wildPokemon));
 
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
        hp2.setStyle(this.hpStyle());
        StackPane wildPokeHealthBar = new StackPane();          // The wild pokemon. 
        wildPokeHealthBar.getChildren().add(hp1);
        gp.add(wildPokeHealthBar, 0, 1);
        wildPokeHealthBar.setPadding(new Insets(0, 1035, 160, 0));  
        hp1.setStyle(this.hpStyle());
 
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
     * CSS styling for move buttons mouse enter and exit events. 
     * @param string the name of the move button.
     * @param myEvent the name of the event MouseEnter or MouseExit.
     */
    private void moveMouseEnterExitStyle(String string, String myEvent) {
        String style; 
            if(string.equals("move1"))
            {
                style = this.standardMoveStyleButton() + EncounterGUI.setColor(0);
                if(myEvent.equals("enter"))
                {
                    style += "-fx-border-color: rgb(0,0,0);";
                } else {    
                    style += "-fx-border-color: slategray;";
                }
                move1.setStyle(style);
            }
            if(string.equals("move2"))
            {
                style = this.standardMoveStyleButton() + EncounterGUI.setColor(1);
                if(myEvent.equals("enter"))
                {
                    style += "-fx-border-color: rgb(0,0,0);";
                } else {    
                    style += "-fx-border-color: slategray;";
                }
                move2.setStyle(style);
            }
            if(string.equals("move3"))
            {
                style = this.standardMoveStyleButton() + EncounterGUI.setColor(2);
                if(myEvent.equals("enter"))
                {
                    style += "-fx-border-color: rgb(0,0,0);";
                } else {    
                    style += "-fx-border-color: slategray;";
                }
                move3.setStyle(style);
            }
            if(string.equals("move4")) {
                style = this.standardMoveStyleButton() + EncounterGUI.setColor(3);
                if(myEvent.equals("enter"))
                {
                    style += "-fx-border-color: rgb(0,0,0);";
                } else {    
                    style += "-fx-border-color: slategray;";
                }
                move4.setStyle(style);
            }
    }
    
    /** Common CSS style for move buttons. */
    public String standardMoveStyleButton() {
        return "-fx-background-radius: 400px;" + "-fx-pref-width: 400px; " + "-fx-border: none; "
            + "-fx-border-radius: 400px;" + "-fx-border-width: 7px;"
            + "-fx-font-size: 32px;" + "-fx-text-align: center;"
            + "-fx-text-base-color: rgb(0,0,0);";
    }
    
    /** CSS style for battle buttons when a mouse enters a node */
    private void battleMouseEnterStyle(String string) {
        if(string.equals("fight"))
        {
            fight.setStyle(this.styleMouseEnterBattleMenuButtons() + "-fx-border-color: salmon;");
        }
        else if(string.equals("bag"))
        {
            bag.setStyle(this.styleMouseEnterBattleMenuButtons() + "-fx-border-color: wheat;");
        }
        else if(string.equals("pokemon"))
        {
            pokemon.setStyle(this.styleMouseEnterBattleMenuButtons() + "-fx-border-color: springgreen;");
        }
        else if(string.equals("run"))
        {
            run.setStyle(this.styleMouseEnterBattleMenuButtons() + "-fx-border-color: dodgerblue;");
        } else {} // do nothing 
    }

    /** CSS style for when a mouse exits a node. */
    public void battleMouseExitStyle(String string) {
        if(string.equals("fight"))
        {
            fight.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: salmon;");
        }
        else if(string.equals("bag"))
        {
            bag.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: white;");
        }
        else if(string.equals("pokemon"))
        {
            pokemon.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: white;");
        }
        else if(string.equals("run"))
        {
            run.setStyle(this.styleMouseExitBattleMenuButtons() + "-fx-background-color: white;");
        } else {} // do nothing 
    }

    /** CSS style for health pokemon bars */
    private String hpStyle() {
        return "-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
                + "-fx-background-color: white;" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
                + "-fx-font-size: 24px;" + "-fx-text-align: center;" + "-fx-text-base-color: rgb(0,0,0);";
    }

    /**  Updates the pokemon health, name, and level. */
    public String updateHP(Pokemon myPoke) {
        // update the player's pokemon status bars: name, level, health.
        return playerPokemon.getIdentStats().getName() + "       Lv."
                + Integer.toString(playerPokemon.getIdentStats().getLevel()) + "\n    "
                + Integer.toString(playerPokemon.getDefensiveStats().getHPCurrent()) + " / "
                + Integer.toString(playerPokemon.getDefensiveStats().getHP()) + " HP";
    }

    /**
     * Switches the Player's pokemon with another pokemon that is in the player's team.
     * @param e this is the Pokemon you are switching with.
     */
    public void pokemonSwitch(ActionEvent e) {
        if (e.getSource() == pokemons[0]) {
            this.pokemonSwitchHelper(0);
        }
        if (e.getSource() == pokemons[1]) {
            this.pokemonSwitchHelper(1);
        }
        if (e.getSource() == pokemons[2]) {
            this.pokemonSwitchHelper(2);
        }
        if (e.getSource() == pokemons[3]) {
            this.pokemonSwitchHelper(3);
        }
        if (e.getSource() == pokemons[4]) {
            this.pokemonSwitchHelper(4);
        }
        if (e.getSource() == pokemons[5]) {
            this.pokemonSwitchHelper(5);
        }
    }

    /** Common code for pokemon switch, switch the pokemon image, update moves, update hp bar. */
    public void pokemonSwitchHelper(int index) {
         // switch the active pokemon's image to the pokemon's image in pokemons[0].
         playerPoke.setImage(this.getImage(this.whatPokeBackImage(pokemons[index].getText())));
         // update the fight moves menu.
         playerPokemon = aEncounter.getPlayerTeam().switchPokemonInTeam(1, index+1);
         playerPokemon = aEncounter.getPlayerActivePokemon();
         this.styleMoveButtons();
         // update the pokemon status bars: name, level, health.
         hp1.setText(this.updateHP(playerPokemon));
         // change visibility of menus.
         battleMenu.setVisible(true);
         pokemonMenu.setVisible(false);
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
     * What it does: it locates the Player's pokemon url path source used to create
     * an image on the GUI.
     * @param pokeName this should be the Pokemon's name to be displayed.
     * @return the url path of the Player's pokemon image.
     */
    public String whatPokeBackImage(String pokeName) {
        if (pokeName.equals("Flareon")) {
            path = "https://vignette.wikia.nocookie.net/pokemon/images/d/d5/FlareonBackXY.gif/revision/latest?cb=20140721002515";
        } else if (pokeName.equals("Pikachu")) {
            path = "https://vignette.wikia.nocookie.net/pokemon/images/5/5b/Pikachu_Back_XY.gif/revision/latest?cb=20141009080948";
        } else if (pokeName.equals("Poliwrath")) {
            path = "https://vignette.wikia.nocookie.net/misdreavous525sofficialtppage/images/6/6c/Poliwrath_XY_back.gif/revision/latest?cb=20150621195202";
        } else if (pokeName.equals("Gengar")) {
            path = "https://vignette.wikia.nocookie.net/pokemon/images/8/8b/Gengar_Back_XY.gif/revision/latest?cb=20140901010942";
        } else if (pokeName.equals("Sandslash")) {
            path = "https://vignette.wikia.nocookie.net/misdreavous525sofficialtppage/images/0/01/Sandslash_XY_back.gif/revision/latest?cb=20151107164632";
        } else {
        } // do nothing
        return path;
    }

    /**
     * What it does: displays the fight menu consisting of the Player's active
     * pokemon's move-set and updates the text-box of the wild encounter status.
     */
    public void fight() {
        // Get the active pokemon and display the battle status on the GUI.
        playerPokemon = aEncounter.getPlayerActivePokemon();
        String bla = "Displaying " + playerPokemon.getIdentStats().getName() + "'s moves.";
        text.setText(bla + aEncounter.getAttackStatus());
        // Update move buttons with active pokemon's move-set along with CSS styling.
        this.updateMoves();
        battleMenu.setVisible(false); // hide the battle menu while fight menu is active.
        fightMenu.setVisible(true); // show the fight menu while battle menu is hidden.
        wasMove1Used = false; // The following flags are used to help the
        wasMove2Used = false; // fight calculation used in Encounter class.
        wasMove3Used = false;
        wasMove4Used = false;
    }

    /** Updates the active pokemon's move-set. */
    public void updateMoves() {
        // Update the active pokemon's move-set on the GUI.
        move1.setText(playerPokemon.getMove(0).getMoveName());
        move2.setText(playerPokemon.getMove(1).getMoveName());
        move3.setText(playerPokemon.getMove(2).getMoveName());
        move4.setText(playerPokemon.getMove(3).getMoveName());
    }

    /** Styles the move buttons. */
    public void styleMoveButtons() {
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
    public void bag() {
        // Display the items on the text-box.
        String catchStatus = aEncounter.bag("1");
        text.setText(aEncounter.displayItems() + "\n" + catchStatus + "\n" + aEncounter.getAttackStatus());
        fightMenu.setVisible(false);

        System.out.println("Catch Status: " + catchStatus);

        // If wild pokemon was caught.
        if (catchStatus.equals("Caught wild " + wildPokemon.getIdentStats().getName())) {
            Soundtrack.stopMusic();
            Soundtrack.loadMusic("in_game1.wav");
            Soundtrack.startMusic();
            sceneController.returnScene();
        } else { // pokemon wasnt caught.
            battleMenu.setVisible(true);
            fightMenu.setVisible(false);
            pokemonMenu.setVisible(false);
        }
    }

    /** Initializes the pokemon switch buttons from Pokemon 1-6 in Player's Party. */
    public void pokemon() {
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
                if (aEncounter.getPlayerTeam().getPokemonAtIndex(i).getIdentStats().getName() != "") {
                    pokemons[i].setText(aEncounter.getPlayerTeam().getPokemonAtIndex(i).getIdentStats().getName());
                    pokemons[i].setOnAction(e -> { this.pokemonSwitch(e); });
                }
            }
        }
        // Update the text-box.
        aEncounter.setAttacker(false);
        String temp = aEncounter.getAttackStatus();
        text.setText(temp);
    }

    /** Exits the wild pokemon encounter if "run is successful". */
    public void run() {
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
     * Calculates the move outcome in the wild pokemon encounter.
     * @param moveNumber this indicates what move was used 0-3.
     */
    public void move(String moveNumber) {
        // Determines if the first move was used.
        playerPokemon = aEncounter.getPlayerTeam().getActivePokemon();
        wasMove1Used = true;
        text.setText(aEncounter.fight(moveNumber, playerPokemon));
        // Update pokemons health
        hp1.setText(this.updateHP(playerPokemon));
        hp2.setText(wildPokemon.getIdentStats().getName() + "       Lv."
                + Integer.toString(wildPokemon.getIdentStats().getLevel()) + "\n    "
                + Integer.toString(wildPokemon.getDefensiveStats().getHPCurrent()) + " / "
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

    /** Sets up the six pokemon buttons for players to switch their pokemons. */
    public void createPokemonPartyUI() {
        for (int i = 0; i < 6; i++) {
            pokemons[i] = new Button();
            pokemons[i].setStyle(this.styleStandardPokemonSwitchButtons());
            pokemonMenu.getChildren().add(pokemons[i]);
        }
    }

    /** CSS styling for pokemon buttons. */
    public void styleThemPokemonButtons() {
        pokemons[0].setOnMouseEntered(e -> {this.styleThemPokeSwitchButtons(0, "enter");});
        pokemons[0].setOnMouseExited(e -> {this.styleThemPokeSwitchButtons(0, "exit");});
        pokemons[1].setOnMouseEntered(e -> {this.styleThemPokeSwitchButtons(1, "enter");});
        pokemons[1].setOnMouseExited(e -> {this.styleThemPokeSwitchButtons(1, "exit");});
        pokemons[2].setOnMouseEntered(e -> {this.styleThemPokeSwitchButtons(2, "enter");});
        pokemons[2].setOnMouseExited(e -> {this.styleThemPokeSwitchButtons(2, "exit");});
        pokemons[3].setOnMouseEntered(e -> {this.styleThemPokeSwitchButtons(3, "enter");});
        pokemons[3].setOnMouseExited(e -> {this.styleThemPokeSwitchButtons(3, "exit");});
        pokemons[4].setOnMouseEntered(e -> {this.styleThemPokeSwitchButtons(4, "enter");});
        pokemons[4].setOnMouseExited(e -> {this.styleThemPokeSwitchButtons(4, "exit");});
        pokemons[5].setOnMouseEntered(e -> {this.styleThemPokeSwitchButtons(5, "enter");});
        pokemons[5].setOnMouseExited(e -> {this.styleThemPokeSwitchButtons(5, "exit");});
    }

    /** A flag to indiciate if move 1 was used used for fight() calculation in Encounter class. */
    public boolean getMove1UsedStatus() { return EncounterGUI.wasMove1Used; }

    /** A flag to indiciate if move 2 was used used for fight() calculation in Encounter class. */
    public boolean getMove2UsedStatus() { return EncounterGUI.wasMove2Used; }

    /** A flag to indiciate if move 3 was used used for fight() calculation in Encounter class. */
    public boolean getMove3UsedStatus() { return EncounterGUI.wasMove3Used; }

    /** A flag to indiciate if move 4 was used used for fight() calculation in Encounter class. */
    public boolean getMove4UsedStatus() { return EncounterGUI.wasMove4Used; }

    /** A flag to indicate if run was successful or not. */
    public boolean getRunStatus() { return this.runStatus; }

    /**
     * A CSS function to set the button background color based on their move
     * "types". Example: fire type = red background.
     */
    public static String setColor(int i) {
        String color = "";
        if (playerPokemon.getMove(i).getMoveType().equals("Electric")) {
            color = "-fx-background-color: rgb(255,255,51);";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Normal")) {
            color = "-fx-background-color: white;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Fire")) {
            color = "-fx-background-color: crimson;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Water")) {
            color = "-fx-background-color: dodgerblue;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Ghost")) {
            color = "-fx-background-color: mediumpurple;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Fighting")) {
            color = "-fx-background-color: darkkhaki;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Dark")) {
            color = "-fx-background-color: darkslateblue;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Ice")) {
            color = "-fx-background-color: skyblue;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Psychic")) {
            color = "-fx-background-color: magenta;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Rock")) {
            color = "-fx-background-color: chocolate;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Ground")) {
            color = "-fx-background-color: sienna;";
        } else if (playerPokemon.getMove(i).getMoveType().equals("Poison")) {
            color = "-fx-background-color: purple;";
        } else {
        } // do nothing
        return color;
    }

    /** Locates the path to the front model of Pokemon via url. */
    public void getFrontPokeImage() {
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
        } else {} // do nothing
    }

    /** Sends data into the wild encounter scene. */
    public void setData(Pokemon wildPoke, Pokemon playerPoke2, Encounter aEncounter2) {
        this.wildPokemon = wildPoke;
        this.playerPokemon = playerPoke2;
        this.aEncounter = aEncounter2;
    }

    /** Creates an ImageView object and returns it. */
    public ImageView getImageView(String filePath, int height, int width) {
        File myFile = new File(filePath);
        String x = myFile.toURI().toString();
        ImageView myIV = new ImageView(x);
        myIV.setFitHeight(height);
        myIV.setFitWidth(width);
        return myIV;
    }

    /** CSS style when a mouse enters a battle button node. */
    public String styleMouseEnterBattleMenuButtons() {
        return "-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
            + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
            + "-fx-background-color: rgb(0,0,0);" + "-fx-font-size: 32px;" + "-fx-text-align: center;"
            + "-fx-text-base-color: white;";
    }
    /** CSS style when a mouse exits a battle button node. */
    public String styleMouseExitBattleMenuButtons() {
        return "-fx-background-radius: 300px;" + "-fx-pref-width: 300px; " + "-fx-border: none; "
            + "-fx-border-color: rgb(0,0,0);" + "-fx-border-radius: 300px;" + "-fx-border-width: 7px;"
            + "-fx-font-size: 32px;" + "-fx-text-align: center;"
            + "-fx-text-base-color: #333;";
    }
    /** Standard CSS style for pokemon switch buttons. */
    public String styleStandardPokemonSwitchButtons() {
        return "-fx-background-radius: 300px;" + "-fx-pref-width: 300px; "
            + "-fx-pref-height: 300px;" + "-fx-border: none; " + "-fx-border-radius: 300px;"
            + "-fx-border-width: 7px;" + "-fx-font-size: 32px;" + "-fx-text-align: center;";
    }
    /** Assist function for style pokemon switch buttons.  */
    public void styleThemPokeSwitchButtons(int index, String event) {
        this.stylePokeSwitchHelper(index, event);
    }
    /** Helper function for pokemon switch style buttons. */
    public void stylePokeSwitchHelper(int index, String event) {
        if(event.equals("enter"))
        {
            pokemons[index].setStyle(this.styleStandardPokemonSwitchButtons() + "-fx-background-color: rgb(0,0,0);" + "-fx-text-base-color: white;");
        }else {
            pokemons[index].setStyle(this.styleStandardPokemonSwitchButtons() + "-fx-background-color: white;" + "-fx-text-base-color: rgb(0,0,0);");
        }
    }
}
 
 
 

