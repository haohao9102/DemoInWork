package com.example.administrator.helloworld.listview;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.helloworld.R;

import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by Administrator on 2017/5/17.
 */

class TestAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter{
    private Context context;
    private List<String> list1;
    private LayoutInflater inflater;

    public TestAdapter(Context context, List<String> list) {
        this.context = context;
        this.list1 = list;
    }


    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

       if (position%2 == 0){
           return 1;
       }else {
           return 2;
       }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            inflater = LayoutInflater.from(context);
            switch (type) {
                case 1:
                    convertView = inflater.inflate(R.layout.listviw_item_layout1, parent, false);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_1);
                    convertView.setTag(holder);
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.listviw_item_layout2, parent, false);
                    holder.textView2 = (TextView) convertView.findViewById(R.id.tv);
                    convertView.setTag(holder);
                    break;
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (type){
            case 1:
                holder.textView.setText(list1.get(position));
                break;
            case 2:
                holder.textView2.setText(list1.get(position));
                break;
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) == 1;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == 1;
    }

    class ViewHolder{
        TextView textView ,textView2;
    }

}
