package app.ring04w.com.myfirstapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyActivity extends ActionBarActivity {

    private TextView textView;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private Button button1;
    private Button button2;
    private RadioGroup radioGroup;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    public int counte = 0;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        textView = (TextView)findViewById(R.id.textView);
        button1 = (Button)findViewById(R.id.button_1);
        button2 = (Button)findViewById(R.id.button_2);
        checkBox1 = (CheckBox)findViewById(R.id.checkbox_1);
        checkBox2 = (CheckBox)findViewById(R.id.checkbox_2);
        checkBox3 = (CheckBox)findViewById(R.id.checkbox_3);

        radioGroup = (RadioGroup)findViewById(R.id.RedioGroupId);
        femaleButton = (RadioButton)findViewById(R.id.female_Buton);
        maleButton = (RadioButton)findViewById(R.id.male_button);

        imageView = (ImageView)findViewById(R.id.image_view_id);


        ButtonListener buttonListener = new ButtonListener();
        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);


        CheckBoxListener checkBoxListener = new CheckBoxListener();
        checkBox1.setOnCheckedChangeListener(checkBoxListener);
        checkBox2.setOnCheckedChangeListener(checkBoxListener);
        checkBox3.setOnCheckedChangeListener(checkBoxListener);

        RadioListener radioListener = new RadioListener();
        radioGroup.setOnCheckedChangeListener(radioListener);
//        maleButton.setOnCheckedChangeListener(radioListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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


    class ButtonListener implements View.OnClickListener{

        public void onClick(View view){
            if (view.getId() == R.id.button_1){
                counte ++;
                textView.setText(counte + "");
            }else if (view.getId() == R.id.button_2){
                counte --;
                textView.setText(counte + "");
            }

        }
    }



    class RadioListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == femaleButton.getId()){
                System.out.println("Female button is clicked");
                textView.setText("Female button is clicked");
            }else if (checkedId == maleButton.getId()){
                System.out.println("Male button is clicked");
                textView.setText("male button is clicked");
            }


        }
    }

    class CheckBoxListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.checkbox_1){
                System.out.println("eat clicked");
                textView.setText("Eat button is clicked");
            }else if (buttonView.getId() == R.id.checkbox_2){
                System.out.println("Sleep clicked");
                textView.setText("Sleep button is clicked");
            }else if (buttonView.getId() == R.id.checkbox_3){
                System.out.println("Code clicked");
                textView.setText("Code button is clicked");
            }

            if (buttonView.isChecked()){
                System.out.println("checkbox  is checked");
            }else{
                System.out.println("checkbox is unchecked");
            }
        }


    }

}
