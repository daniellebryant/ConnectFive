package edu.augustana.csc490.individualgame;

import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by Danielle Morgan on 4/4/2015.
 */
public class CheckBoard {
     /* if(player1Turn){ //it is player 1's turn
            if(){ //check outer corners
              player1OuterCorners = player1OuterCorners + 1;
            }else if(){ //check inner corners
               player1InnerCorners = player1InnerCorners + 1;
            }else if(){ //check down
               player1Down = player1Down +1;
            }else if(){ //check across
                player1Across = player1Across + 1;
            }else if(){ //check left diagonal
                player1LeftDiagonal = player1LeftDiagonal + 1;
            }else{ //check right diagonal
                player1RightDiagonal = player1RightDiagonal + 1;
            }

        }else{   //it is player 2's turn
            if(){ //check outer corners
              player2OuterCorners = player2OuterCorners + 1;
            }else if(){ //check inner corners
               player2InnerCorners = player2InnerCorners + 1;
            }else if(){ //check down
               player2Down = player2Down +1;
            }else if(){ //check across
                player2Across = player2Across + 1;
            }else if(){ //check left diagonal
                player2LeftDiagonal = player2LeftDiagonal + 1;
            }else{ //check right diagonal
                player2RightDiagonal = player2RightDiagonal + 1;
            }

         }
    */
     public boolean onTouchEnd(MotionEvent e) {
         //if(player1Turn){
         //Toast toast1 = Toast.makeText(getContext(), "Player 2's turn", Toast.LENGTH_SHORT);
         //toast1.show();
         //}else{
         //Toast toast2 = Toast.makeText(getContext(), "Player 1's turn", Toast.LENGTH_SHORT);
         // toast2.show();
         //}
         // player1Turn = !player1Turn;
         // return player1Turn;

         return true;
     }
}
