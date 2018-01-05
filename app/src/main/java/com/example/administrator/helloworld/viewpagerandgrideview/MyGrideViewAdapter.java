package com.example.administrator.helloworld.viewpagerandgrideview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class MyGrideViewAdapter extends BaseAdapter {

    private Context context;
    private List<ProductListBean> list;
    private LayoutInflater inflater;
    private int pageSize;
    private int mIndex;//页数下标，表示第几页，从0开始

    public MyGrideViewAdapter(Context context, List<ProductListBean> listDatas, int mPageSize,int mIndex) {
        this.context = context;
        this.list = listDatas;
        this.pageSize = mPageSize;
        this.mIndex = mIndex;
        inflater = LayoutInflater.from(context);
    }
    /**
     * 先判断数据集的大小是否足够显示满本页？listData.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量mPagerSize的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个 (listData.size() - mIndex*mPagerSize)
     */
    @Override
    public int getCount() {
        return list.size() > (mIndex+1)* pageSize ? pageSize :(list.size() - mIndex* pageSize);
    }

    @Override
    public Object getItem(int position) {
        return list.get(position+mIndex + pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.girdeview_item_layout,parent,false);
            holder.proName = (TextView) convertView.findViewById(R.id.proName);
            holder.imgUrl = (ImageView) convertView.findViewById(R.id.imgUrl);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position（因为拿到的是总的数据源，数据源是分页加载到每页的GridView上的，为了确保能正确的点对不同页上的item）
        final int pos = position + mIndex * pageSize;//假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        ProductListBean productListBean = list.get(pos);
        holder.proName.setText(productListBean.getProName());
        holder.imgUrl.setImageResource(productListBean.getImgUrl());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"你点击了 "+list.get(pos).getProName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView proName;
        ImageView imgUrl;
    }
}
