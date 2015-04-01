package edu.augustana.csc490.individualgame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import static android.view.View.OnClickListener;


public class HowToActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        //Assign listener to playButton
        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(playButtonListener);



    }

    //playButtonListener
    OnClickListener playButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent playIntent = new Intent(HowToActivity.this, GameOptionsActivity.class);
            startActivity(playIntent);
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_how_to, menu);
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
