package app.ring04w.com.muactivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {
    ;

    private ProgressBar progressBar;
    private Button firstButton;

    private Handler handler;
    private Button messageButton;

    private TextView showView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressBar = (ProgressBar)findViewById(R.id.progressbarId);
        firstButton = (Button)findViewById(R.id.firstbuttonId);
        firstButton.setOnClickListener(new ButtonLisener());

        handler = new FirstHandler();
        messageButton = (Button)findViewById(R.id.messagebuttonId);
        messageButton.setOnClickListener(new Button1Listener());

        showView = (TextView)findViewById(R.id.show);


    }




    class ButtonLisener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
//            try {
////                Thread.sleep(10*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
           Thread th = new MyThread();
            th.start();

        }
    }


    class MyThread extends Thread{
        @Override
        public void run() {

            for (int i = 0; i < 100; ++i){
                progressBar.setProgress(progressBar.getProgress() + 1);
            }

        }
    }

    class  Button1Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Message msg = handler.obtainMessage();
            msg.what = 2;
            handler.sendMessage(msg);



        }
    }

    class FirstHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            System.out.println("what : " + what);

            showView.setText("what: " + what);
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







