package com.example.administrator.helloworld.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.helloworld.R;

/**
 * Created by Administrator on 2017/4/27.
 */
public class SearchActivity extends AppCompatActivity{

    private String TAG = getClass().getSimpleName() ;
    private EditText searchView;
    private ListView listView;
    private String[] string = {"王牌逗王牌","28岁未成年","疯狂动物城","北京卫视高清","江苏卫视高清","浙江卫视高清","湖南卫视高清"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_view_layout);

        searchView = (EditText) findViewById(R.id.search_view);
        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,string);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);



        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                }else{
                    listView.clearTextFilter();
                }
                return true;
            }
        });*/
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyworld = s.toString();
                if (!TextUtils.isEmpty(keyworld)){
                    listView.setFilterText(keyworld);//设置listview的搜索结果
                    listView.dispatchDisplayHint(View.INVISIBLE);//设置黑色提示框是否显示

                }else{
                    listView.clearTextFilter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
