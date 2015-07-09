package app.ring04w.com.muactivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by ring04w on 15/7/9.
 */
public class OtherActivity extends ActionBarActivity {

    private TextView textView;
    private TextView secondtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        secondtextview = (TextView)findViewById(R.id.secondtextview);

        Intent intent = getIntent();
        int age = intent.getIntExtra("app.ring04w.com.muactivity.Age", 10);
        String name = intent.getStringExtra("app.ring04w.com.muactivity.Name");
        textView = (TextView)findViewById(R.id.otherview);
        textView.setText(name + ": " + age);



    }
}
