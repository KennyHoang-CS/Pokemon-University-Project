package gamarket;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StoreGUI extends NPC {
    private List<String> items = new ArrayList<>();
    private Store store;
    private GridPane gp;
    private SceneController sceneController;

    StoreGUI(Store store, Stage window){
        this.store = store;
        this.name = "Merchant";
        this.phrases[0] = "Welcome to the Pokemart!\nWould you like to buy or sell?";
        this.phrases[1] = "Please select what you'd like to buy.";
        this.phrases[2] = "What would you like to sell?";
        this.sceneController = SceneController.getInstance(window);
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

        Button buyBtn = new Button("Buy");
        btnStyle(buyBtn);
        buyBtn.setOnMouseEntered(event -> { hoverStyle(buyBtn); });
        buyBtn.setOnMouseExited(event -> { btnStyle(buyBtn);} );

        Button sellBtn = new Button("Sell");
        btnStyle(sellBtn);
        sellBtn.setOnMouseEntered(event -> { hoverStyle(sellBtn); });
        sellBtn.setOnMouseExited(event -> { btnStyle(sellBtn);} );


        Button byeBtn = new Button("Bye!");
        btnStyle(byeBtn);
        byeBtn.setOnMouseEntered(event -> { hoverStyle(byeBtn); });
        byeBtn.setOnMouseExited(event -> { btnStyle(byeBtn);} );
        byeBtn.setOnAction(event -> {
            StackPane s = (StackPane)byeBtn.getScene().getRoot();
            s.getChildren().remove(gp);
        });

        gp.add(buyBtn,0,0);
        gp.add(sellBtn,0,1);
        gp.add(byeBtn,0,2);
        gp.add(dialogue,0,3);

        return gp;
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
