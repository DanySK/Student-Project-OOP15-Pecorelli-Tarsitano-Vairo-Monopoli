package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToRollDices;
import it.unibo.monopoli.model.table.Start;

public enum ImprevistiCards {

    CARD1("VENITE PAGATI $50 PER I VOSTRI SERVIZI", 1, new ToBePaid(50)),
    CARD2("ANDATE FINO AL PARCO DELLA VITTORIA: SE PASSATE DAL 'VIA' RITIRATE $" + Start.getMuchToPick(), 2),
    CARD3("AVANZATE FINO ALLA STAZIONE PIÙ VICINA: SE È LIBERA POTETE ACQUISTARLA DALLA BANCA; SE È POSSEDUTA DA UN'ALTRO GIOCATORE, PAGATE AL PROPIETARIO IL DOPPIO DELL'AFFITTO CHE GLI SPETTA NORMALMENTE",
            3, MoveUpTo.theNearestStation()),
    CARD4("USCITE GRATIS DI PRIGIONE, SE CI SIETE. POTETE CONSERVARE QUESTA CARTA FINO AL MOMENTO DI SERVIRVENE", 4),
    CARD5("MULTA PER ECCESSO DI VELOCITÀ. PAGATE $20", 5),
    CARD6("LA BANCA VI PAGHERÀ UN DIVIDENDO DI $50", 6, new ToBePaid(50)),
    CARD7("ANDATE DIRETTAMENTE IN PRIGIONE E SENZA PASSARE DAL 'VIA'", 7),
    CARD8("ESEGUITE LAVORI DI MANUTENZIONE SU TUTTI I VOSTRI EDIFICI: PAGATE $25 PER OGNI CASA E $100 PER OGNI ALBERGO CHE POSSIEDETE", 8),
    CARD9("SIETE STATI PROMOSSI ALLA PRESIDENZA DEL CONSIGLIO DI AMMINISTRAZIONE: PAGATE $50 AD OGNI GIOCATORE", 9),
    CARD10("ANDATE AVANTI FINO AL 'VIA' E RITIRATE $" + Start.getMuchToPick(), 10),
    CARD11("ANDATE FINO ALLA STAZIONE NORD: SE PASSATE DAL 'VIA' RITIRATE " + Start.getMuchToPick(),11),
    CARD12("AVANZATE FINO ALLA STAZIONE PIÙ VICINA: SE È LIBERA POTETE ACQUISTARLA DALLA BANCA; SE È POSSEDUTA DA UN'ALTRO GIOCATORE, PAGATE AL PROPIETARIO IL DOPPIO DELL'AFFITTO CHE GLI SPETTA NORMALMENTE",
            12, MoveUpTo.theNearestStation()),
    CARD13("ANDATE FINO A LARGO COLOMBO: SE PASSATE DAL VIA RITIRATE $" + Start.getMuchToPick(), 13),
    CARD14("ANDATE FINO A CORSO ATENEO: SE PASSATE DAL VIA RITIRATE $" + Start.getMuchToPick(), 14),
    CARD15("AVANZATE FINO ALLA STAZIONE PIÙ VICINA: SE È LIBERA POTETE ACQUISTARLA DALLA BANCA; SE È POSSEDUTA DA UN'ALTRO GIOCATORE, PAGATE AL PROPIETARIO 10 VOLTE IL NUMERO FATTO CON I DADI",
            15, MoveUpTo.theNearestStation()),
    CARD16("MATURANO LE CEDOLE DEI VOSTRI FONDI IMMOBILIARI: INCASSATE $150", 16, new ToBePaid(150));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    ImprevistiCards(final String description, final int id, final Action... actions) {
        this.description = description;
        this.cardId = id;
        this.actions = Optional.of(Arrays.asList(actions));
    }

    public int getID() {
        return this.cardId;
    }

    public String getDescription() {
        return this.description;
    }

    public Optional<List<Action>> getActions() {
        return this.actions;
    }
}
