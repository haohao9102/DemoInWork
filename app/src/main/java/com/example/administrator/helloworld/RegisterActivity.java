package com.example.administrator.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 注册测试activity
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private EditText password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);

        Button cancle = (Button) findViewById(R.id.cancle);
        Button sure = (Button) findViewById(R.id.sure);

        sure.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure:
                text();
                break;
            case R.id.cancle:
                finish();
                break;
        }
    }

    private void text() {
        Intent intent = new Intent(this,MainActivity.class);
        if (username.getText().length()<= 0&& password.getText().length()<= 0&& password2.getText().length()<=0){
            Toast.makeText(this, "用户名密码不能空", Toast.LENGTH_SHORT).show();
        }else if (username.getText().length()<= 0){
            Toast.makeText(this, "用户名不能空", Toast.LENGTH_SHORT).show();
        }else if (password.getText().length()<= 0){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if (password2.getText().length()<=0 ){
            Toast.makeText(this, "重复密码不能为空", Toast.LENGTH_SHORT).show();
        }else if( !(password2.getText().toString().equals(password.getText().toString()))){
            Toast.makeText(this, "重复密码不同", Toast.LENGTH_SHORT).show();
        }else {
            if (username.getText() != null && password.getText() != null && password2.getText() != null) {
                intent.putExtra("username", username.getText());
                intent.putExtra("password", password.getText());
                SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("username",username.getText().toString());
                edit.putString("password",password.getText().toString());
                edit.commit();
                startActivity(intent);
            }
        }
    }
}
