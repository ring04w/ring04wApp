package app.ring04w.com.omninotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ring04w on 15/8/24.
 */
public class AddContent extends Activity {

    private Button save;
    private Button cancel;
    private EditText c_text;
    private ImageView c_img;
    private VideoView c_video;
    private String value;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    private File phoneFile;//文件对象
    private File videoFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);

        value = getIntent().getStringExtra("flag").toString();

        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();


        save = (Button)findViewById(R.id.saveId);
        cancel = (Button)findViewById(R.id.cancelId);

        c_text = (EditText)findViewById(R.id.c_text);
        c_img = (ImageView)findViewById(R.id.c_img);
        c_video = (VideoView)findViewById(R.id.c_video);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDB();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();

    }



    public void initView(){
        if(value.equals("1")){//文字
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.GONE);
        }
         if(value.equals("2")){//图片
            c_img.setVisibility(View.VISIBLE);
            c_video.setVisibility(View.GONE);
            Intent iimge = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到系统相机
            phoneFile = new File(Environment.getExternalStorageDirectory()
            .getAbsoluteFile() + "/" + getTime() + ".jpg");//文件名，用时间命名，前面是获取绝对路径
            Uri imageUri = Uri.fromFile(phoneFile);
            iimge.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//把拍的照片存起来
            startActivityForResult(iimge, 1);

        }
        else if(value.equals("3")){//视频
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.VISIBLE);
            Intent vvideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoFile = new File(Environment.getExternalStorageDirectory()
            .getAbsoluteFile() + "/" + ".mp4");
            vvideo.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
            startActivityForResult(vvideo, 2);

        }
    }


    public void addDB(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesDB.CONTENT, c_text.getText().toString());
        contentValues.put(NotesDB.TIME, getTime());
        contentValues.put(NotesDB.PATH, phoneFile + "");
        contentValues.put(NotesDB.VIDEO, videoFile + "");
        dbWriter.insert(NotesDB.TABLE_NAME, null, contentValues);
    }

    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日  HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return  str;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Bitmap bitmap = BitmapFactory.decodeFile(phoneFile.getAbsolutePath());
            c_img.setImageBitmap(bitmap);
        }
        else if (requestCode == 2){
            c_video.setVideoURI(Uri.fromFile(videoFile));
            c_video.start();
        }
    }


}
