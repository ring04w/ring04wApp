package app.ring04w.com.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    private Button bindButton;
    private Button unbindButton;
    private SmsReceiver smsReceiver = null;


    private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindButton = (Button)findViewById(R.id.bindbuttonId);
        unbindButton = (Button)findViewById(R.id.unbindbuttonId);

        RegisterReceiverListener registerReceiverListener = new RegisterReceiverListener();
        bindButton.setOnClickListener(registerReceiverListener);

        UnRegisterReceiverListener unRegisterReceiverListener = new UnRegisterReceiverListener();
        unbindButton.setOnClickListener(unRegisterReceiverListener);





    }



    class RegisterReceiverListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            smsReceiver = new SmsReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(SMS_ACTION);
            MainActivity.this.registerReceiver(smsReceiver, filter);
        }
    }

    class UnRegisterReceiverListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            
            MainActivity.this.unregisterReceiver(smsReceiver);
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
