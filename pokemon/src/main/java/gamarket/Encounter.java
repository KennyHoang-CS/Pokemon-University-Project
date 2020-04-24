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
        this.thePlayer = player;
        this.activePlayerPokemon = getPlayerActivePokemon();
        this.wildPokemon = generateWildPokemon();
        this.battling = true; 
        
        this.attacker = true;
    }

    private Pokemon getPlayerActivePokemon() {
        return this.thePlayer.getPokeTeam().getActivePokemon();
    }

    private Pokemon generateWildPokemon() {
        int randomInt =  generateRandomInt(0,  this.collection.getNumPokes() );//(int) (Math.random() * ( collection.getNumPokes() - 0 ));
        Pokemon generated =  PokemonCollection.getPokemonAtIndex(randomInt);
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
        String message = this.attacker ? " \n You are attacking":"You are deffending";
        System.out.println(message);
        System.out.println("Displaying " + this.activePlayerPokemon.getIdentStats().getName() + "'s moves");
        System.out.println(this.activePlayerPokemon.printPokemonMoves());
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
            attackMove = attacker.getMove(moveNum); //getPokemonMove(attacker, moveNum);
            deffMove =  deffender.getMove(generateRandomInt(0, 3)); //getPokemonMove(deffender,  generateRandomInt(0, 4));
        }
        else { 
            attacker = this.wildPokemon;
            deffender = this.activePlayerPokemon;
            attackMove = attacker.getMove(generateRandomInt(0, 3));
            deffMove =  deffender.getMove(moveNum);
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

        if(damage > 0){
            defender.getDefensiveStats().takeDamage(damage);
        }
        else {
            damage = 0;
        }
        
        resultString += "attack hit for "  + Integer.toString(damage);
        
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

    public void bag() {
        displayItems();
        String inputString = getInput();
        if(inputString.equals("b")) {
            return ; 
        }
        //if(inputString.equals(anObject))
        PokeBall ball = new PokeBall();
        
        useItem(ball);
    }
    private void displayItems() {
        //Bag playerBag this.player.getBag(); TODO make getBag Function
        //String bagString = playerBag.toString || itemsString();
        String bagString =  "PokeBall 9, Potion 3";
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

        if(input.equals("b")) {
            System.out.println("return to main menu");
            return ;
        }

        int convetedInput = Integer.parseInt(input);
        if(convetedInput > team.getNumOfPokesInTeam()) {
            switchPokemon();
            return;
        }

        this.activePlayerPokemon = team.getPokemonAtIndex(convetedInput - 1);
        
        this.attacker = false;
        fight();
        
    }
    
    public void useItem (Item item) {
        //choosenItem.use();
        //Item choosenItem = bag.getItemAtIndex(Integer.parseInt(choosenItemStr));
        String itemString = item.getType();
        switch (itemString) {
            case "Pokeball":
                PokeBall ball = (PokeBall) item;
                boolean throwSucceed = ball.throwBall(this.wildPokemon, this.thePlayer.getPokeTeam());
                if(throwSucceed) {
                    System.out.println("Caught wild " + this.wildPokemon.getIdentStats().getName());
                    this.battling = false;
                }
                else {
                    System.out.println(this.wildPokemon.getIdentStats().getName() + " was not caught");
                    
                }
                break;
            case "Potion":
                Potion potion = (Potion) item;
                potion.use(this.activePlayerPokemon);
                System.out.println(this.wildPokemon.getIdentStats().getName() + "healed to " + Integer.toString(this.activePlayerPokemon.getDefensiveStats().getHPCurrent()) + " hp");
                break;
            default:
                break;
        }
        //System.out.println("Using item " + choosenItemStr);
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
            this.attacker = false;
            fight();
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