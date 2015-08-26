package app.ring04w.com.omninotes;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by ring04w on 15/8/25.
 */
public class SelectAct  extends Activity{

    private Button s_delete;
    private Button s_back;
    private ImageView s_img;
    private VideoView s_video;
    private TextView s_tv;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

//        System.out.println(getIntent().getIntExtra(NotesDB.ID, 0));

        s_delete = (Button)findViewById(R.id.s_delete);
        s_back = (Button)findViewById(R.id.s_back);
        s_img = (ImageView)findViewById(R.id.s_img);
        s_video = (VideoView)findViewById(R.id.s_video);
        s_tv = (TextView)findViewById(R.id.s_tv);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();

        if (getIntent().getStringExtra(NotesDB.PATH).equals("null")){
            s_img.setVisibility(View.GONE);
        }else{
            s_img.setVisibility(View.VISIBLE);
        }
        if (getIntent().getStringExtra(NotesDB.VIDEO).equals("null")){
            s_video.setVisibility(View.GONE);
        }else {
            s_video.setVisibility(View.VISIBLE);
        }
        s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(
                NotesDB.PATH));
        s_img.setImageBitmap(bitmap);//让图片显示
        s_video.setVideoURI(Uri.parse(getIntent().getStringExtra(NotesDB.VIDEO)));
        s_video.start();//让视频播放

        s_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDate();
                finish();
            }
        });

        s_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void deleteDate(){//删除
        dbWriter.delete(NotesDB.TABLE_NAME,
                "_id=" + getIntent().getIntExtra(notesDB.ID, 0), null);
    }


}
