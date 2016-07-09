package com.example.m.prainbuiltcameraapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int LICZBA=1;
    private static final int LICZBAKamera=2;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.imageView);


    }

    public void buttonek(View view){
    File fajl = getFile("sdcard/moje_zdjecia");


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fajl));
        startActivityForResult(intent,LICZBA);

    }


    public File getFile(String path){

        File folder = new File(path);
        if(!folder.exists()){

            folder.mkdirs();
        }

        File plik = new File(folder,"zdjecie.jpg");
        return plik;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==LICZBA){
            Log.d("Uwaga","startActivityFor");
            image.setImageDrawable(Drawable.createFromPath("sdcard/moje_zdjecia/zdjecie.jpg"));

        }
        if(requestCode==LICZBAKamera){
            Log.d("Uwaga","startActivityFor2");
            Toast.makeText(this,"Zrobione",Toast.LENGTH_LONG).show();



        }



    }

    public void buttonekkamera(View view) {
        String pathCamera= "sdcard/kamera";
       File getKamera = getFileKamera (pathCamera);
        Uri uri = Uri.fromFile(getKamera);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent,LICZBAKamera);



    }

    private File getFileKamera(String pathCamera) {
        File fileKamera1 = new File(pathCamera);
        if(!fileKamera1.exists()){
            fileKamera1.mkdirs();
        }
        File fileKamera2 = new File(fileKamera1,"filmik.mp4");
        return  fileKamera2;

    }
}
