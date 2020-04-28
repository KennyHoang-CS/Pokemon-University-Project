package gamarket;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StartMenuGUI{
    private String username;
    private String email;
    private String password;
    private Boolean verified = null;
    private Boolean newUser = null;
    private Stage window;
    private StackPane stackPane;
    private Scene scene;
    private ImageView pokemonEastBay;

    /**
     * display sets up the window and background of the GUI
     */
    public void display(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        File file = new File("./pokemon/imgs/pikachu.gif");
        Image image =  new Image(file.toURI().toString());
        ImageView bg = new ImageView(image);
        stackPane = new StackPane();
        stackPane.getChildren().addAll(bg, loginPane());
        scene = new Scene(stackPane);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * loginPane sets up the GUI for the log-in form as well as its functionality
     * @return returns the panel to be inserted in the scenee
     */
    public StackPane loginPane(){
        StackPane sp = new StackPane();
        GridPane white = new GridPane();
        white.setStyle("-fx-background-color: white;" +
                "-fx-max-width: 160px;" +
                "-fx-max-height: 300px;" +
                "-fx-translate-x: -160px;" +
                "-fx-background-radius: 15px;" +
                "-fx-translate-y: -20px;" +
                "-fx-opacity: .8;");

        GridPane grid = new GridPane();
        grid.setStyle("-fx-max-width: 140px;" +
                "-fx-max-height: 250px;" +
                "-fx-translate-x: -160px;" +
                "-fx-vgap: 8px;" +
                "-fx-background-radius: 15px;" +
                "-fx-translate-y: -20px;");
        grid.setPadding(new Insets(10,10,10,10));

        File file = new File("./pokemon/imgs/PokemonEastBay.png");
        Image image =  new Image(file.toURI().toString());
        pokemonEastBay = new ImageView(image);
        pokemonEastBay.setFitWidth(130);
        pokemonEastBay.setFitHeight(80);

        pokemonEastBay.setStyle("-fx-opacity: 1;");
        grid.add(pokemonEastBay,0,0);


        Label nameLabel = new Label("Username or Email:");
        grid.add(nameLabel, 0, 1);

        final TextField nameInput = new TextField();
        grid.add(nameInput,0,2);

        Label passLabel = new Label("Password:");
        grid.add(passLabel,0,3);

        final TextField passInput = new TextField();
        grid.add(passInput,0,4);

        Button registerBtn = new Button("Register");
        registerBtn.setOnAction(e -> {
            stackPane.getChildren().remove(sp);
            registerPane();
        });
        grid.add(registerBtn, 0,5);

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            String name = nameInput.getText();
            String pass = passInput.getText();
            verifyUser(name, pass);
        });

        grid.add(loginBtn, 0,6);
        sp.getChildren().addAll(white,grid);

        sp.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                loginBtn.fire();
                return;
            }
        });

        return sp;
    }

    /**
     * verifyUsers checks the UserDataBase.txt and verifies whether a returning player's username and password is correct.
     * If the information is correct, the window closes.
     * @param un the player's username
     * @param pw the playeer's password
     */
    private void verifyUser(String un, String pw){
        String filePath = "./pokemon/databaseFiles/UserDataBase.txt";
        File inFile = new File(filePath);

        Scanner userDB = null;
        try {
            userDB = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(userDB.hasNextLine()){
            String data = userDB.nextLine();
            int indexOfFirstComma = data.indexOf(",");
            int indexOfLastComma = data.lastIndexOf(",");
            String email = data.substring(0,indexOfFirstComma);
            String name = data.substring( (indexOfFirstComma+1),indexOfLastComma);
            String pass = data.substring((indexOfLastComma+1), data.length());

            if( (name.compareToIgnoreCase(un) == 0 || email.compareToIgnoreCase(un) == 0) && pass.compareTo(pw) == 0 ){
                username = name;
                password = pass;
                verified = true;
                newUser = false;
                window.close();
                break;
            } else if( (name.compareToIgnoreCase(un) == 0 || email.compareToIgnoreCase(un) == 0) && pass.compareTo(pw) != 0){
                verified = false;
                alertBox(0);
                break;
            }else if(verified == null){
                alertBox(0);
            }
        }

    }

    /**
     * alertBox creates an alert box for the user to know their log-in information was incorrct
     * @param alert decides what type of alert to display. '0' if failed login, '1' for existing user
     */
    private void alertBox(int alert) {
        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setPrefSize(300, 100);
        grid.setStyle("-fx-text-alignment: center;" +
                "-fx-vgap: 10px;" +
                "-fx-alignment: center;");

        Label label;
        if (alert == 0) {
            alertWindow.setTitle("Failed Login");
            label = new Label("Wrong username or password.\n Please register or try again!");
        } else if (alert == 1) {
            alertWindow.setTitle("User exists");
            label = new Label("The username or email is already being used.\nPlease try again! ");
        } else if (alert == 2){
            alertWindow.setTitle("Invalid Email");
            label = new Label("The email entered is invalid.\nPlease try again! ");
        }else {
            System.out.println("error");
            return;
        }
        grid.add(label,0,0);

        Button okBtn = new Button("OK");
        okBtn.setOnAction(e -> {
            alertWindow.close();
        });

        grid.add(okBtn,0,1);
        grid.setHalignment(okBtn, HPos.CENTER);
        alertWindow.setScene(new Scene(grid));
        alertWindow.showAndWait();
    }

    /**
     * registerPane sets up the GUI for the register form as well as its functionality
     */
    public void registerPane() {
        GridPane black = new GridPane();
        black.setStyle("-fx-background-color: black;" +
                "-fx-opacity: .3px");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: white;" +
                "-fx-max-width: 305px;" +
                "-fx-max-height: 330px;" +
                "-fx-background-radius: 15px;" +
                "-fx-translate-y: 0px;" +
                "-fx-vgap: 5px;" +
                "-fx-font-alignment: center;");

        grid.add(pokemonEastBay, 0,0);
        Label nameLabel = new Label("Please enter desired username:");
        grid.add(nameLabel, 0, 1);

        final TextField nameInput = new TextField();
        grid.add(nameInput, 0, 2);

        Label emailLabel = new Label("Please enter your email:");
        grid.add(emailLabel, 0, 3);

        final TextField emailInput = new TextField();
        grid.add(emailInput, 0, 4);

        Label passLabel = new Label("Please enter your password:");
        grid.add(passLabel, 0, 5);

        final TextField passInput = new TextField();
        grid.add(passInput, 0, 6);

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            stackPane.getChildren().removeAll(black, grid);
            stackPane.getChildren().addAll(loginPane());
        });

        Button registerBtn = new Button("Register");
        registerBtn.setOnAction(e -> {
            String name = nameInput.getText();
            String pass = passInput.getText();
            String email = emailInput.getText();
            if(email.contains("@") && email.contains(".com") && (email.contains("gmail") || email.contains("yahoo")) ){
                if(!checkDatabase(name, email)){
                    this.username = name;
                    this.email = email;
                    this.password = pass;
                    this.newUser = true;
                    addToDataBase(email, name, pass);
                    window.close();
                } else{
                    alertBox(1);
                }
            }else {
                alertBox(2);
            }

        });

        grid.setHalignment(pokemonEastBay, HPos.CENTER);
        grid.setHalignment(registerBtn, HPos.CENTER);
        grid.setHalignment(backBtn, HPos.CENTER);
        grid.add(backBtn, 0,8);
        grid.add(registerBtn, 0, 7);
        stackPane.getChildren().addAll(black, grid);
        window.setScene(new Scene(stackPane));
    }

    /**
     * checkDatabase checks whether credentials the player is asking for exist in the UserDataBase.txt
     * @return if credentials do not exist it returns false, if they do it returns true
     */
    private boolean checkDatabase(String un, String email){
        File inFile = new File("./pokemon/databaseFiles/UserDataBase.txt");
        Scanner userDB = null;
        try {
            userDB = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(userDB.hasNextLine()){
            String data = userDB.nextLine();
            int indexOfFirstComma = data.indexOf(",");
            int indexOfLastComma = data.lastIndexOf(",");
            String emailAddress = data.substring(0,indexOfFirstComma);
            String name = data.substring( (indexOfFirstComma+1),indexOfLastComma);
            if(name.compareToIgnoreCase(un) == 0 || emailAddress.compareToIgnoreCase(email) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * After meeting valid registration requirements
     * addToDataBase adds user's info into UserDataBase.txt for future reference
     * @param email user's valid email
     * @param un user's unique username
     * @param pw user's password
     */
    private void addToDataBase(String email, String un, String pw){
        try {
            File inFile = new File("./pokemon/databaseFiles/UserDataBase.txt");
            Scanner userDB;
            String data = "";
            userDB = new Scanner(inFile);
            while(userDB.hasNextLine()) {
                data = data + userDB.nextLine() + "\n";
            }
            FileWriter writer;
            writer = new FileWriter("./pokemon/databaseFiles/UserDataBase.txt");
            writer.write( data + email + "," + un + "," + pw);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Boolean getNewUser(){ return newUser; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
}
