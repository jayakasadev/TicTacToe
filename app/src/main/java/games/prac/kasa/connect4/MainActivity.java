package games.prac.kasa.connect4;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;


public class MainActivity extends AppCompatActivity {

    public void dropIn(View view){
        ImageView coin = (ImageView) view;
        coin.setTranslationY(-1000f);
        coin.setImageResource(R.drawable.red);
        coin.animate().translationYBy(1000f).setDuration(300);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = (TableLayout)findViewById(R.id.grid);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        System.err.println("Width is " + width);

        tableLayout.getLayoutParams().height = width;
        tableLayout.getLayoutParams().width = width;
        tableLayout.requestLayout();
    }
}
