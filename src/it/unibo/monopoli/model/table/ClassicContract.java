package it.unibo.monopoli.model.table;

public class ClassicContract implements Contract{
    
    private final String name;
    
    public ClassicContract(final String name) {
        this.name = name;
    }

    @Override
    public String getname() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ownership getOwnership() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getIncome() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMortgageValue() {
        // TODO Auto-generated method stub
        return 0;
    }

}
