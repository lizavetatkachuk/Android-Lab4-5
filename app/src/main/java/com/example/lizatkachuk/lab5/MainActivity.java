package com.example.lizatkachuk.lab5;

import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import java.util.*;
import android.net.Uri;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {
    Button lab4 = (Button) findViewById(R.id.lab4);
    Button lab5 = (Button) findViewById(R.id.lab5);
    Button rot = (Button) findViewById(R.id.Rotate);
    Button slid = (Button) findViewById(R.id.Slide);
    Button fad = (Button) findViewById(R.id.Fade);
    Button zoom = (Button) findViewById(R.id.Zoom);
    ImageView img=(ImageView) findViewById(R.id.imglungs);
    ImageView imgv=(ImageView) findViewById(R.id.imageView2);
    ImageView me=(ImageView) findViewById(R.id.me);
    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.myrecycler);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lab4.setOnClickListener(Lab4Listener);
        lab5.setOnClickListener(Lab5Listener);
        rot.setOnClickListener(RotListener);
        fad.setOnClickListener(FadListener);
        zoom.setOnClickListener(ZoomListener);
        slid.setOnClickListener(SlidListener);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<String> myDataset=getAllShownImagesPath(this);
        RecyclerView.Adapter  mAdapter = new MyAdapter(myDataset,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private View.OnClickListener Lab4Listener = new View.OnClickListener() {
        public void onClick(View v) {
            setContentView(R.layout.lab4);
        }
    };
    private View.OnClickListener RotListener = new View.OnClickListener() {
        public void onClick(View v) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, View.ROTATION , 0f, 360f);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(me, View.ROTATION , 0f, 360f);
            AnimatorSet set = new AnimatorSet();
            set.play(animator1).before(animator2);
            set.start();
            animator1.setRepeatCount(0);
            animator1.setDuration(1000);
        }
    };
    private View.OnClickListener FadListener = new View.OnClickListener() {
        public void onClick(View v) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, View.ALPHA, 0,1);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(me, View.ALPHA, 0,1);
            AnimatorSet set = new AnimatorSet();
            set.play(animator1).before(animator2);
            set.start();
            animator1.setRepeatCount(0);
            animator1.setDuration(1000);
        }
    };
    private View.OnClickListener ZoomListener = new View.OnClickListener() {
        public void onClick(View v) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, View.SCALE_X, 0,5f);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(img, View.SCALE_Y, 0,5f);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(me, View.SCALE_X, 0,5f);
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(me, View.SCALE_Y, 0,5f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(animator1,animator2);
            animator1.setDuration(1000);
            set.playTogether(animator3,animator4);
            set.start();
            animator1.setRepeatCount(0);
            animator1.setDuration(1000);
        }
    };
    private View.OnClickListener SlidListener = new View.OnClickListener() {
        public void onClick(View v) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, View.TRANSLATION_X, 500);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(me, View.TRANSLATION_X, 500);
            AnimatorSet set = new AnimatorSet();
            set.play(animator1).before(animator2);
            set.start();
            animator1.setRepeatCount(0);
            animator1.setDuration(1000);
        }
    };
    private View.OnClickListener Lab5Listener = new View.OnClickListener() {
        public void onClick(View v) {
            setContentView(R.layout.lab5);

        }
    };
    public static  ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index;
        StringTokenizer st1;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA };

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index);
            listOfAllImages.add(absolutePathOfImage);
        }

        return listOfAllImages;
    }
}