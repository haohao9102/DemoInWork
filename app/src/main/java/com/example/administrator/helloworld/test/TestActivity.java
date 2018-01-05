//package com.example.administrator.helloworld.test;
//
//import android.graphics.Color;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import com.chaychan.viewlib.NumberRunningTextView;
//import com.example.administrator.helloworld.R;
//import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;
//import com.qf.chenhao.mr_chenlibrary.eventbus.C;
//import com.qf.chenhao.mr_chenlibrary.eventbus.Event;
//
//import butterknife.Bind;
//import butterknife.BindView;
//
///**
// * Created by Administrator on 2017/5/5.
// */
//public class TestActivity extends BaseActivity {
//
//    @BindView(R.id.swipe)
//    public SwipeRefreshLayout swipe;
//    @BindView(R.id.money)
//    public NumberRunningTextView money;
//    @BindView(R.id.number)
//    public NumberRunningTextView number;
//
//    @Override
//    protected int getContentId() {
//        return R.layout.test_activity_layout;
//    }
//
//    @Override
//    public void setListener() {
//
//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                money.setContent("12345");
//                number.setContent("400");
//                swipe.setRefreshing(false);
//            }
//
//        });
//    }
//
//    public void send(View view) {
//        Button button = (Button) view;
//        switch (view.getId()) {
//            case R.id.send_message:
//                startActivity(Second.class);
//                break;
//            case R.id.send_code:
//                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(60000, 1000, button);
//                countDownTimerUtils.start();
//                break;
//        }
//    }
//
//
//    @Override
//    protected void loadDatas() {
//        super.loadDatas();
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//
//        money.setContent("156345");
//        number.setContent("200");
//
//        swipe.setColorSchemeColors(Color.parseColor("#ff7300"));
//    }
//
//    @Override
//    protected boolean isOpenStatus() {
//        return false;
//    }
//
//    @Override
//    protected boolean isRegisterEventBus() {
//        return true;
//    }
//
//    @Override
//    public void onEventBusCome(Event event) {
//        switch (event.getCode()) {
//            case C.EventCode.A:
//                String str = (String) event.getData();
//                Log.d("EventBus", "接收到A类型的Event" + str);
//                break;
//            case C.EventCode.B:
//                Log.d("EventBus", "接收到B类型的Event");
//                break;
//        }
//    }
//}
