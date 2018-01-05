package com.qf.chenhao.mr_chenlibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.chenhao.mr_chenlibrary.R;
import com.qf.chenhao.mr_chenlibrary.eventbus.Event;
import com.qf.chenhao.mr_chenlibrary.eventbus.EventBusUtil;
import com.qf.chenhao.mr_chenlibrary.util.ScreenUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }
    /**
     * 是否注册事件分发
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }
    /**
     * 接收到分发到事件
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }
    /**
     * 接受到分发的粘性事件
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentId(), container, false);
        ButterKnife.bind(view);

        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) activity;
            if (baseActivity.isOpenStatus()) {
                View actionbar = view.findViewById(R.id.actionbar);
                if (actionbar != null) {
                    int statusHeight = ScreenUtil.getStatusHeight(activity);
                    if (statusHeight != -1) {
                        actionbar.setPadding(0, statusHeight, 0, 0);
                    }
                }
            }
        }

        return view;
    }

    protected abstract int getContentId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        loadDatas();
    }

    protected void loadDatas() {

    }

    protected void init(View view) {
    }

    protected <T> T findViewByIds(View view, int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            getDatas(bundle);
        }
    }

    protected void getDatas(Bundle bundle){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()){
            EventBusUtil.unRegister(this);
        }
    }
}
