package gamarket;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class SceneController {
    public void trainerCardScene(ActionEvent event, Player player){
        TrainerCard card = TrainerCard.getInstance();
        card.setPlayer(player);
        Scene scene = new Scene(card.display());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
}
