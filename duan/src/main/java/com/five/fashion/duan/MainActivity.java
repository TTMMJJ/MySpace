package com.five.fashion.duan;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar pb;
    private TextView tv_error;
    private TextView tv_progress;
    private Button btn_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //找到控件
// 初始化
        pb = (ProgressBar) findViewById(R.id.pb);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        tv_error = (TextView) findViewById(R.id.tv_failure);
        btn_down = (Button) findViewById(R.id.btn_down);

        // 开始点击事件
        btn_down.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String path = "http://c.hiphotos.baidu.com/image/pic/item/b90e7bec54e736d1e51217c292504fc2d46269f3.jpg" ;
        HttpUtils http = new HttpUtils();
        http.download(path, Environment.getExternalStorageDirectory() + "/Androidstudio.png", true, true, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                // 下载成功
                Toast.makeText(MainActivity.this, arg0.result.getPath(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(com.lidroid.xutils.exception.HttpException e, String s) {
                // 下载失败
                tv_error.setText(s);
            }
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                // 下载任务
                pb.setMax((int) total);
                pb.setProgress((int) current);
                tv_progress.setText(current * 100 / total + "%");
            }
        });
    }
}
