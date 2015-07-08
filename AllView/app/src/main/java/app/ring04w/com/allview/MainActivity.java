package app.ring04w.com.allview;

import android.media.Rating;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    private ProgressBar progressBar;
    private Button button1;
    private Button button2;
    private SeekBar seekBar;
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.first_progressbar);


        button1 = (Button)findViewById(R.id.button_1);
        ButtonListener buttonListener = new ButtonListener();
        button1.setOnClickListener(buttonListener);

        seekBar = (SeekBar)findViewById(R.id.first_seekbar);
        SeekBarListener seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);

        ratingBar = (RatingBar)findViewById(R.id.first_ratingbar);
        RatingBarListener ratingBarListener = new RatingBarListener();
        ratingBar.setOnRatingBarChangeListener(ratingBarListener);
        button2 = (Button)findViewById(R.id.button_2);
        ButtonRatingBarListener buttonRatingBarListener = new ButtonRatingBarListener();
        button2.setOnClickListener(buttonRatingBarListener);



    }


    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            progressBar.incrementProgressBy(10);
        }
    }

    class SeekBarListener implements android.widget.SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
            System.out.println("progress: " + progress + "fromUser: " + fromUser);

        }

        @Override
        public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
            System.out.println("Strat to move bar");
        }

        @Override
        public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
            System.out.println("Stop to move bar");
        }
    }

    class RatingBarListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            System.out.println("rating: " + rating + "fromUser: " + fromUser);
        }
    }

    class ButtonRatingBarListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ratingBar.setRating(ratingBar.getRating() + 1.0f);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
