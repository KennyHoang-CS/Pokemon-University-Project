package gamarket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartMenuGUI extends Application {
    Stage window;
    private String username;
    private String password;
    public Boolean verified = null;
    public Boolean newUser = null;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Pokemon");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username:");
        grid.add(nameLabel, 0, 0);

        final TextField nameInput = new TextField();
        grid.add(nameInput,0,1);

        Label passLabel = new Label("Password:");
        grid.add(passLabel,1,0);

        final TextField passInput = new TextField();
        grid.add(passInput,1,1);

        Button registerBtn = new Button("Register");
        registerBtn.setOnAction(e -> {
            System.out.println("register!");
            String name = nameInput.getText();
            String pass = passInput.getText();
            setUsername(name);
            setPassword(pass);
            newUser = true;
        });
        grid.add(registerBtn, 0,2);

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            String name = nameInput.getText();
            String pass = passInput.getText();
            verifyUser(name, pass);
        });
        grid.add(loginBtn, 1,2);




        Scene scene = new Scene(grid, 300,100);
        window.setScene(scene);
        window.show();


    }

    public void verifyUser(String un, String pw){
        //verifyUser function checks wether the user input matches what is found in the database
        String filePath = "./pokemon/files/UserDataBase.txt";
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
            String email = data.substring(0,indexOfFirstComma );
            String name = data.substring( (indexOfFirstComma+1),indexOfLastComma);
            String pass = data.substring((indexOfLastComma+1), data.length());

            if( (name.compareToIgnoreCase(un) == 0 || email.compareToIgnoreCase(un) == 0) && pass.compareTo(pw) == 0 ){
                verified = true;
                System.out.println("user found & logged in successfully"); // stub code
                newUser = false;
                break;
            } else if( (name.compareToIgnoreCase(un) == 0 || email.compareToIgnoreCase(un) == 0) && pass.compareTo(pw) != 0){
                verified = false;
                System.out.println("incorrect username or password"); // stub code
                break;
            }
        }

        if(verified == null){
            System.out.println("user info. not found. try again or register."); // stub code
        }

    }

    public Boolean getVerified(){
        return verified;
    }

    public Boolean getNewUser(){
        return getNewUser();
    }


    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String un){
        username = un;
    }

    public void setPassword(String pw){
        password = pw;
    }
}
