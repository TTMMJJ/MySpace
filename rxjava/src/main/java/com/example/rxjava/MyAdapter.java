package com.example.rxjava;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by T_baby on 17/11/03.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Supers sup;
    Context context;
    MyItemListion myItemListion;

    public MyAdapter(Supers sup, Context context) {
        this.sup = sup;
        this.context = context;
    }

    interface MyItemListion {
        void OnItemlistion(View view, int position);
    }
 public void SetMyitemListion(MyItemListion myItemListion){
        this.myItemListion=myItemListion;
 }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Supers.NewslistBean bean = sup.getNewslist().get(position);
        holder.name.setText(bean.getTitle());
        holder.time.setText(bean.getCtime());
        Uri uri = Uri.parse(bean.getPicUrl());
        holder.img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return sup.getNewslist().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView name, time;

        public MyViewHolder(final View view) {
            super(view);
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
            //点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemListion != null) {
                        myItemListion.OnItemlistion(v, getPosition());
                    }
                }
            });
        }
    }
}
