package gamarket;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class PoketeamGUI {
    private Team team;
    private SceneController sceneController;
    private Bag bag;
    private StackPane teamDisplay;

    protected static PoketeamGUI poketeamGUI;
    protected PoketeamGUI(){
    }

    public static synchronized PoketeamGUI getInstance(){
        if(poketeamGUI == null) {
            poketeamGUI = new PoketeamGUI();
        }
        return poketeamGUI;
    }

    public void setTeam(Team team) { this.team = team; }
    public void setSceneController(Stage window) { this.sceneController = SceneController.getInstance(window);  }

    public StackPane display(String... sceneType){
        GridPane gp = new GridPane();
        GridPane col1 = new GridPane();
        GridPane col2 = new GridPane();

        if(sceneType.length > 0 && sceneType[0] == "bag" ){
            renderSlots(col1, col2, sceneType);
        }else {
            renderSlots(col1, col2);
        }

        col1.setStyle("-fx-translate-y: 50px;" +
                "-fx-vgap: 10px;");
        col2.setStyle(  "-fx-vgap: 10px;");

        gp.add(col1,0,0);
        gp.add(col2, 1,0);

        Button exitBtn = new Button("Exit");
        buttonStyle(exitBtn);
        exitBtn.setOnMouseEntered(event -> {hover(exitBtn);});
        exitBtn.setOnMouseExited(event -> {buttonStyle(exitBtn);});

        gp.add(exitBtn,1,1);
        gp.setStyle("-fx-vgap: 20px;" +
                "-fx-hgap: 20px;" +
                "-fx-translate-x: 30px;" +
                "-fx-translate-y: -20px;" +
                "-fx-alignment: center;");

        teamDisplay = new StackPane();

        if(sceneType.length > 0 && sceneType[0] == "bag" ){
            exitBtn.setOnAction(ev -> { sceneController.bagScene(bag, team);});
            teamDisplay.getChildren().addAll(renderBG(), gp, renderDialogue());
        }else{
            teamDisplay.getChildren().addAll(renderBG(), gp);
            exitBtn.setOnAction(ev -> {
                sceneController.returnScene();
            });
        }

        return teamDisplay;
    }

    private void  renderSlots(GridPane col1, GridPane col2, String... sceneType){
        team.displayTeam();
        if(sceneType.length > 0 && sceneType[0] == "bag" ){
            for (int i = 0; i < 3; i++) {
                if (team.getPokemonAtIndex(i) == null) {
                    col1.add(renderEmptySlot(), 0, i);
                } else {
                    col1.add(renderSlot(team.getPokemonAtIndex(i), sceneType), 0, i);
                }
            }

            for (int i = 3; i < 6; i++) {
                if (team.getPokemonAtIndex(i) == null) {
                    col2.add(renderEmptySlot(), 0, i);
                } else {
                    col2.add(renderSlot(team.getPokemonAtIndex(i), sceneType), 0, i);
                }
            }
        }else {
            for (int i = 0; i < 3; i++) {
                if (team.getPokemonAtIndex(i) == null) {
                    col1.add(renderEmptySlot(), 0, i);
                } else {
                    col1.add(renderSlot(team.getPokemonAtIndex(i)), 0, i);
                }
            }

            for (int i = 3; i < 6; i++) {
                if (team.getPokemonAtIndex(i) == null) {
                    col2.add(renderEmptySlot(), 0, i);
                } else {
                    col2.add(renderSlot(team.getPokemonAtIndex(i)), 0, i);
                }
            }
        }
    }

    private StackPane renderSlot(Pokemon pokemon, String... sceneType){
        GridPane bg = new GridPane();
        String bgStyle = "-fx-max-width: 350px;" +
                "-fx-max-height: 200px;" +
                "-fx-min-height: 200px;" +
                "-fx-min-width:  350;" +
                "-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;";

        bg.setStyle(bgStyle + "-fx-border-color: white;");

        Button name = new Button(pokemon.getIdentStats().getName());
        nameStyle(name);

        Button level = new Button("Lv."+ pokemon.getIdentStats().getLevel());
        level.setStyle(infoStyle() +
            "-fx-min-width: 50px;" +
                "-fx-translate-x: 40px;");

        Button health = new Button("HP: "+pokemon.getDefensiveStats().getHPCurrent()+"/"+pokemon.getDefensiveStats().getHP());
        health.setStyle(infoStyle() + "-fx-translate-x: -30px;" +
                "-fx-min-width: 115px;");

        Button type = new Button("Type: " + pokemon.getIdentStats().getType());
        type.setStyle(infoStyle() + "-fx-translate-x: 40px;");

        Button atk = new Button("ATK: "+ pokemon.getOffeniveStats().getATK());
        atk.setStyle(infoStyle() + "-fx-translate-x: 40px;");

        Button spatk = new Button("SPATK: " + pokemon.getOffeniveStats().getSPATK());
        spatk.setStyle(infoStyle() + "-fx-translate-x: 40px;");

        Button spd = new Button("SPD: "+ pokemon.getOffeniveStats().getSpeed());
        spd.setStyle(infoStyle() + "-fx-translate-x: 40px;");

        Button def = new Button("DEF: " + pokemon.getDefensiveStats().getDEF());
        def.setStyle(infoStyle() + "-fx-translate-x: 40px;");

        Button spdef = new Button("DEF: " + pokemon.getDefensiveStats().getSPDEF());
        spdef.setStyle(infoStyle() + "-fx-translate-x: 40px;");

        GridPane gp = new GridPane();
        gp.setStyle("-fx-max-width: 300px;" +
                "-fx-max-height: 200px;" +
                "-fx-min-height: 200px;" +
                "-fx-min-width:  300;");

        gp.add(renderPokemon(pokemon.getIdentStats().getName()),0,0);
        gp.add(name,1,0);
        gp.add(renderGender(pokemon.getIdentStats().getGender()),2,0);
        gp.add(level,1,1);
        gp.add(health,2,1);
        gp.add(type, 1, 2);
        gp.add(atk, 1, 3);
        gp.add(spatk, 1, 4);
        gp.add(spd, 1, 5);
        gp.add(def, 2, 3);
        gp.add(spdef, 2, 4);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(bg, gp);
        if(sceneType.length > 0 && sceneType[0] == "bag" ){
            sp.setOnMouseEntered(event -> {bg.setStyle(bgStyle + "-fx-background-color: darkred;");});
            sp.setOnMouseExited(event -> {bg.setStyle(bgStyle + "-fx-background-color: black;");});
            sp.setOnMouseClicked(event -> {
                sp.getChildren().add(renderOption(sp,pokemon));
            });
        }

        return sp;
    }

    private GridPane renderEmptySlot(){
        GridPane bg = new GridPane();
        bg.setStyle("-fx-max-width: 350px;" +
                "-fx-max-height: 200px;" +
                "-fx-min-height: 200px;" +
                "-fx-min-width:  350;" +
                "-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: white;");

        return bg;
    }

    private Pane renderBG(){
        Pane pane = new Pane();
        File file = new File("./pokemon/imgs/teamBG.png");
        Image image =  new Image(file.toURI().toString());
        ImageView bg = new ImageView(image);
        bg.setFitHeight(800);
        bg.setFitWidth(800);
        pane.getChildren().add(bg);
        return pane;
    }

    private Pane renderPokemon(String name){
        Pane pane = new Pane();
        File file = new File("./pokemon/imgs/"+name.toLowerCase()+".png");
        Image image =  new Image(file.toURI().toString());
        ImageView bg = new ImageView(image);
        bg.setFitHeight(100);
        bg.setFitWidth(100);
        bg.setStyle("-fx-translate-x: -20px;");
        pane.getChildren().add(bg);
        return pane;
    }

    private Button renderGender(String gender){
        Button pane;
        if( gender.compareToIgnoreCase("male") == 0){
            pane = new Button("♂");
            pane.setStyle("-fx-text-fill: lightblue;" +
                    "-fx-font-size: 30;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-color: none;");
            return pane;
        }else if( gender.compareToIgnoreCase("female") == 0){
            pane = new Button("♀");
            pane.setStyle("-fx-text-fill: pink;" +
                    "-fx-font-size: 30;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-color: none;");
            return pane;
        }else{
            pane = new Button("");
            pane.setStyle(
                    "-fx-background-color: none;");
            return pane;
        }
    }

    private void nameStyle(Button name){
        name.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-background-color: none;" +
                "-fx-text-alignment: left;" +
                "-fx-alignment: left;" +
                "-fx-min-width: 150px;" +
                "-fx-translate-x: 30px;");
    }

    private String infoStyle(){
        String style = "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-background-color: none;" +
                "-fx-text-alignment: left;" +
                "-fx-translate-y: -30px;";
        return style;
    }


    private void buttonStyle(Button button){
         button.setStyle("-fx-background-color: darkred;" +
                "-fx-opacity: 0.5;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 40px;" +
                "-fx-background-radius: 40px;" +
                 "-fx-translate-x: 180px;" +
                 "-fx-translate-y: 5px;" +
                 "-fx-min-width: 175px;" +
                 "-fx-min-height: 85px;");

    }

    private void hover(Button button){
        button.setStyle( "-fx-background-color: darkred;" +
                "-fx-opacity: 0.9;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 40px;" +
                "-fx-background-radius: 40px;" +
                "-fx-translate-x: 180px;" +
                "-fx-translate-y: 5px;" +
                "-fx-min-width: 175px;" +
                "-fx-min-height: 85px;" +
                "-fx-border-width: 2px;");

    }

    private Button renderDialogue(){
        Button dialogue = new Button("Select a Pokemon to use your potion on.");
        dialogue.setStyle("-fx-background-color: white;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-size: 24px;" +
                "-fx-min-height: 80px;" +
                "-fx-min-width: 100px;" +
                "-fx-background-radius: 15px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2px;" +
                "-fx-translate-x: -90px;" +
                "-fx-translate-y: 320px;");
        return dialogue;
    }

    public void setBag(Bag bag){
        this.bag = bag;
    }

    private GridPane renderOption(StackPane sp, Pokemon pokemon){
        GridPane options = new GridPane();
        Button useBtn = new Button("Use Potion");
        optionStyle(useBtn);
        useBtn.setOnMouseEntered(e -> {optionHover(useBtn);});
        useBtn.setOnMouseExited(event -> {optionStyle(useBtn);});
        useBtn.setOnMouseClicked(event -> {
            this.bag.usePotion(pokemon);
            sceneController.bagScene(this.bag,this.team);
        });

        Button cancelBtn = new Button("Cancel");
        optionStyle(cancelBtn);
        cancelBtn.setOnMouseEntered(e -> {optionHover(cancelBtn);});
        cancelBtn.setOnMouseExited(event -> {optionStyle(cancelBtn);});
        cancelBtn.setOnMouseClicked(event -> {
            sp.getChildren().remove(options);
        });

        options.add(useBtn,0,0);
        options.add(cancelBtn,0,1);

        options.setStyle("-fx-alignment: center;" +
                "-fx-vgap: 5px;");

        return options;
    }

    public void optionStyle(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 50px;" +
                "-fx-min-width: 250px;" +
                "-fx-max-width: 175px;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-radius: 15px;" +
                "-fx-translate-x: 5px;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: lightgray;");
    }

    public void optionHover(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 50px;" +
                "-fx-min-width: 250px;" +
                "-fx-max-width: 175px;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-radius: 15px;" +
                "-fx-translate-x: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: black;");
    }


}
