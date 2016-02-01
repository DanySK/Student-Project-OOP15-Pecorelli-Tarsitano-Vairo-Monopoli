package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.table.Start;

public enum ProbabilitaCards {

    CARD1("RICEVETE LA PARCELLA DEL DOTTORE: PAGATE $50", 17),
    CARD2("PAGATE LE RETTE SCOLASTICHE DEI VOSTRI FIGLI: VERSATE $50", 18),
    CARD3("EREDITATE $100 DA UNO ZIO LONTANO", 19, new ToBePaid(100)),
    CARD4("USCITE GRATIS DI PRIGIONE, SE CI SIETE. POTETE CONSERVARE QUESTA CARTA FINO AL MOMENTO DI SERVIRVENE", 20),
    CARD5("PAGATE LA RETTA OSPEDALIERA DI $100", 21),
    CARD6("DALLA VENDITA DI UNO STOCK DI MERCI RICAVATE $50", 22, new ToBePaid(50)),
    CARD7("ANDATE DIRETTAMENTE IN PRIGIONE E SENZA PASSARE DAL 'VIA'", 23),
    CARD8("PAGATE PER CONTRIBUTI DI MIGLIORIA STRADALE $40 PER OGNI CASA E $115 PER OGNI ALBERGO CHE POSSEDETE", 24),
    CARD9("È IL VOSTRO COMPLEANNO: OGNI GIOCATORE VI REGALA $10", 25),
    CARD10("ANDATE AVANTI FINO AL 'VIA' E RITIRATE $" + Start.getMuchToPick(), 26),
    CARD11("RICEVETE $25 PER LA VOSTRA CONSULENZA", 27, new ToBePaid(25)),
    CARD12("LA BANCA RICONOSCE UN ERRORE NEL VOSTRO ESTRATTO CONTO: INCASSATE $200", 28, new ToBePaid(200)),
    CARD13("AVETE VINTO IL SECONDO PREMIO IN UN CONCORSO DI BELLEZZA. INCASSATE $10", 29, new ToBePaid(10)),
    CARD14("MATURANO LE CEDOLE DELLE VOSTRE AZIONI: INCASSATE $100", 30, new ToBePaid(100)),
    CARD15("VI VIENE RIMBORSATA LA TASSA SUI REDDITI: INCASSATE $20", 31, new ToBePaid(20)),
    CARD16("MATURANO GLI INTERESSI DELLA VOSTRA ASSICURAZIONE SULLA VITA: INCASSATE $100", 32, new ToBePaid(100));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    ProbabilitaCards(final String description, final int id, final Action... actions) {
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
