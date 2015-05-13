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

        deck.shuffle();
        unturned = (ImageButton) findViewById(R.id.unturned);
        turned = (ImageButton) findViewById(R.id.turned);
        turned.setVisibility(View.INVISIBLE);

        unturned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = deck.drawCard();
                refreshScreen(imageName);

            }
        });

        turned.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String imageName = deck.returnCard();
                refreshScreen(imageName);

            }
        });



        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void refreshScreen( String imageName){
        int id = getResources().getIdentifier(imageName, "drawable",  getPackageName());
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
}
