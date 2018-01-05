package com.example.administrator.helloworld.cunpiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.bean.Movie;
import com.qf.chenhao.mr_chenlibrary.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class MyFragment extends BaseFragment {

    public ListView listView;
    private List<Movie> list = new ArrayList<>();
    private String type;
    private SwipeRefreshLayout swipe;

    @Override
    protected int getContentId() {
        return R.layout.frag_layout_buy;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        listView = findViewByIds(view, R.id.listview);
        swipe = findViewByIds(view, R.id.swipe);
        Bundle bundle = getArguments();
        type = bundle.getString("type");

        for (int i = 0; i < 10; i++) {
            if ("1".equals(type)) {
                list.add(new Movie(R.mipmap.timg, "摔跤吧！爸爸！", "9.5", "为圆摔跤梦！！", "万达影院"));
            }else{
                list.add(new Movie(R.mipmap.nv, "神奇的女侠！", "9.7", "超人女侠！拯救地球！", "中影IMAX影院"));
            }
        }

        MyListAdapter adapter = new MyListAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {
        super.loadDatas();
    }

    public static Fragment getInstance(String type) {
        MyFragment instance = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        instance.setArguments(bundle);
        return instance;
    }

    private class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_buy, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.image_view);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.detail = (TextView) convertView.findViewById(R.id.detail);
                holder.count = (TextView) convertView.findViewById(R.id.count);
                holder.address = (TextView) convertView.findViewById(R.id.address);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image.setImageResource(list.get(position).image);
            holder.title.setText(list.get(position).title);
            holder.address.setText(list.get(position).address);
            holder.detail.setText(list.get(position).detail);
            holder.count.setText(list.get(position).count);
            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView title, detail, count, address;
        }
    }
}
