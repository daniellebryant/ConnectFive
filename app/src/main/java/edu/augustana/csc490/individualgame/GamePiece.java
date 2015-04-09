package edu.augustana.csc490.individualgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by daniellebryant12 on 4/7/2015.
 */
public class GamePiece {
    int x;
    int y;
    int radius;
    int playerOwner;
    Paint myPaint;

    public GamePiece(int x, int y, int radius, int playerOwner){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.playerOwner = playerOwner;
        myPaint = new Paint();
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, myPaint);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getOwner(){
        return playerOwner;
    }

    public boolean contains(int touchX, int touchY){
       int xDistance = touchX - x;
       int yDistance = touchY - y;
       double distance = Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));
       if(distance > radius){
           return false;
       }else{
           return true;
       }
    }

    public void setOwner(int owner){
        if(owner==1){
            myPaint.setColor(Color.MAGENTA);

        }else if(owner==2){
            myPaint.setColor(Color.CYAN);

        }else{ // not owned game piece
            myPaint.setColor(Color.BLACK);

        }
    }


}
