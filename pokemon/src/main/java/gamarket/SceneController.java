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
    protected static SceneController sceneController;

    protected SceneController(){

    }

    public static synchronized SceneController getInstance(Stage window){
        if(sceneController == null){
            sceneController = new SceneController();
            sceneController.setWindow(window);
            sceneController.setMainScene(window);
        }
        return sceneController;
    }

    public void setMainScene(Stage window) {
        this.mainScene = window.getScene();
    }

    public void setWindow(Stage window) {
        this.window = window;
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
