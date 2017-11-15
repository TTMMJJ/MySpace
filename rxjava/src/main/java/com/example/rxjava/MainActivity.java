package com.example.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.rxjava.R.id.recycle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(recycle);
        Rx1();
    }
    private void Rx1() {
        //创建Retrofit
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(API.PATHA).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        final APIServers apiServers = retrofit.create(APIServers.class);
        Observable<Supers> observable = apiServers.getNoParams();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Supers>() {
                    @Override
                    public void onCompleted() {
                        Log.d("33333333", "sffffffffffffffffff");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("dfdfdfdfdfdfdf", "fsfssf");
                    }
                    @Override
                    public void onNext(final Supers supers) {
                        Log.d("111111", supers.getNewslist().get(4).toString());
                        MyAdapter myAdapter = new MyAdapter(supers, MainActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(myAdapter);
                        myAdapter.SetMyitemListion(new MyAdapter.MyItemListion() {
                            @Override
                            public void OnItemlistion(View view, int position) {
                                String url = supers.getNewslist().get(position).getUrl();
                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                intent.putExtra("path", url);
                                startActivity(intent);
                            }
                        });
                    }
                });

    }
}
