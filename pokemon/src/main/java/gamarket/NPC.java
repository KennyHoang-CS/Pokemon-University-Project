package gamarket;

public abstract class NPC {
    String name;
    String phrases[]= new String[5];


    public String getDialogueAt(int element){
        return this.name +": " + phrases[element];
    }

}
