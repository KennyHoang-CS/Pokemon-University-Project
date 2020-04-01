package gamarket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Player {
    private String name;
    private String email;
    private String password;
    private int badges;
    private double money;
    private int totalPokemon;
    private String joinDate;
    private String time1;
    private String time2;
    private String totalTime; //changing to string, deal with later
    private Grid grid;
    private PokemonCollection pokeTeam;
    private PokemonCollection pokeDex;

    /**
     * Player constructor initializes either a new player or returning player
     * @param newUser lets the constructor know whether it's a new user or not
     * @param un gives the constructor the player's username
     * @param pw gives the constructor the player's password
     **/
    public Player(Boolean newUser, String un, String pw) {
        if (newUser) {
            Date originalDate = new Date();
            //format
            this.joinDate = originalDate.toString();
            this.name = un;
            this.password = pw;
            this.badges = 0;
            this.money = 0;
            this.totalPokemon = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            this.joinDate = formatter.format(originalDate);
            this.grid = new Grid();
            formatter = new SimpleDateFormat("HH:mm:ss");
            this.time1 = formatter.format(originalDate);
        } else {
            this.name = un;
            this.password = pw;
            loadData(this.name);

        }
    }

    public void setName(String username) {
        this.name = username;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    public void setBadges(int amt){
        this.badges = amt;
    }

    public void setMoney(long amt){ this.money = amt; }

    public void setTotalPokemon(int amt){ this.totalPokemon = amt; }

    public String getName(){ return name; }

    public String getEmail(){ return email; }

    public String getPassword(){ return password; }

    public int getBadges(){ return badges; }

    public double getMoney(){ return money; }

    public int getTotalPokemon(){ return totalPokemon; }

    public PokemonCollection getPokeTeam(){ return pokeTeam; }

    public PokemonCollection getPokeDex(){ return pokeDex; }

    /**
     * loadData loads the returning player's saved data
     * @param fileName takes in the file name where the player's profile is saved
     *                 Proper player profile format:
     *                 playerName,email,password
     *                 amountOfBadges,money,totalPokemon
     *                 joinDate,totalTime,gridFileName
     *                 pokeTeamFile,pokedexFile,
     **/
    public void loadData(String fileName) {
        String filePath = "./pokemon/database files/User Profiles/" + fileName + "_profile.txt";
        File inFile = new File(filePath);

        Scanner scanner = null;
        try {
            scanner = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = 1;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String temp1;
            String temp2;
            String temp3;
            switch (line) {
                case 1:
                    int indexOfFirstComma = data.indexOf(",");
                    int indexOfLastComma = data.lastIndexOf(",");
                    temp1 = data.substring(0, indexOfFirstComma);
                    temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    temp3 = data.substring((indexOfLastComma + 1), data.length());
                    this.name = temp1;
                    this.email = temp2;
                    this.password = temp3;
                    break;
                case 2:
                    indexOfFirstComma = data.indexOf(",");
                    indexOfLastComma = data.lastIndexOf(",");
                    temp1 = data.substring(0, indexOfFirstComma);
                    temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    temp3 = data.substring((indexOfLastComma + 1), data.length());
                    this.badges = Integer.parseInt(temp1);
                    this.money = Double.parseDouble(temp2);
                    this.totalPokemon = Integer.parseInt(temp3);
                    break;
                case 3:
                    indexOfFirstComma = data.indexOf(",");
                    indexOfLastComma = data.lastIndexOf(",");
                    temp1 = data.substring(0, indexOfFirstComma);
                    temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    temp3 = data.substring((indexOfLastComma + 1), data.length());
                    this.joinDate = temp1;
                    this.totalTime = temp2;
                    this.grid.loadData(temp3);
                    break;
                case 4:
                    indexOfFirstComma = data.indexOf(",");
                    indexOfLastComma = data.lastIndexOf(",");
                    temp1 = data.substring(0, indexOfFirstComma);
                    temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    this.pokeTeam.loadData(temp1);
                    this.pokeDex.loadData(temp2);
                    break;
            }
            line++;
        }
    }

    /**
     * saveData saves the user's data in the proper format
     * Proper player profile format:
     * playerName,email,password
     * amountOfBadges,money,totalPokemon
     * joinDate,totalTime,gridFileName
     * pokeTeamFile,pokeDexFile
     **/
    public void saveData() {
        File file = new File("./pokemon/database files/User Profiles/" + this.name + "_profile.txt");

        try {
            if (file.exists() == false){
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }

           FileWriter writer = new FileWriter(file);
           SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
           Date currentTime = new Date();
           totalTime = calculateTime(timeFormat.format(currentTime));
           writer.write(this.name + "," + this.email + "," + this.password + "\n"
                   + this.badges + "," + this.money + "," + this.totalPokemon + "\n"
                   + this.joinDate + "," + this.totalTime + "," + this.name + "\n"
                   + this.name + "," + this.name + ",");



            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * calculateTime calculates the difference between the time passed in and the last saved time
     * @param time takes in a string that should hold the time of method call
     * @return returns a string of the calculated time
     **/
    private String calculateTime(String time) {
        System.out.println(time1);
        time2 = time;
        System.out.println(time2);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        Date date1;
        Date date2;
        try {
             date1 = timeFormat.parse(time1);
             date2 = timeFormat.parse(time2);
             long diff = date2.getTime() - date1.getTime();
             time1 = timeFormat.format(new Date(diff));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("The diff is "+ time1);

        return time1;
    }

    public String toString(){
        return "Username: " + this.name +
                "\nEmail: " + this.email +
                "\nPassword: " + this.password +
                "\nBages: " + this.badges +
                "\nMoney: " + this.money +
                "\nTotal Pokemon: " + this.totalPokemon +
                "\nJoin date: " + this.joinDate +
                "\nPlayed Time: " + this.totalTime +
                "\nPokemon Team: " + this.pokeTeam.toString() +
                "\nPokedex: " + this.pokeDex.getNumPokes();
    }
}