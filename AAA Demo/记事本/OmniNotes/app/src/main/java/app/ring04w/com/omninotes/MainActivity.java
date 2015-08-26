package app.ring04w.com.omninotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.text.SimpleDateFormat;


public class MainActivity extends Activity {

    private Button textButton;
    private Button imageButton;
    private Button videoButton;
    private ListView lv;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private MyAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();

        lv = (ListView)findViewById(R.id.listId);
        textButton = (Button)findViewById(R.id.textId);
        imageButton = (Button)findViewById(R.id.imageId);
        videoButton = (Button)findViewById(R.id.videoId);

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("flag", "1");
                intent.setClass(MainActivity.this, AddContent.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("flag", "2");
                intent.setClass(MainActivity.this, AddContent.class);
                startActivity(intent);

            }
        });


        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("flag", "3");
                intent.setClass(MainActivity.this, AddContent.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//从一个ListView上点击跳转到新的页面
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);//获取点击位置
                Intent intent = new Intent(MainActivity.this, SelectAct.class);
                intent.putExtra(NotesDB.ID, cursor.getInt(cursor.getColumnIndex(NotesDB.ID)));
                intent.putExtra(NotesDB.CONTENT, cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT)));
                intent.putExtra(NotesDB.TIME, cursor.getString(cursor.getColumnIndex(NotesDB.TIME)));
                intent.putExtra(NotesDB.PATH, cursor.getString(cursor.getColumnIndex(NotesDB.PATH)));
                intent.putExtra(NotesDB.VIDEO, cursor.getString(cursor.getColumnIndex(NotesDB.VIDEO)));
                startActivity(intent);

            }
        });

    }


    public void selectDB(){
        Cursor cursor = dbReader.query(NotesDB.TABLE_NAME, null, null, null,
                null, null, null);
        adapter = new MyAdapter(this, cursor);//将adapter实例化
        lv.setAdapter(adapter);

    }

    protected void onResume(){//当第一个活动从暂停状态进入active状态，该方法被调用
        super.onResume();
        selectDB();
    }


}
