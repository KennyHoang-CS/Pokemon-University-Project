package gamarket;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class Player {
    private String name;
    private String email;
    private String password;
    private int badges;
    private float money;
    private int totalPokemon;
    private Date originalDate;
    private String joinDate;
    private Time previousTime;
    private Time currentTime;
    private long totalTime;


    public Player(Boolean newUser, String un, String pw) {
        if (newUser) {
            originalDate = new Date();
            name = un;
            password = pw;

        } else {
            name = un;
            password = pw;
        }
    }

    public void setName(String username) {
        name = username;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setPassword(String pw) {
        password = pw;
    }

    public void loadData(String fileName) {
        //this function will load data from username.txt file
        //user files will be named exactly as username is spelled
        //verifyUser function checks wether the user input matches what is found in the database
        String filePath = "./pokemon/database files/User Profiles/" + fileName + ".txt";
        File inFile = new File(filePath);

        Scanner scanner = null;
        try {
            scanner = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            int line = 1;
            switch (line) {
                case 1:
                    int indexOfFirstComma = data.indexOf(",");
                    int indexOfLastComma = data.lastIndexOf(",");
                    String temp1 = data.substring(0, indexOfFirstComma);
                    String temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    String temp3 = data.substring((indexOfLastComma + 1), data.length());
                    name = temp1;
                    email = temp2;
                    password = temp3;
                    break;
                case 2:
                    indexOfFirstComma = data.indexOf(",");
                    indexOfLastComma = data.lastIndexOf(",");
                    temp1 = data.substring(0, indexOfFirstComma);
                    temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    temp3 = data.substring((indexOfLastComma + 1), data.length());
                    badges = Integer.parseInt(temp1);
                    money = Float.parseFloat(temp2);
                    totalPokemon = Integer.parseInt(temp3);
                    break;
                case 3:
                    indexOfFirstComma = data.indexOf(",");
                    indexOfLastComma = data.lastIndexOf(",");
                    temp1 = data.substring(0, indexOfFirstComma);
                    temp2 = data.substring((indexOfFirstComma + 1), indexOfLastComma);
                    temp3 = data.substring((indexOfLastComma + 1), data.length());
                    joinDate = temp1;

                    break;
            }
        }


    }


    public void saveData(Player player) {

    }

    private String calculateTime(){
        String time1="0:01:30";
        String time2="0:01:35";

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date1 = timeFormat.parse(time1);
        Date date2 = timeFormat.parse(time2);

        long sum = date1.getTime() + date2.getTime();

        String date3 = timeFormat.format(new Date(sum));
        System.out.println("The sum is "+date3);
        //Ouput : The sum is 00:03:05
        return date3;
    }
}
