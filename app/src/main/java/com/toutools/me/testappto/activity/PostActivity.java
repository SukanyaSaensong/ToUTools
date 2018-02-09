package com.toutools.me.testappto.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inthecheesefactory.thecheeselibrary.widget.AdjustableImageView;
import com.kbeanie.multipicker.api.ImagePicker;

import com.toutools.me.testappto.R;

import com.toutools.me.testappto.fragment.PostFragment;



public class PostActivity extends AppCompatActivity {
    ImagePicker imagePicker;
    AdjustableImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PostFragment())
                    .commit();
        }

    }



}