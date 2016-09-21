package games.prac.kasa.connect4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //false = yellow = 0
    //true = red = 1
    private boolean activePlayer = false;

    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6 ,7 ,8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean reset = false;

    /**
     * Method handles dropping in new coins and checking who won.
     *
     * @param view
     */
    public void dropIn(View view){
        ImageView coin = (ImageView) view;
        //coin.setImageAlpha(100);

        //Log.d("tag", coin.getTag().toString());
        int tag = Integer.parseInt(coin.getTag().toString());
        //Log.d("alpha", ""+coin.getImageAlpha());
        //coin.setVisibility(View.VISIBLE);
        if(gameState[tag] == 2){
            if(activePlayer) {
                coin.setImageResource(R.drawable.red);
                activePlayer = false;
                gameState[tag] = 1;
            }
            else{
                coin.setImageResource(R.drawable.yellow);
                activePlayer = true;
                gameState[tag] = 0;
            }

            coin.setTranslationY(-7500f);
            coin.animate().translationYBy(7500f).setDuration(300);

            for(int[] win : winningPositions){
                if(gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[1]] != 2){
                    //Log.d("win", ""+ activePlayer);

                    //fade the game out of view
                    GridLayout g = (GridLayout)findViewById(R.id.grid);
                    //g.setAlpha(0);
                    g.animate().alpha(0).setDuration(750);

                    //fade the button in
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.newGameLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout.animate().alpha(1).setDuration(750);

                    //let player know who won
                    TextView text = (TextView)findViewById(R.id.winnerMessage);
                    String message = "";
                    if(gameState[win[0]] == 0){
                        //yellow won
                        message = "Yellow Wins!";
                    }
                    else{
                        //red won
                        message = "Red Wins!";
                    }
                    text.setText(message);

                    break;
                }
            }

            int count = 0;

            for(int a : gameState){
                if(a == 2){
                    count++;
                }
            }

            if(count == 0){
                //fade the game out of view
                GridLayout g = (GridLayout)findViewById(R.id.grid);
                //g.setAlpha(0);
                g.animate().alpha(0).setDuration(750);

                //fade the button in
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.newGameLayout);
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.animate().alpha(1).setDuration(750);

                //let player know who won
                TextView text = (TextView)findViewById(R.id.winnerMessage);
                text.setText("Draw");
            }
        }
    }

    public void playAgain(View view){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.newGameLayout);
        linearLayout.animate().alpha(0).setDuration(750);
        linearLayout.setVisibility(View.INVISIBLE);

        activePlayer = false;

        for(int a = 0; a < gameState.length; a++){
            gameState[a] = 2;
        }

        GridLayout grid = (GridLayout)findViewById(R.id.grid);

        for(int a = 0; a < grid.getChildCount(); a++){
            //Log.d("child", ""+a);

            LinearLayout linear = (LinearLayout)grid.getChildAt(a);
            for(int b = 0; b < linear.getChildCount(); b++){
                ((ImageView)linear.getChildAt(b)).setImageResource(0);
            }

        }

        //fade the game out of view
        GridLayout g = (GridLayout)findViewById(R.id.grid);
        //g.setAlpha(0);
        g.animate().alpha(1).setDuration(750);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this code is custom and not what the lecturer discusses in the lecture
        /*
        TableLayout tableLayout = (TableLayout)findViewById(R.id.grid);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        //System.out.println("\n\nWidth is " + width + "\n\n");
        Log.d("width", ""+width);

        tableLayout.getLayoutParams().height = width;
        tableLayout.getLayoutParams().width = width;
        tableLayout.requestLayout();
        */
    }
}
