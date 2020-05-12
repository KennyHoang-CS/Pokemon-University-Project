package gamarket;

public class Store {
    private Bag bag;
    private double money;

    Store(Bag bag, double money){
        this.bag = bag;
        this.money = money;
    }

    public Bag getBag() { return this.bag; }

    public double getMoney() { return this.money; }

    public boolean purchase(String item, int qty){
        boolean purchased = true;
        if(this.money > (getBuyPrice(item)*qty)){
            this.money = money - (getBuyPrice(item)*qty);
            this.bag.addItem(item,qty);
            return purchased;
        }
        return !purchased;
    }

    public boolean sell(String item, int qty){
        boolean sold = true;
        if(this.bag.getQty(item) > 0){
            this.money = money + (getSellPrice(item)*qty);
            this.bag.removeItem(item,qty);
            return sold;
        }
        return !sold;
    }

    private double getBuyPrice(String item){
        if(item.compareToIgnoreCase("pokeball") == 0){
            return new PokeBall().getBuyCost();
        }else if(item.compareToIgnoreCase("potion") == 0){
            return new Potion().getBuyCost();
        }
        return 0.0;
    }

    private double getSellPrice(String item){
        if(item.compareToIgnoreCase("pokeball") == 0){
            return new PokeBall().getSellCost();
        }else if(item.compareToIgnoreCase("potion") == 0){
            return new Potion().getSellCost();
        }
        return 0.0;
    }

}
