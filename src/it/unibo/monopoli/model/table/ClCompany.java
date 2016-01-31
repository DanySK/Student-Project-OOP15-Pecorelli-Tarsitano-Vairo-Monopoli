package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.BoxesPositions;
import it.unibo.monopoli.model.mainunits.Owner;

public enum ClCompany implements Ownership {
    ELETTRIC("ELECTRIC COMPANY", 8),
    WATER("WATER COMPANY", 21);

    Company(final String name, final int id) {
        super(name, id, owner);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Contract getContract() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Owner getOwner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setOwner(Owner owner) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Group getGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGroup(Group group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isMortgaged() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setMortgage(boolean mortgage) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setContract(Contract contract) {
        // TODO Auto-generated method stub
        
    }

}
