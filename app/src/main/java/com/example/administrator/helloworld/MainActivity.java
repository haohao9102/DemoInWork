package com.example.administrator.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView api;
    private EditText name;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * 业务
     *
     * @param intent
     */
    private void work(Intent intent) {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String aaa = sp.getString("username", "");
        String bbb = sp.getString("password", "");

        String username = name.getText().toString();
        String word = password.getText().toString();

        if (username.length() <= 0 && word.length() <= 0) {
            Toast.makeText(this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
        } else if (username.length() <= 0) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (word.length() <= 0) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (!username.equals(aaa) && !word.equals(bbb)) {
            Toast.makeText(this, "账户密码错误", Toast.LENGTH_SHORT).show();
        } else if (username != null && word != null) {
            if (username.equals(aaa) && word.equals(bbb)) {
                intent = new Intent(this, HomeActivity.class);

                username = aaa;
                word = bbb;
                startActivity(intent);
                finish();
            } else if (!word.equals(bbb)) {
                Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
            } else if (!username.equals(aaa)) {
                Toast.makeText(this, "账号错误", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * 初始化控件
     */
    private void init() {

        name = (EditText) findViewById(R.id.et_name);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_login:
                work(intent);
                break;
            case R.id.btn_register:
                intent.setClass(this, RegisterActivity.class);
                break;
            case R.id.jump:
                intent.setClass(this,HomeActivity.class);
                break;
        }
        startActivity(intent);
    }
}
