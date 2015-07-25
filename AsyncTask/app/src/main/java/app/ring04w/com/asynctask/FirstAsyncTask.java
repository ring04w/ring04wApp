package app.ring04w.com.asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by ring04w on 15/7/24.
 */
public class FirstAsyncTask extends AsyncTask<Integer, Integer, String>{

    private TextView textView;
    private ProgressBar progressBar;


    public FirstAsyncTask(TextView textView, ProgressBar progressBar){
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        textView.setText("开始执行任务");
    }

    @Override
    protected  String doInBackground(Integer... params) {
        NetOperator netOperator = new NetOperator();
        int i = 0;
        for (i = 10; i < 300; i += 20){
            netOperator.operate();
            publishProgress(i);
        }
        return i + params[0].intValue() + "";

    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText("任务执行结束" + result);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        progressBar.setProgress(value);


    }
}