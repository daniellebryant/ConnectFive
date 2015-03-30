package edu.augustana.csc490.individualgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(playButtonListener);

        Button howToButton = (Button) findViewById(R.id.howToButton);
        howToButton.setOnClickListener(howToButtonListener);

    }
    //playButtonListener
    OnClickListener playButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent playIntent = new Intent(this, GameOptionsActivity.class);
            startActivity(playIntent);
        }
    };

    //howToButton listener
    OnClickListener howToButtonListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent howToIntent = new Intent(this, HowToActivity.class);
            startActivity(howToIntent);
        }
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
