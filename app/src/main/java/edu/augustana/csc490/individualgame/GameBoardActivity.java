package edu.augustana.csc490.individualgame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class GameBoardActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
    }
}
