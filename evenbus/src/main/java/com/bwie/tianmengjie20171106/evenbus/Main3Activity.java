package com.bwie.tianmengjie20171106.evenbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main3Activity extends AppCompatActivity {
    Button find;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        find = findViewById(R.id.find);
        textView = findViewById(R.id.main3text);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EventBus.getDefault().isRegistered(Main3Activity.this)) {
                    EventBus.getDefault().register(Main3Activity.this);
                }
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDate(FirstEvent first) {
        textView.setText("姓名：" + first.getMsg());
    }
}
