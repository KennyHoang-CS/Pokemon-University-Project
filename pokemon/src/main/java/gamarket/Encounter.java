package gamarket;

import java.util.Scanner;

public class Encounter {
    private Pokemon wildPokemon;
    private Pokemon activePlayerPokemon;
    private boolean battling;
    private boolean attacker;
    private Player thePlayer; 
    private PokemonCollection collection;
    //private Team playerTeam; 
    private Scanner scan = new Scanner(System.in);
    

    Encounter (Player player, PokemonCollection pc) {
        this.collection = pc;
        this.activePlayerPokemon = getPlayerActivePokemon();
        this.wildPokemon = generateWildPokemon();
        this.battling = true; 
        this.thePlayer = player;
        this.attacker = true;
        
        //battle();
    }

    private Pokemon getPlayerActivePokemon() {
        //TODO actually take from Player's team 
        //
        
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("pokemon/databaseFiles/pokedata.txt");
        return collection.getPokemonAtIndex(4);
    }

    public static void main(String args[]) {
        Player testPlayer = new Player(true, "david", "password");
        PokemonCollection collection = new PokemonCollection();
        collection.loadData("pokemon/databaseFiles/pokedata.txt");
        Encounter test = new Encounter(testPlayer, collection);
        test.battle();
        // Pokemon wild = test.getWildPokemon();

        // System.out.println(wild.toString("other"));

    }

    private Pokemon generateWildPokemon() {
        int randomInt =  generateRandomInt(0,  this.collection.getNumPokes() );//(int) (Math.random() * ( collection.getNumPokes() - 0 ));
        Pokemon generated =  this.collection.getPokemonAtIndex(randomInt);
        return generated;
    }

    public void battle() {
        System.out.println("battling a wild " + this.wildPokemon.getIdentStats().getName());
        System.out.println("go " + this.activePlayerPokemon.getIdentStats().getName());
        String inputString;
        while(battling) {
            System.out.println("Type fight, bag, pokemon, or run");
            inputString = "";
            inputString = getInput();
            switch(inputString) {
                case  "fight" :
                    fight();
                    break;
                case "bag" :
                    bag();
                    break;
                case "pokemon":
                    switchPokemon();
                    break;
                case "run":
                    run();
                    break;
                case "b":
                    break;
                default:

            }
        }
    }
    public void fight() {
        String message = this.attacker ? "You are attacking":"You are deffending";
        System.out.println(message);
        System.out.println("Displaying " + this.activePlayerPokemon.getIdentStats().getName() + "'s moves");
        String inputString = getInput();
        if(inputString.equals("b")) {
            return ; 
        }
        int moveNum = Integer.parseInt(inputString);
        if(moveNum > 3) {
            fight();
            return ;
        }
        Move attackMove;
        Move deffMove;
        Pokemon attacker;
        Pokemon deffender;
        if(this.attacker) {
            attacker = this.activePlayerPokemon;
            deffender = this.wildPokemon;
            attackMove = getPokemonMove(attacker, moveNum);
            deffMove =  getPokemonMove(deffender,  generateRandomInt(0, 4));
        }
        else { 
            attacker = this.wildPokemon;
            deffender = this.activePlayerPokemon;
            attackMove = getPokemonMove(attacker, generateRandomInt(0, 4));
            deffMove =  getPokemonMove(deffender, moveNum );
        }
        
        
        System.out.println(resolveAttack(attackMove, deffMove, attacker, deffender));
        if(deffender.hasPokemonFainted()) {
            fainted();
        }

        if(this.attacker) {
            this.attacker = false;
            fight();
        }
        else {
            this.attacker = true;
        }
    }
    
    private Move getPokemonMove(Pokemon pokemon, int moveNum) {
        Move move0 = new Move("tackle", "normal", "physical", 20);
        Move move1 = new Move("cut", "normal", "physical", 10);
        Move move2 = new Move("absorb", "grass", "special", 30);
        Move move3 = new Move("ember", "fire", "special", 40);
        Move[] moveset = {move0, move1 , move2, move3 };
        return moveset[moveNum];
    }

