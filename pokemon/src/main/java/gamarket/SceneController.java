package gamarket;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class SceneController {
    private Stage window;
    private Scene mainScene;

    SceneController(Stage window){
        this.window = window;
        mainScene = window.getScene();
    }

    public void trainerCardScene(Player player){
        TrainerCard card = TrainerCard.getInstance();
        card.setSceneController(window);
        card.setPlayer(player);
        Scene scene = new Scene(card.display());

        this.window.setScene(scene);
        this.window.show();
    }

    public void returnScene(){
        this.window.setScene(mainScene);
        this.window.show();
    }

    public void encounterScene(){
        EncounterGUI encounterGUI = EncounterGUI.getInstance();
        encounterGUI.setSceneController(this.window);
        Scene scene = new Scene(encounterGUI.display());

        this.window.setScene(scene);
        this.window.show();
    }

    public void poketeamScene(Player player){
        PoketeamGUI poketeamGUI = PoketeamGUI.getInstance();
        poketeamGUI.setSceneController(this.window);
        poketeamGUI.setTeam(player.getPokeTeam());
        Scene scene = new Scene(poketeamGUI.display());

        this.window.setScene(scene);
        this.window.show();
    }
}
