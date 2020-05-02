package gamarket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Menu {
    private Player player;
    private Team playerTeam;
    protected static Menu menu;
    private SceneController sceneController;
    private StackPane menuScene;

    protected Menu(){}

    public static synchronized Menu getInstance(){
        if(menu == null){
            menu = new  Menu();

        }
        return menu;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setSceneController(Stage window){
        sceneController = SceneController.getInstance(window);
    }

    public StackPane display(){
        return menuScene;
    }

    public void renderDisplay(){
        StackPane sp = new StackPane();
        GridPane black = new GridPane();

        black.setStyle("-fx-background-color: black;" +
                "-fx-opacity: .3px");

        GridPane gameMenu = new GridPane();
        gameMenu.setAlignment(Pos.CENTER_LEFT);
        gameMenu.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 15px;" +
                "-fx-max-height: 800px;" +
                "-fx-max-width: 400px;" +
                "-fx-translate-x: 250px;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-size: 50px;" +
                "-fx-vgap: 30px;");

        //gameMenu.setGridLinesVisible(true);

        Button pokedex = new Button("Pokedex");
        pokedex.setOnAction(e -> {
            //TODO

        });
        setStyles(pokedex);

        Button pokemon = new Button("Pokemon");
        pokemon.setOnAction(e -> {
            //TODO
            sceneController.poketeamScene(playerTeam);
        });
        setStyles(pokemon);

        Button bag = new Button("Bag");
        bag.setOnAction(e -> {
            //TODO
        });
        setStyles(bag);

        Button playerInfo = new Button(player.getName());
        playerInfo.setOnAction(e -> {
            sceneController.trainerCardScene(player);
        });
        setStyles(playerInfo);

        Button saveGame = new Button("Save");
        saveGame.setOnAction(e -> {
            player.saveData();
            saveGame.setStyle(buttonStyle());
            //TODO
        });
        setStyles(saveGame);

        Button exit = new Button("Exit");
        exit.setOnAction(e -> {
            exit.setStyle(buttonStyle());
            StackPane s = (StackPane)exit.getScene().getRoot();
            s.getChildren().remove(display());

        });
        setStyles(exit);

        pokedex.setPadding(new Insets(20,20,20,20));
        gameMenu.add(pokedex, 0,0);
        gameMenu.add(pokemon, 0,1);
        gameMenu.add(bag,0,2);
        gameMenu.add(playerInfo, 0,3);
        gameMenu.add(saveGame, 0,4);
        gameMenu.add(exit, 0,5);


        sp.getChildren().addAll(black, gameMenu);

        menuScene = sp;
    }

    private void setStyles(Button button){
        button.setStyle(buttonStyle());
        button.setOnMouseEntered(event -> {
            button.setStyle(hover());
        });
        button.setOnMouseExited(event -> {
            button.setStyle(buttonStyle());
        });
    }

    private String buttonStyle(){
        String style = "-fx-background-color: white;" +
                "-fx-text-alignment: center;" +
                "-fx-max-width: 300px" +
                "-fx-translate-x: 20px;";

        return style;
    }
    private String hover(){
        String style = "-fx-background-color: white;" +
                "-fx-text-alignment: center;" +
                "-fx-font-weight: bold;" +
                "-fx-max-width: 300px" +
                "-fx-translate-x: 20px;";

        return style;
    }

	public void setTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
	}
}
