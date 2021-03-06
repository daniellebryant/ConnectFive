package edu.augustana.csc490.individualgame;

/**
 * Created by Danielle Morgan on 4/5/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameBoardView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "GameStarter";

    private GameThread gameThread; // runs the main game loop
    private Activity gameBoardActivity; // keep a reference to the main Activity

    private boolean isGameOver = true;
    private boolean player1Turn = true;

    private int radius = 30;
    private int screenWidth;
    private int screenHeight;

    private Paint myPaint;
    private Paint backgroundPaint;

    private GamePiece upperLeft;
    private GamePiece upperMiddle;
    private GamePiece upperRight;
    private GamePiece middleLeft;
    private GamePiece middleRight;
    private GamePiece center;
    private GamePiece lowerLeft;
    private GamePiece lowerRight;
    private GamePiece lowerMiddle;
    private GamePiece innerTopLeft;
    private GamePiece innerTopMiddle;
    private GamePiece innerTopRight;
    private GamePiece innerMiddleLeft;
    private GamePiece innerMiddleRight;
    private GamePiece innerLowerLeft;
    private GamePiece innerLowerRight;
    private GamePiece innerLowerMiddle;

    private ArrayList<GamePiece> gamePieceList = new ArrayList<GamePiece>();
    private ArrayList<GamePiece> outerCornerList = new ArrayList<GamePiece>();
    private ArrayList<GamePiece> innerCornerList = new ArrayList<GamePiece>();
    private ArrayList<GamePiece> acrossList = new ArrayList<GamePiece>();
    private ArrayList<GamePiece> upDownList = new ArrayList<GamePiece>();
    private ArrayList<GamePiece> leftDiagonalList = new ArrayList<GamePiece>();
    private ArrayList<GamePiece> rightDiagonalList = new ArrayList<GamePiece>();


    private int player1OuterCorners = 0;
    private int player1InnerCorners = 0;
    private int player1Down = 0;
    private int player1Across = 0;
    private int player1LeftDiagonal = 0;
    private int player1RightDiagonal = 0;

    private int player2OuterCorners = 0;
    private int player2InnerCorners = 0;
    private int player2Down = 0;
    private int player2Across = 0;
    private int player2LeftDiagonal = 0;
    private int player2RightDiagonal = 0;
    private int sum = 0;


    public GameBoardView(Context context, AttributeSet atts) {
        super(context, atts);
        gameBoardActivity = (Activity) context;

        getHolder().addCallback(this);

        myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);

    }

    // called when the size changes (and first time, when view is created)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

        startNewGame();
    }

    public void startNewGame() {
        //Create outer square pieces
        upperLeft = new GamePiece(radius, radius, radius, 0);
        upperMiddle = new GamePiece(getWidth() / 2, radius, radius, 0);
        upperRight = new GamePiece(getWidth() - radius, radius, radius, 0);
        middleLeft = new GamePiece(radius, getHeight() / 2, radius, 0);
        middleRight = new GamePiece(getWidth() - radius, getHeight() / 2, radius, 0);
        lowerLeft = new GamePiece(radius, getHeight() - radius, radius, 0);
        lowerMiddle = new GamePiece(getWidth() / 2, getHeight() - radius, radius, 0);
        lowerRight = new GamePiece(getWidth() - radius, getHeight() - radius, radius, 0);

        //Create inner square pieces
        innerTopLeft = new GamePiece((getWidth() / 4), getHeight() / 4, radius, 0);
        innerTopRight = new GamePiece((getWidth() - (getWidth() / 4)), getHeight() / 4, radius, 0);
        innerTopMiddle = new GamePiece((getWidth() / 2), getHeight() / 4, radius, 0);
        innerMiddleLeft = new GamePiece(getWidth() / 4, getHeight() / 2, radius, 0);
        innerMiddleRight = new GamePiece(getWidth() - (getWidth() / 4), getHeight() / 2, radius, 0);
        innerLowerLeft = new GamePiece((getWidth() / 4), getHeight() / 2 + getHeight() / 4, radius, 0);
        innerLowerMiddle = new GamePiece((getWidth() / 2), getHeight() / 2 + getHeight() / 4, radius, 0);
        innerLowerRight = new GamePiece((getWidth() - (getWidth() / 4)), getHeight() / 2 + getHeight() / 4,
                radius, 0);

        //Create center piece
        center = new GamePiece(getWidth() / 2, getHeight() / 2, radius, 0);

        //Add outer square pieces to Lists
        gamePieceList.add(upperLeft);
        gamePieceList.add(upperMiddle);
        gamePieceList.add(upperRight);
        gamePieceList.add(middleLeft);
        gamePieceList.add(middleRight);
        gamePieceList.add(lowerLeft);
        gamePieceList.add(lowerMiddle);
        gamePieceList.add(lowerRight);
        outerCornerList.add(upperLeft);
        outerCornerList.add(upperRight);
        outerCornerList.add(lowerLeft);
        outerCornerList.add(lowerRight);
        acrossList.add(middleLeft);
        acrossList.add(middleRight);
        upDownList.add(upperMiddle);
        upDownList.add(lowerMiddle);
        leftDiagonalList.add(upperLeft);
        leftDiagonalList.add(lowerRight);
        rightDiagonalList.add(upperRight);
        rightDiagonalList.add(lowerLeft);

        //Add inner square pieces to Lists
        gamePieceList.add(innerLowerRight);
        gamePieceList.add(innerTopLeft);
        gamePieceList.add(innerTopMiddle);
        gamePieceList.add(innerTopRight);
        gamePieceList.add(innerMiddleLeft);
        gamePieceList.add(innerMiddleRight);
        gamePieceList.add(innerLowerLeft);
        gamePieceList.add(innerLowerMiddle);
        innerCornerList.add(innerLowerLeft);
        innerCornerList.add(innerLowerRight);
        innerCornerList.add(innerTopLeft);
        innerCornerList.add(innerTopRight);
        acrossList.add(innerMiddleLeft);
        acrossList.add(innerMiddleRight);
        upDownList.add(innerTopMiddle);
        upDownList.add(innerLowerMiddle);
        leftDiagonalList.add(innerTopLeft);
        leftDiagonalList.add(innerLowerRight);
        rightDiagonalList.add(innerTopRight);
        rightDiagonalList.add(innerLowerLeft);

        if (isGameOver) {
            isGameOver = false;
            gameThread = new GameThread(getHolder());
            gameThread.start(); // start the main game loop going
        }
    }

     private void gameStep(){

    }

    public void updateView(Canvas canvas){
        if(canvas != null){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

            for(GamePiece piece: gamePieceList){
                piece.draw(canvas);
            }
            center.draw(canvas);
        }
    }

    // stop the game; may be called by the MainGameFragment onPause
    public void stopGame(){
        if(gameThread != null){
            gameThread.setRunning(false);
        }
    }

    // release resources; may be called by GameBoardFragment onDestroy
    public void releaseResources(){

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // called when the surface is destroyed
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // ensure that thread terminates properly
        boolean retry = true;
        gameThread.setRunning(false);

        while(retry){
            try{
                gameThread.join(); //wait for gameThread to finish
                retry = false;
            }catch (InterruptedException e){
                Log.e(TAG, "Thread interrupted", e);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            int dx = (int) e.getX();
            int dy = (int) e.getY();

            for (GamePiece piece : gamePieceList) {
                if (piece.contains(dx, dy)) {
                    if (piece.getOwner() == 0) {
                        if (player1Turn) {
                            if(outerCornerList.contains(piece)){
                                if(leftDiagonalList.contains(piece)){
                                    player1LeftDiagonal = player1LeftDiagonal + 1;
                                }else{
                                    player1RightDiagonal = player1RightDiagonal + 1;
                                }
                                player1OuterCorners = player1OuterCorners + 1;
                            }else if(innerCornerList.contains(piece)){
                                if(leftDiagonalList.contains(piece)){
                                    player1LeftDiagonal = player1LeftDiagonal + 1;
                                }else{
                                    player1RightDiagonal = player1RightDiagonal + 1;
                                }
                                player1InnerCorners = player1InnerCorners + 1;
                            }else if(upDownList.contains(piece)){
                                player1Down = player1Down + 1;
                            }else{
                                player1Across = player1Across + 1;
                            }
                            sum = sum + 1;
                            piece.setOwner(1);
                            player1Turn = false;
                         }else {
                            if(outerCornerList.contains(piece)){
                                if(leftDiagonalList.contains(piece)){
                                    player2LeftDiagonal = player2LeftDiagonal + 1;
                                }else{
                                    player2RightDiagonal = player2RightDiagonal + 1;
                                }
                                player2OuterCorners = player2OuterCorners + 1;
                            }else if(innerCornerList.contains(piece)){
                                if(leftDiagonalList.contains(piece)){
                                    player2LeftDiagonal = player2LeftDiagonal + 1;
                                }else{
                                    player2RightDiagonal = player2RightDiagonal + 1;
                                }
                                player2InnerCorners = player2InnerCorners + 1;

                            }else if(upDownList.contains(piece)){
                                player2Down = player2Down + 1;
                            }else{
                                player2Across = player2Across + 1;
                            }
                            sum = sum + 1;
                            piece.setOwner(2);
                            player1Turn = true;
                        }
                   }

                }

            }

        }
        if(player1Turn){ //true after player2 clicks
            if(player2Down == 4 || player2Across == 4 || player2RightDiagonal == 4
               || player2LeftDiagonal == 4 || player2InnerCorners == 4
               || player2OuterCorners == 4){//counts for player2
                Toast toast1 = Toast.makeText(getContext(), "Player 2 wins", Toast.LENGTH_SHORT);
                toast1.show();
                center.setOwner(2);
             }
        }else{ //false after player1 clicks
            if(player1Down == 4 || player1Across == 4 || player1RightDiagonal == 4
               || player1LeftDiagonal == 4 || player1InnerCorners == 4
               || player1OuterCorners == 4){ //counts for player1
                Toast toast1 = Toast.makeText(getContext(), "Player 1 wins", Toast.LENGTH_SHORT);
                toast1.show();
                center.setOwner(1);
            }
        }
        if(sum == 16){
            Toast toast1 = Toast.makeText(getContext(), "Draw. Nobody Wins", Toast.LENGTH_SHORT);
            toast1.show();
        }

        return true;
    }


    // Thread subclass to run the main game loop
    private class GameThread extends Thread{
        private SurfaceHolder surfaceHolder; // for manipulating canvas
        private boolean threadIsRunning = true; // running by default

        // initializes the surface holder
        public GameThread(SurfaceHolder holder){
            surfaceHolder = holder;
            setName("GameThread");
        }

        // changes running state
        public void setRunning(boolean running){
         threadIsRunning = running;
        }

        @Override
        public void run(){
            Canvas canvas = null;

            while(threadIsRunning){
                try{
                    // get Canvas for exclusive drawing from this thread
                    canvas = surfaceHolder.lockCanvas(null);

                    // lock the surfaceHolder for drawing
                    synchronized(surfaceHolder){
                        gameStep();            // update game state
                        updateView(canvas);   // draw using the canvas
                    }
                    Thread.sleep(10); // if you want to slow down the action...
                } catch (InterruptedException ex){
                    Log.e(TAG,ex.toString());
                }finally{ // regardless if any errors happen
                     //unlock the canvas so other threads can use it
                    if(canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }
    }
}
