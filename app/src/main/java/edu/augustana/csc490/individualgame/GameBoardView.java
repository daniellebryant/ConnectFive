package edu.augustana.csc490.individualgame;

/**
 * Created by Danielle Morgan on 4/5/2015.
 */

import android.app.Activity;
import android.content.Context;
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

public class GameBoardView extends SurfaceView implements SurfaceHolder.Callback{
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

    private int player = 0;

    private int player1OuterCorners;
    private int player1InnerCorners;
    private int player1Down;
    private int player1Across;
    private int player1LeftDiagonal;
    private int player1RightDiagonal;

    private int player2OuterCorners;
    private int player2InnerCorners;
    private int player2Down;
    private int player2Across;
    private int player2LeftDiagonal;
    private int player2RightDiagonal;



    public GameBoardView(Context context, AttributeSet atts){
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

        startNewGame();
    }

    public void startNewGame(){
        upperLeft = new GamePiece(0, 0, radius, 0);
        gamePieceList.add(upperLeft);
        upperMiddle = new GamePiece(getWidth()/2, 0, radius, 0);
        gamePieceList.add(upperMiddle);
        upperRight = new GamePiece(getWidth(), 0, radius, 0);
        gamePieceList.add(upperRight);
        middleLeft = new GamePiece(0, getHeight()/2, radius, 0);
        gamePieceList.add(middleLeft);
        middleRight = new GamePiece(getWidth(), getHeight()/2, radius, 0);
        gamePieceList.add(middleRight);
        center = new GamePiece(getWidth()/2, getHeight()/2, radius, 0);
        innerLowerRight = new GamePiece((getWidth()-(getWidth()/4)), getHeight()/2 + getHeight()/4,
                radius, 0);
        gamePieceList.add(innerLowerRight);
        lowerLeft = new GamePiece(getWidth(), getHeight(), radius, 0);
        gamePieceList.add(lowerLeft);
        lowerMiddle = new GamePiece(getWidth()/2, getHeight(), radius, 0);
        gamePieceList.add(lowerMiddle);
        lowerRight = new GamePiece(0, getHeight(), radius, 0);
        gamePieceList.add(lowerRight);
        innerTopLeft = new GamePiece((getWidth()/4), getHeight()/4, radius, 0);
        gamePieceList.add(innerTopLeft);
        innerTopMiddle = new GamePiece((getWidth()/2), getHeight()/4, radius, 0);
        gamePieceList.add(innerTopMiddle);
        innerTopRight = new GamePiece((getWidth() - (getWidth()/4)), getHeight()/4, radius, 0);
        gamePieceList.add(innerTopRight);
        innerMiddleLeft = new GamePiece(getWidth()/4, getHeight()/2, radius, 0);
        gamePieceList.add(innerMiddleLeft);
        innerMiddleRight = new GamePiece(getWidth() - (getWidth()/4), getHeight()/2, radius, 0);
        gamePieceList.add(innerMiddleRight);
        innerLowerLeft = new GamePiece((getWidth()/4), getHeight()/2 + getHeight()/4, radius, 0);
        gamePieceList.add(innerLowerLeft);
        innerLowerMiddle = new GamePiece((getWidth()/2), getHeight()/2 + getHeight()/4, radius, 0);
        gamePieceList.add(innerLowerMiddle);

        if(isGameOver){
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
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            int dx = (int) e.getX();
            int dy = (int) e.getY();

            for(GamePiece piece: gamePieceList){
                if(piece.contains(dx, dy)){
                   if(player1Turn){
                     piece.setOwner(1);
                     player1Turn = false;
                   }else{
                     piece.setOwner(2);
                     player1Turn = true;
                   }

                }
            }
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
