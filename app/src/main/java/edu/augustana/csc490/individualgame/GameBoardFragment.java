package edu.augustana.csc490.individualgame;

/**
 * Created by Danielle Morgan on 4/5/2015.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GameBoardFragment extends Fragment {
    private GameBoardView gameBoardView; // custom view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        gameBoardView = (GameBoardView) view.findViewById(R.id.gameBoardView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    //when paused, GameBoardFragment stops the game
    @Override
    public void onPause(){
        super.onPause();
        gameBoardView.stopGame();
    }

    // when GameBoardActivity is over, releases game resources
    @Override
    public void onDestroy(){
        super.onDestroy();
        gameBoardView.releaseResources();
    }
}
