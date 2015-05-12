package com.holidaydiaries.carddeck_memorytrainer;


import java.util.ArrayList;

/**
 * Created by dick on 11/05/2015.
 */
public class Deck {
    int laidCards;
    int remainingCards;
    int position;
    String deck[];

    public void setLaidCards(int laidCards) {
        this.laidCards = laidCards;
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    public int getLaidCards() {
        return laidCards;
    }

    public void setRemainingCards(int remainingCards) {
        this.remainingCards = remainingCards;
    }


    private static final String[] packOfCards = {
            "crd1" , "crd2", "crd3", "crd4",
            "crd5" , "crd6", "crd7", "crd8",
            "crd9" , "crd10", "crd11", "crd12",
            "crd13" , "crd14", "crd15", "crd16",
            "crd17" , "crd18", "crd19", "crd20",
            "crd21" , "crd22", "crd23", "crd24"
    };

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String drawCard(){
        position++;
        return deck[position];
    }

    Deck() {
        super();
    }


    Deck (int packCount) {
        int deckSize = packCount * packOfCards.length;
        laidCards = 0;
        remainingCards = deckSize;
        position = -1;
        deck = packOfCards;
        for (int i = 0; i < packCount; i++){
            System.arraycopy(packOfCards, 0, deck, i*packOfCards.length, packOfCards.length);
        }

    }

}
