package com.example.administrator.helloworld;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ItemViewDelegateManager;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView的万能适配器adapter使用
 * @author 爱享365
 */
public class PullFrushActivity extends AppCompatActivity {


    private static final int PAGE_SIZE = 1;//加载页数
    private static final int TOTAL_COUNTER = 50;
    private static final String TAG = "print";
    private RecyclerView recyclerView;
    private int mCurrentCounter;
    private long delayMillis = 200;
    private boolean isErr = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_frush);
        initView();
    }

    private void initView() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("这是第"+i+"数据");
        }
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        /*new CommonAdapter<String>(this,R.layout.item_recycylerviw,list) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv,s);
            }
        }*/
        final QuickAdapter quickAdapter = new QuickAdapter(R.layout.item_recycylerviw,list);
        //item加载动画
        quickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置设配器
        recyclerView.setAdapter(quickAdapter);
        //item点击事件
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(PullFrushActivity.this, "点几个第"+position+"item", Toast.LENGTH_SHORT).show();
            }
        });
        //item长按点击事件
        quickAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemLongClick: ");
                Toast.makeText(PullFrushActivity.this, "点几个第"+position+"item", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //加载头布局
        View view = LayoutInflater.from(this).inflate(R.layout.head_view, null);
        //添加头布局
        quickAdapter.addHeaderView(view);
        //获取列表的item个数
        mCurrentCounter = quickAdapter.getItemCount();
        //上拉加载
        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            quickAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据
                              //  quickAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                                mCurrentCounter = quickAdapter.getData().size();
                                quickAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                Toast.makeText(PullFrushActivity.this, "错误", Toast.LENGTH_LONG).show();
                                quickAdapter.loadMoreFail();

                            }
                        }
                    }
                },delayMillis);
            }
        },recyclerView);
        //设置自定义加载布局
       // quickAdapter.setLoadMoreView(new CustomLoadMoreView());
    }

    /**
     * 自定义加载布局
     */
    public final class CustomLoadMoreView extends LoadMoreView {

        @Override
        public int getLayoutId() {
            return 0;
        }
        /**
         * 如果返回true，数据全部加载完毕后会隐藏加载更多
         * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
         */
        @Override
        public boolean isLoadEndGone() {
            return super.isLoadEndGone();
        }

        @Override
        protected int getLoadingViewId() {
            return 0;
        }

        @Override
        protected int getLoadFailViewId() {
            return 0;
        }
        /**
         * isLoadEndGone()为true，可以返回0
         * isLoadEndGone()为false，不能返回0
         */
        @Override
        protected int getLoadEndViewId() {
            return 0;
        }
    }

    private class QuickAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public QuickAdapter(int layoutResId, List<String> data) {
            super(R.layout.item_recycylerviw, data);


        }

        @Override
        protected void convert(BaseViewHolder holder, String s) {
            int layoutPosition = holder.getLayoutPosition();//获取当前item的position
            holder.setText(R.id.tv,s);
        }
    }

    /*private class Adapter extends BaseMultiItemQuickAdapter<String>{

        public Adapter(List<String> data) {
            super(data);
            addItemType(1,1);
            addItemType(2,1);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            switch (baseViewHolder.getItemViewType()){
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }*/

}
