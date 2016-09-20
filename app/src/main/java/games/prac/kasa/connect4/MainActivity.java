package games.prac.kasa.connect4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private boolean turn = false;

    public void dropIn(View view){
        ImageView coin = (ImageView) view;
        coin.setTranslationY(-1000f);
        if(turn){
            coin.setImageResource(R.drawable.blue);
            turn = false;
        }
        else{
            coin.setImageResource(R.drawable.red);
            turn = true;
        }
        coin.animate().translationYBy(1000f).setDuration(300);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
