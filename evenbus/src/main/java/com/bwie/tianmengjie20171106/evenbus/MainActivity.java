package com.bwie.tianmengjie20171106.evenbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    Button btn,btn3;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        EventBus.getDefault().register(MainActivity.this);
        btn = (Button) findViewById(R.id.btn1);
        btn3 = (Button) findViewById(R.id.btn3);
        tv = (TextView)findViewById(R.id.tv);
        //非粘性事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });
        //粘性事件
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new FirstEvent("Hello,Everyone,my name is tianmengjie"));
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false)
    public void onEventMainThread(FirstEvent event) {
        String msg = "收到了消息：" + event.getMsg();
        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
