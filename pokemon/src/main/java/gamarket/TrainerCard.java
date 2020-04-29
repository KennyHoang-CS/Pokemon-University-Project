package gamarket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class TrainerCard {
    private Player player;
    private Button title;
    private Button name;
    private Button money;
    private Button joinDate;
    private Button timePlayed;
    private Button badge;
    private GridPane badges;

    protected static TrainerCard trainerCard;

    protected TrainerCard(){
    }

    public static synchronized TrainerCard getInstance(){
        if(trainerCard == null){
            trainerCard = new TrainerCard();
        }
        return trainerCard;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public StackPane display() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(50,0,50,0));
        gp.setGridLinesVisible(true);

        /*
        ColumnConstraints col = new ColumnConstraints();
        col.setMaxWidth(150.0);
        gp.getColumnConstraints().addAll(col);

        RowConstraints row = new RowConstraints();
        row.setMaxHeight(150);
        gp.getRowConstraints().addAll(row);
        */

        gp.setStyle("-fx-max-height: 560px;" +
                "-fx-max-width: 600px;" +
                "-fx-hgap: 150px;" +
                "-fx-cell-size: 300px;");

        Button trainerCard = new Button("Trainer Card");
        trainerCard.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 20px;" +
                "-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-style: oblique;" +
                "-fx-max-width: 200px;" +
                "-fx-border-radius: 5;" +
                "-fx-alignment: left;");

        Button exit = new Button("Exit");
        exit.setOnAction(ev -> {

        });
        exit.setAlignment(Pos.BASELINE_RIGHT);

        gp.add(trainerCard,0,0);
        gp.add(renderTrainer(),0,1);
        gp.add(renderInfo(),1,1);
        gp.add(exit, 1,2);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(renderBG(),gp);
        return sp;
    }

    private GridPane loadBadges(int num){
        badges = new GridPane();

        for(int i = 0; i < 8; i++ ){
            switch(i){
                case 0:
                    badges.add(renderBadge("badge1"),0,0);
                    break;
                case 1:
                    badges.add(renderBadge("badge2"),1,1);
                    break;
                case 2:
                    badges.add(renderBadge("badge3"),2,0);
                    break;
                case 3:
                    badges.add(renderBadge("badge4"),3,1);
                    break;
                case 4:
                    badges.add(renderBadge("badge5"),4,0);
                    break;
                case 5:
                    badges.add(renderBadge("badge6"),5,1);
                    break;
                case 6:
                    badges.add(renderBadge("badge7"),6,0);
                    break;
                case 7:
                    badges.add(renderBadge("badge8"),7,1);
                    break;
            }
        }
        return badges;
    }

    private ImageView renderBG(){
        File file = new File("./pokemon/imgs/tCard.jpg");
        Image image =  new Image(file.toURI().toString());
        ImageView bg = new ImageView(image);
        bg.setFitHeight(600);
        bg.setFitWidth(800);
        return bg;
    }

    private ImageView renderBadge(String name){
        File file = new File("./pokemon/imgs/"+ name + ".png");
        Image image =  new Image(file.toURI().toString());
        ImageView badge = new ImageView(image);
        badge.setFitWidth(50);
        badge.setFitHeight(50);
        return  badge;
    }

    private StackPane renderInfo(){
        StackPane sp = new StackPane();
        GridPane bg = new GridPane();
        GridPane info = new GridPane();
        bg.setStyle("-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-max-width: 550px;" +
                "-fx-max-height: 700px;");
        name = new Button("Name: " + player.getName());
        setStyle(name);
        money = new Button("Money: "+ player.getMoney());
        setStyle(money);
        joinDate = new Button("Join Date: " + player.getJoinDate());
        setStyle(joinDate);
        timePlayed = new Button("Total Time Played: " + player.getTimePlayed());
        setStyle(timePlayed);
        badge = new Button("Badges Earned: " + player.getBadges());
        setStyle(badge);

        info.add(name,0,0);
        info.add(money,0,1);
        info.add(joinDate,0,2);
        info.add(timePlayed,0,3);
        info.add(badge,0,4);
        info.add(loadBadges(player.getBadges()),0,5);

        sp.getChildren().addAll(bg, info);

        return sp;
    }

    private StackPane renderTrainer(){
        Pane black = new Pane();
        black.setStyle("-fx-background-color: white;" +
                "-fx-opacity: .8;" +
                "-fx-border-radius: 25px;");

        GridPane gp = new GridPane();
        File file = new File("./pokemon/imgs/trainer.png");
        Image image =  new Image(file.toURI().toString());
        ImageView trainer = new ImageView(image);
        trainer.setFitHeight(350);
        trainer.setFitWidth(198);

        gp.add(trainer, 0,0);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(black,gp);
        return sp;
    }

    private void setStyle(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 20px;" +
                "-fx-background-color: none;" +
                "-fx-text-fill: white;");
    }
}
