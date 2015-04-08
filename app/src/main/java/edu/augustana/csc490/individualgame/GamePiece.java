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
        if (playerOwner == 0) {
            myPaint.setColor(Color.BLACK);
        }
    }

    public void changeOwner(int newOwner) {
        // change owner
        // change color
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, myPaint);
    }

}
