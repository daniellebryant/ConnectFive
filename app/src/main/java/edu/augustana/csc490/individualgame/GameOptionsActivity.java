package edu.augustana.csc490.individualgame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import static android.view.View.OnClickListener;


public class GameOptionsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        //Assign listener to play button
        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(playButtonListener);

        //Assign player spinners
        Spinner player1Spinner = (Spinner) findViewById(R.id.player1Spinner);
        Spinner player2Spinner = (Spinner) findViewById(R.id.player2Spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_list,
                android.R.layout.simple_spinner_dropdown_item);

       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        player1Spinner.setAdapter(adapter);
        player2Spinner.setAdapter(adapter);



    }

    //playButtonListener
    OnClickListener playButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent playIntent = new Intent(GameOptionsActivity.this, GameBoardActivity.class);
            startActivity(playIntent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_options, menu);
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
