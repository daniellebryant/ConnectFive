package edu.augustana.csc490.individualgame;

/**
 * Created by Danielle Morgan on 4/5/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameBoardView extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG = "GameStarter";

    private GameThread gameThread; // runs the main game loop
    private Activity gameBoardActivity; // keep a reference to the main Activity

    private boolean isGameOver = true;
    private boolean player1Turn = true;

    private int x;
    private int y;
    private int size = 30;
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

    public GameBoardView(Context context, AttributeSet atts){
        super(context, atts);
        gameBoardActivity = (Activity) context;

        getHolder().addCallback(this);

        myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);

        upperLeft = new GamePiece(0, 0, size, 0);
        upperMiddle = new GamePiece(getWidth()/2, 0, size, 0);
        upperRight = new GamePiece(getWidth(), 0, size, 0);
        middleLeft = new GamePiece(0, getHeight()/2, size, 0);
        middleRight = new GamePiece(0, getHeight()/2, size, 0);
        center = new GamePiece(getWidth()/2, getHeight()/2, size, 0);
        innerLowerRight = new GamePiece((getWidth()-(getWidth()/4)), getHeight()/2 + getHeight()/4,
                size, 0);
        lowerLeft = new GamePiece(getWidth(), getHeight(), size, 0);
        lowerMiddle = new GamePiece(getWidth()/2, getHeight(), size, 0);
        lowerRight = new GamePiece(0, getHeight(), size, 0);
        innerTopLeft = new GamePiece((getWidth()/4), getHeight()/4, size, 0);
        innerTopMiddle = new GamePiece((getWidth()/2), getHeight()/4, size, 0);
        innerLowerRight = new GamePiece((getWidth() - (getWidth()/4)), getHeight()/4, size, 0);
        innerMiddleLeft = new GamePiece(getWidth()/2/2, getHeight()/2, size, 0);
        innerMiddleRight = new GamePiece(getWidth() - (getWidth()/4), getHeight()/2, size, 0);
        innerLowerLeft = new GamePiece((getWidth()/4), getHeight()/2 + getHeight()/4, size, 0);
        innerLowerMiddle = new GamePiece((getWidth()/2), getHeight()/2 + getHeight()/4, size, 0);
        innerLowerRight = new GamePiece((getWidth()-(getWidth()/4)), getHeight()/2 + getHeight()/4,size,0);


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
        this.x = 25;
        this.y = 25;

        if(isGameOver){
            isGameOver = false;
            gameThread = new GameThread(getHolder());
            gameThread.start(); // start the main game loop going
        }
    }

    private void gameStep(){
        //x++;
    }

    public void updateView(Canvas canvas){
        if(canvas != null){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

            // Draw outside circles
            upperLeft.draw(canvas);
            upperMiddle.draw(canvas);
            upperRight.draw(canvas);
            middleLeft.draw(canvas);
            middleRight.draw(canvas);
            lowerRight.draw(canvas);
            lowerMiddle.draw(canvas);
            lowerLeft.draw(canvas);
            innerTopLeft.draw(canvas);
            innerTopMiddle.draw(canvas);
            innerTopRight.draw(canvas);
            innerMiddleLeft.draw(canvas);
            innerMiddleRight.draw(canvas);
            //canvas.drawCircle(x, y, size, myPaint); // upper left
            //canvas.drawCircle(getWidth()/2, 0, size, myPaint); //upper middle
            //canvas.drawCircle(getWidth(), 0, size, myPaint); // upper right
            //canvas.drawCircle(0, getHeight()/2, size, myPaint ); // middle left
            //canvas.drawCircle(getWidth(), getHeight()/2, size, myPaint); //middle right
            //canvas.drawCircle(0, getHeight(), size, myPaint); //lower right
            //canvas.drawCircle(getWidth()/2, getHeight(), size, myPaint); //lower middle
            //canvas.drawCircle(getWidth(), getHeight(), size, myPaint); //lower left

            //Draw center
            //canvas.drawCircle(getWidth()/2, getHeight()/2, size, myPaint); //center

            //Draw inside circles
            //canvas.drawCircle((getWidth()/4), getHeight()/4, size, myPaint); //top left
            //canvas.drawCircle((getWidth()/2), getHeight()/4, size, myPaint); //top middle
            //canvas.drawCircle((getWidth() - (getWidth()/4)), getHeight()/4, size, myPaint); //top right
            //canvas.drawCircle(getWidth()/2/2, getHeight()/2, size, myPaint); //middle left
            //canvas.drawCircle(getWidth() - (getWidth()/4), getHeight()/2, size, myPaint); //middle right
            //canvas.drawCircle((getWidth()/4), getHeight()/2 + getHeight()/4,
                //size, myPaint); //bottom left
            //canvas.drawCircle((getWidth()/2), getHeight()/2 + getHeight()/4,
                //size, myPaint); //bottom middle
            //canvas.drawCircle((getWidth()-(getWidth()/4)), getHeight()/2 + getHeight()/4,
                //size, myPaint); //bottom right
            innerLowerRight.draw(canvas);
            innerLowerMiddle.draw(canvas);
            innerLowerLeft.draw(canvas);
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
        //if(){

        //}
        //if(e.getAction() == MotionEvent.ACTION_DOWN){
            //this.x = (int) e.getX();
            //this.y = (int) e.getY();
        //}
        return true;
    }

    public boolean onTouchEnd(MotionEvent e){
        player1Turn = !player1Turn;
        return player1Turn;
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
