package app.ring04w.com.http;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button getButton;
    private Button postButton;
    private EditText nameText;
    private EditText ageText;
    private String baseUrl = "http://127.0.0.1:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButton = (Button) findViewById(R.id.getButtonId);
        postButton = (Button) findViewById(R.id.postButtonId);

        nameText = (EditText) findViewById(R.id.nameTextId);
        ageText = (EditText) findViewById(R.id.ageTextId);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                String url = baseUrl + "?" + "name=" + name + "&age=" + age;//加上用户填上的值

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                InputStream inputStream = null;

                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    inputStream = httpEntity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String result = "";
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        result = result + line;
                    }
                    System.out.println(result);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                NameValuePair nameValuePair1 = new BasicNameValuePair("name", name);
                NameValuePair nameValuePair2 = new BasicNameValuePair("age", age);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(nameValuePair1);
                nameValuePairs.add(nameValuePair2);


                HttpEntity requestHttpEntity = null;
                try {
                    requestHttpEntity = new UrlEncodedFormEntity(nameValuePairs);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                HttpPost httpPost = new HttpPost(baseUrl);
                httpPost.setEntity(requestHttpEntity);
                HttpClient httpClient = new DefaultHttpClient();
                InputStream inputStream = null;

                try {
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    inputStream = httpEntity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String result = "";
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        result = result + line;
                    }
                    System.out.println(result);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
