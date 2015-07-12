package app.ring04w.com.httpclient;

import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {
    private EditText nameText;
    private EditText passwordText;
    private Button summitButton;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText)findViewById(R.id.nametextId);
        passwordText = (EditText)findViewById(R.id.passwordtextId);
        summitButton = (Button)findViewById(R.id.summitbuttonId);

        ButtonListener buttonListener = new ButtonListener();
        summitButton.setOnClickListener(buttonListener);



    }


    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String name = nameText.getText().toString();
            String password = passwordText.getText().toString();

            GetThread gt = new GetThread(name, password);
            gt.start();

        }
    }



    class GetThread extends Thread{

        String name;
        String password;
        public GetThread(String name, String password){
            this.name = name;
            this.password = password;

        }



        @Override
        public void run() {
            HttpClient httpClient = new DefaultHttpClient();
            String url = "http://localhost:8080?name=" + name + "&password=" + password;
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse resp = httpClient.execute(httpGet);

                int code = resp.getStatusLine().getStatusCode();
                if (code == 200){
                    HttpEntity entity = resp.getEntity();
                    InputStream in = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = reader.readLine();
                    android.util.Log.d("line:", line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
