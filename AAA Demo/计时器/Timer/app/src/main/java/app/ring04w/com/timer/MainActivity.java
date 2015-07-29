package app.ring04w.com.timer;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity{

    private EditText timeInput;
    private Button getTime;
    private TextView timeshow;

    private Button startTime;
    private Button stopTime;

    private  int i = 0;
    private Timer timer = null;
    private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeInput = (EditText)findViewById(R.id.timeInputId);
        getTime = (Button)findViewById(R.id.getTimeId);
        timeshow = (TextView)findViewById(R.id.timshowId);

        startTime = (Button)findViewById(R.id.startTimeId);
        stopTime = (Button)findViewById(R.id.stopTimeId);

        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeshow.setText(timeInput.getText().toString());
                i = Integer.parseInt(timeInput.getText().toString());
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime();

            }
        });

        stopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTime();
            }
        });

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result = msg.arg1 + "";
            timeshow.setText(result);
            startTime();//每次显示完当时时间后，再次启动Timer,就形成了一个循环
        }
    };



        public void startTime() {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    i--;
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);

                }
            };
            timer.schedule(task, 1000);//timer延时时间为1秒
        }

        public void stopTime(){
            timer.cancel();
        }




}