    private String resolveAttack(Move attackMove, Move defMove, Pokemon attacker , Pokemon defender) {
        
        String resultString = attacker.getIdentStats().getName() + " attacks with " + 
        attackMove.getMoveName() + ". " + defender.getIdentStats().getName() + " defends itself with " + defMove.getMoveName() +". \n";
        int damage;
        int attackVal;
        int defVal;
        if(attackMove.getMoveTypeCat().equals("physical")) {
            attackVal = attackMove.getMoveDamage() + attacker.getOffeniveStats().getATK();
        }
        else {
            attackVal = attackMove.getMoveDamage() + attacker.getOffeniveStats().getSPATK();
        }
        if(defMove.getMoveTypeCat().equals("physical")) {
            defVal = defMove.getMoveDamage() + defender.getDefensiveStats().getDEF();
        }
        else {
            defVal = defMove.getMoveDamage() + defender.getDefensiveStats().getSPDEF();
        }
        damage = attackVal - defVal;

        resultString += "attack hit for "  + Integer.toString(damage);
        if(damage > 0){
            defender.getDefensiveStats().takeDamage(damage);
        }
        resultString += "\n" + defender.getIdentStats().getName() + " " + Integer.toString(defender.getDefensiveStats().getHPCurrent()) + " hp";
        
        return resultString;
    }

    public String fainted () {
        String result = "";
        if(this.wildPokemon.hasPokemonFainted()) {
            result =  this.wildPokemon.getIdentStats().getName() + "has fainted";
            battling = false;
        }
        if(this.activePlayerPokemon.hasPokemonFainted()) {
            result = this.activePlayerPokemon.getIdentStats().getName() + "has fainted.";
            if(this.thePlayer.getPokeTeam().hasActivePokemon()) {
                switchPokemon();
            }
            else {
                result += "out of pokemon.";
                return result;
            }
            
        }
        return result;
    }
    private int calculateDamage() {
        return generateRandomInt(0, 100);
    }

    public void displayMoves() {

    }
    public void bag() {
        displayItems();
        String inputString = getInput();
        if(inputString.equals("b")) {
            return ; 
        }
        useItem(inputString);
    }
    private void displayItems() {
        //Bag playerBag this.player.getBag(); TODO make getBag Function
        //String bagString = playerBag.toString || itemsString();
        String bagString =  "PokeBall 9, Potion 3, GreatBall 1";
        String[] itemStrings = bagString.split(",");
        for(int index = 0; index < itemStrings.length; index++) {
            System.out.println(index + " " + itemStrings[index]);
        }
    }

    public String getInput() {
        // Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        if(input.equals("")) {
            return getInput();
        }
        //scan.nextLine();
        //scan.close();
        return input;
    }
    public void  catchPokemon () {

    }

    public void switchPokemon () {
        Team team = this.thePlayer.getPokeTeam();
        team.displayTeam();
        String input  = getInput();
        int convetedInput = Integer.parseInt(input);
        if(convetedInput > 6) {
            switchPokemon();
            return;
        }

        if(input.equals("b")) {
            System.out.println("return to main menu");
            return ;
        }
    }
    
    public void useItem (String choosenItemStr) {
        //choosenItem.use();
        //Item choosenItem = bag.getItemAtIndex(Integer.parseInt(choosenItemStr));
        
        System.out.println("Using item " + choosenItemStr);
    }

    public void run () {
        int runAwayChance = 80;
        int ran = generateRandomInt(0, 100);
        if(ran < 80) {
            battling = false;
            System.out.println("Ran away succesful");
        }
        else {
            System.out.println("Run away was unsuccessful");
        }
        //this.wildPokemon
    }
    public Pokemon getWildPokemon () {
        return this.wildPokemon;
    } 

    private int generateRandomInt (int min, int max) {
        return (int) (Math.random() * ((max-min))+min);
    }
}