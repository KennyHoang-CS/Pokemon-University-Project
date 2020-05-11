package gamarket;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class BagGUI {
    private Bag bag;
    private SceneController sceneController;
    private GridPane layout;
    private Pane img;
    protected static BagGUI bagGUI;
    protected BagGUI(){ }
    private StackPane sp;
    private Team team;
    private Button description;

    public static synchronized BagGUI getInstance(){
        if(bagGUI == null){
            bagGUI = new BagGUI();
        }
        return bagGUI;
    }

    public void setBag(Bag bag){ this.bag = bag; }
    public void setTeam(Team team) { this.team = team; }
    public void setSceneController(Stage window){ this.sceneController = SceneController.getInstance(window); }

    /**
     *
     * @param sceneType
     * @return
     */
    public StackPane display(String... sceneType){
        GridPane gp = new GridPane();
        gp.setStyle("-fx-translate-x: 150px;" +
                "-fx-translate-y: -50px;" +
                "-fx-max-width: 400px;");

        if(sceneType.length > 0 && sceneType[0] == "store"){
            gp.add(renderItemSlot("Pokeball", bag.getQty("pokeball"), sceneType),0,0);
            gp.add(renderItemSlot("Potions", bag.getQty("potion"),sceneType),0,1);
        }else{
            gp.add(renderItemSlot("Pokeball",bag.getQty("pokeball")),0,0);
            gp.add(renderItemSlot("Potion",bag.getQty("potion")),0,1);
        }

        renderLayout(gp,"pokeball");

        sp = new StackPane();
        sp.getChildren().addAll(renderIMG("bag"),layout);
        return sp;
    }

    /**
     *
     * @param gp
     * @param item
     */
    private void renderLayout(GridPane gp, String item){
        layout = new GridPane();

        Button exit = new Button("Return");
        exit.setOnMouseClicked(e -> { sceneController.returnScene();});
        returnStyle(exit);
        exit.setOnMouseEntered(e -> { returnHover(exit); });
        exit.setOnMouseExited(e -> { returnStyle(exit);});

        layout.add(exit,0,0);
        layout.add(gp,1,0);
        renderDescription(item);
        layout.add(description,1,1);
        this.img = renderIMG(item);
        layout.add(this.img,0,1);
        layout.setStyle("-fx-vgap: 350px;" +
                "-fx-alignment: center;" +
                "-fx-min-width: 800px;" +
                "-fx-min-height: 800px;" +
                "-fx-max-width: 800px;" +
                "-fx-max-height:800px; ");

    }

    /**
     *
     * @param item
     * @param qty
     * @return
     */
    private GridPane renderItemSlot(String item, int qty, String... sceneType){
        GridPane gp = new GridPane();
        gp.setStyle("-fx-translate-y: 20px;");
        String style = "-fx-font-family: 'Courier New';" +
                "-fx-background-color: none;" +
                "-fx-font-size: 40px;";

        Button name = new Button(item);
        name.setStyle(style);

        Pane arrow = renderIMG("arrow");
        arrow.setStyle("-fx-opacity: 0.0;");

        Button amt = new Button("x"+qty);
        amt.setStyle(style +"-fx-text-alignment: right;");
        item.toLowerCase();
        gp.add(arrow,0,0);
        gp.add(name,1,0);
        gp.add(amt,2,0);
        gp.setOnMouseEntered(e -> {
            arrow.setStyle("-fx-opacity: 1.0;");
            name.setStyle(style + "-fx-font-weight: bold;");
            amt.setStyle(style + "-fx-font-weight: bold;");
            layout.getChildren().removeAll(this.img, this.description);
            this.img = renderIMG(item);
            renderDescription(item);
            layout.add(this.img,0,1);
            layout.add(description,1,1);
        });

        gp.setOnMouseExited(e -> {
            arrow.setStyle("-fx-opacity: 0.0;");
            name.setStyle(style);
            amt.setStyle(style);
        });

        gp.setOnMouseClicked(event -> {
            if(sceneType.length > 0 && sceneType[0] == "store"){
                //TODO store options
            }else{
                if(item.compareToIgnoreCase("potion") == 0) {
                    if(this.bag.getQty("potion") > 0){
                        sp.getChildren().addAll(renderOption());
                    }
                }
            }
        });

        return gp;
    }

    /**
     *
     * @param imgName
     * @return
     */
    private Pane renderIMG(String imgName){
        imgName = imgName.toLowerCase();
        Pane pane = new Pane();
        File file = new File("./pokemon/imgs/"+ imgName +".png");
        Image image =  new Image(file.toURI().toString());
        ImageView bg = new ImageView(image);
        switch(imgName){
            case "bag":
                bg.setFitHeight(800);
                bg.setFitWidth(800);
                break;
            case "potion":
                bg.setFitHeight(80);
                bg.setFitWidth(80);
                pane.setStyle("-fx-translate-y: 40px;" +
                        "-fx-translate-x: 5px;");
                break;
            case "pokeball":
                bg.setFitHeight(80);
                bg.setFitWidth(80);
                pane.setStyle("-fx-translate-y: 40px;" +
                        "-fx-translate-x: 5px;");
                break;
            case "arrow":
                bg.setFitHeight(50);
                bg.setFitWidth(50);
                bg.setStyle("-fx-translate-x: 25px;" +
                        "-fx-translate-y: 15px;");
                break;
        }
        pane.getChildren().add(bg);
        return pane;
    }

    /**
     *
     * @param item
     */
    private void renderDescription(String item){
        item = item.toLowerCase();
        String style = "-fx-background-color: none;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-size: 40px;" +
                "-fx-text-fill: white;" +
                "-fx-translate-x: -80px;" +
                "-fx-max-height: 125px;" +
                "-fx-min-height: 125px;";
        switch(item){
            case "pokeball":
                description = new Button(new PokeBall().getDescription());
                description.setStyle(style + "-fx-translate-y: -15px;");
                break;
            case "potion":
                description = new Button(new Potion().getDescription());
                description.setStyle(style + "-fx-translate-y: -30px;");
                break;
            default:
                description = new Button("error");
                break;
        }

        description.setStyle(style);
    }

    /**
     *
     * @return
     */
    private GridPane renderOption(){
        //when trying to use a potion on a pokemon
        //can call display method but include an (optional) parameter
        //telling it what type of scene it is to set up the action events appropriately
        GridPane options = new GridPane();

        Button useBtn = new Button("Use");
        optionStyle(useBtn);
        useBtn.setOnMouseEntered(e -> {optionHover(useBtn);});
        useBtn.setOnMouseExited(event -> {optionStyle(useBtn);});
        useBtn.setOnMouseClicked(event -> {
            sceneController.poketeamScene(this.team, this.bag, "bag");
        });

        Button cancelBtn = new Button("Cancel");
        optionStyle(cancelBtn);
        cancelBtn.setOnMouseEntered(e -> {optionHover(cancelBtn);});
        cancelBtn.setOnMouseExited(event -> {optionStyle(cancelBtn);});
        cancelBtn.setOnMouseClicked(event -> {sp.getChildren().remove(options);});

        options.add(useBtn,0,0);
        options.add(cancelBtn,0,1);

        options.setStyle("-fx-alignment: center;" +
                "-fx-translate-x: 290px;" +
                "-fx-translate-y: 85px;" +
                "-fx-vgap: 5px;");

        return options;
    }

    /**
     *
     * @param button
     */
    private void returnStyle(Button button){
        button.setStyle("-fx-background-color: darkred;" +
                "-fx-opacity: 0.5;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 25px;" +
                "-fx-background-radius: 25px;" +
                "-fx-min-width: 175px;" +
                "-fx-min-height: 55px;" +
                "-fx-translate-y: -100px;" +
                "-fx-translate-x: 75px;");

    }

    /**
     *
     * @param button
     */
    private void returnHover(Button button){
        button.setStyle( "-fx-background-color: darkred;" +
                "-fx-opacity: 0.9;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 25px;" +
                "-fx-background-radius: 25px;" +
                "-fx-min-width: 175px;" +
                "-fx-min-height: 55px;" +
                "-fx-border-width: 2px;" +
                "-fx-translate-y: -100px;" +
                "-fx-translate-x: 75px;");
    }

    /**
     *
     * @param button
     */
    public void optionStyle(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 50px;" +
                "-fx-min-width: 175px;" +
                "-fx-max-width: 175px;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-radius: 15px;" +
                "-fx-translate-x: 5px;" +
                "-fx-opacity: 0.9;"+
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: lightgray;");
    }

    /**
     *
     * @param button
     */
    public void optionHover(Button button){
        button.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-size: 30px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 50px;" +
                "-fx-min-width: 175px;" +
                "-fx-max-width: 175px;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-radius: 15px;" +
                "-fx-translate-x: 5px;" +
                "-fx-opacity: 1.0;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-color: black;");
    }

    public void menuOptions(Button button){

    }

    public void storeOptions(Button button){

    }
}
