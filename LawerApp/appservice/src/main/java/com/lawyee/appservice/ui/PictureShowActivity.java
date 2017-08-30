package com.lawyee.appservice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lawyee.appservice.R;

public class PictureShowActivity extends AppCompatActivity {
    /**
     * 图片 的oid
     */
    public static final String INTENT_PARAMETER_PICTURE="pictureoid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_show);
    }
}
