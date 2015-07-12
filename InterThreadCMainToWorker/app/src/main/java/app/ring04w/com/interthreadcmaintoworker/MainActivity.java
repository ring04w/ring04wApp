package app.ring04w.com.interthreadcmaintoworker;

import android.os.Handler;
import android.os.Looper;
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

import java.security.PrivateKey;


public class MainActivity extends ActionBarActivity {

    private TextView displayView;
    private Button sendButton;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        displayView = (TextView)findViewById(R.id.displayviewId);

        sendButton =  (Button)findViewById(R.id.sendbuttonId);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ThreadMessage-->" + Thread.currentThread().getName());
                Message msg = handler.obtainMessage();
                msg.obj = "这就是最后结果";
                handler.sendMessage(msg);
            }
        });

        ReadThread rh = new ReadThread();
        rh.start();





    }




    class ReadThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();

             handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    System.out.println("收到了消息");
                    System.out.println("ReadThread-->" + Thread.currentThread().getName());
                }
            };

            Looper.loop();

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
