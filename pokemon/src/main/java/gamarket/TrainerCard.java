package gamarket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class TrainerCard {
    private Player player;
    private Button name;
    private Button money;
    private Button joinDate;
    private Button timePlayed;
    private Button badge;
    private GridPane badges;
    private SceneController sceneController;

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
    public void setSceneController(Stage window){ sceneController = new SceneController(window); }

    public StackPane display() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(50,0,50,0));


        gp.setStyle("-fx-min-height: 560px;" +
                "-fx-max-height: 560;" +
                "-fx-min-width: 600px;" +
                "-fx-max-width: 600px;" +
                "-fx-translate-x: -85px;"+
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        Button trainerCard = new Button("Trainer Card");
        trainerCard.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 20px;" +
                "-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-style: oblique;" +
                "-fx-border-color: white;" +
                "-fx-alignment: left;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        Button exit = new Button("Exit");
        exit.setOnAction(ev -> {
            sceneController.returnScene();
            exit.setStyle(hover());
        });
        exit.setStyle(buttonStyle());
        exit.setOnMouseEntered(event -> {exit.setStyle(hover());});
        exit.setOnMouseExited(event -> {exit.setStyle(buttonStyle());});

        gp.add(trainerCard,0,0);
        gp.add(renderTrainer(),0,1);
        gp.add(renderInfo(),1,1);
        gp.add(exit, 1,2);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(renderBG(),gp);
        return sp;
    }

    private StackPane loadBadges(int num){
        StackPane sp = new StackPane();
        GridPane bg = new GridPane();
        bg.setStyle(
                "-fx-translate-x: 15;" +
                        "-fx-background-color: lightgrey;" +
                        "-fx-opacity: .5;" +
                        "-fx-min-height: 150px;" +
                        "-fx-vgap: 20px;" +
                        "-fx-alignment: center;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;");
        badges = new GridPane();
        badges.setStyle(
                "-fx-translate-x: 15;" +
                "-fx-min-height: 150px;" +
                        "-fx-vgap: 20px;" +
                        "-fx-alignment: center;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-opacity: 0.8;" +
                        "-fx-border-radius: 10px;");
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
        sp.getChildren().addAll(bg, badges);
        return sp;
    }

    private Pane renderBG(){
        Pane pane = new Pane();
        File file = new File("./pokemon/imgs/tCard.jpg");
        Image image =  new Image(file.toURI().toString());
        ImageView bg = new ImageView(image);
        bg.setFitHeight(600);
        bg.setFitWidth(800);
        pane.setStyle("-fx-border-color: black;" +
                "-fx-translate-y: 2px;" +
                "-fx-opacity: 1.0;");
        pane.getChildren().add(bg);
        return pane;
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
                "-fx-min-width: 520px;" +
                "-fx-min-height: 400px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");
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
        black.setStyle("-fx-background-color: black;" +
                "-fx-opacity: .5;" +
                "-fx-min-height: 100px;" +
                "-fx-min-width: 190px;" +
                "-fx-border-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-radius: 10px");

        File file = new File("./pokemon/imgs/trainer.png");
        Image image =  new Image(file.toURI().toString());
        ImageView trainer = new ImageView(image);
        trainer.setFitHeight(350);
        trainer.setFitWidth(198);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(black,trainer);
        sp.setStyle("-fx-min-width: 225px;");
        return sp;
    }

    private void setStyle(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 24px;" +
                "-fx-background-color: none;" +
                "-fx-text-fill: white;");
    }

    private String buttonStyle(){
        String style = "-fx-background-color: black;" +
                "-fx-text-alignment: center;" +
                "-fx-opacity: 0.5;" +
                "-fx-text-fill: white;" +
                "-fx-translate-x: 455px;" +
                "-fx-font-size: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;";

        return style;
    }

    private String hover(){
        String style = "-fx-background-color: black;" +
                "-fx-opacity: 0.9;" +
                "-fx-text-alignment: center;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-translate-x: 455px;" +
                "-fx-font-size: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;";

        return style;
    }
}
