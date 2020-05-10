package gamarket;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Nurse extends NPC {
    private Team team;
    private GridPane gp;

    Nurse(Team team){
        this.team = team;
        this.name = "Nurse";
        this.phrases[0] = "Welcome to the Pokecenter!\nWould you like to heal your Pokemon back\nto full health??";
        this.phrases[1] = "All healed! Hope to see you\nagain soon.";
    }

    public GridPane display(){
        gp = new GridPane();
        gp.setAlignment(Pos.BOTTOM_LEFT);
        gp.setStyle("-fx-min-width: 800px;" +
                "-fx-max-width: 800px;" +
                "-fx-min-height: 800px;" +
                "-fx-max-height: 800px;" +
                "-fx-vgap: 10px;");

        Button dialogue = new Button(getDialogueAt(0));
        dialogue.setStyle( "-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-text-alignment: left;" +
                "-fx-background-color: white;" +
                "-fx-min-width: 800px;" +
                "-fx-max-width: 800px;" +
                "-fx-min-height: 250px;" +
                "-fx-max-height: 250px;" +
                "-fx-border-radius: 25px;" +
                "-fx-background-radius: 25px;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2px;");

        Button noBtn = new Button("No");
        btnStyle(noBtn);
        noBtn.setOnMouseEntered(event -> { hoverStyle(noBtn); });
        noBtn.setOnMouseExited(event -> { btnStyle(noBtn);} );
        noBtn.setOnAction(event -> {
            StackPane s = (StackPane)noBtn.getScene().getRoot();
            s.getChildren().remove(gp);
        });


        Button yesBtn = new Button("Yes");
        btnStyle(yesBtn);
        yesBtn.setOnMouseEntered(event -> { hoverStyle(yesBtn); });
        yesBtn.setOnMouseExited(event -> { btnStyle(yesBtn);} );
        yesBtn.setOnAction(event -> { heal();
            dialogue.setText(getDialogueAt(1));
            gp.getChildren().remove(yesBtn);
            noBtn.setText("Ok");
        });

        gp.add(yesBtn,0,0);
        gp.add(noBtn,0,1);
        gp.add(dialogue,0,2);

        return gp;
    }

    public void heal(){
        for(int i = 0; i < this.team.getNumOfPokesInTeam(); i++){
            this.team.getPokemonAtIndex(i).getDefensiveStats().setHPCurrent(this.team.getPokemonAtIndex(i).getDefensiveStats().getHP());
        }
    }


    public void btnStyle(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 50px;" +
                "-fx-min-width: 150px;" +
                "-fx-max-width: 150px;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-radius: 15px;" +
                "-fx-translate-x: 5px;" +
                "-fx-opacity: 0.9;"+
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: lightgray;");
    }

    public void hoverStyle(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 50px;" +
                "-fx-min-width: 150px;" +
                "-fx-max-width: 150px;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-radius: 15px;" +
                "-fx-translate-x: 5px;" +
                "-fx-opacity: 1.0;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: black;");
    }

}

