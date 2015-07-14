package app.ring04w.com.listviewex;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ring04w on 15/7/14.
 */
public class SendMsgActivity extends Activity {

    private TextView sendmsgTextview;
    private Button sendmsgButton;
    private Button returnButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);


        sendmsgTextview = (TextView)findViewById(R.id.sendmsgId);
        sendmsgButton = (Button)findViewById(R.id.sendbuttonId);
        returnButton = (Button)findViewById(R.id.returnbuttonId);

        ButtonListener buttonListener = new ButtonListener();
        sendmsgButton.setOnClickListener(buttonListener);

        Button1Listener button1Listener = new Button1Listener();
        returnButton.setOnClickListener(button1Listener);



    }


    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("sms:10086");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "This is a test message to 10086");
            startActivity(intent);

        }
    }

    class Button1Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SendMsgActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }



    }

