package it.unibo.monopoli.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.table.Ownership;

public class TestController {
    private static final int PAWN_ID_AND_N_PLAYER_1 = 1;
    private static final int PAWN_ID_AND_N_PLAYER_2 = 2;
    private static final int PAWN_ID_AND_N_PLAYER_3 = 3;
    private static final int PAWN_ID_AND_N_PLAYER_4 = 4;
    private static final int PAWN_ID_AND_N_PLAYER_5 = 5;
    private static final int PAWN_ID_AND_N_PLAYER_6 = 6;

    private static final int MONEY_5_PLAYERS = 200;

    private static final int OWNERSHIP_5_PLAYERS = 4;
    
    private static final int N_ALL_BOXES = 40;
    @Test
    public void testBuy() {
        Controller controller =new ControllerImpl();
        

        controller.addPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true);
        controller.addPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true);
        controller.initializedVersion(EVersion.CLASSIC);
        
        List<it.unibo.monopoli.model.table.Box> boxes=controller.getAllBoxes();
        //      final GameVersion version = new GameVersionImpl(new ClassicStrategy(players));
        
        //this is a test for try what do if an ownership is already buy

        int i=controller.toRollDices();
        try{

        System.out.println(controller.getActualPosition());

        controller.setActualPosition(1);
        controller.buyOwnership();
        }catch(IllegalArgumentException e){
            System.out.println("This ownership is already buyed");
        }catch(ClassCastException e){
            System.out.println("This is not an ownreship");
        }
        
        


        List<Actions> actions=controller.getNextBoxsActions(boxes.get(i), controller.getPlayers().get(0));
        
       
        System.out.println(actions);
    }

//    public void testMortgage() {
//        Controller controller =new ControllerImpl();
//        
//        
//        controller.addPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true);
//        controller.addPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true);
//        controller.initializedVersion(EVersion.CLASSIC);
//        controller.getPlayers().get(0).setDicesRoll(true);
//        List<it.unibo.monopoli.model.table.Box> boxes=controller.getAllBoxes();
//        //      final GameVersion version = new GameVersionImpl(new ClassicStrategy(players));
//        
//        //this is a test for try what do if an ownership is already buy
//        try{
//        
//        ((Ownership) boxes.get(1)).setOwner(controller.getPlayers().get(0));
//        ((Ownership) boxes.get(1)).setMortgage(false);
//
//        controller.buyOwnership();
//        controller.getNextBoxsActions(boxes.get(1), controller.getActualPlayer());
//        }catch(IllegalArgumentException e){
//            System.out.println("Non puoi comprare perche è gia di un player");
//        }
//        ((Ownership) boxes.get(1)).setMortgage(true);
//
//
//        List<Actions> actions=controller.getNextBoxsActions(boxes.get(1), controller.getPlayers().get(0));
//        
//       
//        System.out.println(actions);
//    }
}
