package games.prac.kasa.connect4;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;


public class MainActivity extends AppCompatActivity {

    //false = yellow
    //true = red
    private boolean activePlayer = false;

    private boolean[] gameState = new boolean[9];

    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6 ,7 ,8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    /**
     * Method handles dropping in new coins and checking who won.
     *
     * @param view
     */
    public void dropIn(View view){
        ImageView coin = (ImageView) view;
        //coin.setImageAlpha(100);

        Log.d("tag", coin.getTag().toString());
        int tag = Integer.parseInt(coin.getTag().toString());
        //Log.d("alpha", ""+coin.getImageAlpha());
        //coin.setVisibility(View.VISIBLE);
        if(!gameState[tag]){
            if(activePlayer) {
                coin.setImageResource(R.drawable.red);
                activePlayer = false;
            }
            else{
                coin.setImageResource(R.drawable.yellow);
                activePlayer = true;
            }
            gameState[tag] = true;

            coin.setTranslationY(-5000f);
            coin.animate().translationYBy(5000f).setDuration(300);

            for(int[] win : winningPositions){
                if(gameState[win[0]] && gameState[win[1]] && gameState[win[2]]){
                    Log.d("win", ""+ activePlayer);

                    //Someone one
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.newGameLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){

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
