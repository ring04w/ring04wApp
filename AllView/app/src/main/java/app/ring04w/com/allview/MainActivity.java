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
    private Button progressButton;

    private SeekBar seekBar;
    private Button seekBarButton;

    private RatingBar ratingBar;
    private Button ratingBarButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar)findViewById(R.id.first_progressbar);
        progressButton = (Button)findViewById(R.id.progressbar_button);
        ButtonProgressBarListener buttonProgressBarListener = new ButtonProgressBarListener();
        progressButton.setOnClickListener(buttonProgressBarListener);


        seekBar = (SeekBar)findViewById(R.id.first_seekbar);
        SeekBarListener seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        seekBarButton = (Button)findViewById(R.id.seekbar_button);
        ButtonSeekBarListener buttonSeekBarListener = new ButtonSeekBarListener();
        seekBarButton.setOnClickListener(buttonSeekBarListener);

        ratingBar = (RatingBar)findViewById(R.id.first_ratingbar);
        RatingBarListener ratingBarListener = new RatingBarListener();
        ratingBar.setOnRatingBarChangeListener(ratingBarListener);
        ratingBarButton = (Button)findViewById(R.id.ratingbar_button);
        ButtonRatingBarListener buttonRatingBarListener = new ButtonRatingBarListener();
        ratingBarButton.setOnClickListener(buttonRatingBarListener);




    }


    class ButtonProgressBarListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            progressBar.incrementProgressBy(10);
        }
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            System.out.println("progress: " + progress + "fromUser: " + fromUser );
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            System.out.println("Start to change the seekBar");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
         System.out.println("Stopped to change the seekBar");
        }
    }

    class ButtonSeekBarListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            seekBar.incrementProgressBy(20);
        }
    }

    class RatingBarListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            System.out.println("progress: " + rating + "fromUser: " + fromUser);
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
