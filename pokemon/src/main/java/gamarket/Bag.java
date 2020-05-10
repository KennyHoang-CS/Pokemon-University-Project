package gamarket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bag {
    private List<PokeBall> pokeballs;
    private List<Potion> potions;
    //private PokeBall pokeBalls[];
    //private Potion potions[];

    protected static Bag bag;
    protected Bag(){ }
    public static synchronized Bag getInstance(){
        if(bag == null){
            bag = new Bag();
        }
        return bag;
    }


    public int getQty(String itemType){
        switch (itemType){
            case "pokeball":
                return pokeballs.size();
            case "potion":
                return potions.size();
        }
        return 0;
    }

    public void loadData(String fileName, boolean... test){
        String filePath;
        if(test.length > 0 && test[0]==true){
            filePath = "./databaseFiles/bagFiles/" + fileName + "_bag.txt";
        }else{
            filePath = "./pokemon/databaseFiles/bagFiles/" + fileName + "_bag.txt";
        }
        File inFile = new File(filePath);
        if(inFile.exists()){
            try {
                Scanner scanner = new Scanner(inFile);
                String data = scanner.nextLine();
                String temp[] = data.split(":");
                String item1[] = temp[0].split(",");
                String item2[] = temp[1].split(",");
                pokeballs = new ArrayList<PokeBall>();
                for(int i = 0; i < Integer.parseInt(item1[1]); i++){
                    pokeballs.add(new PokeBall());
                }
                potions = new ArrayList<Potion>();
                for(int i = 0; i < Integer.parseInt(item2[1]); i++){
                    potions.add(new Potion());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else{
            try { inFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
            pokeballs = new ArrayList<PokeBall>();
            potions = new ArrayList<Potion>();
        }

    }

    public void saveData(String fileName, boolean... test){
        File file;
        if(test.length>0 && test[0]==true){
            file = new File("./databaseFiles/bagFiles/" + fileName + "_bag.txt");
        }else{
            file = new File("./pokemon/databaseFiles/bagFiles/" + fileName + "_bag.txt");
        }

        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Pokeballs," + pokeballs.size() + ":Potions," + potions.size());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String item, int qty){
        if(item.compareToIgnoreCase("pokeball") == 0){
            for(int i = 0; i < qty; i++){
                pokeballs.add(new PokeBall());
            }
        }else if(item.compareToIgnoreCase("potion") == 0){
            for(int i = 0; i < qty; i++){
                potions.add(new Potion());
            }
        }
    }

    public void removeItem(String item, int qty){
        if(item.compareToIgnoreCase("pokeball") == 0){
            if(qty == potions.size()){
                pokeballs.clear();
            }else {
                for (int i = 0; i < qty; i++) {
                    pokeballs.remove(i);
                }
            }
        }else if(item.compareToIgnoreCase("potion") == 0){
            if(qty == potions.size()){
                potions.clear();
            }else {
                for (int i = 0; i < qty; i++) {
                    potions.remove(i);
                }
            }
        }
    }

    public void usePotion(Pokemon pokemon){
        new Potion().use(pokemon);
        potions.remove(0);
    }

    public String toString(){
        return "Pokeballs: "+ pokeballs.size() +"\nPotions: " +potions.size();
    }
}
