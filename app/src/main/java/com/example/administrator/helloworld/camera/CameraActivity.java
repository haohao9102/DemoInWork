package com.example.administrator.helloworld.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

import java.io.File;

/**
 * Created by Administrator on 2017/4/18.
 */

public class CameraActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private ImageView iv;
    private File targetFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        iv = (ImageView) findViewById(R.id.iv);

    }

    public void cam(View view){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // 设置 EXTRA_OUTPUT 数值，来指定保存的文件位置；
            // 数据类型为 Uri
            targetFile = null;

            String state = Environment.getExternalStorageState();

            File dir = null;

            if(state.equals(Environment.MEDIA_MOUNTED)){
                // 获取公共的照相机拍照存储文件的位置
//            dir =
//                    Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_DCIM
//                    );
//            if(!dir.exists()){
                // 因为公共的拍照位置不存在
                // 获取存储卡中的特定目录
                dir = Environment.getExternalStorageDirectory();

                dir = new File(dir, "MediaRecorder/Images");

                if(!dir.exists()){
                    dir.mkdirs();
                }
//            }
            }else{
                // 内部存储区
                dir = getFilesDir();
            }

            // 最终目标文件的位置
            targetFile = new File(dir, "Image-" + System.currentTimeMillis()+".jpg");
            Uri uri = Uri.fromFile(targetFile);// 图片存储的位置
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 199);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 199){
            Log.d("Capture", "199 result = " + resultCode);

            if (resultCode == RESULT_OK){
                if(targetFile != null && targetFile.exists()){
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inSampleSize = 2; // 压缩加载
                    Bitmap bitmap = BitmapFactory.decodeFile(
                            targetFile.getAbsolutePath(),
                            opts
                    );
                    iv.setImageBitmap(bitmap);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 998);
            }else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
