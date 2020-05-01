package gamarket;

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
    public void setSceneController(Stage window) { this.sceneController = new SceneController(window);  }

    public StackPane display(){
        GridPane gp = new GridPane();
        GridPane col1 = new GridPane();
        GridPane col2 = new GridPane();

        for(int i = 0; i < 3; i++){
            if(team.getPokemonAtIndex(i) == null){
                col1.add(renderEmptySlot(),0, i);
            }else{
                col1.add(renderSlot(team.getPokemonAtIndex(i)), 0,i);
            }
           //System.out.println(team.getPokemonAtIndex(i).toString());
        }

        for(int i = 3; i < 6; i++){
            if(team.getPokemonAtIndex(i) == null){
                col2.add(renderEmptySlot(),0,i);
            }else{
                col2.add(renderSlot(team.getPokemonAtIndex(i)), 0,i);
            }
            //System.out.println(team.getPokemonAtIndex(i).toString());
        }
        gp.add(col1,0,0);
        gp.add(col2, 1,0);

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> {
            sceneController.returnScene();
        });

        gp.add(exitBtn,1,1);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(renderBG(), gp);
        return sp;
    }

    private StackPane renderSlot(Pokemon pokemon){
        GridPane bg = new GridPane();
        bg.setStyle("-fx-max-width: 350px;" +
                "-fx-max-height: 250px;" +
                "-fx-min-height: 250px;" +
                "-fx-min-width:  350;" +
                "-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: white;");

        Button name = new Button(pokemon.getIdentStats().getName());
        nameStyle(name);

        Button level = new Button("Lv."+ pokemon.getIdentStats().getLevel());
        infoStyle(level);

        renderGender(pokemon.getIdentStats().getGender());

        Button health = new Button(pokemon.getDefensiveStats().getHPCurrent()+"/"+pokemon.getDefensiveStats().getHP());
        infoStyle(health);

        GridPane gp = new GridPane();
        gp.setStyle("-fx-max-width: 350px;" +
                "-fx-max-height: 250px;" +
                "-fx-min-height: 250px;" +
                "-fx-min-width:  350;");

        StackPane sp = new StackPane();
        return sp;
    }

    private GridPane renderEmptySlot(){
        GridPane bg = new GridPane();
        bg.setStyle("-fx-max-width: 350px;" +
                "-fx-max-height: 100px;" +
                "-fx-min-height: 100px;" +
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
        bg.setFitHeight(60);
        bg.setFitWidth(60);
        pane.getChildren().add(bg);
        return pane;
    }

    private Pane renderGender(String gender){
        Pane pane = new Pane();
        File file;
        if( gender.compareToIgnoreCase("male") == 0){
            file = new File("./pokemon/imgs/male.png");
            Image image =  new Image(file.toURI().toString());
            ImageView g = new ImageView(image);
            g.setFitHeight(25);
            g.setFitWidth(25);
            pane.getChildren().add(g);
        }else if( gender.compareToIgnoreCase("female") == 0){
            file = new File("./pokemon/imgs/female.png");
            Image image =  new Image(file.toURI().toString());
            ImageView g = new ImageView(image);
            g.setFitHeight(25);
            g.setFitWidth(25);
            pane.getChildren().add(g);
        }

        pane.setStyle("-fx-max-width: 25px;" +
                "-fx-max-height: 25px;");
        return pane;
    }

    private void nameStyle(Button name){
        name.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-background-color: pink;");
    }
    private void infoStyle(Button info){
        info.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-background-color: blue;");
    }

}
