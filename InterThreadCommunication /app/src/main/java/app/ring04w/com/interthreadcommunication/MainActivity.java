package app.ring04w.com.interthreadcommunication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView dataView;
    private Button sendmsgButton;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataView = (TextView)findViewById(R.id.dataviewId);
        handler = new MyHandler();


        sendmsgButton = (Button)findViewById(R.id.sendmsgbuttonId);
        sendmsgButton.setOnClickListener(new BUttonListener());

        
    }

    class BUttonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Thread th = new NetWorkThread();
            th.start();
        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            String s = (String) msg.obj;
            dataView.setText("The result is " + s);


        }
    }

    class NetWorkThread extends Thread{
        @Override
        public void run() {
            System.out.println("This thead is ----->" + Thread.currentThread().getName());
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String result = "这就是运算结果";
            Message msg = handler.obtainMessage();
            msg.obj = result;
            handler.sendMessage(msg);


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
