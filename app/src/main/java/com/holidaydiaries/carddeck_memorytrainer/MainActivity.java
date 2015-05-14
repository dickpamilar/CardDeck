package com.holidaydiaries.carddeck_memorytrainer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends ActionBarActivity {

    Deck deck;
    TextView laidCards;
    TextView remainingCards;
    ImageView unturned;
    ImageView turned;
    ImageView rewind;
    ImageView fastforward;
    ImageView reshuffle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int defaultDeckCount = 1;
        int deckCount = sharedPref.getInt(getString(R.string.savedDeckCount), defaultDeckCount);

        setContentView(R.layout.activity_main);
        deck = new Deck(deckCount);
        laidCards = (TextView) findViewById(R.id.laidCards);
        remainingCards = (TextView) findViewById(R.id.remainingCards);
        laidCards.setText(String.valueOf(deck.getLaidCards()));
        remainingCards.setText(String.valueOf(deck.getRemainingCards()));


        unturned = (ImageButton) findViewById(R.id.unturned);
        turned = (ImageButton) findViewById(R.id.turned);
        turned.setVisibility(View.INVISIBLE);

        unturned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deck.drawCard();
                refreshScreen();

            }
        });

        turned.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deck.returnCard();
                refreshScreen();

            }
        });

        rewind = (ImageButton) findViewById(R.id.rewind);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deck.rewind();
                refreshScreen();
            }
        });

        fastforward = (ImageButton) findViewById(R.id.fastforward);
        fastforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deck.fastforward();
                refreshScreen();
            }
        });

        reshuffle = (ImageButton) findViewById(R.id.shuffle);
        reshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deck.shuffle();
                refreshScreen();
            }
        });

        if (savedInstanceState == null) {
            deck.shuffle();
        }



            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


    }

    public void refreshScreen( ){
        int id = getResources().getIdentifier(deck.getCurrentCard(), "drawable",  getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        turned.setImageDrawable  (drawable);

        if (deck.remainingCards == 0) {
            unturned.setVisibility(View.INVISIBLE);
        } else {
            unturned.setVisibility(View.VISIBLE);
        }
        if (deck.laidCards == 0 ){
            turned.setVisibility(View.INVISIBLE);
        } else {
            turned.setVisibility(View.VISIBLE);
        }
        remainingCards.setText(String.valueOf(deck.getRemainingCards()));
        laidCards.setText(String.valueOf(deck.getLaidCards()));

    };

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("deck", deck.getDeck());
        outState.putInt("deckSize", deck.getDeckSize());
        outState.putInt("position", deck.getPosition());
        outState.putInt("laidCards", deck.getLaidCards());
        outState.putInt("remainingCards", deck.getRemainingCards());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        deck.setDeck(savedInstanceState.getStringArray("deck"));
        deck.setDeckSize(savedInstanceState.getInt("deckSize"));
        deck.setPosition(savedInstanceState.getInt("position"));
        deck.setLaidCards(savedInstanceState.getInt("laidCards"));
        deck.setRemainingCards(savedInstanceState.getInt("remainingCards"));
        refreshScreen();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
