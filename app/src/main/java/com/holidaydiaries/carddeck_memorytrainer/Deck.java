package com.holidaydiaries.carddeck_memorytrainer;


import java.util.Random;

/**
 * Created by dick on 11/05/2015.
 */
public class Deck {
    int laidCards;
    int remainingCards;
    int position;
    int deckSize;
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
            "c01", "c02" , "c03", "c04", "c05","c06", "c07", "c08", "c09", "c10", "c11", "c12", "c13",
            "h01", "h02" , "h03", "h04", "h05","h06", "h07", "h08", "h09", "h10", "h11", "h12", "h13",
			"d01", "d02" , "d03", "d04", "d05","d06", "d07", "d08", "d09", "d10", "d11", "d12", "d13",
			"s01", "s02" , "s03", "s04", "s05","s06", "s07", "s08", "s09", "s10", "s11", "s12", "s13"
    };

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void drawCard(){
        if (remainingCards != 0) {
            position++;
            laidCards++;
            remainingCards--;
        }
    }
    public void returnCard(){
        if (laidCards != 0 ){
            position--;
            laidCards--;
            remainingCards++;
        }

    }

    Deck() {
        super();
    }


    Deck (int packCount) {
        deckSize = packCount * packOfCards.length;
        laidCards = 0;
        remainingCards = deckSize;
        position = -1;
        deck = packOfCards;
        for (int i = 0; i < packCount; i++){
            System.arraycopy(packOfCards, 0, deck, i*packOfCards.length, packOfCards.length);
        }

    }

    public void shuffle(){
        String temp;
        int random;
        Random rand = new Random();
        for (int i = 0; i < deckSize; i++) {
            temp = deck[i];
            random = rand.nextInt(deckSize);
            deck[i] = deck[random];
            deck[random] = temp;

        }
        setPosition(-1);
        setLaidCards(0);
        setRemainingCards(deckSize);
    }

    public String[] getDeck() {
        return deck;
    }

    public void setDeck(String[] deck) {
        this.deck = deck;
    }

    public void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public String getCurrentCard() {
        if (getPosition() >= 0) {
            return deck[getPosition()];
        } else {
            return String.valueOf(R.drawable.blueback);
        }

    }

    public void rewind(){
        setPosition(-1);
        setLaidCards(0);
        setRemainingCards(getDeckSize());
    }

    public void fastforward(){
        setPosition(getDeckSize()-1);
        setLaidCards(getDeckSize());
        setRemainingCards(0);
    }
}
