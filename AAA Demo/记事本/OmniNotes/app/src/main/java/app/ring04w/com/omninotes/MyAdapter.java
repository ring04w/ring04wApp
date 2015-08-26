package app.ring04w.com.omninotes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ring04w on 15/8/24.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private Cursor cursor;//数据查询的内容是cursor类型
    private LinearLayout layout;

    public MyAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }


    @Override
    public int getCount() {
        return cursor.getCount();//返回cursor的长度
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);//加载视图权限
        layout = (LinearLayout)inflater.inflate(R.layout.cell, null);//第一个参数为适配每一条Listview的view对象
        TextView  contenttv = (TextView)layout.findViewById(R.id.list_content);
        TextView timetv = (TextView)layout.findViewById(R.id.list_time);
        ImageView imgtv = (ImageView)layout.findViewById(R.id.list_image);
        ImageView videoiv = (ImageView)layout.findViewById(R.id.list_video);
        cursor.moveToPosition(position);

        //下面分别加载内容 时间 缩略图
        String content = cursor.getString(cursor.getColumnIndex("content"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        String url = cursor.getString(cursor.getColumnIndex("path"));
        String urlvideo = cursor.getString(cursor.getColumnIndex("video"));

        contenttv.setText(content);
        timetv.setText(time);
        imgtv.setImageBitmap(getImageThumbnail(url, 200, 200));
        videoiv.setImageBitmap(getVideoThumbnail(urlvideo, 200, 200,
                MediaStore.Images.Thumbnails.MICRO_KIND));
        return layout;

    }

    public Bitmap getImageThumbnail(String uri, int width, int height){//获取缩略图
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeFile(uri, options);
        options.inJustDecodeBounds = false;//与上面成对出现
        int beWidth = options.outWidth / width;//缩略图宽度和高度
        int beHeight = options.outHeight / height;

        int be = 1;
        if (beWidth < beHeight){//防止图片太大或者太小
            be  = beWidth;
        }else{
            be = beHeight;
        }

        if (be <= 0){
            be = 1;
        }
        options.inSampleSize = be;//缩略图尺寸
        bitmap = BitmapFactory.decodeFile(uri, options);//重新获取bitmap，缩略图之后的图片
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);//传递参数为bitmap对象，宽，高
        return  bitmap;
    }

    private Bitmap getVideoThumbnail(String uri, int width, int height, int kind){
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(uri, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return  bitmap;
    }



}
