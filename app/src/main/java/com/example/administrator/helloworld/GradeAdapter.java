package com.example.administrator.helloworld;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */
public class GradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener{

    private Context context;
    private List<Meizi> list;

    public GradeAdapter(Context context, List<Meizi> meizis) {
        this.context =context;
        this.list = meizis;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
        boolean onItemLongClick(View view);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(context).inflate(R.layout.grid_meizi_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            return holder;
        }else{
            MyViewHolder2 holder2=new MyViewHolder2(LayoutInflater.from(context).inflate(R.layout.page_item, parent, false));
            return holder2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            Glide.with(context.getApplicationContext()).load(list.get(position).getUrl())
                    .into(((MyViewHolder) holder).iv);
        }else if(holder instanceof MyViewHolder2){
            ((MyViewHolder2) holder).tv.setText(list.get(position).getPage()+"页");
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(list.get(position).getUrl())){
            return 0;
        }else {
            return 1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageButton iv;

        public MyViewHolder(View view)
        {
            super(view);
            iv = (ImageButton) view.findViewById(R.id.iv);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder
    {
        private TextView tv;

        public MyViewHolder2(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }

}
