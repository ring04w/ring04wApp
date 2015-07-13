package app.ring04w.com.listviewex;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

    private TextView tsungView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tsungView = (TextView)findViewById(R.id.ring04wtextviewId);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        HashMap<String, String> map3 = new HashMap<String, String>();
        HashMap<String, String> map4 = new HashMap<String, String>();
        HashMap<String, String> map5 = new HashMap<String, String>();
        map1.put("user_name", "ring04w");
        map1.put("user_ip", "192.168.0.1");
        map2.put("user_name", "Tsung");
        map2.put("user_ip", "192.168.0.2");
        map3.put("user_name", "Peter");
        map3.put("user_ip", "192.168.0.3");
        map4.put("user_name", "Jack");
        map4.put("user_ip", "192.168.0.4");
        map5.put("user_name", "Jobs");
        map5.put("user_ip", "192.168.0.5");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        SimpleAdapter listAdapter = new SimpleAdapter(this, list, R.layout.user,
                new String[]{"user_name", "user_ip"}, new int[]{R.id.user_name, R.id.user_ip});
        setListAdapter(listAdapter);



    }

    protected void onListItemClick(ListView l, View v, int position, long id) {//在这里面根据点击的view执行任务
        super.onListItemClick(l, v, position, id);
        System.out.println("id------>" + id);
        System.out.println("position---->" + position);
        if (id == 0) {
            Toast.makeText(MainActivity.this, "ring04w turn", Toast.LENGTH_SHORT).show();
        }else if (id == 1){
            tsungView.setText("Tsung's Turn");
        }else if (id == 2){
            Uri uri = Uri.parse("smsto:10010");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "Hello, Peter");
            startActivity(intent);
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
