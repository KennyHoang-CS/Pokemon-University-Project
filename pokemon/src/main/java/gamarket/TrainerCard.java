package gamarket;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
        gp.setStyle("-fx-max-height: 400px;" +
                "-fx-max-width: 300px;" +
                "-fx-vgap: 50px;");
        gp.add(renderTrainer(),0,0);
        gp.add(renderInfo(),1,0);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(renderBG(),gp);
        return sp;
    }

    private GridPane loadBadges(int num){
        badges = new GridPane();

        for(int i = 0; i < num; i++ ){
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
        badge.setFitWidth(80);
        badge.setFitHeight(80);
        return  badge;
    }

    private StackPane renderInfo(){
        StackPane sp = new StackPane();
        GridPane bg = new GridPane();
        GridPane info = new GridPane();
        bg.setStyle("-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-max-width: 500px;" +
                "-fx-max-height: 500px;");
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
        Button trainerCard = new Button("Trainer Card");
        trainerCard.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: none;" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-font-style: oblique;");
        Pane black = new Pane();
        black.setStyle("-fx-background-color: pink;" +
                "-fx-opacity: .5;" +
                "-fx-max-height: 300px;" +
                "-fx-max-width: 200px;");

        GridPane gp = new GridPane();
        File file = new File("./pokemon/imgs/trainer.png");
        Image image =  new Image(file.toURI().toString());
        ImageView trainer = new ImageView(image);
        trainer.setFitHeight(300);
        trainer.setFitWidth(158);

        gp.add(trainerCard,0,0);
        gp.add(trainer, 0,1);

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
